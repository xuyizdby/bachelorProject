layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    form.verify({
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        birthday: function (value) {
            if (value != '') {
                if (!isValidBirthday(value)) {
                    return '请输入合法的出生日期';
                }
            }
        }
        , age: function (value) {
            var regNum = /^[0-9]{1,2}$/;
            if (!regNum.test(value)) {
                return '请输入合法的年龄';
            }
        }
        , id: function (value) {
            if (!Number12(value)) {
                return '用户名必须为12位数字';
            }
        }
        , CMCid: function (value) {
            if (value == "") {
                return '此项不能为空';
            } else if (value.length > 10) {
                return '班级、专业、学院编号长度限制小于等于10个字符';
            }
        }
        , teaId: function (value) {
            if (!Number12(value)) {
                return '工号必须为12位数字';
            } else {
                if (value.charAt(0) != '1') {
                    return '工号第1位必须为1';
                }
            }
        }
        , stuId: function (value) {
            if (!Number12(value)) {
                return '学号必须为12位数字';
            } else {
                if (value.charAt(0) != '5') {
                    return '学号第1位必须为5';
                }
            }
        }
        , myidentity: function (value) {
            if (value == "") {
                return '请选择您的身份';
            }
        }
        , name: function (value) {
            if (value == "") {
                return '此项不能为空';
            } else if (value.length > 50) {
                return '姓名长度不能超过50个字符';
            }
        }
        , remark: function (value) {
            if (value.length > 500) {
                return '备注长度不能超过500个字符';
            }
        }
        //用户名-函数
        ,username: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
        }

        //密码-正则
        ,password: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
    });

    exports('myform', {})
});
