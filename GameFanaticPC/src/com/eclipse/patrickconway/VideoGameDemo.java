package com.eclipse.patrickconway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VideoGameDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Boolean keepGoing = true;
		ArrayList<VideoGame> gameList = new ArrayList<>();
		VideoGame crap = new VideoGame("Game 1", 69.96f, LocalDate.now(), 10);
		VideoGame crap2 = new VideoGame("Game 2", 20.69f, LocalDate.now().minus(5, ChronoUnit.DAYS), 50);
		VideoGame crap3 = new VideoGame("Game 3", 25.68f, LocalDate.now().minus(9999, ChronoUnit.DAYS), 8.5f);
		gameList.add(crap);
		gameList.add(crap2);
		gameList.add(crap3);
		
		System.out.println("Welcome to the Game Fanatic trade in system.");
		
		while (keepGoing == true)
		{
			int selection = MainMenu();
			
			switch (selection)
			{
				case 1:	
					gameList.add(enterGame());
					break;
				case 2:	
					calculateTotalTradeInValue(gameList);
					break;
				case 3:
					keepGoing = false;
					break;
			}
		}				
	}
	
	public static float calculateTotalTradeInValue(ArrayList<VideoGame> gameList)
	{
		float totalValue = 0;
		
		for (int i = 0; i < gameList.size(); i++)
		{
			totalValue += gameList.get(i).calculateTradeInValue();
			System.out.println(totalValue);
		}
		
		return totalValue;
	}
	
	public static VideoGame enterGame()
	{
		String title;
		float price;
		int rating;
		String date;
				
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter the game title: ");
		title = sc.next();
		
		System.out.println("Please enter the game price: ");
		price = sc.nextFloat();
		
		System.out.println("Please enter the release date in YYYY-MM-DD format: ");
		date = sc.next();
		
		LocalDate releaseDate = LocalDate.parse(date);
		
		System.out.println("Please enter the rating for this game between 1-10: ");
		rating = sc.nextInt();
		
		VideoGame newGame = new VideoGame(title, price, releaseDate, rating);
		
		
		System.out.println(newGame.getTitle() + "\n" + newGame.getPrice() + "\n" + newGame.getReleaseDate().toString() + "\n" + newGame.getRating());
		
		return newGame;
	}
	
	public static int MainMenu()
	{
		Scanner sc = new Scanner(System.in);
		int input;
		Boolean validInput = true;
		
//		while (validInput)
//		{
//			try
//			{
				System.out.println("Please enter 1 to enter in a game, 2 to display total trade in values, or 3 to exit.");
				input = sc.nextInt();
				
//				if (input < 0 && input > 3)
//				{
//					
//				}
//				
//			}
//			catch (InputMismatchException e)
//			{
//				
//			}
//		}
		return input;
	}
	

}
