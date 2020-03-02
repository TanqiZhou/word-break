package com.github.tanqizhou.share.wb;

import com.github.tanqizhou.share.wb.dictionary.DefaultDictionaryOperate;
import com.github.tanqizhou.share.wb.dictionary.WordDictionary;
import com.github.tanqizhou.share.wb.dictionary.WordDictionaryFactory;
import com.github.tanqizhou.share.wb.dictionary.WordDictionaryTools;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @auther: TanqiZhou
 * @Date: 2020/02/29/18:12
 * @Description: word segmentation test
 */
public class WordSegmenterTest {
    //Dictionary
    WordDictionary wordDictionary;
    //dictionary operation class
    DefaultDictionaryOperate defaultDictionaryOperate;
    //tokenizer
    WordSegmenter wordSegmenter;

    String input = "ilikesamsungmobile";

    String input2 = "ilikeicecreamandmango";

    //default dictionary
    String from = "default";
    String defaulTwordString = "{ i, like, sam, sung, samsung, mobile, ice, cream, man go}";

    //user dictionary
    String user = "user";
    String userWordString = "{ i, like, sam, sung, mobile, icecream, man go, mango}";

    //second dictionary
    String two = "two";
    String TwoWordString = "{ i, like, sam, sung, mobile, icecream, man go, mango}";

    /**
     * Stage1 default dictionary test
     */
    @Test
    public void seg() {
        wordDictionary = WordDictionaryFactory.getWordDictionaryByString(from, defaulTwordString);
        defaultDictionaryOperate = new DefaultDictionaryOperate(wordDictionary);
        defaultDictionaryOperate.printWordDict(from);
        wordSegmenter = new WordSegmenter(wordDictionary);

        List<StringBuilder> outStringBuilderList = wordSegmenter.seg(input);
        String out = outStringBuilderList.get(0).toString();
        int outSize = outStringBuilderList.size();
        System.out.println(out);

        assertEquals(out, "i like sam sung mobile");
        assertEquals(outSize, 1);

        List<StringBuilder> outStringBuilderList2 = wordSegmenter.seg(input2);
        String out2 = outStringBuilderList2.get(0).toString();
        int outSize2 = outStringBuilderList2.size();
        System.out.println(out2);

        assertEquals(out2, "i like ice cream and man go");
        assertEquals(outSize2, 1);

    }

    /**
     * Stage2  user dictionary test
     */
    @Test
    public void segByUser() {
        wordDictionary = WordDictionaryFactory.getWordDictionaryByString(user,userWordString);
        defaultDictionaryOperate = new DefaultDictionaryOperate(wordDictionary);
        defaultDictionaryOperate.printWordDict(from);
        wordSegmenter = new WordSegmenter(wordDictionary);

        List<StringBuilder> outStringBuilderList = wordSegmenter.segByUser(input);
        String out = outStringBuilderList.get(0).toString();
        int outSize = outStringBuilderList.size();
        System.out.println(out);

        assertEquals(out, "i like sam sung mobile");
        assertEquals(outSize, 1);

        assertEquals(wordSegmenter.segByUser(input2).get(0).toString(), "i like icecream and man go");
        assertEquals(wordSegmenter.segByUser(input2).get(1).toString(), "i like icecream and mango");
    }

    /**
     * Stage3 merge dictionary test
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
