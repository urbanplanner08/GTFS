package DataProcess;

import java.io.*;
import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;
// create the 168 semantic attributes csv file and be ready for data mining 
public class StatisticsDriver {

	public static final int WEEKDAY = 0;
	public static final int WEEKEND = 1;
	public static final int MORNING = 1;
	public static final int NOON = 2;
	public static final int AFTERNOON = 3;
	public static final int EVENING = 4;
	public static final int BAD = 1;
	public static final int FAIR = 2;
	public static final int GOOD = 3;
	public static final int[] DAY = { WEEKDAY, WEEKEND };
	public static final String[] DAY_STRING = { "weekday", "weekend" };
	public static final int[] TIME = { MORNING, NOON, AFTERNOON, EVENING };
	public static final int[] CLIMATE = { BAD, FAIR, GOOD };
	public static final int[] BUS_LINE_ID = { 9, 10, 11, 12, 63, 90 };
	public static final String PATH_PREFIX = "data\\";
	public static final int TIME_INDEX = 1;
	public static final int DATE_INDEX = 11;
	public static final int STOP_ID_INDEX = 3;
	public static final int ON_INDEX = 5;
	public static final int OFF_INDEX = 6;
	public static final int SUM_INDEX = 7;
	public static final int COLUMN_NUMBER = 11;
	public static final int ROW_NUMBER = 82919;

	public static PassengerStatistics getPassengerStatistics(int stopId,
			int day, int time, int climate) throws BiffException, IOException {
		PassengerStatistics statistics = new PassengerStatistics();
		String[][] passengerData = WeatherPassengerLink.getDataFromCSV(
				ROW_NUMBER, COLUMN_NUMBER, "Passenger_Weather_Combined.csv");

		for (int i = 0; i < ROW_NUMBER; i++) {
			if (stopId == Integer.parseInt(passengerData[i][2])
					&& day == Integer.parseInt(passengerData[i][8])
					&& time == Integer.parseInt(passengerData[i][11])
					&& climate == Integer.parseInt(passengerData[i][13])) 
			{
				int on = Integer.parseInt(passengerData[i][4]);
				int off = Integer.parseInt(passengerData[i][5]);
				int sum = Integer.parseInt(passengerData[i][6]);
				statistics.addStatistics(on, off, sum);
			}
		}
		return statistics;
	}

	public static void main(String[] args) throws Exception {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"stopid.csv"));
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"statistics_result3.csv"));
			String line = reader.readLine();
			line=reader.readLine();
			while (line != null) {
				String[] stop = line.split(",");
				System.out.println("now processing stop id " + stop[0]);
				int stopId = Integer.parseInt(stop[0]);
				for (int dayIndex = 0; dayIndex < DAY.length; dayIndex++)
					for (int timeIndex = 0; timeIndex < TIME.length; timeIndex++)
						for (int weatherIndex = 0; weatherIndex < CLIMATE.length; weatherIndex++) 
						{
							System.out.println(stopId + "," + DAY[dayIndex]
									+ "," + TIME[timeIndex] + ","
									+ CLIMATE[weatherIndex]);
							PassengerStatistics statistics = getPassengerStatistics(
									stopId, DAY[dayIndex], TIME[timeIndex],
									CLIMATE[weatherIndex]);
							double[] allStatistics = statistics
									.getAllStatistics();
							String resultLine = "" + allStatistics[0];
							for (int i = 1; i < allStatistics.length; i++)
								resultLine = resultLine + ","
										+ allStatistics[i]+",";
							writer.write(resultLine);
						}
				line = reader.readLine();
				writer.newLine();

			}
			writer.close();
			reader.close();
		} catch (IOException e) {

		}
	}
}