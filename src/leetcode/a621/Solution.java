package leetcode.a621;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int leastInterval(char[] tasks, int n) {
        ++ n;
        int count[] = new int[26];
        for(char t : tasks) {
            ++ count[t - 'A'];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> - Integer.compare(a, b));
        for(int c : count) {
            if(c > 0) {
                queue.add(c);
            }
        }
        int li = 0;
        while (!queue.isEmpty()) {
            int max = Math.min(queue.size(), n);
            int[] cache = new int[max];
            for(int i = 0; i < max; ++ i) {
                cache[i] = queue.poll();
            }
            li += n;
            for(int i = 0; i < max; ++ i) {
                cache[i] -= 1;
            }
            for(int i = 0; i < max; ++ i) {
                if(cache[i] > 0) {
                    queue.add(cache[i]);
                }
            }
            if(queue.isEmpty()) {
                li -= n - max;
                break;
            }
        }
        return li;
    }

    public static void main(String[] args) {
        List<char[]> tests = Arrays.asList(
//                new String("AAABB").toCharArray(),
                new String("FJJAJFCHJBEGGFACIFJCJCHCADGHBFGCCAEBHJEIFDEACDBDJJCFDDJHAECDJDGGBCEGHIDHFEIBDEIECJGIDEDJCACCDIJBDHHJGBGAHEHEDEJEJCFCJGBCIIHFADGFCCFGCJBBICJJEGHCIGJIGGJGGEGBIJBHDHGFCHCDAGBHHBJCAFJGFEBFEBBAEFEHIICGJDHEFGGDEBFJJJDHEBDJIFCIEHFEGDECFEDEAIEADHGCIEGAHIGGAGFHJDFAGHBJAHBHCGFACCBIGGBCJJIEGDIJICGAJGFJFCFGJIEBGFADAIAEHFDDCBJIJHICDAGFIBEDCJGIHECEIIBBHJCFIDBFHFACAABDCAGBGFEGAAACJHHGCCBCEBEFIEEDIHGFAHBJBGHCCBGCBAEGAJGDCIGFGGAJEIDEAFAHCEDDDHIFFAFAACJDJHIFACBCACCHAJIBAIFJCIBCEEEJGFEIAAEBJHHHAHJEFEFGJDIDIFBJDAADFGBJHFADHCBAJHIFHEGBAFFACDGIIJHHCJGBADBFJDIAFFFFAEBCGHEBBAGDCCEACFGAIFBHJGCBHDAHBHHCAFICFACJIHHFBBDECJFCEAJECAEBAJFJJJHHCIEGGHJJHHHJHAGICECDGGFHDGHAEIDAHGEABFICAFBAIFGIFDABJBDFGJJAACHGFBIIJAHDFEFJBFCGEAGHEHHFIGCCGJBHFHDIBDIFHIDFGGEACAGHGHJFDFGDDCJCJGGGGHHGDEHGCBFIFCHJIAFDCFCEEDDCGBFEJCIEDBBIIIHCECJFGAIJDICGFIEIEFAGEJAIADAGJFEDIAEJICJBFBECEFGEJJIEDFCHHBGDIIFBGCFJBGJHDGCCIIEIBHBIGFHGCJDCEGFCHDACDHBCHIBAJCBDJDHFBAGGJIEFADHDBCAHFGBFHBHIJDHIBCDGAEAAIFIFBBIFAEIABGCFIAFIDHBIIBJFEBBBDCJEJJGDFFFGIHJJGDGF").toCharArray()
        );
        List<Integer> stop = Arrays.asList(
//                2,
                8
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.leastInterval(tests.get(i), stop.get(i)));
        }
    }
}