package com.github.tanqizhou.share.wb;

import com.github.tanqizhou.share.wb.dictionary.WordDictionary;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.SetMultimap;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/15:46
 * @Description: 分词器
 */
@Slf4j
public class WordSegmenter {
    //字典
    private WordDictionary wordDictionary;

    public WordSegmenter(WordDictionary wordDictionary) {
        this.wordDictionary = wordDictionary;
    }

    /**
     * 默认分词
     *
     * @param wordString 要分的句子
     * @return 所有字典结果
     */
    public List<StringBuilder> seg(String wordString) {
        return getDictionarybyConst(ConstDictionary.DefaultDictionary, wordString);
    }

    /**
     * 用户字典分词
     *
     * @param wordString 要分的句子
     * @return 所有字典结果
     */
    public List<StringBuilder> segByUser(String wordString) {
        return getDictionarybyConst(ConstDictionary.UserDictionary, wordString);
    }

    /**
     * 合并字典分词
     *
     * @param wordString 要分的句子
     * @return 所有字典结果
     */
    public List<StringBuilder> segByAll(String wordString) {
        return getDictionarybyConst(ConstDictionary.AllDictionary, wordString);
    }

    private List<StringBuilder> getDictionarybyConst(ConstDictionary constDictionary, String wordString) {
        return getDictionarybyConst(constDictionary, wordString, false);
    }

    /**
     * @param constDictionary 分类字典范围
     * @param wordString      要分的词
     * @param preSegmenta     是否预分词
     * @return 所有字典结果
     */
    private List<StringBuilder> getDictionarybyConst(ConstDictionary constDictionary, String wordString, boolean preSegmenta) {
        List<StringBuilder> allWordResultList = new ArrayList<>();
        List<StringBuilder> commonSag = null;
        int startSub = 0;
        int wordLenght = 1;
        while (startSub < wordString.length()) {
            String word;
            try {
                //截取字符
                word = wordString.substring(startSub, startSub + wordLenght);
                log.trace(word);
            } catch (StringIndexOutOfBoundsException e) {
                if (preSegmenta) {
                    log.trace("预分词失败");
                    return null;
                } else {
                    log.trace(allWordResultList.toString());
                    throw new RuntimeException("分词失败");
                }
            }
            for (int j = 0; j < constDictionary.getFrom().size(); j++) {
                commonSag = commmonSeg(constDictionary.getFrom().get(j), word);
                if (commonSag != null) {
                    //发现单词
                    if (!preSegmenta) {
                        //需要预分词
                        int preStartSub = startSub + wordLenght;
                        String preWordString = wordString.substring(preStartSub);
                        List<StringBuilder> dictionarybyConst = getDictionarybyConst(constDictionary, preWordString, true);
                        if (dictionarybyConst == null) {
                            //预分词失败
                            wordLenght++;
                            continue;
                        }
                    } else {
                        //预分词成功
                        log.debug(commonSag.toString());
                        return commonSag;
                    }
                    if (allWordResultList.size() == 0) {
                        allWordResultList.addAll(commonSag);
                    } else {
                        allWordResultList = getAllwordResultList(allWordResultList, commonSag);
                    }
                    startSub = startSub + wordLenght;
                    wordLenght = 1;
                    break;
                }
            }
            //没有发现单词
            if (commonSag == null) {
                wordLenght++;
            }
        }
        return allWordResultList;
    }

    /**
     * 从字典查找分词
     *
     * @param from 字典
     * @param word 单词
     * @return 所有字典
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private List<StringBuilder> commmonSeg(String from, String word) {
        SetMultimap<String, String> wordDict = (SetMultimap) wordDictionary.getWordDict();
        Set<String> strings = wordDict.get(from);
        if (strings.contains(word)) {
            ListMultimap<String, String> wordMoreWord = (ListMultimap) wordDictionary.getWordMoreWord();
            if (wordMoreWord.containsKey(word)) {
                ArrayList<StringBuilder> stringArrayList = new ArrayList<>();
                for (String s : wordMoreWord.get(word)) {
                    StringBuilder stringBuilder = new StringBuilder(s);
                    stringArrayList.add(stringBuilder);
                }
                return stringArrayList;
            }
            return Collections.singletonList(new StringBuilder(word));
        } else {
            return null;
        }
    }

    /**
     * 合并字典结果
     *
     * @param wordResultList 本次结果
     * @param commmonSeg     新单词
     * @return 合并后的字典结果
     */
    public List<StringBuilder> getAllwordResultList(List<StringBuilder> wordResultList, List<StringBuilder> commmonSeg) {
        ArrayList<StringBuilder> allWordResultList = new ArrayList<>();
        for (StringBuilder start : wordResultList) {
            for (StringBuilder tmp : commmonSeg) {
                StringBuilder startNew = new StringBuilder(start);
                startNew.append(" ").append(tmp);
                allWordResultList.add(startNew);
            }
        }
        return allWordResultList;
    }
}
