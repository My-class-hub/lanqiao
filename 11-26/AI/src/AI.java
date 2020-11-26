import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;
import java.util.*;

public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas implements Runnable
{
	Image[][] heroImg = new Image[8][3];
	Image bossImg;
	Image currImg;
	int heroX, heroY, bossX, bossY;
	int countFlag;
	int[] gailv;
	Random random;
	Thread thread;

	public MainCanvas(){
		try{
			for(int i = 0; i < heroImg.length; i++){
				for(int j = 0; j < heroImg[i].length; j++){
					heroImg[i][j] = Image.createImage("/sayo"+j+i+".png");
				}
			}
			heroX = 140;
			heroY = 120;
			bossX = 50;
			bossY = 40;
			bossImg = Image.createImage("/kemuri01.png");
			gailv = new int[]{1, 2, 3};
			random = new Random();
			thread = new Thread(this);
			thread.start();
			currImg = heroImg[0][1];
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void run(){
		while(true){
			try{
				Thread.sleep(200);
				int a = random.nextInt(3);
				if(gailv[a] == 1){
					if(bossX < heroX){
						bossX++;
					}
					if(bossX > heroX){
						bossX--;
					}
					if(bossY < heroY){
						bossY++;
					}
					if(bossY > heroY){
						bossY--;
					}
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			repaint();
		}
		
	}
	public void paint(Graphics g){
		g.setColor(250,200,180);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currImg, heroX, heroY, 0);
		g.drawImage(bossImg, bossX, bossY, 0);
	}
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		//LEFT,RIGHT,DOWN,UP
		if(action == LEFT){
			moveHero(1);
			heroX--;
		}
		if(action == RIGHT){
			moveHero(3);
			heroX++;
		}
		if(action == DOWN){
			moveHero(0);
			heroY++;
		}
		if(action == UP){
			moveHero(2);
			heroY--;
		}
		repaint();
	}
	public void keyReleased(int keyCode){
		int action=getGameAction(keyCode);
		if(action == LEFT){
			stopHero(1);
		}
		if(action == RIGHT){
			stopHero(3);
		}
		if(action == DOWN){
			stopHero(0);
		}
		if(action == UP){
			stopHero(2);
		}
		repaint();
	}
	public void keyRepeated(int keyCode){
		int action=getGameAction(keyCode);
		if(action == LEFT){
			moveHero(1);
			heroX--;
		}
		if(action == RIGHT){
			moveHero(3);
			heroX++;
		}
		if(action == DOWN){
			moveHero(0);
			heroY++;
		}
		if(action == UP){
			moveHero(2);
			heroY--;
		}
		repaint();
	}
	public void moveHero(int indexFlag){
		if(countFlag == 0){
			currImg = heroImg[indexFlag][0];
			countFlag++;
		}else{
			currImg = heroImg[indexFlag][2];
			countFlag--;
		}
	}
	public void stopHero(int indexFlag){
		currImg = heroImg[indexFlag][1];
	}
}