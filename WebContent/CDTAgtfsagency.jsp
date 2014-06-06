<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.sql.*" errorPage=""%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*"%>
<%@ page import="search.CDTARoutesearch"%>
<%@ page import="search.CDTAStop"%>
<%@ page import="search.CDTADirectionsearch"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>General Transit Feed Specialization Data Visualization 
	and Application</title>
<link type="text/css" rel="stylesheet"
	href="style.css" media="all" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?v=3&key=AIzaSyDvcH3b-bogVZ3PfKgvwgYKXlwPjtFcQTI&sensor=true">
	
</script>
<script type="text/javascript">
	function initialize() {
		var mapOptions = {
			center : new google.maps.LatLng(42.65, -73.75),
			zoom : 10,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("map-canvas"),
				mapOptions);
	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>

</head>

<body id="mainBody">
	<div id="headerImage">
		<img src="brt.jpg" height="100"
			width="180" />
	</div>
	
	<header id="mainHeader">
		<hgroup id="mainHeaderGroup">
			<h1>GTFS Data Visualization & Application</h1>
			<br />
			<br />
			<br />
		</hgroup>
	</header>
	<div id="contentContainer">
		<div id="leftPane">


			<form id="searchForm" name="form" action="CDTADirectionsearch.do" method="post">
				RouteID and Name <br /> <select name="routeid">
				<option value=""></option>
					<%
						ResultSet rs = null;
						CDTARoutesearch routesearch = new CDTARoutesearch();
						rs = routesearch.SearchRoute();
						while (rs.next()) {
					%>
					<option value='<%=rs.getString("routename")%>'>Route
						<%=rs.getInt("routeid")%>:
						<%=rs.getString("routename")%>
					</option>
					<%
						}
					%>
				</select> <br />

				<!-- here retrieval the value of direction  -->

				<br /> <input type="Submit" name="submit"> <br />
				<input type="Reset" value="Reset  "> <br>
			</form>

			<br />
		

		</div>

		<div id="mapDiv">
			<form action="resume.jsp">
				<input id="overviewButton" type="submit" value="Resume">
			</form>

			<div id="map-canvas" />
		</div>

	</div>
	
        <footer>
        		<p> The source code of this project is available in the <a href="source_code_page/SourceCode.html">source code website</a>.  </p>
        
            <p>
                <br /> Tianchi Zhang, Department of Informatics, University at
                Albany, State University of New York <br /></p>
                     <p>If you have any questions, please contact author by <a href="mailto:ztc.sky@gmail.com">
  ztc.sky@gmail.com</a>. <br /><em>(This site is
                    only for author's dissertation )</em></p>
        </footer>
</body>
</html>