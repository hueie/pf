		var app = angular.module("myApp", [ "ngRoute", "ngSanitize"]);
		app.config(function($routeProvider) {
			$routeProvider.when("/", {
				controller : 'mainController',
				templateUrl : "pf-main.html",
				resolve : { }
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
				controller : 'signinController',
				templateUrl : "/user/signin.html",
				resolve : { }
			}).when("/signup", {
				controller : 'signupController',
				templateUrl : "/user/signup.html",
				resolve : { }
			});
		});
		
		app.controller('mainController', [ '$scope', 
			function($scope) {
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
			$scope.init = function(countrycode){
				var height = $("body").prop("clientHeight");
				$('.old_pub').css('min-height', height+'px');
				
				initAutocomplete();
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
		
		

		app.service('signinService', ['$http', function($http){
			this.init = function(){
				
			}
			this.signin = function(email, password){
				var data = $.param({
					email: email, password: password
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/user/Signin', data, config);
			}
		}]);
		app.controller('signinController', [ '$scope', 'signinService',
			function($scope, signinService) {
			$scope.init = function(){

			}
			$scope.signin = function(){
				var email = $("#email").val();
				var password = $("#password").val();
				signinService.signin(email, password)
				.then(function (response) {
					alert('success');
				},function (error){
					alert('something went wrong!!!');
				});
			}
		}]);
		
		app.service('signupService', ['$http', function($http){
			this.init = function(){
				
			}
			this.signup = function(email, password, name, last_name){
				var data = $.param({
					email: email, password: password, name: name, last_name: last_name
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/user/Signup', data, config);
			}
		}]);
		app.controller('signupController', [ '$scope', 'signupService',
			function($scope, signupService) {
			$scope.init = function(){

			}
			$scope.signup = function(){
				var email = $("#email").val();
				var password = $("#password").val();
				var name = $("#name").val();
				var last_name = $("#last_name").val();
				
				signupService.signup(email, password, name, last_name)
				.then(function (response) {
					alert('success');
				},function (error){
					alert('something went wrong!!!');
				});
			}
		}]);
		