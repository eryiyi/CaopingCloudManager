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
            <li><a href="javaScript:void(0)">会员管理</a></li>
            <li><a href="javaScript:void(0)">会员列表</a></li>
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
                    <span>会员列表</span>
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
                    <div class="col-sm-4 col-xs-3">
                        <div class="col-sm-6">
                            <input class="form-control" id="phone_number" value="${query.phoneNumber}" type="text"
                                   placeholder="手机号">
                        </div>
                        <div class="col-sm-6">
                            <input class="form-control" id="keyWords" value="${query.keyWords}" type="text"
                                   placeholder="昵称">
                        </div>
                    </div>

                    <div class="col-sm-4 col-xs-3">
                        <div class="col-sm-6">
                            <select class="form-control w12" id="level_id">
                                <option value="" >请选择等级</option>
                                <c:forEach items="${levels}" var="e" varStatus="st">
                                    <option value="${e.levelId}" ${e.levelId==query.level_id?'selected':''}>${e.levelName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="col-xs-3 col-sm-2">
                        <i class="fa fa-search"></i>
                        <a href="javascript:void (0);" onclick="nextPage('1')">搜索</a>
                    </div>
                </form>
                <form action="" class="form">
                    <div class="form-group">
                        <div class="col-md-2 col-lg-2">
                            <button type="button" onclick="Daochu_Select()"
                                    class="btn w12 form-control btn-block btn-danger btn-sm">批量导出Excel
                            </button>
                        </div>
                    </div>
                </form>

                <table class="table">
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="allmails" onclick="checkAll()"></th>
                        <th>#</th>
                        <th>昵称</th>
                        <th>手机号</th>
                        <th>零钱</th>
                        <th>积分</th>
                        <th>是否禁用</th>
                        <th>注册时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td><input type="checkbox" id="${e.empId}" name="checkbox_one"></td>
                            <td>${st.index+1}</td>
                            <td>${e.empName}</td>
                            <td>${e.empMobile}</td>
                            <td>${e.package_money}</td>
                            <td>${e.jfcount}</td>

                            <td>
                                <c:if test="${e.isUse == '0'}">否</c:if>
                                <c:if test="${e.isUse == '1'}">是</c:if>
                            </td>
                            <td>${um:format(e.dateline, "yyyy-MM-dd HH:mm:ss")}</td>
                            <td>
                                <button class="btn btn-primary" type="button" onclick="detailEmp('${e.empMobile}')">编辑
                                </button>
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
        var size = getCookie("contract_size");
        var phone = $("#phone_number").val();
        var keywords = $("#keyWords").val();
        var level_id = $("#level_id").val();
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=ajax/listMember&page=" + _index + "&size=" + size  + "&level_id=" + level_id +  "&phoneNumber=" + phone + "&keyWords=" + keywords + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function nextPage(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var phone = $("#phone_number").val();
        var keywords = $("#keyWords").val();
        var level_id = $("#level_id").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1)) {
            window.location.href = "#module=ajax/listMember&page=" + page + "&size=" + size + "&level_id=" + level_id + "&phoneNumber=" + phone + "&keyWords=" + keywords + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }

    function changeAdmin(id, index, _page) {
        $.ajax({
            cache: true,
            type: "POST",
            url: "/ajax/changeAdmin.do",
            data: {"empId": id, "flag": index},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    nextPage(_page)
                } else {
                    var _case = {1: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });
    }

    function changeBusiness(id, index, _page) {
        if (index == '1') {
            if (!confirm("是否将该用户设为普通用户？")) {
                return;
            }
        } else {
            if (!confirm("是否将该用户设为商家？")) {
                return;
            }
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: "/ajax/changeBusiness.do",
            data: {"empId": id, "flag": index},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("设置成功");
                    nextPage(_page)
                } else {
                    var _case = {1: "设置失败"};
                    alert(_case[data.code])
                }
            }
        });
    }

    function detailEmp(emp_mobile) {
        window.location.href = "#module=toDetailEmp&emp_mobile=" + emp_mobile+ "&_t="+ new Date().getTime();
    }

    function chongzhi(_id) {
        window.location.href = "#module=/lxConsumptionController/toChongzhi&emp_id=" + _id+ "&_t="+ new Date().getTime();
    }

    function Daochu_Select() {
        var select_id = '';
        var select = document.getElementsByName("checkbox_one");
        for (var i = 0; i < select.length; i++) {
            if (select[i].checked == true) {
                select_id = select_id + select[i].id + ',';
            }
        }
        if (select_id == '') {
            alert('请选择数据！');
            return
        } else {
            if (confirm("确定要导出所选择的数据吗？")) {
                $.ajax({
                    url: "/memberExportExcel.do",
                    data: {"ids": select_id},
                    type: "POST",
                    success: function (_data) {
                        var data = $.parseJSON(_data);
                        if (data.success) {
                            window.location.href = "/upload" + data.data;//这样就可以弹出下载对话框了
                        } else {
                            var _case = {1: "导出失败"};
                            alert(_case[data.code])
                        }
                    }
                });
            }
        }
    }

    function checkAll() {
        var all = document.getElementsByName("allmails")[0];
        var select = document.getElementsByName("checkbox_one");
        if (all.checked) {
            for (var i = 0; i < select.length; i++) {
                select[i].checked = true;
            }
        } else {
            for (var i = 0; i < select.length; i++) {
                select[i].checked = false;
            }
        }
    }

</script>
