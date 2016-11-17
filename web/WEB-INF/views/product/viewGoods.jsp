<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${vo.cloud_caoping_title}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <!--css-->
    <link rel="stylesheet" href="/grass/css/reset.css"/>
    <link rel="stylesheet" href="/grass/css/grass.css"/>
    <script src="/grass/js/jquery-2.2.4.min.js"></script>

    <!--GLIDE-->
    <link rel="stylesheet" href="/grass/css/glide.core.css"/>
    <link rel="stylesheet" href="/grass/css/glide.theme.css"/>
    <script src="/grass/js/glide.min.js"></script>

</head>
<body>

<div id="Glide" class="glide hidden-sm hidden-xs">
    <!--<div class="glide__arrows">-->
    <!--<button class="glide__arrow prev" data-glide-dir="<"><</button>-->
    <!--<button class="glide__arrow next" data-glide-dir=">">></button>-->
    <!--</div>-->

    <div class="glide__wrapper">
        <a class="pic-btn">
            <span class="product-name">${vo.emp_name}</span>
            <img src="/grass/pics/lg_grass_bg.png" alt=""/>
        </a>
        <ul class="glide__track">
            <c:forEach items="${voPic}" var="e">
                <li class="glide__slide"><img src="${e}" alt=""/></li>
            </c:forEach>
        </ul>
    </div>
    <div class="glide__bullets"></div>
</div>

<script>
    $(function(){
        $('.glide').glide({
            mode:'horizontal',
            autoplay:5000,
            startAt:1,
            // paddings:'1rem',
            centered:true,
            hoverpause:true,
            autoheight:true,
            keyboard:true,
            touchDistance:80,
            dragDistance:120,
            animationDuration:400

        });
    })
</script>

<!--container-->
<!--价格-->
<div class="row price clearfix">
    <div class="price-left">
        <h3><span>￥</span>${e.cloud_caoping_prices}</h3>
    </div>
    <div class="price-right">
        <p>已销售${vo.sale_count}件</p>
        <p>产地：${vo.cloud_caoping_address}</p>
    </div>
</div>
<!--认证-->
<div class="row verify clearfix">
    <div class="verify-item"><img src="/grass/pics/sm_good.png"/><span>正品保证</span></div>
    <div class="verify-item"><img src="/grass/pics/sm_good.png"/><span>诚信保障</span></div>
    <div class="verify-item"><img src="/grass/pics/sm_good.png"/><span>认证公司</span></div>
</div>
<!--口碑-->
<div class="good row">
    <h4>草原口碑(230)</h4>
    <div class="clearfix">
        <a class="btn btn-1" href="">好评（200）</a>
        <a class="btn btn-2" href="">一般（21）</a>
        <a class="btn btn-3" href="">差评（9）</a>
    </div>
</div>
<!--评论-->
<div class="row">
    <div class="comment-item">
        <div class="comment-head">
            <img src="/grass/pics/01.jpg"/>
            <div class="info">
                <h4>18366883986</h4>
                <h4><span class="label label-green">个人</span></h4>
            </div>
        </div>
        <div class="comment-body">
            <p>草坪质量好，发货快。</p>
        </div>
        <div class="comment-footer">
            2016-11-09 规格：40 x 90
        </div>
    </div>
    <div class="comment-item">
        <div class="comment-head">
            <img src="/grass/pics/01.jpg"/>
            <div class="info">
                <h4>18366883986</h4>
                <h4><span class="label label-green">个人</span></h4>
            </div>
        </div>
        <div class="comment-body">
            <p>草坪质量好，发货快。</p>
        </div>
        <div class="comment-footer">
            2016-11-09 规格：40 x 90
        </div>
    </div>
</div>

<div class="body">
    <div class="switch clearfix">
        <a class="active" target="detail">图文详情</a>
        <a target="info">产品参数</a>
        <a target="company">公司简介</a>
    </div>
    <div class="body-content detail">
        <c:forEach items="${voPic}" var="e">
            <img src="${e}"/>
        </c:forEach>

        <p>${vo.cloud_caoping_content}</p>
    </div>

    <div class="body-content info hide">
        <table>
            <c:if test="${vo.is_type == '0'}">
                <tr>
                    <th>草坪规格</th>
                    <td>${vo.cloud_caoping_guige_cont}</td>
                </tr>
                <tr>
                    <th>草坪属性</th>
                    <td>${vo.cloud_caoping_type_cont}</td>
                </tr>
                <tr>
                    <th>草坪用途</th>
                    <td>${vo.cloud_caoping_use_cont}</td>
                </tr>
            </c:if>
            <c:if test="${vo.is_type == '1'}">
                <tr>
                    <th>草种规格</th>
                    <td>${vo.cloud_caozhong_guige_cont}</td>
                </tr>
                <tr>
                    <th>草种品种</th>
                    <td>${vo.cloud_caozhong_type_cont}</td>
                </tr>
            </c:if>

            <c:if test="${vo.is_type == '2'}">
                <tr>
                    <th>机械规格</th>
                    <td>${vo.cloud_jixie_guige_cont}</td>
                </tr>
                <tr>
                    <th>机械用途</th>
                    <td>${vo.cloud_jixie_use_cont}</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="body-content company hide">
        <div class="company-head clearfix">
            <img src="${vo.emp_cover}" />
            <div class="name">
                <p>${company.company_name}${vo.emp_name}</p>
                <p>
                    <img class="ico" src="/grass/pics/sm_dscv_top.png"/>
                    <img class="ico" src="/grass/pics/sm_dscv_hot.png"/>
                    <img class="ico" src="/grass/pics/sm_dscv_new.png"/>
                    <img class="ico" src="/grass/pics/sm_dscv_seed.png"/>
                </p>
            </div>
        </div>
        <div class="company-body">
            <div class="pos clearfix">
                <div>
                    <p>${cyNum}</p>
                    <span>草原规模</span>
                </div>
                <div>
                    <p>12.4万</p>
                    <span>关注人数</span>
                </div>
                <div>
                    <p>top1</p>
                    <span>全国排行</span>
                </div>
            </div>
            <div class="detail">
                <img src="/grass/pics/03.jpg" alt=""/>
                <p>${company.company_detail}</p>
            </div>
        </div>
        <div class="company-footer">
            <table>
                <tr>
                    <th>总经理</th>
                    <td>${company.company_name}</td>
                </tr>
                <tr>
                    <th>电话</th>
                    <td>${company.company_tel}</td>
                </tr>
                <tr>
                    <th>公司地址</th>
                    <td>${company.company_address}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script>
    $(function(){
        $('.switch a').each(function(){
            $(this).click(function(){
                $('.switch a').removeClass('active');
                $(this).addClass('active');
                $('.body-content').addClass('hide');
                var target = $(this).attr('target');
                $('.'+target).removeClass('hide');
            })
        })
    })
</script>
</body>
</html>
