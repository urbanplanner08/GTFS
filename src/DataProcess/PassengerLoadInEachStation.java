package DataProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// calculate the passenger load in each station in each routes
public class PassengerLoadInEachStation {

	public static final int COLUMN_NUMBER = 11;
	public static final int ROW_NUMBER = 903;

	public static final int PassengerLoad_COLUMN_NUMBER = 26;
	public static final int PassengerLoad_ROW_NUMBER = 431;

	public static void main(String[] args) throws Exception {
		String[][] stopData = WeatherPassengerLink.getDataFromCSV(ROW_NUMBER,
				COLUMN_NUMBER, "final_Version_stop.csv");
		String[][] loadData = WeatherPassengerLink.getDataFromCSV(
				PassengerLoad_ROW_NUMBER, PassengerLoad_COLUMN_NUMBER,
				"Passenger_Load_Each_Station.csv");
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				"Station_Load_Result.csv"));

		for (int i = 1; i < ROW_NUMBER; i++) {
			String busIndex = stopData[i][0];
			String busRouteName = stopData[i][1];
			String busDirection = stopData[i][3];
			String StopSequence = stopData[i][4];
			String StopID = stopData[i][5];
			String StopName = stopData[i][6];
			String PassengerLoad = null;
			String line = null;
			int j = 1;
			while (j < PassengerLoad_ROW_NUMBER) {
				if (loadData[j][0].equals(StopID)) {
					PassengerLoad = loadData[j][25];
					break;
				}
				j++;
			}
			line = busIndex + "," + busRouteName + "," + busDirection + ","
					+ StopSequence + "," + StopID + "," + StopName + ","
					+ PassengerLoad + "\n";
			writer.write(line);

		}
		writer.close();

	}
}
