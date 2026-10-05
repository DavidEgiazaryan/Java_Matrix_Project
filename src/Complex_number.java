public class Complex_number{
        private double real;
        private double imaginary;

        public Complex_number(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        public double getReal() {
            return real;
        }

        public double getImaginary() {
            return imaginary;
        }

        public Complex_number add(Complex_number other) {
            return new Complex_number(real + other.real, imaginary + other.imaginary);
        }

        public Complex_number subtract(Complex_number other) {
            return new Complex_number(real - other.real, imaginary - other.imaginary);
        }

        public Complex_number multiply(Complex_number other) {
            double newReal = real * other.real - imaginary * other.imaginary;
            double newImaginary = real * other.imaginary + imaginary * other.real;
            return new Complex_number(newReal, newImaginary);
        }

        public Complex_number divide(Complex_number other) {
            if (other.isZero()) {
                throw new ArithmeticException("Деление на ноль");
            }

            double denominator = other.real * other.real
                    + other.imaginary * other.imaginary;
            double newReal = (real * other.real
                    + imaginary * other.imaginary) / denominator;
            double newImaginary = (imaginary * other.real
                    - real * other.imaginary) / denominator;
            return new Complex_number(newReal, newImaginary);
        }

        public boolean isZero() {
            return real == 0.0 && imaginary == 0.0;
        }

        public String toString() {
            if (imaginary < 0) {
                return real + " - " + (-imaginary) + "i";
            }
            return real + " + " + imaginary + "i";
        }
}
