package model;
public class Area {
    private int ID, CoordX, CoordY, Temp;
    private String Fargen;
    Area neste;
    public Area(int E, int A, int B, String F, int H) {
	ID = E;
	CoordX = A;
	CoordY = B;
	Fargen = F;
	Temp = H;
	neste = null;
    }
    public int GetID() {
	return ID;
    }
    public int GetCoordX() {
	return CoordX;
    }
    public int GetCoordY() {
	return CoordY;
    }
    public String GetFargen() {
	return Fargen;
    }
    public int GetTemp() {
	return Temp;
    }
    // ----------------------
    public void SendCoordX(int A) {
	CoordX = A;
    }
    public void SendCoordY(int B) {
	CoordY = B;
    }
    public void SendFargen(String F) {
	Fargen = F;
    }
    public void SendTemp(int G) {
	Temp = G;
    }
}
