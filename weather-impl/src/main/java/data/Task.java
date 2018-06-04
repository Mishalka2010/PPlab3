package data;

import java.util.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task implements TaskApi<Location>
{
	public static Random random = new Random();
	public static BlockingQueue<Location> qIn;
	public static Date date = new Date();
	public static ArrayList<String> weather = new ArrayList<String>();
	public static ArrayList<String> temper = new ArrayList<String>();
	public static ArrayList<String> locations = new ArrayList<String>();
	public static int id = 0;
	
	public Task(BlockingQueue<Location> qIn)
	{
		this.qIn = qIn;
		weather.add("Sunny");
		weather.add("Cloudy");
		weather.add("Hazily");
		weather.add("Wet");
		weather.add("Frosty");
		weather.add("Hot");
		weather.add("Rain");
		weather.add("Thunderstorm");
		weather.add("Blizzard");
		weather.add("Cold");
		
		locations.add("Moscow");
		locations.add("Saint Petersburg");
		locations.add("Berlin");
		locations.add("Vilnus");
		locations.add("Saratov");
		locations.add("Belgorod");
		locations.add("Ekaterinburg");
		locations.add("Tambov");
		locations.add("New York");
		locations.add("Rostov");
		locations.add("Washington");
		locations.add("Novosibirsk");
		locations.add("Nizhny Novgorod");
		locations.add("Kazan");
		
		int j = 0;
		for (int i = -30; i < 40; i++)
		{
				temper.add(""+i);
		}
	}
	
	public String randWeather()
	{
		return weather.get(random.nextInt(weather.size()));
	}

	public String randLocation()
	{
		return locations.get(random.nextInt(locations.size()));
	}

	public String randTemper()
	{
		random = new Random();
		return temper.get(random.nextInt(temper.size()));
	}

	public void createTasks(int n)
	{
		while (n>0)
		{
			Location loc = new Location();
			loc.ID = id++;
			loc.city = randLocation();
			loc.date = date;
			qIn.add(loc);
			--n;
		}
	}
}