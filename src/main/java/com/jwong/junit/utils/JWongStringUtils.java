package com.jwong.junit.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JWongStringUtils class
 *
 * @author J.Wong
 * @date 2017/12/20
 */
public class JWongStringUtils {

    /**
     * 所有 str 是否都为 NULL 或者 空字符串
     *
     * @param str String对象
     * @return Boolean true:全部为空;  false:有任意一个不为空
     */
    public static Boolean noString(String... str) {
        for (String s : str) {
            if (null != s && !"".equals(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将所有参数以toString()的方式连接起来
     *
     * @param obj Object对象
     * @return StringBuffer
     */
    public static StringBuffer connectEverything(Object... obj) {
        StringBuffer sb = new StringBuffer();
        for (Object o : obj) {
            if (null != o) {
                sb.append(o);
            }
        }
        return sb;
    }

    /**
     * List<String> 转 Set<String>
     *
     * @param list<String>
     * @return Set<String>
     */
    public static Set<String> List2Set(List<String> list) {
        Set<String> stringSet = new HashSet<>();
        list.forEach((str) -> stringSet.add(str));
        return stringSet;
    }
}
