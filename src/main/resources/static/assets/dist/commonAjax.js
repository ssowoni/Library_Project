
function callAjax(url, method, dataType, async, param, callback){

    $.ajax({
        url : url,
        method: method,
        dataType : dataType,
        async : async,
        data : param,
        success : function (data){
            callback(data);
        },
        error: function (xhr,status,err){
            console.log("xhr : " + xhr);
            console.log("status : " + status);
            console.log("err : " + err);
        }
    })

}