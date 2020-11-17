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
	Image downImg, leftImg, upImg, rightImg, currentImg, leftZoreImg, leftOneImg;
	int x, y, leftCount;
	//初始化
	public MainCanvas(){
		try{
			x = 110;
			y = 140;
			downImg = Image.createImage("/sayo10.png");
			leftImg = Image.createImage("/sayo12.png");
			upImg = Image.createImage("/sayo14.png");
			rightImg = Image.createImage("/sayo16.png");
			leftZoreImg = Image.createImage("/sayo02.png");
			leftOneImg = Image.createImage("/sayo22.png");
			currentImg = downImg;
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
		//System.out.println("x1"+x);
	}
	public void keyPressed(int keyCode){
		int action = getGameAction(keyCode);
		if(action == LEFT){
			if(leftCount == 0){
				currentImg = leftImg;
				leftCount = 1;
			}else if(leftCount == 1){
				currentImg = leftZoreImg;
				leftCount = 2;
			}else{
				currentImg = leftOneImg;
				leftCount = 1;
			}
			if(x <= 0){
				x = 0;
			}else{
				x--;
			}
			//System.out.println("x2"+x);
			repaint();
		}
		else if(action == UP){
			leftCount = 0;
			currentImg = upImg;
			if(y <= 0){
				y = 0;
			}else{
				y--;
			}
			repaint();
		}
		else if(action == RIGHT){
			leftCount = 0;
			currentImg = rightImg;
			x++;
			repaint();
		}
		else if(action == DOWN){
			leftCount = 0;
			currentImg = downImg;
			y++;
			repaint();
		}
		else{
//			new throw Exception("按键不属于对应的keyCode无法转向");
			System.out.println("yongbuliao");
		}
	}
}