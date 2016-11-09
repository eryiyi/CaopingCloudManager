package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.PaopaoGoods;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.GoodsTypeThreeQuery;
import com.liangxunwang.unimanager.query.PaopaoGoodsQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/16.
 */
@Controller
@RequestMapping("/paopaogoods")
public class PaopaoGoodsController extends ControllerConstants {
    @Autowired
    @Qualifier("goodsTypeService")
    private ListService goodsTypeListService;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private SaveService paopaoGoodsSaveService;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private ListService paopaoGoodsListService;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private DeleteService paopaoGoodsDeleteService;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private FindService paopaoGoodsFindService;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private UpdateService paopaoGoodsUpdateService;
    @Autowired
    @Qualifier("appPaopaoGoodsService")
    private UpdateService appPaopaoGoodsUpdateService;

    @Autowired
    @Qualifier("appPaopaoGoodsService")
    private ListService appPaopaoGoodsServiceList;
    
    @RequestMapping("toAdd")
    public String toAdd(GoodsTypeThreeQuery query,ModelMap map, HttpSession session){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setType_isuse("0");
        List<GoodsType> list = (List<GoodsType>) goodsTypeListService.list(query);
        map.put("list", list);
        return "/paopaogoods/add";
    }


    @RequestMapping("toAddDxk")
    public String toAddDxk(GoodsTypeThreeQuery query,ModelMap map, HttpSession session){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setType_isuse("0");
        List<GoodsType> list = (List<GoodsType>) goodsTypeListService.list(query);
        map.put("list", list);
        return "/paopaogoods/addDxk";
    }

    @RequestMapping(value = "save", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String save(PaopaoGoods goods, HttpSession session){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(!StringUtil.isNullOrEmpty(admin.getEmp_id()) && !"0".equals(admin.getEmp_id())){
            goods.setManager_id(admin.getId());
            goods.setEmpId(admin.getEmp_id());//商家
            goods.setIs_zhiying("0");
        }else {
            goods.setManager_id(admin.getId());//我们自己的管理员
        }
        paopaoGoodsSaveService.save(goods);
        return toJSONString(SUCCESS);
    }


    /**
     * App端取商品列表
     * @param query
     * @return
     */
    @RequestMapping(value = "listGoods", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String listGoods(PaopaoGoodsQuery query, Page page){
        try {
            query.setIndex(page.getIndex()==0?1:page.getIndex());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
            List<PaopaoGoodsVO> list = (List<PaopaoGoodsVO>) appPaopaoGoodsServiceList.list(query);
            List<PaopaoGoodsVO> list1 = new ArrayList<PaopaoGoodsVO>();
            if("1".equals(query.getIsMine())){
                //说明查询我的
                list1.addAll(list);
            }else{
                //说明不是查询我的
                for(PaopaoGoodsVO paopaoGoodsVO:list){
                    if("0".equals(paopaoGoodsVO.getIsUse())){
                        list1.add(paopaoGoodsVO);//只查询有用的
                    }
                }
            }
            DataTip tip = new DataTip();
            tip.setData(list1);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
    }


    /**
     * 后台查询商品
     * @param query
     * @param page
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("list")
    public String list(PaopaoGoodsQuery query, Page page, HttpSession session, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        map.addAttribute("page", page);
        map.addAttribute("query", query);
        if(!StringUtil.isNullOrEmpty(admin.getEmp_id()) && !"0".equals(admin.getEmp_id())){
            //商家登录
            map.put("is_admin", "1");//0是管理员登录  1的话是商家登录
            query.setEmpId(admin.getEmp_id());
        }else{
            map.put("is_admin", "0");
        }

        Object[] result = (Object[]) paopaoGoodsListService.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/paopaogoods/list";
    }

    /**
     * 根据ID删除我的商品
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id){
        paopaoGoodsDeleteService.delete(id);
        return toJSONString(SUCCESS);
    }


    @Autowired
    @Qualifier("paopaoGoodsFindService")
    private FindService paopaoGoodsFindServiceFind;

    @RequestMapping("edit")
    public String edit(String id, ModelMap map){
        GoodsTypeThreeQuery query = new GoodsTypeThreeQuery();
        List<GoodsType> list = (List<GoodsType>) goodsTypeListService.list(query);
        PaopaoGoods goods = (PaopaoGoods) paopaoGoodsFindServiceFind.findById(id);
        map.put("list", list);
        map.put("goods", goods);
        return "/paopaogoods/edit";
    }

    @RequestMapping("tuijian")
    public String tuijian(String id, ModelMap map){
        GoodsTypeThreeQuery query = new GoodsTypeThreeQuery();
        PaopaoGoods goods = (PaopaoGoods) paopaoGoodsFindServiceFind.findById(id);
        map.put("good", goods);
        return "/paihang/addpaihang";
    }

    @RequestMapping("update")
    @ResponseBody
    public String update(PaopaoGoods goods){
        paopaoGoodsUpdateService.update(goods);
        return toJSONString(SUCCESS);
    }

    /**
     * App根据ID查询商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "findById", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String findByGoodsId(@RequestParam String id, @RequestParam String empid){
        if (StringUtil.isNullOrEmpty(id)){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        Object[] params = new Object[]{id, empid};
        PaopaoGoodsVO vo = (PaopaoGoodsVO) paopaoGoodsFindService.findById(params);
        DataTip tip = new DataTip();
        tip.setData(vo);
        return toJSONString(tip);
    }

    /**
     * 根据商品ID查找商品详情
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("detail")
    public String detail(String id, ModelMap map){
        if (StringUtil.isNullOrEmpty(id)){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        PaopaoGoodsVO paopaoGoodsVO = (PaopaoGoodsVO) paopaoGoodsFindServiceFind.findById(id);
        map.put("vo", paopaoGoodsVO);
        return "/paopaogoods/detail";
    }

    @RequestMapping("updatePosition")
    @ResponseBody
    public String updatePosition(String id, String position){
        Map<String, String> map = new HashMap<String, String>();
        map.put("id" , id);
        map.put("goodsPosition" , position);
        appPaopaoGoodsUpdateService.update(map);
        return toJSONString(SUCCESS);
    }

    @Autowired
    @Qualifier("appPaopaoGoodsJiaService")
    private UpdateService appPaopaoGoodsJiaService;

    /**
     * App上架 下架产品
     * @param id
     * @return
     */
    @RequestMapping(value = "updatePaopaoGoodsJia", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String updatePaopaoGoodsJia(String id,String status){
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("status", status);
        appPaopaoGoodsJiaService.update(map);
        return toJSONString(SUCCESS);
    }


    /**
     * 分享商品
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("shareGoodsUrl")
    public String shareGoodsUrl(String id, ModelMap map){
        if (StringUtil.isNullOrEmpty(id)){
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );
        }
        Object[] params = new Object[2];
        params[0] = id;
        params[1] = "";

        PaopaoGoodsVO paopaoGoodsVO = (PaopaoGoodsVO) paopaoGoodsFindService.findById(params);
        map.put("vo", paopaoGoodsVO);
        return "/paopaogoods/viewGoods";
    }
}
