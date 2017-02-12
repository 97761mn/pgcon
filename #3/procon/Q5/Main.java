package procon.Q5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static int totalNum = 0;
	public static int[] pack = {20, 9, 6, 4};
	public static boolean flag = false;
	public static int[] answer;
	
	public static void main(String[] args) throws IOException {

		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		String line = br.readLine();
		
		String[] lineArr = line.split("[\\s]+");
		
		totalNum = Integer.parseInt(lineArr[0]);
		int[] input = {0,0,0,0};
		calc(input, -1);

		
		for (int i = 0; i < answer.length; i++) {
			if(i != 0){
				System.out.print(" ");
			}
			System.out.print(answer[i]);
		}
		System.out.println();
	}
	
	public static void calc(int[] input , int diff){
		if (!flag) {
			int tmpRem = pack[0] * input[0] + pack[1] * input[1] + pack[2] * input[2] + pack[3] * input[3];
			tmpRem = totalNum - tmpRem;
			
			for (int i = 0; i < pack.length; i++) {
				if (tmpRem > pack[i]) {
					input[i]++;
					calc(input, i);
				} else if (tmpRem == pack[i]) {
					tmpRem -= pack[i];
					input[i]++;
					answer = input.clone();
					flag = true;
				}
			}
			if(diff != -1) { 
				input[diff]--;
			}
		}
	}

}
