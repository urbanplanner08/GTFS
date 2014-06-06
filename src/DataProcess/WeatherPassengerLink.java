package DataProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// combine passenger on_off data with weather model 
public class WeatherPassengerLink {

	public static final int COLUMN_NUMBER = 11;
	public static final int ROW_NUMBER = 77224;

	public static final int WEATHER_COLUMN_NUMBER = 11;
	public static final int WEATHER_ROW_NUMBER = 6720;

	public static final String MORNING = "1";
	public static final String NOON = "2";
	public static final String AFTERNOON = "3";
	public static final String EVENING = "4";

	public static final String WEEKDAY = "0";
	public static final String WEEKEND = "1";
	private static String decideTimeType(String time) {
		String timer = null;

		int hour = Integer.parseInt(time.split(":")[0]);
		if (hour < 9)
			timer = MORNING;
		if (hour >= 9 && hour <= 15)
			timer = NOON;
		if (hour > 15 && hour <= 19)
			timer = AFTERNOON;
		if (hour > 19)
			timer = EVENING;
		return timer;
	}

	public static String[][] getDataFromCSV(int row, int column, String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String[][] result = new String[row][column];
			String line = reader.readLine();
			int index = 0;
			while (line != null) {
				result[index] = line.split(",");
				index++;
				line = reader.readLine();
			}
			reader.close();
			return result;
		} catch (IOException e) {
		}
		return null;
	}

	private static String changeTimeFormat(String Time) {
		String pretime = Time.split(" ")[0];
		String AMPM = Time.split(" ")[1];
		int hour = Integer.parseInt(pretime.split(":")[0]);
		int minute = Integer.parseInt(pretime.split(":")[1]);
		String timer = null;
		if (hour >= 5 && hour <= 11 && minute < 30 && AMPM.equals("AM"))
			timer = Integer.toString(hour) + ":00:00";

		if (hour >= 5 && hour <= 11 && minute >= 30 && AMPM.equals("AM"))
			timer = Integer.toString(hour + 1) + ":00:00";

		if (hour >= 1 && hour <= 10 && minute < 30 && AMPM.equals("PM"))
			timer = Integer.toString(hour + 12) + ":00:00";

		if (hour >= 1 && hour <= 10 && minute >= 30 && AMPM.equals("PM"))
			timer = Integer.toString(hour + 13) + ":00:00";

		if (hour == 12 && minute >= 30 && AMPM.equals("PM"))
			timer = "13:00:00";

		if (hour == 12 && minute < 30 && AMPM.equals("PM"))
			timer = "12:00:00";

		return timer;
	}

	public static String WeekNum(String Week) {
		String weekNum = null;
		if (Week.contains("end"))
			weekNum =WEEKEND;
		else
			weekNum =WEEKDAY;
		return weekNum;
	}

	public static void main(String[] args) throws Exception {
		String[][] PassengerData = getDataFromCSV(ROW_NUMBER, COLUMN_NUMBER,
				"DataGeneralization.csv");
		String[][] WeatherData = getDataFromCSV(WEATHER_ROW_NUMBER,
				WEATHER_COLUMN_NUMBER, "weather_model.csv");
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				"Passenger_Weather_Combined.csv"));

		for (int i = 0; i < ROW_NUMBER; i++) {
			String busIndex = PassengerData[i][0];
			String fullTime = PassengerData[i][1];
			String fullTimeFormat = changeTimeFormat(fullTime);
			String timeClassfication = decideTimeType(fullTimeFormat);
			String direction = PassengerData[i][2];
			String id = PassengerData[i][3];
			String stopName = PassengerData[i][4];
			String passengerOn = PassengerData[i][5];
			String passengerOff = PassengerData[i][6];
			String passengerSum = PassengerData[i][7];
			String weatherDes = PassengerData[i][8];
			String fullDate = PassengerData[i][9];
			String WeekdayOrWeekend = PassengerData[i][10];
			String WeekNumber = WeekNum(WeekdayOrWeekend);
			String weather = null;
			String weatherScore = null;
			String line = null;
			int j = 0;
			while (j < WEATHER_ROW_NUMBER) {
				if (fullTimeFormat.equals(WeatherData[j][1])
						&& fullDate.equals(WeatherData[j][0])) {
					weather = WeatherData[j][10];
					weatherScore = WeatherData[j][9];
					System.out.println(i + "," + weather);
					break;
				}
				j++;
			}
			line = busIndex + "," + direction + "," + id + "," + stopName + ","
					+ passengerOn + "," + passengerOff + "," + passengerSum
					+ "," + WeekdayOrWeekend + "," + WeekNumber + ","
					+ fullDate + "," + fullTime + "," + timeClassfication + ","
					+ weatherDes + "," + weather + "," + weatherScore + "\n";
			writer.write(line);

		}
		writer.close();

	}
}
