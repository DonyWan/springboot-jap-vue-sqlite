package com.udbac.versionpublish.util;

/**
 * 封装的返回类型.
 * <p>
 * 第一个参数<code>code</code>为<code>boolean</code>类型.
 * <code>true</code>代表成功,<code>false</code>代表失败.
 * <p>
 * 第二个参数<code>message</code>,如果有错误信息,则进行补充.
 * <p>
 * 第三个参数<code>object</code>为任意对象,需要返回得数据得时候使用.
 * 
 * @author dundun.wang
 * @date 2019/09/24
 */
public class ResponseData {
    public static final boolean SUCCESS = true;
    public static final boolean FAILD = false;

    private boolean code;
    private String message;
    private Object object;

    public boolean getCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
