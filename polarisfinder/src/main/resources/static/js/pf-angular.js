		var app = angular.module("myApp", [ "ngRoute" ]);
		app.config(function($routeProvider) {
			$routeProvider.when("/", {
				controller : 'mainController',
				templateUrl : "pf-main.html",
				resolve : {
					init : function() {
						return function() {
							//signCheck();
						}
					}
				}
			}).when("/dreamers", {
				controller : 'dreamersController',
				templateUrl : "pf-dreamers.html",
				resolve : {
					init : function() {
						return function() {
							var height = $("body").prop("clientHeight");
							$('.old_newspaper').css('min-height', height+'px');
							
							getDreamersList();
							console.log('Loading dreamers');
						}
					}
				}
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
				resolve : {
					init : function() {
						return function() {
							var height = $("body").prop("clientHeight");
							$('.old_pub').css('min-height', height+'px');
							
							initAutocomplete();
							getChitChatpubList();
							//getCamList();
							console.log('Loading chitchatpub');
						}
					}
				}
			}).when("/tortugaisland", {
				controller : 'tortugaislandController',
				templateUrl : "pf-tortugaisland.html",
				resolve : {
					init : function() {
						return function() {
							//signCheck();
							//getCamList();
							console.log('Loading tortugaisland');
						}
					}
				}
			}).when("/portroyal", {
				controller : 'portroyalController',
				templateUrl : "pf-portroyal.html",
				resolve : {
					init : function() {
						return function() {
							//signCheck();
							//getCamList();
							console.log('Loading portroyal');
						}
					}
				}
			}).when("/atworldsend", {
				controller : 'atworldsendController',
				templateUrl : "pf-atworldsend.html",
				resolve : {
					init : function() {
						return function() {
							//signCheck();
							//getCamList();
							console.log('Loading atworldsend');
						}
					}
				}
			}).when("/signin", {
				controller : 'signinController',
				templateUrl : "/user/signin.html",
				resolve : {
					init : function() {
						return function() {
							console.log('Loading atworldsend');
						}
					}
				}
			}).when("/signup", {
				controller : 'signupController',
				templateUrl : "/user/signup.html",
				resolve : {
					init : function() {
						return function() {
							console.log('Loading atworldsend');
						}
					}
				}
			});
		});
		
		app.controller('mainController', [ '$scope', '$route', 'init',
			function($scope, $route, init) {
				init($route);
			} ]
		);
		app.controller('dreamersController', [ '$scope', '$route', '$routeParams', 'init',
			function($scope, $route, $routeParams, init) {
				init($route);
			} ]
		);
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
				var height = $("body").prop("clientWidth")/3*2-30;
				$('.old_worldmap').css('min-height', height+'px');
				console.log('Loading treasuremap');
			}
			this.getCountry = function(x){
				return $http.get('/treasuremap/country');
			}
		}]);
		app.controller('treasuremapController', [ '$scope', 'treasuremapService', 
			function($scope, treasuremapService) {
				treasuremapService.getCountry()
				.then(function (response) {
					$scope.countrylist = response.data;
				},function (error){
					alert('something went wrong!!!');
				});
				treasuremapService.init();
			}]
		);
		
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
		app.controller('treasuremapCountryController', [ '$scope', 'treasuremapCountryService', 
			function($scope, treasuremapCountryService) {
			$scope.init = function(countrycode){
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
					if($('#info').css('display') == 'none'){
						$('#info').css('display','block');
					} else {
						$('#info').css('display','none');
					}
				},function (error){
					alert('something went wrong!!!');
				});
			}
			
			}]
		);


		
		
		app.controller('chitchatpubController', [ '$scope', '$route', 'init',
			function($scope, $route, init) {
				init($route);
			} ]
		);
		app.controller('tortugaislandController', [ '$scope', '$route', 'init',
			function($scope, $route, init) {
				init($route);
			} ]
		);
		app.controller('portroyalController', [ '$scope', '$route', 'init',
			function($scope, $route, init) {
				init($route);
			} ]
		);
		app.controller('atworldsendController', [ '$scope', '$route', 'init',
			function($scope, $route, init) {
				init($route);
			} ]
		);
		
		

