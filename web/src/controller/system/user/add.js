
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

    form.render(null, 'form-add');

})