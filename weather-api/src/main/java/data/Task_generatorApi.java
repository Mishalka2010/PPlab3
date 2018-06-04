package data;

import java.util.concurrent.BlockingQueue;

public interface Task_generatorApi<T> extends Runnable
{
		//public Task_generator(Task task,BlockingQueue<T> qIn, int locCount, int sleepTime);
		
		public void Close();
	
        @Override
        public void run();
}