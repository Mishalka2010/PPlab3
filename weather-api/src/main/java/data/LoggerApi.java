package data;

import java.util.ArrayDeque;

public interface LoggerApi<T> extends Runnable
{
	//public Logger(ArrayDeque<T> qOut, int sleepTime);
	
	public void Close();
	
	@Override
    public void run();
}
