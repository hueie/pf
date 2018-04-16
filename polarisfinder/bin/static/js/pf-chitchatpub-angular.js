		app.service('followService', ['$http', function($http){
			this.init = function(){
				
			}
			this.getFollowing = function(paging){
				return $http.get('/follow/getFollowing', {
				    params: { paging: paging }
				});
			}
			this.setFollowing = function(id){
				return $http.get('/follow/setFollowing', {
				    params: { following_user_id: id }
				});
			}
		}]);

		
		app.service('chitchatpubService', ['$http', function($http){
			this.init = function(){
				
			}
			this.chitchatpubstar = function(id, starcnt){
				return $http.get('/chitchatpub/Chitchatpubstar', {
				    params: { id, id, starcnt: starcnt }
				});
			}
			
			this.getChitChatpubList = function(placelatitude, placelongitude, paging){
				return $http.get('/chitchatpub/ChitchatpubList', {
				    params: { placelatitude, placelongitude, paging: paging }
				});
			}
			this.addComment = function(placename, placelatitude, placelongitude, placecomment){
				var data = $.param({
					placename: placename, placelatitude, placelongitude, placecomment: placecomment
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/chitchatpub/ChitchatpubAddComment', data, config);
			}
		}]);
		app.controller('chitchatpubController', [ '$rootScope', '$scope', '$compile', 'chitchatpubService', 'followService',
			function($rootScope, $scope, $compile, chitchatpubService, followService) {
			$scope.setFollowing = function(id){
				followService.setFollowing(id)
				.then(function (response) {
					alert("팔로우 성공!");
				},function (error){
					alert('something went wrong!!!');
				});
			}
			
			$scope.mapinit = function(){
				var map = new google.maps.Map(document.getElementById('map'), {
					center : {
						lat: -28.024, lng: 140.887 //lat : -33.8688, lng : 151.2195
					},
					zoom : 3,//12
					mapTypeId : 'roadmap'
				});

				// Create the search box and link it to the UI element.
				
				var input = document.getElementById('pac-input');
				var searchBox = new google.maps.places.SearchBox(input);
				
				
				//map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

				// Bias the SearchBox results towards current map's viewport.
				map.addListener('bounds_changed', function() {
					searchBox.setBounds(map.getBounds());
				});

				
				
				var markerCluster = new MarkerClusterer(map, markers,
			            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
				
				var locations = [
			        {lat: -31.563910, lng: 147.154312},
			        {lat: -33.718234, lng: 150.363181},
			        {lat: -33.727111, lng: 150.371124},
			        {lat: -33.848588, lng: 151.209834},
			        {lat: -33.851702, lng: 151.216968},
			        {lat: -34.671264, lng: 150.863657},
			        {lat: -35.304724, lng: 148.662905},
			        {lat: -36.817685, lng: 175.699196},
			        {lat: -36.828611, lng: 175.790222},
			        {lat: -37.750000, lng: 145.116667},
			        {lat: -37.759859, lng: 145.128708},
			        {lat: -37.765015, lng: 145.133858},
			        {lat: -37.770104, lng: 145.143299},
			        {lat: -37.773700, lng: 145.145187},
			        {lat: -37.774785, lng: 145.137978},
			        {lat: -37.819616, lng: 144.968119},
			        {lat: -38.330766, lng: 144.695692},
			        {lat: -39.927193, lng: 175.053218},
			        {lat: -41.330162, lng: 174.865694},
			        {lat: -42.734358, lng: 147.439506},
			        {lat: -42.734358, lng: 147.501315},
			        {lat: -42.735258, lng: 147.438000},
			        {lat: -43.999792, lng: 170.463352}
			      ]
				var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
				var markers = locations.map(function(location, i) {
			          return new google.maps.Marker({
			            position: location,
			            label: labels[i % labels.length]
			          });
			        });
				
				// Listen for the event fired when the user selects a prediction and
				// retrieve
				// more details for that place.
				searchBox.addListener('places_changed', function() {
					var places = searchBox.getPlaces();
					// Clear out the old markers.
					markers.forEach(function(marker) {
						marker.setMap(null);
					});
					//markers = [];

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
						
						//My Source Code
						var latitude = place.geometry.location.lat();
						var longitude = place.geometry.location.lng();  
						$scope.placelatitude = latitude;
						$scope.placelongitude = longitude;
						$scope.placename = input.value;
						
						if (place.geometry.viewport) {
							// Only geocodes have viewport.
							bounds.union(place.geometry.viewport);
						} else {
							bounds.extend(place.geometry.location);
						}
					});
					
					map.fitBounds(bounds);
				});
			}
			
			$scope.mapinit2 = function(){
				var map = new google.maps.Map(document.getElementById('map'), {
					center : {
						lat : -33.8688,
						lng : 151.2195
					},
					zoom : 13,
					mapTypeId : 'roadmap'
				});

				// Create the search box and link it to the UI element.
				
				var input = document.getElementById('pac-input');
				var searchBox = new google.maps.places.SearchBox(input);
				
				
				//map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

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
						
						//My Source Code
						var latitude = place.geometry.location.lat();
						var longitude = place.geometry.location.lng();  
						$scope.placelatitude = latitude;
						$scope.placelongitude = longitude;
						$scope.placename = input.value;
						
						if (place.geometry.viewport) {
							// Only geocodes have viewport.
							bounds.union(place.geometry.viewport);
						} else {
							bounds.extend(place.geometry.location);
						}
					});
					
					map.fitBounds(bounds);
				});
			}
			$scope.init = function(){
				var height = $("body").prop("clientHeight");
				$('.old_pub').css('min-height', height+'px');
				$scope.placelatitude = 0;
				$scope.placelongitude = 0;
				$scope.placename = '';
				$scope.paging = 0;
				this.mapinit();
				this.getChitChatpubList();
				console.log('Loading chitchatpub');
			}
			$scope.setStarOnOff = function(id, starcnt){
				for(var tmpidx = 1; tmpidx <= 5 ; tmpidx++){
        			if(tmpidx <= starcnt){
        				$("#star_"+id+"_"+tmpidx).removeClass( "star_black_16" ).addClass( "star_yellow_16" );
        			}else{
        				$("#star_"+id+"_"+tmpidx).removeClass( "star_yellow_16" ).addClass( "star_black_16" );
        			}
        		}
			}
			$scope.chitchatpubstar = function(id, star_cnt){
				chitchatpubService.chitchatpubstar(id, star_cnt)
				.then(function (response) {
					var el = document.getElementById("star_"+id);
					var html = "";
	        		for(var starcnt = 1; starcnt <= 5 ; starcnt++){
	        			if(starcnt <= star_cnt){
	        				html += "<div id='star_"+id+"_"+starcnt+"' ng-mouseover='setStarOnOff("+id+","+starcnt+")' ng-mouseleave='setStarOnOff("+id+","+star_cnt+")' ng-click='chitchatpubstar("+id+","+starcnt+")' class='star_yellow_16' style='margin:5px;cursor: pointer;'></div>";
	        			}else{
	        				html += "<div id='star_"+id+"_"+starcnt+"' ng-mouseover='setStarOnOff("+id+","+starcnt+")' ng-mouseleave='setStarOnOff("+id+","+star_cnt+")' ng-click='chitchatpubstar("+id+","+starcnt+")' class='star_black_16' style='margin:5px;cursor: pointer;'></div>";
	        			}
	        		}
	        		angular.element(el).empty();
	        		angular.element(el).append( $compile(html)($scope) );
				},function (error){
					alert('something went wrong!!!');
				});
			}
			
			$scope.getChitChatpubList = function(pPaging){
				if(pPaging == 0){
					$scope.paging = pPaging;
				}
				console.log($scope.paging);
				if($("#pac-input").val() == ''){
					$scope.placelatitude=0;
					$scope.placelongitude=0;
				}
				chitchatpubService.getChitChatpubList($scope.placelatitude, $scope.placelongitude, $scope.paging)
				.then(function (response) {
					$scope.paging = $scope.paging + 1;
		        	if($scope.paging == 1){
		            	$("#list").html("");
		        	} 
		        	var obj = response.data;// objs.data;
		        	var html = "";
		        	var el = document.getElementById('list');
					
		        	for(var idx in obj){
		        		html += "<div class='well'>";
		        		html += "<div style='margin-bottom:10px;text-align:left;'>"+obj[idx].user.nickname;
		        		//html += "<span style='float:left;'>"+obj[idx].user.username+"</span>";
		        		html += "<span ng-click='setFollowing("+obj[idx].user_id+")' style='float:right;cursor:pointer;border: 1px solid black; -webkit-border-radius: 4px; border-radius: 4px; padding:2px;'>follow</span>";
		        		html += "</div>";
		        		html += "<div style='font-size:10px;'>"+obj[idx].placename + "</div>"; 
		        		html += "<div>"+obj[idx].placecomment + "</div>";
		        		html += "<div id='star_"+obj[idx].id+"'>";
		        		for(var starcnt = 1; starcnt <= 5 ; starcnt++){
		        			if(starcnt <= obj[idx].star_cnt){
		        				html += "<div id='star_"+obj[idx].id+"_"+starcnt+"' ng-mouseover='setStarOnOff("+obj[idx].id+","+starcnt+")' ng-mouseleave='setStarOnOff("+obj[idx].id+","+obj[idx].star_cnt+")' ng-click='chitchatpubstar("+obj[idx].id+","+starcnt+")' class='star_yellow_16' style='margin:5px;cursor: pointer;'></div>";
		        			}else{
		        				html += "<div id='star_"+obj[idx].id+"_"+starcnt+"' ng-mouseover='setStarOnOff("+obj[idx].id+","+starcnt+")' ng-mouseleave='setStarOnOff("+obj[idx].id+","+obj[idx].star_cnt+")' ng-click='chitchatpubstar("+obj[idx].id+","+starcnt+")' class='star_black_16' style='margin:5px;cursor: pointer;'></div>";
		        			}
		        		}
		        		html += "</div>"; 
		        		html += "</div>"; 
		        	}
		        	if(obj.length < 5){
		        		$("#morebtn").css("display", "none");
		        	} else{
		            	$("#morebtn").css("display", "block");
		        	}
		        	if(html !== ''){
		        		angular.element(el).append( $compile(html)($scope) );
		        	}
		        	
				},function (error){
					alert('something went wrong!!!');
				});
			}
			
			$scope.addComment = function(){
				var placecomment = $("#placecomment").val();
				if($scope.placename !='' && $scope.placelatitude != '' && $scope.placelongitude !='' && placecomment != ''){
					chitchatpubService.addComment($scope.placename, $scope.placelatitude, $scope.placelongitude, placecomment)
					.then(function (response) {
						alert("Chit! - Chat!");
						$scope.getChitChatpubList(0);
					},function (error){
						alert('something went wrong!!!');
					});
					this.getChitChatpubList();
				}else{
					alert("장소를 검색하고 내용을 입력햊주세요.");
				}
				
			}
		}]);