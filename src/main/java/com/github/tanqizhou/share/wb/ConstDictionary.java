package com.github.tanqizhou.share.wb;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/15:46
 * @Description: dictionary location
 */
public enum ConstDictionary {
    //default dictionary
    DefaultDictionary("default"),
    //user dictionary
    UserDictionary("user"),
    //merge a dictionary
    AllDictionary("two","default");

    private List<String> from;

    ConstDictionary(String ...from) {
        this.from = Arrays.asList(from);
    }

    public List<String> getFrom() {
        return from;
    }
}
