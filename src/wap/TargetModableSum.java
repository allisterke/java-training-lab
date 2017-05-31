package wap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by ubuntu on 5/20/17.
 */
public class TargetModableSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < nums.length; ++ i) {
            nums[i] = scanner.nextInt() % m;
        }
        System.out.println(modable(nums, m) ? "Yes" : "No");
    }

    static boolean modable(int[] nums, int target) {
        boolean[] mark1 = new boolean[target];
        boolean[] mark2 = new boolean[target];
        for(int n : nums) {
            mark2[n % target] = true;
            for(int i = 0; i < target; ++ i) {
                if(mark1[i]) {
                    mark2[(i + n) % target] = true;
                }
            }
            if(mark2[0]) {
                return true;
            }
            for(int i = 0; i < target; ++ i) {
                if(mark2[i]) {
                    mark1[i] = true;
                    mark2[i] = false;
                }
            }
        }
        return false;
    }
}
