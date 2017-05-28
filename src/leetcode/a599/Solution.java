package leetcode.a599;

import java.util.*;

public class Solution {
    Map<String, Integer> stringArrayToMap(String[] list) {
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < list.length; ++ i) {
            map.put(list[i], i);
        }
        return map;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map2 = stringArrayToMap(list2);
        List<String> bestChoices = new ArrayList<>();
        int sum = Integer.MAX_VALUE;
        for(int i = 0; i < list1.length; ++ i) {
            Integer index2 = map2.get(list1[i]);
            if(index2 != null) {
                int s = index2 + i;
                if(s < sum) {
                    sum = s;
                    bestChoices.clear();
                    bestChoices.add(list1[i]);
                }
                else if(s == sum) {
                    bestChoices.add(list1[i]);
                }
            }
        }
        return bestChoices.toArray(new String[bestChoices.size()]);
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