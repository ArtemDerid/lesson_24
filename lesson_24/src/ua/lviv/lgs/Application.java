package ua.lviv.lgs;

import java.util.Scanner;

public class Application {

	static void menu() {
		System.out.println("press 1 to add a movie to the movies library");
		System.out.println("press 2 to add a new seance to the schedule");
		System.out.println("press 3 to remove a movie from the movies library and from the schedule");
		System.out.println("press 4 to remove a movie from a schedule in a particular day of the week");
		System.out.println("press 5 to see the Cinema schedule");
	}

	public static void main(String[] args) {

		Cinema cinema = Cinema.getInstance();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			menu();
			switch (scanner.next()) {
			case "1": {
				cinema.addMovie(cinema.searchMovie());
			}

				break;

			case "2": {
				cinema.addSeance();
			}
				break;

			case "3": {
				cinema.removeMovie();
			}
				break;

			case "4": {
				cinema.removeSeance();
			}
				break;
				
			case "5": {
				System.out.println(cinema);
			}
				break;
			}
		}

	}

}
