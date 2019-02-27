package com.geekcattle.mapper.finance;

import com.geekcattle.model.finance.FinCertificateBase;
import com.geekcattle.util.CustomerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinCertificateBaseMapper extends CustomerMapper<FinCertificateBase> {
    List<FinCertificateBase> findBy(@Param("finCertificateBase") FinCertificateBase finCertificateBase);

    int  review(FinCertificateBase finCertificateBase);

    FinCertificateBase getMaxNumber(FinCertificateBase finCertificateBase);

    void updateOver(FinCertificateBase finCertificateBase);
}