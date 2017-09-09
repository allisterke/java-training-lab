package practise;

import java.util.*;

public class BananaGambling {

    static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        if(a < b) {
            return gcd(b, a);
        }
        return gcd(b, a%b);
    }

    static boolean isInfinite(int a, int b) {
        while (true) {
            if(a == b) {
                break;
            }
            int c = gcd(a, b);
            a /= c;
            b /= c;
            if((a + b) % 2 == 1) {
                return true;
            }
            if(a > b) {
                a -= b;
                b <<= 1;
            }
            else {
                b -= a;
                a <<= 1;
            }
        }
        return false;
    }

    static int pair(int[] bs) {
        Set<Integer>[] g = new Set[bs.length];
        for(int i = 0; i < bs.length; ++ i) {
            g[i] = new HashSet<Integer>();
        }
        for(int i = 0; i < bs.length; ++ i) {
            for(int j = i+1; j < bs.length; ++ j) {
                if(isInfinite(bs[i], bs[j])) {
                    g[i].add(j);
                    g[j].add(i);
                }
            }
        }
        int p = 0;
        while(true) {
            int index = -1;
            for(int i = 0; i < g.length; ++ i) {
                if(!g[i].isEmpty() && (index == -1 || g[i].size() < g[index].size())) {
                    index = i;
                }
            }
            if(index == -1) {
                break;
            }
            Set<Integer> set = g[index];
            int other = -1;
            for(Integer i : set) {
                g[i].remove(index);
                if(other == -1 || g[i].size() < g[other].size()) {
                    other = i;
                }
            }
            g[index].clear();
            if(other == -1) {
                continue;
            }
            else {
                p += 2;
            }
            for(Integer i : g[other]) {
                g[i].remove(other);
            }
            g[other].clear();
        }
        return bs.length - p;
    }

    public static void main(String[] args) {
        System.out.println(pair(new int[] {
                1, 1
        }));
        System.out.println(pair(new int[] {
                1, 7, 3, 21, 13, 19
        }));
    }
}
