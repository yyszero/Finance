package com.geekcattle.mapper.finance;

import com.geekcattle.model.finance.FinCertificateItem;
import com.geekcattle.util.CustomerMapper;

import java.util.List;

public interface FinCertificateItemMapper extends CustomerMapper<FinCertificateItem> {
    List<FinCertificateItem> findByCertificateId(Integer certificateId);

    void deleteByCertifcateBaseId(Integer id);


    int updateOver(FinCertificateItem finCertificateItem);
}