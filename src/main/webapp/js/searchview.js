window.onload = function () {
    if (window.sessionStorage.getItem('value') == null) {
        if (window.opener != null && window.opener.sessionStorage.getItem('searchvalue') != null) {
            search(window.opener.sessionStorage.getItem('searchvalue'));
            $('#value').val(window.opener.sessionStorage.getItem('searchvalue'))
        }
    } else {
        $('#value').val(window.sessionStorage.getItem('value'));
        search(window.sessionStorage.getItem('value'));
    }
    let view = getQueryVariable('view');
    if (view === 'keyword') {
        $('#keyword').addClass('nav-activity')
    } else if (view === 'user') {
        $('#user').addClass('nav-activity')
    } else {
        $('#title').addClass('nav-activity')
    }
    if (window.opener != null) {
        let key = window.opener.sessionStorage.getItem('key');
        if (key != null) {
            window.sessionStorage.setItem('key', key);
            window.sessionStorage.setItem('name', window.opener.sessionStorage.getItem('name'))
        }
    }
};

function search(value) {
    if (value.length !== 0) {
        window.sessionStorage.setItem('value', value);
        $.get("/search", {value: value}, function (data) {
            let json = JSON.parse(data);
            let view = getQueryVariable('view');
            if ('keyword' === view) {
                setContent(json['keyword'], true)
            } else if ('user' === view) {
                setContent(json['user'], false)
            } else {
                setContent(json['title'], true)
            }
            $('#title').text(`标题 ${json['title'].length}`);
            $('#keyword').text(`关键字 ${json['keyword'].length}`);
            $('#user').text(`用户名 ${json['user'].length}`)
        })
    }
}

function setContent(contents, atlas) {
    function createItem(content) {
        return `<div class="list-item" onclick="openRes(${content['id']}, ${atlas})">` +
            `<img class="list-item-icon" src="${content['icon']}">` +
            `<span class="list-item-value" title="${content['value']}">${content['value']}</span>` +
            `<div/>`
    }

    let list = $('#list');
    list.text('');

    for (let i = 0; i < contents.length; i++) {
        list.append(createItem(contents[i]));
    }
}

function openRes(id, atlas) {
    if (atlas) {
        window.open('atlas?id=' + id)
    } else {
        window.open('authorspace?id=' + id)
    }
}
