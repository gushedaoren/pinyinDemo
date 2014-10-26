package airballoon.db;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

public class PinyinUtil {
	
	 
	    
	    /**
	    * 汉字转拼音的方法
	    * @param name 汉字
	    * @return 拼音
	    */
	  public String HanyuToPinyin(String name){
		     name=name.replace("·", "").replace("（", "").replace("）", "");
	    	 String pinyinName = "";
	        char[] nameChar = name.toCharArray();
	        HanyuPinyinOutputFormat defaultFormat = 
	                                           new HanyuPinyinOutputFormat();
	        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	       
	            if (nameChar[0] > 128) {
	                try {
	                	
	                    pinyinName = PinyinHelper.toHanyuPinyinStringArray
	                                           (nameChar[0], defaultFormat)[0];
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    return name;
	                }
	            } 
	        
	        return pinyinName.toUpperCase();
	    }
	 

}
