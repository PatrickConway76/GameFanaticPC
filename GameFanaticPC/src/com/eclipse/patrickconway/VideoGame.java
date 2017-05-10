package com.eclipse.patrickconway;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Stores a video game and related information
 * @author Patrick
 *
 */
public class VideoGame
{
	private String title;
	private float price;
	private LocalDate releaseDate;
	private float rating;
	
	/**
	 * Gets the title of the game
	 * @return The title of the game
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Sets the title of the game
	 * @param title The title of the game
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * Gets the price of the game
	 * @return The price of the game
	 */
	public float getPrice()
	{
		return price;
	}
	
	/**
	 * Sets the price of the game
	 * @param price The price of the game
	 */
	public void setPrice(float price)
	{
		this.price = price;
	}
	
	/**
	 * Gets the release date of the game
	 * @return The release date of the game
	 */
	public LocalDate getReleaseDate()
	{
		return releaseDate;
	}
	
	/**
	 * Sets the release date of the game
	 * @param releaseDate The release date of the game
	 */
	public void setReleaseDate(LocalDate releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	/**
	 * Gets the rating of the game
	 * @return The rating of the game
	 */
	public float getRating()
	{
		return rating;
	}
	
	/**
	 * Sets the rating of the game
	 * @param rating The rating of the game
	 */
	public void setRating(float rating)
	{
		this.rating = rating;
	}

	/**
	 * Constructs a VideoGame object with the title, price, release date, and rating set
	 * @param title The title of the game
	 * @param price The price of the game
	 * @param releaseDate The release date of the game
	 * @param rating The rating of the game
	 */
	public VideoGame(String title, float price, LocalDate releaseDate, float rating)
	{
		setTitle(title);
		setPrice(price);
		setReleaseDate(releaseDate);
		setRating(rating);
	}
	
	/**
	 * Parameterless constructor. These defaults are used to check if something was entered correctly
	 */
	public VideoGame()
	{
		setTitle("");
		setPrice(-1.0f);
		setReleaseDate(null);
		setRating(-1.0f);
	}
	
	/**
	 * Calculates the trade in value for a particular game
	 * @return The final trade in value of a game
	 */
	public float calculateTradeInValue()
	{
		long daysSinceRelease;
		int percentageMultiplier;
		float tradeInValue;
		
		final int DEGRADATION_DAYS = 60; // Number of days before the trade in value drops off
		final float PERCENTAGE_OFF = 0.10f; // Percentage the trade in value drops off
		final float LESS_THAN_DEGRADATION_DAYS_PERCENTAGE = 0.05f; // Percentage to take off if before 60 days
		
		// Calculates the number of days between the release date of the game, and today's date
		daysSinceRelease = ChronoUnit.DAYS.between(releaseDate, LocalDate.now());
		
		// We have a default percentage, but after a certain amount of days, that percentage increases 
		// So we have a percentage multiplier which increases the percentage by how many multiples of days that we have
		percentageMultiplier = (int) (daysSinceRelease / DEGRADATION_DAYS);
		
		if (percentageMultiplier == 0)
		{
			// We should still take some money off when they trade-in is before DEGRADATION_DAYS
			tradeInValue = this.getPrice() - (LESS_THAN_DEGRADATION_DAYS_PERCENTAGE * this.getPrice());
		}
		else
		{
			tradeInValue = this.getPrice() - ((percentageMultiplier * PERCENTAGE_OFF) * this.getPrice());
		}
		
		if (tradeInValue < 0 && this.getRating() < 8.5)
		{
			// The customer can't owe us money (unless it's E.T. for the Atari 2600), so just say it's worth nothing.
			tradeInValue = 0.0f;
		}
		else if (this.getRating() >= 8.5)
		{
			float tempTradeInValue = tradeInValue + 5; // Don't want to modify trade in value until we know the exact bonus
			
			// If trade in value is still less than 0 after $5 increase
			if (tempTradeInValue < 0)
			{
				tradeInValue = 0.0f;
			}
			else if (tempTradeInValue > this.getPrice())
			{
				// The trade in value shouldn't be able to exceed the price. Otherwise people could just buy something and trade it in for more money.
				tradeInValue = this.getPrice();
			}
			else if (tempTradeInValue < this.getPrice())
			{
				tradeInValue = tempTradeInValue;
			}
		}
		
		System.out.println("The trade-in value for " + title + " is: $" + String.format("%.2f", tradeInValue));
		
		return tradeInValue;
	}
	
	
}
