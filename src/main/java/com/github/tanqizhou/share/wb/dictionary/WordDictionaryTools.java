package com.github.tanqizhou.share.wb.dictionary;

import com.github.tanqizhou.share.wb.ConstDictionary;
import com.google.common.base.Splitter;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.SetMultimap;

import java.util.List;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/18:11
 * @Description: 字典工具类
 */
public class WordDictionaryTools {

    /**
     * 添加合并字典
     * @param wordDictionary 字典
     * @param from 新字典位置
     * @param wordString 字典字符串
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public static void addWordDictionary(WordDictionary wordDictionary,String from, String wordString){
        SetMultimap wordDict = (SetMultimap) wordDictionary.getWordDict();
        ListMultimap wordMoreWord = (ListMultimap) wordDictionary.getWordMoreWord();
        wordString = wordString.replaceAll("\\{", "");
        wordString = wordString.replaceAll("}","");
        List<String> split = Splitter.on(',').trimResults().omitEmptyStrings().splitToList(wordString);
        wordDict.put(from,"and");
        split.forEach( s -> {
            String keyword = s.replaceAll(" ", "");
            wordDict.put(from, keyword);
            if (s.contains(" ")) {
                wordMoreWord.put(keyword,s);
                if (split.contains(keyword)) {
                    if (!wordMoreWord.get(keyword).contains(keyword)) {
                        wordMoreWord.put(keyword,keyword);
                    }
                }
            }
        });
    }
}
