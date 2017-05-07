package com.eclipse.patrickconway;

import java.time.LocalDate;

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
		return 0.0f;
	}
	
	
}
