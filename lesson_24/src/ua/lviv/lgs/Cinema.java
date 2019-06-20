package ua.lviv.lgs;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Cinema {

	private static final Cinema instance = new Cinema();
	private TreeMap<Days, Schedule> schedules;
	private ArrayList<Movie> moviesLibrary = new ArrayList<>();
	private Time open;
	private Time close;

	private Cinema() {
		schedules = new TreeMap<>();
		Days daysOfWeek[] = Days.values();
		for (int i = 0; i < daysOfWeek.length; i++) {
			schedules.put(daysOfWeek[i], new Schedule());
		}
		open = new Time(00, 10);
		close = new Time(00, 23);
	}

	public static Cinema getInstance() {
		return instance;
	}

	public Movie addMovie(Movie movie) {
		moviesLibrary.add(movie);
		System.out.println("You added the movie: \n" + movie + "\nto the library \n" + moviesLibrary);
		return movie;

	}

	public Movie searchMovie() {
		System.out.println("Enter a movie");
		System.out.println("First enter a title");
		Scanner scanner = new Scanner(System.in);
		String title = scanner.nextLine();

		System.out.println("Now enter the movie's duration");
		System.out.println("Enter hours");
		int hour = scanner.nextInt();

		System.out.println("Enter minutes");
		int min = scanner.nextInt();

		Movie movie = new Movie(title, new Time(min, hour));
		return movie;
	}

	public void addSeance() {
		System.out.println("Enter the day of week");
		Scanner scanner = new Scanner(System.in);
		String dayOfWeek = scanner.next();
		Days[] days = Days.values();
		Days day = null;

		for (int i = 0; i < days.length; i++) {
			if (dayOfWeek.toUpperCase().equals(days[i].toString())) {
				day = days[i];
			}
		}

		if (day == null) {
			System.out.println("You enter incorrect day");
			return;
		}

		if (day != null) {
			Movie movie = searchMovie();

			Optional<Movie> findMovie = moviesLibrary.stream().filter(m -> m.compareTo(movie) == 0).findFirst();
			if (!findMovie.isPresent()) {
				System.out.println("There is no such movie in movie library");
				return;
			}

			schedules.get(day).addSeance(schedules.get(day).createSeanse(movie));
		}
	}

	public void removeMovie() {
		Movie movie = searchMovie();
		Days[] days = Days.values();
		for (int i = 0; i < days.length; i++) {
			Set<Seance> seances = schedules.get(days[i]).getSeances();
			Optional<Seance> removedSeance = seances.stream().filter(s -> s.getMovie().compareTo(movie) == 0)
					.findFirst().map(s -> {
						seances.remove(s);
						return s;
					});
			if (removedSeance.isPresent()) {
				System.out.println("You removed seance " + removedSeance.get() + "from the schedule");
			}
		}

		Optional<Movie> removedMovie = moviesLibrary.stream().filter(m -> m.compareTo(movie) == 0).findFirst()
				.map(m -> {
					moviesLibrary.remove(m);
					return m;
				});
		if (removedMovie.isPresent()) {
			System.out.println("You removed movie " + removedMovie.get() + " from movies Library");
		} else {
			System.out.println("There is no such movie in moviesLibrary");
		}

	}

	public void removeSeance() {

		System.out.println("Enter the day of week");
		Scanner scanner = new Scanner(System.in);
		String dayOfWeek = scanner.next();
		Days[] days = Days.values();
		Movie movie = searchMovie();

		for (int i = 0; i < days.length; i++) {
			if (dayOfWeek.toUpperCase().equals(days[i].toString())) {
				Schedule schedule = schedules.get(days[i]);
				Optional<Seance> removedSeance = schedule.getSeances().stream()
						.filter(s -> s.getMovie().compareTo(movie) == 0).findFirst().map(s -> {
							schedule.getSeances().remove(s);
							return s;
						});

				if (removedSeance.isPresent()) {
					System.out.println("You removed the seanse " + removedSeance.get());
				} else {
					System.out.println("There is no such seance in our schedule");
				}

			}
		}
	}

	public TreeMap<Days, Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(TreeMap<Days, Schedule> schedules) {
		this.schedules = schedules;
	}

	public ArrayList<Movie> getMoviesLibrary() {
		return moviesLibrary;
	}

	public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
		this.moviesLibrary = moviesLibrary;
	}

	public Time getOpen() {
		return open;
	}

	public void setOpen(Time open) {
		this.open = open;
	}

	public Time getClose() {
		return close;
	}

	public void setClose(Time close) {
		this.close = close;
	}

	@Override
	public String toString() {
		return "Cinema schedules= \n" + schedules + "\nopens at " + open + ", closes at " + close + "\n";
	}

}
