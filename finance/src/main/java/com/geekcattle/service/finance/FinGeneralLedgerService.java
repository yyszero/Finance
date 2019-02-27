/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.finance;

import com.geekcattle.mapper.finance.FinCertificateItemMapper;
import com.geekcattle.mapper.finance.FinGeneralLedgerMapper;
import com.geekcattle.model.finance.FinCertificateItem;
import com.geekcattle.model.finance.FinGeneralLedger;
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
public class FinGeneralLedgerService {

    @Autowired
    private FinGeneralLedgerMapper finGeneralLedgerMapper;
    /*总帐*/
    public List<FinGeneralLedger> getPageList(FinGeneralLedger finGeneralLedger) {
        PageHelper.offsetPage(finGeneralLedger.getOffset(), finGeneralLedger.getLimit(), CamelCaseUtil.toUnderlineName(finGeneralLedger.getSort())+" "+finGeneralLedger.getOrder());
        return finGeneralLedgerMapper.findBy(finGeneralLedger);
    }

    /*明细*/
    public List<FinGeneralLedger> getDetailPageList(FinGeneralLedger finGeneralLedger) {
        PageHelper.offsetPage(finGeneralLedger.getOffset(), finGeneralLedger.getLimit(), CamelCaseUtil.toUnderlineName(finGeneralLedger.getSort())+" "+finGeneralLedger.getOrder());
        return finGeneralLedgerMapper.findDetail(finGeneralLedger);
    }


    /*科目余额*/
    public List<FinGeneralLedger> getAccountover(FinGeneralLedger finGeneralLedger) {
        PageHelper.offsetPage(finGeneralLedger.getOffset(), finGeneralLedger.getLimit(), CamelCaseUtil.toUnderlineName(finGeneralLedger.getSort())+" "+finGeneralLedger.getOrder());
        return finGeneralLedgerMapper.selectaccountover(finGeneralLedger);
    }

}
