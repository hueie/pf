

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
			this.loginfacebook = function(){
				return $http.get('/login/facebook');
			}
			
		}]);
		app.controller('userController', [ '$rootScope', '$scope', '$http', '$location', 'userService',
			function($rootScope, $scope, $http, $location, userService) {
			$scope.init = function(){
				var height = $("body").prop("clientHeight");
				$('.signin').css('min-height', height+'px');

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
			$scope.loginfacebook = function() {
				userService.loginfacebook()
				.then(function (response) {
					//$scope.login();
					alert('success');
				},function (error){
					alert('something went wrong!!!');
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
		