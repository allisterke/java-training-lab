package leetcode.a655;

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

    public List<List<String>> printTree(TreeNode root) {
        int height = treeHeight(root);
        List<List<String>> list = new ArrayList<>();
        for(int i = 0; i < height; ++ i) {
            List<String> ls = new ArrayList<>();
            for(int j = 1; j < (1 << height); ++ j) {
                ls.add("");
            }
            list.add(ls);
        }
        printTree(root, list, 0, 0, (1 << height) - 2);
        return list;
    }

    void printTree(TreeNode root, List<List<String>> list, int h, int begin, int end) {
        if(root != null) {
            int mid = (begin + end) / 2;
            list.get(h).set(mid, String.valueOf(root.val));
            printTree(root.left, list, h+1, begin, mid - 1);
            printTree(root.right, list, h+1, mid + 1, end);
        }
    }

    int treeHeight(TreeNode root) {
        if(root != null) {
            return Math.max(treeHeight(root.left), treeHeight(root.right)) + 1;
        }
        return 0;
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