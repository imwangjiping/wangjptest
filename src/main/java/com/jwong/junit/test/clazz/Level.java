package com.jwong.junit.test.clazz;

/**
 * Level class
 *
 * @author J.Wong
 * @date 2017/12/22
 */
public enum Level {

    A("85-100", "优秀"), B("75-85", "良好"), C("60-75", "合格"), D("0-60", "不合格");

    private String range;
    private String text;

    Level(String range, String text) {
        this.range = range;
        this.text = text;
    }

    public String getRange() {
        return range;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return this.name() + this.text;
    }

}
