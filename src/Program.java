import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.sqrt;

public class Program {

    // ax^2 = 0
    static boolean caseWithOnlyA(Double varA, Double[] res) {
        if (varA == 0) {
            System.out.println("Корнем уравнения является любое число\n");
            return false;
        }  else {
            res[0] = 0.0;
            return true;
        }
    }

    // ax^2 + c = 0
    static void caseWithNoB(Double varA, Double varC, Double[] res) {
        if ((-varC / varA) > 0) {
            res[0] = sqrt(-varC / varA);
            res[1] = -res[0];
        }
    }

    // ax^2 + bx = 0
    static void caseWithNoC(Double varA, Double varB, Double[] res) {
        res[0] = -varB / varA;
        res[1] = 0.0;
    }

    // ax^2 + bx + c = 0
    static void findDiscriminant(Double varA, Double varB, Double varC, Double[] res) {
        Double discriminant = Math.pow(varB, 2) - 4 * varA * varC;
        if (discriminant == 0)
            res[0] = -varB / (2 * varA);
        else if (discriminant > 0) {
            res[0] = (-varB + sqrt(discriminant)) / (2 * varA);
            res[1] = (-varB - sqrt(discriminant)) / (2 * varA);
        }
    }

    static void printResult(Double[] res) {
        if (res[0] == null)
            System.out.println("Уравнение не имеет корней\n");
        else {
            DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(
                    Locale.ENGLISH));
            df.setMaximumFractionDigits(2);

            if (res[1] == null)
                System.out.println("Уравнение имеет один корень: " + df.format(res[0]) + "\n");
            else
                System.out.println("Уравнение имеет два корня: " + df.format(res[0]) + " и " + df.format(res[1]) + "\n");
        }
    }

        public static void main(String[] args) throws InterruptedException {
        Double varA;
        Double varB;
        Double varC;

        while (1 == 1) {
            TimeUnit.SECONDS.sleep(1);

            Scanner in = new Scanner(System.in);
            System.out.println("Введите 3 коэффициента для квадратного уравнения");

            try {
                System.out.print("a: ");
                varA = in.nextDouble();
                System.out.print("b: ");
                varB = in.nextDouble();
                System.out.print("c: ");
                varC = in.nextDouble();
            } catch (Exception e) {
                System.err.println("Коэффициент введен неверно. Попробуйте еще!\n");
                continue;
            }

            Double[] res = new Double[2];

            if (varB == 0 && varC == 0) {
                if (caseWithOnlyA(varA, res) == false)
                    continue;
            }
            else if (varA == 0) {
                System.err.println("У уравнения нет решения, т.к. делить на ноль нельзя\n");
                continue;
            }
            else if (varB == 0 || varC == 0) {
                if (varB == 0)
                    caseWithNoB(varA, varC, res);
                else if (varC == 0)
                    caseWithNoC(varA, varB, res);
            }
            else
                findDiscriminant(varA, varB, varC, res);
            printResult(res);
        }
    }
}