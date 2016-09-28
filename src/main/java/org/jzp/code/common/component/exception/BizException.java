package org.jzp.code.common.component.exception;

import org.jzp.code.common.component.code.BizCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务异常
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 6602674732286863727L;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int code;

    public BizException(String msg) {
        super(msg);
        logger.error(msg);
    }

    public BizException(Exception e) {
        super(e);
        logger.error("error", e);
    }

    public BizException(BizCode code) {
        super(code.getMessage());
        this.code = code.getValue();
        logger.error("code={};msg=", code.getValue(), code.getMessage());
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        logger.error("code={};msg=", code, msg);
    }

    public BizException(int code, String msg, Exception e) {
        super(msg, e);
        this.code = code;
        logger.error("code={};msg={}", code, msg);
    }

    /**
     * getters && setters
     */
    public int getCode() {
        return code;
    }
}
