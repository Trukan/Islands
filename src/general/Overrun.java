package general;

import java.util.Stack;

//class for crawl islands and find their form
public class Overrun{
	static Map map;
	static volatile int callOverrunCount = 0;
	int n, m;
	Pattern pat;
	Stack<Cell> stack;
	Cell currCell;

	public Overrun(int n, int m) {
		setStartLand(n, m);
	}

	public void setStartLand(int n, int m) {
		this.n = n;
		this.m = m;
	}

	
	public void run() {
		stack = new Stack<Cell>();
		int sizestack = 0;
		if (map.getNumCell(this.n, this.m) == 0) {

			int temp = getCallNum();
			pat = new Pattern(temp);
			if (checkAndMarkStep(this.n, this.m, temp)) {
				while (!stack.isEmpty()) {
					currCell = stack.peek();
					sizestack = stack.size();
					if ((currCell.n == 0 || checkAndMarkStep(currCell.n - 1, currCell.m, temp))
							& (currCell.m == map.getMtr()[0].length - 1
									|| checkAndMarkStep(currCell.n, currCell.m + 1, temp))
							& (currCell.n == map.getMtr().length - 1
									|| checkAndMarkStep(currCell.n + 1, currCell.m, temp))
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
			Main.patAr.addElement(pat);
		}
	}

	// false - cell reserved over priority thread, need stop research this island by
	// this thread
	// true - cell marked in map.numcell
	// or marked earlier with current threadNum - numCell
	// or cell with koord n,m !isLand
	private boolean checkAndMarkStep(int n, int m, int numCell) {
		if (map.getMtr(n, m) == 1) {
			synchronized (map) {
			if (map.changeCell(n, m, numCell)) {
				stack.push(new Cell(n, m));
				pat.addCell(n, m);
			} else {
				if (map.getNumCell(n, m) == numCell) {
					return true;
				} else {
					return false;
				}
			}
			}
		}
		return true;
	}

	private static synchronized int getCallNum() {
		return ++callOverrunCount;
	}

	public static void setMap(Map m) {
		map = m;
	}

	public static Map getMap() {
		return map;
	}

}
