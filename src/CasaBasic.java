/**
   Classe base per tots els càlculs de cases.
És una implementació nul·la, o sigui, les cases comencen als rètols.
Però d'altra banda reparteix tots els mètodes per les cases. Altres
sistemes de cases es consideren classes implementades de forma separada.
*/
public abstract class CasaBasic extends Object implements CasaInt {

   /** Nombre de cases. */
   protected static final int NOMBRE_CASES = 12;

   /** Es poden anul�lar els noms de les cases que es considerin. */
   protected static final String[] NOM_CASA = {
        "AC","2","3","IC","5","6","DC","8","9","MC","11","12" };

   /** Angles d'una casa en graus si s'usen cases equidistants. */
   protected static final int ANGLE_CASA_G = 360/NOMBRE_CASES;
   /** Angles d'una casa en radians si s'usen cases equidistants. */
   protected static final double ANGLE_CASA_R = 2.0*Math.PI/NOMBRE_CASES;
   /** Constant sobre l'ecl�ptica, on no s'apliquen alguns sistemes. */
   protected static final double rAxis = 23.44578889;

   /** Rang de validesa del sistema. */
   protected double[] rang = {-CalcUtil.PIH,CalcUtil.PIH};

   /** Latitud de la posici� en radians. */
   protected double latR;
   /** Augment de radians (RA). */
   protected double ascensioRecta;
   /** Obliquitat de l'ecl�ptica en radians (OB). */
   protected double obliquitatEcliptica;
   /** Compensaci� sideral en radians, despla�ament de la ubicaci� (rSid). */
   protected double compensacioSideral;

   /** Ascendent en radians (AS). */
   protected double ascendent;
   /** Mig Cel en radians (MC). */
   protected double migCel;

   /** Coordenades de les cases en Radians. */
   protected final double[] casesR = new double[NOMBRE_CASES];

   /** Constructor buit, despr�s s'haur� de cridar setCases(). */
   public CasaBasic() {
      // no fer res
   }

   public final double getAscendent() { return ascendent; }

   public final double getMigCel() { return migCel; }

   public final double[] getCasesR() { return casesR; }

   public final String[] getCasesText() { return NOM_CASA; }

   public final double getCasesR(int i) { return casesR[i]; }

   /** Transforma una casa en graus a radians */
   public final int CasaGraus(double g) {
      return CasaRadians(CalcUtil.GaR(g));
   }

   public final int CasaRadians(double r) {
      boolean limit = (casesR[NOMBRE_CASES-1]>casesR[0]);
      if (limit) {
         if ((casesR[NOMBRE_CASES-1] <= r)||(r < casesR[0])) return NOMBRE_CASES-1;
      } else {
         if ((casesR[NOMBRE_CASES-1] <= r)&&(r < casesR[0])) return NOMBRE_CASES-1;
      }
      for (int i=0; i<NOMBRE_CASES-1; i++) {
         limit = (casesR[i]>casesR[i+1]);
         if (limit) {
            if ( (casesR[i] <= r) || (r < casesR[i+1]) ) return i;
         } else {
            if ((casesR[i] <= r)&&(r < casesR[i+1])) return i;
         }
      }
      throw new RuntimeException("angle inv�lid a CasaRadians");
   }

   public final void setCases(double L, double RA, double OB, double SO) {
      this.latR = L;
      this.ascensioRecta = RA;
      this.obliquitatEcliptica = OB;
      this.compensacioSideral = SO;
      calcBasicAngles();
      calcCases();
   }

   /** Calcula el Mig Cel, l'Ascendent i el Punt de l'Est de la carta en
	   q�esti�, basats en l'hora i el lloc. Els dos primers tamb� s'utilitzen
	   en algunes de les rutines de c�lcul dels cims de les cases com una forma
	   r�pida per aconseguir els cims de les cases 1 i 10. */
   protected final void calcBasicAngles() {

      ascendent = CalcUtil.Angle(-Math.sin(ascensioRecta)*Math.cos(obliquitatEcliptica)-
                                 Math.tan(latR)*Math.sin(obliquitatEcliptica),Math.cos(ascensioRecta));
      if (compensacioSideral!=0.0) ascendent = CalcUtil.Mod2PI(ascendent+compensacioSideral);

      migCel = CalcUtil.Angle(Math.cos(obliquitatEcliptica),Math.tan(ascensioRecta));
      if (compensacioSideral!=0.0) migCel = CalcUtil.Mod2PI(migCel+compensacioSideral);
     if (CalcUtil.DiferenciaMinima(migCel, ascendent) < 0.0)
        migCel = CalcUtil.Mod2PI(migCel + Math.PI);
   }

   /** Calcula les cases en radians. Aquest m�tode es sobreescriu per altres sistemes.
       HouseNull: a les cases "zero", les c�spides sempre es fixen per comen�ar al seu
       signe corresponent, �s a dir la casa 1 sempre est� a 0 graus d'aries, etc.
   */
   protected void calcCases() {
      if ((latR<rang[0])||(latR>rang[1])) return; // no fa res

      for (int i = 0; i < NOMBRE_CASES; i++) casesR[i] = i*ANGLE_CASA_R;
   }

   /** Nom de l'algoritme de c�lcul de la casa. Aquest m�tode ha de ser anul�lat per a altres sistemes 
       Noms dels sistemes: ("Placidus", "Koch", "Equal", "Campanus", "Meridian", "Regiomontanus", 
	   "Porf�ria", "Morinus", "Topoc�ntric", "Alcabitius", "EqualMidheaven", "PorphyryNeo ", "Whole",
	   "Vedic", "Null"} */
   public String getCasaNom() { return "Null"; }

   public double[] getRangValidesa() {
      return rang;
   }

   /** Converteix el sistema de cases a String. */
   public final String toString() {
      StringBuffer buf = new StringBuffer();
      buf.append("Casa \"");
      buf.append(getCasaNom());
      buf.append("\" [");
         for (int i =0;i<NOMBRE_CASES; i++) {
            buf.append(NOM_CASA[i]);
            buf.append(':');
            CalcUtil.RaHMString(buf,casesR[i]);
            buf.append("; ");
            if (i == 5) buf.append('\n');
         }
      buf.append(']');
      return buf.toString();
   }

}