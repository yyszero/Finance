/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.finance;

import com.geekcattle.core.shiro.AdminShiroUtil;
import com.geekcattle.model.finance.FinAccountCertificate;
import com.geekcattle.model.finance.FinCertificateBase;
import com.geekcattle.model.finance.FinCertificateItem;
import com.geekcattle.model.finance.FinEvidenceType;
import com.geekcattle.service.finance.FinAccountCertificateService;
import com.geekcattle.service.finance.FinCertificateBaseService;
import com.geekcattle.service.finance.FinCertificateItemService;
import com.geekcattle.service.finance.FinEvidenceTypeService;
import com.geekcattle.util.DateUtil;
import com.geekcattle.util.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 信息管理
 */
@Controller
@RequestMapping("/console/certifcateBase")
public class FinCertificateBaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinCertificateBaseService service;
    @Autowired
    private FinAccountCertificateService accountCertificateService;
    @Autowired
    private FinCertificateItemService finCertificateItemService;
    @Autowired
    private FinEvidenceTypeService finEvidenceTypeService;



    @RequiresPermissions("certifcateBase:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {

        return "console/certifcateBase/index";
    }

    @RequiresPermissions("certifcateBase:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(FinCertificateBase certifcateBase) {
        ModelMap map = new ModelMap();
        List<FinCertificateBase> Lists = service.getPageList(certifcateBase);
        map.put("pageInfo", new PageInfo<FinCertificateBase>(Lists));
        map.put("queryParam", certifcateBase);
        return ReturnUtil.Success("加载成功", map, null);
    }



    @RequiresPermissions("certifcateBase:edit")
    @RequestMapping(value = "/from", method = {RequestMethod.GET})
    public String from(FinCertificateBase certifcateBase, Model model) {
        if (null!=certifcateBase.getId()) {
            certifcateBase = service.getById(certifcateBase.getId());
        }

        List<FinEvidenceType> finEvidenceTypeList  =finEvidenceTypeService.selectAll();
        List<FinAccountCertificate> finAccountCertificates =accountCertificateService.findAll();

        model.addAttribute("finEvidenceTypeList", finEvidenceTypeList);
        model.addAttribute("finAccountCertificates", finAccountCertificates);
        model.addAttribute("currentdate", DateUtil.getCurrentDate());
        model.addAttribute("currentUser", AdminShiroUtil.getUserInfo().getUsername());
        model.addAttribute("certifcateBase", certifcateBase);
        return "console/certifcateBase/from";
    }


    @RequiresPermissions("certifcateBase:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    public String edit(FinCertificateBase certifcateBase, Model model) {
        if (null!=certifcateBase.getId()) {
            certifcateBase = service.getById(certifcateBase.getId());
        }


        List<FinEvidenceType> finEvidenceTypeList  =finEvidenceTypeService.selectAll();
        List<FinCertificateItem> finCertificateItems = finCertificateItemService.findByCertificateId(certifcateBase.getId());

        model.addAttribute("finEvidenceTypeList", finEvidenceTypeList);

        double total_de = 0;
        double total_la = 0;
        for (FinCertificateItem item :finCertificateItems
             ) {
            total_de+=item.getDebitAmount();
            total_la+=item.getLoanAmount();
        }
        model.addAttribute("total_de", total_de);
        model.addAttribute("total_la", total_la);
        model.addAttribute("finCertificateItems", finCertificateItems);

        List<FinAccountCertificate> finAccountCertificates =accountCertificateService.findAll();

        model.addAttribute("finAccountCertificates", finAccountCertificates);
        model.addAttribute("currentdate", DateUtil.getCurrentDate());
        model.addAttribute("currentUser", AdminShiroUtil.getUserInfo().getUsername());
        model.addAttribute("certifcateBase", certifcateBase);
        return "console/certifcateBase/edit";
    }


    @RequiresPermissions("certifcateBase:index")
    @RequestMapping(value = "/itemList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap itemList(FinCertificateBase certifcateBase) {
        ModelMap map = new ModelMap();
        if (null!=certifcateBase.getId()) {
            certifcateBase = service.getById(certifcateBase.getId());
        }

        map.put("queryParam", certifcateBase);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @RequiresPermissions("certifcateBase:save")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public String save(@ModelAttribute("certifcateBase")  FinCertificateBase certifcateBase, BindingResult result) {

        try {
            certifcateBase.setBookkeepingTime(new Date());//记账时间
            certifcateBase.setCreateTime(new Date());
            certifcateBase.setCreateUser(AdminShiroUtil.getUserInfo().getUsername());//创建人
            certifcateBase.setOrders(AdminShiroUtil.getUserInfo().getUsername());//制单
            certifcateBase.setStatus(0);//待复核
           service.insert(certifcateBase);

            for (FinCertificateItem  item:certifcateBase.getFinCertificateItems()
                 ) {
                if(StringUtils.isNotBlank(item.getSummary())){
                    item.setCertificateId(certifcateBase.getId());
                    if(StringUtils.isNotBlank(item.getDebitAccountCode())){
                        item.setDebitAccount(accountCertificateService.findByCode(item.getDebitAccountCode()).getAccountName());
                    }
                    if(null==item.getDebitAmount()){
                        item.setDebitAmount(0d);
                    }
                    if(null==item.getLoanAmount()){
                        item.setLoanAmount(0d);
                    }
                    finCertificateItemService.save(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "console/certifcateBase/index";
    }




    @RequiresPermissions("certifcateBase:save")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public String update(@ModelAttribute("certifcateBase")  FinCertificateBase certifcateBase, BindingResult result) {

        try {
            certifcateBase.setBookkeepingTime(new Date());//记账时间
            certifcateBase.setCreateTime(new Date());
            certifcateBase.setCreateUser(AdminShiroUtil.getUserInfo().getUsername());//创建人
            certifcateBase.setOrders(AdminShiroUtil.getUserInfo().getUsername());//制单
            service.save(certifcateBase);
            //
            finCertificateItemService.deleteByCertifcateBaseId(certifcateBase.getId());

            for (FinCertificateItem  item:certifcateBase.getFinCertificateItems()
                    ) {
                if(StringUtils.isNotBlank(item.getSummary())){
                    item.setCertificateId(certifcateBase.getId());
                    if(StringUtils.isNotBlank(item.getDebitAccountCode())){
                        item.setDebitAccount(accountCertificateService.findByCode(item.getDebitAccountCode()).getAccountName());
                    }
                    if(StringUtils.isNotBlank(item.getLoanAccountCode())){
                        item.setLoanAccount(accountCertificateService.findByCode(item.getLoanAccountCode()).getAccountName());
                    }
                    finCertificateItemService.save(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "console/certifcateBase/index";
    }

    @RequiresPermissions("certifcateBase:delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap delete(String[] ids) {
        try {
            if ("null".equals(ids) || "".equals(ids)) {
                return ReturnUtil.Error("Error", null, null);
            } else {
                for (String id : ids) {
                    service.deleteById(Integer.valueOf(id));
                }
                return ReturnUtil.Success("操作成功", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }




    @RequiresPermissions("certifcateBase:index")
    @RequestMapping(value = "/getMaxNumber")
    @ResponseBody
    public int getMaxNumber(FinCertificateBase finCertificateBase) {
        try {
            FinCertificateBase  fin = service.getMaxNumber(finCertificateBase);
              if(null!=fin){
                  return fin.getNumber()+1;
              }
        } catch (Exception e) {
            e.printStackTrace();
            return  1;
        }
        return 1;
    }


    /**
     * 复核
     * @param certifcateBase
     * @param model
     * @return
     */
    @RequiresPermissions("certifcateBase:review")
    @RequestMapping(value = "/review", method = {RequestMethod.GET})
    public String revew(FinCertificateBase certifcateBase, Model model) {
        if (null!=certifcateBase.getId()) {
            certifcateBase = service.getById(certifcateBase.getId());
        }


        List<FinCertificateItem> finCertificateItems = finCertificateItemService.findByCertificateId(certifcateBase.getId());


        double total_de = 0;
        double total_la = 0;
        for (FinCertificateItem item :finCertificateItems
                ) {
            total_de+=item.getDebitAmount();
            total_la+=item.getLoanAmount();
        }
        model.addAttribute("total_de", total_de);
        model.addAttribute("total_la", total_la);
        model.addAttribute("finCertificateItems", finCertificateItems);

        List<FinAccountCertificate> finAccountCertificates =accountCertificateService.findAll();

        model.addAttribute("finAccountCertificates", finAccountCertificates);
        model.addAttribute("currentdate", DateUtil.getCurrentDate());
        model.addAttribute("currentUser", AdminShiroUtil.getUserInfo().getUsername());
        model.addAttribute("certifcateBase", certifcateBase);
        return "console/certifcateBase/review";
    }


    /**
     * 复核
     * @param finCertificateBase
     * @return
     */
    @RequiresPermissions("certifcateBase:review")
    @RequestMapping(value = "/updatereview", method = {RequestMethod.POST})
    public String updateReview( FinCertificateBase finCertificateBase) {
        try {
            if(finCertificateBase.getId()!=null) {
                finCertificateBase.setReview(AdminShiroUtil.getUserInfo().getUsername());
                finCertificateBase.setStatus(1);
                service.review(finCertificateBase);

                //更新余额

               List<FinCertificateItem> finCertificateItems = finCertificateItemService.findByCertificateId(finCertificateBase.getId());

                for (FinCertificateItem item:finCertificateItems
                     ) {

                    FinAccountCertificate finAccountCertificate = accountCertificateService.findByCode(item.getDebitAccountCode());

                    if(null!=finAccountCertificate){
                        //获取科目余额
                        /**
                         * 总账余额是最初的钱+(借方-贷方)。
                           科目余额也是最初的钱+(借方-贷方)。
                         */
                       double overage = finAccountCertificate.getOverage();

                       double overReal = finAccountCertificate.getOverReal();

                       double ov =overReal+(item.getDebitAmount()-item.getLoanAmount());
                        FinAccountCertificate params = new FinAccountCertificate();
                        params.setId(finAccountCertificate.getId());
                        params.setOverReal(ov);
                        //更新总账
                        accountCertificateService.updateOver(params);
                        //更新分账
                        FinCertificateItem finCertificateItem = new FinCertificateItem();
                        finCertificateItem.setOver(ov);
                        finCertificateItem.setId(item.getId());
                        finCertificateItemService.updateOver(finCertificateItem);
                    }

                }



                return "console/certifcateBase/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "console/certifcateBase/index";
    }


}
