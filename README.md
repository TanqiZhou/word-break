### Java WordBreak

#### 1、快速体验
	
        String from = "default";
        String defaulTwordString = "{ i, like, sam, sung, samsung, mobile, ice, cream, man go}";
	    构建字典
        wordDictionary = WordDictionaryFactory.getWordDictionaryByString(from, defaulTwordString);
        
#### 2、对文本进行分词

	defaultDictionaryOperate = new DefaultDictionaryOperate(wordDictionary);
    defaultDictionaryOperate.printWordDict(from);
    wordSegmenter = new WordSegmenter(wordDictionary);
    默认字典
    List<StringBuilder> StageInput = wordSegmenter.seg(input);
    用户字典
    List<StringBuilder> StageInput1 = wordSegmenter.segByUser(input1);
    合并字典
    List<StringBuilder> StageInput2 = wordSegmenter.segByAll(input2);
    
    System.out.println(StageInput);
    System.out.println(StageInput1);
    System.out.println(StageInput2);
    

