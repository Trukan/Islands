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
	public void addCell(int i, int j){
		form.add(new Cell(i,j));
	}
	public void optimal() {
		int rN=0;
		int rM=0;
		Cell tempCell;
		Iterator<Cell> it = this.form.iterator();
		if(it.hasNext()) {
			tempCell = it.next();
			rN = tempCell.n;
			rM = tempCell.m;
			tempCell.n -= rN;
			tempCell.m -= rM;
		}
		while(it.hasNext()) {
			tempCell = it.next();
			tempCell.n -= rN;
			tempCell.m -= rM;
		}
	}
	//if equal to true
	public boolean compare(Pattern pat) {
		if(this.form.size()!=pat.form.size())
			return false;
		Iterator<Cell> it1 = this.form.iterator();
		Iterator<Cell> it2 = pat.form.iterator();
		
		while(it1.hasNext()&it2.hasNext()) {
			if(!it1.next().compare(it2.next()))
				return false;
		}
		return true;
	}

}
