package com.hxgy.wechat.VO;

/**
 * Created by wx on 2018/1/25.
 */
public class CategoryVo {
    private Long id;
    private String code;
    private String categdesc;
    private String version;
    private String stand;
    private String push;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcategdesc() {
        return categdesc;
    }

    public void setcategdesc(String categdesc) {
        this.categdesc = categdesc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getPush() {
        return push;
    }

    public void setPush(String push) {
        this.push = push;
    }
}
