package com.mygdx.conquer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

public class Globe {
	public static List<LandMass> land;
	public static List<Point> clicked;
	static {
		land = new ArrayList<LandMass>();
		clicked = new ArrayList<Point>();
		final FileHandle source = Gdx.files.internal("maps"), details = Gdx.files
				.internal("resources/details.txt");
		final String[] landDetails = details.readString().split("\n");
		// for (Lands l : Lands.values())
		final String current = Lands.AFRICA.name;
		Resources r = null;
		for (String s : landDetails) {
			if (s.contains(current)) {
				final String[] landResources = s.split("\\s+");
				r = new Resources(Integer.parseInt(landResources[1]),
						Integer.parseInt(landResources[2]),
						Integer.parseInt(landResources[3]),
						Integer.parseInt(landResources[4]));
			}
		}
		land.add(new LandMass(current, new Texture(source.child(current
				+ ".png")), r));
		// land.get(Lands.NORTH_ASIA.ordinal()).occupy();
		land.forEach(l -> {
			final Resources rs = l.getResources();
			System.out.println(rs.getFoodRound() + ", " + rs.getOresRound()
					+ ", " + rs.getWoodRound() + ", " + rs.getEnemy());
		});
	}

	public static LandMass getLand(final Lands l) {
		final int ord = l.ordinal();
		return land.size() > ord ? land.get(ord) : null;
	}

	public static LandMass getLand(final int x, final int y) {
		System.out.println("------------(" + x + ", " + y + ")-----------");
		for (final LandMass l : land) {

			final TextureData td = l.getImage().getTextureData();
			td.prepare();
			// final Color c = new Color(td.consumePixmap().getPixel(x, y));
			final Point p = new Point(x, Gdx.graphics.getHeight() - y);
			final Color c = new Color(td.consumePixmap().getPixel(p.x, p.y));
			// System.out.println(l.getName() + ": " + c.a + ", " + c.r + ", "
			// + c.g + ", " + c.b);
			// clicked.add(p);

			td.disposePixmap();
		}
		return null;
	}

	public static void getMapClicks() {
		for (final LandMass l : land) {
			final TextureData td = l.getImage().getTextureData();
			td.prepare();
			final Pixmap map = td.consumePixmap();
			for (int i = 0; i < map.getWidth(); i++)
				for (int j = 0; j < map.getHeight(); j++)
					if (new Color(map.getPixel(i, j)).a == 0)
						clicked.add(new Point(i, j));

			td.disposePixmap();
		}
	}

	public static List<Point> getClicks() {
		return clicked;
	}

	public static enum Lands {
		AFRICA("Africa"), ARCTIC_REGIONS("Arctic regions"), EAST_ASIA(
				"East asia"), EUROPE("Europe"), INDIA("India"), MIDDLE_EAST(
				"Middle east"), NORTH_AMERICA("North america"), NORTH_ASIA(
				"North asia"), OCEANIA("Oceania"), SOUTH_AMERICA(
				"South america"), SOUTHERN_ASIAN_ISLANDS(
				"Southern asian islands");

		private final String name;

		Lands(final String name) {
			this.name = name;
		}
	}
}
