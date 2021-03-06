package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.PaopaoGoodsDao;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.PaopaoGoodsQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/2.
 */
@Service("appPaopaoGoodsService")
public class AppPaopaoGoodsService implements UpdateService,ListService {

    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;

    @Override
    public Object update(Object object) {
        Map<String, String> map = (Map<String, String>) object;
        String id = map.get("id");
        String goodsPosition = map.get("goodsPosition");
        paopaoGoodsDao.updatePostionById(id, goodsPosition);
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        PaopaoGoodsQuery query = (PaopaoGoodsQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if (!StringUtil.isNullOrEmpty(query.getTypeId())) {
            map.put("typeId", query.getTypeId());
        }
        if (!StringUtil.isNullOrEmpty(query.getCont())) {
            map.put("cont", query.getCont());
        }
        if (!StringUtil.isNullOrEmpty(query.getEmpId())) {
            map.put("empId", query.getEmpId());
        }
        if (!StringUtil.isNullOrEmpty(query.getManager_id())) {
            map.put("manager_id", query.getManager_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getLat_company())){
            map.put("lat_company", query.getLat_company());
        }
        if(!StringUtil.isNullOrEmpty(query.getLng_company())){
            map.put("lng_company", query.getLng_company());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_time())){
            map.put("is_time", query.getLng_company());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_count())){
            map.put("is_count", query.getIs_count());
        }
        map.put("isUse", "0");
        //查询的是我的商品
        if (!StringUtil.isNullOrEmpty(query.getIsMine()) && query.getIsMine().equals("1")) {
            //查询会员下的所有商品
            if (!StringUtil.isNullOrEmpty(query.getEmpId())) {
                map.put("empId", query.getEmpId());
            }
        }

        List<PaopaoGoodsVO> list = paopaoGoodsDao.listGoods(map);
        for (PaopaoGoodsVO vo : list){
            if (!StringUtil.isNullOrEmpty(vo.getCover())) {
                if (vo.getCover().startsWith("upload")) {
                    vo.setCover(Constants.URL + vo.getCover());
                }else {
                    vo.setCover(Constants.QINIU_URL + vo.getCover());
                }
            }
            if (!StringUtil.isNullOrEmpty(vo.getGoods_cover1())) {
                if (vo.getGoods_cover1().startsWith("upload")) {
                    vo.setGoods_cover1(Constants.URL + vo.getGoods_cover1());
                }else {
                    vo.setGoods_cover1(Constants.QINIU_URL + vo.getGoods_cover1());
                }
            }
            if (!StringUtil.isNullOrEmpty(vo.getGoods_cover2())) {
                if (vo.getGoods_cover2().startsWith("upload")) {
                    vo.setGoods_cover2(Constants.URL + vo.getGoods_cover2());
                }else {
                    vo.setGoods_cover2(Constants.QINIU_URL + vo.getGoods_cover2());
                }
            }
            if (!StringUtil.isNullOrEmpty(vo.getEmpCover())) {
                if (vo.getEmpCover().startsWith("upload")) {
                    vo.setEmpCover(Constants.URL + vo.getEmpCover());
                }else {
                    vo.setEmpCover(Constants.QINIU_URL + vo.getEmpCover());
                }
            }
            if (!StringUtil.isNullOrEmpty(vo.getManagerCover())) {
                if (vo.getEmpCover().startsWith("upload")) {
                    vo.setManagerCover(Constants.URL + vo.getManagerCover());
                }else {
                    vo.setManagerCover(Constants.QINIU_URL + vo.getManagerCover());
                }
            }
            if(!StringUtil.isNullOrEmpty(vo.getUpTime())){vo.setUpTime(RelativeDateFormat.format(Long.parseLong(vo.getUpTime())));}

        }

        return list;
    }

}
