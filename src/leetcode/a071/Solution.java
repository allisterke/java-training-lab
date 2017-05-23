package leetcode.a071;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {
    public String simplifyPath(String path) {
        String[] pathNodes = path.split("/");
        Deque<String> deque = new ArrayDeque<>();
        for(String node : pathNodes) {
            if(node.isEmpty() || node.equals(".")) {
                continue;
            }
            else if(node.equals("..")) {
                if(!deque.isEmpty()) {
                    deque.removeLast();
                }
            }
            else {
                deque.addLast(node);
            }
        }
        if(deque.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for(String node : deque) {
            sb.append('/');
            sb.append(node);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "/home/",
                "/a/./b/../../c/"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.simplifyPath(tests.get(i)));
        }
    }
}