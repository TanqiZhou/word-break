package com.github.tanqizhou.share.wb.dictionary;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.Assert.*;

/**
 * @auther: TanqiZhou
 * @Date: 2020/02/29/15:12
 * @Description: dictionary library operation test
 */
public class DefaultDictionaryOperateTest {

    DefaultDictionaryOperate defaultDictionaryOperate;

    String from = "default";

    String wordString = "{ i, like, sam, sung, samsung, mobile, ice, cream, man go}";

    @Before
    public void setUp() throws Exception {
        WordDictionary wordDictionary = WordDictionaryFactory.getWordDictionaryByString(from, wordString);
        defaultDictionaryOperate = new DefaultDictionaryOperate(wordDictionary);
        defaultDictionaryOperate.printWordDict(from);
    }

    @Test
    public void addWord() {
        defaultDictionaryOperate.addWord(from, "me");
        defaultDictionaryOperate.printWordDict(from);
        assertTrue(defaultDictionaryOperate.contains(from, "me"));
    }

    @Test
    public void removeWord() {
        defaultDictionaryOperate.removeWord(from, "sam");
        defaultDictionaryOperate.printWordDict(from);
        assertFalse(defaultDictionaryOperate.contains(from, "sam"));
    }

    @Test
    public void printWordDict() {
        defaultDictionaryOperate.printWordDict(from);
    }

    @Test
    public void getWordDict() {
        checkNotNull(defaultDictionaryOperate.getWordDict(),"defaultDictionaryOperate not null");
    }
}
