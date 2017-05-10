package com.eclipse.patrickconway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VideoGameDemo {

	public static void main(String[] args) {
		
		// Create the video game list and add in some default data
		ArrayList<VideoGame> gameList = new ArrayList<>();
		VideoGame vg1 = new VideoGame("Prey", 59.99f, LocalDate.of(2017, 05, 9), 8.5f);
		VideoGame vg2 = new VideoGame("Horizon Zero Dawn", 59.99f, LocalDate.of(2017, 02, 28), 9.0f);
		VideoGame vg3 = new VideoGame("Halo 5", 40.00f, LocalDate.of(2015, 10, 27), 7.0f);
		gameList.add(vg1);
		gameList.add(vg2);
		gameList.add(vg3);
		
		Boolean keepGoing = true;

		System.out.println("Welcome to the Game Fanatic trade in system.");
		
		// Keep displaying the menu after each action until the user enters 3 to exit
		while (keepGoing == true)
		{
			int selection = MainMenu();
			
			switch (selection)
			{
				case 1:	
					gameList.add(enterGame()); // Brings up the add game menu and adds the game to the list
					break;
				case 2:	
					calculateTotalTradeInValue(gameList); // Calculates the total trade in value from all games in the list
					break;
				case 3:
					viewGames(gameList); // Signals that we're done and need to exit
					break;
				case 4:
					keepGoing = false;
					break;
			}
		}
		
		System.out.println("Thank you for using the Game Fanatic trade-in system.");
	}
	
	public static void viewGames(ArrayList<VideoGame> gameList)
	{
		for (int i = 0; i < gameList.size(); i++)
		{
			System.out.println("Title: " + gameList.get(i).getTitle());
			System.out.println("Price: $" + gameList.get(i).getPrice());
			System.out.println("Release Date: " + gameList.get(i).getReleaseDate());
			System.out.println("Rating: " + gameList.get(i).getRating());
			System.out.println("");
		}
	}
	// Calculates the total trade in value by taking the trade in value for each game and adding it to the list
	public static void calculateTotalTradeInValue(ArrayList<VideoGame> gameList)
	{
		float totalValue = 0;
		
		for (int i = 0; i < gameList.size(); i++)
		{
			totalValue += gameList.get(i).calculateTradeInValue(); // Calls the method the generate the trade in values and adds it to the total
		}
		System.out.println("The total trade-in value for your " + gameList.size() + " games is $" + String.format("%.2f", totalValue) + ".");
	}
	
	// Menu for entering the game info
	public static VideoGame enterGame()
	{
		// Needed to parse date and send invalid date to exception handler
		String date = "";
		
		boolean isComplete = false;			
		Scanner sc = new Scanner(System.in);
		VideoGame newGame = new VideoGame();
		
		// Loop forever until the user enters valid info for each field
		while (isComplete == false)
		{
			try
			{
				// Each field uses the same pattern, except for string fields since they could be anything
				// If the field is empty, -1, or null then that signals that the field hasn't been correctly filled out, otherwise it's valid input
				// This allows us to skip asking for the name again if the user inputs an invalid date for example
				if (newGame.getTitle().isEmpty())
				{
					System.out.print("Please enter the game title: ");
					newGame.setTitle(sc.nextLine());
				}
				
				if (newGame.getPrice() == -1.0f)
				{
					System.out.print("Please enter the game price: ");
					newGame.setPrice(sc.nextFloat());
					
					// Since a negative number would be considered 'good' input, we need to make sure the user puts in something greater than 0
					// If it's less than 0 than throw an exception with a reason and ask them again
					if (newGame.getPrice() <= 0.0f)
					{
						newGame.setPrice(-1.0f);
						String message = "The price must be greater than 0. Please try again.";
						throw new InputMismatchException(message);
					}
				}
				
				if (newGame.getReleaseDate() == null)
				{
					System.out.print("Please enter the release date in YYYY-MM-DD format: ");
					date = sc.next();
					newGame.setReleaseDate(LocalDate.parse(date)); // This throws a DateTimeParseException if we can't parse the date
				}
				
				if (newGame.getRating() == -1.0f)
				{
					System.out.print("Please enter the rating for this game between 1-10: ");
					newGame.setRating(sc.nextInt());
					
					// Check to see if the rating is between 1 and 10
					// Throws a new exception with a custom error message if it's not
					if (newGame.getRating() < 1 || newGame.getRating() > 10)
					{
						newGame.setRating(-1.0f);
						String message = "The rating must be between 1-10. Please try again.";
						throw new InputMismatchException(message);
					}
				}
				
				isComplete = true; // If we've gotten this far, it means we're done
				//newGame = new VideoGame(title, price, releaseDate, rating);
			}
			
			// Comes up when the user enters in a string instead of numbers, or if the input is not in the range that we won't
			catch (InputMismatchException e )
			{
				String badInput = sc.nextLine(); // Consume bad input
				
				// If it's empty, it means the input was fine, but it didn't meet criteria
				if (badInput.equalsIgnoreCase(""))
				{
					System.out.println(e.getMessage());
				}
				else
				{
					System.out.println(badInput + " is invalid input. Please try again.");
				}
			}
			
			// If we can't parse the date
			catch (DateTimeParseException e)
			{
				System.out.println(date + " is invalid input. Please try again.");
			}
			
		}
		return newGame;
		

	}
	
	public static int MainMenu()
	{
		Scanner sc = new Scanner(System.in);
		int input = 0;
		Boolean isValidInput = false;
		
		while (isValidInput == false)
		{
			try
			{
				System.out.print("Please enter 1 to enter in a game, 2 to display total trade in values, 3 to display the list of games in the queue, or 4 to exit: ");
				input = sc.nextInt();
				isValidInput = true;
				
				if (input < 0 || input > 4)
				{
					System.out.println(input + " is invalid input. Please try again.");
				}
				
			}
			catch (InputMismatchException e)
			{
				String badInput = sc.nextLine();
				System.out.println(badInput + " is invalid input. Please try again.");
			}
		}
		return input;
	}
}
