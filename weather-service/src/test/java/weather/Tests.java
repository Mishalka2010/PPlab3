package data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.ArrayDeque;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.*;

public class Tests
{
	@Test
	public void Test_Execution()
	{
		System.out.println("[Execution test]");
		
		BlockingQueue<Location> qIn = new LinkedBlockingQueue<Location>();
		ArrayDeque<Location> qOut = new ArrayDeque<Location>();
		
		Task task = new Task(qIn);
		Date date = new Date();
		Location loc;
		
		Task_executor taskExecutor = new Task_executor(task,qIn,qOut,0);
		Thread threadExecutor = new Thread(taskExecutor);
		
		qIn.add(new Location("",0,date,"Moscow",""));
		qIn.add(new Location("",1,date,"Belgorod",""));
		qIn.add(new Location("",2,date,"Rostov",""));
		qIn.add(new Location("",3,date,"Kazan",""));
		qIn.add(new Location("",4,date,"Washington",""));
		
		threadExecutor.start();
		
		while (!qIn.isEmpty())
		{
			try {
			Thread.sleep(100);}
		catch (InterruptedException e){
			e.printStackTrace();}
            
		}
		taskExecutor.Close();
		try {
			threadExecutor.join();}
		catch (InterruptedException e){
			e.printStackTrace();}
		
		loc = qOut.poll();
		assertTrue(loc!=null);
		assertTrue(loc.date==date&&loc.city=="Moscow"&&loc.weather!="");
		loc = qOut.poll();
		assertTrue(loc!=null);
		assertTrue(loc.date==date&&loc.city=="Belgorod"&&loc.weather!="");
		loc = qOut.poll();
		assertTrue(loc!=null);
		assertTrue(loc.date==date&&loc.city=="Rostov"&&loc.weather!="");
		loc = qOut.poll();
		assertTrue(loc!=null);
		assertTrue(loc.date==date&&loc.city=="Kazan"&&loc.weather!="");
		loc = qOut.poll();
		assertTrue(loc!=null);
		assertTrue(loc.date==date&&loc.city=="Washington"&&loc.weather!="");
		assertTrue(qOut.isEmpty()&&qIn.isEmpty());
	}
	
	@Test
	public void Test_Generation()
	{
		System.out.println("[Generator test]");
		
		BlockingQueue<Location> qIn = new LinkedBlockingQueue<Location>();
		Task task = new Task(qIn);
		Location loc;
		
		int elementNumber = 1000;
		Task_generator taskGenerator = new Task_generator(task, qIn, 1000, 0);
		Thread threadGenerator = new Thread(taskGenerator);
		threadGenerator.start();
		try {
			threadGenerator.join();}
		catch (InterruptedException e){
			e.printStackTrace();}
		
		assertTrue(elementNumber==qIn.size());
		
		int Moscow = 0, Saint_Petersburg = 0, Berlin = 0, Vilnus = 0, Saratov = 0, Belgorod = 0, Ekaterinburg = 0, Tambov = 0, New_York = 0, Rostov = 0, Washington = 0, Novosibirsk = 0, Nizhny_Novgorod = 0, Kazan = 0;
		System.out.println("Elements number: " + qIn.size());
		
		while (!qIn.isEmpty())
		{
			loc = qIn.poll();
			if (loc.city == "Moscow")
				Moscow++;
			if (loc.city == "Saint Petersburg")
				Saint_Petersburg++;
			if (loc.city == "Berlin")
				Berlin++;
			if (loc.city == "Vilnus")
				Vilnus++;
			if (loc.city == "Saratov")
				Saratov++;
			if (loc.city == "Belgorod")
				Belgorod++;
			if (loc.city == "Ekaterinburg")
				Ekaterinburg++;
			if (loc.city == "Tambov")
				Tambov++;
			if (loc.city == "New York")
				New_York++;
			if (loc.city == "Rostov")
				Rostov++;
			if (loc.city == "Washington")
				Washington++;
			if (loc.city == "Novosibirsk")
				Novosibirsk++;
			if (loc.city == "Nizhny Novgorod")
				Nizhny_Novgorod++;
			if (loc.city == "Kazan")
				Kazan++;
		}
		assertTrue((Moscow > 0)||(Saint_Petersburg > 0)||(Berlin > 0)||(Vilnus > 0)||(Saratov > 0)||(Belgorod > 0)||(Ekaterinburg > 0)||(Tambov > 0)||(New_York > 0)||(Rostov > 0)||(Washington > 0)||(Novosibirsk > 0)||(Nizhny_Novgorod > 0)||(Kazan > 0));
	}
}