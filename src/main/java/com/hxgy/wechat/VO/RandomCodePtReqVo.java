package com.hxgy.wechat.VO;

public class RandomCodePtReqVo {
    private String msgType;
    private String productType;
    private String templateId;
    private String channelType;
    private String msgDest;
    private String msgContent;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getMsgDest() {
        return msgDest;
    }

    public void setMsgDest(String msgDest) {
        this.msgDest = msgDest;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
