window.onload = function () {
    if (window.opener === null) {
        alert('错误的打开方式');
        window.close()
    } else {
        let key = window.opener.sessionStorage.getItem('key')
        if (key === null) {
            alert('错误的打开方式');
            window.close()
        } else {
            window.sessionStorage.setItem('key', key);
            window.sessionStorage.setItem('name', window.opener.sessionStorage.getItem('name'));
            $.get("/user/get/authorinfo/" + getQueryVariable('id'), function (data) {
                $('#number').text(data['number']);
            }, "json");
            getContent()
        }
    }
};

$(window).scroll(function () {
    const scrollTop = $(this).scrollTop();
    const scrollHeight = $(document).height();
    const windowHeight = $(this).height();
    if (scrollTop + windowHeight + 8 >= scrollHeight) {
        getContent()
    }
});

let lastid = 0;

function getContent() {
    $.get(`content/get_author/${getQueryVariable('id')}`, {number: 18, lastid: lastid}, function (data) {
        let json = JSON.parse(data);
        let content = json['content'];
        for (let i = 0; i < content.length; i++) {
            let html = createItem(content[i]);
            $('#list').append(html);
            lastid = content[i]['id'];
        }
    });
}

function createItem(content) {
    return `<div class="list-item" id="${content['id']}">` +
        `<img class="list-item-img" src="${content['thumbnails'][0]}"/>` +
        `<div class="list-item-content">` +
        `<p class="list-item-title">${content['title']}</p>` +
        `<p class="list-item-time">${getDate(content['releasetime'])}</p>` +
        `<div class="list-item-status">` +
        `<img class="status-icon" src="/image/image.png"/><span class="status-text">${content['number']}</span>` +
        `<img class="status-icon" src="/image/watch.png"/><span class="status-text">${content['watch']}</span>` +
        `<img class="status-icon" src="/image/star.png"/><span class="status-text">${content['star']}</span>` +
        `</div>` +
        `<div class="show-root">` +
        `<img class="status-ic-more" src="/image/more.png"/>` +
        `<div class="show">` +
        `<p class="show-item" onclick="editItem(${content['id']})">编辑</p>` +
        `<p class="show-item" onclick="deleteItem(${content['id']})">删除</p>` +
        `</div>` +
        `</div>` +
        `</div>` +
        `</div>`
}

function deleteItem(id) {
    if (confirm("是否删除？")) {
        $.get("/submission/delete", {
            name: window.sessionStorage.getItem('name'),
            key: window.sessionStorage.getItem('key'),
            submission_id: id
        }, function (code) {
            if (code === 0) {
                let item = document.getElementById(id);
                item.parentNode.removeChild(a)
            } else {
                alert('登录信息已失效，请重新登录');
                window.open('sign_in', '登录');
            }
        })
    }
}

function editItem(id) {
    window.open('editsubmission?id=' + id);
}