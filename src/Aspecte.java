public class Aspecte implements Comparable<Aspecte>{

 //Entitat e1, e2;
 int hMomIni, hMomFi, mMomIni, mMomFi, sMomIni, sMomFi = 0;
 String tipus, nom1, nom2, casa1, casaAnt1, casa2, casaAnt2 = "";
 String signe1 = "";
 String signe2 = "";
 String[] regent1 = new String[3];
 String[] regent2 = new String[3];
 boolean ant1 = false;
 
 public Aspecte (Entitat e1, boolean ant1, Entitat e2, int i, int hMom, int mMom, int sMom, String tipus) {
	 // lent - lent
	 /*
	 this.e1 = e1;
	 this.e2 = e2;
	 */
	 this.ant1 = ant1;
	 setMomIni(hMom, mMom, sMom);
	 this.nom1 = e1.getNom();
	 if (ant1 == true) {
		 this.nom1 = "ant. " + this.nom1;
	 }
	 this.nom2 = e2.getNom();
	 this.casa1 = e1.getCasa();
	 this.casaAnt1 = e1.getCasaAnt();
	 this.signe1 = e1.getSigne(i);
	 this.tipus = tipus;
	 this.casa2 = e2.getCasa();
	 this.casaAnt2 = e2.getCasaAnt();
	 this.signe2 = e2.getSigne(i);
 }
 
 /*
 public Aspecte (Entitat e1, boolean ant1, Entitat e2, int hMom, int mMom, int sMom, String casa1, String casaAnt1,
		String signe1, String regent1a, String regent1b, String regent1c, String signe2, String tipus) {
	 // lent - casa
	 this.e1 = e1;
	 this.e2 = e2;
	 this.ant1 = ant1;
	 setMomIni(hMom, mMom, sMom);
	 this.nom1 = e1.getNom();
	 this.nom2 = e2.getNom();
	 this.casa1 = casa1;
	 this.casaAnt1 = casaAnt1;
	 this.signe1 = signe1;
	 this.signe2 = signe2;
	 char[] angle = regent1.toCharArray();
	 String cadena = "";
	 int x = 0;
	 for (int i = 0; i <= angle.length - 1; i++) {
		 if (!angle[i].equals("")) {
			 if (angle[i].equals("/")) {
				 this.regent1[x] = cadena;
				 x += 1;
			 }
			 else
			 {
				 cadena += angle[i];
			 }
		 }
	 }
	 this.tipus = tipus;
	 
     if (ant1 == true) {
		 this.nom1 = "ant. " + this.nom1;
	 }
 }
 
 public Aspecte (Entitat e1, boolean ant1, Entitat e2, int hMom, int mMom, int sMom, String casa1, String casaAnt1,
		String casa2, String casaAnt2, String signe2, String tipus) {
	 // lent - part
	 this.e1 = e1;
	 this.e2 = e2;
	 this.ant1 = ant1;
	 setMomIni(hMom, mMom, sMom);
	 this.nom1 = e1.getNom();
	 this.nom2 = e2.getNom();
	 this.casa1 = casa1;
	 this.casaAnt1 = casaAnt1;
	 this.signe1 = signe1;
	 char[] angle = regent1.toCharArray();
	 String cadena = "";
	 int x = 0;
	 for (int i = 0; i <= angle.length - 1; i++) {
		 if (!angle[i].equals("")) {
			 if (angle[i].equals("/")) {
				 this.regent1[x] = cadena;
				 x += 1;
			 }
			 else
			 {
				 cadena += angle[i];
			 }
		 }
	 }
	 this.casa2 = casa2;
	 this.casaAnt2 = casaAnt2;
	 this.signe2 = signe2;
	 this.tipus = tipus;
	 
	 if (ant1 == true) {
		 this.nom1 = "ant. " + this.nom1;
	 }
 }
 
 public Aspecte (Entitat e1, boolean ant1, Entitat e2, int hMom, int mMom, int sMom, String casa1, String casaAnt1,
		String signe1, String signe2, String tipus) {
	 // part - casa
	 this.e1 = e1;
	 this.e2 = e2;
	 this.ant1 = ant1;
	 setMomIni(hMom, mMom, sMom);
	 this.nom1 = e1.getNom();
	 this.nom2 = e2.getNom();
	 this.casa1 = casa1;
	 this.casaAnt1 = casaAnt1;
	 this.signe1 = signe1;
	 this.signe2 = signe2;
	 this.tipus = tipus;
	 
	 if (ant1 == true) {
		 this.nom1 = "ant. " + this.nom1;
	 }
 }
 */
 
 public int compareTo (Aspecte a) {
  if ((hMomIni < a.hMomIni)
    | (hMomIni == a.hMomIni
    && mMomIni < a.mMomIni)
    | (hMomIni == a.hMomIni
    && mMomIni == a.mMomIni
    && sMomIni < a.sMomIni)
    | (hMomIni == a.hMomIni
    && mMomIni == a.mMomIni
    && sMomIni == a.sMomIni
    && hMomFi < a.hMomFi)
    | (hMomIni == a.hMomIni
    && mMomIni == a.mMomIni
    && sMomIni == a.sMomIni
    && hMomFi == a.hMomFi
    && mMomFi < a.mMomFi)
    | (hMomIni == a.hMomIni
    && mMomIni == a.mMomIni
    && sMomIni == a.sMomIni
    && hMomFi == a.hMomFi
    && mMomFi == a.mMomFi
    && sMomFi < a.sMomFi)) {
   return -1;
  }
  
  if (!((hMomIni < a.hMomIni)
    | (hMomIni == a.hMomIni
    && mMomIni < a.mMomIni)
    | (hMomIni == a.hMomIni
    && mMomIni == a.mMomIni
    && sMomIni < a.sMomIni)
    | (hMomIni == a.hMomIni
    && mMomIni == a.mMomIni
    && sMomIni == a.sMomIni
    && hMomFi < a.hMomFi)
    | (hMomIni == a.hMomIni
    && mMomIni == a.mMomIni
    && sMomIni == a.sMomIni
    && hMomFi == a.hMomFi
    && mMomFi < a.mMomFi)
    | (hMomIni == a.hMomIni
    && mMomIni == a.mMomIni
    && sMomIni == a.sMomIni
    && hMomFi == a.hMomFi
    && mMomFi == a.mMomFi
    && sMomFi < a.sMomFi))) {
   return 1;
  }
  
  return 0;
 }
 
 public String getRegent1 (int x) {
	 return regent1[x];
 }
 
 public void setRegent1(int x, String regent) {
	 this.regent1[x] = regent;
 }
 
  public String getRegent2 (int x) {
	 return regent2[x];
 }
 
 public void setRegent2(int x, String regent) {
	 this.regent2[x] = regent;
 }
 
 public String getCasa1() {
  return casa1;
 }
 
 public String getCasaAnt1() {
  return casaAnt1;
 }
 
 public String getSigne1() {
	  return signe1;
}
 
 public String getCasa2() {
  return casa2;
 }
 
 public String getCasaAnt2() {
  return casaAnt2;
 }

 public String getSigne2() {
	  return signe2;
}

 public String getTipus() {
  return tipus;
 }
 
 public boolean getAnt1() {
  return ant1;
 }
 
 public int getHMomIni() {
  return hMomIni;
 }
 
 public int getMMomIni() {
  return mMomIni;
 }
 
 public int getSMomIni() {
  return sMomIni;
 }
 
 public int getHMomFi() {
  return hMomFi;
 }
 
 public int getMMomFi() {
  return mMomFi;
 }
 
 public int getSMomFi() {
  return sMomFi;
 }
 
 /*
 public String getAspecte(int difSignes) {
  return aspectes.get(difSignes); 
 }
 */
 
 public void setMomIni(int momH, int momM, int momS) {
  this.hMomIni = momH;
  this.mMomIni = momM;
  this.sMomIni = momS;
 }
 
 public void setMomFi(int momH, int momM, int momS) {
  this.hMomFi = momH;
  this.mMomFi = momM;
  this.sMomFi = momS;
 }
 
 public void setHMomFi(int momH) {
  this.hMomFi = momH;
 }
 
 public void setMMomFi(int momM) {
  this.mMomFi = momM;
 }
 
 public void setSMomFi(int momS) {
  this.sMomFi = momS;
 }

 public String getNom1() {
	 return this.nom1;
}

public String getNom2() {
	 return this.nom2;
}
}