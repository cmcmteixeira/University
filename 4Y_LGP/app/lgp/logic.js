var earthOpened = false;
var earth, earthOcclusion, earthIndicators, metaioMan;

arel.sceneReady(function()
{
	


	console.log("sceneReady");


	 var socketInfo;

        var div = document.getElementById('socketTest');
        div.innerHTML = "div encontrado";

        // Host we are connecting to
                var host = 'localhost:4040';
                // Port we are connecting on
                var port = 4444;

                socket= new WebSocket(host);
                socket.onopen= function() {
                    div.innerHTML = "Open    ";
                };
                socket.onmessage= function(s) {
                    div.innerHTML = div.innerHTML + s;
                };





	//set a listener to tracking to get information about when the image is tracked
	arel.Events.setListener(arel.Scene, trackingHandler);

	// Check initial state of tracking
	arel.Scene.getTrackingValues(function(trackingValues)
	{
		if (trackingValues.length == 0)
		{
			document.getElementById('info').style.display = "block";
		}
	});

	var Scale = new arel.Vector3D(11.0, 11.0, 11.0);
	var Rotation = new arel.Rotation();
	Rotation.setFromEulerAngleDegrees(new arel.Vector3D(90.0, 0.0, 0.0));

	// get earth model reference
	earth = arel.Object.Model3D.create("1", "/storage/emulated/0/lgp/Earth.zip");
	earth.setVisibility(true);
	earth.setCoordinateSystemID(1);
	earth.setScale(Scale);
	earth.setRotation(Rotation);
	arel.Scene.addObject(earth);
	earth.setPickingEnabled(true);
	
	
	// get metaioman model reference
	
	
	metaioMan = arel.Object.Model3D.create("4", "/storage/emulated/0/lgp/metaioman.md2");
	metaioMan.setVisibility(true);
	metaioMan.setCoordinateSystemID(2);
	metaioMan.setScale(Scale);
	arel.Scene.addObject(metaioMan);
	
	/*
	metaioMan = arel.Object.Model3D.createFromMovie("4", "/storage/emulated/0/lgp/video.3gp");
	metaioMan.setVisibility(true);
	metaioMan.setCoordinateSystemID(2);
	arel.Scene.addObject(metaioMan);
	*/
	

	// add a handler for the onTouchStarted event
	earth.onTouchStarted = function( param )
	{
		if (!earthOpened)
		{
			arel.Scene.getObject("1").startAnimation("Open");
			arel.Scene.getObject("3").startAnimation("Grow");
			earthOpened = true;
		}
		else
		{
			arel.Scene.getObject("1").startAnimation("Close");
			arel.Scene.getObject("3").startAnimation("Shrink");
			earthOpened = false;
		}
	}

	// get earth occlusion model reference
	earthOcclusion = arel.Object.Model3D.create("2", "/storage/emulated/0/lgp/Earth_Occlusion.zip");
	earthOcclusion.setVisibility(true);
	earthOcclusion.setCoordinateSystemID(1);
	earthOcclusion.setScale(Scale);
	earthOcclusion.setRotation(Rotation);
	earthOcclusion. setOccluding(true);
	arel.Scene.addObject(earthOcclusion);
	earthOcclusion.setPickingEnabled(true);

	// get earth indicators model reference
	earthIndicators = arel.Object.Model3D.create("3", "/storage/emulated/0/lgp/EarthIndicators.zip");
	earthIndicators.setVisibility(true);
	earthIndicators.setCoordinateSystemID(1);
	earthIndicators.setScale(Scale);
	earthIndicators.setRotation(Rotation);
	arel.Scene.addObject(earthIndicators);
	earthIndicators.setPickingEnabled(true);
});

function trackingHandler(type, param)
{

	//check if there is tracking information available
	if (param[0] !== undefined)
	{
		//if the pattern is found, hide the information to hold your phone over the pattern
		if (type == arel.Events.Scene.ONTRACKING && param[0].getState() == arel.Tracking.STATE_TRACKING)
		{
			document.getElementById('info').style.display = "none";
		}
		//if the pattern is lost tracking, show the information to hold your phone over the pattern
		else if (type == arel.Events.Scene.ONTRACKING && param[0].getState() == arel.Tracking.STATE_NOTTRACKING)
		{
			document.getElementById('info').style.display = "block";
		}
	}
};

function clickHandler()
{
	arel.Scene.setTrackingConfiguration("TrackingData_MarkerlessFast.xml");
}
