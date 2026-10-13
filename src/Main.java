//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите матрицу A:");
        ComplexMatrix a = readMatrix(scanner);

        System.out.println("Введите матрицу B:");
        ComplexMatrix b = readMatrix(scanner);

        System.out.println("Матрица A:");
        System.out.println(a);

        System.out.println("Матрица B:");
        System.out.println(b);

        scanner.close();
    }

    private static ComplexMatrix readMatrix(Scanner scanner) {
        System.out.print("Количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Количество столбцов: ");
        int cols = scanner.nextInt();

        ComplexMatrix matrix = new ComplexMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println("Элемент [" + (i + 1) + "][" + (j + 1) + "]:");

                System.out.print("Действительная часть: ");
                double real = scanner.nextDouble();

                System.out.print("Мнимая часть: ");
                double imaginary = scanner.nextDouble();

                matrix.set(i, j, new Complex_number(real, imaginary));
            }
        }

        return matrix;
    }
}