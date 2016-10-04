package procon.Q4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.DatatypeConverter;

public class Main {

	public static void main(String[] args) throws IOException {
		int lineNum = 0;
		int x = 0;
		int y = 0;
		Main mine = new Main();
		String emptyStr = "";
		StringBuffer sb;
		for (int i = 0; i < 80; i++) {
			emptyStr += " ";
		}

		String[] tmpLine;
		byte[] bytes;
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		lineNum = Integer.parseInt(br.readLine());
		String[] line = new String[lineNum];
		String hexText;
		boolean escapeMode = false;
		String[] outStr = new String[25];

		for (int i = 0; i < 25; i++) {
			outStr[i] = emptyStr;
		}

		for (int i = 0; i < lineNum; i++) {
			line[i] = br.readLine();
			tmpLine = line[i].split("");
			for (int j = 0; j < tmpLine.length; j++) {
				if (!escapeMode) {
					bytes = tmpLine[j].getBytes();
					hexText = DatatypeConverter.printHexBinary(bytes);
					// escape
					if (hexText.equals("1B")) {
						escapeMode = true;
					} else {
						sb = new StringBuffer(outStr[y]);
						sb = sb.deleteCharAt(x);
						sb = sb.insert(x, tmpLine[j]);
						outStr[y] = sb.toString();
						if (x > 78) {
							x = 0;
							if(y == 24) {
								outStr = mine.scrollScreen(outStr, emptyStr);
							} else {
								y++;
							}
						} else {
							x++;
						}
					}
				} else {
					String tStr = "";
					String direct = "";
					int type = 0;
					for (int k = j; k < tmpLine.length; k++) {
						if (tmpLine[k].equals("H")) {
							tStr = line[i].substring(line[i].indexOf("[", j) + 1, k);
							type = 1;
							break;
						} else if (tmpLine[k].matches("[A-D]")) {
							tStr = line[i].substring(line[i].indexOf("[", j) + 1, k);
							direct = tmpLine[k];
							type = 2;
							break;
						}
					}
					if ((tmpLine[j + 1] + tmpLine[j + 2]).equals("2J")) {
						for (int k = 0; k < 25; k++) {
							outStr[k] = emptyStr;
						}
						x = 0;
						y = 0;
						j += 2;
					} else if (type == 1) {
						String[] cNum = tStr.split(";");
						x = Integer.parseInt(cNum[1]) - 1;
						y = Integer.parseInt(cNum[0]) - 1;
						j += tStr.length() + 1;
					} else if (type == 2) {
						switch (direct) {
						case "B":
							y += Integer.parseInt(tStr);
							break;
						case "D":
							x -= Integer.parseInt(tStr);
							break;
						case "C":
							x += Integer.parseInt(tStr);
							break;
						case "A":
							y -= Integer.parseInt(tStr);
							break;
						}
						j += tStr.length() + 1;
					}
					escapeMode = false;
				}
			}
		}
		for (int i = 0; i < 25; i++) {
			System.out.println(outStr[i]);
		}
		
		br.close();
		ir.close();
	}
	
	public String[] scrollScreen(String[] input, String emp){
		for(int i=0; i < 24; i++){
			input[i] = input[i+1];
		}
		input[24] = emp;
		return input;
	}

}
