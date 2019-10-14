package com.udbac.versionpublish.enums;

/**
 * @author dundun.wang
 * @date 2019/10/12
 */
public enum Provinces {
    北京("bj"), 安徽("ah"), 重庆("cq"), 福建("fj"), 广东("gd"), 广西("gx"), 甘肃("gs"), 贵州("gz"), 河北("hb"), 河南("ha"), 海南("hi"),
    湖北("hb"), 湖南("hn"), 黑龙江("hlj"), 吉林("jl"), 江苏("js"), 江西("jx"), 辽宁("ln"), 内蒙古("nmg"), 宁夏("nx"), 青海("qh"), 上海("sh"),
    四川("sc"), 山东("sd"), 山西("sx"), 陕西("sn"), 天津("tj"), 新疆("xj"), 西藏("xz"), 云南("yn"), 浙江("zj");

    private String value;

    /**
     * 
     */
    private Provinces(String value) {
        // TODO Auto-generated constructor stub
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
