package general;

import java.util.Vector;

public class Map {
	private byte[][] mtr;
	private volatile int[][] numCell;
	private Vector<Pattern> pat;

	public Map(int n, int m) {
		setMtr(new byte[n][m]);
		numCell = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				setMtr(i, j, (byte) 0);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				numCell[i][j] = 0;
			}
		}
		pat = new Vector<Pattern>();
	}

	public Map(String[] args) {
		try {
			int n = Integer.parseInt(args[0]);
			int m = Integer.parseInt(args[1]);
			setMtr(new byte[n][m]);
			numCell = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					setMtr(i, j, Byte.parseByte(args[i * m + j + 2]));
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					numCell[i][j] = 0;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("parse for map error");
			System.exit(5);
		}
		pat = new Vector<Pattern>();
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

	public int getNumCell(int n, int m) {
		return numCell[n][m];
	}

	public void setNumCell(int n, int m, int num) {
		this.numCell[n][m] = num;
	}

	public Pattern getPat(int num) {
		return pat.get(num);
	}

	public void setPat(Pattern pattern) {
		this.pat.addElement(pattern);
		
	}
	public void setPat(Vector<Pattern> pattern) {
		for (int j = 0; j < pattern.size(); j++) {
			if(pattern.elementAt(j)!=null)
			this.pat.addElement(pattern.elementAt(j));
		}
		
		
	}

	public void removePat(int num) {
		this.pat.remove(num);
	}
	public synchronized boolean changeCell(int n, int m, int numCell) {
		if (this.numCell[n][m] == 0 | this.numCell[n][m] > numCell) {
			this.numCell[n][m] = numCell;
			return true;
		}else {
			return false;
		}
	}

	public int getNumUniqForm() {
		int numUniq = 0;
		if (this.pat.size() == 1)
			return 1;
		Pattern[] tempPat = new Pattern[this.pat.size()];
		this.pat.toArray(tempPat);
		boolean[] dublicate = new boolean[this.pat.size()];

		for (int i = 0; i < tempPat.length; i++) {
			dublicate[i] = false;
		}
		for (int i = 0; i < tempPat.length - 1; i++) {
			if (dublicate[i])
				continue;
			numUniq++;
			for (int j = i + 1; j < tempPat.length; j++) {
				if (tempPat[i].compare(tempPat[j]))
					dublicate[j] = true;
			}
		}
		if (!dublicate[tempPat.length - 1])
			numUniq++;
		return numUniq;
	}
	public int getNumIsland() {
		return pat.size();
	}
}
