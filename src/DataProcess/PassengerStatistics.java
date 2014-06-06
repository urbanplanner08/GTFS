package DataProcess;

import java.util.ArrayList;
import java.util.List;
// calculate the passenger data, mean, standard deviation, on-off
public class PassengerStatistics {

	List<Integer> onNumber = new ArrayList<Integer>();
	List<Integer> offNumber = new ArrayList<Integer>();
	List<Integer> sumNumber = new ArrayList<Integer>();
	int passLineNumber = 0;
	double onMean, onVariance, offMean, offVariance, sumMean, sumVariance;
	
	public static int STATISTICS_NUMBER = 7;
	public static int ON_MEAN = 0;
	public static int ON_VARIANCE = 1;
	public static int OFF_MEAN = 2;
	public static int OFF_VARIANCE = 3;
	public static int SUM_MEAN = 4;
	public static int SUM_VARIANCE = 5;
	public static int PASS_LINE_NUMBER = 6;

	public void addStatistics(int on, int off, int sum) 
	{
		onNumber.add(on);
		offNumber.add(off);
		sumNumber.add(sum);
		passLineNumber++;
	}
	
	private void calculateAllStatistics()
	{
		onMean = StatisticsTools.getMean(onNumber);
		offMean = StatisticsTools.getMean(offNumber);
		sumMean = StatisticsTools.getMean(sumNumber);
		onVariance = StatisticsTools.getVariance(onNumber);
		offVariance = StatisticsTools.getVariance(offNumber);
		sumVariance = StatisticsTools.getVariance(sumNumber);
	}
	
	public double[] getAllStatistics()
	{
		calculateAllStatistics();
		double[] allStatistics = new double[STATISTICS_NUMBER];
		allStatistics[ON_MEAN] = onMean;
		allStatistics[ON_VARIANCE]= onVariance;
		allStatistics[OFF_MEAN]= offMean;
		allStatistics[OFF_VARIANCE] = offVariance;
		allStatistics[SUM_MEAN]= sumMean;
		allStatistics[SUM_VARIANCE]= sumVariance;
		allStatistics[PASS_LINE_NUMBER] = passLineNumber;
		return allStatistics;
	}	
}
