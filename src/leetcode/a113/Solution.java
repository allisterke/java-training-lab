package leetcode.a113;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSum(root, path, result, sum);
        return result;
    }

    void pathSum(TreeNode root, List<Integer> path, List<List<Integer>> result, int sum) {
        if(root != null) {
            path.add(root.val);
            sum -= root.val;
            if(sum == 0 && root.left == null && root.right == null) {
                result.add(new ArrayList<>(path));
            }
            else {
                pathSum(root.left, path, result, sum);
                pathSum(root.right, path, result, sum);
            }
            path.remove(path.size() - 1);
        }
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