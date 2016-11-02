package org.jzp.code.common.component.util;

import com.google.common.collect.Lists;
import org.jzp.code.common.component.data.CommonCode;
import org.jzp.code.common.component.data.Message;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 返回结果处理工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-10-27
 */
public class ResultUtil {

    /**
     * 从一个集合中返回第一个元素
     *
     * @param <T>
     * @param list
     * @return 元素
     */
    public static <T> T returnFirst4List(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 从一个对象拷贝属性到指定对象
     *
     * @param <T>
     * @param src
     * @param dst
     * @return 元素
     * @throws Exception
     */
    public static <T, D> D returnCopyClass(T src, Class<D> dst) throws Exception {
        if (src == null) {
            return null;
        }
        D result = dst.newInstance();
        BeanUtils.copyProperties(src, result);
        return result;
    }

    /**
     * 从一个集合中返回第一个元素(并拷贝元素属性到指定对象并返回,PO转VO使用)
     *
     * @param <T>
     * @param list
     * @param dst
     * @return 元素
     * @throws Exception
     */
    public static <T, D> D returnFirst4ListCopyClass(List<T> list, Class<D> dst) throws Exception {
        T po = returnFirst4List(list);
        if (po == null) {
            return null;
        }
        D result = dst.newInstance();
        BeanUtils.copyProperties(po, result);
        return result;
    }

    /**
     * 从一个集合拷贝对象里相同元素到指定对象集合(List<PO>转List<VO>使用)
     *
     * @param <T>
     * @param list
     * @param dst
     * @return 元素
     * @throws Exception
     */
    public static <T, D> List<D> returnCopyList(List<T> list, Class<D> dst) throws Exception {
        if (returnFirst4List(list) == null) {
            return null;
        }

        List<D> result = Lists.newArrayList();
        for (T t : list) {
            D d = dst.newInstance();
            BeanUtils.copyProperties(t, d);
            result.add(d);
        }
        return result;
    }

    /**
     * 判断接口返回结果是否为成功
     *
     * @param msg
     * @return 判断结果
     */
    public static boolean returnIsSucc(Message msg) {
        return returnIsSucc(msg, CommonCode.SUCCESS.getValue());
    }

    /**
     * 判断接口返回结果是否为成功
     *
     * @param msg
     * @param succCode
     * @return 判断结果
     */
    public static boolean returnIsSucc(Message msg, int succCode) {
        if (msg == null || succCode != msg.getCode()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 判断接口返回结果是否为成功 && data非空
     *
     * @param msg
     * @return 判断结果
     */
    public static boolean returnIsSucc2NotNull(Message msg) {
        return returnIsSucc2NotNull(msg, CommonCode.SUCCESS.getValue());
    }

    /**
     * 判断接口返回结果是否为成功 && data非空
     *
     * @param msg
     * @param succCode
     * @return 判断结果
     */
    public static boolean returnIsSucc2NotNull(Message msg, int succCode) {
        if (msg == null || succCode != msg.getCode() || msg.getData() == null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}