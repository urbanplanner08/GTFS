package DataProcess;

import java.util.List;
// statistics tools for calculation of weather model

public class StatisticsTools {
	
	public static double getMean(List<Integer> numbers)
	{
		double mean=0;
		for (int i=0;i<numbers.size();i++)
		{
			mean+=numbers.get(i);	
		}
        mean=mean/numbers.size();
        return mean;
	}
	
	public static double getDeviation(List<Integer> numbers)
	{
		double mean=getMean(numbers);
        double deviation = 0;
        for (int i=0;i<numbers.size();i++)
        	deviation+=(mean-numbers.get(i))*(mean-numbers.get(i));
        return deviation;
	}
	
	public static double getVariance(List<Integer> numbers)
	{
        return Math.sqrt(getDeviation(numbers));
	}

}
