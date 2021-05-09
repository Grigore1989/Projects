
public class LazyNumberDetails {

	private int number;
	private boolean cachedIsPrime;
	private boolean isCachedPrimeComputed;
	private boolean cachedIsPerfect;
	private boolean isCachedPerfectComputed;
	private boolean cachedIsMagic;
	private boolean isCachedMagicComputed;

	public LazyNumberDetails(int number) {
		updateNumber(number);

	}

	public void updateNumber(int number) {
		this.number = number;
		isCachedPrimeComputed = false;
		isCachedPerfectComputed = false;
		isCachedMagicComputed = false;

	}

	public boolean isPrime() {
		if (isCachedPrimeComputed == false) {
			System.out.println("Calculam daca numarul este prim: ");
			cachedIsPrime = computeIsPrime();
			isCachedPrimeComputed = true;
		}
		System.out.println("Este " + number + " numar prim? " + cachedIsPrime);
		return cachedIsPrime;
	}

	public boolean computeIsPrime() {

		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;

	}

	public boolean isPerfect() {
		if (isCachedPerfectComputed == false) {
			System.out.println("Calculam daca numarul este perfect: ");
			cachedIsPerfect = computeIsPerfect();
			isCachedPerfectComputed = true;
		}
		System.out.println("Este " + number + " numar perfect? " + cachedIsPerfect);
		return cachedIsPerfect;

	}

	public boolean computeIsPerfect() {
		int sum = 0;
		for (int i = 1; i < number; i++) {
			if (number % i == 0) {
				sum += i;
			}
		}
		return sum == number;
	}

	public int reduceDigitsSum(int n) {
		while (n / 10 != 0) {
			int sum = 0;
			while (n != 0) {
				sum += n % 10;
				n /= 10;
			}
			n = sum;
		}
		return n;
	}

	public boolean isMagic() {
		if (isCachedMagicComputed == false) {
			System.out.println("Calculam daca numarul este magic: ");
			cachedIsMagic = computeIsMagic();
			isCachedMagicComputed = true;
		}
		System.out.println("Este " + number + " numar magic? " + cachedIsMagic);
		return cachedIsMagic;

	}

	public boolean computeIsMagic() {
		int result = reduceDigitsSum(number);
		if (result != 3 && result != 7 && result != 9) {
			return false;
		}
		return true;
	}

}
