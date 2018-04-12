package general;

public class Main {
	static int threadsQuantity = 1;
	static Map map;
	static int tempCount, count;

	public static void main(String[] args) {
		if (args.length > 0) {
			map = new Map(args);
			tempCount = 0;
			count = 0;
			islandSearch();
			System.out.println(count);
		}
	}

	private static void islandSearch() {
		int n = map.getMtr().length;
		int m = map.getMtr()[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map.getMtr(i, j) == 0)
					continue;
				if (map.getMtr(i, j) == 1)
					checkCell(i, j);
			}
		}

	}

	// check
	private static void checkCell(int n, int m) {
		if (m > 0 && map.getMtr(n, m - 1) == 1)
			return;
		if (n > 0 && map.getMtr(n - 1, m) == 1)
			return;
		if (map.getNumCell(n, m) == 0)
			landOverrun(n, m);
	}

	private static void landOverrun(int n, int m) {
		if (map.getNumCell(n, m) == 0) {
			int temp = ++tempCount;
			if (landGoing(0, n, m, temp))
				count++;
		} else
			return;
	}

	// from = 0|1|2|3 (from left | from top | from right | from bottom)
	private static boolean landGoing(int from, int n, int m, int numCell) {
	//	System.out.println("from:" + from + " " + "n:" + n + " m:" + m + " numCell" + numCell);
		if (map.getNumCell(n, m) == 0 | map.getNumCell(n, m) > numCell)
			map.setNumCell(n, m, numCell);
		else
			return false;
		switch (from) {
		case 0:

			if (m < map.getMtr()[0].length - 1) {
				if (!landStep(n, m + 1, 0, numCell))
					return false;
			}
			if (n < map.getMtr().length - 1) {
				if (!landStep(n + 1, m, 1, numCell))
					return false;
			}
			if (n > 0) {
				if (!landStep(n - 1, m, 3, numCell))
					return false;
			}
			break;
		case 1:

			if (m < map.getMtr()[0].length - 1) {
				if (!landStep(n, m + 1, 0, numCell))
					return false;
			}
			if (n < map.getMtr().length - 1) {
				if (!landStep(n + 1, m, 1, numCell))
					return false;
			}
			if (m > 0) {
				if (!landStep(n, m - 1, 2, numCell))
					return false;
			}
			break;
		case 2:
			if (n < map.getMtr().length - 1) {
				if (!landStep(n + 1, m, 1, numCell))
					return false;
			}
			if (m > 0) {
				if (!landStep(n, m - 1, 2, numCell))
					return false;
			}
			if (n > 0) {
				if (!landStep(n - 1, m, 3, numCell))
					return false;
			}
			break;
		case 3:
			if (m < map.getMtr()[0].length - 1) {
				if (!landStep(n, m + 1, 0, numCell))
					return false;
			}
			if (m > 0) {
				if (!landStep(n, m - 1, 2, numCell))
					return false;
			}
			if (n > 0) {
				if (!landStep(n - 1, m, 3, numCell))
					return false;
			}
			break;
		default:
			System.out.println("case default!");
			break;
		}
		return true;
	}

	private static boolean landStep(int n, int m, int from, int numCell) {
	//	System.out.println("--from:" + from + " " + "n:" + n + " m:" + m + " numCell" + numCell);
		if (map.getMtr(n, m) == 1) {
			if (map.getNumCell(n, m) == 0 | map.getNumCell(n, m) > numCell) {
				if (!landGoing(from, n, m, numCell))
					return false;
			} else
				return false;
		}
		return true;
	}

}
