package com.github.tanqizhou.share.wb.dictionary;

/**
 * @auther: TanqiZhou
 * @Date: 2020/02/29/15:12
 * @Description: dictionary library factory
 */
public class WordDictionaryFactory {


    /**
     * production dictionary
     * @param from dictionary location
     * @param wordString dictionary string
     * @return Dictionary
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
