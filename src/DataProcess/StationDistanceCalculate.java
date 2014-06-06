package DataProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.*;
import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;
// calculate the station distance to prior station and next station 
public class StationDistanceCalculate {

	public static final int COLUMN_NUMBER = 9;
	public static final int ROW_NUMBER = 884;

	public static double[] getNextStationDistance(int stopId,
			String[][] distanceMatrix) throws BiffException, IOException {

		double result[] = { 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < ROW_NUMBER; i++) {
			if (stopId == Integer.parseInt(distanceMatrix[i][3])) {
				result[0] += ChangeDistanceStyle(distanceMatrix[i][7]);
				result[1] += ChangeDistanceStyle(distanceMatrix[i][8]);
				result[2] = result[2] + 1;
			}
			if (stopId == Integer.parseInt(distanceMatrix[i][5])) {
				result[3] += ChangeDistanceStyle(distanceMatrix[i][7]);
				result[4] += ChangeDistanceStyle(distanceMatrix[i][8]);
				result[5] = result[5] + 1;
			}
		}
		result[0] = result[0] / result[2];
		result[1] = result[1] / result[2];
		result[3] = result[3] / result[5];
		result[4] = result[4] / result[5];

		return result;
	}

	static double ChangeDistanceStyle(String distance) {

		double distanceResult = Double.parseDouble(distance.split(" ")[0]);
		String unit = distance.split(" ")[1];
		if (unit.equals("ft")) {
			distanceResult = distanceResult / 5280;
		}
		if (unit.equals("km")) {
			distanceResult = distanceResult / 1.61;
		}
		return distanceResult;
	}

	public static void main(String[] args) throws Exception {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"stopid.csv"));
			String[][] stopDistance = WeatherPassengerLink.getDataFromCSV(
					ROW_NUMBER, COLUMN_NUMBER, "Station_Distance.csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"StationPathDistanceAndAbsoluteDistance.csv"));
			String line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				String[] stop = line.split(",");
				System.out.println("now processing stop id " + stop[0]);
				int stopId = Integer.parseInt(stop[0]);
				double[] distanceMatrix = getNextStationDistance(stopId,
						stopDistance);
				String resultLine = "" + stopId+","+distanceMatrix[0];
				for (int i = 1; i < distanceMatrix.length; i++)
					resultLine = resultLine + "," + distanceMatrix[i] + ",";

				writer.write(resultLine);
				line = reader.readLine();
				writer.newLine();
			}
			writer.close();
			reader.close();
		} catch (IOException e) {

		}
	}
}
