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
				resolve : {
					"myDreamersedit": function( ) {
				        return {
							foo : function(dreamerseditService, id) {
								console.log('Resolve id : ' + id);
								$("#id").val(id);
					            return dreamerseditService.getFoo(id);
					        },
							init : function() {
								var height = $("body").prop("clientHeight");
								$('.old_newspaper').css('min-height', height+'px');
									
								var editor = new MediumEditor('.editable');
							    $('.editable').mediumInsert({
							        editor: editor,
							        addons: {
		  		                    	images: {
						                	fileUploadOptions: { url: '/dreamers/DreamersUpload' }
						            	}
							    	}
								});
								//var allContents = editor.serialize();
								//var elContent = allContents["element-0"].value;
									
								$("#publish_article").click(function (){
									var content = editor.getContent();
									var id = $("#id").val();
									if(content != ''){
										$.ajax({
											type: "post",
											url: "/dreamers/DreamersAddContent",
											data: {
												"id" : id,
												"content" : content
											},
											success: function(msg){
												var height = $("body").prop("clientHeight");
												$('.old_newspaper').css('min-height', height+'px');
												$('#paging').val(0);
												$("#id").val(0);
									            getDreamersList();
												console.log('Loading dreamers');
											},
											error:function (xhr, ajaxOptions, thrownError){
												alert(xhr.status);
											    alert(thrownError);
											} 
										});
									}
								});
								$("#delete_article").click(function (){
									var id = $("#id").val();
									if(content != ''){
										$.ajax({
											type: "post",
											url: "/dreamers/DreamersDelContent",
											data: {
												"id" : id
											},
											success: function(msg){
												var height = $("body").prop("clientHeight");
												$('.old_newspaper').css('min-height', height+'px');
												$('#paing').val(0);
												$("#id").val(0);
									            getDreamersList();
												console.log('Loading dreamers');
											},
											error:function (xhr, ajaxOptions, thrownError){
												alert(xhr.status);
											    alert(thrownError);
											} 
										});
									}
								});
								console.log('Loading dreamers');
							}
				        }
				    }
				}
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
			}).when("/treasuremap-view", {
				controller : 'treasuremapController',
				templateUrl : "pf-treasuremap-view.html",
				resolve : {
					init : function() {
						return function() {
							var height = $("body").prop("clientHeight");
							$('.old_newspaper').css('min-height', height+'px');
							
							getDreamersList();
							console.log('Loading treasuremap');
						}
					}
				}
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
		
		app.controller('navigation', [ '$rootScope', '$scope', '$http',
			function($rootScope, $scope, $http) {
			$scope.logout = function() {
				  $http.post('/user/Signout', {})
				  .then(function() {
				    $rootScope.authenticated = false;
				    $location.path("/");
				  },function(data) {
				    $rootScope.authenticated = false;
				  });
				}
		}]);
		app.controller('mainController', [ '$scope', 
			function($scope) {
			
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
		app.controller('dreamersController', [ '$scope', '$compile', 'dreamersService', function($scope, $compile, dreamersService) {
			$scope.init = function(){
				var height = $("body").prop("clientHeight");
				$('.old_newspaper').css('min-height', height+'px');
				
				this.getDreamersList();
				console.log('Loading dreamers');
			}
			$scope.addDreamersComment = function(dreamers_id){
				console.log("Add Dreamers Comment : "+dreamers_id);
				var dreamers_comment = $("#dreamerscomment_"+dreamers_id).val();
				dreamersService.addDreamersComment(dreamers_id, dreamers_comment)
				.then(function (response){
					$("#dreamerscomment_"+dreamers_id).val("");
		        	$scope.getDreamerscommentList(dreamers_id, 0);
				},function (error){
					alert('something went wrong!!!');
				});
			}
			$scope.getDreamerscommentList = function(dreamers_id, paging){
				dreamersService.getDreamerscommentList(id, paging)
				.then(function (response) {
					var obj = data;// objs.data;
		        	var html = "";
		        	for(var idx in obj){
		        		html += obj[idx].dreamers_comment + "<br>";
		        	}
		        	$("#dreamerscommentlist_"+dreamers_id).html(html);
				},function (error){
					alert('something went wrong!!!');
				});
			}
			$scope.getDreamersList = function(){
				var id = $("#id").val();
				var paging = $("#paging").val();
				dreamersService.getDreamersList(id, paging)
				.then(function (response) {
					var p = parseInt(paging) + 1;
		        	if(p == 1){
		            	$("#list").html("");
		        	} 
		        	$("#paging").val(p);
		        	var obj = response.data;// objs.data;
		        	var html = "";
		        	var el = document.getElementById('list');
	                for(var idx in obj){
		        		var myContent = obj[idx].content;
		                var substr = myContent;
		                html += "<div class='well'>";
		                html += "<div style='background-color:white;'>" + substr + "</div>";
		                html += "<div>";
		                html += "<div id='like_"+obj[idx].id+"' ng-click='dreamerslike("+obj[idx].id+")' class='like_black_32' style='margin:5px;cursor: pointer;'></div>";
		                html += "<span id='like_text_"+obj[idx].id+"'>"+obj[idx].like_cnt+"</span>";
		                //html += "<a href='#'><div class='chat_black_32' style='margin:10px 0px;'></div></a>";
		                
		                html += "<a href='#!/dreamers-editor/"+obj[idx].id+"'><div class='edit_black_32' style='margin:5px; float:right;'></div></a>";
		                
		                html += "<div class='label_black_32' style='margin:5px; float:right; cursor: pointer;'></div>";
		                html += "</div>";
		                //Commoent Div Start
		                html += "<div id='dreamerscommentlist_"+obj[idx].id+"'></div>";
		                //Commoent Div End

		                html += "<div class='input-group'>";
		                html += "<textarea id='dreamerscomment_"+obj[idx].id+"' name='dreamerscomment_"+obj[idx].id+"' rows='1' cols='' class='form-control '  placeholder='Reply!'></textarea>";
		                html += "<duv class='input-group-addon' ng-click='addDreamersComment("+obj[idx].id+")' style='vertical-align:bottom;cursor: pointer;'><div class='chat_black_16' style='margin:0px;'></div></div>";
		                html += "</div>";
		                html += "</div>";
		                
		                dreamersService.getDreamerscommentList(obj[idx].id, 0);
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
			}
			
			$scope.dreamerslike = function(dreamers_id){
				console.log(dreamers_id);
				dreamersService.dreamerslike(dreamers_id)
				.then(function (response) {
					$("#like_"+dreamers_id).removeClass( "like_black_32" ).addClass( "like_red_32" );
		        	var cnt = $("#like_text_"+dreamers_id).text();
		        	cnt = parseInt(cnt) +1;
		        	$("#like_text_"+dreamers_id).text(cnt);
				},function (error){
					alert('something went wrong!!!');
				});
			}
		}]);
		
		app.service('dreamerseditService', ['$http', function($http){
			console.log('make service');
			var service={}; 
			service.getFoo = function(id){
				console.log('Service Get Foo : '+id);
				var data = $.param({
	                id: id
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/dreamers/Dreamersedit', data, config);
			}
			return service;
		}]);
		app.controller('dreamerseditController', [ '$scope', '$route', '$routeParams', 'dreamerseditService','myDreamersedit',
			function($scope, $route, $routeParams, dreamerseditService, myDreamersedit) {
				$scope.controllerName = "dreamerseditController";
				$scope.id = $routeParams.id;
				$scope.fooObj = {};
				console.log('scope.id : ' + $scope.id);
				if($scope.id != 0){
					myDreamersedit.foo(dreamerseditService, $scope.id) //dreamerseditService.getFoo($scope.id)
					.then(function (response) {
			            data = response.data;
			            //console.log(data.content);
			            $scope.fooObj = data;
			            $("#content").html(data.content);
					},function (error){
						alert('something went wrong!!!');
					});
					console.log($scope.fooObj);
				}
				//$("#id").val($routeParams.id);
				myDreamersedit.init();
			} ]
		);
		
		
		app.service('treasuremapService', ['$http', function($http){
			this.init = function(){
				
			}
			this.getCountry = function(x){
				return $http.get('/treasuremap/country');
			}
		}]);
		app.controller('treasuremapController', [ '$scope', 'treasuremapService', 
			function($scope, treasuremapService) {
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
			this.getChitChatpubList = function(placelocation, paging){
				return $http.get('/chitchatpub/ChitchatpubList', {
				    params: { placelocation: placelocation, paging: paging }
				});
			}
			this.addComment = function(placename, placelocation, placecomment){
				var data = $.param({
					placename: placename, placelocation: placelocation, placecomment: placecomment
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/chitchatpub/ChitchatpubAddComment', data, config);
			}
		}]);
		app.controller('chitchatpubController', [ '$scope', 'chitchatpubService',
			function($scope, chitchatpubService) {
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
					
					map.fitBounds(bounds);
				});
			}
			$scope.init = function(){
				var height = $("body").prop("clientHeight");
				$('.old_pub').css('min-height', height+'px');
				
				this.mapinit();
				$('#paging').val(0);
				this.getChitChatpubList();
				console.log('Loading chitchatpub');
			}
			$scope.getChitChatpubList = function(){
				var placelocation = $("#placelocation").val();
				var paging = $("#paging").val();
				chitchatpubService.getChitChatpubList(placelocation, paging)
				.then(function (response) {
					var p = parseInt(paging) + 1;
		        	$("#paging").val(p);

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
		        	if(p == 1){
		            	$("#list").html(html);
		        	} else {
		            	$("#list").append(html);
		        	}
				},function (error){
					alert('something went wrong!!!');
				});
			}
			
			$scope.addComment = function(){
				var placename = $("#placename").val();
				var placelocation = $("#placelocation").val();
				var placecomment = $("#placecomment").val();
				var paging = $("#paging").val();
				chitchatpubService.addComment(placename, placelocation, placecomment)
				.then(function (response) {
					alert("Chit! - Chat!");
		    		$('#paging').val(0);
				},function (error){
					alert('something went wrong!!!');
				});
				this.getChitChatpubList();
			}
		}]);
		app.controller('tortugaislandController', [ '$scope',
			function($scope) {
			} ]
		);
		app.controller('portroyalController', [ '$scope',
			function($scope) {
			} ]
		);
		app.controller('atworldsendController', [ '$scope',
			function($scope) {
			} ]
		);
		
		

		app.service('userService', ['$http', function($http){
			this.init = function(){
				
			}
			this.signup = function(email, password, nickname){
				var data = $.param({
					email: email, password: password, nickname: nickname
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
			function($rootScope,$scope, $http, $location, userService) {
			$scope.init = function(){

			}
			var authenticate = function(credentials, callback) {

			    var headers = credentials ? {authorization : "Basic "
			        + btoa(credentials.username + ":" + credentials.password)
			    } : {};

			    $http.get('/user/login', {headers : headers})
			    .then(function(response) {
			      if (response.data.name) {
			    	  $rootScope.username = response.data.name;
			    	  $rootScope.authenticated = true;
			      } else {
			    	  $rootScope.authenticated = false;
			      }
			      callback && callback();
			    }, function() {
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
				var email = $("#email").val();
				var password = $("#password").val();
				var nickname = $("#nickname").val();
				
				userService.signup(email, password, nickname)
				.then(function (response) {
					$scope.login();
					alert('success');
				},function (error){
					alert('something went wrong!!!');
				});
			}
		}]);
		
		