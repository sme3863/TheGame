package at.green.utils;

import java.util.HashMap;

public class Timers {

	
	public static HashMap<String,Timer> timers = new HashMap<String,Timer>();
	
	public static String createTimer(String timerName, int milliSeconds){
		//TODO check if timer is already present, else adjust name accordingly
		timers.put(timerName, new Timer(timerName,milliSeconds));
		return timerName;
	}
	
	
	public static boolean match(String timerName){
		return timers.get(timerName).match();
	}
	
	public static boolean matchThenRemove(String timerName){
		return timers.get(timerName).matchThenRemove();
	}
	
	public static void startTimer(String timerName){
		timers.get(timerName).start();
	}
	
	public static void removeTimer(String timerName){
		timers.remove(timerName);
	}
	
	public static void cleanUp(){
		for(Timer t: timers.values()){
			t.matchThenRemove();
		}
	}

	public static void reset(String string) {
		timers.get(string).start();
		
	}
	
}
