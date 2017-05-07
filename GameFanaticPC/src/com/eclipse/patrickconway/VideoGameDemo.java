package com.eclipse.patrickconway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class VideoGameDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VideoGame crap = new VideoGame("Game 1", 69.96f, LocalDate.now(), 10);
		VideoGame crap2 = new VideoGame("Game 2", 20.69f, LocalDate.now().minus(5, ChronoUnit.DAYS), 10);
		
		crap2.calculateTradeInValue();
		
//		enterGame();
		
	}
	
	public float calculateAllTradeIns()
	{
		return 0.0f;
	}
	
	public static VideoGame enterGame()
	{
		String title;
		float price;
		int rating;
		String date;
		long crap;
				
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
		//System.out.println(crap);
		
		return newGame;
	}
	

}
