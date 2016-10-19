/*
まだコーディング途中です。

*/
package procon.Q5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		Main myTools = new Main();

		int numOfMovie;
		int minInterval;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		numOfMovie = Integer.parseInt(br.readLine());
		minInterval = Integer.parseInt(br.readLine());

		MovieProgram[] program = new MovieProgram[numOfMovie];
		String tmpLine;
		String regexBlank = "[\\s]+";
		String[] tmpArr;
		MovieProgram tmpProgram;
		String tmpTitle;
		MovieTime[] tmpSets;
		MovieTime tmpSet;
		int time;

		for(int i=0; i < numOfMovie; i++) {
			tmpLine = br.readLine();
			tmpArr = tmpLine.split(regexBlank);
			tmpTitle = tmpArr[0];

			tmpSets = new MovieTime[tmpArr.length/2];
			for(int j=0; j < tmpArr.length / 2; j++) {
				tmpSet = myTools.getNewMovieTime(tmpArr[j*2+1], tmpArr[j*2+2]);
				tmpSets[j] = tmpSet;
			}

			tmpProgram = myTools.getNewMovieProgram(tmpTitle, tmpSets, i);
			program[i] = tmpProgram;
		}


	}



	// クラスgetter
	public WatchList getNewWatchList(){
		return new WatchList();
	}
	public WatchList getNewWatchList(int[] indexs, MovieTime[] timeSets) {
		return new WatchList(indexs, timeSets);
	}
	public MovieProgram getNewMovieProgram() {
		return new MovieProgram();
	}
	public MovieProgram getNewMovieProgram(String title, MovieTime[] timeSets, int index) {
		return new MovieProgram(title, timeSets, index);
	}
	public MovieTime getNewMovieTime() {
		return new MovieTime();
	}
	public MovieTime getNewMovieTime(String start, String end) {
		return new MovieTime(start, end);
	}




	// クラス定義


	// 見るつもりの映画と時間のセット、出力にも使う
	public class WatchList {
		int[] indexs;
		MovieTime[] timeSets;

		WatchList() {}

		WatchList(int[] indexs, MovieTime[] timeSets) {
			this.indexs   = indexs;
			this.timeSets = timeSets;
		}

		public int[] getIndexs() {
			return indexs;
		}
		public void setIndexs(int[] indexs) {
			this.indexs = indexs;
		}
		public MovieTime[] getTimeSets() {
			return timeSets;
		}
		public void setTimeSet(MovieTime[] timeSets) {
			this.timeSets = timeSets;
		}

	}

	//  まずここに入れる
	public class MovieProgram {
		String title;
		MovieTime[] timeSets;
		int index;

		MovieProgram() {}

		MovieProgram(String title, MovieTime[] timeSets, int index) {
			this.title    = title;
			this.timeSets = timeSets;
			this.index    = index;
		}

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public MovieTime[] getTimeSets() {
			return timeSets;
		}
		public void setTimeSets(MovieTime[] timeSets) {
			this.timeSets = timeSets;
		}

		public int getIndex() {
			return index;
		}
	}

	public class MovieTime {
		String start = "";
		String end   = "";
		int interval = 0;

		MovieTime() {}

		MovieTime(String start, String end) {
			this.start = start;
			this.end   = end;
		}

		public String getStart() {
			return start;
		}
		public void setStart(String start) {
			this.start = start;
		}
		public String getEnd() {
			return end;
		}
		public void setEnd(String end) {
			this.end = end;
		}
		public int getInterval() {
			return interval;
		}
		public void setInterval(int interval) {
			this.interval = interval;
		}

	}


}
