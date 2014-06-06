package GTFSDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class CDTADataset {
	private String DB_USERNAME = "root";
	private String DB_PASSWORD = "root";

	public void IntoCDTA(String filePath) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// change the connection file
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/albany", DB_USERNAME,
					DB_PASSWORD);
			Statement st = con.createStatement();
			ResultSet rs = null;
			FileReader fr = new FileReader(filePath);
			BufferedReader in = new BufferedReader(fr);
			String line = null;
			String[] arrs = null;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");

				String sql = "insert into CDTA (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog) Values( "
						+ arrs[0]
						+ " , '"
						+ arrs[1]
						+ "' , "
						+ arrs[2]
						+ " , '"
						+ arrs[3]
						+ "' , "
						+ arrs[4]
						+ " , "
						+ arrs[5]
						+ " , '"
						+ arrs[6]
						+ "' , '"
						+ arrs[7]
						+ "' , "
						+ arrs[8] + " , " + arrs[9] + ")";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.executeUpdate();
				pst.close();

			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String filePath1 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\cdta.txt";
		new CDTADataset().IntoCDTA(filePath1);
	}
}