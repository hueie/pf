var global_url = location.host;

		var app = angular.module("myApp", [ "ngRoute", "ngSanitize"]);
		app.config(function($routeProvider, $httpProvider) {
			$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
			$routeProvider.when("/", {
				controller : 'mainController',
				templateUrl : "pf-main.html",
				resolve : { }
			}).when("/dreamers", {
				controller : 'dreamersController',
				templateUrl : "pf-dreamers.html",
				resolve : { }
			}).when("/dreamers-editor/:id", {
				controller : 'dreamerseditController',
				templateUrl : "pf-dreamers-editor.html",
				resolve : { }
			}).when("/treasuremap", {
				controller : 'treasuremapController',
				templateUrl : "pf-treasuremap.html",
				resolve : { }
			}).when("/treasuremap-au", {
				controller : 'treasuremapCountryController',
				templateUrl : "pf-treasuremap-au.html",
				resolve : { }
			}).when("/treasuremap-ca", {
				controller : 'treasuremapCountryController',
				templateUrl : "pf-treasuremap-ca.html",
				resolve : { }
			}).when("/treasuremap-jp", {
				controller : 'treasuremapCountryController',
				templateUrl : "pf-treasuremap-jp.html",
				resolve : { }
			}).when("/treasuremap-us", {
				controller : 'treasuremapCountryController',
				templateUrl : "pf-treasuremap-us.html",
				resolve : { }
			}).when("/chitchatpub", {
				controller : 'chitchatpubController',
				templateUrl : "pf-chitchatpub.html",
				resolve : { }
			}).when("/tortugaisland", {
				controller : 'tortugaislandController',
				templateUrl : "pf-tortugaisland.html",
				resolve : { }
			}).when("/portroyal", {
				controller : 'portroyalController',
				templateUrl : "pf-portroyal.html",
				resolve : { }
			}).when("/atworldsend", {
				controller : 'atworldsendController',
				templateUrl : "pf-atworldsend.html",
				resolve : { }
			}).when("/signin", {
				controller : 'userController',
				templateUrl : "/user/signin.html",
				resolve : { }
			}).when("/signup", {
				controller : 'userController',
				templateUrl : "/user/signup.html",
				resolve : { }
			});
		});
		
		app.controller('navigation', [ '$rootScope', '$scope', '$location','$http',
			function($rootScope, $scope, $location, $http) {
			$scope.logout = function() {
				  $http.get('/user/logout', {})
				  .then(function() {
				    $rootScope.authenticated = false;
				    $location.path("/");
				  },function(data) {
					$location.path("/");
				    $rootScope.authenticated = false;
				  });
			}
		}]);
		app.controller('mainController', [ '$rootScope', '$scope', 
			function($rootScope, $scope) {
			
		}]);
		app.service('dreamersService', ['$http', function($http){
			this.init = function(){
				
			}
			this.getDreamerscommentList = function(dreamers_id, paging){
				return $http.get('/dreamers/DreamerscommentList', {
				    params: { dreamers_id: dreamers_id, paging: paging }
				});
			}
			this.getDreamersList = function(id, paging){
				return $http.get('/dreamers/DreamersList', {
				    params: { id: id, paging: paging }
				});
			}
			this.dreamerslike = function(dreamers_id){
				return $http.get('/dreamers/Dreamerslike', {
				    params: { dreamers_id: dreamers_id }
				});
			}
			this.dreamersdislike = function(dreamers_id){
				return $http.get('/dreamers/Dreamersdislike', {
				    params: { dreamers_id: dreamers_id }
				});
			}
			this.dreamersbookmark = function(dreamers_id){
				return $http.get('/dreamers/Dreamersbookmark', {
				    params: { dreamers_id: dreamers_id }
				});
			}
			this.dreamersdisbookmark = function(dreamers_id){
				return $http.get('/dreamers/Dreamersdisbookmark', {
				    params: { dreamers_id: dreamers_id }
				});
			}
			this.addDreamersComment = function(dreamers_id, dreamers_comment){
				var data = $.param({
					dreamers_id: dreamers_id,
					dreamers_comment: dreamers_comment
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/dreamers/DreamerscommentAdd', data, config);
			}
		}]);
		app.controller('dreamersController', [ '$rootScope','$scope', '$location','$compile', 'dreamersService', function($rootScope, $scope, $location, $compile, dreamersService) {
			$scope.init = function(){
				var height = $("body").prop("clientHeight");
				$('.old_newspaper').css('min-height', height+'px');

				$scope.paging = 0;
				$scope.id = 0;
				this.getDreamersList();
				console.log('Loading dreamers');
			}
			$scope.addDreamersComment = function(dreamers_id){
				if($rootScope.authenticated){
					$scope.current_dreamers_id = dreamers_id;
					console.log("Add Dreamers Comment : "+dreamers_id);
					var dreamers_comment = $("#dreamerscomment_"+dreamers_id).val();
					dreamersService.addDreamersComment(dreamers_id, dreamers_comment)
					.then(function (response){
						$("#dreamerscomment_"+$scope.current_dreamers_id).val("");
			        	$scope.getDreamerscommentList($scope.current_dreamers_id, 0);
					},function (error){
						alert('something went wrong!!!');
					});
				}else{
					alert("로그인을 해주세요.");
				    $location.path("/signin");
				}
				
			}
			$scope.getDreamerscommentList = function(dreamers_id, paging){
				$scope.current_dreamers_id = dreamers_id;
				dreamersService.getDreamerscommentList(dreamers_id, paging)
				.then(function (response) {
					var obj = response.data;// objs.data;
		        	var html = "";
		        	for(var idx in obj){
		        		html += obj[idx].dreamers_comment + "<br>";
		        	}
		        	$("#dreamerscommentlist_"+$scope.current_dreamers_id).html(html);
		        	//var el = document.getElementById('dreamerscommentlist_'+$scope.current_dreamers_id);
	                //angular.element(el).append( $compile(html)($scope) );
	                
				},function (error){
					alert('something went wrong!!!');
				});
			}
			$scope.getDreamersList = function(){
				dreamersService.getDreamersList($scope.id, $scope.paging)
				.then(function (response) {
					$scope.paging = $scope.paging + 1;
		        	if($scope.paging == 1){
		            	$("#list").html("");
		        	} 
		        	var obj = response.data;// objs.data;
		        	var html = "";
		        	var el = document.getElementById('list');
	                for(var idx in obj){
		        		var myContent = obj[idx].content;
		                var substr = myContent;
		                html += "<div class='well'>";
		                html += "<div style='background-color:white;'>" + substr + "</div>";
		                html += "<div>";
		                html += "<div id='like_"+obj[idx].id+"'";
		                if(obj[idx].like_checked == 1){
		                	html += "ng-click='dreamerslike("+obj[idx].id+")' class='like_red_32'";
		                } else{
		                	html += "ng-click='dreamerslike("+obj[idx].id+")' class='like_black_32'";
		                }
		                html += " style='margin:5px;cursor: pointer;'></div>";
		                html += "<span id='like_text_"+obj[idx].id+"'>"+obj[idx].like_cnt+"</span>";
		                //html += "<a href='#'><div class='chat_black_32' style='margin:10px 0px;'></div></a>";
		                
		                html += "<a href='#!/dreamers-editor/"+obj[idx].id+"' ng-show='authenticated'><div class='edit_black_32' style='margin:5px; float:right;'></div></a>";
		                
		                html += "<div id='bookmark_"+obj[idx].id+"'";
		                if(obj[idx].bookmark_checked == 1){
		                	html += "ng-click='dreamersbookmark("+obj[idx].id+")' class='bookmark_red_32'";
		                } else{
		                	html += "ng-click='dreamersbookmark("+obj[idx].id+")' class='bookmark_black_32'";
		                }
		                html += " style='margin:5px;cursor: pointer; float:right;'></div>";
		                html += "<span id='bookmark_text_"+obj[idx].id+"' style='float:right'>"+obj[idx].bookmark_cnt+"</span>";
		                
		                html += "</div>";
		                
		                //Commoent Div Start
		                html += "<div id='dreamerscommentlist_"+obj[idx].id+"'>";
		                for( var idx2 in obj[idx].dreamerscomment_list){
		                	html += obj[idx].dreamerscomment_list[idx2].dreamers_comment + "<br>";
		                }
		                html += "</div>";
		                //Commoent Div End

		                html += "<div class='input-group'>";
		                html += "<textarea id='dreamerscomment_"+obj[idx].id+"' name='dreamerscomment_"+obj[idx].id+"' rows='1' cols='' class='form-control '  placeholder='Reply!'></textarea>";
		                html += "<duv class='input-group-addon' ng-click='addDreamersComment("+obj[idx].id+")' style='vertical-align:bottom;cursor: pointer;'><div class='chat_black_16' style='margin:0px;'></div></div>";
		                html += "</div>";
		                html += "</div>";
		                
		        	}
	                angular.element(el).append( $compile(html)($scope) );
	                
		        	if(obj.length < 5){
		        		$("#morebtn").css("display", "none");
		        	} else{
		            	$("#morebtn").css("display", "block");
		        	}
				},function (error){
					alert('something went wrong!!!');
				});
				for(var idx in $scope.dreamers_ids){
					this.getDreamerscommentList($scope.dreamers_ids[idx],0);
				}
				
			}
			
			$scope.dreamerslike = function(dreamers_id){
				if($rootScope.authenticated){
					var className = $("#like_"+dreamers_id).attr('class');
					if(className == "like_black_32"){
						dreamersService.dreamerslike(dreamers_id)
						.then(function (response) {
							$("#like_"+dreamers_id).removeClass( "like_black_32" ).addClass( "like_red_32" );
				        	var cnt = $("#like_text_"+dreamers_id).text();
				        	cnt = parseInt(cnt) +1;
				        	$("#like_text_"+dreamers_id).text(cnt);
						},function (error){
							alert('something went wrong!!!');
						});
					}else{
						dreamersService.dreamersdislike(dreamers_id)
						.then(function (response) {
							$("#like_"+dreamers_id).removeClass( "like_red_32" ).addClass( "like_black_32" );
				        	var cnt = $("#like_text_"+dreamers_id).text();
				        	cnt = parseInt(cnt) -1;
				        	$("#like_text_"+dreamers_id).text(cnt);
						},function (error){
							alert('something went wrong!!!');
						});
					}
				} else{
					alert("로그인을 해주세요.");
				    $location.path("/signin");
				}
			}
			
			$scope.dreamersbookmark = function(dreamers_id){
				if($rootScope.authenticated){
					var className = $("#bookmark_"+dreamers_id).attr('class');
					if(className == "bookmark_black_32"){
						dreamersService.dreamersbookmark(dreamers_id)
						.then(function (response) {
							$("#bookmark_"+dreamers_id).removeClass( "bookmark_black_32" ).addClass( "bookmark_red_32" );
				        	var cnt = $("#bookmark_text_"+dreamers_id).text();
				        	cnt = parseInt(cnt) +1;
				        	$("#bookmark_text_"+dreamers_id).text(cnt);
						},function (error){
							alert('something went wrong!!!');
						});
					}else{
						dreamersService.dreamersdisbookmark(dreamers_id)
						.then(function (response) {
							$("#bookmark_"+dreamers_id).removeClass( "bookmark_red_32" ).addClass( "bookmark_black_32" );
				        	var cnt = $("#bookmark_text_"+dreamers_id).text();
				        	cnt = parseInt(cnt) -1;
				        	$("#bookmark_text_"+dreamers_id).text(cnt);
						},function (error){
							alert('something went wrong!!!');
						});
					}
				} else{
					alert("로그인을 해주세요.");
					$location.path("/signin");
				}
			}
		}]);
		
		app.service('dreamerseditService', ['$http', function($http){
			this.init = function(){
				
			}
			this.getContent = function(id){
				return $http.get('/dreamers/Dreamersedit', {
				    params: { id: id }
				});
			}
			this.publish_article = function(id, content){
				var data = $.param({
					"id" : id,
					"content" : content
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/dreamers/DreamersAddContent', data, config);
			}
			this.delete_article = function(id){
				return $http.get('/dreamers/DreamersDelContent', {
				    params: { id: id }
				});
			}
		}]);
		app.controller('dreamerseditController', [ '$rootScope','$location', '$scope', '$route', '$routeParams', 'dreamerseditService',
			function($rootScope, $location, $scope, $route, $routeParams, dreamerseditService) {
			var editor = null;
			$scope.init = function(){
				$scope.id = $routeParams.id;
				var height = $("body").prop("clientHeight");
				$('.old_newspaper').css('min-height', height+'px');
					
				editor = new MediumEditor('#content');
			    $('#content').mediumInsert({
			        editor: editor,
			        addons: {
	                    	images: {
		                	fileUploadOptions: { url: '/dreamers/DreamersUpload' }
		            	}
			    	}
				});
			    editor.subscribe('editableInput', function (event, editorElement) {
			    	$scope.content = editor.getContent();
			    });
			    
				this.getContent();
			}
			$scope.publish_article = function(){
				//alert("111 : "+$scope.content);
				if($scope.content!=''){
					dreamerseditService.publish_article($scope.id, $scope.content)
					.then(function (response) {
					    $location.path("/dreamers");
					},function (error){
						alert('something went wrong!!!');
					});
				}else{
				    $location.path("/dreamers");
				}
			}
			$scope.delete_article = function(){
				var id = $scope.id;
				dreamerseditService.delete_article(id)
				.then(function (response) {
				    $location.path("/dreamers");
				},function (error){
					alert('something went wrong!!!');
				});
			}
			
			$scope.getContent = function(){
				console.log('scope.id : ' + $scope.id);
				if($scope.id != 0){
					dreamerseditService.getContent($scope.id)
					.then(function (response) {
			            data = response.data;
			            //console.log(data.content);
			            $scope.content = data.content;
			            $("#content").html($scope.content);
					},function (error){
						alert('something went wrong!!!');
					});
				}
			}	
		}]);
		
		
		app.service('treasuremapService', ['$http', function($http){
			this.init = function(){
				
			}
			this.getCountry = function(x){
				return $http.get('/treasuremap/country');
			}
		}]);
		app.controller('treasuremapController', [ '$rootScope', '$scope', 'treasuremapService', 
			function($rootScope, $scope, treasuremapService) {
			$scope.init = function(){
				var height = $("body").prop("clientWidth")/3*2-30;
				$('.old_worldmap').css('min-height', height+'px');
				console.log('Loading treasuremap');
				this.getCountry();
			}
			$scope.getCountry = function(){
				treasuremapService.getCountry()
				.then(function (response) {
					$scope.countrylist = response.data;
				},function (error){
					alert('something went wrong!!!');
				});
			}
			
		}]);
		
		app.service('treasuremapCountryService', ['$http', function($http){
			this.init = function(){
				
			}
			this.getCountryInfos = function(countrycode){
				return $http.get('/treasuremap/'+countrycode);
			}
			this.getinfo = function(countrycode, name){
				return $http.get('/test/getok');
			}
			this.httpTestCallBackfunction = function(){
				return $http.get('/test/getok');
			}
		}]);
		app.controller('treasuremapCountryController', [ '$scope', '$compile','treasuremapCountryService', 
			function($scope, $compile, treasuremapCountryService) {
			
			$scope.init = function(countrycode){
				$scope.listDiv="";
				$scope.countrycode = countrycode;
				console.log(countrycode);
				var height = $("body").prop("clientWidth")/3*2-30;
				$('.old_'+countrycode+'_map').css('min-height', height+'px');
				console.log('Loading treasuremap');
				this.getCountryInfos(countrycode);
			}
			$scope.getCountryInfos = function(countrycode){
				treasuremapCountryService.getCountryInfos(countrycode)
				.then(function (response) {
					$scope.topiclist = response.data;
				},function (error){
					alert('something went wrong!!!');
				});
			}
			$scope.getinfo = function(countrycode, type){
				treasuremapCountryService.getinfo(countrycode, type)
				.then(function (response) {
					$scope.listDiv = $scope.listDiv + "<div id='info' class='col-lg-12 text-center well' ><h1>VISA</h1></div>";
					
					if($('#info').css('display') == 'none'){
						$('#info').css('display','block');
					} else {
						//$('#info').css('display','none');
					}
				},function (error){
					alert('something went wrong!!!');
				});
			}
			
		}]);

		
		app.service('chitchatpubService', ['$http', function($http){
			this.init = function(){
				
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
		app.controller('chitchatpubController', [ '$rootScope', '$scope', 'chitchatpubService',
			function($rootScope, $scope, chitchatpubService) {
			$scope.mapinit = function(){
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

		        	var obj = response.data;// objs.data;
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
		        	if($scope.paging == 1){
		            	$("#list").html(html);
		        	} else {
		            	$("#list").append(html);
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
		app.controller('tortugaislandController', [ '$rootScope', '$scope',
			function($rootScope, $scope) {
			
		}]);
		app.controller('portroyalController', [ '$rootScope', '$scope', '$location',
			function($rootScope, $scope, $location) {
			
		}]);
		app.controller('atworldsendController', [ '$rootScope', '$scope', '$location',
			function($rootScope, $scope, $location) {
			var ws = null;
			
			$scope.init = function(){
				if ($rootScope.authenticated) {
					this.connector();
		        } else {
					alert("로그인을 해주세요.");
		        	$location.path("/signin");
		        }
			}
			$scope.sendText = function(){
				ws.send($("#wsText").val());
				$("#wsText").val("");
			}
			$scope.myEnterPress = function(keyEvent) {
				if (keyEvent.which === 13){
					this.sendText();
				}
			}
			$scope.connector = function(){
				var tmpurl = "ws://"+global_url+"/chatroom";
				//alert(tmpurl);
				ws = new WebSocket(tmpurl);
				ws.onopen = function() {
					//alert("Connection Open");
					$("#chatdata").append("열결됨");
					$("#connect").css("display","none");
					$("#send").css("display","inline");
				};
				ws.onmessage = function(e) {
					var data = e.data;
					var sessionusername = null;
					var message = null;
					
					var strArray = data.split('|');
					
					/*
					for(var i=0; i< strArray.length; i++){
						console.log('str['+i+']: '+strArray[i]);
					}
					*/
					
					sessionusername = strArray[0];
					message=strArray[1];
					
					var printHTML="";
					if(sessionusername == 'add'){
						var tmpmsg = message.replace("@", "_");
						tmpmsg = tmpmsg.replace(".", "_");
						printHTML += "<div id='"+tmpmsg+"'>";
						printHTML += message
						printHTML += "</div>"
						$("#chatmembers").append(printHTML);
					} else if(sessionusername == 'del'){
						var tmpmsg = message.replace("@", "_");
						tmpmsg = tmpmsg.replace(".", "_");
						$("#"+tmpmsg).remove();
					} else if(sessionusername == $rootScope.username){
						printHTML += "<div style='text-align: right;'>";
						//printHTML += "<strong style='background-color:yellow;'>["+sessionusername+"] -> "+message+"</strong>";
						printHTML += "<strong style='border-radius:25px;padding:5px;background:#f5f5dc;'>"+message+"</strong>";
						printHTML += "</div>";
						$("#chatdata").append(printHTML);
					}else{
						printHTML += "<div style='text-align: left;'>";
						printHTML += "<strong style='border-radius:25px;padding:5px;background:white;'>["+sessionusername+"] -> "+message+"</strong>";
						printHTML += "</div>";
						$("#chatdata").append(printHTML);
					}
					//console.log('chatting data: ' + data);
				};
				ws.onclose = function() {
					$("#chatdata").append("열결 끊김");
					//alert("Connection Close");
					$("#connect").css("display","inline");
					$("#send").css("display","none");
				};
				ws.onerror = function(e) {
					$("#chatdata").append("에러 : "+e.data);
					//alert(e.data);
				}
			}
		}]);
		
		

		app.service('userService', ['$http', function($http){
			this.init = function(){
				
			}
			this.signup = function(username, password, nickname){
				var data = $.param({
					username: username, password: password, nickname: nickname
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/user/Signup', data, config);
			}
			
		}]);
		app.controller('userController', [ '$rootScope', '$scope', '$http', '$location', 'userService',
			function($rootScope, $scope, $http, $location, userService) {
			$scope.init = function(){

			}
			var authenticate = function(credentials, callback) {

			    var headers = credentials ? {authorization : "Basic "
			        + btoa(credentials.username + ":" + credentials.password)
			    } : {};

			    $http.get('/user/login', {headers : headers})
			    .then(function(response) {
			    	  $rootScope.currentuser = response.data;
			      if ($rootScope.currentuser.username) {
			    	  $rootScope.username = $rootScope.currentuser.username;
			    	  $rootScope.authenticated = true;
			      } else {
			    	  $rootScope.authenticated = false;
			      }
			      callback && callback();
			    }, function() {
			    	alert("계정을 확인해주세요");
			    	$rootScope.authenticated = false;
			    	callback && callback();
			    });
			}

			//authenticate();
			$scope.credentials = {};
			$scope.login = function() {
			    authenticate($scope.credentials, function() {
			    	if ($rootScope.authenticated) {
			        	$location.path("/");
			        	$scope.error = false;
			        } else {
			        	$location.path("/signin");
			        	$scope.error = true;
			        }
			    });
			};
			$scope.signup = function(){
				var username = $("#username").val();
				var password = $("#password").val();
				var nickname = $("#nickname").val();
				
				userService.signup(username, password, nickname)
				.then(function (response) {
					$scope.login();
					alert('success');
				},function (error){
					alert('something went wrong!!!');
				});
			}
		}]);
		
		