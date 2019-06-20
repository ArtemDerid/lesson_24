package ua.lviv.lgs;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {

	private Set<Seance> seances;

	public Schedule() {
		super();
		this.seances = new TreeSet<>();
	}

	public Seance addSeance(Seance seance) {
		if (seance.getStartTime().getHour() < Cinema.getInstance().getOpen().getHour()) {
			System.out.println(
					"Cinema opens at " + Cinema.getInstance().getOpen() + "\nCan't add this seance to the Schedule");
			return seance;
		}
		if (seance.getEndTime().getHour() >= Cinema.getInstance().getClose().getHour()) {
			System.out.println(
					"Cinema closes at " + Cinema.getInstance().getClose() + "\nCan't add this seance to the Schedule");
			return seance;
		}
		seances.add(seance);
		System.out.println("You added the new seance " + seance + "\nto the Schedule " + seances);
		return seance;
	}

	public Seance createSeanse(Movie movie) {
		System.out.println("Enter the data to create a seance");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Plase enter the start time");
		System.out.println("Enter an hour");
		int startTimeHour = scanner.nextInt();

		System.out.println("Enter minutes");
		int startTimeMinutes = scanner.nextInt();

		Seance seance = new Seance(movie, new Time(startTimeMinutes, startTimeHour));
		seance.setterEndTime(seance);
		return seance;
	}

	public synchronized void removeSeance(Seance seance) {
		this.seances.remove(seance);
	}

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seances == null) ? 0 : seances.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (seances == null) {
			if (other.seances != null)
				return false;
		} else if (!seances.equals(other.seances))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Schedule: \n" + seances + "\n";
	}

}
