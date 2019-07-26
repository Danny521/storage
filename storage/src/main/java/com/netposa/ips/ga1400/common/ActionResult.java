package com.netposa.ips.ga1400.common;

import com.netposa.ips.ga1400.constants.ActionConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ActionResult implements Serializable {
    private static final long serialVersionUID = 6116515098874945764L;
    private int code;
    private String message;
    private Object content;
    private Object ext;

    public ActionResult() {
        this.code = ActionConstants.SUCCEED_CODE;
        this.message = ActionConstants.SUCCEED_MSG;
    }

    public static ActionResult build() {
        return new ActionResult();
    }

    public ActionResult setCode(int code) {
        this.code = code;
        return this;
    }

    public ActionResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public ActionResult setContent(Object content) {
        this.content = content;
        return this;
    }

    public ActionResult setExt(Object ext) {
        this.ext = ext;
        return this;
    }
}
