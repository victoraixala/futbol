/**
Interfície per a càlculs de cases. Interfície mínima pels altres
tipus de cases que es permeten. Es permet especificar
un nombre variable de cases a la implementació base.
*/
public interface CasaInt {

   /** Coordenades dels límits de la casa en radians.
       @return Coordenades dels límits de cada casa en radians. */
   public double[] getCasesR();

   /** Noms dels límits domèstics com text.
       @return Noms dels límits domèstics com a text. */
   public String[] getCasesText();

   /** Algorisme per calcular la casa.
       @return Nom de la casa. */
   public String getCasaNom();

   /** Guany de l'algoritme del càlcul de casa.
       @return Rang del radian, o sigui 0 < r < 1. */
   public double[] getRangValidesa();

   /** Retorna la posició d'un angle en graus.
       @param g Posició del cos en graus.
       @return Valor de 0 fins n-1, o sigui, a quina casa es troba. */
   public int CasaGraus(double g);

   /** Retorna la posició d'un angle en radians.
       @param r posició del cos en radians.
       @return Valor de 0 fins n-1, o sigui, a quina casa es troba. */
   public int CasaRadians(double r);

   /** Estableix valors pel sistema de cases.
       @param L Latitud de la posició en radians.
       @param RA Augment de radians.
       @param OB Inclinació de l'eclíptica en radians.
       @param SO compensació sideral per horòscops.
   */
   public void setCases(double L, double RA, double OB, double SO);

}