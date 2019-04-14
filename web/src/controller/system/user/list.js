
/**

 @Name：##{jsName}##
 @Author：##{jsAuthor}##
 @Site：##{jsSite}##
 @Data：##{jsData}##


 */

layui.define(['admin','form','table', 'upload'], function(exports){

    var $ = layui.$
        ,admin = layui.admin
        ,view = layui.view
        ,table = layui.table
        ,form = layui.form;

    form.render(null, 'form-list');

    //监听搜索
    form.on('submit(search)', function(data){
        var field = data.field;
        //执行重载
        table.reload('table-list', {
            where: field
        });
    });

    // 监听回车搜索框事件
    document.onkeydown = function(e){
        var ev =document.all ? window.event : e;
        if(ev.keyCode==13) {
            layui.table.reload('table-list'); //重载表格
            return false
        }
    };


    //table列表
    table.render({
        elem: '#table-list'
        ,url: '/sys/sysUser/list' //列表接口
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', width: 80, title: 'ID', sort: true}
            ,{field: 'loginname', title: '登录名'}
            ,{field: 'telphone', title: '手机'}
            ,{field: 'email', title: '邮箱'}
            ,{field: 'role', title: '角色'}
            ,{field: 'jointime', title: '加入时间', sort: true}
            ,{field: 'check', title:'审核状态', templet: '#buttonTpl', minWidth: 80, align: 'center'}
            ,{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-button'}
        ]]
        ,text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(table-list)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('确定删除此信息？', function(index){
                console.log(obj)
                obj.del();
                layer.close(index);
            });
        }else if(obj.event === 'edit'){
            admin.popup({
                title: '编辑'
                ,area: ['50%', '80%']
                ,id: 'edit-button'
                ,success: function(layero, index){
                    view(this.id).render('system/user/edit', data).done(function(){
                        form.render(null, 'form-edit');

                        //监听提交
                        form.on('submit(submit-edit)', function(data){
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
    });


    // 监听按钮事件
    $('.layui-btn.layuiadmin-btn-form').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 按钮事件
    var active = {

        // 批量删除按钮
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
    exports('system/user/list', {});
})