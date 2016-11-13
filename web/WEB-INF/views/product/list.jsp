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
            <li><a href="javaScript:void(0)">产品管理</a></li>
            <li><a href="javaScript:void(0)">产品列表</a></li>
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
                    <span>产品列表</span>
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
                <!-- style -->
                <style>
                    .w12 {
                        max-width: 12rem;
                    }
                </style>
                <!-- style -->
                <form class="form-inline">

                    <div class="form-group">

                        <div class="col-sm-6">
                            <input class="form-control" id="keyWords" value="${query.keyWords}" type="text"
                                   placeholder="产品名称">
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-sm-6">
                            <select class="form-control w12" id="is_type">
                                <option value="">--类型--</option>
                                <option value="0" ${query.is_type=='0'?'selected':''}>草坪</option>
                                <option value="1" ${query.is_type=='1'?'selected':''}>草种</option>
                                <option value="1" ${query.is_type=='2'?'selected':''}>机械</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-6">
                            <select class="form-control w12" id="cloud_is_use">
                                <option value="">--是否下架--</option>
                                <option value="0" ${query.cloud_is_use=='0'?'selected':''}>否</option>
                                <option value="1" ${query.cloud_is_use=='1'?'selected':''}>是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">

                        <div class="col-sm-6">
                            <select class="form-control w12" id="cloud_is_del">
                                <option value="">--是否删除--</option>
                                <option value="0" ${query.cloud_is_del=='0'?'selected':''}>否</option>
                                <option value="1" ${query.cloud_is_del=='1'?'selected':''}>是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <button type="submit" onclick="nextPage('1')"
                                class="btn form-control btn-warning btn-sm btn-block">搜索
                        </button>
                    </div>
                </form>

                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>产品名称</th>
                        <th>会员名</th>
                        <th>类别</th>
                        <th>销售价格</th>
                        <th>已售数量</th>
                        <th>发布时间</th>
                        <th>是否下架</th>
                        <th>是否删除</th>
                        <th>操作</th>
                        <th>操作</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${e.cloud_caoping_title}</td>
                            <td>${e.emp_name}</td>
                            <td>
                                <c:if test="${e.is_type == '0'}">草坪</c:if>
                                <c:if test="${e.is_type == '1'}">草种</c:if>
                                <c:if test="${e.is_type == '2'}">机械</c:if>
                            </td>
                            <td>${e.cloud_caoping_prices}</td>
                            <td>${e.sale_count}</td>
                            <td>${um:format(e.cloud_caoping_dateline, 'yyyy-MM-dd')}</td>
                            <td>
                                <c:if test="${e.cloud_is_use == '0'}">否</c:if>
                                <c:if test="${e.cloud_is_use == '1'}">是</c:if>
                            </td>
                            <td>
                                <c:if test="${e.cloud_is_del == '0'}">否</c:if>
                                <c:if test="${e.cloud_is_del == '1'}">是</c:if>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm" href="#module=/paihang/toTuijian&cloud_caoping_id=${e.cloud_caoping_id}" role="button">推荐</a>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm" href="#module=/productController/toEdit&cloud_caoping_id=${e.cloud_caoping_id}" role="button">编辑</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div style="margin-top: 20px;border-top: 1px solid #dedede;padding-bottom:15px; height: 50px">
                    <span style="line-height:28px;margin-top:25px;padding-left:10px; float: left">共${page.count}条/${page.pageCount}页</span>
                    <ul class="pagination" style="padding-left:100px; float: right">
                        <li>
                            <a style="margin-right:20px">每页显示&nbsp;<select name="size" id="size"
                                                                           onchange="nextPage('1')">
                                <option value="10" ${query.size==10?'selected':''}>10</option>
                                <option value="20" ${query.size==20?'selected':''}>20</option>
                                <option value="30" ${query.size==30?'selected':''}>30</option>
                                <option value="100" ${query.size==100?'selected':''}>100</option>
                            </select>&nbsp;条</a>
                        </li>
                        <c:choose>
                            <c:when test="${page.page == 1}">
                                <li><a href="javascript:void(0)">首页</a></li>
                                <li><a href="javascript:void(0)"><span class="left">《</span></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="javascript:void(0);" onclick="nextPage('1')">首页</a></li>
                                <li><a href="javascript:void(0);" onclick="nextPage('${page.page-1}')"><span
                                        class="left">《</span></a></li>
                            </c:otherwise>
                        </c:choose>
                        <li><a style="height: 30px; width: 100px">第<input style="width: 40px;height:20px;" type="text"
                                                                          id="index" name="index"
                                                                          onkeyup="searchIndex(event)"
                                                                          value="${page.page}"/> 页</a></li>

                        <c:choose>
                            <c:when test="${page.page == page.pageCount}">
                                <li><a href="javascript:void(0)"><span class="right">》</span></a></li>
                                <li><a href="javascript:void(0)">末页</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="javascript:void(0);" onclick="nextPage('${page.page+1}')"><span
                                        class="right">》</span></a></li>
                                <li><a href="javascript:void(0);" onclick="nextPage('${page.pageCount}')">末页</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function searchIndex(e) {
        if (e.keyCode != 13) return;
        var _index = $("#index").val();
        var keyWords = $("#keyWords").val();
        var cloud_is_use = $("#cloud_is_use").val();
        var is_type = $("#is_type").val();
        var cloud_is_del = $("#cloud_is_del").val();
        var size = getCookie("contract_size");
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=/productController/list&page=" + _index + "&is_type="+is_type + "&cloud_is_del="+cloud_is_del+
            "&size=" + size+ "&cloud_is_use=" + cloud_is_use+ "&keyWords="
            + keyWords + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function nextPage(_page) {
        var page = parseInt(_page);
        var keyWords = $("#keyWords").val();
        var cloud_is_use = $("#cloud_is_use").val();
        var is_type = $("#is_type").val();
        var cloud_is_del = $("#cloud_is_del").val();
        var size = $("#size").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1)) {
            window.location.href = "#module=/productController/list&page=" + page +
            "&is_type="+is_type+
            "&size=" + size+   "&cloud_is_del=" + cloud_is_del+
            "&cloud_is_use=" + cloud_is_use+
            "&keyWords=" + keyWords
            + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }

    function deletePaopaoGoods(_id) {
        if (!confirm("确定要删除该商品？")) {
            return;
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: "/productController/delete.do",
            data: {"id": _id},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("删除成功");
                    var _index = $("#index").val();
                    var size = getCookie("contract_size");
                    var keyWords = $("#keyWords").val();
                    var is_type = $("#is_type").val();
                    var cloud_is_use = $("#cloud_is_use").val();
                    var cloud_is_del = $("#cloud_is_del").val();
                    window.location.href = "#module=/productController/list&page=" + _index +"&is_type="+is_type+ "&size=" + size+ "&cloud_is_del=" + cloud_is_del + "&cloud_is_use=" + cloud_is_use
                    + "&keyWords=" + keyWords + "&_t=" + new Date().getTime();
                } else {
                    alert(data.message)
                }
            }
        });
    }


</script>


