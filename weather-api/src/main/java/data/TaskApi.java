package data;

import java.util.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public interface TaskApi<T>
{
	//public Task(BlockingQueue<T> qIn);
	
	public String randWeather();

	public String randLocation();

	public String randTemper();

	public void createTasks(int n);
}