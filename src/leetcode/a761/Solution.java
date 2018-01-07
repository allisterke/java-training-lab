package leetcode.a761;

import java.util.*;

public class Solution {
    public String makeLargestSpecial(String S) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        visited.add(S);
        queue.add(S);
        String largest = S;
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            String front = queue.poll();
            for (int i = 0; i < front.length(); ++ i) {
                int diff1 = 0;
                for (int j = i; j < front.length(); ++ j) {
                    if(front.charAt(j) == '0') {
                        -- diff1;
                    }
                    else {
                        ++ diff1;
                    }
                    if(diff1 < 0) {
                        break;
                    }
                    else if(diff1 == 0) {
                        builder.setLength(0);
                        for (int t = 0; t < i; ++ t) {
                            builder.append(front.charAt(t));
                        }
                        int diff2 = 0;
                        for (int k = j+1; k < front.length(); ++ k) {
                            if(front.charAt(k) == '0') {
                                -- diff2;
                            }
                            else {
                                ++ diff2;
                            }
                            if(diff2 < 0) {
                                break;
                            }
                            else if(diff2 == 0) {
//                                String candidate = front.substring(0, i) + front.substring(j + 1, k + 1) + front.substring(i, j + 1) + front.substring(k + 1);
                                builder.setLength(i);
                                for (int t = j+1; t <= k; ++ t) {
                                    builder.append(front.charAt(t));
                                }
                                for (int t = i; t <= j; ++ t) {
                                    builder.append(front.charAt(t));
                                }
                                for (int t = k+1; t < front.length(); ++ t) {
                                    builder.append(front.charAt(t));
                                }
                                String candidate = builder.toString();
                                if(candidate.compareTo(largest) <= 0) {
                                    continue;
                                }
                                if (visited.contains(candidate)) {
                                    continue;
                                } else {
                                    visited.add(candidate);
                                    queue.add(candidate);
                                    if (candidate.compareTo(largest) > 0) {
                                        largest = candidate;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return largest;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "11011000",
                "101010101010101010101010101010101010101010101010",
                "1110110010111000001011101001100100110010"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.makeLargestSpecial(tests.get(i)));
        }
    }
}