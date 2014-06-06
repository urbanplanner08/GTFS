<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.sql.*" errorPage=""%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*"%>
<%@ page import="search.AlbanyRoutesearch"%>
<%@ page import="search.AlbanyStop"%>
<%@ page import="search.AlbanyDirectionsearch"%>
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
	<br />
	<div id="contentContainer">
		<div id="leftPane">

			<!--  form, here the route id and route direction could be retrieval so that the stops could be showed -->

			<form id="searchForm" name="form" action="Albanystop.do" method="post">
				RouteID <%=session.getAttribute("routeid").toString()%><br /> 
				
				<!-- here retrieval the value of direction  -->

				<br /> Direction <br /> <select name="direction">
				<option value=""></option>
							<%
					    String routeid=session.getAttribute("routeid").toString();
						ResultSet rs1 = null;
						AlbanyDirectionsearch directionsearch = new AlbanyDirectionsearch();
						rs1 = directionsearch.SearchDirection(routeid);
						while (rs1.next()) {
					%>
					<option value='<%=rs1.getString("trip_headsign")%>'>
						<%=rs1.getString("trip_headsign")%></option>
					<%
						}
					%>
				</select> <br> <input type="Submit" name="submit"> <br />
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
<br />

	<footer id="mainFooter">
			<p> The source code of this project is available in the <a href="source_code_page/SourceCode.html">source code website</a>.  </p>
	
		<p>
			<br /> Tianchi Zhang, Department of Informatics, University at
			Albany, State University of New York <br /> <em>(This site is
				only for author's dissertation )</em>
		</p>
	</footer>
</body>
</html>