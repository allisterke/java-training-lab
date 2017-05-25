package leetcode.a093;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        int[] nums = toIntArray(s);
        List<String> possibleIpAddresses = new ArrayList<>();
        int[] ipAddress = new int[4];
        backtrack(nums, 0, ipAddress, 0, possibleIpAddresses);
        return possibleIpAddresses;
    }

    String buildIpAddress(int[] ipAddress) {
        return String.format("%d.%d.%d.%d", ipAddress[0], ipAddress[1], ipAddress[2], ipAddress[3]);
    }

    void backtrack(int[] nums, int offset, int[] ipAddress, int nextSegment, List<String> possibleIpAddresses) {
        if(nums.length - offset + nextSegment*3 > 12 || nums.length - offset + nextSegment < 4) {
            return;
        }
        if(nextSegment >= 4) {
            if(offset >= nums.length) {
                possibleIpAddresses.add(buildIpAddress(ipAddress));
            }
            return;
        }
        for(int i = offset; i < offset + 3 && i < nums.length; ++ i) {
            ipAddress[nextSegment] = ipAddress[nextSegment] * 10 + nums[i];
            if(ipAddress[nextSegment] < 256) {
                backtrack(nums, i + 1, ipAddress, nextSegment + 1, possibleIpAddresses);
            }
            if(ipAddress[nextSegment] == 0) {
                break;
            }
        }
        ipAddress[nextSegment] = 0;
    }

    private int[] toIntArray(String s) {
        int[] nums = new int[s.length()];
        for(int i = 0; i < s.length(); ++ i) {
            nums[i] = s.charAt(i) - '0';
        }
        return nums;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "25525511135",
                "0010101"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.restoreIpAddresses(tests.get(i)));
        }
    }
}