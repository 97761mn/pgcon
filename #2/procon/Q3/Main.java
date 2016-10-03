package procon.Q3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		String state = "";
		String left = "";
		String right = "";

		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		String line = br.readLine();
		String[] lineArr = line.split("");

		for (int i = 0; i < lineArr.length; i++) {
			if (state.length() != 0) {
				right = state.substring(state.length() - 1);
				left = state.substring(0, 1);
			} else {
				right = "";
				left = "";
			}
			if (lineArr[i].equals(right)) {
				state = state.substring(0, state.length() - 1);
			} else if (lineArr[i].equals(left)) {
				state = state.substring(1, state.length());
			} else {
				if (lineArr.length - 1 > i) {
					if (lineArr[i + 1].equals(right)) {
						state = lineArr[i] + state;
					} else if (lineArr[i + 1].equals(left)) {
						state += lineArr[i];
					} else {
						state += lineArr[i];
					}
				} else {
					state += lineArr[i];
				}
			}
		}
		System.out.println(state.length());
	}

}
