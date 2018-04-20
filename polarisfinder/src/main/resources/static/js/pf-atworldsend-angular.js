app.controller('atworldsendController', [ '$rootScope', '$scope', '$location',
			function($rootScope, $scope, $location) {
			var ws = null;
			
			$scope.init = function(){
				$("#chatmembers").empty();
				console.log("Chat init");
				$("#chatdata").empty();
				
				this.connector();
				/*
				if ($rootScope.authenticated) {
					this.connector();
		        } else {
					alert("로그인을 해주세요.");
		        	$location.path("/signin");
		        }
		        */
			}
			$scope.sendText = function(){
				ws.send( $rootScope.animalsname+"|"+ $("#wsText").val());
				$("#wsText").val("");
				$("#wsText").focus();
			}
			$scope.myEnterPress = function(keyEvent) {
				if (keyEvent.which === 13){
					this.sendText();
				}
			}
			$scope.connector = function(){
				var tmpurl = "wss://"+global_url+"/chatroom";
				if (location.protocol != 'https:'){
					tmpurl = "ws://"+global_url+"/chatroom";
				}
				//alert(tmpurl);
				ws = new WebSocket(tmpurl);
				ws.onopen = function() {
					//alert("Connection Open");
					$("#chatdata").append("열결됨");
					$("#connect").css("display","none");
					$("#send").css("display","inline");
				};
				ws.onmessage = function(e) {
					var data = e.data;
					var sessionusername = null;
					var message = null;
					
					var strArray = data.split('|');
					
					/*
					for(var i=0; i< strArray.length; i++){
						console.log('str['+i+']: '+strArray[i]);
					}
					*/
					
					sessionusername = strArray[0];
					message=strArray[1];
					
					var printHTML="";
					if(sessionusername == 'init'){
						//var tmpmsg = message.replace("@", "_").replace(".", "_");
						var tmpmsg = message.split(' ');
						$rootScope.animalsname = message;//tmpmsg[1];
					}else if(sessionusername == 'add'){
						var tmpmsg = message.split(' ');
						printHTML += "<div id='"+tmpmsg[1]+"'>";
						printHTML += message
						printHTML += "</div>"
						$("#chatmembers").append(printHTML);
					} else if(sessionusername == 'del'){
						var tmpmsg = message.split(' ');
						$("#"+tmpmsg[1]).remove();
					} else if(sessionusername == $rootScope.animalsname){
						printHTML += "<div style='text-align: right;'>";
						//printHTML += "<strong style='background-color:yellow;'>["+sessionusername+"] -> "+message+"</strong>";
						printHTML += "<strong style='border-radius:25px;padding:5px;background:#f5f5dc;'>"+message+" ["+sessionusername+"]</strong>";
						printHTML += "</div>";
						$("#chatdata").append(printHTML);
					}else{
						printHTML += "<div style='text-align: left;'>";
						printHTML += "<strong style='border-radius:25px;padding:5px;background:white;'>["+sessionusername+"] "+message+"</strong>";
						printHTML += "</div>";
						$("#chatdata").append(printHTML);
					}
					//console.log('chatting data: ' + data);
				};
				ws.onclose = function() {
					$("#chatdata").append("열결 끊김");
					//alert("Connection Close");
					$("#connect").css("display","inline");
					$("#send").css("display","none");
				};
				ws.onerror = function(e) {
					$("#chatdata").append("에러 : "+e.data);
					//alert(e.data);
				}
			}
		}]);
		