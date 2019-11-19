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
                    swal({
                            title:"删除成功",
                            text:"您发布的问题已经被删除！",
                            type:"success"
                        },
                        function (isConfirm) {
                            // var id = document.getElementById("questionId")
                            // alert("**"+id)
                            // window.location.href("/question/")
                            // window.location.replace("/")
                        })
                }
            });
    })
})