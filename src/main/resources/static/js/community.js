$(function () {
    $("#delete").click(function () {
        swal({
                title: "删除",
                text: "您确定要删除您发布的问题吗？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: true
            },
            function (isConfirm) {
                if (isConfirm) {
                    var id = document.getElementById("questionId").value;
                    var temp = document.createElement("form");
                    temp.action = "/question/" + id + "/delete";
                    temp.method = "get";
                    temp.style.display = "none";
                    document.body.appendChild(temp);
                    temp.submit();
                }
            });
    })
})

function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment-content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
                "parentId": questionId,
                "content": content,
                "type": 1
            }
        ),
        success: function (response) {
            if(response.code == 200){
                $("#comment-section").hide();
            }else{
                if(response.code == 2001){//解决没有登录直接回复问题
                    top.swal({
                            title: "登录",
                            text: "您当前没有登录，必须登录后回复，是否现在登录？",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "马上登录",
                            cancelButtonText: "让我再考虑一下…",
                            closeOnConfirm: false,
                            closeOnCancel: false,
                            customClass: "custom_swal"
                        },
                        function (isConfirm) {
                            if(isConfirm){
                                window.open("https://github.com/login/oauth/authorize?client_id=c53ac12fc34d039e1fa7&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                                window.localStorage.setItem("param",true);
                                top.swal.close()
                            }else{
                                top.swal.close()
                            }
                        });
                }else{
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

