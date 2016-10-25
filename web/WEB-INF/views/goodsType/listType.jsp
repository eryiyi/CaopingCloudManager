<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javaScript:void(0)">主页</a></li>
            <li><a href="javascript:void (0);">商城分类</a></li>
            <li><a href="javascript:void (0);">商城分类列表</a></li>
        </ol>
        <div id="social" class="pull-right">
            <a href="#"><i class="fa fa-google-plus"></i></a>
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-youtube"></i></a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>商城分类列表</span>
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
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>商城分类图片</th>
                        <th>商城分类名称</th>
                        <th>商城分类介绍</th>
                        <th>排序/倒序</th>
                        <th>是否禁用</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td><img class="img-rounded" width="50" height="50" src="${e.typeCover}"></td>
                            <td>${e.typeName}</td>
                            <td>${e.typeContent}</td>
                            <td>${e.type_num}</td>
                            <td>
                                <c:if test="${e.typeIsUse == '0'}">未禁用</c:if>
                                <c:if test="${e.typeIsUse == '1'}">已禁用</c:if>
                            </td>
                            <td>
                                <button class="btn btn-info" type="button" onclick="toUpdate('${e.typeId}')">修改</button>
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
    function toUpdate(_id) {
        $.ajax({
            type: "GET",
            data: {"typeId": _id},
            url: "/toUpdateType.do",
            success: function (response) {
                $("#content").html(response);
            }
        });
    }
</script>


