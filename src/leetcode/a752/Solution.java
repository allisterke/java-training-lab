package leetcode.a752;

import java.util.*;

public class Solution {
    public int openLock(String[] deadends, String target) {
        Queue<List<Integer>> codes = new ArrayDeque<>();
        Queue<Integer> distances = new ArrayDeque<>();
        Set<List<Integer>> visited = new HashSet<>();
        codes.add(Arrays.asList(0, 0, 0, 0));
        distances.add(0);
        visited.add(Arrays.asList(0, 0, 0, 0));
        Set<List<Integer>> dead = new HashSet<>();
        Map<List<Integer>, List<Integer>> previous = new HashMap<>();
        for (String s : deadends) {
            dead.add(code2list(s));
        }
        if(dead.contains(Arrays.asList(0, 0, 0, 0))) {
            return -1;
        }
        List<Integer> t = code2list(target);
        while (!codes.isEmpty()) {
            List<Integer> frontCode = codes.poll();
            int d = distances.poll();
            if(frontCode.equals(t)) {
//                System.out.println(frontCode);
//                while (previous.containsKey(frontCode)) {
//                    System.out.println(previous.get(frontCode));
//                    frontCode = previous.get(frontCode);
//                }
                return d;
            }
            List<Integer> next = new ArrayList<>(frontCode);
            for (int i = 0; i < 4; ++ i) {
                next.set(i, (frontCode.get(i) + 1) % 10);
                if(visited.contains(next) || dead.contains(next)) {
//                    continue;
                }
                else {
                    visited.add(new ArrayList<>(next));
                    codes.add(new ArrayList<>(next));
                    distances.add(d+1);
//                    previous.put(new ArrayList<>(next), frontCode);
                }
                next.set(i, (frontCode.get(i) + 9) % 10);
                if(visited.contains(next) || dead.contains(next)) {
//                    continue;
                }
                else {
                    visited.add(new ArrayList<>(next));
                    codes.add(new ArrayList<>(next));
                    distances.add(d+1);
//                    previous.put(new ArrayList<>(next), frontCode);
                }
                next.set(i, frontCode.get(i));
            }
        }
        return -1;
    }

    List<Integer> code2list(String code) {
        return Arrays.asList(
                code.charAt(0) - '0',
                code.charAt(1) - '0',
                code.charAt(2) - '0',
                code.charAt(3) - '0');
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[][] {
                        {"0201","0101","0102","1212","2002"},
                        {"8887","8889","8878","8898","8788","8988","7888","9888"}
                }
        );
        List<String> targets = Arrays.asList(
                "0202",
                "8888"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.openLock(tests.get(i), targets.get(i)));
        }
    }
}