public class Trader
{
	char [] trader = new char[32];
	float money = 100000;
	int shares;
	float currentPrice;
	//Char values can either be B/S/H for Buy/Sell/Hold respectively
	//The trader will need to be generated from the Renko Chart Data (i.e. every 5 bricks in Renko will be either buy sell or hold in trader)
	public Trader(char [] renkoChart)
	{
		//Generate Trader From Renko Chart
	}
	
	public float trade(int period)
	{
		/*Trade over period
		Trading starts on the first day AFTER the Renko Chart has 5 bricks.
		Using the last 5 bricks of the Renko chart (including any effect that the previous day's
		closing price had on the chart but not the current day's) each trader has a corresponding decision
		of whether to buy stock, sell stock or hold
		*/
		return 0;
	}
	
	/*
		Every time a trader buys shares, the trader's numbers of shares increases but its cash on hand decreases
		Every time it sells the opposite happens
		A trader can only buy what it can afford with the cash it has on hand
		The price at which the shares must be bought or sold should be the average of the high and low
		prices on the current trading day
		
		Every buy or sell transaction incurs fees.
		
		Each subsequent trading day the trader could make a new decision based on the last 5 bricks.
		In this way the trader buys and sells stocks over time
		
	*/
	/*Every time a trader makes a buy decision, it must buy the maximum number of shares possible
	with the money on hand.
	
	*/

	public float buyStock()
	{
		return 0;
	}
	/*Every time the trader makes a sell decision all shares on hand must be sold*/
	public float sellStock()
	{
		return 0;
	}
	public float holdStock()
	{
		return 0;
	}
	
	public int increaseShares(int s)
	{
		return ++s;
	}
	public int decreaseShares(int s)
	{
		return --s;
	}
	
	/*At the end of the period the fitness of a trader can be determined using the return on investment*/
	public int fitness()
	{
		/*Calculated as: cash + (num_stocks x current price)
		The closing price on the final trading day should be used in this calculation (i.e. current price)
		*/
		return 0;
	}
	
	public double fees(float tradeAmount, boolean buy)
	{
		double STT = 0;
		if(buy)
			STT = tradeAmount * .25;
		double Brokerage = tradeAmount * 0.5;
		double STRATE = 11.58;
		double IPL = tradeAmount * .0002;
		double VAT = 0.14 * (STT + Brokerage + STRATE + IPL);
		return 0;
	}
}