package io.github.etuzon.projects.core.tests.utils;

import org.testng.annotations.Test;

import io.github.etuzon.projects.core.base.ThreadBase;
import io.github.etuzon.projects.core.enums.ThreadStateEnum;
import io.github.etuzon.projects.core.utils.ThreadUtil;
import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;
import io.github.etuzon.unit.tests.base.BaseUnitTest;

public class ThreadTest extends BaseUnitTest {
	public static final long SLEEP_AFTER_STOPPING_THREAD = 10 * ThreadUtil.SECOND_1;

	private LocalThread thread = null;

	@Test(priority = 1)
	public void verify_thread_not_started_state_test() {
		thread = new LocalThread();
		verifyThreadState(thread, ThreadStateEnum.NOT_STARTED);
	}

	@Test(priority = 2)
	public void verify_thread_running_state_test() {
		thread.start();
		ThreadUtil.sleep(ThreadUtil.SECOND_1);
		verifyThreadState(thread, ThreadStateEnum.RUNNING);
	}

	@Test(priority = 3)
	public void verify_thread_stopping_state_test() {
		thread.shutdown();
		verifyThreadState(thread, ThreadStateEnum.STOPPING);
	}

	@Test(priority = 4)
	public void verify_thread_dead_state_test() {
		ThreadUtil.sleep(SLEEP_AFTER_STOPPING_THREAD + 2 * ThreadUtil.SECOND_1);
		verifyThreadState(thread, ThreadStateEnum.DEAD);
	}

	@Test(priority = 5)
	public void verify_thread_not_start_again_negative_test() {
		thread.start();
		verifyThreadState(thread, ThreadStateEnum.DEAD);
	}
	
	@Test(priority = 6)
	public void shutdown_thread_after_thread_dead_negative_test() {
		thread.shutdown();
		verifyThreadState(thread, ThreadStateEnum.DEAD);
	}
	
	@Test(priority = 7)
	public void start_thread_twice_negative_test() {
		thread = new LocalThread();
		thread.start();
		thread.start();
		verifyThreadState(thread, ThreadStateEnum.RUNNING);
	}
	
	@Test(priority = 8)
	public void stop_thread_twice_and_verify_stopping_state_negative_test() {
		thread.shutdown();
		thread.shutdown();
		verifyThreadState(thread, ThreadStateEnum.STOPPING);
	}
	
	@Test(priority = 9)
	public void start_thread_when_it_is_in_stopping_state_negative_test() {
		thread.start();
		verifyThreadState(thread, ThreadStateEnum.STOPPING);
	}
	
	@Test(priority = 10)
	public void verify_thread_dead_state2_test() {
		ThreadUtil.sleep(SLEEP_AFTER_STOPPING_THREAD + 2 * ThreadUtil.SECOND_1);
		verifyThreadState(thread, ThreadStateEnum.DEAD);
	}
	
	private void verifyThreadState(LocalThread thread, ThreadStateEnum expectedThreadState) {
		SoftAssertUnitTest.assertTrueNow(thread.getThreadState() == expectedThreadState,
				"Thread state is [" + thread.getThreadState() + "] and should be [" + expectedThreadState + "]",
				"Verify that thread state is [" + expectedThreadState + "]");
	}

	private class LocalThread extends ThreadBase {
		public LocalThread() {
		}

		public void run() {
			super.run();
			while (isRunning()) {
				ThreadUtil.sleep(ThreadUtil.SECOND_1);
			}

			ThreadUtil.sleep(SLEEP_AFTER_STOPPING_THREAD);
		}
	}
}
