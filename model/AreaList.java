package model;
public class AreaList {
    private Area første, siste;
    public AreaList() {
	første = null;
    }
    public void newArea(Area ny) {
	if (første == null) {
	    første = siste = ny;
	} else {
	    siste.neste = ny;
	    siste = ny;
	}
    }
    public int GetCoordX(int E) {
	Area objekt = første;
	while (objekt != null) {
	    int ID = objekt.GetID();
	    if (ID == E) {
		return objekt.GetCoordX();
	    }
	    objekt = objekt.neste;
	}
	return -10;
    }
    public void SendCoordX(int E, int A) {
	Area objekt = første;
	while (objekt != null) {
	    int ID = objekt.GetID();
	    if (ID == E) {
		objekt.SendCoordX(A);
	    }
	    objekt = objekt.neste;
	}
    }
    public int GetCoordY(int E) {
	Area objekt = første;
	while (objekt != null) {
	    int ID = objekt.GetID();
	    if (ID == E) {
		return objekt.GetCoordY();
	    }
	    objekt = objekt.neste;
	}
	return -10;
    }
    public void SendCoordY(int E, int B) {
	Area objekt = første;
	while (objekt != null) {
	    int ID = objekt.GetID();
	    if (ID == E) {
		objekt.SendCoordY(B);
	    }
	    objekt = objekt.neste;
	}
    }
    public String GetFargen(int E) {
	Area objekt = første;
	while (objekt != null) {
	    int ID = objekt.GetID();
	    if (ID == E) {
		return objekt.GetFargen();
	    }
	    objekt = objekt.neste;
	}
	return "white";
    }
    public void SendFargen(int E, String F) {
	Area objekt = første;
	while (objekt != null) {
	    int ID = objekt.GetID();
	    if (ID == E) {
		objekt.SendFargen(F);
	    }
	    objekt = objekt.neste;
	}
    }
    public int GetTemp(int E) {
	Area objekt = første;
	while (objekt != null) {
	    int ID = objekt.GetID();
	    if (ID == E) {
		return objekt.GetTemp();
	    }
	    objekt = objekt.neste;
	}
	return 0;
    }
    public void SetTemp(int E, int G) {
	Area objekt = første;
	while (objekt != null) {
	    int ID = objekt.GetID();
	    if (ID == E) {
		objekt.SendTemp(G);
	    }
	    objekt = objekt.neste;
	}
    }
}
