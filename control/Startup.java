package control;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import model.Area;
import model.AreaList;

public class Startup extends Applet implements Runnable, MouseMotionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    int areaCounter = 0;
    Thread updateThread = null;
    boolean updatePause;
    int areasTotal = 100;
    int areaRows = 10;
    int areaCollumns = areasTotal / areaRows;
    private AreaList areaList;
    int areaSize = 20;
    int updateInterval = 6000 / areasTotal;
    double colorR = 0;
    double colorG = 0;
    double colorB = 0;
    Graphics bufferGraphics;
    Image offscreen;
    Dimension screenDimensions;
    public void init() {
	setBackground(Color.white);
	addMouseMotionListener(this);
	areaList = new AreaList();
	Random randomizer = new Random(1L); // remember longs are appended with L
	int randomInt = randomizer.nextInt();
	randomInt = Math.abs(randomInt);
	screenDimensions = getSize();
	addMouseMotionListener(this);
	setBackground(Color.black);
	offscreen = createImage(screenDimensions.width, screenDimensions.height);
	bufferGraphics = offscreen.getGraphics();
	int x = 0;
	double TempNyY = 0;
	int newAreaY = 0;
	int newAreaX = 0;
	int colorActive = -1;
	for (x = 0; x <= areasTotal; x++) {
	    int TempTemp;
	    colorActive = colorActive + 1;
	    TempNyY = (double) x / (double) areaRows;
	    TempNyY = (TempNyY + 1.0);
	    newAreaY = (int) (TempNyY - 1);
	    newAreaX = (x - ((newAreaY - 1) * 10));
	    newAreaY = newAreaY * areaSize;
	    newAreaX = newAreaX * areaSize;
	    newAreaX = newAreaX - 200;
	    if (colorActive > 5) {
		colorActive = 0;
	    }
	    TempTemp = (-10) + (int) (Math.random() * 30);
	    Area ny2 = new Area(x, newAreaX, newAreaY, "white", TempTemp);
	    areaList.newArea(ny2);
	}
    }
    public void destroy() {}
    public void start() {
	if (updateThread == null) {
	    updateThread = new Thread(this);
	    updatePause = false;
	    updateThread.start();
	} else {
	    if (updatePause) {
		updatePause = false;
		synchronized (this) {
		    notify();
		}
	    }
	}
    }
    public void stop() {
	updatePause = true;
    }
    public void run() {
	try {
	    while (true) {
		++areaCounter;
		if (areaCounter == areasTotal) {
		    areaCounter = 0;
		}
		if (updatePause) {
		    synchronized (this) {
			while (updatePause) {
			    wait();
			}
		    }
		}
		// SpawnTimer = SpawnTimer + 1;
		// finn gammmel temp
		// finn ut varmere\kaldere
		// plukk random rute og fix
		int randomArea = 0 + (int) (Math.random() * areasTotal);
		if (randomArea > areasTotal) { // 401
		    randomArea = 0; // 0
		}
		if (randomArea <= areasTotal) {
		    if ((randomArea + 5) > areasTotal) {
			areaList.SetTemp(randomArea, (int) (areaList.GetTemp(randomArea) + (areaList.GetTemp(0 + 5) / 20.0)));
		    } else {
			areaList.SetTemp(randomArea, ((int) (areaList.GetTemp(randomArea) + (areaList.GetTemp(randomArea + 5) / 20.0))));
		    }
		    if ((randomArea + 1) > areasTotal) {
			areaList.SetTemp(randomArea, (int) (areaList.GetTemp(randomArea) + (areaList.GetTemp(0 + 1) / 20.0)));
		    } else {
			areaList.SetTemp(randomArea, (int) (areaList.GetTemp(randomArea) + (areaList.GetTemp(randomArea + 1) / 20.0)));
		    }
		    if ((randomArea - 5) < 0) {
			areaList.SetTemp(randomArea, (int) (areaList.GetTemp(randomArea) + (areaList.GetTemp(areasTotal - 5) / 20.0)));
		    } else {
			areaList.SetTemp(randomArea, (int) (areaList.GetTemp(randomArea) + (areaList.GetTemp(randomArea - 5) / 20.0)));
		    }
		    if ((randomArea - 1) < 0) {
			areaList.SetTemp(randomArea, (int) (areaList.GetTemp(randomArea) + (areaList.GetTemp(areasTotal - 1) / 20.0)));
		    } else {
			areaList.SetTemp(randomArea, (int) (areaList.GetTemp(randomArea) + (areaList.GetTemp(randomArea - 1) / 20.0)));
		    }
		    if (areaList.GetTemp(randomArea) < -10) {
			areaList.SetTemp(randomArea, -10);
		    }
		    if (areaList.GetTemp(randomArea) > 30) {
			areaList.SetTemp(randomArea, 30);
		    }
		}
		repaint();
		updateThread.sleep(updateInterval); // interval given in milliseconds
	    }
	} catch (InterruptedException e) {}
    }
    public void paint(Graphics g) {
	bufferGraphics.clearRect(0, 0, screenDimensions.width, screenDimensions.width);
	bufferGraphics.setColor(Color.red);
	g.drawImage(offscreen, 0, 0, this);
	int x = 0;
	for (x = 0; x <= (areasTotal - 1); x++) {
	    int GreenMin = 0;
	    int GreenMax = 20;
	    int RedMin = 20;
	    int RedMax = 30;
	    int BlueMax = -5;
	    int BlueMin = -10;
	    int RedDiff = 0;
	    int GreenDiff = 0;
	    int BlueDiff = 0;
	    double ToMax = 0;
	    if (areaList.GetTemp(x) >= 10) {
		RedMax = 255;
		RedMin = 0;
		GreenMax = 127;
		GreenMin = 255;
		BlueMax = 0;
		BlueMin = 0;
		ToMax = (double) areaList.GetTemp(x) / 20.0;
		RedDiff = (RedMax - RedMin);
		RedDiff = RedMin + (int) (ToMax * RedDiff);
		GreenDiff = (GreenMax - GreenMin);
		GreenDiff = GreenMin + (int) (ToMax * GreenDiff);
		BlueDiff = (BlueMax - BlueMin);
		BlueDiff = BlueMin + (int) (ToMax * BlueDiff);
		colorR = RedDiff;
		colorG = GreenDiff;
		colorB = BlueDiff;
	    } else if (areaList.GetTemp(x) >= 0) {
		RedMax = 0;
		RedMin = 255;
		GreenMax = 255;
		GreenMin = 255;
		BlueMax = 0;
		BlueMin = 255;
		ToMax = (double) areaList.GetTemp(x) / 10.0;
		RedDiff = (RedMax - RedMin);
		RedDiff = RedMin + (int) (ToMax * RedDiff);
		GreenDiff = (GreenMax - GreenMin);
		GreenDiff = GreenMin + (int) (ToMax * GreenDiff);
		BlueDiff = (BlueMax - BlueMin);
		BlueDiff = BlueMin + (int) (ToMax * BlueDiff);
		colorR = RedDiff;
		colorG = GreenDiff;
		colorB = BlueDiff;
	    } else if (areaList.GetTemp(x) <= 0) {
		RedMax = 255;
		RedMin = 0;
		GreenMax = 255;
		GreenMin = 127;
		BlueMax = 255;
		BlueMin = 255;
		ToMax = (double) areaList.GetTemp(x) / -10.0;
		RedDiff = RedMax - RedMin;
		GreenDiff = GreenMax - GreenMin;
		BlueDiff = BlueMax - BlueMin;
		colorR = (int) (ToMax * RedDiff) + RedMin;
		colorG = (int) (ToMax * GreenDiff) + GreenMin;
		colorB = (int) (ToMax * BlueDiff) + BlueMin;
	    }
	    if (colorR < 0) {
		colorR = 0;
	    } else if (colorR > 255) {
		colorR = 255;
	    }
	    if (colorG < 0) {
		colorG = 0;
	    } else if (colorG > 255) {
		colorG = 255;
	    }
	    if (colorB < 0) {
		colorB = 0;
	    } else if (colorB > 255) {
		colorB = 255;
	    }
	    Color mixColor = new Color((int) colorR, (int) colorG, (int) colorB);
	    bufferGraphics.setColor(mixColor);
	    bufferGraphics.fillRect(areaList.GetCoordX(x), areaList.GetCoordY(x), areaSize, areaSize);
	}
	g.drawImage(offscreen, 0, 0, this);
    }
    public void mouseMoved(MouseEvent evt) {}
    public void mouseDragged(MouseEvent evt) {}
    public void mouseClicked(MouseEvent me) {
	int curX = me.getX();
	int curY = me.getY();
	repaint();
    }
    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {
	repaint();
    }
    public void mouseExited(MouseEvent me) {
	repaint();
    }
}