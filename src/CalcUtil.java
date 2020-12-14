public final class CalcUtil {

   private static final double PI180 = 180.0/Math.PI;

   public static final double PI2 = 2.0*Math.PI;

   public static final double PIH = Math.PI/2.0;

   public static final int SigneAngleG = 360/Constants.NOMBRE_MAX;

   static final double SigneAngleR = PI2/Constants.NOMBRE_MAX;


   public static final double RaG(double r) { return r*PI180; }

   public static final double GaR(double d) { return d/PI180; }

   public static final double HMSaG(int h, int m, int s) {
      return (s/60.0 + m)/60.0 + h;
   }

   public static final double HMSaR(int h, int m, int s) {
      return ((s / 60.0 + m) / 60.0 + h) / PI180;
   }

   public static final HMS GaHMS(HMS hms, double d) {

   hms.h = (int)Math.floor(d);
      final double t = (d-hms.h)*60;
      hms.m = (int)Math.floor(t);
      hms.s = (int)Math.floor((t-hms.m)*60);
      return hms;
   }

   public static final HMS RaHMS(HMS hms, double r) {
      return GaHMS(hms,RaG(r));
   }

   public static final void RaHMString(StringBuffer buf, double r) {
      int h,m,s;
      final double d = RaG(r);
      h = (int)Math.floor(d);
      final double t = (d-h)*60;
      m = (int)Math.floor(t);
      s = (int)Math.floor((t-m)*60);
      if (h<10) buf.append('0');
      buf.append(h);
      buf.append('g');
      if (m<10) buf.append('0');
      buf.append(m);
      buf.append('\'');
      if (s<10) buf.append('0');
      buf.append(s);
      buf.append('\"');
   }

   public static final int RaZ(double r, int modeHoroscop) {
      return ( (int) Math.floor(r/SigneAngleR) ) % Constants.NOMBRE_SIGNES[modeHoroscop];
   }

   public static final double DibuixaRadians(double r, int modeHoroscop) {
      final double t = r/SigneAngleR;
      final int signe = ( (int) Math.floor(t) ) % Constants.NOMBRE_SIGNES[modeHoroscop];
      return ( signe + t-Math.floor(t) ) * Constants.ANGLE_SIGNE_R[modeHoroscop];
   }

   public static final double RSinD(double d) {
      return Math.sin(d/PI180);
   }

   public static final double RSgn(double d) {
      if (d>0.0) return 1.0;
      else if (d<0.0) return -1.0;
      else return 0.0;
   }

   public static final XY PolARec(double R, double A) {
     return new XY(R*Math.cos(A),R*Math.sin(A));
   }

   public static final XY RecAPol(double X, double Y) {
     return new XY(Math.sqrt(X*X + Y*Y),Angle(X, Y));
   }

   public static final double SphToEcl(double L, double B, double O) {
     double G;
     double Q;
     XY xy = new XY(), ra = new XY();

     xy = PolARec(1.0,B);
     Q = xy.y;
     xy = PolARec(xy.x,L);
     G = xy.x;

     ra = RecAPol(xy.y, Q);
     xy = PolARec(ra.x,ra.y+O);

     ra = RecAPol(G, xy.x);
     if ( ra.y< 0.0) ra.y += PI2;

     return ra.y;
   }

   public static final XY CoorXform(double azi, double alt, double tilt) {
     double x, y, a1, l1;

     final double sinalt = Math.sin(alt);
     final double cosalt = Math.cos(alt);
     final double sinazi = Math.sin(azi);
     final double sintilt = Math.sin(tilt);
     final double costilt = Math.cos(tilt);

     x = cosalt * sinazi * costilt;
     y = sinalt * sintilt;
     x -= y;
     y = cosalt * Math.cos(azi);
     l1 = Angle(y, x);
     a1 = Math.asin(cosalt * sinazi * sintilt + sinalt * costilt);
     return new XY(l1,a1);
   }

   public static final double Mod360(double d) {
     if (d >= 360.0) d -= 360.0;
     else if (d < 0.0) d += 360.0;

     if ((d >= 0) && (d < 360.0)) return d;
     return d - Math.floor(d / 360.0) * 360.0;
   }

   public static final double Mod2PI(double d) {
     if (d >= PI2) d -= PI2;
     else if (d < 0.0) d += PI2;

     if ((d >= 0) && (d < PI2)) return d;
     return d - Math.floor(d / PI2) * PI2;
   }

   public static final double Angle(double x, double y) {
     double a;

     if (x != 0.0) {
       if (y != 0.0) a = Math.atan(y/x);
       else a = (x < 0.0) ? Math.PI : 0.0;
     } else a = (y < 0.0) ? -Math.PI/2 : Math.PI/2;
     if (a < 0.0) a += Math.PI;
     if (y < 0.0) a += Math.PI;
     return a;
   }

   public static final double DistanciaMinima(double deg1, double deg2) {
      double i = Math.abs(deg2-deg1);
      if (i<Math.PI) return i;
      return PI2-i;
   }

   public static final double DiferenciaMinima(double deg1, double deg2) {
      double i = deg2-deg1;
      if (Math.abs(i) < Math.PI) return i;
      return RSgn(i)*(Math.abs(i) - PI2);
   }

   public static int diaJulia(int dia, int mes, int any) {
        int im, jD;

        im = 12*any+mes+57597;
        jD = (2*(im%12) + 7 + 365*im)/12;
        jD = jD + dia + im/48 - 32083;
        if (jD > 2299171)
          jD = jD + im/4800 - im/1200 + 38;
        return jD;
   }

}

