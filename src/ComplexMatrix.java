public class ComplexMatrix {
    private int rows;
    private int cols;
    private Complex_number[][] data;

    public ComplexMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Размеры матрицы должны быть положительными");
        }

        this.rows = rows;
        this.cols = cols;
        data = new Complex_number[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex_number(0, 0);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Complex_number get(int row, int col) {
        return data[row][col];
    }

    public void set(int row, int col, Complex_number value) {
        if (value == null) {
            throw new IllegalArgumentException("Элемент матрицы не может быть null");
        }
        data[row][col] = value;
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Для сложения размеры матриц должны совпадать");
        }

        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = data[i][j].add(other.data[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException(
                    "Число столбцов первой матрицы должно равняться числу строк второй");
        }

        ComplexMatrix result = new ComplexMatrix(rows, other.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                Complex_number sum = new Complex_number(0, 0);
                for (int k = 0; k < cols; k++) {
                    sum = sum.add(data[i][k].multiply(other.data[k][j]));
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[j][i] = data[i][j];
            }
        }
        return result;
    }

    private ComplexMatrix minor(int excludedRow, int excludedCol) {
        ComplexMatrix result = new ComplexMatrix(rows - 1, cols - 1);
        int resultRow = 0;

        for (int i = 0; i < rows; i++) {
            if (i != excludedRow) {
                int resultCol = 0;
                for (int j = 0; j < cols; j++) {
                    if (j != excludedCol) {
                        result.data[resultRow][resultCol] = data[i][j];
                        resultCol++;
                    }
                }
                resultRow++;
            }
        }
        return result;
    }

    public Complex_number determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException(
                    "Определитель можно вычислить только для квадратной матрицы");
        }

        if (rows == 1) {
            return data[0][0];
        }

        if (rows == 2) {
            return data[0][0].multiply(data[1][1])
                    .subtract(data[0][1].multiply(data[1][0]));
        }

        Complex_number result = new Complex_number(0, 0);
        boolean positive = true;

        for (int j = 0; j < cols; j++) {
            if (!data[0][j].isZero()) {
                Complex_number term = data[0][j].multiply(minor(0, j).determinant());

                if (positive) {
                    result = result.add(term);
                } else {
                    result = result.subtract(term);
                }

                positive = !positive;
            }
        }
        return result;
    }

    public ComplexMatrix inverse() {
        if (rows != cols) {
            throw new IllegalArgumentException(
                    "Обратную матрицу можно найти только для квадратной матрицы");
        }

        Complex_number det = determinant();
        if (det.isZero()) {
            throw new ArithmeticException(
                    "Определитель равен нулю, обратной матрицы нет");
        }

        ComplexMatrix result = new ComplexMatrix(rows, cols);

        if (rows == 1) {
            result.data[0][0] = new Complex_number(1, 0).divide(data[0][0]);
            return result;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Complex_number cofactor = minor(i, j).determinant();

                if ((i + j) % 2 != 0) {
                    cofactor = new Complex_number(0, 0).subtract(cofactor);
                }

                result.data[i][j] = cofactor.divide(det);
            }
        }
        return result;
    }

    public ComplexMatrix divide(ComplexMatrix other) {
        if (other.rows != other.cols) {
            throw new IllegalArgumentException(
                    "Матрица-делитель должна быть квадратной");
        }

        if (cols != other.rows) {
            throw new IllegalArgumentException(
                    "Число столбцов первой матрицы должно равняться размеру матрицы-делителя");
        }

        return multiply(other.inverse());
    }


    public String toString() {
        String text = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                text = text + data[i][j] + "  ";
            }
            if (i < rows - 1) {
                text = text + "\n";
            }
        }
        return text;
    }
}