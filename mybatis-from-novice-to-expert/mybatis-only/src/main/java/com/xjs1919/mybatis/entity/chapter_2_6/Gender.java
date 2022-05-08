package com.xjs1919.mybatis.entity.chapter_2_6;

/**
 * @author jiashuai.xujs
 * @date 2022/3/7 10:12
 */
public enum Gender implements EnumAble<Integer>{

    MALE(1, "男"),
    FEMALE(2, "女")
    ;

    private Integer value;
    private String label;

    Gender(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }
}
