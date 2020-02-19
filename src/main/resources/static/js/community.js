// 问题详情页面，点击删除按钮
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

// 问题详情页面，点击回复按钮,回复问题
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment-content").val();
    comment(questionId, 1, content)
}

//问题详情页面，点击评论按钮,回复评论
function replyComment(e) {
    debugger;
    var commentId = e.getAttribute("data-id");
    var content = $("#reply-" + commentId).val();
    comment(commentId, 2, content);
}

function comment(targetId, type, content) {
    if (!content) {
        swal({
            title: "提交回复",
            text: "回复内容不能为空，请填写回复内容后重新提交！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false,
            customClass: "custom_swal"
        })
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
                "parentId": targetId,
                "content": content,
                "type": type
            }
        ),
        success: function (response) {
            if (response.code == 200) {
                $("#comment-section").hide();
                window.location.reload();
            } else {
                if (response.code == 2001) {//解决没有登录直接回复问题
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
                            if (isConfirm) {
                                window.open("https://github.com/login/oauth/authorize?client_id=c53ac12fc34d039e1fa7&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                                window.localStorage.setItem("param", true);
                                top.swal.close()
                            } else {
                                top.swal.close()
                            }
                        });
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

// 问题详情页面，点击回复列表的评论按钮，实现打开和关闭二级评论

function openTwoLevelComment(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    if (e.getAttribute("data-collapse")) {
        //关闭二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id)
        if (subCommentContainer.children().length != 2) {
            //展开二级评论
            comments.addClass("in");
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded img-size",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<span/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "class": "normal-font",
                        "html": comment.content
                    })).append($("<div/>", {}).append($("<span/>", {
                        "class": "comment-date",
                        "html": moment(comment.gmtCreate).format("YYYY-MM-DD")
                    })));
                    var mediaElement = $("<div/>", {
                        "class": "media menu"
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 list-sp",
                    }).append(mediaElement);
                    subCommentContainer.prepend(commentElement);
                });
            });
            //展开二级评论
            comments.addClass("in");
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        }
    }

}
// 点击publish页面标签input
function showTags() {
    $("#publish-tags").show()
}
//选择标签
function selectedTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val()
    if (previous.indexOf(value) == -1){
        if(previous){
            $("#tag").val(previous+","+value)
        }else {
            $("#tag").val(value)
        }
    }
}
