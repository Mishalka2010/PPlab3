package data;

import java.util.*;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.ArrayDeque;

public class Task_executor implements Task_executorApi<Location>
{
	private BlockingQueue<Location> qIn;
	private ArrayDeque<Location> qOut;
	private boolean isThreadActive;
	private Task task;
	private int sleepTime;
	
	public Task_executor(Task task, BlockingQueue<Location> qIn,ArrayDeque<Location> qOut, int sleepTime)
	{
		this.task=task;
		this.isThreadActive=true;
		this.qIn = qIn;
        this.qOut = qOut;
		this.sleepTime = sleepTime;
    }
	
	public void Close()
	{
		isThreadActive = false;
	}

	public void execute() throws InterruptedException
	{
		String weath;
		Location loc;
		int temp;
		
		synchronized (qIn)
		{
			loc = qIn.poll();
		}
		if (loc!=null)
		{	
			weath = task.randWeather();
			temp = Integer.parseInt(task.randTemper());
			boolean allRight = false;
			while (!allRight)
			{
				if (temp > 5 && weath == "Cold")
					temp = Integer.parseInt(task.randTemper());
				else if ((weath == "Frosty" || weath == "Hazily") && (temp < -5 || temp > 5))
					temp = Integer.parseInt(task.randTemper());
				else if (weath == "Hot" && temp < 25)
					temp = Integer.parseInt(task.randTemper());
				else if (weath == "Thunderstorm" && (temp < 10 || temp > 30))
					temp = Integer.parseInt(task.randTemper());
				else if (weath == "Rain" && (temp < 0 || temp > 30))
					temp = Integer.parseInt(task.randTemper());
				else if (weath == "Blizzard" && temp > -3)
					temp = Integer.parseInt(task.randTemper());
				else allRight = true;
			}	
			if (temp > 0)
				loc.weather = weath + " +" + temp + "*C";
			else loc.weather = weath + " " + temp + "*C";
			
			loc.debug = "Thread_"+Thread.currentThread().getId()+ ": ";
			synchronized (qOut)
			{
				qOut.add(loc);
			}
		}
	}
	
	@Override
    public void run()
	{
		while (isThreadActive)
		{
			try
			{
				execute();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			try
			{
				Thread.sleep(sleepTime);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}