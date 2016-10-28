package org.jzp.code.common.component.util;

import org.jzp.code.common.component.code.BizCode;
import org.jzp.code.common.component.exception.BizException;
import org.slf4j.Logger;

/**
 * 参数校验工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public class CheckUtil {

    /**
     * 验证参数非空
     * <p>StringUtil.isBlank4mulField("a,b,c", ",", logger, a, b, c);</p>
     *
     * @param str      待验证字符串名称，用splitStr隔开
     * @param splitStr 分隔符
     * @param logger   日志
     * @param objs     待验证字符数组
     * @return
     * @throws BizException
     */
    public static final void isBlank4MulField(String str, String splitStr, Logger logger, Object... objs) throws BizException {
        if (objs == null || objs.length < 1) {
            throw new BizException(BizCode.BIZ_CHECK_PARAM_IS_NULL.getValue(), "param is null!");
        }

        String[] fieldArray = str.split(splitStr);
        if (fieldArray.length != objs.length) {
            logger.error("组件配置错误");
            throw new BizException(BizCode.BIZ_REGEX_MATCH_NUM_NOT_EQUAL_EXCEPTION.getValue(), "组件配置错误!");
        }

        for (int i = 0; i < objs.length; i++) {
            if (objs[i] == null || "".equals(objs[i])) {
                logger.error("error::" + fieldArray[i] + " is blank!");
                throw new BizException(BizCode.BIZ_CHECK_PARAM_IS_NULL.getValue(), fieldArray[i] + " is blank!");
            }
        }
    }
}
