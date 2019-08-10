package io.github.etuzon.projects.core.base;

import io.github.etuzon.projects.core.enums.ThreadStateEnum;

/**********************************************
 * 
 * Abstract class that implement thread states.
 * 
 * @author etuzon
 *
 */
public abstract class ThreadBase extends Thread {
	private ThreadStateEnum threadState = ThreadStateEnum.NOT_STARTED;

	/**********************************************
	 * Constructor.
	 */
	protected ThreadBase() {
	}

	/**********************************************
	 * Change thread state to RUNNING and start thread.
	 */
	public void start() {
		if (getThreadState() == ThreadStateEnum.NOT_STARTED) {
			synchronized (threadState) {
				threadState = ThreadStateEnum.RUNNING;
			}

			super.start();
		}
	}

	/**********************************************
	 * Change thread state so thread will be stopped.
	 * 
	 * NOT_STARTED -> DEAD.
	 * RUNNING -> STOPPING.
	 */
	public void shutdown() {
		synchronized (threadState) {
			if (getThreadState() == ThreadStateEnum.NOT_STARTED) {
				threadState = ThreadStateEnum.DEAD;
			} else if (getThreadState() == ThreadStateEnum.RUNNING) {
				threadState = ThreadStateEnum.STOPPING;
			}
		}
	}

	/**********************************************
	 * Get thread state.
	 * @return Thread state.
	 */
	public ThreadStateEnum getThreadState() {
		synchronized (threadState) {
			if (this.isAlive() == false) {
				if ((threadState != ThreadStateEnum.NOT_STARTED) && (threadState != ThreadStateEnum.DEAD)) {
					threadState = ThreadStateEnum.DEAD;
				}
			}

			return threadState;
		}
	}

	/*********************************************
	 * Return true if thread is running, else return false.
	 * @return true if thread is running, else return false.
	 */
	public boolean isRunning() {
		synchronized (threadState) {
			return threadState == ThreadStateEnum.RUNNING;
		}
	}
}