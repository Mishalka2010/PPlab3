package data;

import java.util.*;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.ArrayDeque;

public interface Task_executorApi<T> extends Runnable
{
	//public Task_executor(Task task, BlockingQueue<T> qIn,ArrayDeque<T> qOut, int sleepTime);
	
	public void Close();

	public void execute() throws InterruptedException;
	
	@Override
    public void run();
}