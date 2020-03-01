package com.github.tanqizhou.share.wb;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/15:46
 * @Description: 字典位置
 */
public enum ConstDictionary {
    //默认字典
    DefaultDictionary("default"),
    //用户字典
    UserDictionary("user"),
    //合并个字典
    AllDictionary("two","default");

    private List<String> from;

    ConstDictionary(String ...from) {
        this.from = Arrays.asList(from);
    }

    public List<String> getFrom() {
        return from;
    }
}
