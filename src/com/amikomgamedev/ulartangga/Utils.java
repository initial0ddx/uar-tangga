package com.amikomgamedev.ulartangga;

import java.util.Random;

import org.anddev.andengine.entity.sprite.Sprite;

public class Utils
{
	private static float random;
	
	public static void setRandomValue()
	{
		float range = 6;
		random = range * new Random().nextFloat();
	}
	
	public static int getRandomValuie()
	{
		return (int) (1 + random);
	}

	public static float getCellCenterX(Sprite sprite)
	{
		return (Define.GAME_MAP_CELL_WIDTH - sprite.getWidth()) / 2;
	}
	public static float getCellCenterY(Sprite sprite)
	{
		return (Define.GAME_MAP_CELL_HEIGHT - sprite.getHeight()) / 2;
	}
}