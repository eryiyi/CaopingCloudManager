<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javascript:void(0)" onclick="toPage('mainPage','')">主页</a></li>
            <li><a href="javascript:void(0)">物流车长</a></li>
            <li><a href="javascript:void(0)">车长</a></li>
        </ol>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>物流车长</span>
                </div>
                <div class="box-icons">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="expand-link">
                        <i class="fa fa-expand"></i>
                    </a>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
                <div class="no-move"></div>
            </div>
            <div class="box-content">
                <h4 class="page-header">编辑车长</h4>

                <form class="form-horizontal" role="form">
                    <input type="hidden" id="car_type_id" name="car_type_id" value="${cptype.car_length_id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">车长</label>

                        <div class="col-sm-4">
                            <input type="text" id="car_type_name" value="${cptype.car_length_name}" class="form-control" placeholder="车型"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序</label>

                        <div class="col-sm-4">
                            <input type="text" id="car_num" value="${cptype.car_num}" class="form-control" placeholder="排序 数字"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否禁用</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="is_use">
                                <option value="">--选择--</option>
                                <option value="0" ${cptype.is_use=='0'?'selected':''}>否</option>
                                <option value="1" ${cptype.is_use=='1'?'selected':''}>是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveP()">添加</button>
                            <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回
                            </button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function saveP() {
        var car_type_id = $("#car_type_id").val();
        var car_type_name = $("#car_type_name").val();
        var car_num = $("#car_num").val();
        var is_use = $("#is_use").val();
        if (car_type_name.replace(/\s/g, '') == '') {
            alert("车型不能为空");
            return;
        }

        var regInt = /^([0-9]\d*)$/;
        if (car_num.replace(/\s/g, '') == '') {
            alert("排序不能为空");
            return;
        } else {
            if (!regInt.test(car_num)) {
                alert("排序必须是整数！");
                return;
            }
        }
        if (is_use.replace(/\s/g, '') == '') {
            alert("请选择是否禁用！");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/carLengthController/edit.do",
            data: {
                "car_length_id": car_type_id,
                "car_length_name": car_type_name,
                "is_use": is_use,
                "car_num": car_num
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("执行成功");
                    window.location.href = "#module=carLengthController/list"+ "&_t="+ new Date().getTime();
                } else {
                    alert(data.message);
                }
            }
        });
    }
</script>


