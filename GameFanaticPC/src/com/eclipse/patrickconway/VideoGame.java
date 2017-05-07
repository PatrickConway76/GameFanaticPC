package com.eclipse.patrickconway;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VideoGame
{
	private String title;
	private float price;
	private LocalDate releaseDate;
	private float rating;
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public LocalDate getReleaseDate()
	{
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public float getRating()
	{
		return rating;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
	}
	
	public VideoGame(String title, float price, LocalDate releaseDate, float rating)
	{
		this.title = title;
		this.price = price;
		this.releaseDate = releaseDate;
		this.rating = rating;
	}
	
	public float calculateTradeInValue()
	{
		long daysSinceRelease;
		int percentageMultiplier;
		float tradeInValue;
		
		final int DEGRATION_DAYS = 60;
		final float PERCENTAGE_DISCOUNTED = 0.10f;
		final float LESS_THAN_60_DAYS_PERCENTAGE = 0.05f;
		
		daysSinceRelease = ChronoUnit.DAYS.between(releaseDate, LocalDate.now());
		percentageMultiplier = (int) daysSinceRelease / DEGRATION_DAYS;
		
		if (percentageMultiplier == 0)
		{
			tradeInValue = price - (LESS_THAN_60_DAYS_PERCENTAGE * price);
		}
		else
		{
			tradeInValue = price - ((percentageMultiplier * PERCENTAGE_DISCOUNTED) * price);
		}
		
		if (tradeInValue < 0 && rating < 8.5)
		{
			return 0.0f; // The customer can't owe us money (unless it's E.T. for the Atari 2600), so just say it's worth nothing.
		}
		else if (rating >= 8.5)
		{
			float tempTradeInValue = tradeInValue + 5; // Don't want to modify trade in value until we know the exact bonus
			
			if (tempTradeInValue < 0)
			{
				System.out.println(title + " 0.0");
				return 0.0f;
			}
			else if (tempTradeInValue > price)
			{
				tradeInValue = price;
			}
		}
		
		System.out.println(title + " " + tradeInValue);
		
		return tradeInValue;
	}
	
	
}
