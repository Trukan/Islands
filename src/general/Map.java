package general;

import java.util.Vector;

public class Map {
	private byte[][] mtr;
	private volatile int[][] numCell;
	private Vector<Pattern> set;
	private boolean counted = false;

	public Map(int n, int m) {
		setMtr(new byte[n][m]);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				setMtr(i, j, (byte) 0);
			}
		}
		initCellArray(n, m);
		set = new Vector<Pattern>();
	}

	public Map(String[] args) {
		try {
			int n = Integer.parseInt(args[0]);
			int m = Integer.parseInt(args[1]);
			this.mtr = new byte[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					setMtr(i, j, Byte.parseByte(args[i * m + j + 2]));
				}
			}
			initCellArray(n, m);
		} catch (NumberFormatException e) {
			System.out.println("parse for map error");
			System.exit(5);
		}
		set = new Vector<Pattern>();
	}

	private void initCellArray(int n, int m) {
		numCell = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				numCell[i][j] = 0;
			}
		}
	}

	public /* synchronized */ boolean changeCell(int n, int m, int numCell) {
		boolean res;
		if (this.numCell[n][m] == 0 | this.numCell[n][m] > numCell) {
			this.numCell[n][m] = numCell;
			res = true;
		} else {
			res = false;
		}
		// notifyAll();
		return res;
	}

	public int getNumUniqForm() {
		int numUniq = 0;
		if (set.size() == 1)
			return 1;
		boolean[] dublicateForm = new boolean[set.size()];

		for (int i = 0; i < set.size(); i++) {
			dublicateForm[i] = false;
		}
		for (int i = 0; i < set.size() - 1; i++) {
			if (dublicateForm[i])
				continue;
			numUniq++;
			for (int j = i + 1; j < set.size(); j++) {
				if (set.get(i).equal(set.get(j)))
					dublicateForm[j] = true;
			}
		}
		if (!dublicateForm[set.size() - 1])
			numUniq++;
		return numUniq;
	}

	public int getNumIsland() {

		if (counted)
			return set.size();
		else
			return calcNumIsland();
	}

	// most slow method, needed for decision next problem:
	// the approximate result without them, with accuracy size +0.1% per additional
	// thread (1001 instead 1000)
	// with 1 thread them do not need, just need return set.size()
	private int calcNumIsland() {
		for (int i = 0; i < set.size() - 1; i++) {
			for (int j = set.size() - 1; j > i; j--) {
				if (set.get(i).dublicate(set.get(j))) {
					set.remove(j);
				}
			}
		}
		return set.size();
	}

	public byte[][] getMtr() {
		return mtr;
	}

	public byte getMtr(int n, int m) {
		return mtr[n][m];
	}

	public void setMtr(byte[][] mtr) {
		this.mtr = mtr;
	}

	public void setMtr(int n, int m, byte b) {
		this.mtr[n][m] = b;
	}

	public synchronized int getNumCell(int n, int m) {
		return numCell[n][m];
	}

	public void setNumCell(int n, int m, int num) {
		this.numCell[n][m] = num;
	}

	public Pattern getPat(int num) {
		return set.get(num);
	}

	public void addPat(Pattern pattern) {
		this.set.addElement(pattern);

	}

	public void setSet(Vector<Pattern> patternSet) {
		for (int j = 0; j < patternSet.size(); j++) {
			if (patternSet.elementAt(j) != null)
				this.set.addElement(patternSet.elementAt(j));
		}

	}

	public void removePat(int num) {
		this.set.remove(num);
	}
}
