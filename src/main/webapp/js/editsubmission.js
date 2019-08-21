window.onload = function () {
    if (window.opener === null) {
        alert('错误的打开方式');
        window.close()
    } else {
        let key = window.opener.sessionStorage.getItem('key');
        if (key === null) {
            alert('错误的打开方式');
            window.close()
        } else {
            window.sessionStorage.setItem('key', key);
            window.sessionStorage.setItem('name', window.opener.sessionStorage.getItem('name'));
            getContent()
        }
    }
};

function getContent() {
    $.get("/content/get/" + getQueryVariable('id'), function (data) {
        if (data.length === 0) {
            alert('稿件不存在');
            window.close()
        } else {
            let json = JSON.parse(data);
            $('#title').val(json['title']);
            $('#keyword').val(json['keyword']);
            $('#introduction').val(json['introduction'])
        }
    })
}

function post() {
    $.get("/submission/update", {
        name: window.sessionStorage.getItem('name'),
        key: window.sessionStorage.getItem('key'),
        submission_id: getQueryVariable('id'),
        title: $('#title').val(),
        keyword: $('#keyword').val(),
        introduction: $('#introduction').val()
    }, function (data) {
        let json = JSON.parse(data);
        if (json['code'] === 5) {
            alert('登录信息已失效，请重新登录');
            window.open('sign_in', '登录')
        } else {
            $('#message').text(json['message']);
            if (json['code'] === 0) {
                alert('修改成功，快去刷新查看一下吧');
                window.close()
            }
        }
    })
}