package pgcon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1 {

	public static void main(String[] args) throws IOException {
        // TODO 自動生成されたメソッド・スタブ
    	Integer fullNumberOfBook;
    	String[] fullNumberOfBookArr;
    	String[] myBookTmp;
    	Integer[] myBook;
    	String[] sellBookTmp;
    	Integer[] sellBook;
    	String answer = "None";
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line1 = br.readLine();
		fullNumberOfBookArr = line1.split(" ");
		fullNumberOfBook = Integer.parseInt(fullNumberOfBookArr[0]);
		
		String line2 = br.readLine();
		myBookTmp = line2.split(" ");
		String line3 = br.readLine();
		sellBookTmp = line3.split(" ");
		
		if(!myBookTmp[0].equals("0")){
			myBook = new Integer[myBookTmp.length];
			for(int i = 0; i < myBookTmp.length; i++) {
				myBook[i] = Integer.parseInt(myBookTmp[i]);
			}
			Arrays.sort(myBook);
		} else {
			myBook = new Integer[1];
		}
		
		if(!sellBookTmp[0].equals("0")){
			sellBook = new Integer[sellBookTmp.length];
			for(int i = 0; i < sellBookTmp.length; i++) {
				sellBook[i] = Integer.parseInt(sellBookTmp[i]);
			}
			Arrays.sort(sellBook);
		} else {
			sellBook = new Integer[1];
		}
		
		boolean first = true; 
		if(fullNumberOfBook > 1000 || fullNumberOfBook < 1 || sellBook[0] == null) {
			answer = "None";
		} else {
			for(Integer num : sellBook) {
				if(fullNumberOfBook >= num) {
					if(!Arrays.asList(myBook).contains(num)){
						if(first) {
							answer = "" + num;
							first = false;
						} else {
							answer += " " + num;
						}
					}
				}
			}
		}
		
		System.out.println(answer);
    }

}
