
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
        		var substr = $(myContent).text().substring(0, 100);
                
        		html += "<div class='well col-lg-3'>";
        		html += "<a href=\"#!dreamers-view/"+obj[idx].id+"\">"; 
        		html += "<p>"+ substr + "</p>";
        		html += "</a>";
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
