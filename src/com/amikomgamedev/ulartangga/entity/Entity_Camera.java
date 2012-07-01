package com.amikomgamedev.ulartangga.entity;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.states.State_Gameplay;

public class Entity_Camera implements Define
{
	public static void moveCamera(float pDistanceX, float pDistanceY)
	{
		if(pDistanceX > 0)
		{
			if(State_Gameplay.camera.getMinX() <= Game.spr_Img_Map.getX())
			{
				State_Gameplay.camera.setCenter(CAMERA_CENTER_MAP_X1, State_Gameplay.camera.getCenterY());
			}
			else
			{
				State_Gameplay.camera.offsetCenter(-pDistanceX, 0);
			}
		}

		if(pDistanceX < 0)
		{
			if(State_Gameplay.camera.getMaxX() >= Game.spr_Img_Map.getWidth())
			{
				State_Gameplay.camera.setCenter(CAMERA_CENTER_MAP_X2, State_Gameplay.camera.getCenterY());
			}
			else
			{
				State_Gameplay.camera.offsetCenter(-pDistanceX, 0);
			}
		}
		
		if(pDistanceY > 0)
		{
			if(State_Gameplay.camera.getMinY() <= Config.GAME_SCREEN_HEIGHT - Game.spr_Img_Map.getHeight()
					- Game.reg_Img_Informasi_Header.getHeight() - Game.reg_Img_Informasi_Footer.getHeight())
			{
				State_Gameplay.camera.setCenter(State_Gameplay.camera.getCenterX(), CAMERA_CENTER_MAP_Y1);
			}
			else
			{
				State_Gameplay.camera.offsetCenter(0, -pDistanceY);
			}
		}

		if(pDistanceY < 0)
		{
			if(State_Gameplay.camera.getMaxY() >= Config.GAME_SCREEN_HEIGHT)
			{
				State_Gameplay.camera.setCenter(State_Gameplay.camera.getCenterX(), CAMERA_CENTER_MAP_Y2);
			}
			else
			{
				State_Gameplay.camera.offsetCenter(0, -pDistanceY);
			}
		}
	}
	
	public static void autoMoveCamera(int player)
	{
		State_Gameplay.moveCamera = false;
		State_Gameplay.curPosition[player].setText("" +State_Gameplay.mc[player].Posisi_Mc_Current);
		
		if(Game.spr_MC[player].getX() - GAME_MAP_CELL_WIDTH <= Game.spr_Img_Map.getX())
		{
			State_Gameplay.camera.setCenter(CAMERA_CENTER_MAP_X1, State_Gameplay.camera.getCenterY());
		}
		else if(Game.spr_MC[player].getX() - GAME_MAP_CELL_WIDTH <= State_Gameplay.camera.getMinX())
		{
		
			State_Gameplay.camera.setCenter(Game.spr_MC[player].getX() - GAME_MAP_CELL_WIDTH + 
					CAMERA_CENTER_X, 
					State_Gameplay.camera.getCenterY());
		}
		else if(Game.spr_MC[player].getX() + Game.spr_MC[player].getWidth() + GAME_MAP_CELL_WIDTH >= 
				Game.spr_Img_Map.getX() + Game.spr_Img_Map.getWidth())
		{
			State_Gameplay.camera.setCenter(CAMERA_CENTER_MAP_X2, State_Gameplay.camera.getCenterY());
		}
		else if(Game.spr_MC[player].getX() + Game.spr_MC[player].getWidth() + GAME_MAP_CELL_WIDTH >= 
				State_Gameplay.camera.getMaxX())
		{
		
			State_Gameplay.camera.setCenter(Game.spr_MC[player].getX() + Game.spr_MC[player].getWidth() + 
					GAME_MAP_CELL_WIDTH - CAMERA_CENTER_X, 
					State_Gameplay.camera.getCenterY());
		}

		if(Game.spr_MC[player].getY() - GAME_MAP_CELL_HEIGHT <= Config.GAME_SCREEN_HEIGHT - Game.spr_Img_Map.getHeight()
				- Game.reg_Img_Informasi_Header.getHeight() - Game.reg_Img_Informasi_Footer.getHeight())
		{
			State_Gameplay.camera.setCenter(State_Gameplay.camera.getCenterX(), CAMERA_CENTER_MAP_Y1);
		}
		else if(Game.spr_MC[player].getY() - GAME_MAP_CELL_HEIGHT <= State_Gameplay.camera.getMinY() + Game.reg_Img_Informasi_Header.getHeight())
		{
		
			State_Gameplay.camera.setCenter
			(
					State_Gameplay.camera.getCenterX(),
					Game.spr_MC[player].getY() - GAME_MAP_CELL_HEIGHT + CAMERA_CENTER_Y - Game.reg_Img_Informasi_Header.getHeight()
			);
		}
		else if(Game.spr_MC[player].getY() + Game.spr_MC[player].getHeight() + GAME_MAP_CELL_HEIGHT >= 
				Game.spr_Img_Map.getY() + Game.spr_Img_Map.getHeight())
		{
			State_Gameplay.camera.setCenter(State_Gameplay.camera.getCenterX(), CAMERA_CENTER_MAP_Y2);
		}
		else if(Game.spr_MC[player].getY() + Game.spr_MC[player].getHeight() + GAME_MAP_CELL_HEIGHT >= 
				State_Gameplay.camera.getMaxY())
		{
		
			State_Gameplay.camera.setCenter
			(
					State_Gameplay.camera.getCenterX(),
					Game.spr_MC[player].getY() + Game.spr_MC[player].getHeight() + 
					GAME_MAP_CELL_HEIGHT - CAMERA_CENTER_Y + Game.spr_Img_Informasi_Footer.getHeight()
			);
		}
	}
}
