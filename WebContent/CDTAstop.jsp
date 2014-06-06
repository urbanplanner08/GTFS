<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.sql.*" errorPage=""%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*"%>
<%@ page import="search.CDTARoutesearch"%>
<%@ page import="search.CDTAStop"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>General Transit Feed Specialization Data Visualization
	and Application</title>
<link type="text/css" rel="stylesheet"
	href="style.css" media="all" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="jquery.js"></script>

<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?v=3&key=AIzaSyDvcH3b-bogVZ3PfKgvwgYKXlwPjtFcQTI&sensor=true">
	
</script>
<script>
function myclick(num) {
	  google.maps.event.trigger(gmarkers[num], "click");
	}
	
function createMarker(pt, html, map, icon){
    var myMarkerOpts = {
        position: pt,
        map: map,
        icon: icon
    };
    
    var info = html;
    var marker = new google.maps.Marker(myMarkerOpts);
    
    markers.push(marker);
    
    google.maps.event.addListener(marker, 'click', function() {
    infoWindow.close();
    infoWindow.setContent(info);
    infoWindow.open(map,marker); 
  });
}
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
			<br />
			<div id="stopList">
				<h3 class="leagueName">
					Route:
					<%=session.getAttribute("routeid").toString()%>
				</h3>
				<br />
				
				<li class="stopList"><a class="fakebutton" href="CDTAstatistics.jsp">
						Route Statistics Generalization </a></li>  
						<br />
						<li class="stopList"><a class="fakebutton" href="CDTAelevation.jsp">
						Elevation Along the Route </a></li> <br />
					<h4 style="margin-left: 8%; color:grey"><i><%=session.getAttribute("direction").toString()%>
					Stop List<br />
				</i></h4>
				<ul>
					<%  
					
						ArrayList stopid = new ArrayList();
						ArrayList stopname = new ArrayList();
						ArrayList stoplat = new ArrayList();
						ArrayList stoplog = new ArrayList();
						ArrayList stopseq = new ArrayList();
						ResultSet rs = null;
						CDTAStop stop = new CDTAStop();
						String direction = session.getAttribute("direction").toString();
						String routeid = session.getAttribute("routeid").toString();
						rs = stop.StopSearch(routeid, direction);
						int count = 0;
						while (rs.next()) {
							stopid.add(rs.getInt("StopID"));
							stopname.add(rs.getString("StopName"));
							stoplat.add(rs.getDouble("StopLat"));
							stoplog.add(rs.getDouble("StopLog"));
							stopseq.add(rs.getInt("StopSequence"));
							count++; 
					%>
					<li class="stopList"><ins>
							<b> <a href="javascript:myclick('<%=count - 1%>')"><%=rs.getInt("StopSequence")%>~<%=rs.getString("StopName")%></b></a>
						</ins></li>
					<%
						}
					%>
				</ul>
			</div>

		</div>

		<div id="mapDiv">

			<form action="resume.jsp">
				<input id="overviewButton" type="submit" value="Resume">
			</form>

			<div id="map-canvas"></div>

			<script>
var locations = [
                 <%int count2 = 0;
			while (count2 < count - 1) {%>
                 ['<%=stopname.get(count2)%>', <%=stoplat.get(count2)%>, <%=stoplog.get(count2)%>, <%=stopseq.get(count2)%>, <%=stopid.get(count2)%>],
 				<%count2++;
			}%>
                 ['<%=stopname.get(count2)%>',<%=stoplat.get(count2)%>,	<%=stoplog.get(count2)%>,<%=stopseq.get(count2)%>,<%=stopid.get(count2)%>] 
                 ];
				var gmarkers = [];
				var mapDiv = document.getElementById("map-canvas");
				var mapOptions = {
					center : new google.maps.LatLng(42.65, -73.75),
					zoom : 10,
					mapTypeId : google.maps.MapTypeId.ROADMAP
				};
				var map = new google.maps.Map(mapDiv, mapOptions);
				var infowindow = new google.maps.InfoWindow();
				var marker, i;
				for (i = 0; i < locations.length; i++) {
					marker = new google.maps.Marker({
						position : new google.maps.LatLng(locations[i][1],
								locations[i][2]),
						map : map
					});

					gmarkers.push(marker);
					google.maps.event.addListener(marker, 'click', (function(
							marker, i) {
						return function() {
							var html = "<strong>Stop ID: " + locations[i][4]
									+ "<br /><br />Stop Name: "
									+ locations[i][0] + "</strong>";
							var html2 = "<hr /><br /> <strong>Stop Sequence: "
									+ locations[i][3]
									+ "<br /> <br />Stop Latitude: "
									+ locations[i][1] + "</strong>";
							var html3 = "<strong><br /><br /> Stop Longitude: "
									+ locations[i][2] + "</strong>";
							infowindow.setContent(html + html2 + html3);
							infowindow.open(map, marker);
							map.setZoom(14);
							map.setCenter(marker.getPostion());
						}
					})(marker, i));
				}
				//Add Class to sidebar when clicked
				$('li').live('mouseover', function() {
					$(this).addClass('highlight');
				}).live('mouseleave', function() {
					$(this).removeClass('highlight');
				});
			</script>
		</div>

<br /><br />
	
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