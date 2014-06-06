
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
			center : new google.maps.LatLng(38.09, -97.71),
			zoom : 4,
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

			<br />
			<div id="stopList">
				<h3 class="leagueName">Agency List</h3>
				<br />
				<li class="stopList"><a class="fakebutton" href="transitagency.jsp">
						Austin, TX ---- Captial Metro </a></li> <br />
				
				<li class="stopList"><a class="fakebutton" href="cdtaagency.jsp">
						Albany, NY ---- CDTA </a></li> <br />

		<li class="stopList"><a class="fakebutton" href="CDTAgtfsagency.jsp">
						CDTA ---- Six Routes </a></li> <br />
			</div>

		</div>

		<div id="mapDiv">
			<form action="resume.jsp">
				<input id="overviewButton" type="submit" value="Resume">
			</form>

			<div id="map-canvas"></div>

		</div>
		
        <footer>
                   <p> The source code of this project is available in the <a href="source_code_page/SourceCode.html">source code website</a>.   </p>
        
            <p>
                <br /> Tianchi Zhang, Department of Informatics, University at
                Albany, State University of New York <br /></p>
                     <p>If you have any questions, please contact author by <a href="mailto:ztc.sky@gmail.com">
  ztc.sky@gmail.com</a>. <br /><em>(This site is
                    only for author's dissertation )</em></p>
        </footer>
</body>
</html>