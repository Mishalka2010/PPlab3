package data;

import java.util.ArrayDeque;

public class Logger implements LoggerApi<Location>
{
	private ArrayDeque<Location> qOut;
	private boolean isThreadActive;
	private int sleepTime;
	public Logger(ArrayDeque<Location> qOut, int sleepTime)
	{
        this.qOut = qOut;
		this.isThreadActive = true;
		this.sleepTime = sleepTime;
    }
	
	public void Close()
	{
		isThreadActive = false;
	}
	
	@Override
    public void run()
	{
		Location loc;
		while (isThreadActive)
		{
			synchronized(qOut)
			{
				loc = qOut.poll();
			}
			if (loc != null)
			{
				System.out.println(loc.debug + loc.ID + " " + loc.city + " " + loc.date + " " + loc.weather);
				try{
					Thread.sleep(sleepTime);} 
				catch (InterruptedException e){
					e.printStackTrace();}
			}
			else try{
					Thread.sleep(50);} 
				catch (InterruptedException e){
					e.printStackTrace();}
		}
	}
}
