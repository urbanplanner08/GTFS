<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.sql.*" errorPage=""%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
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
							if (count>90) break;
						}
						%>

<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>GTFS Route Calculation </title>
<link href="table.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="style.css" media="all" />

<style>
    #map{
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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script>
    var arr=new Array;
    var b=0;
    function std(arr)
    {
    var n=0;
    var i=0;
    var sum=0;
    var sd=0;
    while (n<arr.length)
    { sum+=arr[n];
    n++;
    }
    var avg=sum/arr.length;
    var sum_sq=0;
    while(i<arr.length)
    {
    sum_sq+= (avg-arr[i])*(avg-arr[i]);
    i++;
    }
    sd=sum_sq/arr.length;
    return sd;
    }
    
       jQuery(function() {
var stops = [
              <%
			int count2 = 0;
			while (count2<count-1) {
			%>
                 ['<%=stopname.get(count2)%>', <%=stoplat.get(count2)%>, <%=stoplog.get(count2)%>, <%=stopseq.get(count2)%>, <%=stopid.get(count2)%>],
 				<%
 				count2++;
			}%>
                 ['<%=stopname.get(count2)%>', <%=stoplat.get(count2)%>, <%=stoplog.get(count2)%>, <%=stopseq.get(count2)%>, <%=stopid.get(count2)%>] 
               ];
            var map = window.map = new window.google.maps.Map(document.getElementById("map"));

            var directionsDisplay = new window.google.maps.DirectionsRenderer({suppressMarkers: true});
   
            var directionsService = new window.google.maps.DirectionsService();

    Tour_startUp(stops);
    window.tour.loadMap(map, directionsDisplay);
    window.tour.fitBounds(map);
    if (stops.length > 1)
        window.tour.calcRoute(directionsService, directionsDisplay);
});

function Tour_startUp(stops) {
    if (!window.tour) window.tour = {
        updateStops: function (newStops) {
            stops = newStops;
        },


        loadMap: function (map, directionsDisplay) {
            var myOptions = {
                zoom: 13,
                center: new window.google.maps.LatLng(42.65, -73.75), // default to London
                mapTypeId: window.google.maps.MapTypeId.ROADMAP
            };
            map.setOptions(myOptions);
            directionsDisplay.setMap(map);
        },
        fitBounds: function (map) {
            var bounds = new window.google.maps.LatLngBounds();
        },
        calcRoute: function (directionsService, directionsDisplay) {
            var batches = [];
            var itemsPerBatch = 10; // google API max = 10 - 1 start, 1 stop, and 8 waypoints
            var itemsCounter = 0;
            var wayptsExist = stops.length > 0;
            while (wayptsExist) {
                var subBatch = [];
                var subitemsCounter = 0;
                for (var j = itemsCounter; j < stops.length; j++) {
                var point = new google.maps.LatLng(stops[j][1],stops[j][2])
                    subitemsCounter++;
                    subBatch.push({
                        location: point,
                        stopover: true
                    });
                    if (subitemsCounter == itemsPerBatch)
                        break;
                }

                itemsCounter += subitemsCounter;
                batches.push(subBatch);
                wayptsExist = itemsCounter < stops.length;
                // If it runs again there are still points. Minus 1 before continuing to
                // start up with end of previous tour leg
                itemsCounter--;
            }

            // now we should have a 2 dimensional array with a list of a list of waypoints
 var combinedResults;
var unsortedResults = [{}]; // to hold the counter and the results themselves as they come back, to later sort
var directionsResultsReturned = 0;

 for (var k = 0; k < batches.length; k++) {
 var lastIndex = batches[k].length - 1;
 var start = batches[k][0].location;
 var end = batches[k][lastIndex].location;

                // trim first and last entry from array
 var waypts = [];
waypts = batches[k];
 waypts.splice(0, 1);
waypts.splice(waypts.length - 1, 1);

                var request = {
                    origin: start,
                    destination: end,
                    waypoints: waypts,
                    travelMode: window.google.maps.TravelMode.WALKING
                };
                (function (kk) {
                    directionsService.route(request, function (result, status) {
                        if (status == window.google.maps.DirectionsStatus.OK) {

 var unsortedResult = { order: kk, result: result };
 unsortedResults.push(unsortedResult);

 directionsResultsReturned++;

 if (directionsResultsReturned == batches.length) // we've received all the results. put to map
                            {
                                // sort the returned values into their correct order
 unsortedResults.sort(function (a, b) { return parseFloat(a.order) - parseFloat(b.order); });
 var count = 0;
       for (var key in unsortedResults) {
 if (unsortedResults[key].result != null) {
   if (unsortedResults.hasOwnProperty(key)) {
    if (count == 0) // first results. new up the combinedResults object
 combinedResults = unsortedResults[key].result;
                                            else {
 // only building up legs, overview_path, and bounds in my consolidated object. This is not a complete
   // directionResults object, but enough to draw a path on the map, which is all I need
combinedResults.routes[0].legs = combinedResults.routes[0].legs.concat(unsortedResults[key].result.routes[0].legs);
combinedResults.routes[0].overview_path = combinedResults.routes[0].overview_path.concat(unsortedResults[key].result.routes[0].overview_path);
combinedResults.routes[0].bounds = combinedResults.routes[0].bounds.extend(unsortedResults[key].result.routes[0].bounds.getNorthEast());
combinedResults.routes[0].bounds = combinedResults.routes[0].bounds.extend(unsortedResults[key].result.routes[0].bounds.getSouthWest());
  }
  count++;
  }
}
}
 directionsDisplay.setDirections(combinedResults);
var legs = combinedResults.routes[0].legs;
  // alert(legs.length);
for (var i=0; i < legs.length;i++){
//var markerletter = "A".charCodeAt(0);
//markerletter += i;
//markerletter = String.fromCharCode(markerletter);
var a=i+1;
createMarker(directionsDisplay.getMap(),legs[i].start_location,"<strong>Stop Sequence: "+a,"Stop Name: "+stops[i][0]+"<br /> Stop Address: "+legs[i].start_address+"</strong>");
document.getElementById(i.toString()).innerHTML+= "<strong>"+ legs[i].distance.text +"</strong>";  
if (parseFloat(legs[i].distance.text)>10)
{b=b+(parseFloat(legs[i].distance.text)/5280);
 arr[i]=parseFloat(legs[i].distance.text)/5280;}
else {b=b+parseFloat(legs[i].distance.text);
arr[i]=parseFloat(legs[i].distance.text);
}

 }
 
document.getElementById("total").innerHTML=b.toFixed(2);
document.getElementById("length").innerHTML=a;
 document.getElementById("avg").innerHTML=(b/arr.length).toFixed(2);
 document.getElementById("sd").innerHTML=(std(arr)).toFixed(2);

 var i=legs.length;
 a=a+1;
createMarker(directionsDisplay.getMap(),legs[legs.length-1].end_location,"<strong> Stop Sequence: "+a,"Stop Name: "+stops[i][0]+"<br /> Stop Address: "+legs[legs.length-1].end_address);
                            }
                        }
                    });
                })(k);
            }
        }
    };
}
var infowindow = new google.maps.InfoWindow(
  { 
    size: new google.maps.Size(150,50)
  });

var icons = new Array();
icons["red"] = new google.maps.MarkerImage("mapIcons/marker_red.png",
      // This marker is 20 pixels wide by 34 pixels tall.
      new google.maps.Size(20, 34),
      // The origin for this image is 0,0.
      new google.maps.Point(0,0),
      // The anchor for this image is at 9,34.
      new google.maps.Point(9, 34));



function getMarkerImage(iconStr) {
   if ((typeof(iconStr)=="undefined") || (iconStr==null)) { 
      iconStr = "red"; 
   }
   if (!icons[iconStr]) {
      icons[iconStr] = new google.maps.MarkerImage("http://www.google.com/mapfiles/marker"+ iconStr +".png",
      // This marker is 20 pixels wide by 34 pixels tall.
      new google.maps.Size(20, 34),
      // The origin for this image is 0,0.
      new google.maps.Point(0,0),
      // The anchor for this image is at 6,20.
      new google.maps.Point(9, 34));
   } 
   return icons[iconStr];

}
  // Marker sizes are expressed as a Size of X,Y
  // where the origin of the image (0,0) is located
  // in the top left of the image.
 
  // Origins, anchor positions and coordinates of the marker
  // increase in the X direction to the right and in
  // the Y direction down.

  var iconImage = new google.maps.MarkerImage('mapIcons/marker_red.png',
      // This marker is 20 pixels wide by 34 pixels tall.
      new google.maps.Size(20, 34),
      // The origin for this image is 0,0.
      new google.maps.Point(0,0),
      // The anchor for this image is at 9,34.
      new google.maps.Point(9, 34));
  var iconShadow = new google.maps.MarkerImage('http://www.google.com/mapfiles/shadow50.png',
      // The shadow image is larger in the horizontal dimension
      // while the position and offset are the same as for the main image.
      new google.maps.Size(37, 34),
      new google.maps.Point(0,0),
      new google.maps.Point(9, 34));
      // Shapes define the clickable region of the icon.
      // The type defines an HTML &lt;area&gt; element 'poly' which
      // traces out a polygon as a series of X,Y points. The final
      // coordinate closes the poly by connecting to the first
      // coordinate.
  var iconShape = {
      coord: [9,0,6,1,4,2,2,4,0,8,0,12,1,14,2,16,5,19,7,23,8,26,9,30,9,34,11,34,11,30,12,26,13,24,14,21,16,18,18,16,20,12,20,8,18,4,16,2,15,1,13,0],
      type: 'poly'
  };


function createMarker(map, latlng, label, html) {
//alert("createMarker("+latlng+","+label+","+html+","+color+")");
    var contentString = '<b>'+label+'</b><br>'+html;
    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        shadow: iconShadow,
        shape: iconShape,
        title: label,
        zIndex: Math.round(latlng.lat()*-100000)<<5
        });
        marker.myname = label;

    google.maps.event.addListener(marker, 'click', function() {
        infowindow.setContent(contentString); 
        infowindow.open(map,marker);
        });
    return marker;
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
    <br /> <br />
    <div id="contentContainer" >

  <div   id="map"  ></div>
  
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<br/>
<div style="float:right;margin-right:7%">

				<li class="stopList"><a class="fakebutton" href="home.jsp">
						Home Page </a></li>  
						<br />
						<li class="stopList"><a class="fakebutton" href="CDTAelevation.jsp">
						Elevation Page</a></li> <br />
</div>
<h3 class="leagueName" style="margin-left: 8%">Route <%=session.getAttribute("routeid").toString()%> </h3> <br />
<h4 style="margin-left: 8%; color:grey">
<i><%=session.getAttribute("direction").toString()%> Path Distance and Absolute Distance<br /></i></h4>

<br />
<br />
 <table class="CSSTableGenerator" style="width:50%;margin-left:auto;margin-right:auto">
 <caption>Descriptive Statistics of Path Distance(Mile)</caption>
        <tr>
            <th>Total Length</th>
            <th>Total Path Sections </th>
            <th> Mean</th>
            <th>Standard Deviation</th>
        </tr>
        <tr>
            <th id="total"></th>
            <th id="length"></th>
            <th id="avg"></th>
            <th id="sd"></th>
        </tr>
</table>       
 
<br /> 
<table class="CSSTableGenerator" style="width:90%;margin-left:auto;margin-right:auto">
<caption>Station to Station Path Distance and Absolute Distance </caption>
        <tr>
            <th> Section Number</th>
            <th>Start Station</th>
            <th>End Station</th>
            <th>Route Distance</th>
            <th>Absolute Distance </th>
        </tr>
        <% int m=1000; 
        for (int i=0;i<stopid.size()-1;i++)
        	{
        	%>
        <tr> <td style="text-align:center"><%=i+1 %></td>
            <td><%=stopname.get(i) %></td>
            <td><%=stopname.get(i+1) %></td>
            <td id="<%=Integer.toString(i)%>" style="text-align:center"></td>
            <td id="<%=Integer.toString(m)%>" style="text-align:center"> </td>
        </tr>  
        <%
        m++;
        }%>  
</table>        
 
     <script type="text/javascript">
    google.maps.LatLng.prototype.distanceFrom = function(newLatLng) {
        if (newLatLng==undefined) return false;

        var dLat = (newLatLng.lat()-this.lat()) * Math.PI / 180;
        var dLon = (newLatLng.lng()-this.lng()) * Math.PI / 180;
        var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(this.lat() * Math.PI / 180 ) * Math.cos(newLatLng.lat() * Math.PI / 180 )* Math.sin(dLon/2) * Math.sin(dLon/2);
        return 6371000 * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
      }
      
    $(function(){
		var map = window.map;
        var image='http://maps.google.com/mapfiles/ms/icons/green-dot.png';
        var n=1000;
        <%for (int i=0;i<stopname.size()-1;i++)
        { %>

        var marker = new google.maps.Marker({
            map: map,
            position: new google.maps.LatLng( <%=stoplat.get(i)%>, <%=stoplog.get(i)%>),
            draggable: false,
            raiseOnDrag: false,
            icon: image
          });

          var marker2 = new google.maps.Marker({
            map: map,
            position:new google.maps.LatLng( <%=stoplat.get(i+1)%>, <%=stoplog.get(i+1)%>),
            draggable: false,
            raiseOnDrag: false,
            icon:image
          });
   
                var line = new google.maps.Polyline({
            path: [marker.getPosition(), marker2.getPosition()],
            map: map
          });
          $('#'+n.toString()).html(Math.round(marker.getPosition().distanceFrom(marker2.getPosition())/10)/100 + ' km');
         n++;
      <%}%>
	  
	  });
    </script>
     
 
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