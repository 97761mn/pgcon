package procon.Q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {

		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		String line = br.readLine();
		
		String[] lineArr = line.split("[\\s]+");
		
		quickSort(lineArr);

		for(int i=0; i< lineArr.length; i++){
			if(i != 0) {
				System.out.print(' ');
			}
			System.out.print(lineArr[i]);
		}
		System.out.println();
	}
	
	public static String[] quickSort(String[] input) {
		return quick(input, 0, input.length - 1);
	}
	
	public static int parseShort(String input){
		if(input.length() >= 2) {
			if(input.substring(0, 2).equals("0x")) {
				return (int)Long.parseLong(input.substring(2), 16);
			}
		}
		return (int)Long.parseLong(input);
	}

	private static String[] quick(String[] input, int left, int right) {
		String[] array = input;
		int currentLeft = left;
		int currentRight = right;

		if (array.length < 2)
			return array;

		String pivot = array[(currentLeft + currentRight) / 2];

		do {
			while (parseShort(array[currentLeft]) < parseShort(pivot)) {
				currentLeft++;
			}

			while (parseShort(array[currentRight]) > parseShort(pivot)) {
				currentRight--;
			}
			if (currentLeft <= currentRight) {
				int index1 = currentLeft++;
				int index2 = currentRight--;
				String temp = array[index1];
				array[index1] = array[index2];
				array[index2] = temp;
			}
		} while (currentLeft <= currentRight);

		if (left < currentRight)
			quick(array, left, currentRight);

		if (currentLeft < right)
			quick(array, currentLeft, right);
		return array;
	}

}
