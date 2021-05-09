import java.util.Random;

public class Main {
	public static void main(String[] args) {

		PlayerStatus playerStatus1 = new PlayerStatus();
		PlayerStatus playerStatus2 = new PlayerStatus();

		playerStatus1.initPlayer("Artur", 3, 5000);
		playerStatus2.initPlayer("John", 3, 5000);

		final double mapSizeX = 2000;
		final double mapSizeY = 2000;

		Random rand = new Random();
		System.out.println("Va incepe jocul Battle Royal. Lupta se va desfasura intre Artur si John. ");

		while (!playerStatus1.isDead() && !playerStatus2.isDead()) {
			double newPositionX = rand.nextDouble() * mapSizeX;
			double newPositionY = rand.nextDouble() * mapSizeY;
			playerStatus1.setPositionX(newPositionX);
			playerStatus1.setPositionY(newPositionY);

			newPositionX = rand.nextDouble() * mapSizeX;
			newPositionY = rand.nextDouble() * mapSizeY;
			playerStatus2.setPositionX(newPositionX);
			playerStatus2.setPositionY(newPositionY);

			playerStatus1.findArtifact(getRandomArtifact(rand));
			playerStatus2.findArtifact(getRandomArtifact(rand));

			double distance = playerStatus1.computeDistance(playerStatus2.getPositionX(), playerStatus2.getPositionY());
			giveWeapon(distance, playerStatus1);
			giveWeapon(distance, playerStatus2);

			if (playerStatus1.shouldAttackOponent(playerStatus2)) {
				int damage = playerStatus1.computeDamage(playerStatus2);
				playerStatus2.applyDamage(damage);
				System.out.print("Jucatorul " + playerStatus1.getNickname() + " ataca cu " + damage + " damage. ");
				System.out.println(playerStatus2.getNickname() + " mai are " + playerStatus2.getHealth() + " hp si "
						+ playerStatus2.getLives() + " vieti. ");
			} else if (playerStatus2.shouldAttackOponent(playerStatus1)) {
				int damage = playerStatus2.computeDamage(playerStatus1);
				playerStatus1.applyDamage(damage);
				System.out.print("Jucatorul " + playerStatus2.getNickname() + " ataca cu " + damage + " damage. ");
				System.out.println(playerStatus1.getNickname() + " mai are " + playerStatus1.getHealth() + " hp si "
						+ playerStatus1.getLives() + " vieti. ");
			}
			System.out.print(playerStatus1.getNickname() + " are " + playerStatus1.getScore() + " scor si arma "
					+ playerStatus1.getWeaponInHand() + ". ");
			System.out.println(playerStatus2.getNickname() + " are " + playerStatus2.getScore() + " scor si arma "
					+ playerStatus2.getWeaponInHand() + ". ");

		}
		if (playerStatus1.isDead()) {
			System.out.println("Jocul " + PlayerStatus.getGameName() + " s-a terminat. Jucatorul "
					+ playerStatus1.getNickname() + " a murit. ");
		}
		if (playerStatus2.isDead()) {
			System.out.println("Jocul " + PlayerStatus.getGameName() + " s-a terminat. Jucatorul "
					+ playerStatus2.getNickname() + " a murit. ");
		}

	}

	public static int getRandomArtifact(Random rand) {
		int number = rand.nextInt(20);
		switch (number) {
		case 0:
			// return a perfect number
			return 28;
		case 1:
			// return a prime number
			return 17;
		case 2:
			// return even number with sum digit div 3
			return 24;
		default:
			return 500;
		}

	}

	public static void giveWeapon(double distance, PlayerStatus playerStatus) {
		if (distance > 1000) {
			if (playerStatus.setWeaponInHand("sniper") == false) {
				if (playerStatus.setWeaponInHand("kalashnikov") == false) {
					playerStatus.setWeaponInHand("knife");
				}
			}
		} else if (distance <= 1000) {
			if (playerStatus.setWeaponInHand("kalashnikov") == false) {
				if (playerStatus.setWeaponInHand("sniper") == false) {
					playerStatus.setWeaponInHand("knife");
				}
			}
		}
	}
}
