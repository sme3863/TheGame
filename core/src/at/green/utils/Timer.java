package at.green.utils;

import com.badlogic.gdx.utils.TimeUtils;

public class Timer {


	private long startTime;
	private int milliSeconds;
	private String timerName;
	
	public Timer(String timerName, int milliSeconds){
		this.startTime = 0;
		this.timerName = timerName;
		this.milliSeconds = milliSeconds;
	}
	
	public void start(){
		this.startTime = TimeUtils.millis();
	}
	
	public boolean match(){
		return (TimeUtils.millis() - this.startTime >= this.milliSeconds) ? true : false;
	}
	
	public boolean matchThenRemove(){
		boolean match = match();
		if(match){
			Timers.removeTimer(timerName);
		}
		return match;
	}
	
}
