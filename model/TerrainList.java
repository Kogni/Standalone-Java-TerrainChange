package model;


public class TerrainList {
	private Terrain første, siste;

	  public TerrainList()
	  {
	    første = null;
	  }

	 public void settInn( Terrain ny )
	  {
	    if ( første == null ) {
	    	første = siste = ny;
	    }
	    else
	    {
	    	siste.neste = ny;
	    	siste = ny;
	    }
	  }
	 
	 public int GetCoordX(int E)
	 {
		 Terrain objekt = første;

		   while ( objekt != null )
		   {
			   int ID = objekt.GetID();
			   if (ID == E) {
				   return objekt.GetCoordX();
			   }
		     objekt = objekt.neste;
		   }
		   return -10;
	 }
	 
	 public void SendCoordX(int E, int A) {
		 Terrain objekt = første;

		   while ( objekt != null )
		   {
			   int ID = objekt.GetID();
			   if (ID == E) {
				   objekt.SendCoordX(A);
			   }
		     objekt = objekt.neste;
		   }
	 }
	 
	 public int GetCoordY(int E)
	 {
		 Terrain objekt = første;

		   while ( objekt != null )
		   {
			   int ID = objekt.GetID();
			   if (ID == E) {
				   return objekt.GetCoordY();
			   }
		     objekt = objekt.neste;
		   }
		   return -10;
	 }
	 
	 public void SendCoordY(int E, int B) {
		   Terrain objekt = første;

		   while ( objekt != null )
		   {
			   int ID = objekt.GetID();
			   if (ID == E) {
				   objekt.SendCoordY(B);
			   }
		     objekt = objekt.neste;
		   }
	 }
	 
	 public String GetFargen(int E)
	 {
		 Terrain objekt = første;

		   while ( objekt != null )
		   {
			   int ID = objekt.GetID();
			   if (ID == E) {
				   return objekt.GetFargen();
			   }
		     objekt = objekt.neste;
		   }
		   return "white";
	 }
	 
	 public void SendFargen(int E, String F) {
		   Terrain objekt = første;

		   while ( objekt != null )
		   {
			   int ID = objekt.GetID();
			   if (ID == E) {
				   objekt.SendFargen(F);
			   }
		     objekt = objekt.neste;
		   }
	 }
	 
	 public int GetTemp(int E)
	 {
		 Terrain objekt = første;

		   while ( objekt != null )
		   {
			   int ID = objekt.GetID();
			   if (ID == E) {
				   return objekt.GetTemp();
			   }
		     objekt = objekt.neste;
		   }
		   return 0;
	 }
	 
	 public void SendTemp(int E, int G) {
		   Terrain objekt = første;

		   while ( objekt != null )
		   {
			   int ID = objekt.GetID();
			   if (ID == E) {
				   objekt.SendTemp(G);
			   }
		     objekt = objekt.neste;
		   }
	 }
}
