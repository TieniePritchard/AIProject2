import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Renko
{
	/*	
		Predetermined Amount by which the price of the stock should go up or down
		before a brick is added to the Renko chart.
		This is a percentage of the range of stock values for a particular stock.
	*/
	double boxSize;
	int [] RenkoChart = new int [32*5];
	public Renko(String file)
	{
		boxSize = determineRangeOfClosingValues(file);
		generateRenkoData(file);
	}
	
	/*For each datafile, determine the range of closing price values.*/
	public double determineRangeOfClosingValues(String f)
	{
		//Maximum Closing Price - Minimum Closing Price
		ArrayList<String> file = openFileForReading(f);
		//printFileContent(file);
		
		double maxClosingPrice;
		double minClosingPrice;
		String [] line = (file.get(0)).split("\t");
		maxClosingPrice = Double.parseDouble(line[1]);
		minClosingPrice = Double.parseDouble(line[1]);
		for(int i = 0; i < file.size()-1; i++)
		{
			line = (file.get(i)).split("\t");
			double closing = Double.parseDouble(line[1]);
			if(closing < minClosingPrice)
				minClosingPrice = closing;
			else if(closing > maxClosingPrice)
				maxClosingPrice = closing;
			
		}
		//Box price returned as 1% of the range
		return (maxClosingPrice - minClosingPrice) * 0.01;
	}
	
	/*
		Generate the Renko data from the stock data using the closing price on each day
		NOTE: Renko bricks will be created in binary form (i.e. 00000) which indicates 
		how much it went up/down per day. The GA will generate a trader with chromosomes
		which map to these bricks.
		Renko Data will consist of appending 0s and 1s (Up and Down) next to each other
	*/
	public int [] generateRenkoData(String f)
	{
		ArrayList<Integer> Renko = new ArrayList<Integer>();
		//Maximum Closing Price - Minimum Closing Price
		ArrayList<String> file = openFileForReading(f);
		//printFileContent(file);
		double currentDay = 0;
		double oldDay;
		for(int i = 0; i < file.size()-1; i++)
		{
			
			String [] line = (file.get(i)).split("\t");
			double closing = Double.parseDouble(line[1]);
			oldDay = currentDay;
			currentDay = closing;
			double difference = currentDay - oldDay;
			if(i == 0)
				continue;
			if(Math.abs(difference) >= boxSize)
			{
				if(difference >= 0)
					Renko.add(1);
				else Renko.add(0);
			}
			
		}
		displayRenkoData(Renko);
		return null;
	}
	
	public void displayRenkoData(ArrayList<Integer> renk)
	{
		for(int i = 0; i < renk.size(); i++)
		{
			if(i % 5 == 0)
				System.out.print(" ");
			System.out.print(renk.get(i));
		}
		System.out.println();
	}
	public void printFileContent(ArrayList<String> file)
	{
		for(int i = 0; i < file.size(); i++)
		{
			System.out.println(file.get(i));
		}
	}
	public ArrayList<String> openFileForReading(String f)
	{
		BufferedReader file = null;
		String sCurrentLine;
		ArrayList<String> fileContent = new ArrayList<String>();
		try 
		{

			file = new BufferedReader(new FileReader(f));
			while ((sCurrentLine = file.readLine()) != null) 
			{
				fileContent.add(sCurrentLine);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (file != null)file.close();
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		return fileContent;
 
	}
}