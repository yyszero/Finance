package com.geekcattle.mapper.finance;

import com.geekcattle.model.finance.FinAccountCertificate;
import com.geekcattle.util.CustomerMapper;

import java.util.List;

public interface FinAccountCertificateMapper extends CustomerMapper<FinAccountCertificate> {

    @Override
    int updateByPrimaryKeySelective(FinAccountCertificate record);

    FinAccountCertificate findById(Integer id);

    List<FinAccountCertificate> findAll();

    FinAccountCertificate findByCode(String code);

    int updateOver(FinAccountCertificate finAccountCertificate);
}