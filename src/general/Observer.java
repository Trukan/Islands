package general;

public class Observer implements Runnable {
	static Map map;
	int startLine, endLine;
	public Observer(int start, int end ) {
		this.startLine = start;
		this.endLine = end;
	}

	@Override
	public void run() {
		int m = map.getMtr()[0].length;
		for (int i = startLine; i < endLine; i++) {
			for (int j = 0; j < m; j++) {
				if (map.getMtr(i, j) == 0)
					continue;
				if (map.getMtr(i, j) == 1) {
					if (j > 0 && map.getMtr(i, j - 1) == 1)
						continue;
					if (i > 0 && map.getMtr(i - 1, j) == 1)
						continue;
					checkCellandOverrun(i, j);
					
				}
			}
		}
	}
	// check Land or not for start overrun
		static void checkCellandOverrun(int n, int m) {
			if (map.getNumCell(n, m) == 0) {
				new Overrun(n, m).run();
			}
		}
		
		public static void setMap(Map m) {
			map = m;
		}
	

}
