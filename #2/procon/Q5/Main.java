/*
まだコーディング途中です。

*/
package procon.Q5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static int numOfMovie;
	public static int minInterval;

	public static MovieProgram program[];
	public static WatchList bestWatchList;
	public static final String regexBlank = "[\\s]+";

	public static void main(String[] args) throws IOException {
		Main myTools = new Main();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		numOfMovie = Integer.parseInt(br.readLine());
		minInterval = Integer.parseInt(br.readLine());

		program = new MovieProgram[numOfMovie];
		WatchList list;
		String tmpLine;
		String tmpArr[];
		MovieProgram tmpProgram;
		String tmpTitle;
		MovieTime tmpSets[];
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

			tmpProgram = myTools.getNewMovieProgram(tmpTitle, tmpSets, i+1);
			program[i] = tmpProgram;
		}

/*		for(int i=0; i< numOfMovie; i++) {
			System.out.println(i+":");
			System.out.println("index : "+program[i].getIndex());
			System.out.println("title : "+program[i].getTitle());
			tmpSets = program[i].getTimeSets();

			for(int j=0;j<tmpSets.length; j++) {
				System.out.println("set."+j+":");
				System.out.println("  start : "+tmpSets[j].getStart());
				System.out.println("  end : "+tmpSets[j].getEnd());
			}
		}
*/

		// createWatchList
		bestWatchList = myTools.getNewWatchList(new int[numOfMovie], new MovieTime[numOfMovie]);
		list = myTools.getNewWatchList(new int[numOfMovie], new MovieTime[numOfMovie]);

		myTools.searchBestProgram(list);
		myTools.showBestList();
	}

	public void showBestList(){
		Main myTools = new Main();
		int num = bestWatchList.getNumOfList();
		int com = bestWatchList.getCombi();
		int indexes[] = bestWatchList.getIndexes();
		MovieTime set[] = bestWatchList.getTimeSets();

		System.out.println(num);
		System.out.println(com);
		for(int i=0; i < num; i++){
			System.out.print(
				indexes[i] + " " + myTools.displayTime(set[i].getStart()) + " " +  myTools.displayTime(set[i].getEnd())
			);

			if((i+1) != num) {
				System.out.print(" " + set[i].getInterval());
			}
			System.out.println();
		}
	}

	public void searchBestProgram(WatchList list){
		//すべての映画を考慮していく
		for(int i=0; i < program.length; i++){
			MovieProgram tmpPg = program[i];
			int       nowIndex = tmpPg.getIndex();
			System.out.println("i : "+ i);
			System.out.println("in : "+ nowIndex);

			if(!list.isWatched(nowIndex)){
				MovieTime tmpPgSets[] = tmpPg.getTimeSets();

				//ある映画を前から見れるか見ていく
				LoopJ:
				for(int j=0; j < tmpPgSets.length; j++){
					System.out.println("j : "+ j);
					System.out.println(tmpPgSets.length);
					MovieTime tmpPgSet = tmpPgSets[j];

					Main myTools = new Main();
					int tmpNumOfList = list.getNumOfList();

					MovieTime tmpLSets[] = list.getTimeSets();

					String end;
					if(tmpNumOfList == 0) {
						end = "00:00";
					} else {
						end = tmpLSets[tmpNumOfList-1].getEnd();
					}
					String next = tmpPgSet.getStart();

					//見れるかな？
					System.out.println(end+"---"+next);
					if(myTools.isAbleToWatch(end, next)){
						int tmpIndexes[] = new int[list.getIndexes().length];
						MovieTime tmpTimeSets[] = new MovieTime[list.getIndexes().length];

						for(int k=0; k < list.getIndexes().length; k++){
							tmpIndexes[k] = list.getIndexes()[k];
							tmpTimeSets[k] = myTools.getNewMovieTime(new String(list.getTimeSets()[k].getStart()), new String(list.getTimeSets()[k].getEnd()));
						}
						WatchList tmpWatchList = myTools.getNewWatchList(tmpIndexes, tmpTimeSets);
						for(int k =0; k < list.getIndexes().length; k++ ){
							System.out.println(k+":"+list.getIndexes()[k]);
						}
						int tmpNumOfWl = tmpWatchList.getNumOfList();
						int tmpWlIndexes[] = tmpWatchList.getIndexes();
						MovieTime tmpWlSets[] = tmpWatchList.getTimeSets();

						System.out.println("eee: "+ tmpNumOfWl);
						tmpWlIndexes[tmpNumOfWl] = nowIndex;
						tmpWlSets[tmpNumOfWl] = tmpPgSet;

						if (tmpNumOfWl > 1) {
							String prevEnd = tmpWlSets[tmpNumOfWl - 2].getEnd();
							String nowStart = tmpPgSet.getStart();

							int diff = myTools.differTime(prevEnd, nowStart);
							tmpWlSets[tmpNumOfWl - 2].setInterval(diff);
						}

						tmpWatchList.setIndexes(tmpWlIndexes);
						tmpWatchList.setTimeSet(tmpWlSets);

						if(tmpWatchList.getNumOfList() == numOfMovie){
							if(myTools.isBetter(tmpWatchList)){
								bestWatchList.setIndexes(tmpWatchList.getIndexes());
								bestWatchList.setTimeSet(tmpWatchList.getTimeSets());
							}
							if(bestWatchList.getMaxList() == numOfMovie){
								bestWatchList.addCombi();
							} else {
								bestWatchList.resetCombi();
							}
							bestWatchList.setMaxList(bestWatchList.getNumOfList());
							System.out.println("end");
							myTools.searchBestProgram(list);
						} else {
							System.out.println("go");
							myTools.searchBestProgram(tmpWatchList);
						}

					} else {
						//見れなかった・・・
						if(j+1 == tmpPgSets.length) {
							if(myTools.isBetter(list)){
								bestWatchList.setIndexes(list.getIndexes());
								bestWatchList.setTimeSet(list.getTimeSets());
							}
							if(bestWatchList.getMaxList() == list.getNumOfList()){
								bestWatchList.addCombi();
							} else {
								bestWatchList.resetCombi();
							}
							bestWatchList.setMaxList(bestWatchList.getNumOfList());
						}
					}
				}
			}
		}
	}

	public boolean isAbleToWatch(String endTime, String nextTime){
		Main myTools = new Main();
		endTime = addTime(endTime, minInterval);
		System.out.println(endTime+"---"+nextTime);

		return myTools.isEarlier(endTime, nextTime);
	}

	public String addTime(String endTime, int interval) {
		String timeArr[] = endTime.split(":");
		int hh = Integer.parseInt(timeArr[0]);
		int mm = Integer.parseInt(timeArr[1]);
		int hour;

		mm += interval;
		hour = mm / 60;
		hh += hour;
		mm -= (hour * 60);

		return hh + ":" + mm;
	}

	public int differTime(String endTime, String nextTime){
		int diff;
		String endArr[] = endTime.split(":");
		String nextArr[]  = nextTime.split(":");

		int endHH  = Integer.parseInt(endArr[0]);
		int endMM  = Integer.parseInt(endArr[1]);
		int nextHH = Integer.parseInt(nextArr[0]);
		int nextMM = Integer.parseInt(nextArr[1]);

		diff = (nextHH - endHH)*60  + (nextMM - endMM);

		return diff;
	}

	public String displayTime(String time){
		String arr[] = time.split(":");
		arr[0] = ("0" + arr[0]).substring(0, 2);
		arr[1] = ("0" + arr[1]).substring(0, 2);

		return arr[0] + ":" + arr[1];
	}

	//同時刻でもTRUE
	public boolean isEarlier(String early, String late){
		boolean earlier = false;
		String earlyArr[] = early.split(":");
		String lateArr[]  = late.split(":");

		int earlyHH = Integer.parseInt(earlyArr[0]);
		int earlyMM = Integer.parseInt(earlyArr[1]);
		int lateHH  = Integer.parseInt(lateArr[0]);
		int lateMM  = Integer.parseInt(lateArr[1]);

		if(earlyHH < lateHH){
			earlier = true;
		} else if ((earlyHH == lateHH) && (earlyMM <= lateMM)){
			earlier = true;
		}

		return earlier;
	}

	public boolean isSame(String early, String late){
		boolean same = false;
		String earlyArr[] = early.split(":");
		String lateArr[]  = late.split(":");

		int earlyHH = Integer.parseInt(earlyArr[0]);
		int earlyMM = Integer.parseInt(earlyArr[1]);
		int lateHH  = Integer.parseInt(lateArr[0]);
		int lateMM  = Integer.parseInt(lateArr[1]);

		if((earlyHH == lateHH) && (earlyMM == lateMM)) {
			same = true;
		}

		return same;
	}


	public boolean isPrior(WatchList priorList, WatchList posteriorList) {
		boolean prior = false;

		for(int i=0; i < priorList.indexes.length; i++){
			if(priorList.indexes[i] < posteriorList.indexes[i]){
				prior = true;
				break;
			} else if(priorList.indexes[i] > posteriorList.indexes[i]){
				break;
			}
		}

		return prior;

	}

	public boolean isBetter(WatchList tmp){
		boolean isBetter = false;
		Main myTools = new Main();

		if (bestWatchList.getNumOfList() == 0) {
			isBetter = true;
		} else {
			if (bestWatchList.getNumOfList() < tmp.getNumOfList()) {
				isBetter = true;
			} else {
				MovieTime tmpTimes[] = tmp.getTimeSets();
				MovieTime bestTimes[] = bestWatchList.getTimeSets();

				String tmpStart = tmpTimes[0].getStart();
				String bestStart = bestTimes[0].getStart();

				if (myTools.isEarlier(bestStart, tmpStart)) {
					// tmpのほうが遅いのでok
					if (myTools.isSame(bestStart, tmpStart)) {
						if(myTools.isPrior(tmp, bestWatchList)) {
							isBetter = true;
						}
					} else {
						isBetter = true;
					}
				}
			}
		}
		return isBetter;
	}

	// クラスgetter
	public WatchList getNewWatchList() {
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
		int[] indexes;
		MovieTime[] timeSets;

		int maxList;
		int combi;

		WatchList() {
		}

		WatchList(int[] indexes, MovieTime[] timeSets) {
			this.indexes = indexes;
			this.timeSets = timeSets;
			this.maxList = 0;
			this.combi = 0;
		}

		public int[] getIndexes() {
			return indexes;
		}

		public void setIndexes(int[] indexes) {
			this.indexes = indexes;
		}

		public MovieTime[] getTimeSets() {
			return timeSets;
		}

		public void setTimeSet(MovieTime[] timeSets) {
			this.timeSets = timeSets;
		}

		public int getMaxList() {
			return maxList;
		}

		public void setMaxList(int maxList) {
			this.maxList = maxList;
		}

		public int getCombi() {
			return combi;
		}

		public void addCombi() {
			combi += 1;
		}

		public void resetCombi() {
			combi = 1;
		}

		public int getNumOfList() {
			int cnt = 0;
			for (int i = 0; i < indexes.length; i++) {
				if (indexes[i] != 0) {
					cnt++;
				}
			}
			return cnt;
		}

		public boolean isWatched(int index){
			boolean watched = false;
			for(int i=0; i < indexes.length; i++){
				if(indexes[i] == index){
					watched = true;
				}
			}
			return watched;
		}

	}

	// まずここに入れる
	public class MovieProgram {
		String title;
		MovieTime[] timeSets;
		int index;

		MovieProgram() {
		}

		MovieProgram(String title, MovieTime[] timeSets, int index) {
			this.title = title;
			this.timeSets = timeSets;
			this.index = index;
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
		String end = "";
		int interval = 0;

		MovieTime() {
		}

		MovieTime(String start, String end) {
			this.start = start;
			this.end = end;
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
