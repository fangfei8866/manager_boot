/*
 * @Author: Paco
 * @Date:   2017-02-07
 * +----------------------------------------------------------------------
 * | jqadmin [ jq酷打造的一款懒人后台模板 ]
 * | Copyright (c) 2017 http://jqadmin.jqcool.net All rights reserved.
 * | Licensed ( http://jqadmin.jqcool.net/licenses/ )
 * | Author: Paco <admin@jqcool.net>
 * +----------------------------------------------------------------------
 */

layui.define(['jquery', 'dtable', 'jqdate', 'jqform', 'upload'], function(exports) {
    var $ = layui.jquery,
        list = layui.dtable,
        laydate = layui.laydate,
        form = layui.jqform,
        oneList = new list();
    form.set({
        "change": true,
        "form": "#form1"
    }).init();
    
    exports('table', {});
});