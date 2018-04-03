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
			}).when("/pfa-roadmap-ca-wh", {
				controller : 'roadmapController',
				templateUrl : "pfa-roadmap-ca-wh.html",
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
			}).when("/pfa-message-view", {
				controller : 'messageController',
				templateUrl : "pfa-message-view.html",
				resolve : { }
			}).when("/pfa-calendar", {
				controller : 'calanderController',
				templateUrl : "pfa-calendar.html",
				resolve : { }
			}).when("/pfa-task", {
				controller : 'taskController',
				templateUrl : "pfa-task.html",
				resolve : { }
			}).when("/pfa-notice", {
				controller : 'noticeController',
				templateUrl : "pfa-notice.html",
				resolve : { }
			}).when("/pfa-notice-view", {
				controller : 'noticeController',
				templateUrl : "pfa-notice-view.html",
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
		

		app.controller('roadmapController', [ '$rootScope', '$scope', '$location', 'taskService',
			function($rootScope, $scope, $location, taskService) {
			$scope.init_ca_wh = function(){
				console.log($rootScope.roadmap_page);
				if (typeof $rootScope.roadmap_page == 'undefined'){
					$rootScope.roadmap_page = 1;
				}
			}
			$scope.viewRoadmap = function(roadmap_page){
				$rootScope.roadmap_page = roadmap_page;
				if(1 <= roadmap_page  && roadmap_page <= 9){
					$location.path("/pfa-roadmap-ca-wh");
				} else{
					
				}
			}
			$scope.saveTask = function(related_id, subject){
				var type = "roadmap";
				var content = "";
				
				taskService.saveTask(type, related_id, subject, content)
				.then(function() {
					alert("Task 에 성공하였습니다.");
				   	//$scope.init();
					//$location.path("/pfa-task");
				},function(data) {
					alert("Task에 실패하였습니다.");
				});
				console.log("subject : " + subject);
				console.log("content : " + content);
			}
		}]);
		
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
			this.updateStarred = function(id,star){
				return $http.get('/message/updateStarred', {
				    params: { id: id, star: star }
				});
			}
			this.viewMessage = function(id){
				return $http.get('/message/viewMessage', {
				    params: { id: id }
				});
			}
		}]);
		app.controller('messageController', [ '$rootScope', '$scope', '$location', 'messageService', 'followService',
			function($rootScope, $scope, $location, messageService, followService) {
			$scope.init = function(){
				$scope.currentPage = 0;
			    $scope.pageSize = 10;
			    $scope.totalCnt = 1;
			    $scope.data = [];
			    $scope.showme = true;
			    
			    this.getData();
			}
			$scope.init_compose = function(){
				$scope.currentPage = 0;
			    $scope.pageSize = 10;
			    $scope.totalCnt = 1;
			    $scope.data = [];
			    this.getFollowing();
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
		    
			$scope.getFollowing = function () {
				followService.getFollowing($scope.currentPage)
				.then(function(response) {
					var obj = response.data;
					$scope.data = obj;
					//$scope.data.push("Item "+i);
				},function(data) {
					alert("쪽지 보내기에 실패하였습니다.");
				});
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
		    
		    $scope.updateStarred = function(id, star){
		    	messageService.updateStarred(id, star)
				.then(function(response) {
					//var obj = response.data;
					//$scope.data = obj;
					//$scope.data.push("Item "+i);
				},function(data) {
					//alert("쪽지 보내기에 실패하였습니다.");
				});
		    }
		    
		    $scope.viewMessage = function(id){
		    	messageService.viewMessage(id)
				.then(function(response) {
					var obj = response.data;
					$rootScope.viewobj = obj;
			    	$location.path("/pfa-message-view");
				},function(data) {
					//alert("쪽지 보내기에 실패하였습니다.");
				});
		    }
		    
		    
			
		}]);
		
		
		
		
		app.service('calendarService', ['$http', function($http){
			this.init = function(){
				
			}
			this.getCalendar = function(id){
				return $http.get('/calendar/getCalendar', {
				    params: {  }
				});
			}
		}]);
		app.controller('calanderController', [ '$rootScope', '$scope', 'calendarService',
			function($rootScope, $scope, calendarService) {
			
			$scope.init = function(){
				console.log("hi!Calendaer");
				this.makeCalendar();
			}
			
			$scope.makeCalendar = function() {
				 var calendar;
				 var today = new Date();//pdd
				 var yy = today.getFullYear();
				 var mm = today.getMonth() + 1;
				 var dd = today.getDate();
				 var day = today.getDay();

				 var minusWeekNum = parseInt(((dd - day) / 7) + 1);
				 var strtdd = (dd - day - minusWeekNum * 7);
				 if(strtdd > 23){
					 mm=mm-1;
				 }
				 var firstDay = new Date();
				 firstDay.setFullYear(yy, mm - 1, strtdd);
				 //last Day must be reset;
				 var lastDay = new Date();
				 lastDay.setFullYear(yy, mm - 1, strtdd);
				 lastDay.setDate(firstDay.getDate() + 41);
				 
				 var sendStartDate =  firstDay.getFullYear() + "" + ( (firstDay.getMonth()+1)<10 ? "0"+(firstDay.getMonth()+1):(firstDay.getMonth()+1) ) +
				  "" + ( (firstDay.getDate())<10 ? "0"+(firstDay.getDate()):(firstDay.getDate()) );
				 var sendEndDate =  lastDay.getFullYear() + "" + ( (lastDay.getMonth()+1)<10 ? "0"+(lastDay.getMonth()+1):(lastDay.getMonth()+1) ) +
				  "" + ( (lastDay.getDate())<10 ? "0"+(lastDay.getDate()):(lastDay.getDate()) );
				 //alert(sendStartDate +" "+sendEndDate);
				 
				 var sendData="sendStartDate="+sendStartDate+"&sendEndDate="+sendEndDate;
				 
				 
				 calendarService.getCalendar(/*sendData*/)
					.then(function(response) {
						var obj = response.data;
						$rootScope.viewobj = obj;

						var dataRow = obj;
						 var contentDataIdx = 0;
						 var content = "";
						 content += "<div>";
						 content += "<h1><i class='fas fa-caret-left'></i> "+yy+"."+mm +" <i class='fas fa-caret-right'></i></h1>";
						 content += "</div>";
						 var style = "";
						 for (var i = 0; i < 6; i++) {
							 content = content + "<tr style='padding: 0; margin: 0;'>";
							 for (var j = 0; j < 7; j++) {
								 var fontColor = "black";
								 var fyy = firstDay.getFullYear();
								 var fmm = parseInt(firstDay.getMonth()) + 1;
								 var fdd = firstDay.getDate();
								 var str = "" + fyy + "," + fmm + "," + fdd;
								 var sfmm = fmm;
								 var sfdd = fdd;
								 if (parseInt(fmm) < 10) {
									 sfmm = "0" + fmm;
								 }
								 if (parseInt(fdd) < 10) {
									 sfdd = "0" + fdd;
								 }
								 var reg_dt = fyy + "" + sfmm + "" + sfdd;
					     
								 //alert(dataRow.length);
								 // Calendar Contents 
								 var contentData = "";
								 if ( contentDataIdx < dataRow.length-1 ){
									 var dataCol = dataRow[contentDataIdx].split("/");
									 if(dataCol.length>1){
										 var contentData_reg_dt = dataCol[1];
										 if( contentData_reg_dt == reg_dt){
											 contentData = dataCol[0];
											 contentData = "<div id='c"+reg_dt+"'>" + contentData + "</div>"; 
											 contentDataIdx++;
										 }
									 }
								 }
					     
								 if (firstDay.getMonth() == today.getMonth()) {
									 fdd = "<div style='font-size: 1.17em;'>" + fdd + "</div>";
								 } else {
									 fdd = "<div style='font-size: 0.9em;'>" + fdd + "</div>";
								 }
								 fdd = fdd + contentData;
								 
								 if (j == 0) {
									 fontColor = "red";
								 } else if (j == 6) {
									 fontColor = "blue";
								 }
								 
								 var tmpHeight = 50;//parseInt( ( (totalHeight) - (noticHeight + calMenuHeight + mainMenuHeight + dayHeight) ) / 6 );

								 content = content + "<td style='min-height: " + tmpHeight + "px;' id='" + reg_dt + "' name='caldate' ><a href='#' onclick='inputDateData(" + str + ")' style='width:100%; min-height: " + tmpHeight + "px;display:inline-block;text-decoration:none;color:" + fontColor + ";vertical-align: text-top;'>" + fdd + "</a></td>";
								 firstDay.setDate(firstDay.getDate() + 1);
							 }
							 content = content + "</tr>";
						 }
					   
						 //return content;
						 content = "<table width='100%' id='entryTable' style='text-align: left;'><tbody>" +
						 	"<colgroup><col width='14%'/><col width='14%'/><col width='14%'/><col width='14%'/><col width='14%'/><col width='14%'/><col width='14%'/></colgroup>" +
						 	content + "</tbody></table>";
						 $('#entryTable').replaceWith(content);
						
					},function(data) {
					});
				 
			}
		}]);
		
		
		
		
		
		app.service('taskService', ['$http', function($http){
			this.init = function(){
				
			}
			this.saveTask = function(type, related_id, subject, content){
				var data = $.param({
					type: type, related_id:related_id, subject: subject, content: content
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/task/setTask', data, config);
			}
			this.getTask = function(paging){
				return $http.get('/task/getTask', {
				    params: { paging: paging }
				});
			}
		}]);
		app.controller('taskController', [ '$rootScope', '$scope', '$location', 'taskService',
			function($rootScope, $scope, $location, taskService) {
			/* For youtube url */
			$scope.evaluateChange = function(obj,$event) {
				//var el = event.target;
			    //console.log(el.value);
		        console.log($scope.subjectModel);//this will give you value of current element
		        var subject = $scope.subjectModel;
		        
		        var expression = /[-a-zA-Z0-9@:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&//=]*)?/gi;
		        var regex = new RegExp(expression);
		        if(subject.match(regex)){
		        	console.log("hi! youtube");
		        } else {
		        	
		        }
		        
			};
			
			$scope.init = function(){
				$scope.currentPage = 0;
			    $scope.pageSize = 10;
			    $scope.totalCnt = 1;
			    $scope.data = [];
			    $scope.showme = true;
			    
			    this.getData();
			}
			$scope.getData = function () {
				taskService.getTask($scope.currentPage)
				.then(function(response) {
					var obj = response.data;
					$scope.data = obj;
					//$scope.data.push("Item "+i);
				},function(data) {
					alert("쪽지 보내기에 실패하였습니다.");
				});
			}
			
			$scope.saveTask = function(){
				var type = "task";
				var related_id = "";
				var subject = $("#subject").val();
				var content = $("#content").val();
				
				if(subject.trim() === ''){
					alert("제목을 추가해주세요.")
				} else if(content.trim() === ''){
					alert("내용을 추가해주세요.")
				} else {
					taskService.saveTask(type, related_id, subject, content)
					.then(function() {
						$("#subject").val("");
						$("#content").val("");
						alert("Task 에 성공하였습니다.");
				    	$scope.init();
						//$location.path("/pfa-task");
					},function(data) {
						alert("Task에 실패하였습니다.");
					});
					console.log("subject : " + subject);
					console.log("content : " + content);
				}
			}
		}]);
		
		
		app.service('anchorService', ['$http', function($http){
			this.init = function(){
				
			}
			this.saveAnchor = function(subject, content){
				var data = $.param({
					subject, subject, content: content
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/notice/setNotice', data, config);
			}
			this.updateAnchor = function(id,anchor){
				return $http.get('/anchor/updateAnchor', {
				    params: { id: id, anchor: anchor }
				});
			}
		}]);
		
		
		app.service('noticeService', ['$http', function($http){
			this.init = function(){
				
			}
			this.saveNotice = function(subject, content){
				var data = $.param({
					subject, subject, content: content
	            });
	            var config = {
	                headers : {
	                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	            }
				return $http.post('/notice/setNotice', data, config);
			}
			this.getNotice = function(paging){
				return $http.get('/notice/getNotice', {
				    params: { paging: paging }
				});
			}
			this.viewNotice = function(id){
				return $http.get('/notice/viewNotice', {
				    params: { id: id }
				});
			}
		}]);
		app.controller('noticeController', [ '$rootScope', '$scope', '$location', 'noticeService', 'taskService',
			function($rootScope, $scope, $location, noticeService, taskService) {
			
			$scope.saveTask = function(related_id, subject){
				var type = "notice";
				var content = "";
				
				taskService.saveTask(type, related_id, subject, content)
				.then(function() {
					alert("Task 에 성공하였습니다.");
				   	//$scope.init();
					//$location.path("/pfa-task");
				},function(data) {
					alert("Task에 실패하였습니다.");
				});
				console.log("subject : " + subject);
				console.log("content : " + content);
			}
			
			$scope.init = function(){
				$scope.currentPage = 0;
			    $scope.pageSize = 10;
			    $scope.totalCnt = 1;
			    $scope.data = [];
			    $scope.showme = true;
			    
			    this.getData();
			}
			$scope.getData = function () {
				noticeService.getNotice($scope.currentPage)
				.then(function(response) {
					var obj = response.data;
					$scope.data = obj;
					//$scope.data.push("Item "+i);
				},function(data) {
					alert("쪽지 보내기에 실패하였습니다.");
				});
			}
			$scope.saveNotice = function(){
				var subject = $("#subject").val();
				var content = $("#content").val();
				
				if(subject.trim() === ''){
					alert("제목을 추가해주세요.")
				} else if(content.trim() === ''){
					alert("내용을 추가해주세요.")
				} else {
					noticeService.saveNotice(subject, content)
					.then(function() {
						$("#subject").val("");
						$("#content").val("");
						alert("Task 에 성공하였습니다.");
				    	$scope.init();
						//$location.path("/pfa-task");
					},function(data) {
						alert("Task에 실패하였습니다.");
					});
					console.log("subject : " + subject);
					console.log("content : " + content);
				}
			}
			$scope.viewNotice = function(id){
		    	noticeService.viewNotice(id)
				.then(function(response) {
					var obj = response.data;
					$rootScope.viewobj = obj;
			    	$location.path("/pfa-notice-view");
				},function(data) {
					//alert("쪽지 보내기에 실패하였습니다.");
				});
		    }
		}]);