package procon.Q1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		String line = br.readLine();
		
		String[] lineArr = line.split("[\\s]+");
		String data1 = lineArr[0];
		String data2 = lineArr[1];
		int data3 = Integer.parseInt(lineArr[2]);
		int data4 = Integer.parseInt(lineArr[3]);
		double data5 = Double.parseDouble(lineArr[4]);
		

/*		InputStreamReader ir = new InputStreamReader(System.in);
		Scanner sc = new Scanner(ir);
		String data1 = sc.next();
		String data2 = sc.next();
		int data3 = (int)sc.nextDouble();
		int data4 = (int)sc.nextDouble();
		double data5 = sc.nextDouble();
		
		sc.close();
*/		
		char blank = ' ';
		char zero  = '0';
		String blankStr = "";
		String zeroStr  = "";
		
		for(int i=0; i < 9; i++ ){
			blankStr += blank;
			zeroStr += zero;
		}
		blankStr += blankStr + blank;

		String output = "";
		String tmp;
		
		// 1
		tmp = data1+blankStr;
		tmp = tmp.substring(0,19);
		output += tmp+blank;
		
		// 2
		tmp = blankStr+data2;
		tmp = tmp.substring(tmp.length()-19,tmp.length());
		output += tmp+blank;

		// 3
		if(data3 < 0) {
			data3 *= -1;
			tmp = zeroStr+data3;
			tmp = "-" + tmp.substring(tmp.length()-8,tmp.length());
		} else {
			tmp = zeroStr+data3;
			tmp = tmp.substring(tmp.length()-9,tmp.length());
		}
		output += tmp+blank;

		// 4
		tmp = blankStr+data4;
		tmp = tmp.substring(tmp.length()-9,tmp.length());
		output += tmp+blank;
		
		// 5
		tmp = blankStr+String.format("%.3f", data5);
		tmp = tmp.substring(tmp.length()-9,tmp.length());
		output += tmp;

		System.out.println(output);
	}

}
