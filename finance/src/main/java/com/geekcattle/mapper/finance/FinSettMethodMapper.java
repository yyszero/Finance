package com.geekcattle.mapper.finance;

import com.geekcattle.model.finance.FinSettMethod;
import com.geekcattle.util.CustomerMapper;

public interface FinSettMethodMapper extends CustomerMapper<FinSettMethod> {

    @Override
    int updateByPrimaryKeySelective(FinSettMethod record);

}