import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
public class Trader
{
	Renko generator;
	ArrayList<Brick> RenkoData;
	char [] trader = new char[32];
	TraderRepresentation [] finalTrade;
	double money = 100000;
	int shares = 0;
	//Char values can either be B/S/H for Buy/Sell/Hold respectively
	//The trader will need to be generated from the Renko Chart Data (i.e. every 5 bricks in Renko will be either buy sell or hold in trader)
	public Trader(String file)
	{
		//Trader will trade with the Renko Data.
		generator = new Renko(file);
		RenkoData = generator.getRenkoData();
		generateRandomTrader();
		System.out.println("Money Made: " + trade(trader));
		System.out.println("Fitness: " + fitness());
	}
	
	public double trade(char [] trade)
	{
		
		/*Trade over period
		Trading starts on the first day AFTER the Renko Chart has 5 bricks.
		Using the last 5 bricks of the Renko chart (including any effect that the previous day's
		closing price had on the chart but not the current day's) each trader has a corresponding decision
		of whether to buy stock, sell stock or hold
		*/
		generateTraderRepresentation(trade);
		String renkoString = "";
		int count = 0;
		for(int i = 0; i <  RenkoData.size(); i++)
		{
			if(i % 5 == 0 && i != 0)
			{
				count = 0;
				for(int j = 0; j < finalTrade.length; j++)
				{
					if((finalTrade[j].index).equals(renkoString))
					{
						char d = finalTrade[j].Decision;
						if(d == 'B')
						{
							
							double high = (RenkoData.get(i)).HighPrice;
							double low = (RenkoData.get(i)).LowPrice;
							double avg = (high + low) / 2;
							buyStock(avg);
						}
						else if(d == 'S')
						{
							double high = (RenkoData.get(i)).HighPrice;
							double low = (RenkoData.get(i)).LowPrice;
							double avg = (high + low) / 2;
							sellStock(avg);
						}
						else if(d == 'H')
						{
							holdStock();
						}
					}
				}
				renkoString = "";
			}
			char dec;
			if((RenkoData.get(i)).up == 1)
				dec = '1';
			else dec = '0';
			renkoString += dec;
			count++;
		}
		return money;
	}
	
	public void generateTraderRepresentation(char [] chromosome)
	{
		finalTrade = new TraderRepresentation[32];
		int binIndex = 0b00000;
		for(int i = 0; i < chromosome.length; i++)
		{
			finalTrade[i] = new TraderRepresentation(chromosome[i], binIndex);
			binIndex++;
		}
		PrintTraderRep(finalTrade);
	}
	
	public void PrintTraderRep(TraderRepresentation [] trader)
	{
		for(int i = 0; i < trader.length; i++)
		{
			System.out.println(trader[i].Decision + "\t" + trader[i].index);
		}
	}
	
	public char [] generateRandomTrader()
	{
		int binaryCur = 0b00000;
		int count = 0;
		while(binaryCur <= 0b11111)
		{
			Random rand = new Random();
			int randomNum = rand.nextInt((3 - 1) + 1) + 1;
			char selected;
			if(randomNum == 1)
				selected = 'B';
			else if(randomNum == 2)
				selected = 'S';
			else selected = 'H';
			trader[count] = selected;
			count++;
			binaryCur++;
		}
		printTrader(trader);
		generateTraderRepresentation(trader);
		return trader;
	}
	
	public void printTrader(char [] trader)
	{
		for(int i = 0; i < trader.length; i++)
		{
			System.out.print(trader[i]);
		
		}
		System.out.println();
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

	public void buyStock(double cost)
	{
		double currentMoney = money;
		shares++;
		if(currentMoney >= cost)
			currentMoney-=cost;
		double fee = fees(money, false);
		currentMoney -= fee;
		if(currentMoney >= 0)
		{
			money = currentMoney;
		}
	}
	/*Every time the trader makes a sell decision all shares on hand must be sold*/
	public void sellStock(double sellingPrice)
	{
		double currentMoney = money;
		shares = 0;
		currentMoney += sellingPrice;
		double fee = fees(money, false);
		currentMoney -= fee;
		if(currentMoney >= 0)
		{
			money = currentMoney;
		}
	}
	public void holdStock()
	{
		//Do Nothing
	}

	
	/*At the end of the period the fitness of a trader can be determined using the return on investment*/
	public double fitness()
	{
		double finalPrice = RenkoData.get(RenkoData.size()-1).closingPrice;
		/*Calculated as: cash + (num_stocks x current price)
		The closing price on the final trading day should be used in this calculation (i.e. current price)
		*/
		return money + (shares * finalPrice);
	}
	
	public double fees(double tradeAmount, boolean buy)
	{
		tradeAmount = tradeAmount * shares;
		double STT = 0;
		if(buy)
			STT = tradeAmount * .0025;
		double Brokerage = tradeAmount * 0.005;
		if(Brokerage < 70)
		{
			Brokerage = 70;
		}
		double STRATE = 11.58;
		double IPL = tradeAmount * .000002;
		double VAT = 0.14 * (STT + Brokerage + STRATE + IPL);
		double finalFees = STT + Brokerage + STRATE + IPL + VAT;
		return finalFees;
	}
	
	class TraderRepresentation
	{
		char Decision;
		String index;
		public TraderRepresentation(char d, int i)
		{
			Decision = d;
			index = String.format("%5s", Integer.toBinaryString(i)).replace(' ', '0');
		}
	}
}