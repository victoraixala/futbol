public final class Horoscop extends Object {

   private int diaJulia;
   private double seglesJulians;
   private double horaMundial;
   private double lonR;
   private double latR;
   private double ascensioRecta;
   private double obliquitatEcliptica;
   private double compensacioSideral;

   public CasaInt casaEmprada;
   //private PlanetaInt planetaEmprat;

   public Horoscop() {
   }

   public double getAscensioRecta() {
      return ascensioRecta;
   }
   public HMS getHoraLocalSideral() {
      return CalcUtil.GaHMS(new HMS(), CalcUtil.RaG(ascensioRecta) / 15.0);
   }
   public double getObliquitatEcliptica() {
      return obliquitatEcliptica;
   }
   public double getCompensacioSideral() {
      return compensacioSideral;
   }
   public CasaInt getCasa() {
      return casaEmprada;
   }
   //public PlanetaInt getPlaneta() {
   //   return planetaEmprat;
   //}

   public synchronized void setCasa(CasaInt casa) {
      casaEmprada = casa;
   }
   //public synchronized void setPlaneta(PlanetaInt planeta) {
   //   planetaEmprat = planeta;
   //}

   public void setHora(int jD, double F) {
      this.diaJulia = jD;
      this.horaMundial = F;
   }

   public void setHora(int dia, int mes, int any, double F) {
      setHora(CalcUtil.diaJulia(dia, mes, any), F);
   }

   public void setHora(int dia, int mes, int any, int h, int m, int s, double zonaHoraria) {
      setHora(dia, mes, any, CalcUtil.HMSaG(h, m, s) - zonaHoraria);
   }

   public void setCoordenades(double lon, double lat) {
      this.lonR = lon;
      this.latR = lat;
   }

   public void setGrausCoordenades(double lon, double lat) {
      setCoordenades(CalcUtil.GaR(lon), CalcUtil.GaR(lat));
   }

   public void setGrausCoordenades(int lond, int lonm, int lons, int latd, int latm, int lats) {
      setCoordenades(CalcUtil.HMSaR(lond, lonm, lons), CalcUtil.HMSaR(latd, latm, lats));
   }

   public void setDadesUsuari(int dia, int mes, int any, double F, double lon, double lat) {
      setHora(dia, mes, any, F);
      setGrausCoordenades(lon, lat);
   }

   public void calcValors() {
      double horaJuliana = diaJulia - 0.5 + horaMundial / 24.0;

      seglesJulians = (horaJuliana - 2415020) / 36525.0;
      ascensioRecta =
         CalcUtil.GaR(
            CalcUtil.Mod360(
               (6.6460656 + (2400.0513 + 2.58E-5 * seglesJulians) * seglesJulians + horaMundial) * 15.0
                  - CalcUtil.RaG(lonR)));

      obliquitatEcliptica = CalcUtil.GaR(23.452294 - 0.0130125 * seglesJulians);

      compensacioSideral = 0.0;

      if (casaEmprada != null) {
         casaEmprada.setCases(latR, ascensioRecta, obliquitatEcliptica, compensacioSideral);
      }
      //if (planetaEmprat != null) {
      //   planetaEmprat.setPlanetes(seglesJulians, compensacioSideral);
      //}
   }

   public String toString() {
      StringBuffer buf = new StringBuffer();
      buf.setLength(0);
      buf.append("diaJulia: ");
      buf.append(diaJulia);
      buf.append("\n");
      buf.append("horaMundial: ");
      buf.append(horaMundial);
      buf.append("\n");
      buf.append("HLS: ");
      buf.append(getHoraLocalSideral().toString());
      buf.append("\n");
      if (casaEmprada != null) {
    	 buf.append("casaEmprada: ");
         buf.append(casaEmprada.toString());
         buf.append("\n\n");
      }
      //if (planetaEmprat != null) {
    //	 buf.append("planetaEmprat: ");
    //   buf.append(planetaEmprat.toString());
    //  }
      return buf.toString();
   }
}