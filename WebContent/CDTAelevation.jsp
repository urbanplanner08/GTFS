
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.sql.*" errorPage=""%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.math.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="search.CDTARoutesearch"%>
<%@ page import="search.CDTAStop"%>

   <%
					ArrayList stopid= new ArrayList();
					ArrayList stopname= new ArrayList();
					ArrayList stoplat= new ArrayList();
					ArrayList stoplog= new ArrayList();
					ArrayList stopseq= new ArrayList();
					
					
						ResultSet rs = null;
						CDTAStop stop = new CDTAStop();
						String directionid = session.getAttribute("direction").toString();
						String routeid = session.getAttribute("routeid").toString();
						rs = stop.StopSearch(routeid, directionid);
						int count = 0;
						while (rs.next()) {
							stopid.add(rs.getInt("StopID"));
							stopname.add(rs.getString("StopName"));
							stoplat.add(rs.getDouble("StopLat"));
							stoplog.add(rs.getDouble("StopLog"));
							stopseq.add(rs.getInt("StopSequence"));
							count++;
						}
						%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
 Copyright 2010 Google Inc. 
 Licensed under the Apache License, Version 2.0: 
 http://www.apache.org/licenses/LICENSE-2.0 
 -->
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<title>Google Maps JavaScript API v3 Example: Elevation</title>
<link href="table.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="style.css" media="all" />

<style>
    #map_canvas{
    width: 90%;
    height: 450px;
    margin-left:auto;
    margin-right:auto;
     border: 3px solid navy;
    -webkit-border-radius: 8px;
    border-radius: 8px;
    -webkit-box-shadow:2px 2px 7px 2px #aaaaaa;
    box-shadow: 2px 2px 7px 2px #aaaaaa;
}
</style>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
  var map = null;
  var chart = null;
  
  var geocoderService = null;
  var elevationService = null;
  var directionsService = null;
  
  var mousemarker = null;
  var markers = [];
  var polyline = null;
  var elevations = null;
  
  var SAMPLES = 256;

  var examples = [
                  <% 
				  if (count%9==0)
				  { 
					 int count2=0;
                    int max=count/9;
					for (int i=0;i<max-1;i++)
					 { 
                	  int bound=9+i*9;
                  %>  {   
              latlngs: [
             <% for(int x=count2;x<bound;x++)
             {%>
             ['<%=stopname.get(x)%>', <%=stoplat.get(x)%>, <%=stoplog.get(x)%>, <%=stopseq.get(x)%>, <%=stopid.get(x)%>],
             <%count2++;}%>   
             ['<%=stopname.get(count2)%>', <%=stoplat.get(count2)%>, <%=stoplog.get(count2)%>, <%=stopseq.get(count2)%>, <%=stopid.get(count2)%>] 
              ],
              mapType: google.maps.MapTypeId.ROADMAP,
              travelMode: 'driving'
            }, 
            <% } %>
			{ latlngs:[
               <%
          	 for (int x=count2;x<count-1;x++)
          	 {
          	 %>
               ['<%=stopname.get(x)%>', <%=stoplat.get(x)%>, <%=stoplog.get(x)%>, <%=stopseq.get(x)%>, <%=stopid.get(x)%>],
             <%count2++;}%>   
             ['<%=stopname.get(count2)%>', <%=stoplat.get(count2)%>, <%=stoplog.get(count2)%>, <%=stopseq.get(count2)%>, <%=stopid.get(count2)%>] 
               ],
              mapType: google.maps.MapTypeId.ROADMAP,
              travelMode: 'driving'
            }];
			
			<%
			
			
			}
				  else
				  {
                  int count2=0;
                     int max=(int)Math.floor(count/9);
                  for (int i=0;i<max;i++)
                  { 
                	  int bound=9+i*9;
                  %>  {   
              latlngs: [
             <% for(int x=count2;x<bound;x++)
             {%>
             ['<%=stopname.get(x)%>', <%=stoplat.get(x)%>, <%=stoplog.get(x)%>, <%=stopseq.get(x)%>, <%=stopid.get(x)%>],
             <%count2++;}%>   
             ['<%=stopname.get(count2)%>', <%=stoplat.get(count2)%>, <%=stoplog.get(count2)%>, <%=stopseq.get(count2)%>, <%=stopid.get(count2)%>] 
              ],
              mapType: google.maps.MapTypeId.ROADMAP,
              travelMode: 'driving'
            }, 
            <%}%>
            { latlngs:[
               <%
          	 for (int x=count2;x<count-1;x++)
          	 {
          	 %>
               ['<%=stopname.get(x)%>', <%=stoplat.get(x)%>, <%=stoplog.get(x)%>, <%=stopseq.get(x)%>, <%=stopid.get(x)%>],
             <%count2++;}%>   
             ['<%=stopname.get(count2)%>', <%=stoplat.get(count2)%>, <%=stoplog.get(count2)%>, <%=stopseq.get(count2)%>, <%=stopid.get(count2)%>] 
               ],
              mapType: google.maps.MapTypeId.ROADMAP,
              travelMode: 'driving'
            }];
  
  <%}%>

  // Load the Visualization API and the piechart package.
  google.load("visualization", "1", {packages: ["columnchart"]});
  
  // Set a callback to run when the Google Visualization API is loaded.
  google.setOnLoadCallback(initialize);
  
  function initialize() {
    var myLatlng = new google.maps.LatLng(42.65, -73.75);
    var myOptions = {
      zoom: 1,
      center: myLatlng,
      mapTypeId: google.maps.MapTypeId.TERRAIN
    }

    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    
    geocoderService = new google.maps.Geocoder();
    elevationService = new google.maps.ElevationService();
    directionsService = new google.maps.DirectionsService();
    
    google.maps.event.addListener(map, 'click', function(event) {
      addMarker(map, event.latLng,"", true);
    });
    
    google.visualization.events.addListener(chart, 'onmouseover', function(e) {
      if (mousemarker == null) {
        mousemarker = new google.maps.Marker({
          position: elevations[e.row].location,
          map: map,
          icon: "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
        });
      } else {
        mousemarker.setPosition(elevations[e.row].location);
      }
    });

    loadExample(0);
  }
  
  // Takes an array of ElevationResult objects, draws the path on the map
  // and plots the elevation profile on a GViz ColumnChart
  function plotElevation(results) {
    elevations = results;
    
    var path = [];
    for (var i = 0; i < results.length; i++) {
      path.push(elevations[i].location);
    }
    
    if (polyline) {
      polyline.setMap(null);
    }
    
    polyline = new google.maps.Polyline({
      path: path,
      strokeColor: "#000000",
      map: map});
    
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Sample');
    data.addColumn('number', 'Elevation');
    var str = '<tr><th>longitude</th><th>latitude</th><th>elevation</th></tr>';

    for (var i = 0; i < results.length; i++) {
      data.addRow(['', elevations[i].elevation]);
  	str += '<tr>';
  	str += '<td>' + elevations[i]['location']['pb'] + '</td>';
  	str += '<td>' + elevations[i]['location']['ob'] + '</td>';
  	str += '<td>' + elevations[i]['elevation'] + '</td>';
    }

    // print the coordinate-elevation table
    document.getElementById('x').innerHTML = str;


    document.getElementById('chart_div').style.display = 'block';
    chart.draw(data, {
      width: 1000,
      height: 200,
      legend: 'none',
      titleY: 'Elevation (m)',
      focusBorderColor: '#00ff00'
    });
  }
  
  // Remove the green rollover marker when the mouse leaves the chart
  function clearMouseMarker() {
    if (mousemarker != null) {
      mousemarker.setMap(null);
      mousemarker = null;
    }
  }
  
  // Geocode an address and add a marker for the result
  function addAddress() {
    var address = document.getElementById('address').value;
    geocoderService.geocode({ 'address': address }, function(results, status) {
      document.getElementById('address').value = "";
      if (status == google.maps.GeocoderStatus.OK) {
        var latlng = results[0].geometry.location;
        addMarker(latlng, true);
        if (markers.length > 1) {
          var bounds = new google.maps.LatLngBounds();
          for (var i in markers) {
            bounds.extend(markers[i].getPosition());
          }
          map.fitBounds(bounds);
        } else {
          map.fitBounds(results[0].geometry.viewport);
        }
      } else if (status == google.maps.GeocoderStatus.ZERO_RESULTS) {
        alert("Address not found");
      } else {
        alert("Address lookup failed");
      }
    })
  }
  
  // Add a marker and trigger recalculation of the path and elevation
  function addMarker(map, latlng, html, doQuery) {
	  var contentString=html;
    if (markers.length < 10) {
      
      var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        draggable: true
      })
      
      google.maps.event.addListener(marker, 'dragend', function(e) {
        updateElevation();
      });
      
      google.maps.event.addListener(marker, 'click', function() {
          infowindow.setContent(contentString); 
          infowindow.open(map,marker);
          });
      
      markers.push(marker);
      
      if (doQuery) {
        updateElevation();
      }
      
      if (markers.length == 10) {
        document.getElementById('address').disabled = true;
      }
    } else {
      alert("No more than 10 points can be added");
    }
  }
  
  // Trigger the elevation query for point to point
  // or submit a directions request for the path between points
  function updateElevation() {
    if (markers.length > 1) {
      var travelMode = document.getElementById("mode").value;
      if (travelMode != 'direct') {
        calcRoute(travelMode);
      } else {
        var latlngs = [];
        for (var i in markers) {
          latlngs.push(markers[i].getPosition())
        }
        elevationService.getElevationAlongPath({
          path: latlngs,
          samples: SAMPLES
        }, plotElevation);
      }
    }
  }
  
  // Submit a directions request for the path between points and an
  // elevation request for the path once returned
  function calcRoute(travelMode) {
    var origin = markers[0].getPosition();
    var destination = markers[markers.length - 1].getPosition();
    
    var waypoints = [];
    for (var i = 1; i < markers.length - 1; i++) {
      waypoints.push({
        location: markers[i].getPosition(),
        stopover: true
      });
    }
    
    var request = {
      origin: origin,
      destination: destination,
      waypoints: waypoints
    };
   
    switch (travelMode) {
      case "bicycling":
        request.travelMode = google.maps.DirectionsTravelMode.BICYCLING;
        break;
      case "driving":
        request.travelMode = google.maps.DirectionsTravelMode.DRIVING;
        break;
      case "walking":
        request.travelMode = google.maps.DirectionsTravelMode.WALKING;
        break;
    }
    
    directionsService.route(request, function(response, status) {
      if (status == google.maps.DirectionsStatus.OK) {
        elevationService.getElevationAlongPath({
          path: response.routes[0].overview_path,
          samples: SAMPLES
        }, plotElevation);
      } else if (status == google.maps.DirectionsStatus.ZERO_RESULTS) {
        alert("Could not find a route between these points");
      } else {
        alert("Directions request failed");
      }
    });
  }

  // Trigger a geocode request when the Return key is
  // pressed in the address field
  function addressKeyHandler(e) {
    var keycode;
    if (window.event) {
      keycode = window.event.keyCode;
    } else if (e) {
      keycode = e.which;
    } else {
      return true;
    }
    
    if (keycode == 13) {
       addAddress();
       return false;
    } else {
       return true;
    }
  }
  
  function loadExample(n) {
    reset();
    map.setMapTypeId(examples[n].mapType);
    document.getElementById('mode').value = examples[n].travelMode;
    var bounds = new google.maps.LatLngBounds();
    for (var i = 0; i < examples[n].latlngs.length; i++) {
    	var html=examples[n].latlngs[i][0];
      var latlng = new google.maps.LatLng(
        examples[n].latlngs[i][1],
        examples[n].latlngs[i][2]
      );
      addMarker(map, latlng, html, false);
      bounds.extend(latlng);
    }
    map.fitBounds(bounds);
    updateElevation();
  }
  
  // Clear all overlays, reset the array of points, and hide the chart
  function reset() {
    if (polyline) {
      polyline.setMap(null);
    }
    
    for (var i in markers) {
      markers[i].setMap(null);
    }
    
    markers = [];
    
    document.getElementById('chart_div').style.display = 'none';
  }

</script>
</head>
<body id="mainBody" >
<div id="headerImage">
<img src="brt.jpg" height="100" width="180" />
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
    <div id="contentContainer" >

  <div   id="map_canvas"></div>
  <br />
    <div id="chart_div" style="text-align:center" onmouseout="clearMouseMarker()"></div>
  
  <br />
  
  <table class="CSSTableGenerator" style="width:90%;margin-left:auto;margin-right:auto">
    <tr>
    <%for(int i=0;i<((int)Math.floor(stopname.size()/9)+1);i++)
    	{%> 
      <td style="text-align: center"><a href="#" onclick="loadExample(<%=i%>); return false">Section <%=i+1%></a></td>
<%} %>
    </tr>
  </table>
  <br />
  <table  class="CSSTableGenerator" style="width:90%;margin-left:auto;margin-right:auto">
  <tr>
    <td>Address: <input type="text" id="address" size="15" onkeypress="return addressKeyHandler(event)"/></td>
    <td style="text-align: center">
      Mode of travel:
      <select id="mode" onchange="updateElevation()">
        <option value="direct">Direct</option>
        <option value="driving">Driving</option>
        <option value="bicycling">Bicycling</option>
        <option value="walking">Walking</option>
      </select>
    </td>
    <td style="text-align: right">
      <input type="button" value="Clear points" onclick="reset()"/>
    </td>
    <td><li class="stopList"><a class="fakebutton" href="home.jsp"> Home Page </a></li>   </td>
    <td><li class="stopList"><a class="fakebutton" href="CDTAstop.jsp"> Stop Page </a></li>   </td>
    <td><li class="stopList"><a class="fakebutton" href="CDTAstatistics.jsp"> Statistics Page </a></li>   </td>
  </tr>
  </table>

    <br /><br />
    <h2 style="text-align:center"> Elevation Along the Routes (256 Samples) </h2>
        <br /><br />
    
   <table id="x" class="CSSTableGenerator" style="width:60%;margin-left:auto;margin-right:auto"></table>
  

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
						