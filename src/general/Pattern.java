package general;

import java.util.Iterator;
import java.util.TreeSet;

public class Pattern {
	TreeSet<Cell> form;
	int num = 0;
	int minN, minM;
	int height, width;

	public Pattern(int number) {
		form = new TreeSet<Cell>();
		num = number;
	}

	public Pattern(byte[][] array, int number) {
		form = new TreeSet<Cell>();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 1)
					this.addCell(i, j);
			}
		}
		num = number;
	}

	public void optimal() {
		TreeSet<Cell> optiform = new TreeSet<Cell>();
		for (Cell c : form) {
			optiform.add(Cell.getCell(c.n - minN, c.m - minM));
			if (c.m - minM + 1 > width) {
				width = c.m - minM + 1;
			}
		}
		this.height=optiform.last().n+1;
		form = optiform;
	}

	// after optimal();
	public String toString() {
		byte[][] mtr;
		StringBuilder b = new StringBuilder();
	//	System.out.println(height + " " + width + " " + this.form.size());
		mtr = new byte[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				mtr[i][j] = 0;
			}
		}
		b.append("start point - " + minN + ":" + minM + "\n");
		for (Cell c : this.form) {
			mtr[c.n][c.m] = 1;
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				b.append(mtr[i][j]);
			}
			b.append("\n");
		}
		return b.toString();

	}

	public void addCell(int i, int j) {
		if (form.isEmpty()) {
			minN = i;
			minM = j;
			height = 1;
			width = 1;
		} else {
			if (i < minN) {
				minN = i;
			}
			if (j < minM) {
				minM = j;
			}
		}
		form.add(Cell.getCell(i, j));
	}

	// for drop dublicated islands with identical form and position
	// to resolve collision with repeatly overrun on one island
	public int compareIdenticalTo(Pattern a) {
		if (this.minN < a.minN) {
			return -1;
		}
		if (this.minN == a.minN) {
			if (this.minM < a.minM) {
				return -1;
			}
			if (this.minM == a.minM) {
				if (this.height == a.height) {
					return 0;
				}
				if (this.height < a.height) {
					return -1;
				} else {
					return 1;
				}
			}
			return 1;
		}
		return 1;
		// return this.key1.compareTo(a.key1);
	}

	// for uniqSet
	public int compareFormTo(Pattern a) {
		int temp;
		Iterator<Cell> it2 = a.form.iterator();
		for (Iterator<Cell> it1 = this.form.iterator(); it1.hasNext();) {
			if (it2.hasNext()) {
				temp = it1.next().compareTo(it2.next());
				if (temp == -1)
					return -1;
				if (temp == 1)
					return 1;
			} else {
				return -1;
			}
		}
		if (it2.hasNext())
			return 1;
		else
			return 0;
	}

}
