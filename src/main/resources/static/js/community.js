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
            function(isConfirm){
                if (isConfirm) {
                    var id = document.getElementById("questionId").value;
                    var temp = document.createElement("form");
                    temp.action = "/question/"+id+"/delete";
                    temp.method = "get";
                    temp.style.display = "none";
                    document.body.appendChild(temp);
                    temp.submit();
                }
            });
    })
})