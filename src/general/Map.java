package general;

public class Map {
	private byte[][] mtr;
	private int [][] numCell;

	public Map(int n, int m) {
		setMtr(new byte[n][m]);
		numCell = new int [n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				setMtr( i , j, (byte) 0);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				numCell[i][j] = 0;
			}
		}
	}
	public Map(String [] args) {
		try {
			int n = Integer.parseInt(args[0]);
			int m = Integer.parseInt(args[1]);
			setMtr(new byte[n][m]);
			numCell = new int [n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					setMtr( i , j, Byte.parseByte(args[i*m+j+2]));
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					numCell[i][j] = 0;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
		
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
}
