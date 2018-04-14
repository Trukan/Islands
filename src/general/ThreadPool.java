package general;

public class ThreadPool {
	Thread[] th;
	public ThreadPool(int num) {
		th = new Thread[num];
		for (int i = 0; i < th.length; i++) {
			th[i]=new Thread();
		}
	}
	Thread getThread() {
		int i=0;
		while(th[i].isAlive()) {
			i++;
		if(i==th.length) {
			i=0;
			try {
				Thread.currentThread().wait(1);
				System.out.print(",");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		return th[i];
	}
	boolean anyThreadsAlive() {
		for (int i = 0; i < th.length; i++) {
			if(th[i].isAlive())
				return true;
		}
		return false;
	}
	void join() {
		for (int i = 0; i < th.length; i++) {
			try {
				th[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
