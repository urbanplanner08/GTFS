package DataProcess;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// get elevation score between 2 and -2 
public class GetElevationScore {

	public static void main(String[] args) throws Exception {

		try {
			String[][] stop = WeatherPassengerLink.getDataFromCSV(431, 2,
					"stopid.csv");
			String[][] stopelevation = WeatherPassengerLink.getDataFromCSV(831,
					2, "StationElevationScore.csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"STOP_ELEVATION_SCORE.csv"));
			String result="";
			for (int i = 1; i < stop.length; i++) {
				int sum = 0;
				int index = 0;
				int grade = 0;
				for (int j = 0; j < stopelevation.length; j++)
				{
					if (stop[i][0].equals(stopelevation[j][0])) 
					{
						sum = sum + Integer.parseInt(stopelevation[j][1]);
						index++;
					}
				}
				if(index==0)
					grade=100;
				else 
					grade = sum / index;
              result+=stop[i][0]+","+grade+"\n";
			}
			writer.write(result);
			writer.close();
		} catch (IOException e) {

		}
	}

}
