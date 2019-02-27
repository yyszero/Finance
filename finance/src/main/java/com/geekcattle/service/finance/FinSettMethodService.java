package com.geekcattle.service.finance;

import com.geekcattle.mapper.finance.FinSettMethodMapper;
import com.geekcattle.model.finance.FinEvidenceType;
import com.geekcattle.model.finance.FinSettMethod;
import com.geekcattle.util.CamelCaseUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xinxiaoqiang
 * @Description:
 * @Date: 2018/5/18 23:06
 */
@Service
public class FinSettMethodService {
    @Autowired
    private FinSettMethodMapper methodMapper;


    public List<FinSettMethod> getPageList(FinSettMethod certificate) {
        PageHelper.offsetPage(certificate.getOffset(), certificate.getLimit(), CamelCaseUtil.toUnderlineName(certificate.getSort())+" "+certificate.getOrder());
        return methodMapper.selectAll();
    }

    public int deleteByPrimaryKey(Integer id){

        return methodMapper.deleteByPrimaryKey(id);
    };

    public int insert(FinSettMethod record){
        return methodMapper.insert(record);

    };

    public int insertSelective(FinSettMethod record){
        return methodMapper.insertSelective(record);
    };



    public FinSettMethod selectByPrimaryKey(Integer id){
        return methodMapper.selectByPrimaryKey(id);
    };

    public int updateByPrimaryKeySelective(FinSettMethod record){
        return methodMapper.updateByPrimaryKey(record);
    };

    public int updateByPrimaryKey(FinSettMethod record){
        return  methodMapper.updateByPrimaryKey(record);
    };

}
