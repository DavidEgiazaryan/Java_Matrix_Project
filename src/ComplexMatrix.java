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