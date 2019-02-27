package com.geekcattle.mapper.finance;

import com.geekcattle.model.finance.FinEvidenceType;
import com.geekcattle.util.CustomerMapper;

public interface FinEvidenceTypeMapper extends CustomerMapper<FinEvidenceType> {
    @Override
    int updateByPrimaryKeySelective(FinEvidenceType record);

}