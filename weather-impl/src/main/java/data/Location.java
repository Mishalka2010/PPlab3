package data;

import java.util.Date;

public class Location implements LocationApi
{
	public Location()
	{
		debug = "";
	}
	public Location(String debug, int ID, Date date, String city, String weather)
	{
		this.debug = debug;
		this.ID = ID;
		this.date = date;
		this.city = city;
		this.weather = weather;
	}
	public String debug;
	public int ID;
	public Date date;
	public String city;
	public String weather;
}