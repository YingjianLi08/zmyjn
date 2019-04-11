/**
 * ##{editHtmlTitle}## js
 */

layui.config({
    base: '../../../js/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'admin', 'form', 'upload'], function () {
    /** 插件 **/
    var $ = layui.$
        , form = layui.form
        , admin = layui.admin;
    /** 插件end **/

    /** 页面加载 **/
    var id = getQueryString("id");

    if (id != null) {

        admin.req({
            url: "/##{requestMappingName}##/init",
            type: "get",
            data: {id: id},
            done: function (data) {

                //表单初始赋值
                form.val('layuiadmin-form-list', {
                    ##{editJs}##
                    "status1" : data.data.status1 == 1 ? true : false //开关状态
                })
            }
        });
    }
    /** 页面加载end **/


    /** 监听提交 **/
    form.on('submit(layuiadmin-form-submit)', function (data) {

        if (data.field.status1 == "on") {
            data.field.status1 = "1"
        } else {
            data.field.status1 = "0"
        }

        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

        //修改
        data.field.id = id;
        var field = data.field; //获取提交的字段
        //console.log(field);
        admin.req({
            url: "/##{requestMappingName}##/updateSave",
            type: "post",
            data: field,
            done: function (data) {
                parent.layui.table.reload('LAY-list'); //重载表格
                parent.layer.close(index); //再执行关闭
            }
        });

    })
    /** 监听提交end **/


});