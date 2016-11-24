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
            <li><a href="javascript:void (0);">供应商管理</a></li>
            <li><a href="javascript:void (0);">供应商审核</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>供应商审核</span>
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
                    <input type="hidden" id="apply_gys_id" value="${info.apply_gys_id}">
                    <input type="hidden" id="emp_id" value="${info.emp_id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司名称*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_name" placeholder="公司名称" class="form-control"  readonly="true"
                                   value="${info.company_name}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司法人*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_faren" placeholder="公司法人" class="form-control"  readonly="true"
                                   value="${info.company_faren}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司营业执照号*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_yzzz_num" placeholder="公司营业执照号" class="form-control"  readonly="true"
                                   value="${info.company_yzzz_num}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司地址*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_address" placeholder="公司地址" class="form-control"  readonly="true"
                                   value="${info.company_address}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*公司介绍*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_detail" placeholder="公司介绍" class="form-control"  readonly="true"
                                   value="${info.company_detail}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <input type="hidden" id="company_yzzz_pic" name="company_yzzz_pic"   readonly="true" value="${info.company_yzzz_pic}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">公司营业执照</label>

                        <div class="col-sm-10 col-md-2">
                            <img class="img-thumbnail" name="imagePath1" id="imageDiv1" style="cursor: pointer"
                                 src="${info.company_yzzz_pic}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">身份证正面</label>

                        <div class="col-sm-10 col-md-2">
                            <img class="img-thumbnail" name="imagePath2" id="imageDiv2" style="cursor: pointer"
                                 src="${info.company_faren_pic_z}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">身份证反面</label>

                        <div class="col-sm-10 col-md-2">
                            <img class="img-thumbnail" name="imagePath3" id="imageDiv3" style="cursor: pointer"
                                 src="${info.company_faren_pic_f}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">*申请时间*</label>

                        <div class="col-sm-4">
                            <input type="text" id="dateline_apply" placeholder="申请时间" class="form-control" readonly="true"
                                   value="${um:format(info.dateline_apply, 'yyyy-MM-dd')}" data-toggle="tooltip" data-placement="bottom"
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
                        <label class="col-sm-2 control-label">*申请人*</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_name" placeholder="申请人" class="form-control"  readonly="true"
                                   value="${info.emp_name}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*申请人手机号*</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_mobile" placeholder="申请人手机号" class="form-control"  readonly="true"
                                   value="${info.emp_mobile}" data-toggle="tooltip" data-placement="bottom"
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
                                    <option value="2" ${info.is_check=='2'?'selected':''}>不通过</option>
                                </select>
                            </div>
                        </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*审核原因*</label>
                        <div class="col-sm-4">
                            <input type="text" id="check_reason" placeholder="审核原因" class="form-control"
                                   value="${info.check_reason}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveManagerInfo()">审核</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    function saveManagerInfo() {
        var apply_gys_id = $("#apply_gys_id").val();
        var check_reason = $("#check_reason").val();
        var  is_check= $("#is_check").val();
        var  emp_id= $("#emp_id").val();
        if(is_check.replace(/\s/g, '') == ''){
            alert("请选择是否审核");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/applyGysController/check.do",
            data: {
                "apply_gys_id": apply_gys_id,
                "check_reason": check_reason,
                "emp_id": emp_id,
                "is_check": is_check
            },

            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("审核成功");
                    window.location.href = "#module=/applyGysController/list"+ "&_t="+ new Date().getTime();
                } else {
                    alert(data.message)
                }
            }
        });
    };


</script>

