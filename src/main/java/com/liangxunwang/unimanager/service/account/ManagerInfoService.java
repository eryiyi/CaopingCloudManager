package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.ManagerInfoDao;
import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.mvc.vo.ManagerInfoVo;
import com.liangxunwang.unimanager.query.ManagerInfoQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/30.
 */
@Service("managerInfoService")
public class ManagerInfoService implements SaveService, FindService, UpdateService, ListService,ExecuteService {

    @Autowired
    @Qualifier("managerInfoDao")
    private ManagerInfoDao managerInfoDao;
    @Override
    public Object findById(Object object) throws ServiceException {
        if (object instanceof String){
            String id = (String) object;
            ManagerInfo managerInfo = managerInfoDao.findById(id);
            managerInfo.setDateline(DateUtil.getDate(managerInfo.getDateline(), "yyyy-MM-dd HH:mm"));
            return managerInfo;
        }
        return null;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        if (object instanceof ManagerInfo){
            ManagerInfo info = (ManagerInfo) object;
            if(info != null && !StringUtil.isNullOrEmpty(info.getEmp_id())){
                ManagerInfo managerInfo =  managerInfoDao.findById(info.getEmp_id());
                if(managerInfo != null){
                    //说明已经存在该用户的申请了
                    throw new ServiceException("hax_exist_info");
                }
            }else {
                throw new ServiceException("is_error");
            }
            info.setId(UUIDFactory.random());
            info.setDateline(System.currentTimeMillis() +"");
            managerInfoDao.save(info);
        }
        return null;
    }

    @Override
    public Object update(Object object) {
        if (object instanceof ManagerInfo){
            ManagerInfo info = (ManagerInfo) object;
            managerInfoDao.update(info);
        }
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        ManagerInfoQuery query = (ManagerInfoQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if (!StringUtil.isNullOrEmpty(query.getKeyWords())) {
            map.put("cont", query.getKeyWords());
        }
        List<ManagerInfoVo> list = managerInfoDao.lists(map);
        long count = managerInfoDao.count(map);
        return new Object[] {list, count};
    }

    @Override
    public Object execute(Object object) throws Exception {
        ManagerInfoVo managerInfoVo = managerInfoDao.getEmpMsg((String) object);
        if(managerInfoVo != null){

            if(!StringUtil.isNullOrEmpty(managerInfoVo.getEmp_cover())){
                if (managerInfoVo.getEmp_cover().startsWith("upload")) {
                    managerInfoVo.setEmp_cover(Constants.URL + managerInfoVo.getEmp_cover());
                }else {
                    managerInfoVo.setEmp_cover(Constants.QINIU_URL + managerInfoVo.getEmp_cover());
                }
            }

            if(!StringUtil.isNullOrEmpty(managerInfoVo.getCompany_pic())){
                if (managerInfoVo.getCompany_pic().startsWith("upload")) {
                    managerInfoVo.setCompany_pic(Constants.URL + managerInfoVo.getCompany_pic());
                }else {
                    managerInfoVo.setCompany_pic(Constants.QINIU_URL + managerInfoVo.getCompany_pic());
                }
            }

        }
        return managerInfoVo;
    }
}
