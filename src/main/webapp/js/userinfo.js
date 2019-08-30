let json = null;

window.onload = function () {
    if (window.opener != null) {
        let key = window.opener.sessionStorage.getItem('key');
        let name = window.opener.sessionStorage.getItem('name');
        window.sessionStorage.setItem('key', key);
        window.sessionStorage.setItem('name', name)
        $.get("user/get/data", {name: name, key: key}, function (data) {
            json = JSON.parse(data);
            if (json['code'] === 0) {
                //header
                $('#head-icon').attr('src', `/image/source/head/${json['id']}`);
                //info
                $('#nickname').val(json['nickname']);
                $('#word').val(json['word']);
                if (json['sex'] === 1) {
                    $('#sex1').attr('checked', true)
                } else if (json['sex'] === 2) {
                    $('#sex2').attr('checked', true)
                } else {
                    $('#sex0').attr('checked', true)
                }
                $('#username').val(name)
            } else {
                alert('登录信息已失效，请重新登录');
                window.opener.open('sign_in', '登录');
                window.close()
            }
        })
    }
};

function uploadHeader() {
    window.open("cropping?id=" + getQueryVariable('id'))
}

function updateInfo() {
    let sex = 0;
    let sexval = $("input[name='sex']:checked");
    if (sexval.attr('id') === 'sex1') {
        sex = 1
    } else if (sexval.attr('id') === 'sex2') {
        sex = 2
    }
    let json = {"nickname": $('#nickname').val(), "word": $('#word').val(), "sex": sex};
    $.get("/user/set/data", {
        name: window.sessionStorage.getItem('name'),
        key: window.sessionStorage.getItem('key'),
        args: JSON.stringify(json)
    }, function (code) {
        if (code === -1) {
            alert('登录信息已失效，请重新登录');
            window.open('sign_in', '登录');
            $('#message1').hide()
        } else {
            $('#message1').text('保存成功')
            $('#message1').show()
        }
    })
}

function updateUserName() {
    $.get("/user/set/name", {
        name: window.sessionStorage.getItem('name'),
        key: window.sessionStorage.getItem('key'),
        args: $('#username').val()
    }, function (data) {
        if (data === -1) {
            $('#message2').text('登录信息已失效，请重新登录');
            window.open('sign_in', '登录');
        } else {
            let json = JSON.parse(data);
            if (json['code'] === 0) {
                alert('修改成功，需要重新登录');
                window.close()
            } else {
                $('#message2').text(json['message']);
            }
        }
    })
}

function updatePassword() {
    let pass = [$('#pass2').val(), $('#pass3').val()]
    if (pass[0] !== pass[1]) {
        $('#message3').text("两次输入的密码不一致")
    } else {
        $.get("/user/set/password", {
            name: window.sessionStorage.getItem('name'),
            key: window.sessionStorage.getItem('key'),
            password: $('#pass1').val(),
            args: pass[0]
        }, function (data) {
            if (data === -1) {
                $('#message3').text('登录信息已失效，请重新登录');
                window.open('sign_in', '登录');
            } else {
                let json = JSON.parse(data)
                if (json['code'] === 0) {
                    alert('修改成功，需要重新登录');
                    window.close()
                } else {
                    $('#message3').text(json['message']);
                }
            }
        })
    }
}