/* WebSocket File Upload Start*/
var ws = null;

function connector() {
	var url = "ws://www.polarisfinder.com/chatroom";
	//var url = "ws://www.polarisfinder.com/chatroom";
	//alert(url);
	ws = new WebSocket(url);
	
	ws.onopen = function() {
		alert("Connection Open");
		$("#connect").css("display","none");
		$("#send").css("display","inline");
	};
	ws.onmessage = function(e) {
		var data = e.data;
		var sessionid = null;
		var message = null;
		
		var strArray = data.split('|');
		
		for(var i=0; i< strArray.length; i++){
			console.log('str['+i+']: '+strArray[i]);
		}
		var currentuser_session = $('#sessionuserid').val();
		
		sessionid = strArray[0];
		message=strArray[1];
		
		var printHTML="";
		if(sessionid == currentuser_session){
			printHTML += "<div class='well'>";
			printHTML += "<div class='alert alert-info'>";
			printHTML += "<strong>["+sessionid+"] -> "+message+"</strong>";
			printHTML += "</div>";
			printHTML += "</div>";
		}else{
			printHTML += "<div class='well'>";
			printHTML += "<div class='alert alert-warning'>";
			printHTML += "<strong>["+sessionid+"] -> "+message+"</strong>";
			printHTML += "</div>";
			printHTML += "</div>";
			
		}
		$("#chatdata").append(printHTML);
		console.log('chatting data: ' + data);
	};
	ws.onclose = function() {
		$("#chatdata").append("열결 끊김");
		alert("Connection Close");
		$("#connect").css("display","inline");
		$("#send").css("display","none");
	};
	ws.onerror = function(e) {
		alert(e.data);
	}
}

function sendText() {
	ws.send($("#wsText").val());
}

