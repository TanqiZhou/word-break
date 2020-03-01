package com.github.tanqizhou.share.wb.dictionary;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.SetMultimap;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther: TanqiZhou
 * @Date: 2020/02/29/15:12
 * @Description: 字典库工厂
 */
public class WordDictionaryFactory {


    /**
     * 生产字典
     * @param from 字典位置
     * @param wordString 字典字符串
     * @return 字典
     */
    public static WordDictionary getWordDictionaryByString(String from, String wordString){
        if (from != null && wordString != null) {
            WordDictionary wordDictionary = new WordDictionary();
            WordDictionaryTools.addWordDictionary(wordDictionary,from,wordString);
            return wordDictionary;
        }else {
            throw new RuntimeException("from not is null, wordString not is null");
        }
    }
}
