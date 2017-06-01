package leetcode.a238;

import java.util.Arrays;
import java.util.List;

public class Solution {
    int[] buildProductPrefix(int[] nums) {
        int[] productPrefix = new int[nums.length + 1];
        productPrefix[0] = 1;
        for(int i = 0; i < nums.length; ++ i) {
            productPrefix[i+1] = productPrefix[i] * nums[i];
        }
        return productPrefix;
    }

    int[] buildProductSuffix(int[] nums) {
        int[] productSuffix = new int[nums.length + 1];
        productSuffix[nums.length] = 1;
        for(int i = nums.length - 1; i >= 0; -- i) {
            productSuffix[i] = productSuffix[i+1] * nums[i];
        }
        return productSuffix;
    }

    int[] buildProductExceptSelf(int[] productPrefix, int[] productSuffix) {
        int[] product = new int[productPrefix.length];
        for(int i = 0; i < product.length; ++ i) {
            product[i] = productPrefix[i] * productSuffix[i+1];
        }
        return product;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] productPrefix = buildProductPrefix(nums);
        int[] productSuffix = buildProductSuffix(nums);
        return buildProductExceptSelf(productPrefix, productSuffix);
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