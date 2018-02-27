/* WebSocket File Upload Start*/
var ws = null;
var totalChunkLength;

function connector() {
	var url = "ws://localhost:8888/bigfileupload";
	ws = new WebSocket(url);
	ws.binaryType = "arraybuffer";

	ws.onopen = function() {
		alert("Connection Open");
		$("#connect").css("display","none");
		$("#send").css("display","inline");
	};
	ws.onmessage = function(e) {
		var percentage = parseInt( (e.data/totalChunkLength)*100 );
		$("#progressing").html(percentage+'%'+' '+'('+e.data+'/'+totalChunkLength+')');
	};
	ws.onclose = function() {
		alert("Connection Close");
		$("#connect").css("display","inline");
		$("#send").css("display","none");
	};
	ws.onerror = function(e) {
		alert(e.data);
	}
}

function sendFile() {
	
	var userId = $("#userEmail").val();
	ws.send('userId:' + userId);
	
	var wsfile = document.getElementById('wsfile').files[0];
	// send filename Text Message For Text Handler
	ws.send('fileName:' + wsfile.name);
	alert('File Transfer Start');
	$("#progressing").html('~~ Loading Data ~~');
	
	var reader = new FileReader();
	var arrbuf = new ArrayBuffer();

	reader.onload = function(e) {

		if (reader.readyState == FileReader.LOADING) {
			//Not Working
			//$("#progressing").html('~~ Loading Data ~~');
		} else if (reader.readyState == FileReader.DONE) {
			arrbuf = reader.result;
			var fStart;
			var fEnd;
			totalChunkLength = parseInt(arrbuf.byteLength / 1024);
			ws.send('totalChunkLength:'+totalChunkLength);
			
			for (var i = 0; i < totalChunkLength + 1; i++) {
				fStart = i * 1024;
				fEnd = (i + 1) * 1024;
				if (totalChunkLength != i) {
					ws.send(arrbuf.slice(fStart, fEnd));
				} else if (totalChunkLength == i) {
					ws.send(arrbuf.slice(fStart, arrbuf.byteLength));
				}
			}
			ws.send('end');
		}
	}

	reader.readAsArrayBuffer(wsfile);
}

/* WebSocket File Upload End */

/* Normal File Upload Start */
function btnSubmitFunc() {
	var form = $('#fileUploadForm')[0];
	// Create an FormData object
	var data = new FormData(form);
	// If you want to add an extra field for the FormData
	data.append("CustomField", "This is some extra data, testing");

	$.ajax({
		type : "POST",
		enctype : 'multipart/form-data',
		url : "/cubemap/normalfileupload",
		data : data,
		processData : false,
		contentType : false,
		cache : false,
		timeout : 600000,
		success : function(data) {
			$("#result").text(data);
			console.log("SUCCESS : ", data);
		},
		error : function(e) {
			$("#result").text(e.responseText);
			console.log("ERROR : ", e);
		}
	});
}

/*
 * function handleFileSelect(evt) { evt.stopPropagation(); evt.preventDefault();
 * 
 * var files = evt.dataTransfer.files; // FileList object.
 *  // files is a FileList of File objects. List some properties. var output =
 * []; for (var i = 0, f; f = files[i]; i++) { output.push('<li><strong>',
 * escape(f.name), '</strong> (', f.type || 'n/a', ') - ', f.size, ' bytes,
 * last modified: ', f.lastModifiedDate ?
 * f.lastModifiedDate.toLocaleDateString() : 'n/a', '</li>'); }
 * document.getElementById('list').innerHTML = '<ul>' + output.join('') + '</ul>'; }
 * 
 * function handleDragOver(evt) { evt.stopPropagation(); evt.preventDefault();
 * evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy. }
 *  // Setup the dnd listeners. var dropZone =
 * document.getElementById('drop_zone'); dropZone.addEventListener('dragover',
 * handleDragOver, false); dropZone.addEventListener('drop', handleFileSelect,
 * false);
 */

/* Normal File Upload End */