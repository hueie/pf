function initAutocomplete() {
	var map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : -33.8688,
			lng : 151.2195
		},
		zoom : 13,
		mapTypeId : 'roadmap'
	});

	// Create the search box and link it to the UI element.
	
	var searchedmapplace = document.getElementById('searchedmapplace');
	var placename = document.getElementById('placename');
	var placelocation = document.getElementById('placelocation');
	var input = document.getElementById('pac-input');
	var searchBox = new google.maps.places.SearchBox(input);
	
	
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

	// Bias the SearchBox results towards current map's viewport.
	map.addListener('bounds_changed', function() {
		searchBox.setBounds(map.getBounds());
	});

	var markers = [];
	// Listen for the event fired when the user selects a prediction and
	// retrieve
	// more details for that place.
	searchBox.addListener('places_changed', function() {
		var places = searchBox.getPlaces();

		if (places.length == 0) {
			placelocation.value = "";
			placename.value  = "";
			searchedmapplace.innerHTML  = "Search A Place First";

			$('#paging').val(0);
			getChitChatpubList();			
			return;
		}

		// Clear out the old markers.
		markers.forEach(function(marker) {
			marker.setMap(null);
		});
		markers = [];

		// For each place, get the icon, name and location.
		var bounds = new google.maps.LatLngBounds();
		places.forEach(function(place) {
			if (!place.geometry) {
				console.log("Returned place contains no geometry");
				return;
			}
			var icon = {
				url : place.icon,
				size : new google.maps.Size(71, 71),
				origin : new google.maps.Point(0, 0),
				anchor : new google.maps.Point(17, 34),
				scaledSize : new google.maps.Size(25, 25)
			};

			// Create a marker for each place.
			markers.push(new google.maps.Marker({
				map : map,
				icon : icon,
				title : place.name,
				position : place.geometry.location
			}));
			
			placelocation.value = place.geometry.location;
			placename.value  = input.value;
			searchedmapplace.innerHTML  = input.value;
			
			if (place.geometry.viewport) {
				// Only geocodes have viewport.
				bounds.union(place.geometry.viewport);
			} else {
				bounds.extend(place.geometry.location);
			}
		});
		
		$('#paging').val(0);
		getChitChatpubList();
		map.fitBounds(bounds);
	});
}


function addComment(){
	var placename = $("#placename").val();
	var placelocation = $("#placelocation").val();
	var placecomment = $("#placecomment").val();
	var paging = $("#paging").val();
	
	$.ajax({
        type: "post",
        url: "/chitchatpub/ChitchatpubAddComment",
        data: {
        	"placename" : placename,
        	"placelocation" : placelocation,
        	"placecomment" : placecomment
        	},
        success: function(msg){
            alert("Chit! - Chat!");
    		$('#paging').val(0);
            getChitChatpubList();
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}

function getChitChatpubList(){
	var placelocation = $("#placelocation").val();
	var paging = $("#paging").val();
	
	$.ajax({
        type: "get",
        url: "/chitchatpub/ChitchatpubList",
        data: {
        	"placelocation" : placelocation,
        	"paging" : paging
        	},
        success: function(data, textStatus, xhr){
        	var p = parseInt(paging) + 1;
        	$("#paging").val(p);

        	var obj = data;// objs.data;
        	var html = "";
        	for(var idx in obj){
        		html += "<div class='well'>";
        		html += "<p>"+obj[idx].placename + "</p>"; 
        		html += "<p>"+obj[idx].placecomment + "</p>";
        		html += "</div>"; 
        	}
        	if(obj.length < 5){
        		$("#morebtn").css("display", "none");
        	} else{
            	$("#morebtn").css("display", "block");
        	}
        	if(p == 1){
            	$("#list").html(html);
        	} else {
            	$("#list").append(html);
        	}
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}
