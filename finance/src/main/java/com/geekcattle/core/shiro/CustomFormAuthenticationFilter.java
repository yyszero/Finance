/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.core.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 *
 *  23 0023 上午 11:23
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    public void setLoginUrl(String loginUrl) {
        super.setLoginUrl("/member/login");
    }

    @Override
    public void setSuccessUrl(String successUrl) {
        super.setSuccessUrl("/member/index");
    }
}
