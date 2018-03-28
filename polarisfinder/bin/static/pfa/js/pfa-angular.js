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
			}).when("/pfa-message-compose", {
				controller : 'messageController',
				templateUrl : "pfa-message-compose.html",
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
		}]);
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
		
		
		app.service('messageService', ['$http', function($http){
			this.init = function(){
				
			}
			this.send = function(tolist, subject, content){
				var data = $.param({
					tolist: tolist, subject, subject, content: content
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/message/add', data, config);
			}
			this.getMessage = function(paging){
				return $http.get('/message/getMessage', {
				    params: { paging: paging }
				});
			}
			this.getMessageSent = function(paging){
				return $http.get('/message/getMessageSent', {
				    params: { paging: paging }
				});
			}
			this.getMessageStarred = function(paging){
				return $http.get('/message/getMessageStarred', {
				    params: { paging: paging }
				});
			}
		}]);
		app.controller('messageController', [ '$rootScope', '$scope', '$location', 'messageService',
			function($rootScope, $scope, $location, messageService) {
			$scope.init = function(){
				$scope.currentPage = 0;
			    $scope.pageSize = 10;
			    $scope.totalCnt = 1;
			    $scope.data = [];
			    this.getData();
			}
			$scope.init_sent = function(){
				$scope.currentPage = 0;
			    $scope.pageSize = 10;
			    $scope.totalCnt = 1;
			    $scope.data = [];
			    this.getDataSent();
			}
			$scope.init_starred = function(){
				$scope.currentPage = 0;
			    $scope.pageSize = 10;
			    $scope.totalCnt = 1;
			    $scope.data = [];
			    this.getDataStarred();
			}
			$scope.toCheckBox = function(id, name){
				var curlist = $("#to").val().split(" ");
				var newlist = "";
				var flag = true;
				for(var idx in curlist){
					if(curlist[idx] === name){
						flag = false;
					} else {
						if(curlist[idx].trim() !== ""){
							newlist += curlist[idx].trim() +" ";
						}
					}
				}
				if(flag){
					newlist += name +" ";
				}
				
				$("#to").val(newlist);
			}
			$scope.send = function(){
				var tolist = $("#to").val();
				var subject = $("#subject").val();
				var content = $("#content").val();
				
				if(tolist.trim() === ''){
					alert("보낼 사람을 추가해주세요.")
				} else if(subject.trim() === ''){
					alert("제목을 추가해주세요.")
				} else if(content.trim() === ''){
					alert("내용을 추가해주세요.")
				} else {
					messageService.send(tolist, subject, content)
					.then(function() {
						alert("쪽지 보내기에 성공하였습니다.");
				    	$location.path("/pfa-message-sent");
					},function(data) {
						alert("쪽지 보내기에 실패하였습니다.");
					});
					console.log("tolist : " + tolist);
					console.log("subject : " + subject);
					console.log("content : " + content);
				}
			}
		    
		    $scope.getData = function () {
		    	messageService.getMessage($scope.currentPage)
				.then(function(response) {
					var obj = response.data;
					$scope.data = obj;
					//$scope.data.push("Item "+i);
				},function(data) {
					alert("쪽지 보내기에 실패하였습니다.");
				});
			}
		    
		    $scope.getDataSent = function () {
		    	messageService.getMessageSent($scope.currentPage)
				.then(function(response) {
					var obj = response.data;
					$scope.data = obj;
					//$scope.data.push("Item "+i);
				},function(data) {
					alert("쪽지 보내기에 실패하였습니다.");
				});
			}
		    
		    $scope.getDataStarred = function () {
		    	messageService.getMessageStarred($scope.currentPage)
				.then(function(response) {
					var obj = response.data;
					$scope.data = obj;
					//$scope.data.push("Item "+i);
				},function(data) {
					alert("쪽지 보내기에 실패하였습니다.");
				});
			}
		    $scope.numberOfPages=function(){
		        return Math.ceil($scope.totalCnt/$scope.pageSize);                
		    }
		    
			
		}]);
		
		