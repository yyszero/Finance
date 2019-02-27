/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.finance;

import com.geekcattle.mapper.finance.FinCertificateBaseMapper;
import com.geekcattle.model.finance.FinCertificateBase;
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
public class FinCertificateBaseService {

    @Autowired
    private FinCertificateBaseMapper finCertificateBaseMapper;

    public List<FinCertificateBase> getPageList(FinCertificateBase finCertificateBase) {
        PageHelper.offsetPage(finCertificateBase.getOffset(), finCertificateBase.getLimit(), CamelCaseUtil.toUnderlineName(finCertificateBase.getSort())+" "+finCertificateBase.getOrder());
        return finCertificateBaseMapper.selectAll();
    }

    public  List<FinCertificateBase> findBy(FinCertificateBase finCertificateBase){
      return   finCertificateBaseMapper.findBy(finCertificateBase);
    }

    public Integer getCount(Example example){
        return finCertificateBaseMapper.selectCountByExample(example);
    }

    public FinCertificateBase getById(Integer id) {
        return finCertificateBaseMapper.selectByPrimaryKey(id);
    }


    public void deleteById(Integer id) {
        finCertificateBaseMapper.deleteByPrimaryKey(id);
    }

    public void insert(FinCertificateBase finCertificateBase){
        finCertificateBaseMapper.insert(finCertificateBase);
    }

    public void save(FinCertificateBase finCertificateBase) {
        if (finCertificateBase.getId() != null) {
            finCertificateBaseMapper.updateByPrimaryKey(finCertificateBase);
        } else {
            finCertificateBaseMapper.insert(finCertificateBase);
        }
    }

    public void updateExample(FinCertificateBase finCertificateBase, Example example){
        finCertificateBaseMapper.updateByExampleSelective(finCertificateBase, example);
    }


    public int review(FinCertificateBase finCertificateBase){

       return finCertificateBaseMapper.review(finCertificateBase);

    }

    /**
     * 查询最大数
     * @param finCertificateBase
     * @return
     */
    public  FinCertificateBase getMaxNumber(FinCertificateBase finCertificateBase){

        return finCertificateBaseMapper.getMaxNumber(finCertificateBase);
    }


}
