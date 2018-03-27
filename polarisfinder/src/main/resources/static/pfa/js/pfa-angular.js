var global_url = location.host;
var app = angular.module("pfaApp", [ "ngRoute", "ngSanitize"]);

app.config(function($routeProvider, $httpProvider) {
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		$routeProvider.when("/", {
			controller : 'mainController',
			templateUrl : "pfa-dashboard.html",
			resolve : { }
			}).when("/pfa-charts", {
				controller : 'mainController',
				templateUrl : "pfa-charts.html",
				resolve : { }
			}).when("/pfa-tables", {
				controller : 'mainController',
				templateUrl : "pfa-tables.html",
				resolve : { }
			}).when("/pfa-navbar", {
				controller : 'mainController',
				templateUrl : "pfa-navbar.html",
				resolve : { }
			}).when("/pfa-cards", {
				controller : 'mainController',
				templateUrl : "pfa-cards.html",
				resolve : { }
			}).when("/pfa-login", {
				controller : 'mainController',
				templateUrl : "pfa-login.html",
				resolve : { }
			}).when("/pfa-forgot-password", {
				controller : 'mainController',
				templateUrl : "pfa-forgot-password.html",
				resolve : { }
			}).when("/pfa-charts", {
				controller : 'mainController',
				templateUrl : "pfa-charts.html",
				resolve : { }
			}).when("/pfa-ca-wh", {
				controller : 'cawhController',
				templateUrl : "pfa-ca-wh.html",
				resolve : { }
			}).when("/pfa-message", {
				controller : 'messageController',
				templateUrl : "pfa-message.html",
				resolve : { }
			}).when("/pfa-message-sent", {
				controller : 'messageController',
				templateUrl : "pfa-message-sent.html",
				resolve : { }
			}).when("/pfa-message-starred", {
				controller : 'messageController',
				templateUrl : "pfa-message-starred.html",
				resolve : { }
			});
		});
		
		app.controller('navigationController', [ '$rootScope', '$scope', '$location','$window','$http',
			function($rootScope, $scope, $location,$window, $http) {
			$scope.logout = function() {
				  $http.get('/user/logout', {})
				  .then(function() {
					  var landingUrl = "http://" + $window.location.host + "";
					  $window.location.href = landingUrl;
				    //$rootScope.authenticated = false;
				    //$location.path("/");
				  },function(data) {
					//$rootScope.authenticated = false;
					//$location.path("/");
				  });
			}
		}]);
		app.service('mainService', ['$http', function($http){
			this.init = function(){
				
			}
			this.user = function(){
				return $http.get('/user/user');
			}
		}])
		app.controller('mainController', [ '$rootScope', '$scope', 'mainService',
			function($rootScope, $scope, mainService) {
			$scope.user = function(){
				mainService.user()
				.then(function(response) {
			    	  $rootScope.currentuser = response.data;
			      if ($rootScope.currentuser.username) {
			    	  $rootScope.username = $rootScope.currentuser.username;
			    	  $rootScope.authenticated = true;
			      } else {
			    	  $rootScope.authenticated = false;
			      }
			    }, function() {
			    	//alert("계정을 확인해주세요");
			    	$rootScope.authenticated = false;
			    });
			}
			$scope.init = function(){
				if (window.location.hash && window.location.hash == '#_=_') {
			        window.location.hash = '';
			    }
				$scope.user();
			}
			$scope.init2 = function(){
				$.ajaxSetup({
					beforeSend : function(xhr, settings) {
					  if (settings.type == 'POST' || settings.type == 'PUT'
					      || settings.type == 'DELETE') {
					    if (!(/^http:.*/.test(settings.url) || /^https:.*/
					        .test(settings.url))) {
					      // Only send the token to relative URLs i.e. locally.
					      xhr.setRequestHeader("X-XSRF-TOKEN",
					          Cookies.get('XSRF-TOKEN'));
					    }
					  }
					}
				});
			}
			
			
		}]);
		

		app.controller('cawhController', [ '$rootScope', '$scope',
			function($rootScope, $scope) {
			
		}]);
		app.controller('messageController', [ '$rootScope', '$scope',
			function($rootScope, $scope) {
			
		}]);
		
		