
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
				/*
				var height = $("body").prop("clientWidth")/3*2-30;
				$('.old_worldmap').css('min-height', height+'px');
				*/
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
		app.controller('treasuremapCountryController', [ '$scope', '$compile','$templateCache', 'treasuremapCountryService', 
			function($scope, $compile, $templateCache, treasuremapCountryService) {
			
			$scope.init = function(countrycode){
				$scope.roadmap_page = 0;
				$scope.listDiv="";
				$scope.countrycode = countrycode;
				console.log(countrycode);
				/*
				var height = $("body").prop("clientWidth")/3*2-30;
				$('.old_'+countrycode+'_map').css('min-height', height+'px');
				*/
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
