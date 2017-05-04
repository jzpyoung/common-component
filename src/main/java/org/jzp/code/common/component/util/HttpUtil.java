package org.jzp.code.common.component.util;

import com.sun.deploy.net.HttpUtils;
import org.jzp.code.common.component.data.BizCode;
import org.jzp.code.common.component.constant.Constants;
import org.jzp.code.common.component.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * HTTP工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static RestTemplate restTemplate;

    private static Integer readTimeOut = 10 * 1000;
    private static Integer connectTimeOut = 10 * 1000;

    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(readTimeOut);
        requestFactory.setConnectTimeout(connectTimeOut);
        restTemplate = new RestTemplate(requestFactory);
    }

    private HttpUtil() {
    }

    public static <T> T getPostRequestResult(String url, MultiValueMap<String, String> params, Class<T> responseType) {
        return restTemplate.postForObject(url, params, responseType);
    }

    public static <T> T getPostRequestResult(String url, Map<String, String> params, Class<T> responseType) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
        for (Map.Entry<String, String> m : params.entrySet()) {
            multiValueMap.set(m.getKey(), m.getValue());
        }
        return getPostRequestResult(url, multiValueMap, responseType);
    }

    public static <T> T getGetRequestResult(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    public static <T> T getGetRequestResult(String url, Class<T> responseType, Object... urlVariables) {
        return restTemplate.getForObject(url, responseType, urlVariables);
    }

    public static <T> T getGetRequestResult(String url, String str, Class<T> responseType, String... replacements) {
        if (replacements == null || replacements.length < 1) {
            return getGetRequestResult(url, responseType);
        }

        String[] fieldArray = str.split(Constants.SPLIT_STR);

        if (fieldArray.length != replacements.length) {
            logger.error(BizCode.BIZ_HTTP_PARAM_ASSEMBLY_EXCEPTION.getMessage());
            throw new BizException(BizCode.BIZ_HTTP_PARAM_ASSEMBLY_EXCEPTION);
        }

        for (int i = 0; i < replacements.length; i++) {
            url = url.replace(fieldArray[i], replacements[i]);
        }
        return getGetRequestResult(url, responseType);
    }
}
