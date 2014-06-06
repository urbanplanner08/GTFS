package search;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class AlbanyRoutesearch extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
	}

	public static ResultSet SearchRoute() {
		String connectionURL = "jdbc:mysql://localhost:3306/albany";
		Connection connection = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(connectionURL, "root",
					"root");
			String QueryString = "select distinct RouteID, RouteName from final";
			st = connection.createStatement();
			rs = st.executeQuery(QueryString);
			return rs;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
