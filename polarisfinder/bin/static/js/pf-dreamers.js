
/*
function getDreamerscommentList(dreamers_id, paging){
	$.ajax({
        type: "get",
        url: "/dreamers/DreamerscommentList",
        data: {
        	"dreamers_id" : dreamers_id,
        	"paging" : paging,
        	},
        success: function(data, textStatus, xhr){
        	var obj = data;// objs.data;
        	var html = "";
        	for(var idx in obj){
        		html += obj[idx].dreamers_comment + "<br>";
        	}
        	$("#dreamerscommentlist_"+dreamers_id).html(html);
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}
function getDreamersList(){
	var id = $("#id").val();
	var paging = $("#paging").val();
	
	$.ajax({
        type: "get",
        url: "/dreamers/DreamersList",
        data: {
        	"id" : id,
        	"paging" : paging
        	},
        success: function(data, textStatus, xhr){
        	var p = parseInt(paging) + 1;
        	if(p == 1){
            	$("#list").html("");
        	} 
        	$("#paging").val(p);
        	var obj = data;// objs.data;
        	var html = "";
        	for(var idx in obj){
        		var myContent = obj[idx].content;
                var substr = myContent;
                html += "<div class='well'>";
                html += "<div style='background-color:white;'>" + substr + "</div>";
                html += "<div>";
                html += "<div id='like_"+obj[idx].id+"' onclick='dreamerslike("+obj[idx].id+");' class='like_black_32' style='margin:5px;cursor: pointer;'></div>";
                html += "<span id='like_text_"+obj[idx].id+"'>"+obj[idx].like_cnt+"</span>";
                //html += "<a href='#'><div class='chat_black_32' style='margin:10px 0px;'></div></a>";
                var cok = $.cookie("ROLE");
                //alert(cok);
                if( cok == "ADMIN"){
                	html += "<a href='#!/dreamers-editor/"+obj[idx].id+"'><div class='edit_black_32' style='margin:5px; float:right;'></div></a>";
                }
                html += "<div class='label_black_32' style='margin:5px; float:right; cursor: pointer;'></div>";
                html += "</div>";
                //Commoent Div Start
                html += "<div id='dreamerscommentlist_"+obj[idx].id+"'></div>";
                //Commoent Div End

                html += "<div class='input-group'>";
                html += "<textarea id='dreamerscomment_"+obj[idx].id+"' name='dreamerscomment_"+obj[idx].id+"' rows='1' cols='' class='form-control '  placeholder='Reply!'></textarea>";
                html += "<duv class='input-group-addon' onclick='addDreamersComment("+obj[idx].id+");' style='vertical-align:bottom;cursor: pointer;'><div class='chat_black_16' style='margin:0px;'></div></div>";
                html += "</div>";
                html += "</div>";
                
                $("#list").append(html);
                getDreamerscommentList(obj[idx].id, 0);
        	}
        	if(obj.length < 5){
        		$("#morebtn").css("display", "none");
        	} else{
            	$("#morebtn").css("display", "block");
        	}
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}


function dreamerslike(dreamers_id){
	$.ajax({
        type: "POST",
        url: "/dreamers/Dreamerslike",
        data: {
        	"dreamers_id" : dreamers_id,
        	},
        success: function(data, textStatus, xhr){
        	$("#like_"+dreamers_id).removeClass( "like_black_32" ).addClass( "like_red_32" );
        	var cnt = $("#like_text_"+dreamers_id).text();
        	cnt = parseInt(cnt) +1;
        	$("#like_text_"+dreamers_id).text(cnt);
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}

function addDreamersComment(dreamers_id){
	var dreamers_comment = $("#dreamerscomment_"+dreamers_id).val();
	$.ajax({
        type: "post",
        url: "/dreamers/DreamerscommentAdd",
        data: {
        	"dreamers_id" : dreamers_id,
        	"dreamers_comment" : dreamers_comment,
        	},
        success: function(msg){
        	$("#dreamerscomment_"+dreamers_id).val("");
        	getDreamerscommentList(dreamers_id, 0);
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}
*/
