var HOST_PORT;
var BASE_URL;

$(document).ready(function () {
    //获取当前url并填充HOST_PORT,填充URL
    HOST_PORT = getHostPortFromCurrentUrl() === "" ? "localhost" : getHostPortFromCurrentUrl();
    BASE_URL = "http://" + HOST_PORT + "/";
    //填充doc url
    $("#navigator_top_function_btn_docs").attr("href", BASE_URL + "docs");
});

/**
 * 发送GET ajax请求
 *
 * @param url 请求地址
 * @param succeeded 2回调方法(自定义回调)
 * <pre>
 *     function (data) {
 *     //data是已经通过JSON转换的对象
 *           alert(data);
 *       }
 * </pre>
 * @param failed 回调方法(自定义回调)
 * <pre>
 *     function (data, textCode) {
 *     //data是已经通过JSON转换的对象
 *           alert(data);
 *       }
 * </pre>
 */
function executeGetWithJson(url, succeeded, failed) {
    executeGet(url, 30000, succeeded, failed);
}

/**
 * 发送GET ajax请求
 *
 * @param url 请求地址
 * @param timeout 请求超时设置
 * @param succeeded 2回调方法(自定义回调)
 * <pre>
 *     function (data) {
 *     //data是已经通过JSON转换的对象
 *           alert(data);
 *       }
 * </pre>
 * @param failed 回调方法(自定义回调)
 * <pre>
 *     function (data, textCode) {
 *     //data是已经通过JSON转换的对象
 *           alert(data);
 *       }
 * </pre>
 */
function executeGet(url, timeout, succeeded, failed) {
    $.ajax({
        url: url,
        type: "get",
        contentType: "application/json;charset=utf-8",
        timeout: timeout,
        //设置可跨域
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            succeeded(data);
        },
        error: function (jqXHR, textStatus) {
            failed(jqXHR.responseJSON, textStatus);
        }
    });
}

/**
 * 获取url上的host_port
 */
function getHostPortFromCurrentUrl() {
    var url = window.location.href;
    if (url.indexOf("localhost") !== -1) {
        return "";
    }
    var reg = new RegExp("^http(s)?://(.*)/.*$");
    if (reg.test(url) === true) {
        var host_port = url.substring(url.indexOf("/") + 2);
        host_port = host_port.substring(0, host_port.indexOf("/"));
        return host_port;
    }
    return "";
}