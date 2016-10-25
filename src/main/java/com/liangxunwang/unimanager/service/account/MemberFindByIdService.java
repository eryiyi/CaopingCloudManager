package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/31.
 */
@Service("memberFindByIdService")
public class MemberFindByIdService implements FindService{
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;


    @Override
    public Object findById(Object object) throws ServiceException {
        String emp_id = (String) object;

        MemberVO memberVO =  memberDao.findInfoById(emp_id);

        return memberVO;
    }
}
