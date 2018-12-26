package org.eltn.projects.core.tests.utils;

import org.apache.log4j.Logger;
import org.eltn.projects.core.tests.asserts.SoftAssert;
import org.eltn.projects.core.tests.base.BaseTest;
import org.eltn.projects.core.utils.DateUtil;
import org.eltn.projects.core.utils.ThreadUtil;
import org.testng.annotations.Test;

public class DateUtilTest extends BaseTest {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private final long SLEEP = 500;
	private final long TIMEOUT = SLEEP + DateUtil.MS_IN_SECOND;

	@Test
	public void isTimeout_false_test() {
		final long sleep = SLEEP;
		final long timeout = TIMEOUT;
		
		long startTime = System.currentTimeMillis();

		logger.info("Sleep for [" + sleep + "] ms");
		ThreadUtil.sleep(sleep);

		SoftAssert.assertTrueNow(DateUtil.isTimeout(startTime, timeout) == false,
				"Should not be timeout after sleep for [" + sleep + "] ms and timeout parameter is [" + timeout + "]",
				"Verify that isTimeout return false after sleep for [" + sleep + "] and timeout parameter is ["
						+ timeout + "]");
	}
	
	@Test
	public void isTimeout_true_test() {
		final long sleep = TIMEOUT;
		final long timeout = SLEEP;
		
		long startTime = System.currentTimeMillis();

		logger.info("Sleep for [" + sleep + "] ms");
		ThreadUtil.sleep(sleep);

		SoftAssert.assertTrueNow(DateUtil.isTimeout(startTime, timeout),
				"Should be timeout after sleep for [" + sleep + "] ms and timeout parameter is [" + timeout + "]",
				"Verify that isTimeout return true after sleep for [" + sleep + "] and timeout parameter is ["
						+ timeout + "]");
	}
	
	@Test
	public void isTimeout_false_timeout_parameter_0_test() {
		final long sleep = TIMEOUT;
		final long timeout = 0;
		
		long startTime = System.currentTimeMillis();

		logger.info("Sleep for [" + sleep + "] ms");
		ThreadUtil.sleep(sleep);

		SoftAssert.assertTrueNow(DateUtil.isTimeout(startTime, timeout) == false,
				"Should not be timeout after sleep for [" + sleep + "] ms and timeout parameter is [" + timeout + "]",
				"Verify that isTimeout return false after sleep for [" + sleep + "] and timeout parameter is ["
						+ timeout + "]");
	}
}
