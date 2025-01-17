package general;

import java.util.TreeSet;

public class Map {
	private byte[][] mtr;
	private TreeSet<Pattern> set;
	private TreeSet<Pattern> uniqSet;

	public Map(int n, int m) {
		setMtr(new byte[n][m]);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				setMtr(i, j, (byte) 0);
			}
		}
		set = new TreeSet<Pattern>((Pattern a, Pattern b)->b.compareIdenticalTo(a));
		uniqSet = new TreeSet<Pattern>((Pattern a, Pattern b)->b.compareFormTo(a));
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
		} catch (NumberFormatException e) {
			System.out.println("parse for map error");
			System.exit(5);
		}
		set = new TreeSet<Pattern>((Pattern a, Pattern b)->b.compareIdenticalTo(a));
		uniqSet = new TreeSet<Pattern>((Pattern a, Pattern b)->b.compareFormTo(a));
	}

	public int getNumUniqForm() {
		
		return uniqSet.size();
	}

	public int getNumIsland() {
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

	public void addPat(Pattern pattern) {
	//	System.out.println(pattern.toString());
		this.set.add(pattern);
		this.uniqSet.add(pattern);

	}

}
