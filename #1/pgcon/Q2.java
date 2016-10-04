package pgcon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q2 {

	public static void main(String[] args) throws IOException {
		String result = "";
		String[][] status = new String[3][3];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line1 = br.readLine();
		String line2 = br.readLine();
		String line3 = br.readLine();
		String statusLine = line1 + line2 + line3; 
		
		status[0] = line1.split("");
		status[1] = line2.split("");
		status[2] = line3.split("");
		String[] statusLinearArr = statusLine.split("");
		
		boolean filled = filledCheck(statusLinearArr);
		result = winnerCheck(status);
		if(result.equals("")) {
			if(filled){
				result = "FIN";
			} else {
				boolean[] turnJudge = isNotMyTurn(statusLinearArr);
				if(turnJudge[0]){
					result = "NG";
				} else if(!turnJudge[1]){
					boolean canWin = nextCheck(status);
					if (canWin) {
						result = "OK";
					} else {
						result = "NO";
					}
				}
			}
		}
		System.out.println(result);
	}
	public static boolean filledCheck(String[] status){
		boolean judge = true;
		if(Arrays.asList(status).contains("-")) {
			judge = false;
		}
		return judge;
	}
	public static String winnerCheck(String[][] status){
		String result = "";
		String[][] line = new String[8][3];
		for(int i=0; i < 3; i++){
			line[0][i] = status[0][0+i];
			line[1][i] = status[1][0+i];
			line[2][i] = status[2][0+i];
			line[3][i] = status[0+i][0];
			line[4][i] = status[0+i][1];
			line[5][i] = status[0+i][2];
			line[6][i] = status[0+i][0+i];
			line[7][i] = status[0+i][2-i];
		}
		for(int i=0; i < 8; i++){
			if(Arrays.asList(line[i]).contains("o")) {
				if(!Arrays.asList(line[i]).contains("x") && !Arrays.asList(line[i]).contains("-")) {
					result = "WIN";
				}
			} else if(Arrays.asList(line[i]).contains("x")) {
				if(!Arrays.asList(line[i]).contains("o") && !Arrays.asList(line[i]).contains("-")) {
					result = "LOSE";
				}
			}
		}
		return result;
	}
	public static boolean[] isNotMyTurn(String[] status){
		boolean[] judge = new boolean[2];
		judge[0] = false;
		judge[1] = false;
		
		int maru = 0;
		int batu = 0;
		
		for(int i=0; i < 9; i++){
			if(status[i].equals("o")) {
				maru++;
			} else if(status[i].equals("x")) {
				batu++;
			}
		}
		if(batu == maru) {
			judge[0] = false;
		} else if((maru-batu) == 1) {
			judge[0] = true;
		} else {
			judge[1] = true;
		}
		return judge;
	}
	public static boolean nextCheck(String[][] status){
		boolean judge = false;
		String result = "";
		for(int i=0; i<3; i++) {
			if(judge) {
				break;
			}
			for(int j=0; j<3; j++) {
				if(status[i][j].equals("-")) {
					status[i][j] = "o";
					result = winnerCheck(status);
					if(result.equals("WIN")) {
						judge = true;
						break;
					}
					status[i][j] = "-";
				}
			}
		}
		return judge;
	}

}
