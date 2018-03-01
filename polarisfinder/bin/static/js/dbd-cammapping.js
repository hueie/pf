function getAllFiles(){
	$("#stack_add_form").css("display","none");
	$("#booksf_add_form").css("display","none");
	$("#box_add_form").css("display","none");
    
	$.ajax({
        type: "get",
        url: "/cammapping/getAllCams",
        data: { },
        success: function(data, textStatus, xhr){
        	var objs = data;// JSON.parse(msg);
        	var html = "";
        	for(var idx in objs){
        		var file_img_nm = objs[idx].file_nm.split(".")[0];
        		html += "<span><button class=\"btn btn-xs btn-warning\" onclick=\"setImgSrc("+objs[idx].fileupload_id+",'"+file_img_nm+"', "+objs[idx].fileupload_reg_id+");\">카메라 선택</button>"+ objs[idx].fileupload_id + ", " + objs[idx].file_nm + ", " + objs[idx].fileupload_reg_id + "</span><br>"; 
        	}
        	document.getElementById("canvas_list").innerHTML = html;
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}


var canvas, ctx, img;
var prev_pointbool = false;
var start_x, start_y;
var end_x, end_y;
var line_list = [];
var line_id = 1;

function filltext(canvas, x, y, text) {
	var context = canvas.getContext('2d');
	context.font = 'italic 40pt Calibri';
	context.fillStyle = "#000000";
	context.fillText(text, x, y);
}

function fillpoint(canvas, x, y) {
	var context = canvas.getContext('2d');
	context.fillStyle = "#000000";
	context.fillRect(x - 5, y - 5, 10, 10);
}

function getMousePos(canvas, evt) {
	var rect = canvas.getBoundingClientRect();
	return {
		x : evt.clientX - rect.left,
		y : evt.clientY - rect.top
	};
}

function fillline(canvas, start_x, start_y, end_x, end_y) {
	var context = canvas.getContext('2d');
	context.beginPath();
	context.moveTo(start_x, start_y);
	context.lineTo(end_x, end_y);
	context.stroke();
}


function setImgSrc(fileupload_id, file_img_nm, fileupload_reg_id ) {
	$("#fileupload_id").val(fileupload_id);
	
	img = new Image(); //480, 360
	img.src = "/files/"+fileupload_reg_id+"/"+file_img_nm+".jpg";
	
	line_list = [];
	line_id = 1;
	prev_pointbool = false;
	
	img.onload = function() {
		var contents = document.getElementById("canvas_contents");
		canvas =  document.createElement("CANVAS");    // document.getElementById("myCanvas");
		canvas.width = img.width;
		canvas.height = img.height;
		contents.replaceChild(canvas, contents.childNodes[0]);
		
		ctx = canvas.getContext("2d");
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		ctx.drawImage(img, 0, 0, img.width, img.height); //480, 360
		
		
		function myevt(evt){
			var mousePos = getMousePos(canvas, evt);
			fillpoint(canvas, mousePos.x, mousePos.y);
			if (prev_pointbool) {
				prev_pointbool = false;
				end_x = mousePos.x;
				end_y = mousePos.y;
				fillline(canvas, start_x, start_y, end_x, end_y);
				filltext(canvas, start_x, start_y, line_id);
				line_list.push({
					fileupload_id : fileupload_id,
					line_id : line_id,
					start_x : start_x,
					start_y : start_y,
					end_x : end_x,
					end_y : end_y
				});
				line_id++;
			} else {
				prev_pointbool = true;
				start_x = mousePos.x;
				start_y = mousePos.y;
			}
		}
		canvas.addEventListener('click', myevt);
		
		/*
		canvas.addEventListener('click', function(evt) {
			var mousePos = getMousePos(canvas, evt);
			var message = 'Mouse position: ' + mousePos.x + ',' + mousePos.y + '';

			fillpoint(canvas, mousePos.x, mousePos.y);
			if (prev_pointbool) {
				prev_pointbool = false;
				end_x = mousePos.x;
				end_y = mousePos.y;
				fillline(canvas, start_x, start_y, end_x, end_y);
				filltext(canvas, start_x, start_y, line_id);
				line_list.push({
					fileupload_id : fileupload_id,
					line_id : line_id,
					start_x : start_x,
					start_y : start_y,
					end_x : end_x,
					end_y : end_y
				});
				line_id++;
			} else {
				prev_pointbool = true;
				start_x = mousePos.x;
				start_y = mousePos.y;
			}
		}, false);
		*/
		
		$.ajax({
	        type: "get",
	        url: "/cammapping/getLinesfsByFileuploadId",
	        data: {fileupload_id:fileupload_id },
	        success: function(data, textStatus, xhr){
	        	var objs = data;// JSON.parse(msg);
	        	var html = "";
	        	for(var idx in objs){
	        		var sub_cammapping_id = objs[idx].cammapping_id;
	        		var sub_fileupload_id = objs[idx].fileupload_id;
	        		var sub_line_id = objs[idx].line_id;
	        		var sub_start_x = objs[idx].start_x;
	        		var sub_start_y = objs[idx].start_y;
	        		var sub_end_x = objs[idx].end_x;
	        		var sub_end_y = objs[idx].end_y;

	        		fillpoint(canvas, sub_start_x, sub_start_y);
	        		fillpoint(canvas, sub_end_x, sub_end_y);
	        		fillline(canvas, sub_start_x, sub_start_y, sub_end_x, sub_end_y);
					filltext(canvas, sub_start_x, sub_start_y, sub_line_id);
					line_list.push({
						fileupload_id : sub_fileupload_id,
						line_id : sub_line_id,
						start_x : sub_start_x,
						start_y : sub_start_y,
						end_x : sub_end_x,
						end_y : sub_end_y
					});
					line_id++;
					
	        	}
	        	document.getElementById("canvas_list").innerHTML = html;
	        },
	        error:function (xhr, ajaxOptions, thrownError){
	            alert(xhr.status);
	            alert(thrownError);
	        } 
	    });
	}
};


function clearcanvas() {
    var writediv = document.getElementById("writediv");
	writediv.innerHTML = "";
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	ctx.drawImage(img, 0, 0);

	line_list = [];
	line_id = 1;
	prev_pointbool = false;
}

function sendtodb() {
	var fileupload_id = $("#fileupload_id").val();
    var writediv = document.getElementById("writediv");
	var msg = "";
	// alert(line_list.length);
	for (var i = 0; i < line_list.length; i++) {
		msg += line_list[i].line_id + ' START : x' + line_list[i].start_x
				+ ' y' + line_list[i].start_y + '<br>';
		msg += line_list[i].line_id + ' END   : x' + line_list[i].end_x + ' y'
				+ line_list[i].end_y + '<br><br>';
	}
	// alert(msg);
	writediv.innerHTML = msg;
	var myJsonString = "{line_list:" + JSON.stringify(line_list) + "}";
	$.ajax({
		type : "post",
		url : "/cammapping/Cammapping",
		data : {
			"line_list" : myJsonString,
			"fileupload_id" : fileupload_id
		},
		success : function(msg) {
			alert("Successful Sending");
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(xhr.status);
			alert(thrownError);
		}
	});
}