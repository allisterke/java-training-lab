package leetcode.a049;

import java.util.*;

public class Solution {
    class ArrayComparator implements Comparator<int[]> {
        public int compare(int[] a, int [] b) {
            for(int i = 0; i < a.length; ++ i) {
                if(a[i] != b[i]) {
                    return Integer.compare(a[i], b[i]);
                }
            }
            return 0;
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        class CountKey implements Comparable<CountKey> {
            int[] count = new int[26];
            int hash;

            @Override
            public int hashCode() {
                return hash;
            }

            @Override
            public boolean equals(Object obj) {
                return obj instanceof CountKey && Arrays.equals(count, ((CountKey)obj).count);
            }

            @Override
            public int compareTo(CountKey o) {
                for(int i = 0; i < 26; ++ i) {
                    if(count[i] != o.count[i]) {
                        return count[i] < o.count[i] ? -1 : 1;
                    }
                }
                return 0;
            }

            public CountKey(String str) {
                for(int i = 0; i < str.length(); ++ i) {
                    ++ count[str.charAt(i) - 'a'];
                }
                hash = Arrays.hashCode(count);
            }
        }
        Map<CountKey, List<String>> groups = new HashMap<>(strs.length);
        for(String str : strs) {
            CountKey key = new CountKey(str);
//            if(!groups.containsKey(key)) {
//                groups.put(key, new ArrayList<>());
//            }
            groups.putIfAbsent(key, new ArrayList<>());
            groups.get(key).add(str);
        }
        return new ArrayList<>(groups.values());
    }

    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for(String str : strs) {
            char[] a = str.toCharArray();
            Arrays.sort(a);
            String key = new String(a);
            if(!groups.containsKey(key)) {
                groups.put(key, new ArrayList<>());
            }
            groups.get(key).add(str);
        }
        return new ArrayList<>(groups.values());
    }
    public List<List<String>> groupAnagrams0(String[] strs) {
        Map<int[], List<String>> groups = new TreeMap<>(new ArrayComparator());
//        Map<int[], List<String>> groups = new TreeMap<>((a, b) -> {
//            for(int i = 0; i < a.length; ++ i) {
//                if(a[i] != b[i]) {
//                    return Integer.compare(a[i], b[i]);
//                }
//            }
//            return 0;
//        });
        int[] count = new int[26];
        for(String str : strs) {
            for(int i = 0; i < str.length(); ++ i) {
                ++ count[str.charAt(i) - 'a'];
            }
            if(!groups.containsKey(count)) {
                groups.put(Arrays.copyOf(count, count.length), new ArrayList<>());
            }
            groups.get(count).add(str);
            Arrays.fill(count, 0);
        }
        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<int[], List<String>> entry : groups.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
//        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}