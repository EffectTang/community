
function reply() {
    var questionId = $("#question_id").val();
    var comment = $("#comment").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:'application/json',
        data: JSON.stringify({
            "parentId":questionId,
            "content":comment,
            //type 1是问题 2是回答
            "type": 1
        }),
        success: function (response) {
            if(response.code===200){
                $("#section_comment").hide();
            }else {
                if(response.code === 2030){
                    var conf = confirm(response.message);
                    if(conf){
                        window.localStorage.setItem("closeable",true);
                        window.open("https://github.com/login/oauth/authorize?client_id=ff89e30991e3901115fa&redirect_uri=http://localhost:8080/callback&scope=user&status=1")

                    }
                }

                alert(response.message)
            }
            console.log(response)
        },
        dataType: "JSON"
    });
}
