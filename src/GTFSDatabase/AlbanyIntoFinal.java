package GTFSDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class AlbanyIntoFinal {

	private String DB_USERNAME = "root";
	private String DB_PASSWORD = "root";

	public void InsertInto() {
		Statement st = null;
		ResultSet rs = null;
		String agency = null;
		String routeID = null;
		String routeName = null;
		String stopID = null;
		String stopName = null;
		String lat = null;
		String log = null;
		String stopDes = null;
		String locationType = null;
		String stopSeq = null;
		String directionID = null;
		String trip_headsign=null;

		String sql = "select distinct Agency, r.RouteID, r.RouteName, s.StopID, s.StopName, s.StopLat, s.StopLog, s.StopDes, s.LocationType, st.StopSequence, t.DirectionID, t.Trip_Headsign from route as r, stop as s, stoptime as st, trip as t where st.StopID=s.StopID and t.RouteID=r.RouteID and t.TripID=st.TripID ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/albany", DB_USERNAME,
					DB_PASSWORD);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				agency = rs.getString("Agency");
				routeID = rs.getString("r.RouteID");
				routeName = rs.getString("r.RouteName");
				stopID = rs.getString("s.StopID");
				stopName = rs.getString("s.StopName");
				lat = rs.getString("s.StopLat");
				log = rs.getString("s.StopLog");
				stopDes = rs.getString("s.StopDes");
				locationType = rs.getString("s.LocationType");
				stopSeq = rs.getString("st.StopSequence");
				directionID = rs.getString("t.DirectionID");
				trip_headsign=rs.getString("t.Trip_Headsign");
				
				String sql2 = "insert into final Values('" + agency + "'," + routeID
						+ " ,'" + routeName + "','" + stopID + "','" + stopName + "',"
						+ lat + "," + log + ",'" + stopDes + "','" + locationType
						+ "'," + stopSeq + "," + directionID + ", '"+trip_headsign+"')";
				System.out.println(sql2);
				PreparedStatement pst = con.prepareStatement(sql2);
				pst.executeUpdate();
				pst.close();

			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new AlbanyIntoFinal().InsertInto();

	}
}