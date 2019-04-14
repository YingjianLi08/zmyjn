
/**

 @Name：##{jsName}##
 @Author：##{jsAuthor}##
 @Site：##{jsSite}##
 @Data：##{jsData}##


 ##{models}## : 'admin','form','table'
 ##{$$}## : layui.$,admin = layui.admin,view = layui.view,table = layui.table,form = layui.form;
 */

layui.define(['admin','form','table', 'upload'], function(exports){

    var $ = layui.$
        ,admin = layui.admin
        ,view = layui.view
        ,table = layui.table
        ,form = layui.form;

    form.render(null, 'form-edit');

    var id = $("[name=id]").val();

    // 根据id给表单赋值
    admin.req({
        url: "/sys/sysUser/init",
        type: "get",
        data: {id:id},
        done: function (data) {

            var avatar = data.data.id == 1 ? 'a':'b';

            var status1 = data.data.status1 == "" ? "":"";

            //表单初始赋值
            form.val('form-edit', {
               "userName":data.data.userName,//
               "nickname":data.data.nickname,//
               "mobile":data.data.mobile,//
               "email":data.data.email,//
               "avatar":avatar,//
               "status1":"checked",//
               "role":"5",//
            });
        }
    });

    //表单初始赋值
    // form.val('form-edit', {
    //
    //     "userName" : "123", //
    //     "nickname" : "456" //
    // })



    // 监听按钮事件
    $('.layui-btn.layuiadmin-btn-form').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 按钮事件
    var active = {

        // 重置按钮
        batchdel: function(){
            var checkStatus = table.checkStatus('table-list')
                ,checkData = checkStatus.data; //得到选中的数据

            if(checkData.length === 0){
                return layer.msg('请选择数据');
            }

            layer.confirm('确定删除吗？', function(index) {

                //执行 Ajax 后重载
                /*
                admin.req({
                  url: 'xxx'
                  //,……
                });
                */
                table.reload('table-list');
                layer.msg('已删除');
            });

        },

        // 添加按钮
        add: function(){
            admin.popup({
                title: '添加用户'
                ,area: ['50%', '80%']
                ,id: 'add-button'
                ,success: function(layero, index){
                    view(this.id).render('system/user/add').done(function(){
                        form.render(null, 'form-add');

                        //监听提交
                        form.on('submit(LAY-user-back-submit)', function(data){
                            var field = data.field; //获取提交的字段

                            //提交 Ajax 成功后，关闭当前弹层并重载表格
                            //$.ajax({});
                            layui.table.reload('table-list'); //重载表格
                            layer.close(index); //执行关闭
                        });
                    });
                }
            });
        }
    };



    //对外暴露的接口
    exports('system/user/edit', {});
})