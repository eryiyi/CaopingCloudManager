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
            <li><a href="javascript:void(0)">物流车长</a></li>
        </ol>
        <div id="social" class="pull-right">
            <a href="javascript:void(0)"><i class="fa fa-google-plus"></i></a>
            <a href="javascript:void(0)"><i class="fa fa-facebook"></i></a>
            <a href="javascript:void(0)"><i class="fa fa-twitter"></i></a>
            <a href="javascript:void(0)"><i class="fa fa-linkedin"></i></a>
            <a href="javascript:void(0)"><i class="fa fa-youtube"></i></a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
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
                <form class="form-inline">

                    <div class="form-group">
                        <select class="form-control w12" id="is_use">
                            <option value="">--是否禁用--</option>
                            <option value="0" ${query.is_use=='0'?'selected':''}>否</option>
                            <option value="1" ${query.is_use=='1'?'selected':''}>是</option>
                        </select>
                    </div>

                    <button type="submit" onclick="searchOrder('1')"
                            class="btn form-control btn-warning btn-sm btn-block">查找
                    </button>
                </form>
                <%--<p>For basic styling add the base class <code>.table</code> to any <code>&lt;table&gt;</code>.</p>--%>
                <div class="col-xs-3 col-sm-2">
                    <button type="button" onclick="addAd()" class="btn btn-default btn-sm">添加</button>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>车长</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${e.car_length_name}</td>
                            <td>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="editRole('${e.car_length_id}')" role="button">编辑</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function editRole(_id) {
        if (confirm("确定要编辑么？")) {
            window.location.href = "#module=carLengthController/toEdit&car_length_id="+_id + "&_t=" + new Date().getTime();
        }
    }

    function addAd() {
        window.location.href = "#module=carLengthController/toAdd" + "&_t=" + new Date().getTime();
    }
    function searchOrder() {
        var is_use = $("#is_use").val();
        window.location.href = "#module=carLengthController/list&is_use=" + is_use + "&_t=" + new Date().getTime();
    }
</script>


