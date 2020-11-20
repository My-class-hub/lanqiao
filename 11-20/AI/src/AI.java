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
	//字段，声明成员变量
	Image[][] img = new Image[4][3];
	Image currentImg;
	int x, y;
	int newCount;
	boolean flag;
	//初始化
	public MainCanvas(){
		try{
			x = 110;
			y = 140;
			int count = 0;
			for(int i = 2; i <= 8; i += 2){
				for(int j = 0; j < 3; j++){
					img[count][j] = Image.createImage("/sayo" + j + (i - 2) + ".png");
				}
				count++;
			}
			currentImg = img[0][1];
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	//这个方法每次需要刷新图形都会自动重启
	public void paint(Graphics g){
		g.setColor(0,0,0);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,x,y,0);
	}
	public void keyPressed(int keyCode){
		int action = getGameAction(keyCode);
		if(action == LEFT){
			moveHero(1);
		}
		if(action == UP){
			moveHero(2);
		}
		if(action == RIGHT){
			moveHero(3);
		}
		if(action == DOWN){
			moveHero(0);
		}
	}
	public void keyReleased(int keyCode){
		int action = getGameAction(keyCode);
		if(action == LEFT){
			stopHero(1);
		}
		if(action == UP){
			stopHero(2);
		}
		if(action == RIGHT){
			stopHero(3);
		}
		if(action == DOWN){
			stopHero(0);
		}
	}
	public void keyRepeated(int keyCode){
		int action = getGameAction(keyCode);
		if(action == LEFT){
			moveHero(1);
		}
		if(action == UP){
			moveHero(2);
		}
		if(action == RIGHT){
			moveHero(3);
		}
		if(action == DOWN){
			moveHero(0);
		}
	}
//	public void keyPressed(int keyCode){
//		int action = getGameAction(keyCode);
//		if(action == LEFT){
//			removeLeftImg();
//		}
//		if(action == UP){
//			removeUpImg();
//		}
//		if(action == RIGHT){
//			removeRightImg();
//		}
//		if(action == DOWN){
//			removeDownImg();
//		}
//	}
//	public void removeLeftImg(){
//		if(leftCount == 0){
//			currentImg = img[1][0];
//			leftCount++;
//		}else{
//			currentImg = img[1][2];
//			leftCount--;
//		}
//		if(x > 0){
//			x--;
//		}
//		repaint();
//	}
//	public void removeUpImg(){
//		if(upCount == 0){
//			currentImg = img[2][0];
//			upCount++;
//		}else{
//			currentImg = img[2][2];
//			upCount--;
//		}
//		if(y > 0){
//			y--;
//		}
//		repaint();
//	}
//	public void removeRightImg(){
//		if(rightCount == 0){
//			currentImg = img[3][0];
//			rightCount++;
//		}else{
//			currentImg = img[3][2];
//			rightCount--;
//		}
//		if(x < 220){
//			x++;
//		}
//		repaint();
//	}
//	public void removeDownImg(){
//		if(downCount == 0){
//			currentImg = img[0][0];
//			leftCount++;
//		}else{
//			currentImg = img[0][2];
//			downCount--;
//		}
//		if(y < 265){
//			y++;
//		}
//		repaint();
//	}
	public void moveHero(int index){
		if(newCount == 0){
			currentImg = img[index][0];
			newCount++;
		}else{
			currentImg = img[index][2];
			newCount--;
		}
		if(index == 0){
			if(y < 265){
				y++;
			}
		}else if(index == 1){
			if(x > 0){
				x--;
			}
		}else if(index == 2){
			if(y > 0){
				y--;
			}
		}else{
			if(x < 220){
				x++;
			}
		}
		repaint();
	}
	public void stopHero(int index){
		currentImg = img[index][1];
		repaint();
	}
}