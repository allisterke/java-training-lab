package practise;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FinalProbability {
    static long gcd(long a, long b) {
        if(a < 0) {
            a = -a;
        }
        if(b < 0) {
            b = -b;
        }
        if(a < b) {
            return gcd(b, a);
        }
        if(b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }

    static class Fraction {
        long n, d;

        public Fraction(long n, long d) {
            long g = gcd(n, d);
            this.n = n / g;
            this.d = d / g;
        }

        Fraction plus(Fraction b) {
            long n1 = n * b.d + d * b.n;
            long d1 = d * b.d;
            return new Fraction(n1, d1);
        }

        Fraction subtract(Fraction b) {
            long n1 = n * b.d - d * b.n;
            long d1 = d * b.d;
            return new Fraction(n1, d1);
        }

        Fraction multiply(Fraction b) {
            long n1 = n * b.n;
            long d1 = d * b.d;
            return new Fraction(n1, d1);
        }

        Fraction divideBy(Fraction b) {
            long n1 = n * b.d;
            long d1 = d * b.n;
            return new Fraction(n1, d1);
        }

        final static Fraction ZERO = new Fraction(0, 1);
        final static Fraction ONE = new Fraction(1, 1);
    }

    /**
     * calculate the final probability of each element
     * @param equations size = N * (N+1)
     * @return
     */
    Fraction[] calculate(Fraction[][] equations) {
        int n = equations.length;
        for(int i = 0; i < n; ++ i) {
            for(int j = i+1; j < n; ++ j) {
                Fraction f = equations[j][i].divideBy(equations[i][i]);
                for(int k = i; k <= n; ++ k) {
                    equations[j][k] = equations[j][k].subtract(equations[i][k].multiply(f));
                }
            }
        }
        Fraction[] result = new Fraction[n];
        for(int i = n-1; i >= 0; -- i) {
            Fraction right = equations[i][n];
            for(int j = i+1; j < n; ++ j) {
                right = right.subtract(equations[i][j].multiply(result[j]));
            }
            result[i] = right.divideBy(equations[i][i]);
        }
        return result;
    }

    long mcc(long a, long b) {
        return a * b / gcd(a, b);
    }

    long mcc(long[] list) {
        if(list.length == 1) {
            return list[0];
        }
        long r = mcc(list[0], list[1]);
        for(int i = 2; i < list.length; ++ i) {
            r = mcc(r, list[i]);
        }
        return r;
    }

    int[] convert(Fraction[] list) {
        long[] d = new long[list.length];
        for(int i = 0; i < list.length; ++ i) {
            d[i] = list[i].d;
        }
        long r = mcc(d);
        int[] result = new int[d.length - 1];
        for(int i = 2; i < list.length; ++ i) {
            result[i-2] = (int)(list[i].n * (r / list[i].d));
        }
        result[result.length - 1] = (int)r;
        return result;
    }

    Fraction[][] buildEquations(int n, List<Integer> a, List<Integer> b, List<Fraction> c) {
        Fraction[][] matrix = new Fraction[n][n+1];
        for(int i = 0; i < n; ++ i) {
            for(int j = 0; j <= n; ++ j) {
                matrix[i][j] = Fraction.ZERO;
            }
            matrix[i][i] = Fraction.ONE;
        }
        for(int i = 0; i < a.size(); ++ i) {
            int t1 = a.get(i), t2 = b.get(i);
            matrix[t2][t1] = matrix[t2][t1].subtract(c.get(i));
            if(t2 == 0) {
                matrix[0][n] = matrix[0][n].plus(Fraction.ONE);
            }
        }
        if(matrix[0][n] == Fraction.ZERO) {
            matrix[0][n] = Fraction.ONE;
        }
        return matrix;
    }

    Fraction[][] buildEquations(int[][] matrix) {
        int n = matrix.length;
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Fraction> c = new ArrayList<>();
        for(int i = 0; i < n; ++ i) {
            int total = 0;
            for(int j = 0; j < n; ++ j) {
                total += matrix[i][j];
            }
            if(total == 0) {
                continue;
            }
            for(int j = 0; j < n; ++ j) {
                if(matrix[i][j] != 0) {
                    a.add(i);
                    b.add(j);
                    c.add(new Fraction(matrix[i][j], total));
                }
            }
        }
        return buildEquations(n, a, b, c);
    }

    int[] solve(int[][] m) {
        return m.length > 2 ? convert(calculate(buildEquations(m))) : new int[] {};
    }

    public static void main(String[] args) {
        FinalProbabilityWithBigInteger fp = new FinalProbabilityWithBigInteger();
        List<int[][]> list = Arrays.asList(new int[][][] {
                new int[][] {
                        new int[] {0,1,0,0,0,1},
                        new int[] {4,0,0,3,2,0},
                        new int[] {0,0,0,0,0,0},
                        new int[] {0,0,0,0,0,0},
                        new int[] {0,0,0,0,0,0},
                        new int[] {0,0,0,0,0,0}
                },
                new int[][] {
                        new int[] {0, 2, 1, 0, 0},
                        new int[] {0, 0, 0, 3, 4},
                        new int[] {0, 0, 0, 0, 0},
                        new int[] {0, 0, 0, 0, 0},
                        new int[] {0, 0, 0, 0, 0}
                },
                new int[][] {
                        new int[] {0, 1, 0, 0, 0, 1},
                        new int[] {4, 0, 0, 3, 2, 0},
                        new int[] {0, 0, 0, 0, 0, 0},
                        new int[] {0, 0, 0, 0, 0, 0},
                        new int[] {0, 0, 0, 0, 0, 0},
                        new int[] {0, 0, 0, 0, 0, 0}
                },
                new int[][] {
                        new int[] {0, 1, 0},
                        new int[] {0, 0, 1},
                        new int[] {0, 0, 0}
                },
                new int[][] {
                        new int[] {0}
                },
                new int[][] {
                        new int[] {0, 1},
                        new int[] {0, 0}
                },
                new int[][] {
                }
        });
        List<int[]> result = Arrays.asList(
                new int[] {0, 3, 2, 9, 14},
                new int[] {7, 6, 8, 21},
                new int[] {0, 3, 2, 9, 14},
                new int[] {1, 1},
                new int[] {1, 1},
                new int[] {1, 1},
                new int[] {}
        );
        for(int i = 0; i < list.size(); ++ i) {
            System.out.println(Arrays.equals(fp.solve(list.get(i)), result.get(i)));
            System.out.println(Arrays.stream(fp.solve(list.get(i))).boxed().collect(Collectors.toList()));
        }
    }
}

class FinalProbabilityWithBigInteger {
    static BigInteger gcd(BigInteger a, BigInteger b) {
        if(a.compareTo(BigInteger.ZERO) < 0) {
            a = a.negate();
        }
        if(b.compareTo(BigInteger.ZERO) < 0) {
            b = b.negate();
        }
        if(a.compareTo(b) < 0) {
            return gcd(b, a);
        }
        if(b.equals(BigInteger.ZERO)) {
            return a;
        }
        return gcd(b, a.mod(b));
    }

    static class Fraction {
        BigInteger n, d;

        public Fraction(BigInteger n, BigInteger d) {
            BigInteger g = gcd(n, d);
            this.n = n.divide(g);
            this.d = d.divide(g);
        }

        Fraction negate() {
            return new Fraction(n.negate(), d);
        }

        Fraction plus(Fraction b) {
            BigInteger n1 = n.multiply(b.d).add(d.multiply(b.n));
            BigInteger d1 = d.multiply(b.d);
            return new Fraction(n1, d1);
        }

        Fraction subtract(Fraction b) {
            return plus(b.negate());
        }

        Fraction multiply(Fraction b) {
            BigInteger n1 = n.multiply(b.n);
            BigInteger d1 = d.multiply(b.d);
            return new Fraction(n1, d1);
        }

        Fraction divideBy(Fraction b) {
            BigInteger n1 = n.multiply(b.d);
            BigInteger d1 = d.multiply(b.n);
            return new Fraction(n1, d1);
        }

        final static Fraction ZERO = new Fraction(BigInteger.ZERO, BigInteger.ONE);
        final static Fraction ONE = new Fraction(BigInteger.ONE, BigInteger.ONE);
    }

    /**
     * calculate the final probability of each element
     * @param equations size = N * (N+1)
     * @return
     */
    Fraction[] calculate(Fraction[][] equations) {
        int n = equations.length;
        for(int i = 0; i < n; ++ i) {
            for(int j = i+1; j < n; ++ j) {
                Fraction f = equations[j][i].divideBy(equations[i][i]);
                for(int k = i; k <= n; ++ k) {
                    equations[j][k] = equations[j][k].subtract(equations[i][k].multiply(f));
                }
            }
        }
        Fraction[] result = new Fraction[n];
        for(int i = n-1; i >= 0; -- i) {
            Fraction right = equations[i][n];
            for(int j = i+1; j < n; ++ j) {
                right = right.subtract(equations[i][j].multiply(result[j]));
            }
            result[i] = right.divideBy(equations[i][i]);
        }
        return result;
    }

    int[] convert(Fraction[] list) {
        BigInteger r = BigInteger.ONE;
        for(Fraction f : list) {
            r = r.multiply(f.d);
        }
        BigInteger g = r;
        for(Fraction f : list) {
            g = gcd(g, f.n.multiply(r).divide(f.d));
        }
        int[] result = new int[list.length + 1];
        for(int i = 0; i < list.length; ++ i) {
            result[i] = Math.abs(list[i].n.multiply(r).divide(list[i].d).divide(g).intValue());
        }
        result[list.length] = Math.abs(r.divide(g).intValue());
        return result;
    }

    Fraction[][] buildEquations(int[][] matrix) {
        int n = matrix.length;
        int[] sums = new int[n];
        for(int i = 0; i < n; ++ i) {
            for(int j = 0; j < n; ++ j) {
                sums[i] += matrix[i][j];
            }
        }
        Fraction[][] equations = new Fraction[n][n+1];
        for(int i = 0; i < n; ++ i) {
            for(int j = 0; j < n; ++ j) {
                if(matrix[j][i] == 0) {
                    equations[i][j] = Fraction.ZERO;
                }
                else {
                    equations[i][j] = new Fraction(BigInteger.valueOf(matrix[j][i]), BigInteger.valueOf(sums[j]));
                }
            }
        }
        for(int i = 0; i < n; ++ i) {
            equations[i][i] = equations[i][i].subtract(Fraction.ONE);
            equations[i][n] = Fraction.ZERO;
        }
        equations[0][n] = Fraction.ONE.negate();
        return equations;
    }

    int[] solve(int[][] matrix) {
        if(matrix.length == 0) {
            return new int[] {};
        }
        int n = matrix.length;
        int[] sums = new int[n];
        int terminal = 0;
        for(int i = 0; i < n; ++ i) {
            for(int j = 0; j < n; ++ j) {
                sums[i] += matrix[i][j];
            }
            if(sums[i] == 0) {
                ++ terminal;
            }
        }
        if(terminal == 0) {
            return new int[] {};
        }
        Fraction[] p = calculate(buildEquations(matrix));
        Fraction[] t = new Fraction[terminal];
        for(int i = 0, j = 0; i < terminal; ++ i, ++ j) {
            for (; sums[j] != 0; ++ j) ;
            t[i] = p[j];
        }
        return convert(t);
    }
}