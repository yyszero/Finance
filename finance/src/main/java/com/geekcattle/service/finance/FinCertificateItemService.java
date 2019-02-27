/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.finance;

import com.geekcattle.mapper.finance.FinCertificateItemMapper;
import com.geekcattle.model.console.Admin;
import com.geekcattle.model.finance.FinCertificateItem;
import com.geekcattle.util.CamelCaseUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 *  凭证填制
 *  date/21 0021 下午 15:43
 */
@Service
public class FinCertificateItemService {

    @Autowired
    private FinCertificateItemMapper finCertificateItemMapper;

    public List<FinCertificateItem> getPageList(FinCertificateItem finCertificateItem) {
        PageHelper.offsetPage(finCertificateItem.getOffset(), finCertificateItem.getLimit(), CamelCaseUtil.toUnderlineName(finCertificateItem.getSort())+" "+finCertificateItem.getOrder());
        return finCertificateItemMapper.selectAll();
    }


    public Integer getCount(Example example){
        return finCertificateItemMapper.selectCountByExample(example);
    }

    public FinCertificateItem getById(String id) {
        return finCertificateItemMapper.selectByPrimaryKey(id);
    }


    public void deleteById(String id) {
        finCertificateItemMapper.deleteByPrimaryKey(id);
    }

    public void insert(FinCertificateItem finCertificateItem){
        finCertificateItemMapper.insert(finCertificateItem);
    }

    public void save(FinCertificateItem finCertificateItem) {
        if (finCertificateItem.getId() != null) {
            finCertificateItemMapper.updateByPrimaryKey(finCertificateItem);
        } else {
            finCertificateItemMapper.insert(finCertificateItem);
        }
    }


    public List<FinCertificateItem> findByCertificateId(Integer id) {
        return  finCertificateItemMapper.findByCertificateId(id);
    }

    public void deleteByCertifcateBaseId(Integer id) {
        finCertificateItemMapper.deleteByCertifcateBaseId(id);
    }


    /**
     * 更新余额
     * @param finCertificateItem
     * @return
     */
    public  int updateOver(FinCertificateItem finCertificateItem){

       return finCertificateItemMapper.updateOver(finCertificateItem);
    }
}
