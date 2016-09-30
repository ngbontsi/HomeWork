package com.bontsi.ndiraphutcha.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ApplicationLogger {

	private Formatter formatterHTML;
	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private ConsoleHandler consol;
	private SimpleFormatter formatterTxt;

	public ApplicationLogger() throws Exception {
		setup();
	}

	private void setup() throws Exception {
		consol = new ConsoleHandler();
		logger.addHandler(consol);

	}

	public <T> void activityStatrtUp(Class<T> clazz) {
		logger.info("Starting " + clazz.getName());

	}

	public <T> void activityStatrtUp(Class<T> clazz, String methodName)
			throws Exception {
		logger.info(clazz.getName() + " calling method " + methodName);

	}

	public void loagError(Exception e) {
		logger.severe(e.getMessage() + "Stack " + e.getStackTrace());
	}
}
