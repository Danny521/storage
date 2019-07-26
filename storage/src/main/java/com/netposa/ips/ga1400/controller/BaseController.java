package com.netposa.ips.ga1400.controller;

import com.netposa.ips.ga1400.common.ActionResult;

/**
 * Created by ZhiFan
 * 2019-03-19 9:52
 */
public class BaseController {

    protected ActionResult success() {
        return ActionResult.build();
    }

    protected ActionResult success(Object content) {
        return ActionResult.build().setContent(content);
    }

    protected ActionResult success(Object content, Object ext) {
        return ActionResult.build().setContent(content).setExt(ext);
    }

    protected ActionResult error(Integer code, String message, Object content, Object ext) {
        return ActionResult.build().setCode(code).setMessage(message).setContent(content).setExt(ext);
    }
}