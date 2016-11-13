package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaihangObjDao;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.query.PaihangQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service("paihangUpdateVipService")
public class PaihangUpdateVipService implements UpdateService, ListService {
    @Autowired
    @Qualifier("paihangObjDao")
    private PaihangObjDao paihangObjDao;

    @Override
    public Object update(Object object) {
        paihangObjDao.updateOverTime(System.currentTimeMillis()+"");
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        PaihangQuery query = (PaihangQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();

        if (!StringUtil.isNullOrEmpty(query.getIs_del())) {
            map.put("is_del", query.getIs_del());
        }
        if (!StringUtil.isNullOrEmpty(query.getKeyWords())) {
            map.put("keyWords", query.getKeyWords());
        }
        List<PaihangObjVO> lists = paihangObjDao.listsAll(map);

        return lists;
    }
}
