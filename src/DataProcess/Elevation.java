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
// combine the stopid and elevation data. 
public class Elevation {

	private static ArrayList<ArrayList<String>> GetDataByRouteDirection(
			String[][] stringArray, String Route, String Direction) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < stringArray.length; i++) {
			ArrayList<String> listSub = new ArrayList<String>();
			if (stringArray[i][0].equals(Route)
					&& stringArray[i][1].equals(Direction)) {
				for (int j = 0; j < stringArray[i].length; j++)
					listSub.add(stringArray[i][j]);
				list.add(listSub);
			}
		}
		return list;

	}

	private static String getStopElevation(
			ArrayList<ArrayList<String>> routeDistance,
			ArrayList<ArrayList<String>> routeElevation) {
		double[][] StopIdPercentage = getRouteMatrix(routeDistance);
		int[][] StopIdPosition = new int[StopIdPercentage.length][2];
		for (int i = 0; i < StopIdPercentage.length; i++) {
			StopIdPosition[i][0] = (int) StopIdPercentage[i][0];
			StopIdPosition[i][1] = (int) Math
					.floor(256*StopIdPercentage[i][1]);

		}
		String[][] resultArray = new String[routeElevation.size()][2];
		for (int i = 0; i < routeElevation.size(); i++) {
			resultArray[i][0] = "";
			resultArray[i][1] = routeElevation.get(i).get(2);

		}

		for (int i = 0; i < StopIdPosition.length; i++) {
			System.out.println("" + i + "," + StopIdPosition[i][0] + ","
					+ StopIdPosition[i][1]);
		}
		for (int i = 0; i < resultArray.length; i++) {
			System.out.println("" + i + "," + resultArray[i][1]);
		}

		for (int i = 0; i < routeElevation.size(); i++) {
			System.out.println("" + i + "," + routeElevation.get(i).get(2));
		}

		int index = 0;
		for (int i = 0; i < StopIdPosition.length; i++) {
			index = StopIdPosition[i][1];

			if (index == routeElevation.size())
				index = index - 1;
			resultArray[index][0] = "" + StopIdPosition[i][0];
		}

		String resultLine = "";
		for (int i = 0; i < resultArray.length; i++) {
			resultLine += resultArray[i][0] + "," + resultArray[i][1] + "\n";
		}
		return resultLine;
	}

	private static double[][] getRouteMatrix(
			ArrayList<ArrayList<String>> routeDistance) {
		double[][] distance = new double[routeDistance.size()][3];
		for (int i = 0; i < routeDistance.size(); i++) {
			distance[i][0] = Double.parseDouble(routeDistance.get(i).get(3)); // departure
																				// station
			distance[i][1] = Double.parseDouble(routeDistance.get(i).get(5)); // arrival
																				// station
			distance[i][2] = StationDistanceCalculate
					.ChangeDistanceStyle(routeDistance.get(i).get(7)); // distance
																		// float
		}
		int partNumber = routeDistance.size() / 9;
		if (routeDistance.size() % 9 != 0) {
			double[][] answer = new double[partNumber*10
					+ routeDistance.size() % 9 + 1][2];

			int answerIndex = 0;
			double preNumber = 0;

			for (int i = 0; i <= partNumber; i++) {
				int beginIndex = i*9;
				int endIndex;
				if (i < partNumber)
					endIndex = (i + 1)*9 - 1;
				else
					endIndex = routeDistance.size() - 1;
				double sum = 0;
				for (int j = beginIndex; j <= endIndex; j++)
					sum = sum + distance[j][2];
				double curSum = 0;
				for (int j = beginIndex; j <= endIndex; j++) {
					answer[answerIndex][0] = distance[j][0];
					answer[answerIndex][1] = preNumber + curSum / sum;
					curSum = curSum + distance[j][2];
					answerIndex++;
				}
				answer[answerIndex][0] = distance[endIndex][1];
				answer[answerIndex][1] = preNumber + 1;
				answerIndex++;
				preNumber = preNumber + 1;
			}
			return answer;
		} else {
			double[][] answer = new double[partNumber*10][2];

			int answerIndex = 0;
			double preNumber = 0;

			for (int i = 0; i < partNumber; i++) {
				int beginIndex = i*9;
				int endIndex=8+i*9;
				double sum = 0;
				for (int j = beginIndex; j <= endIndex; j++)
					sum = sum + distance[j][2];
				double curSum = 0;
				for (int j = beginIndex; j <= endIndex; j++) {
					answer[answerIndex][0] = distance[j][0];
					answer[answerIndex][1] = preNumber + curSum / sum;
					curSum = curSum + distance[j][2];
					answerIndex++;
				}
			}
			return answer;
		}
	}

	public static void main(String[] args) throws Exception {
		String[] BUS_LINE = { "9", "10", "11", "12", "63", "90" };
		String[] Directions = { "Clockwise", "Counterclockwise",
				"Broadway_East", "Broadway_West", "Greyhound_East",
				"Greyhound_West", "Washington_East", "Washington_West",
				"Western_East", "Western_West", "East", "West" };
		try {
			String[][] StationDistance = WeatherPassengerLink.getDataFromCSV(
					884, 9, "Station_Distance.csv");
			String[][] Elevation = WeatherPassengerLink.getDataFromCSV(27136,
					3, "Elevation.csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"Station_Elevation.csv"));
			String resultLine = "";
			 
			  ArrayList<ArrayList<String>> routeDistance =
			  GetDataByRouteDirection( StationDistance, "9", "Clockwise");
			  ArrayList<ArrayList<String>> routeElevation =
			  GetDataByRouteDirection( Elevation, "9", "Clockwise"); 
			  resultLine
			  += "9,Clockwise" + "\n" + getStopElevation(routeDistance,
			  routeElevation) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance2 =
			  GetDataByRouteDirection( StationDistance, "9",
			  "Counterclockwise"); ArrayList<ArrayList<String>> routeElevation2
			  = GetDataByRouteDirection( Elevation, "9", "Counterclockwise");
			  resultLine += "9,Counterclockwise" + "\n" +
			  getStopElevation(routeDistance2, routeElevation2) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance3 =
			  GetDataByRouteDirection( StationDistance, "10", "Broadway_East");
			  ArrayList<ArrayList<String>> routeElevation3 =
			  GetDataByRouteDirection( Elevation, "10", "Broadway_East");
			  resultLine += "10, Broadway_East"+ "\n" +
			  getStopElevation(routeDistance3, routeElevation3) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance4 =
			  GetDataByRouteDirection( StationDistance, "10", "Broadway_West");
			  ArrayList<ArrayList<String>> routeElevation4 =
			  GetDataByRouteDirection( Elevation, "10", "Broadway_West");
			  resultLine += "10, Broadway_West" + "\n" +
			  getStopElevation(routeDistance4, routeElevation4) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance5 =
			  GetDataByRouteDirection( StationDistance, "10",
			  "Greyhound_East"); ArrayList<ArrayList<String>> routeElevation5 =
			  GetDataByRouteDirection( Elevation, "10", "Greyhound_East");
			  resultLine += "10, Greyhound_East" + "\n" +
			  getStopElevation(routeDistance5, routeElevation5) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance6 =
			  GetDataByRouteDirection( StationDistance, "10",
			  "Greyhound_West"); ArrayList<ArrayList<String>> routeElevation6 =
			  GetDataByRouteDirection( Elevation, "10", "Greyhound_West");
			  resultLine += "10, Greyhound_West"+ "\n" +
			  getStopElevation(routeDistance6, routeElevation6) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance7 =
			  GetDataByRouteDirection( StationDistance, "11",
			  "Washington_East"); ArrayList<ArrayList<String>> routeElevation7
			  = GetDataByRouteDirection( Elevation, "11", "Washington_East");
			  resultLine += "11, Washington_East" + "\n" +
			  getStopElevation(routeDistance7, routeElevation7) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance8 =
			  GetDataByRouteDirection( StationDistance, "11",
			  "Washington_West"); ArrayList<ArrayList<String>> routeElevation8
			  = GetDataByRouteDirection( Elevation, "11", "Washington_West");
			  resultLine += "11, Washington_West" + "\n" +
			  getStopElevation(routeDistance8, routeElevation8) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance9 =
			  GetDataByRouteDirection( StationDistance, "11", "Western_East");
			  ArrayList<ArrayList<String>> routeElevation9 =
			  GetDataByRouteDirection( Elevation, "11", "Western_East");
			  resultLine +="11, Western_East" + "\n" +
			  getStopElevation(routeDistance9, routeElevation9) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance10 =
			  GetDataByRouteDirection( StationDistance, "11", "Western_West");
			  ArrayList<ArrayList<String>> routeElevation10 =
			  GetDataByRouteDirection( Elevation, "11", "Western_West");
			  resultLine +="11, Western_West" + "\n" +
			  getStopElevation(routeDistance10, routeElevation10) + "\n";
			  
			  
			  ArrayList<ArrayList<String>> routeDistance11 =
			  GetDataByRouteDirection( StationDistance, "12", "Broadway_East");
			  ArrayList<ArrayList<String>> routeElevation11 =
			  GetDataByRouteDirection( Elevation, "12", "Broadway_East");
			  resultLine += "12, Broadway_East"+ "\n" +
			  getStopElevation(routeDistance11, routeElevation11) + "\n";
			  
			  
			  
			  ArrayList<ArrayList<String>> routeDistance12 =
			  GetDataByRouteDirection( StationDistance, "12", "Broadway_West");
			  ArrayList<ArrayList<String>> routeElevation12 =
			  GetDataByRouteDirection( Elevation, "12", "Broadway_West");
			  resultLine += "12, Broadway_West" + "\n" +
			  getStopElevation(routeDistance12, routeElevation12) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance13 =
			  GetDataByRouteDirection( StationDistance, "12",
			  "Greyhound_East"); ArrayList<ArrayList<String>> routeElevation13
			  = GetDataByRouteDirection( Elevation, "12", "Greyhound_East");
			  resultLine += "12, Greyhound_East" + "\n" +
			  getStopElevation(routeDistance13, routeElevation13) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance14 =
			  GetDataByRouteDirection( StationDistance, "12",
			  "Greyhound_West"); ArrayList<ArrayList<String>> routeElevation14
			  = GetDataByRouteDirection( Elevation, "12", "Greyhound_West");
			  resultLine +="12, Greyhound_West" + "\n" +
			  getStopElevation(routeDistance14, routeElevation14) + "\n";
			 

			ArrayList<ArrayList<String>> routeDistance15 = GetDataByRouteDirection(
					StationDistance, "63", "East");
			ArrayList<ArrayList<String>> routeElevation15 = GetDataByRouteDirection(
					Elevation, "63", "East");
			resultLine += "63, East" + "\n"
					+ getStopElevation(routeDistance15, routeElevation15)
					+ "\n";
			System.out.println("this line is done");
		 
			  ArrayList<ArrayList<String>> routeDistance16 =
			  GetDataByRouteDirection( StationDistance, "63", "West");
			  ArrayList<ArrayList<String>> routeElevation16 =
			  GetDataByRouteDirection( Elevation, "63", "West"); resultLine +=
			  "63, West" + "\n" + getStopElevation(routeDistance16,
			  routeElevation16) + "\n";
			  
			  
			  
			  ArrayList<ArrayList<String>> routeDistance17 =
			  GetDataByRouteDirection( StationDistance, "90", "East");
			  ArrayList<ArrayList<String>> routeElevation17 =
			  GetDataByRouteDirection( Elevation, "90", "East"); resultLine +=
			  "90, East" + "\n" + getStopElevation(routeDistance17,
			  routeElevation17) + "\n";
			  
			  ArrayList<ArrayList<String>> routeDistance18 =
			  GetDataByRouteDirection( StationDistance, "90", "West");
			  ArrayList<ArrayList<String>> routeElevation18 =
			  GetDataByRouteDirection( Elevation, "90", "West"); resultLine +=
			  "90, West" + "\n" + getStopElevation(routeDistance18,
			  routeElevation18) + "\n";
			  
			  System.out.println("this line is done");
		 

			writer.write(resultLine);
			writer.close();
		} catch (IOException e) {

		}
	}

}
