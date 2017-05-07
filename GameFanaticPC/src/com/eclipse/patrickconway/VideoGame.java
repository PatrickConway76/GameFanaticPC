package com.eclipse.patrickconway;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VideoGame
{
	private String title;
	private float price;
	private LocalDate releaseDate;
	private int rating;
	
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

	public int getRating()
	{
		return rating;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
	}
	
	public VideoGame(String title, float price, LocalDate releaseDate, int rating)
	{
		this.title = title;
		this.price = price;
		this.releaseDate = releaseDate;
		this.rating = rating;
	}
	
	public float calculateTradeInValue()
	{
		long daysSinceRelease;
		final int DEGRATION_DAYS = 60;
		int percentageMultiplier;
		float tradeInValue;
		final float PERCENTAGE_OFF = 0.10f;
		final float LESS_THAN_60_DAYS_PERCENTAGE = 0.05f;
		
		daysSinceRelease = ChronoUnit.DAYS.between(releaseDate, LocalDate.now());
		percentageMultiplier = (int) (daysSinceRelease / DEGRATION_DAYS);
		
		if (percentageMultiplier == 0)
		{
			tradeInValue = price - (LESS_THAN_60_DAYS_PERCENTAGE * price);
		}
		else
		{
			tradeInValue = price - ((percentageMultiplier * PERCENTAGE_OFF) * price);
		}
		
		if (tradeInValue < 0)
		{
			return 0.0f;
		}
		System.out.print(tradeInValue);
		
		
		
		return tradeInValue;
	}
	
	
}
