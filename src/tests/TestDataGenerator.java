package tests;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestDataGenerator {

	static int side = 1024;

	static int numIsland = 40000; //for teoretic quantity
	static int patternSide = (int) (side / Math.sqrt(numIsland) );

	static byte[][] mtr;
	static byte[][][] patternMtr;
	static String filename = "test03 " + numIsland + " " + "" + ".txt";

	public TestDataGenerator() {
	}

	public static void main(String[] args) {
		initMtr(numIsland); 
		writeFile();
	}
	static void writeFile() {
		FileWriter fout; 
		try {
			fout = new FileWriter("test03 " + numIsland + " " + "" + ".txt");
			fout.write(side + " " + side +"\n");
			for (int i = 0; i < mtr.length; i++) {
				for (int j = 0; j < mtr[i].length; j++) {
					fout.write(mtr[i][j]+" ");
				}
				fout.write("\n");
			}
			fout.close();
		} catch (IOException e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}

	static void initMtr(int num) {
		Random random= new Random();
		byte[][] currPat;
		int countIsland=0;
		mtr = new byte[side][side];
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				mtr[i][j] = 0;
			}
		}
		setPatMtr(num);
		for (int i = 0; i < side/patternSide; i++) {
			for (int j = 0; j < side/patternSide; j++) {
				currPat = patternMtr[random.nextInt(patternMtr.length)];
				countIsland++;
				for (int k = 0; k < currPat.length; k++) {
					for (int l = 0; l < currPat[k].length; l++) {
						mtr[i*patternSide+k][j*patternSide+l] = currPat[k][l];
					}
				}
			}
		}
		numIsland=countIsland;
	}

	static void setPatMtr(int num) {
		if(num==40000) {
			byte[][][] temppatternMtr = {
				{	
					{1,1,1},
					{1,1,1},
					{1,1,1},
				},
				{	
					{1,0,1},
					{1,1,1},
					{1,1,1},
				},
				{	
					{1,0,1},
					{1,0,1},
					{1,1,1},
				},
				{	
					{1,0,0},
					{1,1,0},
					{1,1,1},
				},
				{	
					{0,0,1},
					{0,0,1},
					{1,1,1},
				},
				{	
					{0,0,1},
					{1,0,1},
					{1,1,1},
				}
			};
			patternMtr = temppatternMtr;
	}
			if(num<=10000){
				byte[][][] temppatternMtr = {
						{	
							{1,1,1},
							{1,1,1},
							{1,1,1}
						},
						{	
							{1,0,1},
							{1,1,1},
							{1,1,1}
						},
						{	
							{1,0,1},
							{1,0,1},
							{1,1,1}
						},
						{	
							{1,0,0},
							{1,1,0},
							{1,1,1}
						},
						{	
							{0,0,1},
							{0,0,1},
							{1,1,1}
						},
						{	
							{0,0,1},
							{1,0,1},
							{1,1,1}
						},
				{	
					{1,0,0,1,0,0,1,0},
					{1,1,0,1,1,0,1,1},
					{1,1,0,1,1,0,1,1},
					{1,1,0,1,1,0,1,0},
					{0,1,1,1,1,0,1,0},
					{0,1,0,1,1,0,1,0},
					{0,1,0,1,1,1,1,1},
					{1,1,0,1,1,0,1,1}
				},
				{	
					{0,0,0,1,0,0,1,0},
					{1,1,0,1,1,0,1,1},
					{1,1,1,1,1,0,1,1},
					{1,1,1,1,1,0,1,1},
					{1,1,1,1,1,0,1,1},
					{1,1,0,1,1,0,1,1},
					{0,1,0,1,1,0,1,1},
					{1,1,0,1,1,1,1,1}
				},
				{	
					{1,0,0,1,0,0,1,0},
					{1,1,0,1,1,0,1,1},
					{0,1,0,1,1,0,0,1},
					{0,1,0,1,1,0,0,1},
					{0,1,0,1,1,0,0,1},
					{0,1,0,1,1,1,1,1},
					{0,1,0,1,1,1,1,1},
					{1,1,1,1,1,0,1,1}
				},
				{	
					{1,0,0,1,0,0,1,0},
					{1,1,1,1,1,0,1,1},
					{1,1,1,1,1,0,1,1},
					{0,0,0,1,0,0,1,1},
					{1,1,1,1,1,1,1,1},
					{0,0,0,0,0,0,0,1},
					{1,1,0,1,1,1,0,1},
					{1,1,1,1,0,1,1,1}
				},
				{	
					{0,0,1,0,0,1,0,0},
					{1,0,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1},
					{1,1,1,1,0,1,1,0},
					{1,1,1,1,0,1,1,0},
					{1,1,1,1,0,1,1,0}
				}
		};
				patternMtr = temppatternMtr;
		}
	}

}
