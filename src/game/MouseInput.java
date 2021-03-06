package game;


import static java.lang.Math.atan2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Program.STATE;


public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private PlayerObject player;
	
	public MouseInput(Handler h) {
		handler = h;
		try {
			player = (PlayerObject)handler.getObjectAt(0);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void mousePressed(MouseEvent e) {
		
		int button = e.getButton();
		
		if(Program.gameState == STATE.InGame){
			if (button == MouseEvent.BUTTON1) {
				Gun gun = player.getGun();
				player.setAngle(atan2(e.getX() - (player.getX() + 10), e.getY() - (player.getY() + 10)));
				
				//gun.setTickDivider(0);
				
				if(gun.getFullAuto())
					gun.setShooting(true);
				else	
					gun.shoot(player.getAngle());
			}
		}
		else if(Program.gameState == STATE.GameOver){
			if (button == MouseEvent.BUTTON1) {
			}
		}
		else if(Program.gameState == STATE.PauseMenu){
			if (button == MouseEvent.BUTTON1) {
			}
		}
		else if(Program.gameState == STATE.StartMenu){
			if (button == MouseEvent.BUTTON1) {
			}
		}
		else if(Program.gameState == STATE.StoreMenu){
			if (button == MouseEvent.BUTTON1) {
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
		int button = e.getButton();
		
		if(Program.gameState == STATE.InGame) {
			if (button == MouseEvent.BUTTON1) {
				player.getGun().setShooting(false);
			}
		}
		
	}
}
