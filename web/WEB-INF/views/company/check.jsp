<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javascript:void (0);">主页</a></li>
            <li><a href="javascript:void (0);">公司管理</a></li>
            <li><a href="javascript:void (0);">公司审核</a></li>
        </ol>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>公司审核</span>
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
                <h4 class="page-header">信息详情</h4>

                <form class="form-horizontal" role="form">
                    <input type="hidden" id="company_id" value="${info.company_id}">
                    <input type="hidden" id="emp_id" value="${info.emp_id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司名称*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_name" placeholder="店铺名称" class="form-control"
                                   value="${info.company_name}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司联系人*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_person" placeholder="公司联系人" class="form-control"
                                   value="${info.company_person}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司电话*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_tel" placeholder="公司电话" class="form-control"
                                   value="${info.company_tel}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司地址*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_address" placeholder="公司地址" class="form-control"
                                   value="${info.company_address}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司介绍*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_detail" placeholder="公司介绍" class="form-control"
                                   value="${info.company_detail}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <input type="hidden" id="company_pic" name="company_pic" value="${info.company_pic}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">公司主图</label>

                        <div class="col-sm-10 col-md-2">
                            <img class="img-thumbnail" name="imagePath1" id="imageDiv1" style="cursor: pointer"
                                 src="${info.company_pic}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">*入驻时间*</label>

                        <div class="col-sm-4">
                            <input type="text" id="dateline" placeholder="入驻时间" class="form-control" readonly="true"
                                   value="${info.dateline}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">*省市县*</label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   value="${info.pname}${info.cityName}${info.areaName}" readonly="true" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否审核</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="is_check">
                                    <option value="">--请选择--</option>
                                    <option value="0" ${info.is_check=='0'?'selected':''}>未审核</option>
                                    <option value="1" ${info.is_check=='1'?'selected':''}>已审核</option>
                                </select>
                            </div>
                        </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否名企</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="is_paihang">
                                <option value="">--请选择--</option>
                                <option value="0" ${info.is_paihang=='0'?'selected':''}>否</option>
                                <option value="1" ${info.is_paihang=='1'?'selected':''}>是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">*名企排行置顶数字*</label>

                        <div class="col-sm-4">
                            <input type="text" id="paihang_num" placeholder="名企排行置顶数字 越大越靠前 最多两位数字" class="form-control"
                                   value="${info.paihang_num}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">到期日期</label>

                        <div class="col-sm-2">
                            <input type="date" id="end_time" value="${um:format(info.end_time, 'yyyy-MM-dd')}" class="form-control">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveManagerInfo()">审核</button>
                            <button type="button" class="btn btn-primary" onclick="saveManagerInfo1()">名企提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    function saveManagerInfo() {
        var company_id = $("#company_id").val();
        var emp_id= $("#emp_id").val();
        var  is_check= $("#is_check").val();
        if(is_check.replace(/\s/g, '') == ''){
            alert("请选择是否审核");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/companyController/updateCheck.do",
            data: {
                "company_id": company_id, "is_check": is_check
            },

            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("审核成功");
                    window.location.href = "#module=/companyController/list"+ "&_t="+ new Date().getTime();
                } else {
                    alert(data.message)
                }
            }
        });
    };

    function saveManagerInfo1() {
        var company_id = $("#company_id").val();
        var emp_id= $("#emp_id").val();
        var  is_paihang= $("#is_paihang").val();
        var  paihang_num= $("#paihang_num").val();
        var end_time = $("#end_time").val();

        if(is_paihang.replace(/\s/g, '') == ''){
            alert("请选择是否名企");
            return;
        }

        var reg = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;
        if (paihang_num.replace(/\s/g, '') == '') {
            alert("请输入名企排行置顶数字");
            return;
        } else {
            if (!reg.test(paihang_num)) {
                alert("请输入名企排行必须为合法数字(正数，最多两位小数)！");
                return;
            }
        }

        if (end_time.replace(/\s/g, '') == '') {
            alert("请选择到期日期！");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/companyController/updateCheck.do",
            data: {
                "company_id": company_id, "is_paihang": is_paihang, "paihang_num": paihang_num, "end_time": end_time
            },

            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("操作成功");
                    window.location.href = "#module=/companyController/list"+ "&_t="+ new Date().getTime();
                } else {
                    alert(data.message)
                }
            }
        });
    };
</script>

