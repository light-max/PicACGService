window.onload = function () {
    $.get("/user/get/authorinfo/" + getQueryVariable('id'), function (data) {
        if (data !== "-1") {
            let json = JSON.parse(data);
            $('#head-icon').attr('src', json['img_source']);
            $('#nickname').text(json['nickname']);
            let sex = 0;
            if (json['sex'] === '男') {
                sex = 1;
            } else if (json['sex'] === '女') {
                sex = 2;
            }
            $('#sex-icon').attr('src', ['image/sex-0.png', 'image/sex-1.png', 'image/sex-2.png'][sex]);
            $('#signature').text(json['word']);
            $('#number').text(json['number']);
            getContent()
        }
    });
    window.sessionStorage.setItem('flag', 'space');
    if (window.opener != null) {
        window.sessionStorage.setItem('key', window.opener.sessionStorage.getItem('key'));
        window.sessionStorage.setItem('name', window.opener.sessionStorage.getItem('name'))
    }
};

let lastid = 0;
let id = 0;

function getContent() {
    $.get(`content/get_author/${getQueryVariable('id')}`, {number: 18, lastid: lastid}, function (data) {
        let json = JSON.parse(data)
        let content = json['content']
        for (let i = 0; i < content.length; i++) {
            let html = createItem(content[i]);
            $('#list').append(html);
            lastid = content[i]['id']
        }
    })
}

function createItem(content) {
    return `<div class="item">` +
        `<div class="cover-back"><img class="cover" src="${content['show'][0]}"/></div>` +
        `<div class="click" onclick="openAtlas(${content['id']})"/>` +
        `<span class="title">${content['title']}</span>` +
        `<span class="date">${getDate(content['releasetime'])}</span>` +
        `<div class="status">` +
        `<img class="status-icon" src="image/image-w.png"/><span class="status-text">${content['number']}</span>` +
        `<img class="status-icon" src="image/watch-w.png"/><span class="status-text">${content['watch']}</span>` +
        `<img class="status-icon" src="image/star-w.png" style="cursor: pointer" onclick="star(${content['id']},this)"/><span class="status-text">${content['star']}</span>` +
        `</div>` +
        `</div>`
}

function getUserId() {
    return window.sessionStorage.getItem('key')
}

function getUserName() {
    return window.sessionStorage.getItem('name')
}

function star(id, obj) {
    let key = getUserId();
    if (key == null) {
        alert('需要登录才能点赞哟');
        window.open('sign_in', '登录')
    } else {
        $.get("interactive/star/" + id, {
            name: getUserName(),
            key: key
        }, function (data) {
            switch (data) {
                case -1:
                    alert("登录信息已失效，请重新登录");
                    window.open('sign_in', '登录');
                    break;
                case 0:
                    const text = obj.nextSibling;
                    const value = text.innerText;
                    text.innerText = parseInt(value) + 1;
                    break;
                case 1:
                    alert("你已经点过赞了，不能重复点赞");
                    break
            }
        });
    }
}

function openAtlas(id) {
    window.open('atlas?id=' + id)
}