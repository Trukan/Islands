package general;

public class Overrun implements Runnable{
	static Map map;
	static volatile int CallOverrunCount=0;
	int n, m;
	Pattern pat;
	final static byte FROMLEFT = 0;
	final static byte FROMTOP = 1;
	final static byte FROMRIGHT = 2;
	final static byte FROMBOTTOM = 3;

	public Overrun(int n, int m) {
		setStartLand(n, m);
	}
	public void setStartLand(int n, int m) {
		this.n =n;
		this.m= m;
	}

	@Override
	public void run() {
		if (map.getNumCell(this.n, this.m) == 0) {
			int temp = ++CallOverrunCount;
			pat = new Pattern(temp);
			if (checkStep( this.n, this.m, FROMLEFT, temp)) {
				pat.optimal();
				Main.patAr.addElement(pat);
		//		map.setPat(pat);
			} else {
			}
		}
	}

	// step by land, check around and step land again to side if can
	// research island form
	private boolean landStep(byte from, int n, int m, int numCell) {
	//	 System.out.println(" " + "n:" + n + " m:" + m + " numCell" +
	//	 numCell+"from:" + from + "\t"+ Thread.currentThread().getName());
		pat.addCell(n, m);
		switch (from) {
		case FROMLEFT:

			if (m < map.getMtr()[0].length - 1) {
				if (!checkStep(n, m + 1, FROMLEFT, numCell))
					return false;
			}
			if (n < map.getMtr().length - 1) {
				if (!checkStep(n + 1, m, FROMTOP, numCell))
					return false;
			}
			if (n > 0) {
				if (!checkStep(n - 1, m, FROMBOTTOM, numCell))
					return false;
			}
			break;
		case FROMTOP:

			if (m < map.getMtr()[0].length - 1) {
				if (!checkStep(n, m + 1, FROMLEFT, numCell))
					return false;
			}
			if (n < map.getMtr().length - 1) {
				if (!checkStep(n + 1, m, FROMTOP, numCell))
					return false;
			}
			if (m > 0) {
				if (!checkStep(n, m - 1, FROMRIGHT, numCell))
					return false;
			}
			break;
		case FROMRIGHT:
			if (n < map.getMtr().length - 1) {
				if (!checkStep(n + 1, m, FROMTOP, numCell))
					return false;
			}
			if (m > 0) {
				if (!checkStep(n, m - 1, FROMRIGHT, numCell))
					return false;
			}
			if (n > 0) {
				if (!checkStep(n - 1, m, FROMBOTTOM, numCell))
					return false;
			}
			break;
		case FROMBOTTOM:
			if (m < map.getMtr()[0].length - 1) {
				if (!checkStep(n, m + 1, FROMLEFT, numCell))
					return false;
			}
			if (m > 0) {
				if (!checkStep(n, m - 1, FROMRIGHT, numCell))
					return false;
			}
			if (n > 0) {
				if (!checkStep(n - 1, m, FROMBOTTOM, numCell))
					return false;
			}
			break;
		default:
			System.out.println("case default!");
			break;
		}
		return true;
	}

	// check probably step to cell with coordinates (n,m),
	// if numCell check other thread to decide this collision
	// return false if numCell currentThread > numCell other thread, else continue
	// steping
	//need synchronize
	private boolean checkStep(int n, int m, byte from, int numCell) {
		if (map.getMtr(n, m) == 1) {
			if (map.changeCell(n,m , numCell)) {
				if (!landStep(from, n, m, numCell)) {
					return false;
				}
			} else {
				if (map.getNumCell(n, m) == numCell) {
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static void setMap(Map m) {
		map = m;
	}

	public static Map getMap() {
		return map;
	}

}
