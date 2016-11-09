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
		bestWatchList = myTools.getNewWatchList(new int[numOfMovie], new int[numOfMovie]);
		list = myTools.getNewWatchList(new int[numOfMovie], new int[numOfMovie]);

		myTools.searchBestProgram(list);
		System.out.println("end!!!!!");
		myTools.showBestList();
	}

	public void showBestList(){
		int num = bestWatchList.getNumOfList();
		int com = bestWatchList.getCombi();
		int indexes[] = bestWatchList.getIndexes();
		int set[] = bestWatchList.getTimeSets();
		int interval[] = bestWatchList.getInterval(); 
		MovieProgram tmpMovie;

		System.out.println(num);
		System.out.println(com);
		for(int i=0; i < num; i++){
			tmpMovie = program[indexes[i]-1];
			System.out.print(
				tmpMovie.getTitle() + " " + tmpMovie.getTimeSets()[set[i]].getStart().displayTime() + " " + tmpMovie.getTimeSets()[set[i]].getEnd().displayTime()
			);

			if((i+1) != num) {
				System.out.print(" " + interval[i]);
			}
			System.out.println();
		}
	}

	public boolean ttt = true;
	public int cnt = 0;
	public void searchBestProgram(WatchList list) {
		//すべての映画を考慮していく
		for(int i=0; i < program.length; i++){
			MovieProgram tmpPg = program[i];
			int       nowIndex = tmpPg.getIndex();
			if(cnt < 800){
			System.out.println("i : "+ i);
			}
//			System.out.println("in : "+ nowIndex);

			if(!list.isWatched(nowIndex)){
				MovieTime tmpPgSets[] = tmpPg.getTimeSets();

				//ある映画を前から見れるか見ていく
				for(int j=0; j < tmpPgSets.length; j++){
					if(cnt < 800){
					System.out.println(tmpPgSets.length);
					ttt=false;
					System.out.print("j : "+ j + ", ");
					cnt++;
					}
//					System.out.println(tmpPgSets.length);
					MovieTime tmpPgSet = tmpPgSets[j];

//					Main myTools = new Main();
					int tmpNumOfList = list.getNumOfList();

					int tmpLIndexes[] = list.getIndexes();
					int tmpLSets[] = list.getTimeSets();

					Time end;
					if(tmpNumOfList == 0) {
						end = new Time(0,0);
					} else {
						int tmpLIndex = tmpLIndexes[tmpNumOfList-1];
						int tmpLSet   = tmpLSets[tmpNumOfList-1];
						end = program[tmpLIndex-1].getTimeSets()[tmpLSet].getEnd();
					}
					Time next = tmpPgSet.getStart();

					//見れるかな？
//					System.out.println(end+"---"+next);
					if(isAbleToWatch(end, next)){
						int tmpIndexes[] = list.getIndexes().clone();
						int tmpTimeSets[] = list.getTimeSets().clone();
						int tmpInterval[] = list.getInterval().clone();
						
						WatchList tmpWatchList = getNewWatchList(tmpIndexes, tmpTimeSets);
						tmpWatchList.setInterval(tmpInterval);
						tmpWatchList.setNumOfList(list.getNumOfList());
						
						if(cnt < 800){
							System.out.println(end.displayTime()+", "+ next.displayTime());
							System.out.println("list    : "+list.getNumOfList());
							System.out.println("tmplist : "+tmpWatchList.getNumOfList());
						}

						//////////////////////ここから
						int tmpNumOfWl = tmpWatchList.getNumOfList();
						int tmpWlIndexes[] = tmpWatchList.getIndexes();
						int tmpWlSets[] = tmpWatchList.getTimeSets();

//						System.out.println("eee: "+ tmpNumOfWl);
						tmpWlIndexes[tmpNumOfWl] = nowIndex;
						tmpWlSets[tmpNumOfWl] = j;
						tmpWatchList.addNumOfList();

						if (tmpNumOfWl > 1) {
							Time prevEnd = tmpPgSets[tmpNumOfWl-2].getEnd();
							Time nowStart = tmpPgSet.getStart();

							int diff = differTime(prevEnd, nowStart);
							tmpWatchList.setInterval(diff, tmpNumOfWl-2);
						}

						tmpWatchList.setIndexes(tmpWlIndexes);
						tmpWatchList.setTimeSet(tmpWlSets);

						if(tmpWatchList.getNumOfList() == numOfMovie){
							if(isBetter(tmpWatchList)){
								bestWatchList.setIndexes(tmpWatchList.getIndexes());
								bestWatchList.setTimeSet(tmpWatchList.getTimeSets());
								bestWatchList.setInterval(tmpWatchList.getInterval());
								bestWatchList.setNumOfList(numOfMovie);
							}
							bestWatchList.setMaxList(bestWatchList.getNumOfList());
							if(bestWatchList.getMaxList() == numOfMovie){
								bestWatchList.addCombi();
							}
//							System.out.println("end");
//							tmpWatchList = null;
						} else {
//							System.out.println("go");
							searchBestProgram(tmpWatchList);
						}

					} else {
						//見れなかった・・・
						if(j+1 == tmpPgSets.length) {
							if(isBetter(list)){
								bestWatchList.setIndexes(list.getIndexes());
								bestWatchList.setTimeSet(list.getTimeSets());
								bestWatchList.setInterval(list.getInterval());
								bestWatchList.setNumOfList(list.getNumOfList());
							}
							bestWatchList.setMaxList(bestWatchList.getNumOfList());
							if(bestWatchList.getMaxList() == list.getNumOfList()){
								bestWatchList.addCombi();
							} else if(bestWatchList.getMaxList() < list.getNumOfList()){
								bestWatchList.resetCombi();
							}
						}
					}
				}
			}
		}
	}

	public boolean isAbleToWatch(Time endTime, Time nextTime){
		endTime = addTime(endTime, minInterval);
//		System.out.println(endTime+"---"+nextTime);

		return isEarlier(endTime, nextTime);
	}

	public Time addTime(Time endTime, int interval) {
		int hh = endTime.getHour();
		int mm = endTime.getMinute();
		int hour;

		mm += interval;
		hour = mm / 60;
		hh += hour;
		mm -= (hour * 60);
		
		Time result = new Time(hh, mm);

		return result;
	}

	public int differTime(Time endTime, Time nextTime){
		int diff;

		int endHH = endTime.getHour();
		int endMM = endTime.getMinute();
		int nextHH  = nextTime.getHour();
		int nextMM  = nextTime.getMinute();

		diff = (nextHH - endHH)*60  + (nextMM - endMM);

		return diff;
	}

	//同時刻でもTRUE
	public boolean isEarlier(Time early, Time late){
		boolean earlier = false;

		int earlyHH = early.getHour();
		int earlyMM = early.getMinute();
		int lateHH  = late.getHour();
		int lateMM  = late.getMinute();

		if(earlyHH < lateHH){
			earlier = true;
		} else if ((earlyHH == lateHH) && (earlyMM <= lateMM)){
			earlier = true;
		}

		return earlier;
	}

	public boolean isSame(Time early, Time late){
		boolean same = false;

		int earlyHH = early.getHour();
		int earlyMM = early.getMinute();
		int lateHH  = late.getHour();
		int lateMM  = late.getMinute();

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

		if (bestWatchList.getNumOfList() == 0) {
			isBetter = true;
		} else {
			if (bestWatchList.getNumOfList() < tmp.getNumOfList()) {
				isBetter = true;
			} else if (bestWatchList.getNumOfList() < tmp.getNumOfList()) {
				MovieTime tmpTimes[] = program[tmp.getIndexes()[0]].getTimeSets();
				MovieTime bestTimes[] = program[bestWatchList.getIndexes()[0]].getTimeSets();

				Time tmpStart = tmpTimes[0].getStart();
				Time bestStart = bestTimes[0].getStart();

				if (isEarlier(bestStart, tmpStart)) {
					// tmpのほうが遅いのでok
					if (isSame(bestStart, tmpStart)) {
						if(isPrior(tmp, bestWatchList)) {
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

	public WatchList getNewWatchList(int[] indexs, int[] timeSets) {
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
		int[] timeSets;
		int[] interval;
		int numOfList;

		int maxList;
		int combi;

		WatchList() {
		}

		WatchList(int[] indexes, int[] timeSets) {
			this.indexes = indexes;
			this.timeSets = timeSets;
			this.interval = new int[numOfMovie];
			this.numOfList = 0;
			this.maxList = 0;
			this.combi = 0;
		}

		public int[] getIndexes() {
			return indexes;
		}

		public void setIndexes(int[] indexes) {
			this.indexes = indexes;
		}

		public int[] getTimeSets() {
			return timeSets;
		}

		public int[] getInterval() {
			return interval;
		}

		public void setInterval(int[] interval) {
			this.interval = interval;
		}

		public void setInterval(int value, int index) {
			this.interval[index] = value;
		}

		public void setTimeSet(int[] timeSets) {
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

		public void setNumOfList(int numOfList) {
			this.numOfList = numOfList;
		}
		
		public int getNumOfList() {
			return numOfList;
		}
		
		
		public void addNumOfList(){
			numOfList++;
		}

		public boolean isWatched(int index){
			for(int i=0; i < indexes.length; i++){
				if(indexes[i] == index){
					return true;
				}
			}
			return false;
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
		Time start = new Time();
		Time end = new Time();

		MovieTime() {
		}

		MovieTime(String start, String end) {
			this.start.setTime(start);;
			this.end.setTime(end);;
		}

		public Time getStart() {
			return start;
		}

		public void setStart(Time start) {
			this.start = start;
		}

		public Time getEnd() {
			return end;
		}

		public void setEnd(Time end) {
			this.end = end;
		}

	}
	
	public class Time {
		int hour = 0;
		int minute = 0;

		Time() {
		}

		Time(int hh, int mm) {
			this.hour   = hh;
			this.minute = mm;
		}

		public int getHour() {
			return hour;
		}

		public void setHour(int hh) {
			this.hour = hh;
		}

		public int getMinute() {
			return minute;
		}

		public void setMinute(int mm) {
			this.minute = mm;
		}
		
		public void setTime(String time){
			String[] arr = time.split(":");
			this.hour = Integer.parseInt(arr[0]);
			this.minute = Integer.parseInt(arr[1]);
		}

		public String displayTime(){
			String hh = "0" + this.hour;
			String mm = "0" + this.minute;

			hh = hh.substring(hh.length()-2);
			mm = mm.substring(mm.length()-2);

			return hh + ":" + mm;
		}

	}


}