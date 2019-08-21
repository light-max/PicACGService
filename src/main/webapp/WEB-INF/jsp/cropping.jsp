<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>更换头像</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="/css/cropper.min.css" type="text/css">
    <link rel="stylesheet" href="/css/ImgCropping.css" type="text/css">
    <link rel="stylesheet" href="/css/bootstrap.css" type="text/css">

</head>
<body style="background: #eee">

<button id="replaceImg" class="l-btn">更换图片</button>
<div style="width: 320px;height: 320px;border: solid 1px #555;padding: 5px;margin-top: 10px">
    <img id="finalImg" src="" width="100%">
</div>
<input type="button" class="btn btn-primary" value="上传" onclick="upload()">


<!--图片裁剪框 start-->
<div style="display: none" class="tailoring-container">
    <div class="black-cloth" onclick="closeTailor(this)"></div>
    <div class="tailoring-content">
        <div class="tailoring-content-one">
            <label title="上传图片" for="chooseImg" class="l-btn choose-btn">
                <input type="file" accept="image/jpg,image/jpeg,image/png" name="file" id="chooseImg" class="hidden"
                       onchange="selectImg(this)">
                选择图片
            </label>
            <div class="close-tailoring" onclick="closeTailor(this)">×</div>
        </div>
        <div class="tailoring-content-two">
            <div class="tailoring-box-parcel">
                <img id="tailoringImg">
            </div>
            <div class="preview-box-parcel">
                <p>图片预览：</p>
                <div class="square previewImg"></div>
                <div class="circular previewImg"></div>
            </div>
        </div>
        <div class="tailoring-content-three">
            <button class="l-btn cropper-reset-btn">复位</button>
            <button class="l-btn cropper-rotate-btn">旋转</button>
            <button class="l-btn cropper-scaleX-btn">换向</button>
            <button class="l-btn sureCut" id="sureCut">确定</button>
        </div>
    </div>
</div>
<!--图片裁剪框 end-->


<script src="/js/jquery/jquery.js"></script>
<script src="/js/cropper.min.js"></script>
<script src="/js/function.js"></script>
<script>

    //弹出框水平垂直居中
    (window.onresize = function () {
        var win_height = $(window).height();
        var win_width = $(window).width();
        if (win_width <= 768) {
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight()) / 2,
                "left": 0
            });
        } else {
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight()) / 2,
                "left": (win_width - $(".tailoring-content").outerWidth()) / 2
            });
        }
    })();

    //弹出图片裁剪框
    $("#replaceImg").on("click", function () {
        $(".tailoring-container").toggle();
    });

    //图像上传
    function selectImg(file) {
        if (!file.files || !file.files[0]) {
            return;
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            var replaceSrc = evt.target.result;
            //更换cropper的图片
            $('#tailoringImg').cropper('replace', replaceSrc, false);//默认false，适应高度，不失真
        };
        reader.readAsDataURL(file.files[0]);
    }

    //cropper图片裁剪
    $('#tailoringImg').cropper({
        aspectRatio: 1,//默认比例
        preview: '.previewImg',//预览视图
        guides: false,  //裁剪框的虚线(九宫格)
        autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
        movable: false, //是否允许移动图片
        dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
        movable: true,  //是否允许移动剪裁框
        resizable: true,  //是否允许改变裁剪框的大小
        zoomable: false,  //是否允许缩放图片大小
        mouseWheelZoom: false,  //是否允许通过鼠标滚轮来缩放图片
        touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
        rotatable: true,  //是否允许旋转图片
        crop: function (e) {
        }
    });
    //旋转
    $(".cropper-rotate-btn").on("click", function () {
        $('#tailoringImg').cropper("rotate", 45);
    });
    //复位
    $(".cropper-reset-btn").on("click", function () {
        $('#tailoringImg').cropper("reset");
    });
    //换向
    var flagX = true;
    $(".cropper-scaleX-btn").on("click", function () {
        if (flagX) {
            $('#tailoringImg').cropper("scaleX", -1);
            flagX = false;
        } else {
            $('#tailoringImg').cropper("scaleX", 1);
            flagX = true;
        }
        flagX != flagX;
    });

    //裁剪后的处理
    $("#sureCut").on("click", function () {
        if ($("#tailoringImg").attr("src") == null) {
            return false;
        } else {
            var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
            $("#finalImg").prop("src", base64url);//显示为图片的形式

            //关闭裁剪框
            closeTailor();
        }
    });

    //关闭裁剪框
    function closeTailor() {
        $(".tailoring-container").toggle();
    }


</script>

<script>
    window.onload = function () {
        if (window.opener != null) {
            let key = window.sessionStorage.getItem('key');
            if (key == null) {
                alert('无法上传，没有登录信息')
            } else {
                window.sessionStorage.setItem('key', key);
                window.sessionStorage.setItem('name', window.sessionStorage.getItem('name'));
            }
        }
    };

    function upload() {
        let src = $('#finalImg').attr('src');
        // console.log(src)
        if (src === '') {
            alert("请选择图片")
            return
        }
        let key = window.sessionStorage.getItem('key');
        let name = window.sessionStorage.getItem('name');
        if (key == null) {
            alert('无法上传，没有登录信息')
        } else {
            let fd = new FormData();
            fd.append("name", name);
            fd.append("key", key);
            let blob = dataURLtoBlob(src);
            fd.append("file", blob);
            $.ajax({
                url: "/user/set/head",
                type: "POST",
                processData: false,
                contentType: false,
                data: fd,
                success: function (data) {
                    switch (data) {
                        case -2:
                            alert('图片大小不能超过5MB');
                            break;
                        case -1:
                            alert('登录信息已过时，请重新登录');
                            window.open('sign_in', '登录');
                            break;
                        case -3:
                            alert('图片长度和宽度不一样');
                            break;
                        case 0:
                            if (window.opener != null) {
                                let hic = $('#head-icon', window.opener.document);
                                let src = hic.attr('src');
                                src = src.split('?')[0];
                                hic.attr('src', src + '?d=' + new Date());
                                window.close();
                            } else {
                                alert('修改成功')
                            }
                            break;
                    }
                }
            })
        }
    }
</script>

</body>
</html>