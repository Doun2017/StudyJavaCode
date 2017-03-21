package com.doun.chapter9interface;

/**
 * Created by Doun on 2017/3/21.
 */

public class LettersExchanger {

    public String name() {
        return getClass().getSimpleName();
    }

    public String doLetterExchange(String str){
        char oldCharArray[] = str.toCharArray();
        char charArray[] = new char[str.length()];
        for (int i=0; i<str.length(); i++) {
            if (i%2 == 0)//i指示当前字母为待交换的第一个字母
                charArray[i] = str.charAt(i);
            else {
                char oldFirstLetter = charArray[i-1];
                charArray[i-1] = str.charAt(i);
                charArray[i] = oldFirstLetter;
            }
        }
        return new String(charArray);
    }
}


class LettersExchangerAdapter implements Processor {
    LettersExchanger lettersExchanger;
    public LettersExchangerAdapter(LettersExchanger lettersExchanger) {
        this.lettersExchanger = lettersExchanger;
    }
    public String name() { return lettersExchanger.name(); }
    public String process(Object input) {
        return lettersExchanger.doLetterExchange((String)input);
    }
}