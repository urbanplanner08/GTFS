package GTFSDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class DataClassfication {
	private String DB_USERNAME = "root";
	private String DB_PASSWORD = "root";
	
	public void Route9ClockWise(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 9 , 'Circle Route', 0, 'Clockwise', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 9 , 'Circle Route', 0, 'Clockwise', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		
	public void Route9CounterClockWise(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 9 , 'Circle Route', 1, 'CounterClockwise', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 9 , 'Circle Route', 1, 'CounterClockwise', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void Route10BroadwayEast(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 10 , 'Western Ave Broadway', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 10 , 'Western Ave Broadway', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
		
	public void Route10BroadwayWest(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 10 , 'Western Ave Broadway', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 10 , 'Western Ave Broadway', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		
	public void Route10GreyhoundEast(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 10 , 'Western Ave Greyhound', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 10 , 'Western Ave Greyhound', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
		
	public void Route10GreyhoundWest(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 10 , 'Western Ave Greyhound', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 10 , 'Western Ave Greyhound', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
		
	public void Route11WashingtonEast(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 11 , 'Route 11 Washington', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 11 , 'Route 11 Washington', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
		
	public void Route11WashingtonWest(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 11 , 'Route 11 Washington', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 11 , 'Route 11 Washington', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
		
	public void Route11WesternEast(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 11 , 'Route 11 Western', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 11 , 'Route 11 Western', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
		
	public void Route11WesternWest(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 11 , 'Route 11 Western', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 11 , 'Route 11 Western', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
		
	public void Route12BroadwayEast(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 12 , 'Route 12 Broadway', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 12 , 'Route 12 Broadway' , 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
		
	public void Route12BroadwayWest(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 12 , 'Route 12 Broadway', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 12 , 'Route 12 Broadway', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
		
	public void Route12GreyhoundEast(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 12 , 'Route 12 Greyhound', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 12 , 'Route 12 Greyhound', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
		
	public void Route12GreyhoundWest(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 12 , 'Route 12 Greyhound', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 12 , 'Route 12 Greyhound', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
		
	public void Route63East(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 63 , 'Route 63', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 63 , 'Route 63', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
		
	public void Route63West(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 63 , 'Route 63', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 63 , 'Route 63', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
		
	public void Route90East(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 90 , 'Albany - Troy', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 90 , 'Albany - Troy', 0, 'East', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void Route90West(String filePath) {
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
			String stopID = null;
			String stopName = null;
			String lat = null;
			String log = null;
			String stopDes = null;
			int i = 1;
			in.readLine();
			while ((line = in.readLine()) != null) {
				arrs = line.split(",");
				String sql1 = "select distinct StopName, StopLat, StopLog, StopDes, LocationType from stop where stopid="
						+ arrs[3];
				st = con.createStatement();
				rs = st.executeQuery(sql1);
					int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count==0) {
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName) Values( 90 , 'Albany - Troy', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4] + "')";
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				} 
				else {
					st = con.createStatement();
					rs = st.executeQuery(sql1);
					while (rs.next()) {
					stopName = rs.getString("StopName");
					lat = rs.getString("StopLat");
					log = rs.getString("StopLog");
					stopDes = rs.getString("StopDes");					
					String sql2 = "insert into Test (RouteID, RouteName, DirectionID, Trip_Headsign, StopSequence, StopID, StopName, Stop_Name, StopLat, StopLog, StopDes) Values( 90 , 'Albany - Troy', 1, 'West', "
							+ i
							+ " , "
							+ arrs[3]
							+ " , '"
							+ arrs[4]
							+ "', '"
							+ stopName
							+ "', "
							+ lat
							+ ","
							+ log
							+ ",'"
							+ stopDes + "')";
			//System.out.println(sql2);
					PreparedStatement pst = con.prepareStatement(sql2);
					pst.executeUpdate();
					pst.close();
				}
				}
				i++;
			}
			in.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String filePath1 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route9_Clockwise_78.txt";
		String filePath2 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route9_CounterClockwise_80.txt";
		String filePath3 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route10_Broadway_East_60.txt";
		String filePath4 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route10_Broadway_West_67.txt";
		String filePath5 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route10_Greyhound_East_52.txt";
		String filePath6 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route10_Greyhound_West_60.txt";
		String filePath7 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route11_Washington_East_25.txt";
		String filePath8 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route11_Washington_West_31.txt";
		String filePath9 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route11_Western_East_33.txt";
		String filePath10 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route11_Western_West_35.txt";
		String filePath11 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route12_Broadway_East_43.txt";
		String filePath12 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route12_Broadway_West_48.txt";
		String filePath13 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route12_Greyhound_East_44.txt";
		String filePath14= "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route12_Greyhound_West_44.txt";
		String filePath15 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route63_East_56.txt";
		String filePath16 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route63_West_55.txt";
		String filePath17 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route90_East_54.txt";
		String filePath18 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\CDTA\\Route90_West_55.txt";
		new DataClassfication().Route9ClockWise(filePath1);
		new DataClassfication().Route9CounterClockWise(filePath2);
		new DataClassfication().Route10BroadwayEast(filePath3);
		new DataClassfication().Route10BroadwayWest(filePath4);	
		new DataClassfication().Route10GreyhoundEast(filePath5);
		new DataClassfication().Route10GreyhoundWest(filePath6);
		new DataClassfication().Route11WashingtonEast(filePath7);
		new DataClassfication().Route11WashingtonWest(filePath8);		
		new DataClassfication().Route11WesternEast(filePath9);
		new DataClassfication().Route11WesternWest(filePath10);
		new DataClassfication().Route12BroadwayEast(filePath11);
		new DataClassfication().Route12BroadwayWest(filePath12);	
		new DataClassfication().Route12GreyhoundEast(filePath13);
		new DataClassfication().Route12GreyhoundWest(filePath14);
        new DataClassfication().Route63East(filePath15);
		new DataClassfication().Route63West(filePath16);
	    new DataClassfication().Route90East(filePath17);
		new DataClassfication().Route90West(filePath18);
	}
}