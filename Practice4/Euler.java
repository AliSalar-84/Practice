import org.apache.commons.math3.fraction.Fraction;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Euler {

    private static Set<Integer> primeFactorsList(int n) {
        Set<Integer> factors = new HashSet<>();
        if (n < 2) return factors;

        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.addAll(primeFactorsList(n / i));
                break;
            }
        }
        return factors;
    }

    public static void fi(int n) {

        var result = new Fraction(n, 1);

        Set<Integer> list = primeFactorsList(n);

        for (Integer i : list)
            result = result.multiply(Fraction.ONE.subtract(new Fraction(1, i)));

        System.out.println("Euler's  φ(" + n + ") is = " + result);
    }

    public static void main(String[] args) throws IOException {
        var bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the number: ");
        int n = Integer.parseInt(bf.readLine());

        fi(n);

    }

}

