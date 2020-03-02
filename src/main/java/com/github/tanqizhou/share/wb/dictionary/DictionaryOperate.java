package com.github.tanqizhou.share.wb.dictionary;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/16:25
 * @Description: dictionary operation interface
 */
public interface DictionaryOperate {

    //add words to dictionary
    public boolean addWord(String from,String word);

    //determine if the dictionary contains words
    public boolean contains(String from,String word);

    //remove dictionary words
    boolean removeWord(String from,String word);

    //print dictionary from dictionary
    void printWordDict(String from);

    // get operation dictionary
    WordDictionary getWordDict();
}
