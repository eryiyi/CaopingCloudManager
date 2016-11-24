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
            <li><a href="javascript:void(0)">草坪用途</a></li>
            <li><a href="javascript:void(0)">草坪用途</a></li>
        </ol>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>草坪用途</span>
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
                <h4 class="page-header">编辑草坪用途</h4>

                <form class="form-horizontal" role="form">
                    <input type="hidden" id="cloud_caoping_use_id" name="cloud_caoping_use_id" value="${cpuse.cloud_caoping_use_id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用途描述</label>

                        <div class="col-sm-4">
                            <input type="text" id="cloud_caoping_use_cont" value="${cpuse.cloud_caoping_use_cont}" class="form-control" placeholder="用途描述"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序</label>

                        <div class="col-sm-4">
                            <input type="text" id="cloud_caoping_use_num" value="${cpuse.cloud_caoping_use_num}" class="form-control" placeholder="排序 数字"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">类别图片</label>

                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage()" style="float: left;"/><br/><br/>

                            <div id="imageDiv" style="padding: 10px">
                                <script type="text/javascript">
                                    var imagePath = '${cpuse.cloud_caoping_use_pic}';
                                    if (imagePath != null && imagePath != "") {
                                        var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';
//                    var imageDivHtml = $("#imageDiv").html() + html;
                                        $("#imageDiv").html(html);
                                    }
                                </script>
                            </div>
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
    function uploadImage() {
        $.ajaxFileUpload(
                {
                    url: "/uploadUnCompressImage.do?_t=" + new Date().getTime(),            //需要链接到服务器地址
                    secureuri: false,//是否启用安全提交，默认为false
                    fileElementId: 'fileUpload',                        //文件选择框的id属性
                    dataType: 'json',                                     //服务器返回的格式，可以是json, xml
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        if (data.success) {
                            var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + data.data + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';
//                  var imageDivHtml = $("#imageDiv").html() + html;
                            $("#imageDiv").html(html);
                        } else {
                            if (data.code == 1) {
                                alert("上传图片失败");
                            } else if (data.code == 2) {
                                alert("上传图片格式只能为：jpg、png、gif、bmp、jpeg");
                            } else if (data.code == 3) {
                                alert("请选择上传图片");
                            } else {
                                alert("上传失败");
                            }
                        }
                    }
                }
        );
    }

    function deleteImage(e, node) {
        if (e.button == 2) {
            if (confirm("确定移除该图片吗？")) {
                $(node).remove();
            }
        }
    }
    ;

    function saveP() {
        var cloud_caoping_use_id = $("#cloud_caoping_use_id").val();
        var cloud_caoping_use_cont = $("#cloud_caoping_use_cont").val();
        var cloud_caoping_use_num = $("#cloud_caoping_use_num").val();

        if (cloud_caoping_use_cont.replace(/\s/g, '') == '') {
            alert("描述不能为空");
            return;
        }

        var regInt = /^([0-9]\d*)$/;
        if (cloud_caoping_use_num.replace(/\s/g, '') == '') {
            alert("排序不能为空");
            return;
        } else {
            if (!regInt.test(cloud_caoping_use_num)) {
                alert("排序必须是整数！");
                return;
            }
        }

        var imagePath = $("img[name='imagePath']").attr("src");
        if (imagePath == ""  || imagePath==null) {
            alert("请上传图片");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/cpuseController/edit.do",
            data: {
                "cloud_caoping_use_id": cloud_caoping_use_id,
                "cloud_caoping_use_cont": cloud_caoping_use_cont,
                "cloud_caoping_use_pic": imagePath,
                "cloud_caoping_use_num": cloud_caoping_use_num
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("执行成功");
                    window.location.href = "#module=cpuseController/list"+ "&_t="+ new Date().getTime();
                } else {
                    alert(data.message);
                }
            }
        });
    }
</script>


