
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