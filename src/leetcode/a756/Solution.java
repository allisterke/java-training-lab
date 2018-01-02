package leetcode.a756;

import java.util.*;

public class Solution {

    private Map<Integer, Set<Character>> transitionMap;

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        transitionMap = getTransitionMap(allowed);
        return dfs(bottom, new StringBuilder(), 0);
    }

    boolean dfs(String bottom, StringBuilder up, int index) {
        if(bottom.length() == 1) {
            return true;
        }
        if (index == bottom.length() - 1) {
            return dfs(up.toString(), new StringBuilder(), 0);
        }
        int key = getKey(bottom.charAt(index), bottom.charAt(index + 1));
        if(!transitionMap.containsKey(key)) {
            return false;
        }
        for (char c : transitionMap.get(key)) {
            up.append(c);
            if(dfs(bottom, up, index+1)) {
                return true;
            }
            up.setLength(up.length() - 1);
        }
        return false;
    }

    public boolean pyramidTransition0(String bottom, List<String> allowed) {
        Map<Integer, Set<Character>> map = getTransitionMap(allowed);
        List<Collection<Character>> current = new ArrayList<>();
        for (int i = 0; i < bottom.length(); ++ i) {
            current.add(Arrays.asList(bottom.charAt(i)));
        }
        for(int i = 0; i < bottom.length() - 1; ++ i) {
            List<Collection<Character>> next = new ArrayList<>();
            for (int j = 0; j < current.size() - 1; ++ j) {
                Set<Character> up = new HashSet<>();
                for (char c1 : current.get(j)) {
                    for (char c2 : current.get(j+1)) {
                        int key = getKey(c1, c2);
                        up.addAll(map.getOrDefault(key, Collections.emptySet()));
                    }
                }
                next.add(up);
                if(up.isEmpty()) {
                    return false;
                }
            }
            current = next;
        }
        return !current.isEmpty();
    }

    private Map<Integer, Set<Character>> getTransitionMap(List<String> allowed) {
        Map<Integer, Set<Character>> map = new HashMap<>();
        for(String s : allowed) {
            int key = getKey(s.charAt(0), s.charAt(1));
            if(!map.containsKey(key)) {
                map.put(key, new HashSet<>());
            }
            map.get(key).add(s.charAt(2));
        }
        return map;
    }

    int getKey(char a, char b) {
        int key = (a - 'A') * 26 + (b - 'A');
        return key;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "XYZ",
                "XXYX"
        );
        List<List<String>> allowed = Arrays.asList(
                Arrays.asList("XYD", "YZE", "DEA", "FFF"),
                Arrays.asList("XXX", "XXY", "XYX", "XYY", "YXZ")
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.pyramidTransition(tests.get(i), allowed.get(i)));
        }
    }
}