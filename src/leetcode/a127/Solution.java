package leetcode.a127;

import java.util.*;

public class Solution {
    static class ArrayWrapper implements Comparable<ArrayWrapper> {
        char[] a;

        @Override
        public int hashCode() {
            return Arrays.hashCode(a);
        }

        @Override
        public int compareTo(ArrayWrapper o) {
            for(int i = 0; i < a.length; ++ i) {
                if(a[i] != o.a[i]) {
                    return a[i] < o.a[i] ? -1 : 1;
                }
            }
            return 0;
        }

        public ArrayWrapper(String s) {
            a = s.toCharArray();
        }

        public ArrayWrapper(ArrayWrapper aw) {
            a = aw.a.clone();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof ArrayWrapper && equals((ArrayWrapper)obj);
        }

        public boolean equals(ArrayWrapper obj) {
            return Arrays.equals(a, obj.a);
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        ArrayWrapper begin = new ArrayWrapper(beginWord);
        ArrayWrapper end = new ArrayWrapper(endWord);
        Set<ArrayWrapper> set = new HashSet<>();
        for(String word : wordList) {
            set.add(new ArrayWrapper(word));
        }
        return ladderLength(begin, end, set);
    }

    int ladderLength(ArrayWrapper begin, ArrayWrapper end, Set<ArrayWrapper> set) {
        if(!set.contains(end)) {
            return 0;
        }
        set.remove(begin);
        Set<ArrayWrapper> currentLevel = new HashSet<>(Arrays.asList(begin));
        for(int d = 2; !currentLevel.isEmpty(); ) {
            Set<ArrayWrapper> nextLevel = new HashSet<>();
            for (ArrayWrapper aw : currentLevel) {
                ArrayWrapper w = new ArrayWrapper(aw);
                for (int i = 0; i < w.a.length; ++i) {
                    char c = w.a[i];
                    for (char j = 'a'; j <= 'z'; ++j) {
                        if (j != c) {
                            w.a[i] = j;
                            if (w.equals(end)) {
                                return d;
                            }
                            if (set.contains(w)) {
                                nextLevel.add(new ArrayWrapper(w));
                                set.remove(w);
                            }
                        }
                    }
                    w.a[i] = c;
                }
            }
            currentLevel = nextLevel;
        }
        return 0;
    }

    public int ladderLength0(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) {
            return 0;
        }
        set.remove(beginWord);
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(beginWord));
        Queue<Integer> dist = new ArrayDeque<>(Arrays.asList(1));
        while (!queue.isEmpty()) {
            String w = queue.poll();
            int d = dist.poll() + 1;
            char a[] = w.toCharArray();
            for(int i = 0; i < a.length; ++ i) {
                char c = a[i];
                for(char j = 'a'; j <= 'z'; ++ j) {
                    if(j != c) {
                        a[i] = j;
                        String next = new String(a);
                        if(next.equals(endWord)) {
                            return d;
                        }
                        if(set.contains(next)) {
                            queue.add(next);
                            dist.add(d);
                            set.remove(next);
                        }
                    }
                }
                a[i] = c;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> begins = Arrays.asList(
                "hit"
        );
        List<String> ends = Arrays.asList(
                "hot"
        );
        List<List<String>> wordLists = Arrays.asList(
                Arrays.asList("hot","dot","dog","lot","log","cog")
        );
        List<Integer> results = Arrays.asList(
                2
        );

        Solution s = new Solution();
        for(int i = 0; i < begins.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.ladderLength(begins.get(i), ends.get(i), wordLists.get(i)));
        }
    }
}