let json = null;

window.onload = $.get('content/get/' + getQueryVariable('id'), function (data) {
    json = JSON.parse(data)
    let images = json['show'];
    for (let i = 0; i < images.length; i++) {
        $('#list').append(`<img class="atlas-image" src="${images[i]}"/>`);
        $('#list').append(`<span class="atlas-image-source" onclick="openSourceURL('${images[i]}')">下载原图</span>`)
    }
    if (window.opener != null) {
        watch(json['id'], window.opener.sessionStorage.getItem('name'), window.opener.sessionStorage.getItem('key'))
    }
});

function openSourceURL(url) {
    let source = url.replace('show', 'source');
    window.open(source)
}

function watch(id, name, key) {
    if (key != null) {
        $.get('interactive/watch/' + id, {
            name: name, key: key
        })
    }
}