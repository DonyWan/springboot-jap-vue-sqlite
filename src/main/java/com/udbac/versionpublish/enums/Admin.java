package com.udbac.versionpublish.enums;

/**
 * @author dundun.wang
 * @date 2019/10/11
 */
public enum Admin {
    YES("1"), NO("0");

    private String value;

    /**
     * 构造方法
     */
    private Admin(String value) {
        this.setValue(value);
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
}
