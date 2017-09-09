package practise;

import java.util.*;

public class BestShooter {

    static class LastPoint {
        int x, y;
        LastPoint last;
        public LastPoint(int a, int b, LastPoint p) {
            x = a;
            y = b;
            last = p;
        }
    }

    static int width, height;
    static int startX, startY;
    static int endX, endY;
    static long maxDistance;
    static Map<List<Object>, Boolean> cache;

    static int shoot0(int X, int Y, int x1, int y1, int x2, int y2, long D) {
        cache = new HashMap<>();
        width = X;
        height = Y;
        startX = x1;
        startY = y1;
        endX = x2;
        endY = y2;
        maxDistance = D * D;
        if(!checkDistance(x1, y1)) {
            return 0;
        }
        Queue<LastPoint> queue = new LinkedList<>();
        int count = 0, fake = 0;
        queue.add(new LastPoint(x1, y1, null));
        while (!queue.isEmpty()) {
            LastPoint last = queue.poll();
            if(checkRoute(endX, endY, last)) {
                ++ count;
                System.out.println("count: " + count);
                System.out.println("distance: " + Math.sqrt((last.x - endX)*(last.x - endX) + (last.y - endY)*(last.y - endY)));
            }
            else {
                ++ fake;
                System.out.println("fake: " + fake);
            }
            if(last.x > 0 && checkDistance(-last.x, last.y)) {
                queue.add(new LastPoint(-last.x, last.y, last));
            }
            if(last.x < X && checkDistance(2*X - last.x, last.y)) {
                queue.add(new LastPoint(2*X - last.x, last.y, last));
            }
            if(last.y > 0 && checkDistance(last.x, -last.y)) {
                queue.add(new LastPoint(last.x, -last.y, last));
            }
            if(last.y < Y && checkDistance(last.x, 2*Y - last.y)) {
                queue.add(new LastPoint(last.x, 2*Y - last.y, last));
            }
        }
        return count;
    }

    static boolean checkRoute(double x2, double y2, LastPoint last) {
        if(last.last == null) {
            return true;
        }
        List<Object> key = Arrays.asList(x2, y2, last);
        while(!cache.containsKey(key)) {
            double x1 = last.x, y1 = last.y;
            double x, y;
            if(last.x == last.last.x) {
                y = (last.y + last.last.y) / 2;
                x = ((y - y2)*x1 - (y - y1)*x2) / (y1 - y2);
                if(!checkPosition(x, 0, width) || !checkNoShoot(x1, y1, x2, y2)) {
                    cache.put(key, false);
                    break;
                }
            }
            else {
                x = (last.x + last.last.x) / 2;
                y = ((x - x2)*y1 - (x - x1)*y2) / (x1 - x2);
                if(!checkPosition(y, 0, height) || !checkNoShoot(x1, y1, x2, y2)) {
                    cache.put(key, false);
                    break;
                }
            }
            cache.put(key, checkRoute(x, y, last.last));
            break;
        }
        return cache.get(key);
    }

    static boolean checkRoute0(LastPoint last) {
        double x2 = endX, y2 = endY;
        while (last.last != null) {
            double x1 = last.x, y1 = last.y;
            double x = 0, y = 0;
            if(last.x == last.last.x) {
                y = (last.y + last.last.y) / 2;
                x = ((y - y2)*x1 - (y - y1)*x2) / (y1 - y2);
                if(!checkPosition(x, 0, width) || !checkNoShoot(x1, y1, x2, y2)) {
                    return false;
                }
            }
            else {
                x = (last.x + last.last.x) / 2;
                y = ((x - x2)*y1 - (x - x1)*y2) / (x1 - x2);
                if(!checkPosition(y, 0, height) || !checkNoShoot(x1, y1, x2, y2)) {
                    return false;
                }
            }
            x2 = x;
            y2 = y;
            last = last.last;
        }
        return true;
    }

    static boolean checkPosition(double x, double low, double high) {
        return !(x < low || x > high || Math.abs(x - low) <= 1e-3 || Math.abs(x - high) <= 1e-3);
    }

    static boolean checkNoShoot(double x1, double y1, double x2, double y2) {
        if(startX == x1 && startY == y1) {
            return checkNoShoot(x1, y1, x2, y2, endX, endY);
        }
        else if(endX == x2 && endY == y2) {
            return checkNoShoot(x1, y1, x2, y2, startX, startY);
        }
        else {
            return checkNoShoot(x1, y1, x2, y2, startX, startY) && checkNoShoot(x1, y1, x2, y2, endX, endY);
        }
    }

    static boolean checkNoShoot(double x1, double y1, double x2, double y2, double x, double y) {
        return Math.abs((y - y1)*(x - x2) - (y -y2)*(x -x1)) > 1e-3;
    }

    static boolean checkDistance(int x1, int y1) {
        return (x1 - endX)*(x1 - endX) + (y1 - endY)*(y1 - endY) <= maxDistance;
    }

    static int shoot(int X, int Y, int x1, int y1, int x2, int y2, int D) {
        if(x1 > x2) {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if(y1 > y2) {
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        return  shoot1(X, Y, x1, y1, x2, y2, D) +
                shoot2(X, Y, x1, y1, x2, y2, D) +
                shoot3(X, Y, x1, y1, x2, y2, D) +
                shoot4(X, Y, x1, y1, x2, y2, D);
    }

    static int shoot1(int X, int Y, int x1, int y1, int x2, int y2, int D) {
        int W = D / X + 1;
        int H = D / Y + 1;
        int count = 0;
        Set<Long> set = new HashSet<>();
        int s1 = -x1;
        int s2 = -x2;
        for(int i = 0; i <= W; ++ i) {
            s1 = 2 * i * X - s1;
            s2 = 2 * i * X - s2;
            int t1 = -y1;
            int t2 = -y2;
            for(int j = 0; j <= H; ++ j) {
                t1 = 2 * j * Y - t1;
                t2 = 2 * j * Y - t2;
                if(reachable(s1 - x1, t1 - y1, D)) {
                    add(set, s1 - x1, t1 - y1);
                }
                if(reachable(s2 - x1, t2 - y1, D) && add(set, s2 - x1, t2 - y1)) {
//                if(reachable(s2 - x1, t2 - y1, D)) {
                    ++count;
//                }
                }
            }
        }
        return count;
    }

    static int shoot2(int X, int Y, int x1, int y1, int x2, int y2, int D) {
        int W = D / X + 1;
        int H = D / Y + 1;
        int count = 0;
        Set<Long> set = new HashSet<>();
        int s1 = x1;
        int s2 = x2;
        for(int i = 0; i <= W; ++ i) {
            s1 = - 2 * i * X - s1;
            s2 = - 2 * i * X - s2;
            int t1 = -y1;
            int t2 = -y2;
            for(int j = 0; j <= H; ++ j) {
                t1 = 2 * j * Y - t1;
                t2 = 2 * j * Y - t2;
                if(reachable(s1 - x1, t1 - y1, D)) {
                    add(set, s1 - x1, t1 - y1);
                }
                if(reachable(s2 - x1, t2 - y1, D) && add(set, s2 - x1, t2 - y1)) {
//                if(reachable(s2 - x1, t2 - y1, D)) {
                    ++count;
//                }
                }
            }
        }
        return count;
    }

    static int shoot3(int X, int Y, int x1, int y1, int x2, int y2, int D) {
        int W = D / X + 1;
        int H = D / Y + 1;
        int count = 0;
        Set<Long> set = new HashSet<>();
        int s1 = x1;
        int s2 = x2;
        for(int i = 0; i <= W; ++ i) {
            s1 = - 2 * i * X - s1;
            s2 = - 2 * i * X - s2;
            int t1 = y1;
            int t2 = y2;
            for(int j = 0; j <= H; ++ j) {
                t1 = - 2 * j * Y - t1;
                t2 = - 2 * j * Y - t2;
                if(reachable(s1 - x1, t1 - y1, D)) {
                    add(set, s1 - x1, t1 - y1);
                }
                if(reachable(s2 - x1, t2 - y1, D) && add(set, s2 - x1, t2 - y1)) {
//                if(reachable(s2 - x1, t2 - y1, D)) {
                    ++count;
//                }
                }
            }
        }
        return count;
    }

    static int shoot4(int X, int Y, int x1, int y1, int x2, int y2, int D) {
        int W = D / X + 1;
        int H = D / Y + 1;
        int count = 0;
        Set<Long> set = new HashSet<>();
        int s1 = -x1;
        int s2 = -x2;
        for(int i = 0; i <= W; ++ i) {
            s1 = 2 * i * X - s1;
            s2 = 2 * i * X - s2;
            int t1 = y1;
            int t2 = y2;
            for(int j = 0; j <= H; ++ j) {
                t1 = - 2 * j * Y - t1;
                t2 = - 2 * j * Y - t2;
                if(reachable(s1 - x1, t1 - y1, D)) {
                    add(set, s1 - x1, t1 - y1);
                }
                if(reachable(s2 - x1, t2 - y1, D) && add(set, s2 - x1, t2 - y1)) {
//                if(reachable(s2 - x1, t2 - y1, D)) {
                    ++count;
//                }
                }
            }
        }
        return count;
    }

    static boolean reachable(long x, long y, long D) {
        return x*x + y*y <= D*D;
    }

    static int gcd(int a, int b) {
        if(a < b) {
            return gcd(b, a);
        }
        if(b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }

    static boolean add(Set<Long> s, int x, int y) {
        if(x == 0 && y == 0) { // shooter self
            return false;
        }
        if(y < 0) {
            x = -x;
            y = -y;
        }
        int g = gcd(Math.abs(x), Math.abs(y));
        x /= g;
        y /= g;
        long k = x;
        k = (k << 32) | y;
        if(s.contains(k)) {
            return false;
        }
        else {
            s.add(k);
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(shoot(3, 2, 1, 1, 2, 1, 4));
//        System.out.println(shoot(3, 3, 1, 1, 2, 2, 10000));
        System.out.println(shoot(300, 275, 150, 150, 185, 100, 500));
    }
}
