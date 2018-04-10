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
			$scope.init2 = function(){
				//medium
				$scope.id = $routeParams.id;
				var height = $("body").prop("clientHeight");
				$('.old_newspaper').css('min-height', height+'px');
					
				editor = new MediumEditor('#content');
			    $('#content').mediumInsert({
			        editor: editor,
			        addons: {
	                    	images: {
		                	fileUploadOptions: { url: '/dreamers/DreamersUploadList' }
		            	}
			    	}
				});
			    editor.subscribe('editableInput', function (event, editorElement) {
			    	$scope.content = editor.getContent();
			    });
			    
				this.getContent("medium");
			}
			$scope.init = function(){
				//summernote
				$scope.id = $routeParams.id;
				var height = $("body").prop("clientHeight");
				$('.old_newspaper').css('min-height', height+'px');
				
				$('#content').summernote({
			        placeholder: 'Hello stand alone ui',
			        tabsize: 2,
			        height: 300,                 // set editor height
			        minHeight: null,             // set minimum height of editor
			        maxHeight: null,             // set maximum height of editor
			        focus: true,                  // set focus to editable area after initializing summernote
			        lang: 'ko-KR',
			        callbacks: {
			            onImageUpload: function(files, editor, welEditable) {
			            	for(var i =0; i<files.length; i++){
			            		var data = new FormData();
				            	data.append("file", files[i]);
				            	var el = this;
				            	$.ajax({
				                    data: data,
				                    type: "POST",
				                    url: '/dreamers/DreamersUpload',
				                    cache: false,
				                    contentType: false,
				                    enctype: 'multipart/form-data',
				                    processData: false,
				                    success: function(data) {
				                        //console.log(data.files[0].url);
				                        $(el).summernote('editor.insertImage', data.files[0].url);
				                    }
				            	});
			            	}
			            },
			            onChange: function(contents) {
			            	$scope.content = contents;
			                //console.log('onChange:', contents, $editable);
			            }
			        }
			    });

				this.getContent("summernote");
			}
			
			$scope.publish_article = function(){
				console.log("publish_article : "+$scope.content);
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
			
			$scope.getContent = function(editorType){
				console.log("editorType , scope.id : "+editorType+" , "+ $scope.id);
				if($scope.id != 0){
					dreamerseditService.getContent($scope.id)
					.then(function (response) {
			            data = response.data;
			            console.log(data.content);
			            $scope.content = data.content;
			            if(editorType === "medium"){
				            $("#content").html($scope.content);
			            } else if(editorType === "summernote"){
				            $("#content").summernote('code', $scope.content);
			            }
					},function (error){
						alert('something went wrong!!!');
					});
				}
			}	
		}]);
		