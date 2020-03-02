package com.github.tanqizhou.share.wb.dictionary;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @auther: TanqiZhou
 * @Date: 2020/02/29/15:12
 * @Description: dictionary library factory test
 */
public class WordDictionaryFactoryTest {

    /**
     * build factory test
     */
    @Test
    public void getWordDictionaryByStringTest(){
        String from = "default";
        String wordString = "{ i, like, sam, sung, samsung, mobile, ice, cream, man go}";
        WordDictionary wordDictionary = WordDictionaryFactory.getWordDictionaryByString(from, wordString);
        DefaultDictionaryOperate defaultDictionaryOperate = new DefaultDictionaryOperate(wordDictionary);
        defaultDictionaryOperate.printWordDict(from);
        assertNotNull(from, "from is required");
    }
}
