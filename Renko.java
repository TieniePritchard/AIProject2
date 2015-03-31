public static class Renko
{
	/*	Predetermined Amount by which the price of the stock should go up or down
		before a brick is added to the Renko chart.
		This is a percentage of the range of stock values for a particular stock.
	*/
	float boxSize;
	
	int [] RenkoChart = new int [32*5];
	public Renko()
	{
	}
	
	/*For each datafile, determine the range of closing price values.*/
	public float determineRangeOfClosingValues(String file)
	{
		//Maximum Closing Price - Minimum Closing Price
		//Box price returned as 1% of the range
	}
	
	/*
		Generate the Renko data from the stock data using the closing price on each day
		NOTE: Renko bricks will be created in binary form (i.e. 00000) which indicates 
		how much it went up/down per day. The GA will generate a trader with chromosomes
		which map to these bricks.
		Renko Data will consist of appending 0s and 1s (Up and Down) next to each other
	*/
	public void generateRenkoData()
	{
		
	}
}