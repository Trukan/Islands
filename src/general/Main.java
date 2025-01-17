package general;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	public static int numObserverThreads = 4;
	public static ThreadPool observerThpool;
	static Map map;
	public static LinkedBlockingQueue<Pattern> queue;

	// @param "filename" xor String[] ={"numCell", "numColumn", "0|1", "0|1", "0|1",
	// "0|1", ...}
	public static void main(String[] args) {
		if (args.length == 0)
			System.exit(3);
		if (args.length == 1) {
			readFile(args[0]);
		}
		if (args.length > 2) {
			map = new Map(args);
		}
		observerThpool = new ThreadPool(numObserverThreads);
		queue = new LinkedBlockingQueue<Pattern>();
		Observer.setMap(map);
		Observer.setQueue(queue);
		Cell.initMtr(map.getMtr().length, map.getMtr()[0].length);
		islandSearch();
		System.out.println("Quantity islands: " + map.getNumIsland());
		System.out.println("Unique islands: " + map.getNumUniqForm());
		map.getNumIsland();
		map.getNumUniqForm();
	}

	public static void islandSearch() {
		int n = map.getMtr().length;
		int verticalSide = n / numObserverThreads;
		for (int i = 0; i < n; i += verticalSide) {
			if (i + 2 * verticalSide >= n) {
				observerThpool.getThread(new Observer(i, n), "obsTh" + i + ":" + (n - 1)).start();
				break;
			}
			observerThpool.getThread(new Observer(i, i + verticalSide), "obsTh" + i + ":" + (i + verticalSide)).start();
		}

		while (observerThpool.anyThreadsAlive() || !queue.isEmpty()) {
			if(!queue.isEmpty())
			map.addPat(queue.poll());
		}
	}

	public static void readFile(String filename) {
		FileReader fin;
		int n = 0, m = 0;
		try {
			fin = new FileReader(filename);
			Scanner sc = new Scanner(fin);
			if (sc.hasNextInt()) {
				n = sc.nextInt();
			}
			if (sc.hasNextInt()) {
				m = sc.nextInt();
			}
			if (n == 0 | m == 0) {
				fin.close();
				sc.close();
				System.out.println("file data error");
				System.exit(2);
			}
			map = new Map(n, m);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (sc.hasNextByte()) {
						map.setMtr(i, j, sc.nextByte());
					}
				}
			}
			fin.close();
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("file data error");
			e.printStackTrace();
			System.exit(2);
		}

	}

	public static void setMap(Map m) {
		map = m;
	}

	public static Map getMap() {
		return map;
	}

}
