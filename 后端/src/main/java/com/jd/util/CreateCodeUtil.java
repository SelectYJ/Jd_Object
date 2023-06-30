package com.jd.util;

import java.util.Random;

/**
 * 生成验证码工具类
 */
public class CreateCodeUtil {
    public static String getCode(Integer count) {
        Random r = new Random();
        String code = "";
        for (int i = 0; i < count; i++) {
             code += r.nextInt(10);
        }
        return code;
    }
}
