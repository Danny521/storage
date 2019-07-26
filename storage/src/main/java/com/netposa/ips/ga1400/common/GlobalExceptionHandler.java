package com.netposa.ips.ga1400.common;


import com.netposa.ips.ga1400.constants.ActionConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ActionResult customerErrorHandler(HttpServletRequest req, Exception e) {
        // 记录日志信息

        log.error("全局异常捕获： ip - {} req - {} error - {}", req.getRemoteHost(), req.getServletPath(), e.getMessage());
        // 返回通用数据结构 附加异常信息
        return new ActionResult(ActionConstants.FAILED_CODE, e.getMessage(), null, null);
    }
}