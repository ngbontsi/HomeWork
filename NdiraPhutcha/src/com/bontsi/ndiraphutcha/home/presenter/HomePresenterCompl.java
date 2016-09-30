package com.bontsi.ndiraphutcha.home.presenter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.addmenu.StockActivity;
import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.fragment.FragmentsActivity;
import com.bontsi.ndiraphutcha.order.OrderActivity;
import com.bontsi.ndiraphutcha.product.ProductActivity;
import com.bontsi.ndiraphutcha.tables.ImageItem;
import com.bontsi.ngdevelopmentframework.presenters.IHomePresenter;
import com.bontsi.ngdevelopmentframework.utils.ActivityHolder;
import com.bontsi.ngdevelopmentframework.view.IHomeView;

/**
 * Created by kaede on 2015/5/19.
 */
public class HomePresenterCompl implements IHomePresenter {

	public static ActivityHolder activityHolder;
	private List<ImageItem> images;
	static {
		activityHolder = new ActivityHolder();
		activityHolder.addActivity(ApplicationConstants.Products.getValue(),
				ProductActivity.class);
		activityHolder.addActivity(ApplicationConstants.Stock.getValue(),
				StockActivity.class);
		activityHolder.addActivity(ApplicationConstants.Order.getValue(),
				OrderActivity.class);
		activityHolder.addActivity(ApplicationConstants.Search.getValue(),
				FragmentsActivity.class);
	}
	Context context;
	IHomeView homeView;

	public HomePresenterCompl(Context context, IHomeView homeView) {
		this.context = context;
		this.homeView = homeView;
	}

	@Override
	public void loadDatas() {
		images = new ArrayList<ImageItem>();
		for (final String menu : activityHolder.getNameList()) {
			final ImageItem data = new ImageItem();
			if (menu.equals(ApplicationConstants.Products.getValue())) {
				data.setImage(convetrDrawable(R.drawable.product));
			}
			if (menu.equals(ApplicationConstants.Stock.getValue())) {
				data.setImage(convetrDrawable(R.drawable.stock));
			}
			if (menu.equals(ApplicationConstants.Order.getValue())) {
				data.setImage(convetrDrawable(R.drawable.ordering));
			}
			if (menu.equals(ApplicationConstants.Search.getValue())) {
				data.setImage(convetrDrawable(R.drawable.viewing));
			}
			data.setTitle(menu);
			images.add(data);
		}
		homeView.onGetDataList(images);
	}

	@Override
	public void onItemClick(int position) {
		final Class activity = activityHolder.getActivity(activityHolder
				.getNameList().get(position));
		if (activity != null) {
			context.startActivity(new Intent(context, activity));
		}
	}

	private Bitmap convetrDrawable(int resource) {
		Drawable myDrawable;
		if (android.os.Build.VERSION.SDK_INT >= 21) {
			myDrawable = context.getResources().getDrawable(resource,
					context.getTheme());

		} else {
			myDrawable = context.getResources().getDrawable(resource);

		}
		return ((BitmapDrawable) myDrawable).getBitmap();

	}
}
