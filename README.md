### Java WordBreak

#### 1、quick experience
	
        String from = "default";
        String defaulTwordString = "{ i, like, sam, sung, samsung, mobile, ice, cream, man go}";
	    build dictionary
        wordDictionary = WordDictionaryFactory.getWordDictionaryByString(from, defaulTwordString);
        
#### 2、word segmentation

	defaultDictionaryOperate = new DefaultDictionaryOperate(wordDictionary);
    defaultDictionaryOperate.printWordDict(from);
    wordSegmenter = new WordSegmenter(wordDictionary);
    default dictionary
    List<StringBuilder> StageInput = wordSegmenter.seg(input);
    user dictionary
    List<StringBuilder> StageInput1 = wordSegmenter.segByUser(input1);
    merge dictionary
    List<StringBuilder> StageInput2 = wordSegmenter.segByAll(input2);
    
    System.out.println(StageInput);
    System.out.println(StageInput1);
    System.out.println(StageInput2);
    

