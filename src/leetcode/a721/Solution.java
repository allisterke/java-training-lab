package leetcode.a721;

import java.util.*;

public class Solution {
    int[] root;

    public List<List<String>> accountsMerge(List<List<String>> rawAccounts) {
        initRoot(rawAccounts.size());
        List<List<String>> accounts = new ArrayList<>();
        for(int i = 0; i < rawAccounts.size(); ++ i) {
            List<String> tmp = new ArrayList<>();
            for(int j = 1; j < rawAccounts.get(i).size(); ++ j) {
                tmp.add(rawAccounts.get(i).get(j));
            }
            Collections.sort(tmp);
            tmp.add(0, rawAccounts.get(i).get(0));
            accounts.add(tmp);
        }
        for (int i = 0; i < accounts.size(); ++ i) {
            for(int j = i+1; j < accounts.size(); ++ j) {
                if(getRoot(i) == getRoot(j)) {
                    continue;
                }
                List<String> m1 = accounts.get(i);
                List<String> m2 = accounts.get(j);
                boolean found = false;
                for(int p = 1, q = 1; p < m1.size() && q < m2.size(); ) {
                    int r = m1.get(p).compareTo(m2.get(q));
                    if(r < 0) {
                        ++ p;
                    }
                    else if(r > 0) {
                        ++ q;
                    }
                    else {
                        found = true;
                        break;
                    }
                }
                if(found) {
                    merge(i, j);
                }
            }
        }
        Map<Integer, TreeSet<String>> mergedMap = new HashMap<>();
        for(int i = 0; i < accounts.size(); ++ i) {
            int r = getRoot(i);
            if(!mergedMap.containsKey(r)) {
                mergedMap.put(r, new TreeSet<>());
            }
            for (int j = 1; j < accounts.get(i).size(); ++ j) {
                mergedMap.get(r).add(accounts.get(i).get(j));
            }
        }
        List<List<String>> mergedAccounts = new ArrayList<>();
        for(Map.Entry<Integer, TreeSet<String>> entry : mergedMap.entrySet()) {
            List<String> account = new ArrayList<>();
            account.add(accounts.get(entry.getKey()).get(0));
            account.addAll(entry.getValue());
            mergedAccounts.add(account);
        }
        return mergedAccounts;
    }

    void merge(int i, int j) {
        int r1 = getRoot(i);
        int r2 = getRoot(j);
        if(r1 != r2) {
            root[r2] = r1;
        }
    }

    int getRoot(int n) {
        if(root[n] != n) {
            root[n] = getRoot(root[n]);
        }
        return root[n];
    }

    void initRoot(int size) {
        root = new int[size];
        for(int i = 0; i < root.length; ++ i) {
            root[i] = i;
        }
    }

    public static void main(String[] args) {
        List<List<List<String>>> tests = Arrays.asList(
                Arrays.asList(
                        Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                        Arrays.asList("John", "johnnybravo@mail.com"),
                        Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                        Arrays.asList("Mary", "mary@mail.com")
                ),
                Arrays.asList(
                        Arrays.asList("David","David0@m.co","David4@m.co","David3@m.co"),
                        Arrays.asList("David","David5@m.co","David5@m.co","David0@m.co"),
                        Arrays.asList("David","David1@m.co","David4@m.co","David0@m.co"),
                        Arrays.asList("David","David0@m.co","David1@m.co","David3@m.co"),
                        Arrays.asList("David","David4@m.co","David1@m.co","David3@m.co")
                )
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.accountsMerge(tests.get(i)));
        }
    }
}