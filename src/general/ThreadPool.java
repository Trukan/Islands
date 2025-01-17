package general;

public class ThreadPool {
	Thread[] th;

	public ThreadPool(int num) {
		th = new Thread[num];
		for (int i = 0; i < th.length; i++) {
			th[i] = new Thread();
		}
	}

	Thread getThread(Runnable r, String name) {
		int i = 0;
		while (th[i].isAlive()) {
			i++;
			if (i == th.length) {
				i = 0;
				}
		}
		th[i] = new Thread(r, name);
		return th[i];
	}

	boolean anyThreadsAlive() {
		for (int i = 0; i < th.length; i++) {
			if (th[i].isAlive())
				return true;
		}
		return false;
	}

	public void join() {
		for (int i = 0; i < th.length; i++) {
			try {
				th[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
