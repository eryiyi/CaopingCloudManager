<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javascript:void(0)" onclick="toPage('mainPage','')">主页</a></li>
            <li><a href="javaScript:void(0)">产品管理</a></li>
            <li><a href="javaScript:void(0)">编辑产品</a></li>
        </ol>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>编辑产品</span>
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
                <h4 class="page-header">产品详情</h4>

                <form id="save_form" class="form-horizontal" method="post" role="form">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">产品名称</label>

                        <div class="col-sm-4">
                            <input type="text" id="cloud_caoping_title" class="form-control" value="${goods.cloud_caoping_title}"
                                   placeholder="产品名字" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">类型</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="is_type">
                                <option value="0" ${goods.is_type=='0'?'selected':''}>草坪</option>
                                <option value="1" ${goods.is_type=='1'?'selected':''}>草种</option>
                                <option value="2" ${goods.is_type=='2'?'selected':''}>机械</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">销售价格</label>

                        <div class="col-sm-4">
                            <input type="text" id="cloud_caoping_prices" value="${goods.cloud_caoping_prices}" class="form-control"
                                   placeholder="销售价格" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">地址</label>

                        <div class="col-sm-4">
                            <input type="text" id="cloud_caoping_address" value="${goods.cloud_caoping_address}" class="form-control"
                                   placeholder="地址" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">销量</label>

                        <div class="col-sm-4">
                            <input type="text" id="sale_count" value="${goods.sale_count}" class="form-control"
                                   placeholder="销量" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="col-sm-2 control-label">产品介绍</label>

                        <div class="col-sm-4">
                            <textarea id="cloud_caoping_content" style="height: 200px;width:100%">${goods.cloud_caoping_content}</textarea>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">草坪规格</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="cloud_caoping_guige_id" id="cloud_caoping_guige_id">
                                <option value="">-- 选择草坪规格 --</option>
                                <c:forEach items="${listCpGg}" var="s">
                                    <option value="${s.cloud_caoping_guige_id}" ${goods.cloud_caoping_guige_id==s.cloud_caoping_guige_id?'selected':''}>${s.cloud_caoping_guige_cont}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">草坪属性</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="cloud_caoping_type_id" id="cloud_caoping_type_id">
                                <option value="">-- 选择草坪规格 --</option>
                                <c:forEach items="${listCpType}" var="s">
                                    <option value="${s.cloud_caoping_type_id}" ${goods.cloud_caoping_type_id==s.cloud_caoping_type_id?'selected':''}>${s.cloud_caoping_type_cont}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">草坪用途</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="cloud_caoping_use_id" id="cloud_caoping_use_id">
                                <option value="">-- 选择草坪用途 --</option>
                                <c:forEach items="${listCpUse}" var="s">
                                    <option value="${s.cloud_caoping_use_id}" ${goods.cloud_caoping_use_id==s.cloud_caoping_use_id?'selected':''}>${s.cloud_caoping_use_cont}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">草种规格</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="cloud_caozhong_guige_id" id="cloud_caozhong_guige_id">
                                <option value="">-- 选择草种规格 --</option>
                                <c:forEach items="${listCzGg}" var="s">
                                    <option value="${s.cloud_caozhong_guige_id}" ${goods.cloud_caozhong_guige_id==s.cloud_caozhong_guige_id?'selected':''}>${s.cloud_caozhong_guige_cont}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">草种品种</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="cloud_caozhong_type_id" id="cloud_caozhong_type_id">
                                <option value="">-- 选择草种品种 --</option>
                                <c:forEach items="${listCzType}" var="s">
                                    <option value="${s.cloud_caozhong_type_id}" ${goods.cloud_caozhong_type_id==s.cloud_caozhong_type_id?'selected':''}>${s.cloud_caozhong_type_cont}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">机械规格</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="cloud_jixie_guige_id" id="cloud_jixie_guige_id">
                                <option value="">-- 选择机械规格 --</option>
                                <c:forEach items="${listJxGg}" var="s">
                                    <option value="${s.cloud_jixie_guige_id}" ${goods.cloud_jixie_guige_id==s.cloud_jixie_guige_id?'selected':''}>${s.cloud_jixie_guige_cont}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">机械用途</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="cloud_jixie_use_id" id="cloud_jixie_use_id">
                                <option value="">-- 选择机械用途 --</option>
                                <c:forEach items="${listJxUse}" var="s">
                                    <option value="${s.cloud_jixie_use_id}" ${goods.cloud_jixie_use_id==s.cloud_jixie_use_id?'selected':''}>${s.cloud_jixie_use_cont}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <c:forEach items="${listpic}" var="e" varStatus="st">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品图片${st.index+1}</label>
                            <div class="col-sm-10">
                                <input type="file" name="file" id="fileUpload${st.index+1}" style="float: left;"/>
                                <input type="button" value="上传" onclick="uploadImage('fileUpload${st.index+1}','imageDiv${st.index+1}','imagePath')" style="float: left;"/><br/><br/>

                                <div id="imageDiv${st.index+1}" style="padding: 10px">
                                    <script type="text/javascript">
                                        var imagePath${st.index+1} = '${e}';
                                        if (imagePath${st.index+1} != null && imagePath${st.index+1} != "") {
                                            var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath${st.index+1}+ '" width="150" height="150" name="imagePath" title="点击右键删除" class="abc"/>';
                                            $("#imageDiv${st.index+1}").html(html);
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">上架下架</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="cloud_is_use">
                                <option value="">--选择--</option>
                                <option value="0" ${goods.cloud_is_use=='0'?'selected':''}>上架</option>
                                <option value="1" ${goods.cloud_is_use=='1'?'selected':''}>下架</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否删除</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="cloud_is_del">
                                <option value="">--选择--</option>
                                <option value="0" ${goods.cloud_is_del=='0'?'selected':''}>否</option>
                                <option value="1" ${goods.cloud_is_del=='1'?'selected':''}>是</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" id="cloud_caoping_id" value="${goods.cloud_caoping_id}">
                    <input type="hidden" id="emp_id" name="emp_id" value="${goods.emp_id}">


                    <div class="form-group">
                        <div class="col-sm-2 col-sm-offset-2">
                            <button type="button" class="btn btn-primary" onclick="savePaopaoGoods();">
                                <span><i class="fa fa-clock-o"></i></span>
                                提交
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function savePaopaoGoods() {
        var reg = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;
        var cloud_caoping_id = $("#cloud_caoping_id").val();
        var cloud_caoping_title = $("#cloud_caoping_title").val();
        var cloud_caoping_prices = $("#cloud_caoping_prices").val();
        var cloud_caoping_address = $("#cloud_caoping_address").val();
        var cloud_is_use = $("#cloud_is_use").val();
        var cloud_is_del = $("#cloud_is_del").val();
        var sale_count = $("#sale_count").val();
        var is_type = $("#is_type").val();
        var cloud_caoping_content = $("#cloud_caoping_content").val();

        var cloud_caoping_guige_id = $("#cloud_caoping_guige_id").val();
        var cloud_caoping_type_id = $("#cloud_caoping_type_id").val();
        var cloud_caoping_use_id = $("#cloud_caoping_use_id").val();
        var cloud_caozhong_guige_id = $("#cloud_caozhong_guige_id").val();
        var cloud_caozhong_type_id = $("#cloud_caozhong_type_id").val();
        var cloud_jixie_guige_id = $("#cloud_jixie_guige_id").val();
        var cloud_jixie_use_id = $("#cloud_jixie_use_id").val();

        if (cloud_caoping_title.replace(/\s/g, '') == '') {
            alert("产品标题不能为空");
            return;
        }
        if (is_type.replace(/\s/g, '') == '') {
            alert("请选择类型");
            return;
        }

        if(is_type == '0'){
            //草坪
            if (cloud_caoping_guige_id.replace(/\s/g, '') == '') {
                alert("请选择草坪规格");
                return;
            }
            if (cloud_caoping_type_id.replace(/\s/g, '') == '') {
                alert("请选择草坪属性");
                return;
            }
            if (cloud_caoping_use_id.replace(/\s/g, '') == '') {
                alert("请选择草坪用途");
                return;
            }

            var cloud_caozhong_guige_id = "";
            var cloud_caozhong_type_id = "";
            var cloud_jixie_guige_id = "";
            var cloud_jixie_use_id = "";
        }
        if(is_type == '1'){
            if (cloud_caozhong_guige_id.replace(/\s/g, '') == '') {
                alert("请选择草种规格");
                return;
            }
            if (cloud_caozhong_type_id.replace(/\s/g, '') == '') {
                alert("请选择草种品种");
                return;
            }
            var cloud_caoping_guige_id = "";
            var cloud_caoping_type_id = "";
            var cloud_caoping_use_id = "";

            var cloud_jixie_guige_id = "";
            var cloud_jixie_use_id = "";
        }
        if(is_type == '2'){
            if (cloud_jixie_guige_id.replace(/\s/g, '') == '') {
                alert("请选择机械规格");
                return;
            }
            if (cloud_jixie_use_id.replace(/\s/g, '') == '') {
                alert("请选择机械用途");
                return;
            }
            var cloud_caoping_guige_id = "";
            var cloud_caoping_type_id = "";
            var cloud_caoping_use_id = "";
            var cloud_caozhong_guige_id = "";
            var cloud_caozhong_type_id = "";

        }

        if (cloud_caoping_prices.replace(/\s/g, '') == '') {
            alert("销售价格不能为空");
            return;
        } else {
            if (!reg.test(cloud_caoping_prices)) {
                alert("销售价格必须为合法数字(正数，最多两位小数)！");
                return;
            }
        }

        if (sale_count.replace(/\s/g, '') == '') {
            alert("销售数量不能为空");
            return;
        } else {
            if (!reg.test(sale_count)) {
                alert("销售数量必须为合法数字(正数)！");
                return;
            }
        }
        if (cloud_caoping_content.replace(/\s/g, '') == '') {
            alert("请输入产品介绍");
            return;
        }

        if (cloud_is_use.replace(/\s/g, '') == '') {
            alert("请选择是否上架");
            return;
        }
        if (cloud_is_del.replace(/\s/g, '') == '') {
            alert("请选择是否删除");
            return;
        }

        var anchors = '';
        $('.abc').each(function(){
           anchors +=$(this).attr("src")+",";
        });
        $.ajax({
            cache: true,
            type: "POST",
            url: "/productController/edit.do",
            data: {
                "cloud_caoping_id": cloud_caoping_id,
                "cloud_caoping_title": cloud_caoping_title,
                "cloud_caoping_content": cloud_caoping_content,
                "cloud_is_del": cloud_is_del,
                "cloud_is_use": cloud_is_use,
                "cloud_caoping_prices": cloud_caoping_prices,
                "sale_count": sale_count,
                "cloud_caoping_address": cloud_caoping_address,
                "is_type": is_type,
                "cloud_caoping_pic": anchors,

                "cloud_caoping_guige_id": cloud_caoping_guige_id,
                "cloud_caoping_type_id": cloud_caoping_type_id,
                "cloud_caoping_use_id": cloud_caoping_use_id,
                "cloud_caozhong_guige_id": cloud_caozhong_guige_id,
                "cloud_caozhong_type_id": cloud_caozhong_type_id,
                "cloud_jixie_guige_id": cloud_jixie_guige_id,
                "cloud_jixie_use_id": cloud_jixie_use_id

    },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    window.location.href = "#module=/productController/list&page=1" + "&size=10"+ "&_t=" + new Date().getTime();
                } else {
                    alert(data.message)
                }
            }
        });
    }

    function uploadImage(_fileUpload,_imageDiv,_imagePath) {
        $.ajaxFileUpload(
                {
                    url: "/uploadUnCompressImage.do?_t=" + new Date().getTime(),            //需要链接到服务器地址
                    secureuri: false,//是否启用安全提交，默认为false
                    fileElementId: _fileUpload,                        //文件选择框的id属性
                    dataType: 'json',                                     //服务器返回的格式，可以是json, xml
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        if (data.success) {
                            var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + data.data + '" width="150" height="150" name="'+_imagePath+'" title="点击右键删除" class="abc"/>';
                            $("#"+_imageDiv).html(html);
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


</script>


