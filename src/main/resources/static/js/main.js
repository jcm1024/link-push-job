$(document).ready(function () {
    console.log(SysHelper.host() + SysHelper.initSysInfoUrl);
    SysHelper.initSystemInfo(SysHelper.host() + SysHelper.initSysInfoUrl);

    $("#exeCmd").click(function () {
        const cmd = $("#cmd").val();
        SysHelper.executeCommand(SysHelper.host() + SysHelper.executeCmdUrl, cmd);
    });

});

const SysHelper = {
    initSysInfoUrl: "/test/getOsInfo",
    executeCmdUrl: "/test/execute",
    host: function () {
        const reqUrl = "http://" + window.location.host + "/link-push-job";
        return reqUrl;
    },
    login: function () {

    },
    initSystemInfo: function (reqUrl) {
        $.ajax({
            url: reqUrl,
            type: "post",
            async: false,
            success: function (res) {
                console.log(res);
                const sysInfoNode = document.getElementById("sysInfo");
                for (const val in res) {
                    const liNode = document.createElement("li");
                    liNode.innerHTML = '<span style="color: red;">' + val + '</span>'
                        + ' >> '
                        + '<span style="color: blue;">' + res[val] + '</span>';
                    sysInfoNode.appendChild(liNode);
                }
            },
            fail: function (err) {
            }
        });
    },
    executeCommand: function (reqUrl, cmd) {
        $.ajax({
            url: reqUrl,
            type: "post",
            data: "command=" + cmd,
            async: false,
            success: function (res) {
                console.log(res);
                $("#exeRes").val(res);
            },
            fail: function (err) {
            }
        });
    }
}