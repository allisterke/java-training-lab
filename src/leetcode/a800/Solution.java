package leetcode.a800;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String similarRGB(String color) {
        int[] rgb = color2rgb(color);
        int[] best = null;
        int d = 0;
        for (int i = 0; i < 16; ++ i) {
            for (int j = 0; j < 16; ++ j) {
                for(int k = 0; k < 16; ++ k) {
                    int[] c = new int[] {i*17, j*17, k*17};
                    int dist = distance(rgb, c);
                    if(best == null || dist < d) {
                        best = c;
                        d = dist;
                    }
                }
            }
        }
        return rgb2color(best);
    }

    String rgb2color(int[] rgb) {
        StringBuilder builder = new StringBuilder();
        builder.append('#');
        for (int i = 0; i < rgb.length; ++ i) {
            builder.append(int2hex(rgb[i]%16));
            builder.append(int2hex(rgb[i]/16));
        }
        return builder.toString();
    }

    char int2hex(int a) {
        if(a < 10) {
            return (char)(a + '0');
        }
        else {
            return (char)(a - 10 + 'a');
        }
    }

    int[] color2rgb(String color) {
        int[] c = new int[3];
        for (int i = 0; i < 3; ++ i) {
            c[i] = hex2int(color.charAt(i*2+1))*16 + hex2int(color.charAt(i*2+2));
        }
        return c;
    }

    int hex2int(char c) {
        if(Character.isDigit(c)) {
            return c - '0';
        }
        else {
            return c - 'a' + 10;
        }
    }

    int distance(int[] a, int[] b) {
        int s = 0;
        for (int i = 0; i < a.length; ++ i) {
            s += square(a[i] - b[i]);
        }
        return s;
    }

    int square(int a) {
        return a*a;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "#09f166"
        );
        List<String> results = Arrays.asList(
                "#11ee66"
        );

        Solution s = new Solution();
        System.out.println(s.distance(s.color2rgb("#09f166"), s.color2rgb("#11ee66")));
        System.out.println(s.distance(s.color2rgb("#09f166"), s.color2rgb("#99ffff")));
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.similarRGB(tests.get(i)));
        }
    }
}