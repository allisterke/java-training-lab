package leetcode.a606;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public String tree2str(TreeNode t) {
        if(t == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preorder(t, sb);
        return sb.toString();
    }

    void preorder(TreeNode t, StringBuilder sb) {
        sb.append(t.val);
        if(t.left != null) {
            sb.append('(');
            preorder(t.left, sb);
            sb.append(')');
            if(t.right != null) {
                sb.append('(');
                preorder(t.right, sb);
                sb.append(')');
            }
        }
        else if(t.right != null) {
            sb.append("()");
            sb.append('(');
            preorder(t.right, sb);
            sb.append(')');
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