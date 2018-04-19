package general;

import java.util.Iterator;
import java.util.TreeSet;

public class Pattern {
	TreeSet<Cell> form;
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
	public int compareIdenticalTo(Pattern a) {
		if( this.minN<a.minN) {
			return -1;
		}
		if(this.minN==a.minN) {
			if( this.minM<a.minM) {
				return -1;
			}
			if(this.minM==a.minM) {
				return 0;
			}
			return 1;
		}
		return 1;
	//	return this.key1.compareTo(a.key1);
	}
	//for uniqSet
	public int compareFormTo(Pattern a) {
		int temp;
		Iterator<Cell> it2= a.form.iterator();		
		for (Iterator<Cell> it1 = this.form.iterator();it1.hasNext();) {
			if(it2.hasNext()) {
				temp = it1.next().compareTo(it2.next());
				if(temp==-1)return -1;
				if(temp==1)return 1;
			}else{
				return -1;
				}
		}
		if(it2.hasNext()) return 1;
		else return 0;
	}

}
