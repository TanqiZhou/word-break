package com.github.tanqizhou.share.wb;

import com.github.tanqizhou.share.wb.dictionary.WordDictionary;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.SetMultimap;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/15:46
 * @Description: tokenizer
 */
@Slf4j
public class WordSegmenter {
    //字典
    private WordDictionary wordDictionary;

    public WordSegmenter(WordDictionary wordDictionary) {
        this.wordDictionary = wordDictionary;
    }

    /**
     * default segmentation
     *
     * @param wordString sentence to divide
     * @return all dictionary results
     */
    public List<StringBuilder> seg(String wordString) {
        return getDictionarybyConst(ConstDictionary.DefaultDictionary, wordString);
    }

    /**
     * user dictionary word segmentation
     *
     * @param wordString sentence to divide
     * @return all dictionary results
     */
    public List<StringBuilder> segByUser(String wordString) {
        return getDictionarybyConst(ConstDictionary.UserDictionary, wordString);
    }

    /**
     * merge dictionary word segmentation
     *
     * @param wordString sentence to divide
     * @return all dictionary results
     */
    public List<StringBuilder> segByAll(String wordString) {
        return getDictionarybyConst(ConstDictionary.AllDictionary, wordString);
    }

    private List<StringBuilder> getDictionarybyConst(ConstDictionary constDictionary, String wordString) {
        return getDictionarybyConst(constDictionary, wordString, false);
    }

    /**
     * @param constDictionary category dictionary range
     * @param wordString      words to divide
     * @param preSegmenta     whether to pre segment
     * @return all dictionary results
     */
    private List<StringBuilder> getDictionarybyConst(ConstDictionary constDictionary, String wordString, boolean preSegmenta) {
        List<StringBuilder> allWordResultList = new ArrayList<>();
        List<StringBuilder> commonSag = null;
        int startSub = 0;
        int wordLenght = 1;
        while (startSub < wordString.length()) {
            String word;
            try {
                //intercept characters
                word = wordString.substring(startSub, startSub + wordLenght);
                log.trace(word);
            } catch (StringIndexOutOfBoundsException e) {
                if (preSegmenta) {
                    log.trace("pre segmentation failed");
                    return null;
                } else {
                    log.trace(allWordResultList.toString());
                    throw new RuntimeException("word segmentation failure");
                }
            }
            for (int j = 0; j < constDictionary.getFrom().size(); j++) {
                commonSag = commmonSeg(constDictionary.getFrom().get(j), word);
                if (commonSag != null) {
                    //find word
                    if (!preSegmenta) {
                        //need pre segmentation
                        int preStartSub = startSub + wordLenght;
                        String preWordString = wordString.substring(preStartSub);
                        List<StringBuilder> dictionarybyConst = getDictionarybyConst(constDictionary, preWordString, true);
                        if (dictionarybyConst == null) {
                            //pre segmentation failed
                            wordLenght++;
                            continue;
                        }
                    } else {
                        //pre segmentation success
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
            //no words found
            if (commonSag == null) {
                wordLenght++;
            }
        }
        return allWordResultList;
    }

    /**
     * find word segmentation from a dictionary
     *
     * @param from character dictionary
     * @param word individual word
     * @return all dictionaries
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
     * merge dictionary results
     *
     * @param wordResultList this result
     * @param commmonSeg     new words
     * @return merged dictionary results
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
