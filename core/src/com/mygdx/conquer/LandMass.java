package com.mygdx.conquer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class LandMass {
	public static final Color OCCUPIED = Color.GREEN, UNOCCUPIED = Color.RED;
	private final String name;
	private final Texture image;
	private final Resources resources;
	private Color condition;

	public LandMass(final String name, final Texture image,
			final Resources resources) {
		this.name = name;
		this.image = image;
		this.condition = UNOCCUPIED;
		this.resources = resources;
	}

	public String getName() {
		return name;
	}

	public Texture getImage() {
		return image;
	}

	public Color getCondition() {
		return condition;
	}

	public void occupy() {
		condition = OCCUPIED;
	}

	public void unoccupy() {
		condition = UNOCCUPIED;
	}

	public Resources getResources() {
		return resources;
	}
}
