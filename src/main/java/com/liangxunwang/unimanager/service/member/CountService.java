package com.liangxunwang.unimanager.service.member;

import com.liangxunwang.unimanager.dao.CountDao;
import com.liangxunwang.unimanager.dao.PaopaoGoodsDao;
import com.liangxunwang.unimanager.model.Count;
import com.liangxunwang.unimanager.mvc.vo.CountVo;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.CountQuery;
import com.liangxunwang.unimanager.query.PaopaoGoodsQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by zhl on 2015/2/4.
 */
@Service("countService")
public class CountService implements SaveService,ListService ,UpdateService{
    @Autowired
    @Qualifier("countDao")
    private CountDao countDao;

    @Override
    public Object save(Object object) throws ServiceException {
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        CountQuery query = (CountQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getEmp_mobile())) {
            map.put("emp_mobile", query.getEmp_mobile());
        }
        if (!StringUtil.isNullOrEmpty(query.getCont())) {
            map.put("keyWords", query.getCont());
        }

        List<CountVo> list = countDao.list(map);
        long count = countDao.count(map);
        return new Object[]{list, count};
    }

    @Override
    public Object update(Object object) {
        String[] arr = (String[]) object;
        String empId = arr[0];
        String count = arr[1];
        countDao.updateScore(empId, count);
        return null;
    }
}
