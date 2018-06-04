package data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.ArrayDeque;

public class Main
{
    private volatile static boolean isThreadActive = true;
	
	public static BlockingQueue<Location> qIn = new LinkedBlockingQueue<Location>();
	public static ArrayDeque<Location> qOut = new ArrayDeque<Location>();
	
	public static Task task = new Task(qIn);
	
	public static int sleepTime = 300;
	public static int threadNumber = 6;
	
    public static void main(String[] args) throws Exception
	{
		Task_generator taskGenerator = new Task_generator(task, qIn, 100, sleepTime/threadNumber);
		
        Task_executor taskExecutor = new Task_executor(task, qIn, qOut, sleepTime);
        
		Logger logger = new Logger(qOut,sleepTime);

        Thread threadGenerator = new Thread(taskGenerator);
		
		Thread[] threadExecutorArr = new Thread[threadNumber];
		for (int i=0; i<threadNumber;++i)
			threadExecutorArr[i] = new Thread(taskExecutor);
		
		Thread threadLogger = new Thread(logger);	
	
        threadGenerator.start();
        for (int i=0; i<threadNumber;++i)
			threadExecutorArr[i].start();
        threadLogger.start();
		
		try {
			threadGenerator.join();}
		catch (InterruptedException e){
			e.printStackTrace();}
		
        while (!qIn.isEmpty()||!qOut.isEmpty())
		{
            Thread.sleep(100);
		}
		taskExecutor.Close();
		for (int i=0; i<threadNumber;++i)
			try {
				threadExecutorArr[i].join();}
			catch (InterruptedException e){
				e.printStackTrace();}
		
		logger.Close();
		try {
			threadLogger.join();}
		catch (InterruptedException e){
			e.printStackTrace();}
    }
}
