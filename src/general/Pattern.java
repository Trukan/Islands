package general;

import java.util.ArrayList;
import java.util.Iterator;

public class Pattern {
	ArrayList<Cell> form;
	int num = 0;
	int minN, minM;
	int height, width;
	byte[][] mtr;

	public Pattern(int number) {
		form = new ArrayList<Cell>();
		num = number;
	}

	public Pattern(byte[][] array, int number) {
		form = new ArrayList<Cell>();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 1)
					this.addCell(i, j);
			}
		}
		num = number;
	}

	public void addCell(int i, int j) {
		form.add(new Cell(i, j));
	}

	public void getCell(int i, int j) {
		form.add(new Cell(i, j));
	}

	public void optimal() {
		minN = this.form.get(0).n;
		minM = this.form.get(0).m;
		height = 0;
		width = 0;
		Cell tempCell;
		this.form.sort((Cell a, Cell b) -> b.compareTo(a));
		for (int i = 0; i < this.form.size(); i++) {
			tempCell = this.form.get(i);
			if (minN > tempCell.n)
				minN = tempCell.n;
			if (minM > tempCell.m)
				minM = tempCell.m;
		}
		for (int i = 0; i < this.form.size(); i++) {
			tempCell = this.form.get(i);
			tempCell.n -= minN;
			if (height < tempCell.n)
				height = tempCell.n;
			tempCell.m -= minM;
			if (width < tempCell.m)
				width = tempCell.m;
		}
		if (this.form.size() != 0) {
			height++;
			width++;
		}
	}

	// if equal to true
	public boolean equal(Pattern pat) {
		if (this.form.size() != pat.form.size())
			return false;
		Iterator<Cell> it1 = this.form.iterator();
		Iterator<Cell> it2 = pat.form.iterator();

		while (it1.hasNext() & it2.hasNext()) {
			if (!it1.next().isEqual(it2.next()))
				return false;
		}
		return true;
	}
	// if equal to true
		public boolean dublicate(Pattern pat) {
			if (this.minN!=pat.minN|this.minM!=pat.minM)
				return false;
			if (this.height!=pat.height|this.width!=pat.width)
				return false;
			return true;
		}

	// after optimal();
	public String toString() {
		StringBuilder b = new StringBuilder();
//		System.out.println(height + " " + width + " " + this.form.size());
		mtr = new byte[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				mtr[i][j] = 0;
			}
		}
		Cell tempCell;
		b.append(minN+":"+minM+"\n");
		for (int i = 0; i < this.form.size(); i++) {
			tempCell = this.form.get(i);
			mtr[tempCell.n][tempCell.m] = 1;
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				b.append(mtr[i][j]);
			}
			b.append("\n");
		}
		return b.toString();

	}

}
