package com.github.tanqizhou.share.wb.dictionary;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/16:41
 * @Description: Wordbook
 */
public class WordDictionary {

    //character dictionary
    private Object wordDict = HashMultimap.create();

    //word to word
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
