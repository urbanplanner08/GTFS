package search;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Stop extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String direction = request.getParameter("direction").toString();
		session.setAttribute("direction", direction);
		if (direction == null || direction == "" ) {
			response.sendRedirect("direction.jsp");
		}

		else {
			response.sendRedirect("stop.jsp");

		}
	}
	public static ResultSet StopSearch(String routeid, String direction) {
		Statement st = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/austin", "root", "root");
			st = con.createStatement();
			String sql = "select Agency, RouteID, RouteName, StopID, StopName, StopLat, StopLog, StopSequence, DirectionID, Trip_Headsign from final where routeid='"
					+ Integer.parseInt(routeid)
					+ "' and Trip_Headsign= '"
					+ direction + "' order by StopSequence";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			return rs;

		} catch (Exception e) {
			System.out.println("Exception is ;" + e);
			return null;
		}
	}


}