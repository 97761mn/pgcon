package procon.Q1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		String line = "";
		
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		line = br.readLine();
		StringBuffer sb = new StringBuffer(line);
		line = sb.reverse().toString();
		System.out.println(line);
		br.close();
		ir.close();
	}

}
