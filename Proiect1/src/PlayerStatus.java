
public class PlayerStatus {
	private String nickname;
	private int score;
	private int lives;
	private int health = 100;
	private String weaponInHand = "";
	private double positionX;
	private double positionY;
	private static String gameName = "Battle Royal";

	public void initPlayer(String nickname) {
		this.nickname = nickname;
	}

	public void initPlayer(String nickname, int lives) {
		this.nickname = nickname;
		this.lives = lives;
	}

	public void initPlayer(String nickname, int lives, int score) {
		this.nickname = nickname;
		this.lives = lives;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public int getHealth() {
		return health;
	}

	public int getLives() {
		return lives;
	}

	public void findArtifact(int artifactCode) {
		if (isPerfectNumber(artifactCode)) {
			System.out.println(
					this.getNickname() + " a primit artifactul " + artifactCode + " care este un nr perfect. ");

			score += 5000;
			lives++;
			health = 100;
			return;
		}
		if (isPrimeNumber(artifactCode)) {
			System.out.println(this.getNickname() + " a primit artifactul " + artifactCode + " care este un nr prim. ");
			score += 1000;
			lives += 2;
			if ((health + 25) > 100) {
				health = 100;
			} else {
				health += 25;
			}
			return;
		}
		if (artifactCode % 2 == 0 && getSumDigits(artifactCode) % 3 == 0) {
			System.out.println(this.getNickname() + " a primit artifactul " + artifactCode
					+ " care este un nr par cu suma cifrelor divizibila cu 3. ");
			score -= 3000;
			health -= 25;
			if (health <= 0 && lives > 0) {
				lives--;
				health = 100;
			}

			return;
		}
		score += artifactCode;
	}

	private boolean isPerfectNumber(int number) {
		int sum = 0;
		for (int i = 1; i < number; i++) {
			if (number % i == 0) {
				sum = sum + i;
			}
		}

		return sum == number;

	}

	private boolean isPrimeNumber(int number) {
		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;

	}

	private int getSumDigits(int number) {
		int sumDigits = 0;
		while (number != 0) {
			sumDigits += number % 10;
			number /= 10;
		}
		return sumDigits;
	}

	public boolean setWeaponInHand(String weaponInHand) {
		if (this.getWeaponInHand().equals(weaponInHand)) {
			return true;
		}
		int weaponPrice = 0;
		if (weaponInHand.equals("knife")) {
			weaponPrice = 1000;
		}
		if (weaponInHand.equals("sniper")) {
			weaponPrice = 10000;
		}
		if (weaponInHand.equals("kalashnikov")) {
			weaponPrice = 20000;
		}
		if (weaponPrice == 0) {
			return false;
		}
		if (weaponPrice > score) {
			return false;
		}
		this.weaponInHand = weaponInHand;
		score -= weaponPrice;
		return true;

	}

	public String getWeaponInHand() {
		return this.weaponInHand;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public static String getGameName() {
		return gameName;
	}

	void setGameName(String inGameName) {
		gameName = inGameName;
	}

	public void movePlayerTo(double newPositionX, double newPositionY) {
		positionX = newPositionX;
		positionY = newPositionY;
	}

	public String getNickname() {
		return this.nickname;
	}

	public boolean shouldAttackOponent(PlayerStatus oponent) {
		if (this.getWeaponInHand().equals(oponent.getWeaponInHand())) {
			double myProbability = this.calculateWinProbability();
			double otherProbability = oponent.calculateWinProbability();
			return myProbability > otherProbability;
		}

		double distance = this.computeDistance(oponent.getPositionX(), oponent.getPositionY());
		return computeWeaponPower(distance) > oponent.computeWeaponPower(distance);

	}

	public double computeDistance(double oponentPlayerX, double oponentPlayerY) {
		return Math.sqrt(((oponentPlayerX - positionX) * (oponentPlayerX - positionX))
				+ ((oponentPlayerY - positionY) * (oponentPlayerY - positionY)));
	}

	public double calculateWinProbability() {
		return ((3 * health + score / 1000) / 4);
	}

	public int computeWeaponPower(double distance) {
		if (distance > 1000) {
			if (getWeaponInHand().equals("sniper")) {
				return 60;
			}
			if (getWeaponInHand().equals("kalashnikov")) {
				return 30;
			}
			if (getWeaponInHand().equals("knife")) {
				return 10;
			}
		}
		if (distance <= 1000) {
			if (getWeaponInHand().equals("kalashnikov")) {
				return 60;
			}
			if (getWeaponInHand().equals("sniper")) {
				return 30;
			}
			if (getWeaponInHand().equals("knife")) {
				return 10;
			}
		}
		return 0;
	}

	public int computeDamage(PlayerStatus oponent) {
		if (this.getWeaponInHand().equals(oponent.getWeaponInHand())) {
			double myProbability = this.calculateWinProbability();
			double otherProbability = oponent.calculateWinProbability();
			if (myProbability > otherProbability) {
				return 40;
			}

		}
		double distance = this.computeDistance(oponent.getPositionX(), oponent.getPositionY());
		return computeWeaponPower(distance);

	}

	public void applyDamage(int damage) {
		health -= damage;
		if (health < 0) {
			if (lives > 0) {
				lives--;
			}
			health = 100;

		}

	}

	public boolean isDead() {
		return lives <= 0;

	}

}