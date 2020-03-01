package com.github.tanqizhou.share.wb;

import com.github.tanqizhou.share.wb.dictionary.DefaultDictionaryOperate;
import com.github.tanqizhou.share.wb.dictionary.WordDictionary;
import com.github.tanqizhou.share.wb.dictionary.WordDictionaryFactory;
import com.github.tanqizhou.share.wb.dictionary.WordDictionaryTools;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * @auther: TanqiZhou
 * @Date: 2020/02/29/18:12
 * @Description: 分词测试类
 */
public class WordSegmenterTest {
    //字典
    WordDictionary wordDictionary;
    //字典操作类
    DefaultDictionaryOperate defaultDictionaryOperate;
    //分词器
    WordSegmenter wordSegmenter;

    String input = "ilikesamsungmobile";

    String input2 = "ilikeicecreamandmango";

    //默认字典
    String from = "default";
    String defaulTwordString = "{ i, like, sam, sung, samsung, mobile, ice, cream, man go}";

    //用户字典
    String user = "user";
    String userWordString = "{ i, like, sam, sung, mobile, icecream, man go, mango}";

    //第二个字典
    String two = "two";
    String TwoWordString = "{ i, like, sam, sung, mobile, icecream, man go, mango}";

    /**
     * Stage1 默认字典测
     */
    @Test
    public void seg() {
        wordDictionary = WordDictionaryFactory.getWordDictionaryByString(from, defaulTwordString);
        defaultDictionaryOperate = new DefaultDictionaryOperate(wordDictionary);
        defaultDictionaryOperate.printWordDict(from);
        wordSegmenter = new WordSegmenter(wordDictionary);
        for (StringBuilder stringBuilder : wordSegmenter.seg(input)) {
            assertEquals(stringBuilder.toString(), "i like sam sung mobile");
            System.out.println(stringBuilder.toString());
        }
        for (StringBuilder stringBuilder : wordSegmenter.seg(input2)) {
            assertEquals(stringBuilder.toString(), "i like ice cream and man go");
            System.out.println(stringBuilder.toString());
        }
    }

    /**
     * Stage2  用户字典测试
     */
    @Test
    public void segByUser() {
        wordDictionary = WordDictionaryFactory.getWordDictionaryByString(user,userWordString);
        defaultDictionaryOperate = new DefaultDictionaryOperate(wordDictionary);
        defaultDictionaryOperate.printWordDict(from);
        wordSegmenter = new WordSegmenter(wordDictionary);
        for (StringBuilder stringBuilder : wordSegmenter.segByUser(input)) {
            assertEquals(stringBuilder.toString(), "i like sam sung mobile");
            System.out.println(stringBuilder.toString());
        }
        for (StringBuilder stringBuilder : wordSegmenter.segByUser(input2)) {
            System.out.println(stringBuilder.toString());
        }
        assertEquals(wordSegmenter.segByUser(input2).get(0).toString(), "i like icecream and man go");
        assertEquals(wordSegmenter.segByUser(input2).get(1).toString(), "i like icecream and mango");
    }

    /**
     * Stage3 合并字典测试
     */
    @Test
    public void segByAll() {
        wordDictionary = WordDictionaryFactory.getWordDictionaryByString(from, defaulTwordString);
        defaultDictionaryOperate = new DefaultDictionaryOperate(wordDictionary);
        defaultDictionaryOperate.printWordDict(from);
        wordSegmenter = new WordSegmenter(wordDictionary);
        WordDictionaryTools.addWordDictionary(defaultDictionaryOperate.getWordDict(),two,TwoWordString);
        List<StringBuilder> StageInput1 = wordSegmenter.segByAll(input);
        List<StringBuilder> StageInput2 = wordSegmenter.segByAll(input2);
        System.out.println(StageInput1);
        System.out.println(StageInput2);
        assertEquals(StageInput2.get(0).toString(), "i like ice cream and man go");
        assertEquals(StageInput2.get(1).toString(), "i like ice cream and man go");
        assertEquals(StageInput2.get(2).toString(), "i like ice cream and mango");
    }
}
