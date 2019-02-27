package com.geekcattle.mapper.finance;

import com.geekcattle.model.finance.FinCertificateItem;
import com.geekcattle.model.finance.FinGeneralLedger;
import com.geekcattle.util.CustomerMapper;

import java.util.List;

public interface FinGeneralLedgerMapper extends CustomerMapper<FinGeneralLedger> {

    List<FinGeneralLedger> findBy(FinGeneralLedger finGeneralLedger);

    List<FinGeneralLedger> findDetail(FinGeneralLedger finGeneralLedger);

    List<FinGeneralLedger> selectaccountover(FinGeneralLedger finGeneralLedger);

}