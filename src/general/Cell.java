package general;

public class Cell {
	int n,m;
	public Cell(int i, int j) {
		n=i;
		m=j;
	}
	public boolean compare(Cell cell) {
		return this.n==cell.n & this.m==cell.m;
	}

}
