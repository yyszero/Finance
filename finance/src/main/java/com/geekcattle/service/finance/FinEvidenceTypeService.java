package com.geekcattle.service.finance;

import com.geekcattle.mapper.finance.FinEvidenceTypeMapper;
import com.geekcattle.model.finance.FinAccountCertificate;
import com.geekcattle.model.finance.FinEvidenceType;
import com.geekcattle.util.CamelCaseUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xinxiaoqiang
 * @Description:
 * @Date: 2018/5/18 23:04
 */
@Service
public class FinEvidenceTypeService {
    @Autowired
    private FinEvidenceTypeMapper finEvidenceTypeMapper;



    public List<FinEvidenceType> getPageList(FinEvidenceType certificate) {
        PageHelper.offsetPage(certificate.getOffset(), certificate.getLimit(), CamelCaseUtil.toUnderlineName(certificate.getSort())+" "+certificate.getOrder());
        return finEvidenceTypeMapper.selectAll();
    }
    public int deleteByPrimaryKey(Integer id){

        return finEvidenceTypeMapper.deleteByPrimaryKey(id);
    };

    public  List<FinEvidenceType> selectAll(){
        return finEvidenceTypeMapper.selectAll();
    }

    public int insert(FinEvidenceType record){
        return finEvidenceTypeMapper.insert(record);

    };

    public int insertSelective(FinEvidenceType record){
        return finEvidenceTypeMapper.insertSelective(record);
    };



    public FinEvidenceType selectByPrimaryKey(Integer id){
        return finEvidenceTypeMapper.selectByPrimaryKey(id);
    };

    public int updateByPrimaryKeySelective(FinEvidenceType record){
        return finEvidenceTypeMapper.updateByPrimaryKey(record);
    };

    public int updateByPrimaryKey(FinEvidenceType record){
        return  finEvidenceTypeMapper.updateByPrimaryKey(record);
    };
}
