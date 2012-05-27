package com.amikomgamedev.ulartangga.entity;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.Sprite;

import android.util.Log;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Utils;

public class Entity_Mc implements Define
{
	private Sprite spr_Mc;
	private PhysicsHandler handler;

	public int POSISI_MC_START = 1;
	public int Posisi_Mc_Current;
	private int Posisi_Mc_Current_Row;
	private int Posisi_Mc_Max;
	
	public Entity_Mc(Sprite spr)
	{
		spr_Mc = spr;
		handler = new PhysicsHandler(spr_Mc);
		spr_Mc.registerUpdateHandler(handler);

		Posisi_Mc_Current			= POSISI_MC_START;
		Posisi_Mc_Current_Row	= 0;
		Posisi_Mc_Max					= 0;
	}
	
	public void setMove(int valueDice)
	{
		Posisi_Mc_Max = Posisi_Mc_Current + valueDice;
	}
	
	public void updateMove()
	{	
		if(Posisi_Mc_Current < ROW[ROW_END][Posisi_Mc_Current_Row])
		{
			if(Posisi_Mc_Current_Row % 2 == 0) 
			{
				if(spr_Mc.getX() >
					(Posisi_Mc_Current + 1 - ROW[ROW_NEW][Posisi_Mc_Current_Row]) * GAME_MAP_CELL_WIDTH
					+ Utils.getCellCenterX(spr_Mc))
				{
					Posisi_Mc_Current++;
				}
				handler.setVelocity(SPEED, 0);
			}
			else
			{
				if(spr_Mc.getX() + spr_Mc.getWidth() <
					(ROW[ROW_END][Posisi_Mc_Current_Row] - Posisi_Mc_Current) * GAME_MAP_CELL_WIDTH
					- Utils.getCellCenterX(spr_Mc))
				{
					Posisi_Mc_Current++;
				}
				handler.setVelocity(-SPEED, 0);
			}
		} 
		else if(Posisi_Mc_Current == ROW[ROW_END][Posisi_Mc_Current_Row])
		{
			if(spr_Mc.getY() + spr_Mc.getHeight() < 
					Config.GAME_SCREEN_HEIGHT - (Posisi_Mc_Current_Row + 1) * 
					GAME_MAP_CELL_HEIGHT - Game.reg_Img_Informasi_Footer.getHeight()
					- Utils.getCellCenterY(spr_Mc))
			{
				Posisi_Mc_Current++;
				Posisi_Mc_Current_Row++;
			}
			handler.setVelocity(0, -SPEED);
		}
	}
	
	public boolean isMoving()
	{
		return Posisi_Mc_Max > Posisi_Mc_Current ? true : false;
	}
	
	public void stop() 
	{
		handler.setVelocity(0, 0);
	}
	
	public void throwToStart()
	{
		final float distance	= spr_Mc.getX();
		float time			= 3;
		float velocityX	= distance / time;
		float velocity		= (float) (velocityX / Math.cos(Math.toRadians(45)));
		float velocityY	= (float) (velocity * Math.sin(Math.toRadians(45)));// - (980 * State_Gameplay.Second);
		handler.setVelocity(-velocityX, -velocityY);
		Log.d("posisi", ""+spr_Mc.getX());
		if(spr_Mc.getX() <=CAMERA_CENTER_X);
		{
			float mcPosX	= 0;//(-GAME_MAP_CELL_WIDTH);
			float mcPosY	= Config.GAME_SCREEN_HEIGHT - GAME_MAP_CELL_HEIGHT + 
					((GAME_MAP_CELL_HEIGHT - spr_Mc.getHeight()) / 2) - Game.reg_Img_Informasi_Footer.getHeight();
			spr_Mc.setPosition(mcPosX, mcPosY);
			handler.setVelocity(0);
			Posisi_Mc_Current			= POSISI_MC_START;
			Posisi_Mc_Current_Row	= 0;
		}
	}
}