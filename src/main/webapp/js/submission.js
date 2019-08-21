window.onload = function () {
    if (window.opener == null) {
        alert('错误的打开方式');
        window.close()
    } else {
        let key = window.opener.sessionStorage.getItem('key');
        if (key == null) {
            alert('错误的打开方式');
            window.close()
        } else {
            window.sessionStorage.setItem('key', key);
            window.sessionStorage.setItem('name', window.opener.sessionStorage.getItem('name'))
        }
    }
};

function addImage(file) {
    if (!file.files || !file.files[0]) {
        return;
    }
    let reader = new FileReader();
    reader.onload = function (data) {
        let result = data.target['result'];
        let html = getImmageItemHtml(result);
        $('#list').append(html)
        $('#number').text($('#list').children().length)
    };
    reader.readAsDataURL(file.files[0]);
}

function getImmageItemHtml(src) {
    return `<img class="list-item" src="${src}"/>`
}

function upload() {
    $('#message').text("正在上传...");
    $('#uploadbt').hide();

    if ($('#list').children().length === 0) {
        alert("你至少要选择一张图片进行上传")
    }
    let data = new FormData();
    data.append("name", window.sessionStorage.getItem('name'));
    data.append("key", window.sessionStorage.getItem('key'));
    data.append("title", NotNull($('#title').val()));
    data.append("keyword", NotNull($('#keyword').val()));
    data.append("introduction", NotNull($('#introduction').val()));
    let images = $('#list').children();
    for (let i = 0; i < images.length; i++) {
        let image = images[i];
        let blob = dataURLtoBlob(image.src);
        data.append("file", blob);
    }
    $.ajax({
        url: "/submission/upload",
        type: "POST",
        processData: false,
        contentType: false,
        traditional: true,
        data: data,
        success: function (data) {
            let json = JSON.parse(data);
            if (json['code'] === 6) {
                alert('登录信息已失效，请重新登录');
                window.open("sign_in", "登录")
            } else {
                $('#message').text(json['message']);
                if (json['code'] === 0) {
                    alert('发布成功');
                    window.close();
                }
            }
            $('#uploadbt').show()
        }
    });
}

/**
 * @return {string}
 */
function NotNull(obj) {
    if (obj == null) {
        return ""
    }
    return obj
}