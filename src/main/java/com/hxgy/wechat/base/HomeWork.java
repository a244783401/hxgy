package com.hxgy.wechat.base;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

/**
 * @author zy
 * @create 2018-03-09 12:32
 **/
public class HomeWork {

    private static Properties properties;
    static {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(HomeWork.class.getClassLoader().getResourceAsStream("work.properties"),"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getRe(int num){
        if (getValue(num) == null){
            return "Cannot found!";
        }
        for (int i = 0;i <= num;i++){
            String value = getValue(i);
            int leng = value.length();
            if (leng < 13 || judge(i,value)){
                return "ERROR"+num;
            }
        }
        String value = getValue(num);
        int first = Integer.parseInt(value.split("//s+")[1]+value.split("//s+")[4]);
        int second = Integer.parseInt(value.split("//s+")[2]+value.split("//s+")[5]);
        int third = Integer.parseInt(value.split("//s+")[3]+value.split("//s+")[6]);
        return value.split("\\s+")[0] + " " + num+" "+first+" "+second+" "+third;
    }

    public static void main(String[] arg0){
        System.out.print("plane1 1 1 1 2 3 4".split("//+")[]);
    }

    public boolean judge(int num,String value){
           if(getValue(num - 1).substring(9,13).equals(value.substring(3,7))){
               return true;
           }else return false;
    }
    public String getValue(int num) {
        String value = properties.getProperty(String.valueOf(num).trim());
        if (StringUtils.isBlank(value)){
            return null;
        }
        return value;
    }

}
