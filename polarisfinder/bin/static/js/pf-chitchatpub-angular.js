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
			this.getMarkers = function(){
				return $http.get('/chitchatpub/getMarkers', {
				    params: { }
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
				if($rootScope.authenticated){
					followService.setFollowing(id)
					.then(function (response) {
						alert("팔로우 성공!");
					},function (error){
						alert('something went wrong!!!');
					});
				}else{
					alert("로그인을 해주세요.");
				    $location.path("/signin");
				}
			}
			
			$scope.mapinit = function(initlat, initlng){
				if(initlat == 0 && initlng== 0){
					initlat = -25.363882;
					initlng = 131.044922;
				}
				$scope.map = new google.maps.Map(document.getElementById('map'), {
					center : {
						lat: initlat, lng: initlng //-28.024, 140.887 / -33.8688,  151.2195
					},
					zoom : 3,//12
					mapTypeId : 'roadmap'
				});
				
				$scope.input = document.getElementById('pac-input');
				$scope.searchBox = new google.maps.places.SearchBox($scope.input);
				
				chitchatpubService.getMarkers()
				.then(function (response) {
					//Data Marker
					//$scope.locations = [];
					console.log($scope.locations);
					//$scope.labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
					$scope.markers = [];
					var obj = response.data;
					for(var tmpi in obj){
						$scope.locations.push({lat: obj[tmpi].placelatitude, lng: obj[tmpi].placelongitude});
					
						console.log($scope.locations[tmpi]);
						var marker = new google.maps.Marker({
				            position: $scope.locations[tmpi],
				        });
						marker.addListener('click', function() {
							$scope.getChitChatpubMarkeredList(0, this, true);
					    });
						$scope.markers.push(marker);
					}
					
					$scope.markerCluster = new MarkerClusterer($scope.map, $scope.markers,
				            {zoomOnClick: false, imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
					
					//마커 눌렀을 때 해당 마커 치챗 가져오는 이벤트
					/*
					google.maps.event.addListener($scope.markerCluster, 'clusterclick', function(cluster) {
						var cls_center = cluster.getCenter();
						var cls_markers = cluster.getMarkers();
						var cls_getSize = cluster.getSize();
						for(var tmpi=0; tmpi<cluster.getSize(); tmpi++){
							console.log("aaa tmpi"+cls_markers[tmpi]);
							//$scope.getChitChatpubMarkeredList(0, cls_markers[tmpi]);
						}
						$scope.map.setCenter(cluster.getCenter());
						$scope.map.setZoom($scope.map.getZoom()+1);
					});
					*/
					ClusterIcon.prototype.triggerClusterClick = function() {
						this.map_.setCenter(this.cluster_.getCenter());
						var cls_markers = this.cluster_.getMarkers();
						var cls_size = this.cluster_.getSize();
						for(var tmpi=0; tmpi<cls_size; tmpi++){
							$scope.getChitChatpubMarkeredList(0, cls_markers[tmpi], false);
						}
						
						var markerClusterer = this.cluster_.getMarkerClusterer();
						google.maps.event.trigger(markerClusterer, 'clusterclick', this.cluster_);

						if (markerClusterer.isZoomOnClick()) {
							// Zoom into the cluster.
							//this.map_.fitBounds(this.cluster_.getBounds());
							// modified zoom in function
							this.map_.setZoom(markerClusterer.getMaxZoom()+1);
						}
						
					};
				},function (error){
					alert('something went wrong!!!');
				});
				
				//Event Listeners
				//map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
				$scope.map.addListener('bounds_changed', function() {
					$scope.searchBox.setBounds($scope.map.getBounds());
				});
				
				// more details for that place.
				$scope.searchBox.addListener('places_changed', function() {
					var places = $scope.searchBox.getPlaces();
					// Clear out the old markers.
					/*
					$scope.markers.forEach(function(marker) {
						marker.setMap(null);
					});
					*/
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
						$scope.markers.push(new google.maps.Marker({
							map : $scope.map,
							icon : icon,
							title : place.name,
							position : place.geometry.location
						}));
						
						//My Source Code
						var latitude = place.geometry.location.lat();
						var longitude = place.geometry.location.lng();  
						$scope.placelatitude = latitude;
						$scope.placelongitude = longitude;
						$scope.placename = $scope.input.value;
						
						if (place.geometry.viewport) {
							// Only geocodes have viewport.
							bounds.union(place.geometry.viewport);
						} else {
							bounds.extend(place.geometry.location);
						}
					});
					
					$scope.map.fitBounds(bounds);
				});
			}
			
			
			$scope.init = function(){
				var height = $("body").prop("clientHeight");
				$('.old_pub').css('min-height', height+'px');
				$scope.placelatitude = 0;
				$scope.placelongitude = 0;
				$scope.placename = '';
				$scope.paging = 0;
				$scope.showComment = false;
				$scope.locations = [];
				this.getChitChatpubList();
				this.mapinit(0,0);
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
				if($rootScope.authenticated){
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
				}else{
					alert("로그인을 해주세요.");
				    $location.path("/signin");
				}
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
		        	
		        	//$scope.locations = [];
		        	
		        	for(var idx in obj){
		        		//$scope.locations.push({lat: obj[idx].placelongitude, lng: obj[idx].placelatitude});
		        		//console.log(obj[idx].placelongitude)
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
			/*
			 * Pending 인자를 markers 로해 한번에 Controller 단으로 던져서 한번에 DB에서 가져와야한다.
			 */
			$scope.getChitChatpubMarkeredList = function(pPaging, pMarker, isCenter){
				if(isCenter==true){
			        $scope.map.setCenter(pMarker.getPosition());
				}
		        console.log(pMarker.getPosition().lat());
				$scope.placelatitude=pMarker.getPosition().lat();
				$scope.placelongitude=pMarker.getPosition().lng();
				
				if(pPaging == 0){
					$scope.paging = pPaging;
				}
				console.log($scope.paging);
				/*
				if($("#pac-input").val() == ''){
					$scope.placelatitude=0;
					$scope.placelongitude=0;
				}
				*/
				chitchatpubService.getChitChatpubList($scope.placelatitude, $scope.placelongitude, $scope.paging)
				.then(function (response) {
					$scope.paging = $scope.paging + 1;
		        	if($scope.paging == 1){
		            	$("#list").html("");
		        	} 
		        	var obj = response.data;// objs.data;
		        	var html = "";
		        	var el = document.getElementById('list');
		        	
		        	$scope.locations = [];
					
		        	for(var idx in obj){
		        		$scope.locations.push({lat: obj[idx].placelongitude, lng: obj[idx].placelatitude});
		        		
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
				if($rootScope.authenticated){
					var placecomment = $("#placecomment").val();
					if($scope.placename !='' && $scope.placelatitude != '' && $scope.placelongitude !='' && placecomment != ''){
						chitchatpubService.addComment($scope.placename, $scope.placelatitude, $scope.placelongitude, placecomment)
						.then(function (response) {
							alert("Chit! - Chat!");
							$("#placecomment").val("");
							$scope.mapinit($scope.placelatitude,$scope.placelongitude);
							$scope.getChitChatpubList(0);
							$scope.showComment = false;
						},function (error){
							alert('something went wrong!!!');
						});
						this.getChitChatpubList();
					}else{
						alert("장소를 검색하고 내용을 입력햊주세요.");
					}
				}else{
					alert("로그인을 해주세요.");
				    $location.path("/signin");
				}
			}
		}]);