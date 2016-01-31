package com.mygdx.conquer;

public class Resources {
	private final int foodRound, oresRound, woodRound;
	private int troops, enemies, food, ores, wood;

	public Resources(final int foodRound, final int oresRound,
			final int woodRound, final int enemy) {
		this.foodRound = foodRound;
		this.oresRound = oresRound;
		this.woodRound = woodRound;
		this.enemies = enemy;
	}

	public int getFoodRound() {
		return foodRound;
	}

	public int getOresRound() {
		return oresRound;
	}

	public int getWoodRound() {
		return woodRound;
	}

	public int getTroops() {
		return troops;
	}

	public int getEnemy() {
		return enemies;
	}

	public int getFood() {
		return food;
	}

	public int getOres() {
		return ores;
	}

	public int getWood() {
		return wood;
	}

	public void manageTroops(final int troops) {
		this.troops = troops;
	}

	public void manageEnemies(final int enemies) {
		this.enemies = enemies;
	}

	public void manageFood(final int food) {
		this.food += food;
	}

	public void manageOres(final int ores) {
		this.ores = ores;
	}

	public void manageWood(final int wood) {
		this.wood = wood;
	}

}
