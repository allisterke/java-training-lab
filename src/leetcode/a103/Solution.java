package leetcode.a103;

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
    List<Integer> reverseList(List<Integer> list) {
        for(int left = 0, right = list.size() - 1; left < right; ++ left, -- right) {
            Integer tmp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, tmp);
        }
        return list;
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        boolean reverse = false;
        List<List<Integer>> result = new ArrayList<>();
        if(root != null) {
            List<TreeNode> currentLevel = new ArrayList<>();
            currentLevel.add(root);

            for(; !currentLevel.isEmpty(); currentLevel = getNextLevel(currentLevel)) {
                List<Integer> integers = new ArrayList<>();
                for(TreeNode node : currentLevel) {
                    if(node != null) {
                        integers.add(node.val);
                    }
                }
                if(reverse) {
                    integers = reverseList(integers);
                }
                reverse = !reverse;
                result.add(integers);
            }
        }
        return result;
    }

    List<TreeNode> getNextLevel(List<TreeNode> currentLevel) {
        List<TreeNode> nextLevel = new ArrayList<>();
        for(TreeNode node : currentLevel) {
            if(node != null) {
                if(node.left != null) {
                    nextLevel.add(node.left);
                }
                if(node.right != null) {
                    nextLevel.add(node.right);
                }
            }
        }
        return nextLevel;
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