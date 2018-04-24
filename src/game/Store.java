package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import static java.lang.Math.round;

public class Store extends MouseAdapter{

	private PlayerObject player;
	private String str;
	private Handler handler;
	private int mX, mY;
	private int buttonX [] = {100, 260, 420, 580};
	private int buttonY [] = {100, 100, 100, 100};
	private int buttonW;
	private int buttonH;
	
	public Store(Handler h) {
		handler = h;
		try {
			player = (PlayerObject)handler.getObjectAt(0);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		str = new String();
		
		buttonW = 120;
		buttonH = 80;
	}
	
	public void mousePressed(MouseEvent e) {
		mX = e.getX();
		mY = e.getY();
		if(inBounds(buttonX[0], buttonY[0], buttonW, buttonH))
			upgradeCapacity("AR-15", 30, 300);
		else if(inBounds(buttonX[1], buttonY[1], buttonW, buttonH))
			upgradeCapacity("Over-Under", 8, 250);
		else if(inBounds(buttonX[2], buttonY[2], buttonW, buttonH))
			upgradeCapacity("Titan", 14, 200);
		else if(inBounds(buttonX[3], buttonY[3], buttonW, buttonH))
			upgradeMagSize("AR-15", 10, 500);
	}
	
	public void tick() {
		//player.
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 1, 24));
		g.drawString("MONEY: " + player.getMoney(), 340, 50);
		g.drawString("PRESS SPACE TO COMMENCE NEXT LEVEL", 120, 450);
		g.setFont(new Font("Arial", 1, 12));
		g.draw3DRect(buttonX[0], buttonY[0], buttonW, buttonH, true);
		g.draw3DRect(buttonX[1], buttonY[1], buttonW, buttonH, true);
		g.draw3DRect(buttonX[2], buttonY[2], buttonW, buttonH, true);
		g.draw3DRect(buttonX[3], buttonY[3], buttonW, buttonH, true);

		g.setFont(new Font("Arial", 1, 12));
		g.drawString("Increase Capacity", 110, 120);
		g.drawString("AR-15", 120, 140);
		g.drawString("$300", 120, 160);
		
		g.drawString("Increase Capacity", 270, 120);
		g.drawString("Over-Under", 280, 140);
		g.drawString("$250", 280, 160);
		
		g.drawString("Increase Capacity", 430, 120);
		g.drawString("Titan", 440, 140);
		g.drawString("$200", 440, 160);
		
		g.drawString("Increase Mag Size", 590, 120);
		g.drawString("AR-15", 600, 140);
		g.drawString("$500", 600, 160);
	}
	
	public boolean inBounds(int x, int y, int w, int h) {
		if(mX > x && mX < x+w) {
			if(mY > y && mY < y+h) {
				return true;
			}
		}
		return false;
	}
	
	public void upgradeCapacity(String gunName, int ammo, int money) {
		
		LinkedList<Gun> arsenal = player.getArsenal();
		Gun gun = null;
		for(int i = 0; i < arsenal.size(); i++) {
			if(arsenal.get(i).getName().equals(gunName)) {
				gun = arsenal.get(i);
				break;
			}
		}
		System.out.println(gun.getName());
		int cap = gun.getAmmoCapacity();
		if(player.getMoney() >= money) {
			gun.setAmmoCapacity(ammo+cap);
			player.setMoney(player.getMoney() - money);
		}
	}
	
	public void upgradeMagSize(String gunName, int ammo, int money) {
		
		LinkedList<Gun> arsenal = player.getArsenal();
		Gun gun = null;
		for(int i = 0; i < arsenal.size(); i++) {
			if(arsenal.get(i).getName().equals(gunName)) {
				gun = arsenal.get(i);
				break;
			}
		}
		
		if(player.getMoney() >= money) {
			int mag = gun.getMagSize();
			int cap = gun.getAmmoCapacity();
			int m = player.getMoney();
			gun.setMagSize(mag + ammo);
			gun.setAmmoCapacity(cap + ammo);
			player.setMoney(m - money);
		}		
	}

}