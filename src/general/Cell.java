package general;

public class Cell implements Comparable<Cell> {
	int n,m;
	public Cell(int i, int j) {
		n=i;
		m=j;
	}
	public boolean isEqual(Cell cell) {
		return this.n==cell.n & this.m==cell.m;
	}
	public int compareTo(Cell a) {
		if( this.n<a.n) {
			return -1;
		}
		if(this.n==a.n) {
			if( this.m<a.m) {
				return -1;
			}
			if(this.m==a.m) {
				return 0;
			}
			return 1;
		}
		return 1;
	}

}
