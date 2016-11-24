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
            <li><a href="javascript:void(0)">草种规格</a></li>
            <li><a href="javascript:void(0)">草种规格</a></li>
        </ol>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>草种规格</span>
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
                <h4 class="page-header">编辑草种规格</h4>

                <form class="form-horizontal" role="form">
                    <input type="hidden" id="cloud_caozhong_guige_id" name="cloud_caozhong_guige_id" value="${cpguige.cloud_caozhong_guige_id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">规格描述</label>

                        <div class="col-sm-4">
                            <input type="text" id="cloud_caozhong_guige_cont" value="${cpguige.cloud_caozhong_guige_cont}" class="form-control" placeholder="规格描述"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序</label>

                        <div class="col-sm-4">
                            <input type="text" id="cloud_caozhong_guige_num" value="${cpguige.cloud_caozhong_guige_num}" class="form-control" placeholder="排序 数字"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
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
        var cloud_caozhong_guige_id = $("#cloud_caozhong_guige_id").val();
        var cloud_caozhong_guige_cont = $("#cloud_caozhong_guige_cont").val();
        var cloud_caozhong_guige_num = $("#cloud_caozhong_guige_num").val();

        if (cloud_caozhong_guige_cont.replace(/\s/g, '') == '') {
            alert("描述不能为空");
            return;
        }

        var regInt = /^([0-9]\d*)$/;
        if (cloud_caozhong_guige_num.replace(/\s/g, '') == '') {
            alert("排序不能为空");
            return;
        } else {
            if (!regInt.test(cloud_caozhong_guige_num)) {
                alert("排序必须是整数！");
                return;
            }
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: "/cpCaozhongGuigeController/edit.do",
            data: {
                "cloud_caozhong_guige_id": cloud_caozhong_guige_id,
                "cloud_caozhong_guige_cont": cloud_caozhong_guige_cont,
                "cloud_caozhong_guige_num": cloud_caozhong_guige_num
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("执行成功");
                    window.location.href = "#module=cpCaozhongGuigeController/list"+ "&_t="+ new Date().getTime();
                } else {
                    alert(data.message);
                }
            }
        });
    }
</script>


