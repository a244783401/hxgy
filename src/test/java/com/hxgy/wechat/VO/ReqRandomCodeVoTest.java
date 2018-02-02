package com.hxgy.wechat.VO;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;


public class ReqRandomCodeVoTest {
@Test
public void test(){
    ReqRandomCodeVo reqRandomCodeVo=new ReqRandomCodeVo();
    reqRandomCodeVo.setPhoneno("15202809020");
    reqRandomCodeVo.setType("0");
    reqRandomCodeVo.setIsRegister("00");
    reqRandomCodeVo.setChannel("2");
    System.out.println(JSONObject.toJSONString(reqRandomCodeVo));
}
}