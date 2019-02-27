package com.geekcattle.service.finance;

import com.geekcattle.mapper.finance.FinAccountCertificateMapper;
import com.geekcattle.model.finance.FinAccountCertificate;
import com.geekcattle.util.CamelCaseUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xinxiaoqiang
 * @Description:
 * @Date: 2018/5/18 22:57
 */
@Service
public class FinAccountCertificateService {
    @Autowired
    private FinAccountCertificateMapper finAccountCertificateMapper;


    public List<FinAccountCertificate> getPageList(FinAccountCertificate certificate) {
        PageHelper.offsetPage(certificate.getOffset(), certificate.getLimit(), CamelCaseUtil.toUnderlineName(certificate.getSort())+" "+certificate.getOrder());
        return finAccountCertificateMapper.selectAll();
    }

    public int deleteByPrimaryKey(Integer id){

       return finAccountCertificateMapper.deleteByPrimaryKey(id);
    };

    public int insert(FinAccountCertificate record){
       return finAccountCertificateMapper.insert(record);

    };

    public int insertSelective(FinAccountCertificate record){
        return finAccountCertificateMapper.insertSelective(record);
    };



    public FinAccountCertificate selectByPrimaryKey(Integer id){
        return finAccountCertificateMapper.findById(id);
    };

    public int updateByPrimaryKeySelective(FinAccountCertificate record){
        return finAccountCertificateMapper.updateByPrimaryKey(record);
    };

    public int updateByPrimaryKey(FinAccountCertificate record){
        return  finAccountCertificateMapper.updateByPrimaryKey(record);
    };

    public List<FinAccountCertificate> findAll() {
        return  finAccountCertificateMapper.selectAll();
    }

    public FinAccountCertificate findByCode(String code) {
        return finAccountCertificateMapper.findByCode(code);
    }

    /**
     * 更新余额
     * @param finAccountCertificate
     * @return
     */
    public  int updateOver(FinAccountCertificate finAccountCertificate){

        return  finAccountCertificateMapper.updateOver(finAccountCertificate);
    }

}
