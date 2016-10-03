package pgcon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Q5 {
	public static final String firstJobName = "A";
	public Path criticalPathSet;

	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		HashMap<String, Job> jobMap = new HashMap<String, Job>();
		Q5 q5 = new Q5();
		String[] endFlag;
		String criticalPath;

		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);

		jobMap = q5.importJob(br);
		endFlag = q5.getEndFlagJob(jobMap);
		q5.getCriticalPath(jobMap, endFlag);

		criticalPath = q5.criticalPathSet.getPath();

		System.out.println(criticalPath);
	}

	Job getNewJob() {
		return new Job();
	}

	HashMap<String, Job> importJob(BufferedReader br) throws IOException {
		HashMap<String, Job> map = new HashMap<String, Job>();
		int totalJobNumber = 0;
		String tmpJobLine = "";
		String[] tmpJobDetail;
		Q5 q5 = new Q5();
		Job tmpJob = null;
		String tmpJobName = "";
		int tmpRunTime = 0;
		int tmpPrevJobNum = 0;
		String[] tmpPrevJob = null;

		String firstLine = br.readLine();
		totalJobNumber = Integer.parseInt(firstLine);

		for (int i = 0; i < totalJobNumber; i++) {
			tmpJob = q5.getNewJob();
			tmpJobLine = br.readLine();
			tmpJobDetail = tmpJobLine.split(" ");

			tmpJobName = tmpJobDetail[0];
			tmpRunTime = Integer.parseInt(tmpJobDetail[1]);
			tmpPrevJobNum = Integer.parseInt(tmpJobDetail[2]);
			if (tmpPrevJobNum != 0) {
				tmpPrevJob = new String[tmpPrevJobNum];

				for (int j = 0; j < tmpPrevJobNum; j++) {
					tmpPrevJob[j] = tmpJobDetail[3 + j];
				}
			}
			tmpJob.setRunTime(tmpRunTime);
			tmpJob.setPrevJobNum(tmpPrevJobNum);
			tmpJob.setPrevJob(tmpPrevJob);

			map.put(tmpJobName, tmpJob);
		}
		return map;
	}

	String[] getEndFlagJob(HashMap<String, Job> map) {
		Job tmpJob;
		String[] prevFlag = new String[26];
		String[] endFlag = new String[26];
		prevFlag[0] = "0";
		endFlag[0] = "0";
		int tmpPrevJobNum = 0;
		String[] tmpPrevJob;

		for (String key : map.keySet()) {
			tmpJob = map.get(key);
			tmpPrevJobNum = tmpJob.getPrevJobNum();
			if (tmpPrevJobNum != 0) {
				tmpPrevJob = tmpJob.getPrevJob();
				for (int i = 0; i < tmpPrevJobNum; i++) {
					if (!Arrays.asList(prevFlag).contains(tmpPrevJob[i])) {
						prevFlag[0] = (Integer.parseInt(prevFlag[0]) + 1) + "";
						prevFlag[Integer.parseInt(prevFlag[0])] = tmpPrevJob[i];
					}
				}
			}
		}
		for (String key : map.keySet()) {
			if (!Arrays.asList(prevFlag).contains(key)) {
				endFlag[0] = (Integer.parseInt(endFlag[0]) + 1) + "";
				endFlag[Integer.parseInt(endFlag[0])] = key;
			}
		}
		return endFlag;
	}

	void getCriticalPath(HashMap<String, Job> map, String[] eFlag) {
		criticalPathSet = new Path("", 0);
		Path pathStartSet = new Path("", 0);

		int eFlagNum = Integer.parseInt(eFlag[0]);

		for (int i = 1; i < eFlagNum + 1; i++) {
			goPrevPath(map, eFlag[i], pathStartSet, true);
		}
	}

	void goPrevPath(HashMap<String, Job> map, String jobName, Path pathSet, boolean eFlag) {
		Job tmpJob;
		int tmpTime;
		int tmpPrevNum;
		String tmpTotalPath;
		int tmpTotalTime;
		String[] prevJob;
		Path tmpPathSet = new Path(pathSet.getPath(), pathSet.getTime());

		if (eFlag) {
			if (jobName.equals(firstJobName)) {
				criticalPathSet.setPath(jobName);
				criticalPathSet.setTime(map.get(jobName).getRunTime());
			} else {
				tmpPathSet = new Path();
				tmpJob = map.get(jobName);
				tmpTotalPath = jobName;
				tmpTotalTime = tmpJob.getRunTime();
				tmpPrevNum = tmpJob.getPrevJobNum();
				prevJob = tmpJob.getPrevJob();
				tmpPathSet.setPath(tmpTotalPath);
				tmpPathSet.setTime(tmpTotalTime);

				for (int i = 0; i < tmpPrevNum; i++) {
					goPrevPath(map, prevJob[i], tmpPathSet, false);
				}
			}
		} else {
			tmpJob = map.get(jobName);
			tmpTotalPath = tmpPathSet.getPath();
			tmpTotalTime = tmpPathSet.getTime();
			tmpPrevNum = tmpJob.getPrevJobNum();
			prevJob = tmpJob.getPrevJob();
			tmpTime = tmpJob.getRunTime();
			tmpPathSet.setPath(jobName + tmpTotalPath);
			tmpPathSet.setTime(tmpTime + tmpTotalTime);
			if (jobName.equals(firstJobName)) {
				if (tmpPathSet.getTime() == criticalPathSet.getTime()) {
					if (criticalPathSet.getPath().compareTo(tmpPathSet.getPath()) > 0) {
						criticalPathSet.setPath(tmpPathSet.getPath());
						criticalPathSet.setTime(tmpPathSet.getTime());
					}
				} else if (tmpPathSet.getTime() > criticalPathSet.getTime()) {
					criticalPathSet.setPath(tmpPathSet.getPath());
					criticalPathSet.setTime(tmpPathSet.getTime());
				}
			} else {
				for (int i = 0; i < tmpPrevNum; i++) {
					goPrevPath(map, prevJob[i], tmpPathSet, false);
				}
			}

		}
	}

	public class Job {
		int runTime = 0;
		int prevJobNum = 0;
		String[] prevJob;

		public Job() {
		}

		public void setRunTime(int rTime) {
			this.runTime = rTime;
		}

		public int getRunTime() {
			return this.runTime;
		}

		public void setPrevJobNum(int pJobNum) {
			this.prevJobNum = pJobNum;
		}

		public int getPrevJobNum() {
			return this.prevJobNum;
		}

		public void setPrevJob(String[] pJob) {
			this.prevJob = pJob;
		}

		public String[] getPrevJob() {
			return this.prevJob;
		}
	}

	public class Path {
		String path = "";
		int time = 0;

		Path() {
		}

		Path(String p, int t) {
			this.path = p;
			this.time = t;
		}

		public void setPath(String Path) {
			this.path = Path;
		}

		public String getPath() {
			return this.path;
		}

		public void setTime(int rTime) {
			this.time = rTime;
		}

		public int getTime() {
			return this.time;
		}
	}

}