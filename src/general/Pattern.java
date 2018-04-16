package general;

import java.util.ArrayList;
import java.util.Iterator;


public class Pattern {
	ArrayList<Cell> form;
	int num = 0;
	public Pattern(int number) {
		form = new ArrayList<Cell>();
		num = number;
	}
	public Pattern(byte[][] array, int number) {
		form = new ArrayList<Cell>();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(array[i][j]==1) this.addCell(i, j);
			}
		}
		num = number;
	}
	
	public void addCell(int i, int j){
		form.add(new Cell(i,j));
	}
	public void getCell(int i, int j){
		form.add(new Cell(i,j));
	}
	public void optimal() {
		int minN=this.form.get(0).n;
		int minM=this.form.get(0).m;
		Cell tempCell;
		this.form.sort((Cell a,Cell b)->b.compareTo(a));
		for (int i = 0; i < this.form.size(); i++) {
			tempCell = this.form.get(i);
			if(	minN > tempCell.n) minN = tempCell.n;
			if(	minM > tempCell.m) minM = tempCell.m;
		}
		for (int i = 0; i < this.form.size(); i++) {
			tempCell = this.form.get(i);
			tempCell.n -= minN;
			tempCell.m -= minM;
		}
	}
	
	//if equal to true
	public boolean compare(Pattern pat) {
		if(this.form.size()!=pat.form.size())
			return false;
		Iterator<Cell> it1 = this.form.iterator();
		Iterator<Cell> it2 = pat.form.iterator();
		
		while(it1.hasNext()&it2.hasNext()) {
			if(!it1.next().isEqual(it2.next()))
				return false;
		}
		return true;
	}


}
