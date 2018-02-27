function signCheck(){
	$.ajax({
        type: "get",
        url: "/user/Signcheck",
        data: {},
        success: function(data, textStatus, xhr){
        	if(data == ""){
        		$("#userEmail").val("");
        		$(".signinTable").css("display","block")
        		$(".signoutTable").css("display","none")
        	} else {
        		$("#userEmail").val(data);
        		$(".signinTable").css("display","none")
        		$(".signoutTable").css("display","inline")
        	}
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}

function signOut(){
	$.ajax({
        type: "post",
        url: "/user/Signout",
        data: {},
        success: function(data, textStatus, xhr){
        	 window.location.href = "/";
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}


function signIn(){
	var email = $("#email").val();
	var password = $("#password").val();
    
	$.ajax({
        type: "post",
        url: "/user/Signin",
        data: {email:email, password:password },
        success: function(data, textStatus, xhr){
        	$("#userEmail").val(data);
        	//alert(data);
        	document.getElementById("list").innerHTML = "<span>"+data+"</span>";
        	signCheck();
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}



function signUp(){
	var last_name = $("#last_name").val();
	var name = $("#name").val();
	var email = $("#email").val();
	var password = $("#password").val();
    
	$.ajax({
        type: "post",
        url: "/user/Signup",
        data: {last_name:last_name, name:name, email:email, password:password },
        success: function(data, textStatus, xhr){
        	document.getElementById("list").innerHTML = "<span>"+data+"</span>";
        },
        error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
        } 
    });
}


