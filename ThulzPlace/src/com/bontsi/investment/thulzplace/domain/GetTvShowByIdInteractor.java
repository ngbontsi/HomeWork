/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bontsi.investment.thulzplace.domain;

import java.util.concurrent.Executor;

import android.support.annotation.MainThread;

import com.bontsi.investment.thulzplace.domain.exception.TvShowNotFoundException;
import com.bontsi.investment.thulzplace.domain.tvshow.Catalog;
import com.bontsi.investment.thulzplace.domain.tvshow.TvShow;
import com.bontsi.investment.thulzplace.executor.Interactor;
import com.bontsi.investment.thulzplace.util.RandomUtils;
import com.bontsi.investment.thulzplace.util.StringUtils;

/**
 * GetTvShowById implementation. This interactor will go out of the UI thread
 * using the executor, then will get a TvShow from the Catalog using the TvShow
 * identifier and will return the result over the main thread using a callback
 * and MainThread dependency.
 * 
 * This application is a sample about how to work effectively on Android, this
 * interactor will return an error randomly.
 * 
 * This interactor also contains a little delay to simulate a internal http
 * request.
 * 
 * @author Pedro Vicente Gómez Sánchez
 */
class GetTvShowByIdInteractor implements Interactor, GetTvShowById {

	private static final int PERCENTAGE_OF_FAILS = 50;
	private static final long WAIT_TIME = 1500;

	private final Executor executor;
	private final MainThread mainThread;
	private final Catalog catalog;

	private String tvShowId;
	private Callback callback;

	@Inject
	GetTvShowByIdInteractor(Executor executor, MainThread mainThread,
			Catalog catalog) {
		this.executor = executor;
		this.mainThread = mainThread;
		this.catalog = catalog;
	}

	@Override
	public void execute(final String tvShowId, final Callback callback) {
		validateArguments(callback, tvShowId);
		this.callback = callback;
		this.tvShowId = tvShowId;
		executor.run(this);
	}

	@Override
	public void run() {
		waitToDoThisSampleMoreInteresting();

		if (haveToShowError()) {
			notifyConnectionError();
		} else {
			searchTvShow();
		}
	}

	/**
	 * To simulate a we are getting the TvShows data from internet we are going
	 * to force a 1.5 seconds delay using Thread.sleep.
	 */
	private void waitToDoThisSampleMoreInteresting() {
		try {
			Thread.sleep(WAIT_TIME);
		} catch (final InterruptedException e) {
			// Empty
		}
	}

	private boolean haveToShowError() {
		return RandomUtils.percent(PERCENTAGE_OF_FAILS);
	}

	private void searchTvShow() {
		TvShow tvShow = null;
		try {
			tvShow = catalog.getTvShowById(tvShowId);
		} catch (final TvShowNotFoundException e) {
			notifyTvShowNotFound();
		}
		notifyTvShowFound(tvShow);
	}

	private void validateArguments(Callback callback, String tvShowId) {
		if (StringUtils.isNullOrEmpty(tvShowId)) {
			throw new IllegalArgumentException(
					"TvShowId parameter can't be null");
		}
		if (callback == null) {
			throw new IllegalArgumentException(
					"Callback parameter can't be null");
		}
	}

	private void notifyConnectionError() {
		mainThread.post(new Runnable() {
			@Override
			public void run() {
				callback.onConnectionError();
			}
		});
	}

	private void notifyTvShowFound(final TvShow tvShow) {
		mainThread.post(new Runnable() {
			@Override
			public void run() {
				callback.onTvShowLoaded(tvShow);
			}
		});
	}

	private void notifyTvShowNotFound() {
		mainThread.post(new Runnable() {
			@Override
			public void run() {
				callback.onTvShowNotFound();
			}
		});
	}
}
