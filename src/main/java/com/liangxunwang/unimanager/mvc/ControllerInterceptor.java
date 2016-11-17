package com.liangxunwang.unimanager.mvc;

import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 */
public class ControllerInterceptor extends ControllerConstants implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        Object account = request.getSession().getAttribute(ACCOUNT_KEY);

        if(uri.contains(".json")){
                return true;
        }

        if(uri.matches("(^/$)|(^/index\\.do$)|(^/adminLogin\\.do$)|(^/logout\\.do$)" +
                "|(^/uploadImage\\.do$)" +
                "|(^/uploadUnCompressImage\\.do$)" +
                "|(^/recordList\\.do$)" +
                "|(^/goodsTypeList\\.do$)" +
                "|(^/goodsTypeList2\\.do$)"

                +"|(^/saveGoodsFavour\\.do$)"
                +"|(^/listFavour\\.do$)"
                +"|(^/deleteFavour\\.do$)"
                +"|(^/saveShoppingAddress\\.do$)"
                +"|(^/listShoppingAddress\\.do$)"
                +"|(^/deleteShoppingAddress\\.do$)"
                +"|(^/updateShoppingAddress\\.do$)"
                +"|(^/getSingleAddressByEmpId\\.do$)"
                +"|(^/orderSave\\.do$)"
                +"|(^/orderUpdate\\.do$)"
                +"|(^/listOrders\\.do$)"
                +"|(^/getSingleAddressByAddressId\\.do$)"
                +"|(^/updateOrder\\.do$)"
                +"|(^/orderSaveSingle\\.do$)"
                +"|(^/orderUpdateSingle\\.do$)"
                +"|(^/appGetProvince\\.do$)"
                +"|(^/getProvince\\.do$)"
                +"|(^/appGetCity\\.do$)"
                +"|(^/getCity\\.do$)"
                +"|(^/appGetArea\\.do$)"
                +"|(^/getCountry\\.do$)"
                +"|(^/paopaogoods/listGoods\\.do$)"
                +"|(^/paopaogoods/findById\\.do$)"
                +"|(^/paopaogoods/detail\\.do$)"
                +"|(^/viewpager/appList\\.do$)"
                +"|(^/selectOrderNum\\.do$)"
                +"|(^/listOrdersMng\\.do$)"
                +"|(^/cancelOrderSave\\.do$)"
                +"|(^/paopaogoods/updatePosition\\.do$)"
                +"|(^/findOrderByOrderNo\\.do$)"
                +"|(^/paopaogoods/delete\\.do$)"


                +"|(^/appGetGoodsType\\.do$)"
                +"|(^/memberRegister\\.do$)"
                +"|(^/memberLogin\\.do$)"
                +"|(^/updatePushId\\.do$)"
                +"|(^/resetMobile\\.do$)"
                +"|(^/resetPass\\.do$)"
                +"|(^/modifyMember\\.do$)"
                +"|(^/getMemberByMobile\\.do$)"
                +"|(^/modifyMemberSex\\.do$)"
                +"|(^/modifyMemberBirth\\.do$)"
                +"|(^/modifyMemberPayPass\\.do$)"
                +"|(^/listProvince\\.do$)"
                +"|(^/sendLocation\\.do$)"
                +"|(^/saveManagerInfo\\.do$)"
                +"|(^/appGetNearbyDianpu\\.do$)"
                +"|(^/getTuijianProduct\\.do$)"
                +"|(^/appGetDianpuDetailByEmpId\\.do$)"
                +"|(^/appGetAdEmp\\.do$)"
                +"|(^/saveGoodsComment\\.do$)"
                +"|(^/listGoodsComment\\.do$)"
                +"|(^/appGetAdByType\\.do$)"
                +"|(^/appGetDianpuFavour\\.do$)"
                +"|(^/saveDianpuFavour\\.do$)"
                +"|(^/deleteDianpuFavour\\.do$)"
                +"|(^/appGetBankCards\\.do$)"
                +"|(^/appSaveBankCards\\.do$)"
                +"|(^/deleteBankCard\\.do$)"
                +"|(^/appGetPackage\\.do$)"
                +"|(^/getVersionCode\\.do$)"

                +"|(^/appSaveBrowsing\\.do$)"
                +"|(^/appGetBrowsing\\.do$)"
                +"|(^/orderSaveWx\\.do$)"
                +"|(^/getKefuTel\\.do$)"
                +"|(^/appShareReg\\.do$)"
                +"|(^/saveEmpShare\\.do$)"
                +"|(^/orderSaveSingleWx\\.do$)"
                +"|(^/scanOrder\\.do$)"
                +"|(^/appGetCountRecord\\.do$)"
                +"|(^/appGetCount\\.do$)"
                +"|(^/appGetConsumption\\.do$)"

                +"|(^/appSaveBankApply\\.do$)"
                +"|(^/appGetBankApply\\.do$)"
                +"|(^/appGetLikes\\.do$)"
                +"|(^/appGetCardEmp\\.do$)"
                +"|(^/appSaveDxkOrder\\.do$)"

                +"|(^/appGetCountComment\\.do$)"
                +"|(^/resetPassByMobile\\.do$)"


                +"|(^/appGetNews\\.do$)"
                +"|(^/appSaveNews\\.do$)"
                +"|(^/appDeleteNews\\.do$)"
                +"|(^/token\\.do$)"

                +"|(^/appGetNewsComment\\.do$)"
                +"|(^/appSaveNewsComment\\.do$)"
                +"|(^/appDeleteNewsComment\\.do$)"
                +"|(^/appGetNewsById\\.do$)"

                +"|(^/appGetNewsFavour\\.do$)"
                +"|(^/appSaveNewsFavour\\.do$)"
                +"|(^/appDeleteNewsFavour\\.do$)"

                +"|(^/appGetCpGuige\\.do$)"
                +"|(^/appGetCpType\\.do$)"
                +"|(^/appGetCpUse\\.do$)"

                +"|(^/appGetCpLists\\.do$)"
                +"|(^/appSaveCpLists\\.do$)"

                +"|(^/appGetCompanyDetail\\.do$)"
                +"|(^/appSaveCompanyDetail\\.do$)"
                +"|(^/appSaveCompanyDetailPic\\.do$)"
                +"|(^/appGetCompanyList\\.do$)"


                +"|(^/appGetJixieGuige\\.do$)"
                +"|(^/appGetJixieUse\\.do$)"
                +"|(^/appGetJixie\\.do$)"
                +"|(^/appSaveJixie\\.do$)"


                +"|(^/appGetCaozhongGuige\\.do$)"
                +"|(^/appGetCaozhongType\\.do$)"
                +"|(^/appGetCaozhongLists\\.do$)"
                +"|(^/appSaveCaozhong\\.do$)"

                +"|(^/appGetApplyGysById\\.do$)"
                +"|(^/appSaveApplyGys\\.do$)"
                +"|(^/appGetMembersByIds\\.do$)"


                +"|(^/appGetCarType\\.do$)"
                +"|(^/appGetCarLength\\.do$)"
                +"|(^/appSaveTransport\\.do$)"
                +"|(^/appGetTransport\\.do$)"

                +"|(^/paopaogoods/updatePaopaoGoodsJia\\.do$)"
                +"|(^/paopaogoods/saveAppGoods\\.do$)"
                +"|(^/paopaogoods/shareGoodsUrl\\.do$)"
                +"|(^/getThemeApp\\.do$)"
        ) || account != null) {
            return true;
        }

        if("true".equals(request.getParameter("j"))) {
            PrintWriter out = response.getWriter();
            out.print(toJSONString(new ErrorTip(1, "请求超时，请稍后重试！")
            ));
            out.close();
        } else {
            request.getRequestDispatcher("/WEB-INF/session.jsp").forward(request, response);
        }
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
