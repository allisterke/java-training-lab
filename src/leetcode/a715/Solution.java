package leetcode.a715;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class RangeModule {

    TreeMap<Integer, Integer> map = new TreeMap<>();

    public RangeModule() {

    }

    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> floor = map.floorEntry(left);
        if(floor != null && floor.getValue() >= left) {
            left = floor.getKey();
        }
        for (Iterator<Map.Entry<Integer, Integer>> it = map.subMap(left, Integer.MAX_VALUE).entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, Integer> entry = it.next();
            if(entry.getKey() > right) {
                break;
            }
            int value = entry.getValue();
            it.remove();
//            if(entry.getValue() > right) {
            if(value > right) {
                right = entry.getValue();
            }
        }
        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> floor = map.floorEntry(left);
        return floor != null && floor.getValue() >= right;
    }

    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> floor = map.floorEntry(left);
        if(floor != null && floor.getKey() != left && floor.getValue() > left) {
            int tmp = floor.getValue();
            map.put(floor.getKey(), left);
            map.put(left, tmp);
        }
        for (Iterator<Map.Entry<Integer, Integer>> it = map.subMap(left, Integer.MAX_VALUE).entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, Integer> entry = it.next();
            if(entry.getKey() >= right) {
                break;
            }
            int value = entry.getValue(); // value of entry will change after it.remove;
            it.remove();
//            if(entry.getValue() > right) {
            if(value > right) {
                map.put(right, value);
                break;
            }
        }
    }
}

public class Solution {

    static void test1() {
        RangeModule rm = new RangeModule();
        rm.addRange(10, 20);
        rm.removeRange(14, 16);
        System.out.println(rm.queryRange(10, 14));
        System.out.println(rm.queryRange(13, 15));
        System.out.println(rm.queryRange(16, 17));
    }

    static void test2() {
        RangeModule rm = new RangeModule();
        rm.addRange(5, 8);
        System.out.println(rm.queryRange(3, 4));
        rm.removeRange(5, 6);
        rm.removeRange(3, 6);
        rm.addRange(1, 3);
        System.out.println(rm.queryRange(2, 3));
        rm.addRange(4, 8);
        System.out.println(rm.queryRange(2, 3));
        rm.removeRange(4, 9);
    }

    static void test3() {
        RangeModule rm = new RangeModule();
        rm.addRange(6, 8);
        rm.removeRange(7, 8);
        rm.removeRange(8, 9);
        rm.addRange(8, 9);
        rm.removeRange(1, 3);
        rm.addRange(1, 8);
        boolean flag = false;
        flag = rm.queryRange(2, 4);
        flag = rm.queryRange(2, 9);
        flag = rm.queryRange(2, 6);
    }

    static void test4() {
        String[] ops = {"RangeModule","addRange","removeRange","queryRange","queryRange","queryRange","removeRange","removeRange","removeRange","addRange","addRange","addRange","removeRange","addRange","queryRange","addRange","addRange","queryRange","queryRange","addRange","removeRange","removeRange","removeRange","queryRange","queryRange","addRange","addRange","queryRange","addRange","addRange","removeRange","addRange","addRange","queryRange","removeRange","queryRange","removeRange","addRange","addRange","queryRange","removeRange","removeRange","addRange","queryRange","queryRange","removeRange","removeRange","removeRange","queryRange","addRange","removeRange","removeRange","queryRange","removeRange","removeRange","queryRange","addRange","addRange","removeRange","queryRange","queryRange","addRange","removeRange","removeRange","addRange","addRange","addRange","addRange","queryRange","removeRange","addRange","addRange","addRange","queryRange","addRange","removeRange","queryRange","removeRange","removeRange","removeRange","queryRange","queryRange","queryRange","queryRange","queryRange","removeRange","queryRange","removeRange","queryRange","addRange","queryRange"};
        int[][] params = {{},{14,100},{1,8},{77,80},{8,43},{4,13},{3,9},{45,49},{41,90},{58,79},{4,83},{34,39},{84,100},{8,9},{32,56},{35,46},{9,100},{85,99},{23,33},{10,31},{15,45},{52,70},{26,42},{30,70},{60,69},{10,94},{2,89},{26,39},{46,93},{30,83},{42,48},{47,74},{39,45},{14,64},{3,97},{16,34},{28,100},{19,37},{27,91},{55,62},{64,65},{2,48},{55,78},{21,89},{31,76},{13,32},{2,84},{21,88},{12,31},{89,97},{56,72},{16,75},{18,90},{46,60},{20,62},{28,77},{5,78},{58,61},{38,70},{24,73},{72,96},{5,24},{43,49},{2,20},{4,69},{18,98},{26,42},{14,18},{46,58},{16,90},{32,47},{19,36},{26,78},{7,58},{42,54},{42,83},{3,83},{54,82},{71,91},{22,37},{38,94},{20,44},{37,89},{15,54},{1,64},{63,65},{55,58},{23,44},{25,87},{38,85},{27,71}};
        Object[] results = {null,null,null,true,false,false,null,null,null,null,null,null,null,null,true,null,null,true,true,null,null,null,null,false,false,null,null,true,null,null,null,null,null,false,null,false,null,null,null,true,null,null,null,false,false,null,null,null,false,null,null,null,false,null,null,false,null,null,null,false,false,null,null,null,null,null,null,null,true,null,null,null,null,false,null,null,false,null,null,null,false,false,false,false,false,null,false,null,false,null,false};
//        Object[] results = {null,null,null,true,false,false,null,null,null,null,null,null,null,null,true,null,null,true,true,null,null,null,null,false,false,null,null,true,null,null,null,null,null,false,null,false,null,null,null,true,null,null,null,false,false,null,null,null,false,null,null,null,false,null,null,false,null,null,null,false,true,null,null,null,null,null,null,null,true,null,null,null,null,false,null,null,false,null,null,null,false,false,false,false,false,null,false,null,false,null,false};
        RangeModule rm = new RangeModule();
        boolean[] flag = new boolean[101];
        for(int i = 1; i < ops.length; ++ i) {
            if(ops[i].equals("addRange")) {
                rm.addRange(params[i][0], params[i][1]);
                for(int j = params[i][0]; j < params[i][1]; ++ j) {
                    flag[j] = true;
                }
            }
            else if(ops[i].equals("removeRange")) {
                rm.removeRange(params[i][0], params[i][1]);
                for(int j = params[i][0]; j < params[i][1]; ++ j) {
                    flag[j] = false;
                }
            }
            else {
                if(!rm.queryRange(params[i][0], params[i][1]) == (boolean)results[i]) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        test4();
    }
}