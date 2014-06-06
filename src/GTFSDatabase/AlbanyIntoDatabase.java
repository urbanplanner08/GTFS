package GTFSDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class AlbanyIntoDatabase {

	private String DB_USERNAME = "root";
	private String DB_PASSWORD = "root";
	
    public void Routes(String filePath) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // change the connection file to austin
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/albany", DB_USERNAME, DB_PASSWORD);
            Statement st = con.createStatement();
            FileReader fr = new FileReader(filePath);
            BufferedReader in = new BufferedReader(fr);
            String line = null;
            String[] arrs = null;
            in.readLine();
            while ((line = in.readLine()) != null) {
                arrs = line.split(",");
                String sql = "insert into route (Agency, RouteID, RouteName, RouteType ) Values('Capital District Transit Authority', '"
                        + arrs[0]
                        + "' , '"
                        + arrs[3]
                        + "' , '"
                        + arrs[5]
                        + "')";
                System.out.println(sql);
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

    public void Stops(String filePath) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // change the connection file
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/albany", DB_USERNAME, DB_PASSWORD);
            Statement st = con.createStatement();
            FileReader fr = new FileReader(filePath);
            BufferedReader in = new BufferedReader(fr);
            String line = null;
            String[] arrs = null;
            in.readLine();
            while ((line = in.readLine()) != null) {
                arrs = line.split(",");
                String sql = "insert into Stop (StopID, StopName, StopLat, StopLog, StopDes, LocationType) Values('"
                        + arrs[0]
                        + "', '"
                        + arrs[2]
                        + "' , "
                        + arrs[4]
                        + ", "
                        + arrs[5] + ", '" + arrs[3] + "', '" + arrs[8] + "')";

                System.out.println(sql);
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

    public void StopTimes(String filePath) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // change the file connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/albany", DB_USERNAME, DB_PASSWORD);
            Statement st = con.createStatement();
            FileReader fr = new FileReader(filePath);
            BufferedReader in = new BufferedReader(fr);
            String line = null;
            String[] arrs = null;
            in.readLine();
            while ((line = in.readLine()) != null) {
                arrs = line.split(",");
                String sql = "insert into StopTime ( TripID, StopID, StopSequence )"
                        + " Values('"
                        + arrs[0]
                        + "' , '"
                        + arrs[3]
                        + "' , "
                        + arrs[4] + ")";
                System.out.println(sql);
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

    public void Trips(String filePath) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // change the file connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/albany", DB_USERNAME, DB_PASSWORD);
            Statement st = con.createStatement();

            // file read
            FileReader fr = new FileReader(filePath);
            BufferedReader in = new BufferedReader(fr);
            String line = null;
            String[] arrs = null;
            in.readLine();
            while ((line = in.readLine()) != null) {
                arrs = line.split(",");
                String sql = "insert into Trip ( TripID, RouteID, DirectionID, Trip_Headsign )"
                        + " Values('"
                        + arrs[2]
                        + "' , '"
                        + arrs[0] 
                        + "' , "
                        + arrs[4]+", '"
                        + arrs[3] + "')";
                System.out.println(sql);
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

    
    
    
    


    public void DataMining(String filePath) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // change the file connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/albany", DB_USERNAME, DB_PASSWORD);
            Statement st = con.createStatement();

            // file read
            FileReader fr = new FileReader(filePath);
            BufferedReader in = new BufferedReader(fr);
            String line = null;
            String[] arrs = null;
            
            line = in.readLine();
            line = in.readLine();

            while (line!= null) {
            	String total="";
                arrs = line.split(",");
                for(int i=0;i<arrs.length-1;i++)
                	total+=arrs[i]+",";                	
                total=total+arrs[arrs.length-1];
                String sql = "insert into datamining Values("+total+")";
                System.out.println(sql);
                PreparedStatement pst = con.prepareStatement(sql);
                pst.executeUpdate();
                pst.close();
				line = in.readLine();
            }
            in.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void Passenger(String filePath) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // change the file connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/albany", DB_USERNAME, DB_PASSWORD);
            Statement st = con.createStatement();

            // file read
            FileReader fr = new FileReader(filePath);
            BufferedReader in = new BufferedReader(fr);
            String line = null;
            String[] arrs = null;
            
            line = in.readLine();
            line = in.readLine();

            while (line!= null) {
            	String total="'";
                arrs = line.split(",");
               
                for(int i=0;i<arrs.length-1;i++)
                	total+=arrs[i]+"','";                	
                total=total+arrs[arrs.length-1];
                System.out.println(total);
                String sql = "insert into passengerweathertable Values('0',"+total+"')";
                System.out.println(sql);
                PreparedStatement pst = con.prepareStatement(sql);
                pst.executeUpdate();
                pst.close();
				line = in.readLine();
            }
            in.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
      // String filePath1 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\routes.txt";
     //    String filePath2 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\stops.txt";
       // String filePath3 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\stop_times.txt";
     // filePath4 = "C:\\Users\\Daniel\\Desktop\\GTFS\\albany\\trips.txt";
        //new AlbanyIntoDatabase().Routes(filePath1);
   // new AlbanyIntoDatabase().Stops(filePath2);
     //   new AlbanyIntoDatabase().StopTimes(filePath3);
       // new AlbanyIntoDatabase().Trips(filePath4);
    	 String filePath = "C:\\Users\\Daniel\\Desktop\\Passenger_Weather_Combined_intoDatabase.csv";
       new AlbanyIntoDatabase().Passenger(filePath);
    }
}