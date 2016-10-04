package pgcon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q3 {

	public static void main(String[] args) throws IOException {
		String[] inputStr;
		int limit;
		int rateBfr,rateAft,total;
		int max = 0;
		int tmp = 0;
		int totalCompare = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		inputStr = line.split(" ");
		
		rateBfr = Integer.parseInt(inputStr[0]) + 100;
		rateAft = Integer.parseInt(inputStr[1]) + 100;
		total = Integer.parseInt(inputStr[2]);
		
		limit = (int)Math.ceil(total * 0.5);
		
		int price1, price2, taxinBfr1, taxinBfr2;
		for(price1=1 ; price1 <= limit; price1++) {
			taxinBfr1 = (int) (price1 * rateBfr * 0.01);
			taxinBfr2 = total - taxinBfr1;
			price2 = (int) Math.ceil(taxinBfr2 * 100.0 / rateBfr);
			for (int i = price2; i <= total; i++) {
				price2 = i;
				totalCompare = (int) (price1 * rateBfr * 0.01) + (int) (price2 * rateBfr * 0.01);
				if(totalCompare > total){
					break;
				}
				if (totalCompare == total) {
					tmp = (int) (price1 * rateAft * 0.01) + (int) (price2 * rateAft * 0.01);
					if (tmp > max) {
						max = tmp;
					}
				}
			}
		}
		System.out.println(max);
	}

}
