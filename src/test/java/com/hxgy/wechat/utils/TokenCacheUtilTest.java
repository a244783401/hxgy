package com.hxgy.wechat.utils;

import org.junit.Test;

import static com.hxgy.wechat.utils.TokenCacheUtil.getKey;
import static com.hxgy.wechat.utils.TokenCacheUtil.setKey;

public class TokenCacheUtilTest {
    @Test
    public void setData() throws Exception {
        setKey("15202809020","123456");
        System.out.println(getKey("15202809020"));
    }


}