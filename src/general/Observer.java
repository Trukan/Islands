package general;

import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Observer implements Runnable {
	static Map map;
	static int overrunCount = 0;
	static volatile int countRun=0;
	static LinkedBlockingQueue<Pattern> queue;
	int startLine, endLine;
	private int[][] markMtr;
	Stack<Cell> stack;
	Pattern pat;

	public Observer(int start, int end) {
		this.startLine = start;
		this.endLine = end;
	}

	@Override
	public void run() {
		++countRun;
		int m = map.getMtr()[0].length;
		initMarkerMatrix(map.getMtr().length, m);
		for (int i = startLine; i < endLine; i++) {
			for (int j = 0; j < m; j++) {
				if (map.getMtr(i, j) == 0)
					continue;
				if (map.getMtr(i, j) == 1) {
					if (j > 0 && map.getMtr(i, j - 1) == 1)
						continue;
					if (i > 0 && map.getMtr(i - 1, j) == 1)
						continue;
					checkCellandOverrun(i, j);
				}
			}
		}
		if(--countRun==0) {
			
		};
	}

	// check Land or not for start overrun
	void checkCellandOverrun(int n, int m) {
		if (markMtr[n][m] == 0) {
			overrun(n, m);
		}
	}

	private void initMarkerMatrix(int n, int m) {
		markMtr = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				markMtr[i][j] = 0;
			}
		}
	}

	public void overrun(int n, int m) {
		stack = new Stack<Cell>();
		Cell currCell;
		int sizestack = 0;
		if (markMtr[n][m] == 0) {
			int temp = ++overrunCount;
			pat = new Pattern(temp);
			if (checkAndMarkStep(n, m, temp)) {
				while (!stack.isEmpty()) {
					currCell = stack.peek();
					sizestack = stack.size();
					if ((currCell.n == 0 || checkAndMarkStep(currCell.n - 1, currCell.m, temp))
							& (currCell.m == map.getMtr()[0].length - 1 || checkAndMarkStep(currCell.n, currCell.m + 1, temp))
							& (currCell.n == map.getMtr().length - 1 || checkAndMarkStep(currCell.n + 1, currCell.m, temp))
							& (currCell.m == 0 || checkAndMarkStep(currCell.n, currCell.m - 1, temp))) {
						if (sizestack == stack.size())
							stack.pop();
						else
							continue;
					} else {
						return;
					}
				}
			} else {
				return;
			}
			pat.optimal();
			try {
				queue.put(pat);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// false - cell reserved over priority thread, need stop research this island by
	// this thread
	// true - cell marked in map.numcell
	// or marked earlier with current threadNum - numCell
	// or cell with koord n,m !isLand
	private boolean checkAndMarkStep(int n, int m, int numCell) {
		if (map.getMtr(n, m) == 1) {
			if (changeCell(n, m, numCell)) {
				stack.push(Cell.getCell(n, m));
				pat.addCell(n, m);
			} else {
				if (markMtr[n][m] == numCell) {
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	private boolean changeCell(int n, int m, int numCell) {
		if (markMtr[n][m] == 0 | markMtr[n][m] > numCell) {
			markMtr[n][m] = numCell;
			return true;
		} else {
			return false;
		}
	}

	public static void setMap(Map m) {
		map = m;
	}

	public static void setQueue(LinkedBlockingQueue<Pattern> q) {
		queue = q;
		
	}

}
