package com.github.tanqizhou.share.wb.dictionary;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.SetMultimap;
import lombok.extern.slf4j.Slf4j;


/**
 * @Auther: TanqiZhou
 * @Date: 2020/02/29/16:25
 * @Description: defaultDictionaryOperationClass
 */
@Slf4j
public class DefaultDictionaryOperate implements DictionaryOperate {

    private SetMultimap<String,String> wordDictRel;

    private WordDictionary wordDictionary;

    @SuppressWarnings({"unchecked","rawtypes"})
    public DefaultDictionaryOperate(WordDictionary wordDict) {
        this.wordDictionary = wordDict;
        try {
            this.wordDictRel = (SetMultimap<String, String>) wordDict.getWordDict();
        }catch ( Exception e){
            log.error("object type is set incorrectly");
            e.printStackTrace();
        }
    }

    @Override
    public boolean addWord(String from,String word){
        if (!wordDictRel.containsKey(from) && wordDictRel.get(from) == null) {
            return false;
        }else {
            return wordDictRel.get(from).add(word);
        }
    }

    @Override
    public boolean contains(String from,String word){
        if (!wordDictRel.containsKey(from) && wordDictRel.get(from) == null) {
            return false;
        }else {
        return wordDictRel.get(from).contains(word);
    }
    }

    @Override
    public boolean removeWord(String from,String word){
        if (!wordDictRel.containsKey(from) && wordDictRel.get(from) == null) {
            return false;
        }else {
            return wordDictRel.get(from).remove(word);
        }
    }

    @Override
    public void printWordDict(String from){
        String jsonString = JSON.toJSONString(wordDictRel);
        System.out.println(jsonString);
        log.info(jsonString);
    }

    @Override
    public WordDictionary getWordDict() {
        return wordDictionary;
    }
}
