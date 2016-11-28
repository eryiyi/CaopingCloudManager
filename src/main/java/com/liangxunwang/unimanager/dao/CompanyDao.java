package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.Company;
import com.liangxunwang.unimanager.mvc.vo.CompanySort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("companyDao")
public interface CompanyDao {
    /**
     * 查询
     */
    List<Company> lists(Map<String, Object> map);

    Long count(Map<String, Object> map);

    //保存
    void save(Company company);

    //删除
    void delete(String company_id);

    /**
     * 根据ID查找
     */
    public Company findById(String company_id);

    public CompanySort findSort(String company_id);

    //根据会员ID查询
    public Company findByEmpId(String company_id);

    /**
     * 更新
     */
    public void update(Company company);

    //更新位置
    public void updateLocation(Company company);
    //更新图片
    public void updatePic(Company company);
    //名企排行榜
    public void updateMq(Company company);
    //是否审核
    public void updateCheck(Company company);
}
