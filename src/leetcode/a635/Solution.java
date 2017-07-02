package leetcode.a635;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class LogSystem {
    TreeMap<String, Integer> log = new TreeMap<>();
    public LogSystem() {

    }

    public void put(int id, String timestamp) {
        log.put(timestamp, id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        return new ArrayList<>(
                log.subMap(getStartOfSpecifiedGranularity(s, gra), true,
                        getEndOfSpecifiedGranularity(e, gra), true).values());
    }

    String getStartOfSpecifiedGranularity(String start, String gra) {
        switch (gra) {
            case "Year":
                return start.substring(0, 4) + ":00:00:00:00:00";
            case "Month":
                return start.substring(0, 7) + ":00:00:00:00";
            case "Day":
                return start.substring(0, 10) + ":00:00:00";
            case "Hour":
                return start.substring(0, 13) + ":00:00";
            case "Minute":
                return start.substring(0, 16) + ":00";
            default:
                return start;
        }
    }

    String getEndOfSpecifiedGranularity(String start, String gra) {
        switch (gra) {
            case "Year":
                return start.substring(0, 4) + ":12:31:23:59:59";
            case "Month":
                return start.substring(0, 7) + ":31:23:59:59";
            case "Day":
                return start.substring(0, 10) + ":23:59:59";
            case "Hour":
                return start.substring(0, 13) + ":59:59";
            case "Minute":
                return start.substring(0, 16) + ":59";
            default:
                return start;
        }
    }
}

public class Solution {


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