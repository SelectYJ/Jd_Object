package com.jd;


public class tests {
    public static void main(String[] args) {
        String str = "1301808700@qq.com";
        String substring = str.substring(0, str.indexOf("@"));
        System.out.println(substring);
    }
}
