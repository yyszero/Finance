/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.finance;

import com.geekcattle.model.finance.FinAccountCertificate;
import com.geekcattle.service.finance.FinAccountCertificateService;
import com.geekcattle.util.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 信息管理
 */
@Controller
@RequestMapping("/console/accountCertifcate")
public class FinAccountCertificateController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinAccountCertificateService service;



    @RequiresPermissions("accountCertifcate:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {

        return "console/accountCertifcate/index";
    }

    @RequiresPermissions("accountCertifcate:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(FinAccountCertificate accountCertifcate) {
        ModelMap map = new ModelMap();
        List<FinAccountCertificate> Lists = service.getPageList(accountCertifcate);
        map.put("pageInfo", new PageInfo<FinAccountCertificate>(Lists));
        map.put("queryParam", accountCertifcate);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @RequiresPermissions("accountCertifcate:edit")
    @RequestMapping(value = "/from", method = {RequestMethod.GET})
    public String from(FinAccountCertificate accountCertifcate, Model model) {
        if (null!=accountCertifcate.getId()) {
            accountCertifcate = service.selectByPrimaryKey(accountCertifcate.getId());
        }

        model.addAttribute("accountCertifcate", accountCertifcate);
        return "console/accountCertifcate/from";
    }

    @RequiresPermissions("accountCertifcate:save")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ModelMap save(@Valid FinAccountCertificate accountCertifcate, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError er : result.getAllErrors()) return ReturnUtil.Error(er.getDefaultMessage(), null, null);
        }

        try {
            if (null==accountCertifcate.getId()) {
                accountCertifcate.setOverReal(accountCertifcate.getOverage());
                service.insert(accountCertifcate);
            } else {
                service.updateByPrimaryKeySelective(accountCertifcate);
            }
            return ReturnUtil.Success("操作成功", null, "/console/accountCertifcate/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }

    @RequiresPermissions("accountCertifcate:delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap delete(String[] ids) {
        try {
            if ("null".equals(ids) || "".equals(ids)) {
                return ReturnUtil.Error("Error", null, null);
            } else {
                for (String id : ids) {
                    service.deleteByPrimaryKey(Integer.valueOf(id));
                }
                return ReturnUtil.Success("操作成功", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }






}
