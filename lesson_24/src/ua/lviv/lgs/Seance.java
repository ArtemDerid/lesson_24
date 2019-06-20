package ua.lviv.lgs;

public class Seance implements Comparable<Seance> {

	private Movie movie;
	private Time startTime;
	private Time endTime;

	public Seance(Movie movie, Time startTime) {
		super();
		this.movie = movie;
		this.startTime = startTime;
	}

	public Time setterEndTime(Seance seance) {

		int timeHour = seance.startTime.getHour() + movie.getDuration().getHour();
		int timeMin = seance.startTime.getMin() + movie.getDuration().getMin();

		if (timeMin >= 60) {
			timeMin = timeMin - 60;
			timeHour++;
		}

		if (timeHour >= 24) {
			timeHour = timeHour - 24;
		}
		seance.setEndTime(new Time(timeMin, timeHour));
		return endTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Seance: movie=" + movie + ", startTime=" + startTime + ", endTime=" + endTime + "\n";
	}

	@Override
	public int compareTo(Seance o) {
		if (this.movie.compareTo(o.getMovie()) > 0) {
			return 1;
		} else if (this.movie.compareTo(o.getMovie()) < 0) {
			return -1;
		} else {
			if (this.startTime.compareTo(o.getStartTime()) > 0) {
				return 1;
			} else if (this.startTime.compareTo(o.getStartTime()) < 0) {
				return -1;
			}
		}
		return 0;
	}

}
