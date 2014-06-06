package DataProcess;

import java.io.*;
import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;
// weather the stations have pair station 
public class DecidePair {

	public static void main(String[] args) throws Exception {

		try {
			String[][] stop = WeatherPassengerLink.getDataFromCSV(903, 11,
					"final_Version_stop.csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"PairStation.csv"));
			String resultLine = null;
			for (int i = 1; i < 78; i++)
				for (int j = 78; j < 157; j++) {
					if (stop[i][6].equals(stop[j][6]))
						resultLine += "Route9," + stop[i][5] + "," + stop[i][6]
								+ "\n" + "Route9," + stop[j][5] + ","
								+ stop[j][6] + "\n";
				}

			for (int i = 157; i < 216; i++)
				for (int j = 216; j < 282; j++) {
					if (stop[i][6].equals(stop[j][6]))
						resultLine += "Route10_Broadway," + stop[i][5] + ","
								+ stop[i][6] + "\n" + "Route10_Broadway,"
								+ stop[j][5] + "," + stop[j][6] + "\n";
				}

			for (int i = 282; i < 333; i++)
				for (int j = 333; j < 392; j++) {
					if (stop[i][6].equals(stop[j][6]))
						resultLine += "Route10_Greyhound," + stop[i][5] + ","
								+ stop[i][6] + "\n" + "Route10_Greyhound,"
								+ stop[j][5] + "," + stop[j][6] + "\n";
				}

			for (int i = 392; i < 416; i++)
				for (int j = 416; j < 446; j++) {
					if (stop[i][6].equals(stop[j][6]))
						resultLine += "Route11_Washington," + stop[i][5] + ","
								+ stop[i][6] + "\n" + "Route11_Washington,"
								+ stop[j][5] + "," + stop[j][6] + "\n";
				}
			for (int i = 446; i < 478; i++)
				for (int j = 478; j < 512; j++) {
					if (stop[i][6].equals(stop[j][6]))
						resultLine += "Route11_Western," + stop[i][5] + ","
								+ stop[i][6] + "\n" + "Route11_Western,"
								+ stop[j][5] + "," + stop[j][6] + "\n";
				}

			for (int i = 512; i < 554; i++)
				for (int j = 554; j < 601; j++) {
					if (stop[i][6].equals(stop[j][6]))
						resultLine += "Route12_Washington," + stop[i][5] + ","
								+ stop[i][6] + "\n" + "Route12_Washington,"
								+ stop[j][5] + "," + stop[j][6] + "\n";
				}
			for (int i = 601; i < 644; i++)
				for (int j = 644; j < 687; j++) {
					if (stop[i][6].equals(stop[j][6]))
						resultLine += "Route12_Western," + stop[i][5] + ","
								+ stop[i][6] + "\n" + "Route12_Western,"
								+ stop[j][5] + "," + stop[j][6] + "\n";
				}

			for (int i = 687; i < 742; i++)
				for (int j = 742; j < 796; j++) {
					if (stop[i][6].equals(stop[j][6]))
						resultLine += "Route63," + stop[i][5] + ","
								+ stop[i][6] + "\n" + "Route63," + stop[j][5]
								+ "," + stop[j][6] + "\n";
				}
			for (int i = 796; i < 849; i++)
				for (int j = 849; j < 903; j++) {
					if (stop[i][6].equals(stop[j][6]))
						resultLine += "Route90," + stop[i][5] + ","
								+ stop[i][6] + "\n" + "Route90," + stop[j][5]
								+ "," + stop[j][6] + "\n";
				}
			System.out.println(resultLine);
			writer.write(resultLine);
			writer.newLine();
			writer.close();
		} catch (IOException e) {

		}
	}
}
