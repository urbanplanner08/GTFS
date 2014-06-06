package search;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AlbanyDirectionsearch extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String routeid = request.getParameter("routeid").toString();
      session.setAttribute("routeid", routeid);
		if ( routeid == null
				|| routeid == "") {
			response.sendRedirect("albanyagency.jsp");
		}

		else {
			response.sendRedirect("albanydirection.jsp");
		}
	}

	public static ResultSet SearchDirection(String routeid) {
		String connectionURL = "jdbc:mysql://localhost:3306/albany";
		Connection connection = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(connectionURL, "root",
					"root");
			String QueryString = "select distinct trip_headsign from final where routeid='"+Integer.parseInt(routeid)+"'";
			st = connection.createStatement();
			rs = st.executeQuery(QueryString);
			return rs;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
