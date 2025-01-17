package general;

public class Cell implements Comparable<Cell> {
    int n, m;
    private static Cell mtr[][];

    private Cell(int i, int j) {
        n = i;
        m = j;
    }

    static void initMtr(int n, int m) {
        mtr = new Cell[n][m];
    }

    public static Cell getCell(int n, int m) {
        if (mtr[n][m] == null) {
            mtr[n][m] = new Cell(n, m);
        }
        return mtr[n][m];
    }

    public int compareTo(Cell a) {
        if (this == a) return 0;
        if (this.n < a.n) {
            return -1;
        }
        if (this.n == a.n) {
            if (this.m < a.m) {
                return -1;
            }
            if (this.m == a.m) {
                return 0;
            }
            return 1;
        }
        return 1;
    }

}
