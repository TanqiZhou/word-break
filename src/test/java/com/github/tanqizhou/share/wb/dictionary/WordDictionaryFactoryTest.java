package com.github.tanqizhou.share.wb.dictionary;

import com.google.common.base.Splitter;
import com.google.common.collect.SetMultimap;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @auther: TanqiZhou
 * @Date: 2020/02/29/15:12
 * @Description: 字典库工厂测试
 */
public class WordDictionaryFactoryTest {

    /**
     * 构建工厂测试
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
