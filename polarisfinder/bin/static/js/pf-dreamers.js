
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
        	$("#paging").val(p);

        	var obj = data;// objs.data;
        	var html = "";
        	for(var idx in obj){
        		var myContent = obj[idx].content;
        		/*
        		var substr = $(myContent).text().substring(0, 100);
        		html += "<div class='well'>";
        		html += "<a href=\"#!dreamers-view/"+obj[idx].id+"\">"; 
        		html += "<p>"+ substr + "</p>";
        		html += "</a>";
        		html += "</div>"; 
        		*/
                var substr = myContent;
                html += "<div style='border:solid 1px grey; margin:0px 0px 30px 0px; padding:10px;background:rgba(255,255,255,1);'>";
                html += "<div>" + substr + "</div>";
                html += "<div>";
                html += "<a href='#'><div class='like_black_32' style='margin:10px;'></div></a> ";
                html += "<a href='#'><div class='chat_black_32' style='margin:10px 0px;'></div></a>";
                html += "<a href='#'><div class='label_black_32' style='margin:10px; float:right;'></div></a>";
                html += "</div>";
                html += "<br>";
                html += "<div>comment 1 <br> comment 2<br></div>";
                html += "</div>";
        		
        	}
        	if(obj.length < 5){
        		$("#morebtn").css("display", "none");
        	} else{
            	$("#morebtn").css("display", "block");
        	}
        	if(p == 1){
            	$("#list").html(html);
        	} else {
            	$("#list").append(html);
        	}
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}
