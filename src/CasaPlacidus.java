public final class CasaPlacidus extends CasaBasic {

   private final double[] subRang = {-CalcUtil.GaR(90.0-rAxis),CalcUtil.GaR(90.0-rAxis)};

   private double CuspPlacidus(double deg, double FF, boolean fNeg) {
     double R1, XS, X;

     R1 = CalcUtil.Mod2PI(ascensioRecta+CalcUtil.GaR(deg));
     if (fNeg) X = 1.0;
     else X = -1;
     // 10 vegades és arbitrari, però és el que fan altres programes.
     for (int i = 1; i <= 10; i++) {
       // Aquesta fórmula no funciona a latitud zero (AA == 0.0).
       XS = X*Math.sin(R1)*Math.tan(obliquitatEcliptica)*Math.tan( (latR == 0.0) ? 0.000001 : latR);
       XS = Math.acos(XS);
       if (XS < 0.0) XS += Math.PI;
       if (fNeg) R1 = ascensioRecta + Math.PI - XS/FF;
       else R1 = ascensioRecta + XS/FF;
     }
     double LO = Math.atan(Math.tan(R1)/Math.cos(obliquitatEcliptica));
     if (LO < 0.0) LO += Math.PI;
     if (Math.sin(R1) < 0.0) LO += Math.PI;
     return LO;
   }

   protected void calcCases() {
     //if (D.bug) D.log("CasaPlacidus ("+this.getClass()+") - calcCases cridat");
     if ((latR<subRang[0])||(latR>subRang[1])) return; // no fa res

     casesR[0] = ascendent-compensacioSideral;
     casesR[1] = CuspPlacidus(120.0, 1.5, true);
     casesR[2] = CuspPlacidus(150.0, 3.0, true);
     casesR[3] = migCel+Math.PI-compensacioSideral;
     casesR[4] = CuspPlacidus(30.0, 3.0, false) + Math.PI;
     casesR[5] = CuspPlacidus(60.0, 1.5, false) + Math.PI;
     for (int i = 0; i < 6; i++) {
       casesR[i] = CalcUtil.Mod2PI(casesR[i]+compensacioSideral);
       casesR[i+6] = CalcUtil.Mod2PI(casesR[i]+Math.PI);
     }
   }

   public String getCasaNom() { return "Placidus"; }

   /** G&uuml;ltigkeit des Hausberechnungs Algorithmus.
       @return Radiant des Ranges, d.h. range(1) &lt; r &lt; range(1). */
   public double[] getRangValidesa() {
      return subRang;
   }
}