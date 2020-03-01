package com.github.tanqizhou.share.wb.dictionary;

import java.util.Set;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/16:25
 * @Description: 词典操作接口
 */
public interface DictionaryOperate {

    //给词典添加单词
    public boolean addWord(String from,String word);

    //给词典添加单词
    public boolean contains(String from,String word);

    //移除词典的单词
    boolean removeWord(String from,String word);

    //打印词典里的词典
    void printWordDict(String from);

    // 获得操作的词典
    WordDictionary getWordDict();
}
