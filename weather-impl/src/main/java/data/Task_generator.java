package data;

import java.util.concurrent.BlockingQueue;

public class Task_generator implements Task_generatorApi<Location>
{
    	private Task task;
        private int locCount;
        private BlockingQueue<Location> qIn;
		private boolean isThreadActive;
		private int sleepTime;
		
		public Task_generator(Task task,BlockingQueue<Location> qIn, int locCount, int sleepTime)
		{
            this.task = task;
            this.locCount = locCount;
			this.qIn = qIn;
			this.isThreadActive=true;
			this.sleepTime = sleepTime;
        }
		
		public void Close()
		{
			isThreadActive = false;
		}
	
        @Override
        public void run()
		{
            while (isThreadActive&&(locCount>0))
			{
				--locCount;
				try
				{
					synchronized(qIn)
					{
						task.createTasks(1);
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				try{
					Thread.sleep(sleepTime);} 
				catch (InterruptedException e){
					e.printStackTrace();}
            }
		}
}