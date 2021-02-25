function sign_in() {
    let name = $('#name').val();
    let password = $('#password').val();
    $.get("login/sign_in", {name: name, password: password}, function (data) {
        let json = JSON.parse(data);
        if (json.code === 0) {
            let key = json['key'];
            $.get("user/get/data", {name: name, key: key}, function (info) {
                let json = JSON.parse(info);
                if (window.opener) {
                    const opener = window.opener;
                    let flag = opener.sessionStorage.getItem('flag');
                    if (flag === 'home') {
                        opener.sessionStorage.setItem('key', key);
                        opener.sessionStorage.setItem('name', name);
                        opener.sessionStorage.setItem('password', password);
                        opener.sessionStorage.setItem('sex', ['未知', '男', '女'][json['sex']]);
                        opener.sessionStorage.setItem('nickname', json['nickname']);
                        opener.sessionStorage.setItem('id', json['id']);
                        opener.sessionStorage.setItem('word', json['word']);
                        $('#login', opener.document).hide();
                        $('#icon-div', opener.document).show();
                        $('#icon', opener.document).attr('src', 'image/small/head/' + json['id']);
                    } else {
                        opener.sessionStorage.setItem('key', key);
                        opener.sessionStorage.setItem('name', name);
                    }
                }
                window.close()
            });
        }
        $('#message').text(json.message);
    })
}

function get_verify() {
    $.getJSON("login/create_verify_id", function (data) {
        $('#verify_text').text(data['problem']);
        $('#verify_text').attr('verify_id', data['id'])
    })
}

function sign_up() {
    let name = $('#name').val();
    let password = $('#password').val();
    let confirmPassword = $('#confirmPassword').val();
    if (password !== confirmPassword) {
        $('#message').text('两次输入的密码不一致')
    } else {
        $.get("login/sign_up", {
            name: name,
            password: password,
            id: $('#verify_text').attr('verify_id'),
            answer: $('#verify').val()
        }, function (data) {
            let json = JSON.parse(data);
            $('#message').text(json['message']);
            if (json['code'] === 0) {
                alert('注册成功，快去登录吧');
                window.location.href = 'sign_in'
            } else {
                get_verify()
            }
        })
    }
}