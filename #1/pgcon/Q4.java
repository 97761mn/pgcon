package pgcon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String readLine = br.readLine();
		String[] lineArr = readLine.split(" ");
		String line = "";
		String[] binLineArr = null;
		String[][] square = null;
		String[][] squareR = null;
		String[] result = null;
		String[] resultR = null;

		for (int i = 0; i < lineArr.length; i++) {
			String bitTmp = Integer.toBinaryString(Integer.parseInt(lineArr[i], 16));
			int length = bitTmp.length();
			for (int j = 0; j < 8 - length; j++) {
				bitTmp = "0" + bitTmp;
			}
			line += bitTmp.replaceAll("0", ".").replaceAll("1", "X");
		}

		binLineArr = line.split("");
		square = lineIntoSquare(binLineArr);
		squareR = turnRightSquare(square);

		result = squareIntoLine(square);
		resultR = squareIntoLine(squareR);

		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		System.out.println();
		for (int i = 0; i < resultR.length; i++) {
			System.out.println(resultR[i]);
		}
	}

	public static String[][] lineIntoSquare(String[] line) {
		String[][] square = new String[24][24];
		for (int i = 0; i < line.length; i++) {
			square[i/24][i%24] = line[i];
		}
		return square;
	}

	public static String[][] turnRightSquare(String[][] square) {
		String[][] squareR = new String[24][24];
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 24; j++) {
				squareR[j][23-i] = square[i][j];
			}
		}
		return squareR;
	}

	public static String[] squareIntoLine(String[][] square) {
		String[] line = new String[24];
		for(int i = 0; i<square.length; i++){
			line[i] = String.join("", square[i]);
		}
		return line;
	}
}
