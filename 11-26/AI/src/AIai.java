import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;

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
class MainCanvas extends Canvas
{
	//Ò»Î¬Êý×é
	Image[] leftImg = new Image[3];
	Image[] rightImg = new Image[3];
	Image[] downImg = new Image[3];
	Image[] upImg = new Image[3];
	Image currImg;

	int heroX, heroY;
	int flag;

	public MainCanvas(){
		try{
			for(int i = 0; i < leftImg.length; i++){
				leftImg[i] = Image.createImage("/sayo"+i+"2.png");
			}
			for(int i = 0; i < rightImg.length; i++){
				rightImg[i] = Image.createImage("/sayo"+i+"6.png");
			}
			for(int i = 0; i < downImg.length; i++){
				downImg[i] = Image.createImage("/sayo"+i+"0.png");
			}
			for(int i = 0; i < upImg.length; i++){
				upImg[i] = Image.createImage("/sayo"+i+"4.png");
			}
			currImg = downImg[1];
			heroX = 140;
			heroY = 120;
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		g.setColor(250,200,180);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currImg,heroX,heroY,0);
	}
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		if(action == LEFT){
			moveHero(leftImg);
			heroX--;
		}
		if(action == RIGHT){
			moveHero(rightImg);
			heroX++;
		}
		if(action == DOWN){
			moveHero(downImg);
			heroY++;
		}
		if(action == UP){
			moveHero(upImg);
			heroY--;
		}
		repaint();
	}
	public void moveHero(Image[] heroImg){
		if(flag == 0){
			currImg = heroImg[0];
			flag++;
		}else{
			currImg = heroImg[2];
			flag--;
		}
	}
}