package ua.lviv.lgs;

public class Time implements Comparable<Time>{

	private int min;
	private int hour;

	public Time(int min, int hour) {
		super();
		setMin(min);
		setHour(hour);
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		if (min >= 0 && min < 60) {
			this.min = min;
		}
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		if (hour >= 0 && hour < 24) {
			this.hour = hour;
		}
	}

	@Override
	public String toString() {
		return hour + " hours " + min + " minutes";
	}

	@Override
	public int compareTo(Time o) {
		if(this.hour>o.getHour()) {
			return 1;
		}else if(this.hour<o.getHour()) {
			return -1;
		}else {
			if(this.min>o.getMin()) {
				return 1;
			}else if(this.min<o.getMin()) {
				return -1;
			}
		}
		return 0;
	}

}
