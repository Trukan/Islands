package general;

import java.util.TreeSet;

public class Pattern {
	TreeSet<Cell> form;
	String key1, key2;
	int num = 0;
	int minN, minM;
	int height, width;
	byte[][] mtr;

	public Pattern(int number) {
		form = new TreeSet<Cell>((Cell a, Cell b) -> b.compareTo(a));
		num = number;
	}

	public Pattern(byte[][] array, int number) {
		form = new TreeSet<Cell>((Cell a, Cell b) -> b.compareTo(a));
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 1)
					this.addCell(i, j);
			}
		}
		num = number;
	}



	public void optimal() {
		minN = this.form.first().n;
		height = this.form.last().n - minN;
		width = 0;
		TreeSet<Cell>optiform = new TreeSet<Cell>((Cell a, Cell b) -> b.compareTo(a));
		for (Cell c : form) {
			optiform.add(new Cell(c.n-minN,c.m-minM));
		}
		form = optiform;
		generateKey();
	}

	void generateKey() {
		StringBuilder b = new StringBuilder();
		b.append(this.minN);
		b.append(' ');
		b.append(this.minM);
		b.append(' ');
		b.append(this.height);
		b.append(' ');
		b.append(this.width);
		b.append(' ');
		key1 = b.toString();
		b = new StringBuilder();
		for (Cell c : this.form) {
			b.append(c.n);
			b.append(' ');
			b.append(c.m);
			b.append(' ');
		}
		key2 = b.toString();
	}

	// if equal to true
	public boolean equals(Pattern pat) {
		if (this.form.size() != pat.form.size())
			return false;
		if (!this.form.containsAll(pat.form))
			return false;
		return true;
	}

	// if equal to true
	public boolean dublicate(Pattern pat) {
		if (this.minN != pat.minN | this.minM != pat.minM)
			return false;
		if (this.height != pat.height | this.width != pat.width)
			return false;
		return true;
	}

	// after optimal();
	public String toString() {
		StringBuilder b = new StringBuilder();
		// System.out.println(height + " " + width + " " + this.form.size());
		mtr = new byte[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				mtr[i][j] = 0;
			}
		}
		b.append(minN + ":" + minM + "\n");
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
			minM = j;
			width = 0;
		} else {
			if (minM > j)
				minM = j;
			if (minM + width < j)
				width = j - minM;
		}
		form.add(new Cell(i, j));
	}
	//for drop dublicated islands with identical form and position
	//to resolve collision with repeatly overrun on one island
	public int compareTo(Pattern a) {
		return this.key1.compareTo(a.key1);
	}
	//for uniqSet
	public int compareTo2(Pattern a) {
		return this.key2.compareTo(a.key2);
	}

	
	
	@Override
	public int hashCode() {
		return key1.hashCode();
	}
}
