package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import game.Gun.GUN;
import game.Program.STATE;

public class PlayerObject extends GameObject{
	
	private Gun gunWeilded;
	private Gun gunPrimary;
	private Gun gunSecondary;
	private Gun gunSidearm;
	private LinkedList<Gun> arsenal;
	private double angle;
	private double speed;
	private byte tickDivider;
	private BufferedImage img;
	private int money;
	
	public PlayerObject(double xPos, double yPos, Handler h) {
		super(xPos, yPos, ObjectType.Player, h);
		gunSidearm = new Gun("Titan", GUN.Pistol, 7, 42, 22, false, this, h);
		gunPrimary = new Gun("AR-15", GUN.Rifle, 30, 30, 35, false, this, h); 
		gunSecondary = new Gun("Over-Under", GUN.Shotgun, 2, 10, 40, false, this, h);
		arsenal = new LinkedList<Gun>();
		arsenal.add(gunSidearm);
		arsenal.add(gunPrimary);
		arsenal.add(gunSecondary);
		gunWeilded = gunSidearm;
		tickDivider = 0;
		money = 0;
		speed = 2;
		
		try {
			img = ImageIO.read(new File("res/Player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 20, 20);
	}

	public void tick() {
		
		if(velX != 0 && velY != 0) {
			x += (HALFSQRT2*velX);
			y += (HALFSQRT2*velY);
		}
		
		else{
			x += velX;
			y += velY;
		}
		
		x = Program.clamp(x, 0, Program.WIDTH-26);
		y = Program.clamp(y, 0, Program.HEIGHT-48);
		
		if (tickDivider%8 == 0) {
			detectCollision();
			gunWeilded.tick();
			
		}
		tickDivider++;
	}
	
	public void detectCollision()
	{
		for(int i = 2; i < handler.getObjList().size(); i++) {
			GameObject obj = handler.getObjectAt(i);
			if(obj.getType() == ObjectType.Zombie) {
				ZombieObject zomb = (ZombieObject)obj;
				if(zomb.getBounds().intersects(this.getBounds())) {
					Program.gameState = STATE.GameOver;
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		g2d.setColor(Color.LIGHT_GRAY);
		g2d.rotate(-angle, x+10, y+10);
		g2d.fillRect((int)x, (int)y, 20, 20);
		//g2d.drawImage(img, (int)x, (int)y, null);
		g2d.rotate(angle, x+10, y+10);
	}

	public Gun getGun() {return gunWeilded;}
	public LinkedList<Gun> getArsenal() {return arsenal;}
	public double getAngle() {return angle;}
	public void setAngle(double a) {angle = a;}
	public int getMoney() {return money;}
	public void setMoney(int m) {money = m;}
	public double getSpeed() {return speed;}
	public void setSpeed(double s) {speed = s;}
	
	public void switchToPrimary() {
		if(gunWeilded != gunPrimary) 
			gunWeilded.swapGun();
			gunWeilded = gunPrimary;
	}
	public void switchToSecondary() {
		if(gunWeilded != gunSecondary)
			gunWeilded.swapGun();
			gunWeilded = gunSecondary;
	}
	public void switchToSidearm() {
		if(gunWeilded != gunSidearm)
			gunWeilded.swapGun();
			gunWeilded = gunSidearm;
	}
	public void resetAllAmmo() {
		for(int i = 0; i < arsenal.size(); i++) {
			arsenal.get(i).resetAmmo();
		}
	}


}
