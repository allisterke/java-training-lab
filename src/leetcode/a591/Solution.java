package leetcode.a591;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean isValid(String code) {
        return verify(code, 0) == code.length();
    }

    int verify(String code, int start) {
        if(start < code.length()) {
            if(code.charAt(start) == '<') {
                ++ start;
                if(start < code.length()) {
                    String tagName = extractTageName(code, start);
                    if(!tagName.equals("")) {
                        start += tagName.length() + 1;
                        while(start < code.length()) {
                            if(code.charAt(start) != '<') {
                                ++ start;
                            }
                            else {
                                if(start + 1 < code.length()) {
                                    if(code.charAt(start + 1) == '!') {
                                        start = skipCdata(code, start);
                                        if(start < 0) {
                                            break;
                                        }
                                    }
                                    else if(code.charAt(start + 1) == '/'){
                                        start += 2;
                                        String closeTag = extractTageName(code, start);
                                        if(closeTag.equals(tagName)) {
                                            return start + closeTag.length() + 1;
                                        }
                                        else {
                                            break;
                                        }
                                    }
                                    else {
                                        start = verify(code, start);
                                        if(start < 0) {
                                            break;
                                        }
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    int skipCdata(String code, int start) {
        String begin = "<![CDATA[";
        String end = "]]>";
        int i = 0;
        for(; i < begin.length() && start + i < code.length() && begin.charAt(i) == code.charAt(start+i); ++ i)
            ;
        if(i == begin.length()) {
            start += begin.length();
            int pos = code.indexOf(end, start);
            if(pos >= start) {
                return pos + end.length();
            }
        }
        return -1;
    }

    String extractTageName(String code, int start) {
        int i = start;
        for(; i < code.length() && i - start < 9 && Character.isUpperCase(code.charAt(i)); ++ i)
            ;
        if(i > start && i < code.length() && code.charAt(i) == '>') {
            return code.substring(start, i);
        }
        else {
            return "";
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "<TRUE><![CDATA[wahaha]]]><![CDATA[]> wahaha]]></TRUE>",
                "<DIV>This is the first line <![CDATA[<div>]]></DIV>",
                "<DDDDDDDDDD>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DDDDDDDDDD>",
                "<A>  <B> </A>   </B>"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.isValid(tests.get(i)));
        }
    }
}