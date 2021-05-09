import java.util.Scanner;

public class Fibonacci {

	public static int fibonacci(int n) {

		if (n == 1 || n == 2) {
			return 1;
		}
		return fibonacci(n - 2) + fibonacci(n - 1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduceti numarul n.");
		int n = sc.nextInt();
		int fib = 1;

		for (int i = 2; fib <= n; i++) {
			System.out.print(fib + " ");
			fib = fibonacci(i);

		}
		sc.close();

	}
}
