package procon.Q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		String line = "";
		int sCount = 0;
		int dCount = 0;
		int cCount = 0;
		int hCount = 0;
		String spade = "S:";
		String dia = "D:";
		String club = "C:";
		String heart = "H:";

		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		line = br.readLine();
		String[] lineArr = line.split("");

		// Ace
		for (int i = 0; i < (lineArr.length / 3) + 1; i++) {
			if (lineArr[i * 3 + 1].equals("A")) {
				switch (lineArr[i * 3]) {
				case "S":
					if (sCount != 0) {
						spade += ",";
					}
					spade += lineArr[i * 3 + 1];
					sCount++;
					break;
				case "D":
					if (dCount != 0) {
						dia += ",";
					}
					dia += lineArr[i * 3 + 1];
					dCount++;
					break;
				case "C":
					if (cCount != 0) {
						club += ",";
					}
					club += lineArr[i * 3 + 1];
					cCount++;
					break;
				case "H":
					if (hCount != 0) {
						heart += ",";
					}
					heart += lineArr[i * 3 + 1];
					hCount++;
					break;
				}
			}
		}

		// 2-9
		for (int num = 2; num < 10; num++) {
			for (int i = 0; i < (lineArr.length / 3) + 1; i++) {
				if (lineArr[i * 3 + 1].equals(num + "")) {
					switch (lineArr[i * 3]) {
					case "S":
						if (sCount != 0) {
							spade += ",";
						}
						spade += lineArr[i * 3 + 1];
						sCount++;
						break;
					case "D":
						if (dCount != 0) {
							dia += ",";
						}
						dia += lineArr[i * 3 + 1];
						dCount++;
						break;
					case "C":
						if (cCount != 0) {
							club += ",";
						}
						club += lineArr[i * 3 + 1];
						cCount++;
						break;
					case "H":
						if (hCount != 0) {
							heart += ",";
						}
						heart += lineArr[i * 3 + 1];
						hCount++;
						break;
					}
				}
			}
		}

		// 10
		for (int i = 0; i < (lineArr.length / 3) + 1; i++) {
			if (lineArr[i * 3 + 1].equals("0")) {
				switch (lineArr[i * 3]) {
				case "S":
					if (sCount != 0) {
						spade += ",";
					}
					spade += "0";
					sCount++;
					break;
				case "D":
					if (dCount != 0) {
						dia += ",";
					}
					dia += "0";
					dCount++;
					break;
				case "C":
					if (cCount != 0) {
						club += ",";
					}
					club += "0";
					cCount++;
					break;
				case "H":
					if (hCount != 0) {
						heart += ",";
					}
					heart += "0";
					hCount++;
					break;
				}
			}
		}
		// Jack
		for (int i = 0; i < (lineArr.length / 3) + 1; i++) {
			if (lineArr[i * 3 + 1].equals("J")) {
				switch (lineArr[i * 3]) {
				case "S":
					if (sCount != 0) {
						spade += ",";
					}
					spade += lineArr[i * 3 + 1];
					sCount++;
					break;
				case "D":
					if (dCount != 0) {
						dia += ",";
					}
					dia += lineArr[i * 3 + 1];
					dCount++;
					break;
				case "C":
					if (cCount != 0) {
						club += ",";
					}
					club += lineArr[i * 3 + 1];
					cCount++;
					break;
				case "H":
					if (hCount != 0) {
						heart += ",";
					}
					heart += lineArr[i * 3 + 1];
					hCount++;
					break;
				}
			}
		}

		// Queen
		for (int i = 0; i < (lineArr.length / 3) + 1; i++) {
			if (lineArr[i * 3 + 1].equals("Q")) {
				switch (lineArr[i * 3]) {
				case "S":
					if (sCount != 0) {
						spade += ",";
					}
					spade += lineArr[i * 3 + 1];
					sCount++;
					break;
				case "D":
					if (dCount != 0) {
						dia += ",";
					}
					dia += lineArr[i * 3 + 1];
					dCount++;
					break;
				case "C":
					if (cCount != 0) {
						club += ",";
					}
					club += lineArr[i * 3 + 1];
					cCount++;
					break;
				case "H":
					if (hCount != 0) {
						heart += ",";
					}
					heart += lineArr[i * 3 + 1];
					hCount++;
					break;
				}
			}
		}

		// King
		for (int i = 0; i < (lineArr.length / 3) + 1; i++) {
			if (lineArr[i * 3 + 1].equals("K")) {
				switch (lineArr[i * 3]) {
				case "S":
					if (sCount != 0) {
						spade += ",";
					}
					spade += lineArr[i * 3 + 1];
					sCount++;
					break;
				case "D":
					if (dCount != 0) {
						dia += ",";
					}
					dia += lineArr[i * 3 + 1];
					dCount++;
					break;
				case "C":
					if (cCount != 0) {
						club += ",";
					}
					club += lineArr[i * 3 + 1];
					cCount++;
					break;
				case "H":
					if (hCount != 0) {
						heart += ",";
					}
					heart += lineArr[i * 3 + 1];
					hCount++;
					break;
				}
			}
		}

		if (sCount != 0) {
			System.out.println(spade);
		}
		if (dCount != 0) {
			System.out.println(dia);
		}
		if (cCount != 0) {
			System.out.println(club);
		}
		if (hCount != 0) {
			System.out.println(heart);
		}
		
		br.close();
		ir.close();
	}

}
