package DataProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
// determine the stations' elevation : peak bottom flat ~~~~
public class ElevationProcess {

	public static void main(String[] args) throws Exception {

		try {
			String[][] Route9clock = WeatherPassengerLink.getDataFromCSV(2304,
					2, "Route9clock.csv");
			String[][] Route9counterclock = WeatherPassengerLink
					.getDataFromCSV(2304, 2, "Route9counterclock.csv");
			String[][] Route10be = WeatherPassengerLink.getDataFromCSV(1792, 2,
					"Route10be.csv");
			String[][] Route10bw = WeatherPassengerLink.getDataFromCSV(2048, 2,
					"Route10bw.csv");
			String[][] Route10ge = WeatherPassengerLink.getDataFromCSV(1536, 2,
					"Route10ge.csv");
			String[][] Route10gw = WeatherPassengerLink.getDataFromCSV(1792, 2,
					"Route10gw.csv");
			String[][] Route11we = WeatherPassengerLink.getDataFromCSV(768, 2,
					"Route11we.csv");
			String[][] Route11ww = WeatherPassengerLink.getDataFromCSV(1024, 2,
					"Route11ww.csv");
			String[][] Route11westerneast = WeatherPassengerLink
					.getDataFromCSV(1024, 2, "Route11westerneast.csv");
			String[][] Route11westernwest = WeatherPassengerLink
					.getDataFromCSV(1024, 2, "Route11westernwest.csv");
			String[][] Route12be = WeatherPassengerLink.getDataFromCSV(1280, 2,
					"Route12be.csv");
			String[][] Route12bw = WeatherPassengerLink.getDataFromCSV(1536, 2,
					"Route12bw.csv");
			String[][] Route12ge = WeatherPassengerLink.getDataFromCSV(1280, 2,
					"Route12ge.csv");
			String[][] Route12gw = WeatherPassengerLink.getDataFromCSV(1280, 2,
					"Route12gw.csv");
			//String[][] Route63e = WeatherPassengerLink.getDataFromCSV(2304, 2,
				//	"Route63e.csv");
			String[][] Route63w = WeatherPassengerLink.getDataFromCSV(1536, 2,
					"Route63w.csv");
			String[][] Route90e = WeatherPassengerLink.getDataFromCSV(1536, 2,
					"Route90e.csv");
			String[][] Route90w = WeatherPassengerLink.getDataFromCSV(1536, 2,
					"Route90w.csv");

			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"StationElevationScore.csv"));
			String resultLine = GetElevationScore(Route9clock)
					+ GetElevationScore(Route9counterclock)
					+ GetElevationScore(Route10be)
					+ GetElevationScore(Route10bw)
					+ GetElevationScore(Route10ge)
					+ GetElevationScore(Route10gw)
					+ GetElevationScore(Route11we)
					+ GetElevationScore(Route11ww)
					+ GetElevationScore(Route11westerneast)
					+ GetElevationScore(Route11westernwest)
					+ GetElevationScore(Route12be)
					+ GetElevationScore(Route12bw)
					+ GetElevationScore(Route12ge)
					+ GetElevationScore(Route12gw)
					//+ GetElevationScore(Route63e)
					+ GetElevationScore(Route63w)
					+ GetElevationScore(Route90e)
					+ GetElevationScore(Route90w);
			writer.write(resultLine);
			writer.close();
		} catch (IOException e) {

		}
	}

	private static String GetElevationScore(String[][] elevation) {

		int size = elevation.length;
		for (int i = 0; i < size; i++) {
			if (elevation[i][0].equals(""))
				elevation[i][0] = "0";
		}
		System.out.println("now is ");
		String result = elevation[0][0] + ",0" + "\n";
		for (int i = 0; i < size; i++) {
			if (Integer.parseInt(elevation[i][0]) > 30 && i > 0 && i < size - 1)

			{
				result += elevation[i][0]
						+ ","
						+ ElevationScoreCalculation(
								Double.parseDouble(elevation[i - 5][1]),
								Double.parseDouble(elevation[i - 4][1]),
								Double.parseDouble(elevation[i - 3][1]),
								Double.parseDouble(elevation[i - 2][1]),
								Double.parseDouble(elevation[i - 1][1]),
								Double.parseDouble(elevation[i][1]),
								Double.parseDouble(elevation[i + 1][1]),
								Double.parseDouble(elevation[i + 2][1]),
								Double.parseDouble(elevation[i + 3][1]),
								Double.parseDouble(elevation[i + 4][1]),
								Double.parseDouble(elevation[i + 5][1])) + "\n";

			}
		}

		result += elevation[size - 1][0] + ",0" + "\n";
		System.out.println(result);
		return result;
	}

	private static String ElevationScoreCalculation(double a, double b,
			double c, double d, double e, double stop, double u, double w,
			double x, double y, double z) {
		if (a < b && b < c && c < d && d < e && e < stop && stop < u && u < w
				&& w < x && x < y && y < z)
			return "2"; // up
		if (a > b && b > c && c > d && d > e && e > stop && stop > u && u > w
				&& w > x && x > y && y > z)
			return "2"; // down
		if (a > b && b > c && c > d && d > e && e > stop && stop < u && u < w
				&& w < x && x < y && y < z)
			return "1"; // bottom
		if (a < b && b < c && c < d && d < e && e > stop && stop > u && u > w
				&& w > x && x > y && y > z)
			return "2"; // peak

		return "3"; // flat

	}
}
