import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Brick
{
	int up;
	Date date;
	double closingPrice;
	double HighPrice;
	double LowPrice;
	public Brick(boolean upDown, Date d, double CP, double HP, double LP)
	{
		if(upDown == true)
			up = 1;
		else up = 0;
		date = d;
		closingPrice = CP;
		HighPrice = HP;
		LowPrice = LP;
	}
}