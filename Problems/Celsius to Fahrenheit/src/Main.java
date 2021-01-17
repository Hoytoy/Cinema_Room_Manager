import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double celcius = scanner.nextDouble();
        System.out.println(celcius * 1.8 + 32);
    }
}