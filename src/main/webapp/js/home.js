window.onload = function () {
    getContent();
    let key = window.sessionStorage.getItem('key');
    if (key != null) {
        $('#login').hide();
        $('#icon').show();
        $('#icon').attr('src', 'image/small/head/' + window.sessionStorage.getItem('id'))
    } else {
        $('#login').show();
        $('#icon').hide()
    }
    window.sessionStorage.setItem('flag', 'home')
};

$(window).scroll(function () {
    const scrollTop = $(this).scrollTop();
    const scrollHeight = $(document).height();
    const windowHeight = $(this).height();
    if (scrollTop + windowHeight + 8 >= scrollHeight) {
        getContent()
    }
});

let g_ids = [];

function getContent() {
    $.get(
        "/content/get",
        {number: 4, filter_id: JSON.stringify(g_ids)},
        function (data) {
            const json = JSON.parse(data);
            const content = json['content'];
            if (content.length > 0) {
                for (let i = 0; i < content.length; i++) {
                    g_ids.push(content[i]['id']);
                    const html = createAtlas(content[i]);
                    $('#list').append(html);
                    $('#end').hide()
                }
            } else {
                $('#end').show()
            }
        }
    );
}

function createAtlas(content) {
    function images(url) {
        let str = '<div class="atlas">';
        for (let i = 0; i < url.length; i++) {
            str += `<img class="atlas-item" style="cursor: pointer" onclick="showImage(this)" src="${url[i]}"/>`;
        }
        return str + '</div>'
    }

    const head = ('/image/small/head/' + content['author']);
    const html =
        `<div class="item" id="${content['id']}">` +
        `<img class="round_icon" src="${head}" author="${content['author']}" onclick="openAuthorSpace(this)"/>` +
        `<div class="item-box">` +
        `<div class="nickname">${content['authorname']}</div>` +
        `<div class="time">${getDate(content['releasetime'])}</div>` +
        `<div class="title">${content['title']}</div>` +
        `<div><span class="keyword">#${content['keyword']}#&nbsp&nbsp</span><span class="introduction">${content['introduction']}</span></div>` +
        `${images(content['thumbnails'])}` +
        `<div class="status" >` +
        `<div class="status-item"><img src="image/image.png"><span >${content['number']}</span></div>` +
        `<div class="status-item"><img style="cursor:pointer;" src="image/star.png" onclick="star(this)"><span>${content['star']}</span></div>` +
        `<div class="status-item"><img src="image/watch.png"><span >${content['watch']}</span></div>` +
        `<div  class="status-item" onclick="openAtlas(this)"><span style="cursor:pointer;">查看图集</span></div>` +
        `</div>` +
        `</div>` +
        `</div>`;
    return html
}

function star(obj) {
    const key = window.sessionStorage.getItem('key');
    if (key == null) {
        alert('登录后才能点赞哦');
        window.open('sign_in', '登录')
    } else {
        $.get("interactive/star/" + obj.parentNode.parentNode.parentNode.parentNode.id, {
            name: window.sessionStorage.getItem('name'),
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

function watch(id) {
    const key = window.sessionStorage.getItem('key');
    if (key != null) {
        $.get('/interactive/watch/' + id, {
            name: window.sessionStorage.getItem('name'),
            key: key
        }, function (data) {
            //尝试重新登录
            if (data === -1) {
                $.get("login/sign_in", {
                    name: $.sessionStorage.getItem('name'),
                    password: $.sessionStorage.getItem('password')
                }, function (data) {
                    let json = JSON.parse(data);
                    let key = json['key'];
                    //不管成功不成功都替换掉，因为原来的key已经没用了
                    $.sessionStorage.setItem('key', key)
                })
            }
        })
    }
}

function showImage(image) {
    $(image).toggleClass('show');
    if ($(image).hasClass('show')) {
        const src = image.src;
        if (src.indexOf('small') !== -1) {
            image.src = src.replace('small', 'show')
        }
        watch(image.parentNode.parentNode.parentNode.id)
    }
}

function openAtlas(obj) {
    const id = obj.parentNode.parentNode.parentNode.id;
    window.open('atlas?id=' + id);
}

function openAuthorSpace(obj) {
    window.open('authorspace?id=' + $(obj).attr('author'))
}