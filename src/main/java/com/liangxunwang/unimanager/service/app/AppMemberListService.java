package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.mvc.vo.EmpDianpu;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/31.
 */
@Service("appMemberListService")
public class AppMemberListService implements ListService{
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Object[] params = (Object[]) object;
        String hxUsername = (String) params[0];
        Map<String, Object> map = new HashMap<String, Object>();
        List<Member> list = new ArrayList<Member>();
        if(hxUsername!=null){
            String[] strs = hxUsername.split(",");
            List<String> phones = new ArrayList<String>();
            for (int i = 0; i < strs.length; i++) {
                phones.add(strs[i]);
            }
            map.put("hxUsername", strs);
            list = memberDao.listMemberInfoByUsername(map);
        }
        if(list != null && list.size()>0){
            for (Member member : list) {
                if (member.getEmpCover().startsWith("upload")) {
                    member.setEmpCover(Constants.URL + member.getEmpCover());
                }else {
                    member.setEmpCover(Constants.QINIU_URL + member.getEmpCover());
                }
            }
        }
        return list;
    }
}
