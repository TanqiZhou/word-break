package com.github.tanqizhou.share.wb.dictionary;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.SetMultimap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/16:41
 * @Description: 字典
 */
public class WordDictionary {

    //字典
    private Object wordDict = HashMultimap.create();

    //一词对对个词
    private Object wordMoreWord =  ArrayListMultimap.create();

    public Object getWordDict() {
        return wordDict;
    }

    public void setWordDict(Object wordDict) {
        this.wordDict = wordDict;
    }

    public Object getWordMoreWord() {
        return wordMoreWord;
    }

    public void setWordMoreWord(Object wordMoreWord) {
        this.wordMoreWord = wordMoreWord;
    }
}
