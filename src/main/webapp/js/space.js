window.onload = function () {
    if (window.opener != null) {
        let key = window.opener.sessionStorage.getItem('key');
        let name = window.opener.sessionStorage.getItem('name');
        window.sessionStorage.setItem('key', key);
        window.sessionStorage.setItem('name', name)
        $.get("user/get/data", {name: name, key: key}, function (data) {

        })
    }
};