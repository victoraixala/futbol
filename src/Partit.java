import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Partit {

	public String nomPartit = "";
	
	public Partit (String nom) {
		this.nomPartit = nom;
	}
	
 final Horoscop horoscop = new Horoscop();
 double[] casesR = new double[12];
 String colors = "";
 public int diaIni = 0;
 public int diaFi = 0;
 public int mesIni = 0;
 public int mesFi = 0;
 public int anyIni = 0;
 public int anyFi = 0;
 // latitud: N/S, longitud: E/W
 public int latG = 0;
 public int latM = 0;
 public int latS = 0;
 public int lonG = 0;
 public int lonM = 0;
 public int lonS = 0;
 public int horaIni = 0;
 public int horaIniDescans = 0;
 public int horaFi = 0;
 public int horaFiDescans = 0;
 public int minutIni = 0;
 public int minutIniDescans = 0;
 public int minutFi = 0;
 public int minutFiDescans = 0;
 public int segonIni = 0;
 public int segonIniDescans = 0;
 public int segonFi = 0;
 public int segonFiDescans = 0;
 public int zonahoraria = 0;
 public int modLat = 0;
 public int modLon = 0;
 private int segons = 0;
 public boolean activaGols = false;
 String inifi = "";
 String nom = "";
 int i = 0;
 boolean cases = false;
 boolean diurn = false;
 boolean nocturn = false;
 boolean mixt = false;
 ArrayList<Entitat> entitats = new ArrayList<Entitat>(25);
 ArrayList<Aspecte> aspectes = new ArrayList<Aspecte>();
 ArrayList<Gol> gols = new ArrayList<Gol>();
 int aa = 0;
 int az = 0;
 int za = 0;
 int zz = 0;
 int aaa = 0;
 int aaz = 0;
 int aza = 0;
 int azz = 0;
 Entitat sol = null;
 Entitat lluna = null;
 Entitat mercuri = null;
 Entitat venus = null;
 Entitat mart = null;
 Entitat jupiter = null;
 Entitat saturn = null;
 Entitat ura = null;
 Entitat neptu = null;
 Entitat pluto = null;
 Entitat node = null;
 Entitat fortuna = null;
 Entitat infortuni = null;
 Entitat c1 = null;
 Entitat c2 = null;
 Entitat c3 = null;
 Entitat c4 = null;
 Entitat c5 = null;
 Entitat c6 = null;
 Entitat c7 = null;
 Entitat c8 = null;
 Entitat c9 = null;
 Entitat c10 = null;
 Entitat c11 = null;
 Entitat c12 = null;

 private static Map<Integer, String> graus = new HashMap<Integer, String>();
 static {
  graus.put(-2, "conj. ");
  graus.put(-1, "conj. ");
  graus.put(0, "conj. ");
  graus.put(1, "conj. ");
  graus.put(2, "conj. ");
  graus.put(28, "semi. ");
  graus.put(29, "semi. ");
  graus.put(-28, "semi. ");
  graus.put(-29, "semi. ");
  graus.put(30, "semi. ");
  graus.put(-30, "semi. ");
  graus.put(31, "semi. ");
  graus.put(-31, "semi. ");
  graus.put(32, "semi. ");
  graus.put(-32, "semi. ");
  graus.put(328, "semi. ");
  graus.put(-328, "semi. ");
  graus.put(329, "semi. ");
  graus.put(-329, "semi. ");
  graus.put(330, "semi. ");
  graus.put(-330, "semi. ");
  graus.put(331, "semi. ");
  graus.put(-331, "semi. ");
  graus.put(332, "semi. ");
  graus.put(-332, "semi. ");
  graus.put(58, "sex. ");
  graus.put(-58, "sex. ");
  graus.put(59, "sex. ");
  graus.put(-59, "sex. ");
  graus.put(60, "sex. ");
  graus.put(-60, "sex. ");
  graus.put(61, "sex. ");
  graus.put(-61, "sex. ");
  graus.put(62, "sex. ");
  graus.put(-62, "sex. ");
  graus.put(298, "sex. ");
  graus.put(-298, "sex. ");
  graus.put(299, "sex. ");
  graus.put(-299, "sex. ");
  graus.put(300, "sex. ");
  graus.put(-300, "sex. ");
  graus.put(301, "sex. ");
  graus.put(-301, "sex. ");
  graus.put(302, "sex. ");
  graus.put(-302, "sex. ");
  graus.put(88, "quad. ");
  graus.put(-88, "quad. ");
  graus.put(89, "quad. ");
  graus.put(-89, "quad. ");
  graus.put(90, "quad. ");
  graus.put(-90, "quad. ");
  graus.put(91, "quad. ");
  graus.put(-91, "quad. ");
  graus.put(92, "quad. ");
  graus.put(-92, "quad. ");
  graus.put(268, "quad. ");
  graus.put(-268, "quad. ");
  graus.put(269, "quad. ");
  graus.put(-269, "quad. ");
  graus.put(270, "quad. ");
  graus.put(-270, "quad. ");
  graus.put(271, "quad. ");
  graus.put(-271, "quad. ");
  graus.put(272, "quad. ");
  graus.put(-272, "quad. ");
  graus.put(118, "tr\u00edg. ");
  graus.put(-118, "tr\u00edg. ");
  graus.put(119, "tr\u00edg. ");
  graus.put(-119, "tr\u00edg. ");
  graus.put(120, "tr\u00edg. ");
  graus.put(-120, "tr\u00edg. ");
  graus.put(121, "tr\u00edg. ");
  graus.put(-121, "tr\u00edg. ");
  graus.put(122, "tr\u00edg. ");
  graus.put(-122, "tr\u00edg. ");
  graus.put(238, "tr\u00edg. ");
  graus.put(-238, "tr\u00edg. ");
  graus.put(239, "tr\u00edg. ");
  graus.put(-239, "tr\u00edg. ");
  graus.put(240, "tr\u00edg. ");
  graus.put(-240, "tr\u00edg. ");
  graus.put(241, "tr\u00edg. ");
  graus.put(-241, "tr\u00edg. ");
  graus.put(242, "tr\u00edg. ");
  graus.put(-242, "tr\u00edg. ");
  graus.put(148, "quin. ");
  graus.put(-148, "quin. ");
  graus.put(149, "quin. ");
  graus.put(-149, "quin. ");
  graus.put(150, "quin. ");
  graus.put(-150, "quin. ");
  graus.put(151, "quin. ");
  graus.put(-151, "quin. ");
  graus.put(152, "quin. ");
  graus.put(-152, "quin. ");
  graus.put(208, "quin. ");
  graus.put(-208, "quin. ");
  graus.put(209, "quin. ");
  graus.put(-209, "quin. ");
  graus.put(210, "quin. ");
  graus.put(-210, "quin. ");
  graus.put(211, "quin. ");
  graus.put(-211, "quin. ");
  graus.put(212, "quin. ");
  graus.put(-212, "quin. ");
  graus.put(178, "opo. ");
  graus.put(-178, "opo. ");
  graus.put(179, "opo. ");
  graus.put(-179, "opo. ");
  graus.put(180, "opo. ");
  graus.put(-180, "opo. ");
  graus.put(181, "opo. ");
  graus.put(-181, "opo. ");
  graus.put(182, "opo. ");
  graus.put(-182, "opo. ");
 }
 
 public void llegir(String fitxer, PrintWriter pwLog) throws IOException, FileNotFoundException {
	 String cadena;
     FileReader f = new FileReader(fitxer);
     BufferedReader b = new BufferedReader(f);
     cadena = b.readLine();
	 if (fitxer.endsWith("a.txt")) {
		 char[] linia = cadena.toCharArray();
		 cadena = "";
		 for (int i = 0; i <= linia.length - 1; i++) {
			 String caracter = String.valueOf(linia[i]);
			 if (Character.isDigit(linia[i])
				 || caracter.equals("N")
				 || caracter.equals("S")
				 || caracter.equals("E")
				 || caracter.equals("W")) {
					 cadena += linia[i];
				 }
			 }
		     pwLog.println("cadena: " + cadena);
			 //System.out.println("cadena: " + cadena);
			 // la primera fila porta latitud
			 latG = Integer.parseInt(cadena.substring(0,2));
			 pwLog.println("latG: " + latG);
			 //System.out.println("latG: " + latG);
			 latM = Integer.parseInt(cadena.substring(2,4));
			 pwLog.println("latM: " + latM);
			 //System.out.println("latM: " + latM);
			 latS = Integer.parseInt(cadena.substring(4,6));
			 pwLog.println("latS: " + latS);
			 //System.out.println("latS: " + latS);
			 modLat = 1;
			 pwLog.println("N/S: " + cadena.substring(6,7));
			 //System.out.println("N/S: " + cadena.substring(6,7));
			 if (cadena.substring(6,7).equals("S")) {
				 modLat = -1;
			 }
			 // la segona fila porta longitud
			 cadena = b.readLine();
			 linia = cadena.toCharArray();
			 cadena = "";
			 for (int i = 0; i <= linia.length - 1; i++) {
				 String caracter = String.valueOf(linia[i]);
				 if (Character.isDigit(linia[i])
					 || caracter.equals("N")
					 || caracter.equals("S")
					 || caracter.equals("E")
					 || caracter.equals("W")) {
						 cadena += linia[i];
					 }
			 }
			 pwLog.println("cadena: " + cadena);
			 //System.out.println("cadena: " + cadena);
			 lonG = Integer.parseInt(cadena.substring(0,2));
			 pwLog.println("lonG: " + lonG);
			 //System.out.println("lonG: " + lonG);
			 lonM = Integer.parseInt(cadena.substring(2,4));
			 pwLog.println("lonM: " + lonM);
			 //System.out.println("lonM: " + lonM);
			 lonS = Integer.parseInt(cadena.substring(4,6));
			 pwLog.println("lonS: " + lonS);
			 //System.out.println("lonS: " + lonS);
			 modLon = 1;
			 pwLog.println("E/W: " + cadena.substring(6,7));
			 //System.out.println("E/W: " + cadena.substring(6,7));
			 if (cadena.substring(6,7).equals("E")) {
				 modLon = -1;
			 }
	 }
	 else
	 {
		 cadena = b.readLine();
	 }
	 cadena = b.readLine();
	 cadena = b.readLine();
	 // la quarta fila porta el dia i la hora d'inici i fi del partit
	 if (fitxer.endsWith("a.txt")) {
		 diaIni = Integer.parseInt(cadena.substring(0,2));
		 mesIni = Integer.parseInt(cadena.substring(3,5));
		 anyIni = Integer.parseInt(cadena.substring(6,10));
		 horaIni = Integer.parseInt(cadena.substring(11,13));
		 minutIni = Integer.parseInt(cadena.substring(14,16));
		 segonIni = Integer.parseInt(cadena.substring(17,19));
	 }
	 else
	 {
		 cases = false;
		 diaFi = Integer.parseInt(cadena.substring(0,2));
		 mesFi = Integer.parseInt(cadena.substring(3,5));
		 anyFi = Integer.parseInt(cadena.substring(6,10));
		 horaFi = Integer.parseInt(cadena.substring(11,13));
		 minutFi = Integer.parseInt(cadena.substring(14,16));
		 segonFi = Integer.parseInt(cadena.substring(17,19));
		 this.segons = (horaFi * 3600 + minutFi * 60 + segonFi) - (horaIni * 3600 + minutIni * 60 + segonIni);
		 if (diaIni != diaFi) {
			 this.segons += 86400;
		 }
		 sol.setSegons();
		 lluna.setSegons();
		 mercuri.setSegons();
		 venus.setSegons();
		 mart.setSegons();
		 jupiter.setSegons();
		 saturn.setSegons();
		 if(ura instanceof Entitat){
			ura.setSegons();
		 }
		 if(neptu instanceof Entitat){
			neptu.setSegons();
		 }
	     if(pluto instanceof Entitat){
			pluto.setSegons();
		 }
		 node.setSegons();
		 fortuna.setSegons();
		 if(infortuni instanceof Entitat){
			infortuni.setSegons();	
		 }
		 c1.setSegons();
		 c4.setSegons();
		 c7.setSegons();
		 c10.setSegons();
	 }
	 
	 cadena = b.readLine();
	 // la cinquena fila porta la zona horària
	 if (cadena.substring(17,18).equals("-")) {
		 zonahoraria = 0 - Integer.valueOf(cadena.substring(18,20));
     }
     else
     {
		 zonahoraria = Integer.valueOf(cadena.substring(18,20));
	 }
	 cadena = b.readLine();
	 cadena = b.readLine();
	 if (fitxer.endsWith("a.txt")) {
		 inifi = "inici";
	 }
	 else
	 {
		 inifi = "fi";
	 }
	 while((cadena = b.readLine())!=null) {
		 if (!"".equals(cadena)) {
			 processar(cadena,inifi, pwLog);
		 }
	 }
     b.close();
 }
    
 public int getDiaIni() {
  return diaIni;
 }
 
 public int getMesIni() {
  return mesIni;
 }
 
 public int getAnyIni() {
  return anyIni;
 }
 
 public int getHoraIni() {
  return horaIni;
 }
 
 public int getMinutIni() {
  return minutIni;
 }
 
 public int getSegonIni() {
  return segonIni;
 }
 
 public int getDiaFi() {
  return diaFi;
 }
 
 public int getMesFi() {
  return mesFi;
 }
 
 public int getAnyFi() {
  return anyFi;
 }
 
 public int getHoraFi() {
  return horaFi;
 }
 
 public int getMinutFi() {
  return minutFi;
 }
 
 public int getSegonFi() {
  return segonFi;
 }
 
 public int getSegons() {
	 return segons;
 }
 
 public void processar(String cadena, String fitxer, PrintWriter pwLog) {
     
     if (cadena != "") {
      int i = 0;
      if (cadena.equals("Casas (Placidus)")) {
       cases = true;
      }
      if (cases == false) {
    //Planetes
          String[] fila = cadena.split("\t");
    nom = fila[0].substring(0,fila[0].length()/2);
          char[] signepos = fila[1].toCharArray();
          while (!Character.isDigit(signepos[i])) {
           i++;
          }
          String signe = fila[1].substring(0,i);
		  
		  signe = traduir(signe);

    int g, m, s = 0;
    try
    {
     //CF: Casa
     //INICF
     
     g = Integer.parseInt(fila[1].substring(i,i+2));
     m = Integer.parseInt(fila[1].substring(i+3,i+5));
     if (fila[1].substring(i+6,i+8).equals("0\"")) {
      s = 0;
     }
     else
     {
      s = Integer.parseInt(fila[1].substring(i+6,i+8));
     }

     //FICF
    }
    catch (NumberFormatException e)
    {
     //CF: Feina
     //INICF

     g = Integer.parseInt(fila[1].substring(i,i+2));
     m = Integer.parseInt(fila[1].substring(i+4,i+6));
     s = Integer.parseInt(fila[1].substring(i+7,i+9));

     //FICF
    }
    
    String estat = "";
    if (fila[1].substring(i+9) != null) {
     estat = fila[1].substring(i+9);
    }
            
          String c = fila[2];
		  // Weka no accepta el caràcter Unicode
		  String casa = "";
		  if (c.contains("\u00bb")) {
			  //System.out.println("casa abans:" + casa);
			  casa = c.replace("\u00bb","-");
			  //System.out.println("casa canviada:" + casaMod);
		  }
		  else
		  {
			  casa = c;
		  }
      
          switch (nom) {
          case "Sol":
           if (fitxer.equals("inici")) {
            sol = new Entitat (this, nom, signe, g, m, s, casa, estat);
			entitats.add(sol);
			this.i += 1;
			break;
     }
     else
     {
      sol.setSigneFi(signe);
      sol.setGFi(g);
      sol.setMFi(m);
      sol.setSFi(s);
      //sol.setGrauSimpleFi(g,m,s);
      sol.setCasaFi(casa);
	  //carta diurna o nocturna
	  int casaIni = 0;
	  int casaFi = 0;
	  if (sol.getCasaIni().length() == 1) {
		   casaIni = Integer.valueOf(sol.getCasaIni());
	   }
	   else
	   {
		   /*
		 //System.out.println("sol.getCasaIni(): " + sol.getCasaIni());
		 //System.out.println("sol.getCasaIni().substring(0,2): " + sol.getCasaIni().substring(0,2));
		 //System.out.println("sol.getCasaIni().substring(0,2).replace: " + sol.getCasaIni().substring(0,2).replace(" ",""));
		 //System.out.println("Integer.valueOf((sol.getCasaIni().substring(0,2).replace): " + Integer.valueOf(sol.getCasaIni().substring(0,2).replace(" ","")));
		   */
		   casaIni = Integer.valueOf((sol.getCasaIni().substring(0,2).replace(" ","")));
	   }
	  
	  if (sol.getCasaFi().length() == 1) {
		   casaFi = Integer.valueOf(sol.getCasaFi());
	   }
	   else
	   {
		   /*
		 //System.out.println("sol.getCasaFi(): " + sol.getCasaFi());
		 //System.out.println("sol.getCasaFi().substring(0,2): " + sol.getCasaFi().substring(0,2));
		 //System.out.println("sol.getCasaFi().substring(0,2).replace: " + sol.getCasaFi().substring(0,2).replace(" ",""));
		 //System.out.println("Integer.valueOf((sol.getCasaFi().substring(0,2).replace): " + Integer.valueOf(sol.getCasaFi().substring(0,2).replace(" ","")));
		   */
		   casaFi = Integer.valueOf((sol.getCasaFi().substring(0,2).replace(" ","")));
	   }
	  
      if (casaIni == casaFi) {
		  if (casaIni < 7) {
			  nocturn = true;
			  pwLog.println("Partit nocturn. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
			//System.out.println("Partit nocturn. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
		  }
		  else
		  {
			  diurn = true;
			  pwLog.println("Partit diurn. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
			//System.out.println("Partit diurn. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
		  }
	  }
	  else
	  {
		  if (casaIni < 7
		  | casaFi < 7) {
			  nocturn = true;
			  pwLog.println("Partit nocturn. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
			//System.out.println("Partit nocturn. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
		  }
		  if (casaIni > 6
		  | casaFi > 6) {
			  diurn = true;
			  pwLog.println("Partit diurn. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
			//System.out.println("Partit diurn. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
		  }
		  
		  if (diurn == true
		  && nocturn == true) {
			  mixt = true;
			  diurn = false;
			  nocturn = false;
			  pwLog.println("Partit mixt. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
			//System.out.println("Partit mixt. CasaIni: " + sol.getCasaIni() + " CasaFi: " + sol.getCasaFi());
		  }
	  }
      sol.setVelocitat();
	  if (sol.getSigneIni().equals(sol.getSigneFi())) {
		  for (int x = 0; x < segons; x++) {
			  sol.setSigne(x,sol.getSigneIni());
		  }
	  }
	  else
	  {
		  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) sol.getGIni() + (double) sol.getMIni() / 60 + (double) sol.getSIni() / 3600;
		  String se = sol.getSigneIni();
		  sol.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += sol.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = sol.getSigneFi();
				  q = x;
			  }
			  sol.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Sol canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Sol canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
	  }
	  pwLog.println("Creat Sol. Velocitat: " + sol.getVelocitat());
	//System.out.println("Creat Sol. Velocitat: " + sol.getVelocitat());
      break;
     }
           case "Luna":
            if (fitxer.equals("inici")) {
            lluna = new Entitat (this, "Lluna", signe, g, m, s, casa, estat);
      entitats.add(lluna);
      this.i += 1;
	  break;
     }
     else
     {
      lluna.setSigneFi(signe);
      lluna.setGFi(g);
      lluna.setMFi(m);
      lluna.setSFi(s);
      //lluna.setGrauSimpleFi(g,m,s);
      lluna.setCasaFi(casa);
      lluna.setVelocitat();
	  if (lluna.getSigneIni().equals(lluna.getSigneFi())) {
		  for (int x = 0; x < segons; x++) {
			  lluna.setSigne(x,lluna.getSigneIni());
		  }
	  }
	  else
	  {
		  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) lluna.getGIni() + (double) lluna.getMIni() / 60 + (double) lluna.getSIni() / 3600;
		  String se = lluna.getSigneIni();
		  lluna.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += lluna.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = lluna.getSigneFi();
				  q = x;
			  }
			  lluna.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Lluna canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Lluna canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
	  }
	  pwLog.println("Creada Lluna. Velocitat: " + lluna.getVelocitat());
	//System.out.println("Creada Lluna. Velocitat: " + lluna.getVelocitat());
      break;
     }
           case "Mercurio":
     if (fitxer.equals("inici")) {
            mercuri = new Entitat (this, "Mercuri", signe, g, m, s, casa, estat);
      entitats.add(mercuri);
      this.i += 1;
	  break;
     }
     else
     {
      mercuri.setSigneFi(signe);
      mercuri.setGFi(g);
      mercuri.setMFi(m);
      mercuri.setSFi(s);
      //mercuri.setGrauSimpleFi(g,m,s);
      mercuri.setCasaFi(casa);
      mercuri.setVelocitat();
	  if (mercuri.getSigneIni().equals(mercuri.getSigneFi())) {
		  for (int x = 0; x < segons; x++) {
			  mercuri.setSigne(x,mercuri.getSigneIni());
		  }
	  }
	  else
	  {
		  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) mercuri.getGIni() + (double) mercuri.getMIni() / 60 + (double) mercuri.getSIni() / 3600;
		  String se = mercuri.getSigneIni();
		  mercuri.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += mercuri.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = mercuri.getSigneFi();
				  q = x;
			  }
			  mercuri.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Mercuri canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Mercuri canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
	  }
	  pwLog.println("Creat Mercuri. Velocitat: " + mercuri.getVelocitat());
	//System.out.println("Creat Mercuri. Velocitat: " + mercuri.getVelocitat());
      break;
      }
           case "Venus":
     if (fitxer.equals("inici")) {
            venus = new Entitat (this, nom, signe, g, m, s, casa, estat);
      entitats.add(venus);
      this.i += 1;
	  break;
      }
     else
     {
      venus.setSigneFi(signe);
      venus.setGFi(g);
      venus.setMFi(m);
      venus.setSFi(s);
      //venus.setGrauSimpleFi(g,m,s);
      venus.setCasaFi(casa);
      venus.setVelocitat();
	  if (venus.getSigneIni().equals(venus.getSigneFi())) {
		  for (int x = 0; x < segons; x++) {
			  venus.setSigne(x,venus.getSigneIni());
		  }
	  }
	  else
	  {
		  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) venus.getGIni() + (double) venus.getMIni() / 60 + (double) venus.getSIni() / 3600;
		  String se = venus.getSigneIni();
		  venus.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += venus.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = venus.getSigneFi();
				  q = x;
			  }
			  venus.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Venus canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Venus canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
	  }
	  pwLog.println("Creat Venus. Velocitat: " + venus.getVelocitat());
	//System.out.println("Creat Venus. Velocitat: " + venus.getVelocitat());
      break;
      }
           case "Marte":
     if (fitxer.equals("inici")) {
            mart = new Entitat (this, "Mart", signe, g, m, s, casa, estat);
      entitats.add(mart);
      this.i += 1;
	  break;
      }
     else
     {
      mart.setSigneFi(signe);
      mart.setGFi(g);
      mart.setMFi(m);
      mart.setSFi(s);
      //mart.setGrauSimpleFi(g,m,s);
      mart.setCasaFi(casa);
      mart.setVelocitat();
	  if (mart.getSigneIni().equals(mart.getSigneFi())) {
		  for (int x = 0; x < segons; x++) {
			  mart.setSigne(x,mart.getSigneIni());
		  }
	  }
	  else
	  {
		  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) mart.getGIni() + (double) mart.getMIni() / 60 + (double) mart.getSIni() / 3600;
		  String se = mart.getSigneIni();
		  mart.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += mart.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = mart.getSigneFi();
				  q = x;
			  }
			  mart.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Mart canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Mart canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
	  }
	  pwLog.println("Creat Mart. Velocitat: " + mart.getVelocitat());
	//System.out.println("Creat Mart. Velocitat: " + mart.getVelocitat());
      break;
      }
           case "J\u00fapiter":
            if (fitxer.equals("inici")) {
             jupiter = new Entitat (this, "J\u00fapiter", signe, g, m, s, casa, estat);
             entitats.add(jupiter);
             this.i += 1;
			 break;
            }
     else
     {
      jupiter.setSigneFi(signe);
      jupiter.setGFi(g);
      jupiter.setMFi(m);
      jupiter.setSFi(s);
      //jupiter.setGrauSimpleFi(g,m,s);
      jupiter.setCasaFi(casa);
      jupiter.setVelocitat();
	  if (jupiter.getSigneIni().equals(jupiter.getSigneFi())) {
		  for (int x = 0; x < segons; x++) {
			  jupiter.setSigne(x,jupiter.getSigneIni());
		  }
	  }
	  else
	  {
		  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) jupiter.getGIni() + (double) jupiter.getMIni() / 60 + (double) jupiter.getSIni() / 3600;
		  String se = jupiter.getSigneIni();
		  jupiter.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += jupiter.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = jupiter.getSigneFi();
				  q = x;
			  }
			  jupiter.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("J\u00fapiter canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("J\u00fapiter canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
	  }
	  pwLog.println("Creat J\u00fapiter. Velocitat: " + jupiter.getVelocitat());
	//System.out.println("Creat J\u00fapiter. Velocitat: " + jupiter.getVelocitat());
      break;
      }
           case "Saturno":
     if (fitxer.equals("inici")) {
            saturn = new Entitat (this, "Saturn", signe, g, m, s, casa, estat);
      entitats.add(saturn);
      this.i += 1;
	  break;
      }
     else
     {
      saturn.setSigneFi(signe);
      saturn.setGFi(g);
      saturn.setMFi(m);
      saturn.setSFi(s);
      //saturn.setGrauSimpleFi(g,m,s);
      saturn.setCasaFi(casa);
      saturn.setVelocitat();
	  if (saturn.getSigneIni().equals(saturn.getSigneFi())) {
		  for (int x = 0; x < segons; x++) {
			  saturn.setSigne(x,saturn.getSigneIni());
		  }
	  }
	  else
	  {
		  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) saturn.getGIni() + (double) saturn.getMIni() / 60 + (double) saturn.getSIni() / 3600;
		  String se = saturn.getSigneIni();
		  saturn.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += saturn.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = saturn.getSigneFi();
				  q = x;
			  }
			  saturn.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Saturn canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Saturn canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
	  }
	  pwLog.println("Creat Saturn. Velocitat: " + saturn.getVelocitat());
	//System.out.println("Creat Saturn. Velocitat: " + saturn.getVelocitat());
      break;
      }
           case "Urano":
            if (fitxer.equals("inici")) {
             ura = new Entitat (this, "Ur\u00e0", signe, g, m, s, casa, estat);
             entitats.add(ura);
             this.i += 1;
			 break;
      }
      else
      {
       ura.setSigneFi(signe);
       ura.setGFi(g);
       ura.setMFi(m);
       ura.setSFi(s);
       //ura.setGrauSimpleFi(g,m,s);
       ura.setCasaFi(casa);
       ura.setVelocitat();
	   if (ura.getSigneIni().equals(ura.getSigneFi())) {
		  for (int x = 0; x < segons; x++) {
			  ura.setSigne(x,ura.getSigneIni());
		  }
	  }
	  else
	  {
		  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) ura.getGIni() + (double) ura.getMIni() / 60 + (double) ura.getSIni() / 3600;
		  String se = ura.getSigneIni();
		  ura.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += ura.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = ura.getSigneFi();
				  q = x;
			  }
			  ura.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Ur\u00e0 canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Ur\u00e0 canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
	  }
	   pwLog.println("Creat Ur\u00e0. Velocitat: " + ura.getVelocitat());
	 //System.out.println("Creat Ur\u00e0. Velocitat: " + ura.getVelocitat());
       break;
      }
           case "Neptuno":
      if (fitxer.equals("inici")) {
             neptu = new Entitat (this, "Nept\u00fa", signe, g, m, s, casa, estat);
       entitats.add(neptu);
       this.i += 1;
	   break;
      }
      else
      {
       neptu.setSigneFi(signe);
       neptu.setGFi(g);
       neptu.setMFi(m);
       neptu.setSFi(s);
       //neptu.setGrauSimpleFi(g,m,s);
       neptu.setCasaFi(casa);
       neptu.setVelocitat();
	   if (neptu.getSigneIni().equals(neptu.getSigneFi())) {
		  for (int x = 0; x < segons; x++) {
			  neptu.setSigne(x,neptu.getSigneIni());
		  }
	  }
	  else
	  {
		  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) neptu.getGIni() + (double) neptu.getMIni() / 60 + (double) neptu.getSIni() / 3600;
		  String se = neptu.getSigneIni();
		  neptu.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += neptu.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = neptu.getSigneFi();
				  q = x;
			  }
			  neptu.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Nept\u00fa canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Nept\u00fa canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
	  }
	   pwLog.println("Creat Nept\u00fa. Velocitat: " + neptu.getVelocitat());
	 //System.out.println("Creat Nept\u00fa. Velocitat: " + neptu.getVelocitat());
       break;
      }
            case "Plut\u00f3n":
             if (fitxer.equals("inici")) {
              pluto = new Entitat (this, "Plut\u00f3", signe, g, m, s, casa, estat);
              entitats.add(pluto);
              this.i += 1;
			  break;
       }
             else
             {
				 pluto.setSigneFi(signe);
				 pluto.setGFi(g);
				 pluto.setMFi(m);
				 pluto.setSFi(s);
				 //pluto.setGrauSimpleFi(g,m,s);
				 pluto.setCasaFi(casa);
				 pluto.setVelocitat();
				 if (pluto.getSigneIni().equals(pluto.getSigneFi())) {
					 for (int x = 0; x < segons; x++) {
						 pluto.setSigne(x,pluto.getSigneIni());
					 }
				 }
				 else
				 {
					 int q = 0;
		  boolean canviat = false;
		  double posicio = (double) pluto.getGIni() + (double) pluto.getMIni() / 60 + (double) pluto.getSIni() / 3600;
		  String se = pluto.getSigneIni();
		  pluto.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += pluto.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = pluto.getSigneFi();
				  q = x;
			  }
			  pluto.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Plut\u00f3 canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Plut\u00f3 canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
				 }
				 pwLog.println("Creat Plut\u00f3. Velocitat: " + pluto.getVelocitat());
				 //System.out.println("Creat Plut\u00f3. Velocitat: " + pluto.getVelocitat());
				 break;
			}
            case "Nodo N (m)":
             if (fitxer.equals("inici")) {
              node = new Entitat (this, "Node", signe, g, m, s, casa, estat);
              entitats.add(node);
              this.i += 1;
			  break;
       }
             else
             {
              node.setSigneFi(signe);
              node.setGFi(g);
              node.setMFi(m);
              node.setSFi(s);
              //node.setGrauSimpleFi(g,m,s);
              node.setCasaFi(casa);
              node.setVelocitat();
			  if (node.getSigneIni().equals(node.getSigneFi())) {
				  for (int x = 0; x < segons; x++) {
					  node.setSigne(x,node.getSigneIni());
				  }
			  }
			  else
			  {
				  int q = 0;
		  boolean canviat = false;
		  double posicio = (double) node.getGIni() + (double) node.getMIni() / 60 + (double) node.getSIni() / 3600;
		  String se = node.getSigneIni();
		  node.setSigne(0,se);
		  for (int x = 1; x < segons; x++) {
			  posicio += node.getVelocitat() / 3600;
			  if ((posicio > 30 | posicio < 0) && (canviat == false)) {
				  canviat = true;
				  se = node.getSigneFi();
				  q = x;
			  }
			  node.setSigne(x,se);
		  }
		  int momH = (int) q / 3600;
		  int momM = (int) (q - momH * 3600) / 60;
		  int momS = (int) q - momH * 3600 - momM * 60;
		  momS += segonIni;
		  if (momS > 59) {
			  momM++;
			  momS -= 60;
		  }
    
		  momM += minutIni;
		  if (momM > 59) {
			  momH++;
			  momM -= 60;
		  }
   
		  momH += horaIni;
		  if (momH > 23) {
			  momH -= 24;
		  }
		  pwLog.println("Node canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		//System.out.println("Node canvia de signe a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
			  }
			  pwLog.println("Creat Node. Velocitat: " + node.getVelocitat());
			//System.out.println("Creat Node. Velocitat: " + node.getVelocitat());
              break;
			 }
            case "Fortuna":
             if (fitxer.equals("inici")) {
              fortuna = new Entitat (this, nom, signe, g, m, s, casa, estat);
              entitats.add(fortuna);
              this.i += 1;
			  break;
       }
             else
             {
              fortuna.setSigneFi(signe);
              fortuna.setGFi(g);
              fortuna.setMFi(m);
              fortuna.setSFi(s);
              //fortuna.setGrauSimpleFi(g,m,s);
              fortuna.setCasaFi(casa);
              fortuna.setVelocitat();
              pwLog.println("Creada Fortuna. Velocitat: " + fortuna.getVelocitat());
			//System.out.println("Creada Fortuna. Velocitat: " + fortuna.getVelocitat());
              break;
       }
            case "Infortunio":
             if (fitxer.equals("inici")) {
              infortuni = new Entitat (this, "Infortuni", signe, g, m, s, casa, estat);
              entitats.add(infortuni);
              this.i += 1;
			  break;
       }
             else
             {
              infortuni.setSigneFi(signe);
              infortuni.setGFi(g);
              infortuni.setMFi(m);
              infortuni.setSFi(s);
              //infortuni.setGrauSimpleFi(g,m,s);
              infortuni.setCasaFi(casa);
              infortuni.setVelocitat();
              pwLog.println("Creat Infortuni. Velocitat: " + infortuni.getVelocitat());
			//System.out.println("Creat Infortuni. Velocitat: " + infortuni.getVelocitat());
              break;
             }
            }
      }
      else
      {
       if (!cadena.equals("Casas (Placidus)")) {
        //Cases
           String[] fila = new String[2];
           fila = cadena.split("\t");
           nom = fila[0];
           char[] signepos = fila[1].toCharArray();
		   
           while (!Character.isDigit(signepos[i])) {
            i++;
           }
           String signe = fila[1].substring(0,i);

        signe = traduir(signe);

     int g, m, s = 0;
     try
     {
      //CF: Casa
      //INICF
        
      g = Integer.parseInt(fila[1].substring(i,i+2));
      m = Integer.parseInt(fila[1].substring(i+3,i+5));
      s = Integer.parseInt(fila[1].substring(i+6,i+8));
     
      //FICF
     }
        catch (NumberFormatException e)
     {
      //CF: Feina
      //INICF

      g = Integer.parseInt(fila[1].substring(i,i+2));
      m = Integer.parseInt(fila[1].substring(i+4,i+6));
      s = Integer.parseInt(fila[1].substring(i+7,i+9));
     
      //FICF
     }
        
            
        switch (nom) {
        case "Casa 1 (AC)":
         
         if (fitxer.equals("inici")) {
          c1 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c1);
          this.i += 1;
       break;
         }
         else
         {
          c1.setSigneFi(signe);
          c1.setGFi(g);
          c1.setMFi(m);
          c1.setSFi(s);
          //c1.setGrauSimpleFi(g,m,s);
          c1.setVelocitat();
          pwLog.println("Creada C1. Velocitat: " + c1.getVelocitat());
		//System.out.println("Creada C1. Velocitat: " + c1.getVelocitat());
       break;
         }
        case "Casa 2":
         if (fitxer.equals("inici")) {
          c2 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c2);
          this.i += 1;
       break;
         }
         else
         {
          c2.setSigneFi(signe);
          c2.setGFi(g);
          c2.setMFi(m);
          c2.setSFi(s);
          //c2.setGrauSimpleFi(g,m,s);
          c2.setVelocitat();
          pwLog.println("Creada C2. Velocitat: " + c2.getVelocitat());
		//System.out.println("Creada C2. Velocitat: " + c2.getVelocitat());
       break;
         }
        case "Casa 3":
         if (fitxer.equals("inici")) {
          c3 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c3);
          this.i += 1;
       break;
         }
         else
         {
          c3.setSigneFi(signe);
          c3.setGFi(g);
          c3.setMFi(m);
          c3.setSFi(s);
          //c3.setGrauSimpleFi(g,m,s);
          c3.setVelocitat();
          pwLog.println("Creada C3. Velocitat: " + c3.getVelocitat());
		//System.out.println("Creada C3. Velocitat: " + c3.getVelocitat());
       break;
         }
        case "Casa 4":
         if (fitxer.equals("inici")) {
          c4 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c4);
          this.i += 1;
       break;
         }
         else
         {
          c4.setSigneFi(signe);
          c4.setGFi(g);
          c4.setMFi(m);
          c4.setSFi(s);
          //c4.setGrauSimpleFi(g,m,s);
          c4.setVelocitat();
          pwLog.println("Creada C4. Velocitat: " + c4.getVelocitat());
		//System.out.println("Creada C4. Velocitat: " + c4.getVelocitat());
       break;
         }
        case "Casa 5":
         if (fitxer.equals("inici")) {
          c5 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c5);
          this.i += 1;
       break;
         }
         else
         {
          c5.setSigneFi(signe);
          c5.setGFi(g);
          c5.setMFi(m);
          c5.setSFi(s);
          //c5.setGrauSimpleFi(g,m,s);
          c5.setVelocitat();
          pwLog.println("Creada C5. Velocitat: " + c5.getVelocitat());
		//System.out.println("Creada C5. Velocitat: " + c5.getVelocitat());
       break;
         }
        case "Casa 6":
         if (fitxer.equals("inici")) {
          c6 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c6);
          this.i += 1;
       break;
         }
         else
         {
          c6.setSigneFi(signe);
          c6.setGFi(g);
          c6.setMFi(m);
          c6.setSFi(s);
          //c6.setGrauSimpleFi(g,m,s);
          c6.setVelocitat();
          pwLog.println("Creada C6. Velocitat: " + c6.getVelocitat());
		//System.out.println("Creada C6. Velocitat: " + c6.getVelocitat());
       break;
         }
        case "Casa 7":
         if (fitxer.equals("inici")) {
          c7 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c7);
          this.i += 1;
       break;
         }
         else
         {
          c7.setSigneFi(signe);
          c7.setGFi(g);
          c7.setMFi(m);
          c7.setSFi(s);
          //c7.setGrauSimpleFi(g,m,s);
          c7.setVelocitat();
          pwLog.println("Creada C7. Velocitat: " + c7.getVelocitat());
		//System.out.println("Creada C7. Velocitat: " + c7.getVelocitat());
       break;
         }
         case "Casa 8":
         if (fitxer.equals("inici")) {
          c8 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c8);
          this.i += 1;
       break;
         }
         else
         {
          c8.setSigneFi(signe);
          c8.setGFi(g);
          c8.setMFi(m);
          c8.setSFi(s);
          //c8.setGrauSimpleFi(g,m,s);
          c8.setVelocitat();
          pwLog.println("Creada C8. Velocitat: " + c8.getVelocitat());
		//System.out.println("Creada C8. Velocitat: " + c8.getVelocitat());
       break;
         }
        case "Casa 9":
         if (fitxer.equals("inici")) {
          c9 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c9);
          this.i += 1;
       break;
         }
         else
         {
          c9.setSigneFi(signe);
          c9.setGFi(g);
          c9.setMFi(m);
          c9.setSFi(s);
          //c9.setGrauSimpleFi(g,m,s);
          c9.setVelocitat();
          pwLog.println("Creada C9. Velocitat: " + c9.getVelocitat());
		//System.out.println("Creada C9. Velocitat: " + c9.getVelocitat());
       break;
         }
        case "Casa 10 (MC)":
         if (fitxer.equals("inici")) {
          c10 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c10);
          this.i += 1;
       break;
         }
         else
         {
          c10.setSigneFi(signe);
          c10.setGFi(g);
          c10.setMFi(m);
          c10.setSFi(s);
          //c10.setGrauSimpleFi(g,m,s);
          c10.setVelocitat();
          pwLog.println("Creada C10. Velocitat: " + c10.getVelocitat());
		//System.out.println("Creada C10. Velocitat: " + c10.getVelocitat());
       break;
         }
        case "Casa 11":
         if (fitxer.equals("inici")) {
          c11 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c11);
          this.i += 1;
       break;
         }
         else
         {
          c11.setSigneFi(signe);
          c11.setGFi(g);
          c11.setMFi(m);
          c11.setSFi(s);
          //c11.setGrauSimpleFi(g,m,s);
          c11.setVelocitat();
          pwLog.println("Creada C11. Velocitat: " + c11.getVelocitat());
		//System.out.println("Creada C11. Velocitat: " + c11.getVelocitat());
       break;
         }
        case "Casa 12":
         if (fitxer.equals("inici")) {
          c12 = new Entitat (this, nom, signe, g, m, s, "", "");
          entitats.add(c12);
          this.i += 1;
       break;
         }
         else
         {
          c12.setSigneFi(signe);
          c12.setGFi(g);
          c12.setMFi(m);
          c12.setSFi(s);
          //c12.setGrauSimpleFi(g,m,s);
          c12.setVelocitat();
          pwLog.println("Creada C12. Velocitat: " + c12.getVelocitat());
		//System.out.println("Creada C12. Velocitat: " + c12.getVelocitat());
       break;
         }
        }
    }
      }
     }
    }
 
 public void lents(Entitat e1, Entitat e2, CSVWriter csvWriter) {
  boolean mateixN = false;
  boolean mateixA = false;
  boolean aplica = false;
  double posicio1 = 0;
  double posicio2 = 0;
  double posicioAnt1 = 0;
  double posicioAnt2 = 0;
  double graus1 = 0;
  double graus2 = 0;
  double grausAnt = 0;
  //calcular el moment (en segons) al qual una entitat (o el seu antisci) estarà al mateix grau que l'altra
  //les posicions es faran en sistema decimal (i es van actualizant al bucle)
  posicio1 = (double) e1.getGIni() + (double) e1.getMIni() / 60 + (double) e1.getSIni() / 3600;
  graus1 = ((e1.ordSigne(e1.getSigneIni())) - 1) * 30 + posicio1;
  posicio2 = (double) e2.getGIni() + (double) e2.getMIni() / 60 + (double) e2.getSIni() / 3600;
  graus2 = ((e2.ordSigne(e2.getSigneIni())) - 1) * 30 + posicio2;
  posicioAnt1 = (double) e1.getGIniAnt() + (double) e1.getMIniAnt() / 60 + (double) e1.getSIniAnt() / 3600;
  grausAnt = ((e1.ordSigne(e1.getSigneIniAnt())) - 1) * 30 + posicioAnt1;

  Aspecte aspecteN = null;
  Aspecte aspecteA = null;
  e1.setCasa("");
  e1.setCasaAnt("");
  e2.setCasa("");
  e2.setCasaAnt("");
  // inicialitzem lents
  e1.setSigneAnt(e1.getSigneIniAnt());
  e2.setSigneAnt(e2.getSigneIniAnt());
  e1.setG(e1.getGIni());
  e1.setM(e1.getMIni());
  e1.setS(e1.getSIni());
  e1.setGAnt(e1.getGIniAnt());
  e1.setMAnt(e1.getMIniAnt());
  e1.setSAnt(e1.getSIniAnt());
  e2.setG(e2.getGIni());
  e2.setM(e2.getMIni());
  e2.setS(e2.getSIni());
  e2.setGAnt(e2.getGIniAnt());
  e2.setMAnt(e2.getMIniAnt());
  e2.setSAnt(e2.getSIniAnt());
    
  for (int i = 0; i < segons; i++) {
	  
	  
   //toca calcular les coordenades de les cases per un moment a lents
   int momH = (int) i / 3600;
   int momM = (int) (i - momH * 3600) / 60;
   int momS = (int) i - momH * 3600 - momM * 60;
   momS += segonIni;
   if (momS > 59) {
    momM++;
    momS -= 60;
   }
    
   momM += minutIni;
   if (momM > 59) {
    momH++;
    momM -= 60;
   }
   
   momH += horaIni;
   if (momH > 23) {
	   momH -= 24;
   }
   horoscop.setHora(diaIni, mesIni, anyIni, momH, momM, momS, zonahoraria);
   horoscop.calcValors();
   casesR = horoscop.casaEmprada.getCasesR();
   /*
   if (i==0 | i == 1) {
	   System.out.println("diaIni: " + diaIni);
	   System.out.println("mesIni: " + mesIni);
	   System.out.println("anyIni: " + anyIni);
	   System.out.println("momH: " + momH);
	   System.out.println("momM: " + momM);
	   System.out.println("momS: " + momS);
	   System.out.println("zonahoraria: " + zonahoraria);
	   System.out.println("CalcUtil.RaG(casesR[0]: " + CalcUtil.RaG(casesR[0]));
	   System.out.println("CalcUtil.RaG(casesR[1]: " + CalcUtil.RaG(casesR[1]));
	   System.out.println("CalcUtil.RaG(casesR[2]: " + CalcUtil.RaG(casesR[2]));
	   System.out.println("CalcUtil.RaG(casesR[3]: " + CalcUtil.RaG(casesR[3]));
	   System.out.println("CalcUtil.RaG(casesR[4]: " + CalcUtil.RaG(casesR[4]));
	   System.out.println("CalcUtil.RaG(casesR[5]: " + CalcUtil.RaG(casesR[5]));
	   System.out.println("CalcUtil.RaG(casesR[6]: " + CalcUtil.RaG(casesR[6]));
	   System.out.println("CalcUtil.RaG(casesR[7]: " + CalcUtil.RaG(casesR[7]));
	   System.out.println("CalcUtil.RaG(casesR[8]: " + CalcUtil.RaG(casesR[8]));
	   System.out.println("CalcUtil.RaG(casesR[9]: " + CalcUtil.RaG(casesR[9]));
	   System.out.println("CalcUtil.RaG(casesR[10]: " + CalcUtil.RaG(casesR[10]));
	   System.out.println("CalcUtil.RaG(casesR[11]: " + CalcUtil.RaG(casesR[11]));
   }
   */
   assignaCases(e1,i);
   assignaCases(e2,i);
   
   /*
   if (!e1.getNom().equals("Lluna")
	   &&  !e1.getNom().equals("Node")
       &&  !e1.getNom().equals("Ur\u00e0")
	   &&  !e1.getNom().equals("Nept\u00fa")
	   &&  !e1.getNom().equals("Plut\u00f3")) {
	   esRegent(e1, i);
   }
   
   if (!e2.getNom().equals("Lluna")
	   &&  !e2.getNom().equals("Node")
       &&  !e2.getNom().equals("Ur\u00e0")
	   &&  !e2.getNom().equals("Nept\u00fa")
	   &&  !e2.getNom().equals("Plut\u00f3")) {
	   esRegent(e2, i);
   }
   */
   /*
	//if (e1.getNom().equals("J\u00fapiter") && e2.getNom().equals("Saturn")) {
	//if (e1.getNom().equals("Lluna") && e2.getNom().equals("Mercuri")) {
	//if (e1.getNom().equals("Mercuri") && e2.getNom().equals("Venus")) {
		if (i == 559 | i== 4807 | i == 4808) {
			System.out.println("i: " + i);
			System.out.println("e1.getAngle(0): " + e1.getAngle(0));
			System.out.println("e1.getAngle(1): " + e1.getAngle(1));
			System.out.println("e1.getAngle(2): " + e1.getAngle(2));
			System.out.println("e2.getAngle(0): " + e2.getAngle(0));
			System.out.println("e2.getAngle(1): " + e2.getAngle(1));
			System.out.println("e2.getAngle(2): " + e2.getAngle(2));
		}
	}
	*/
	
   if (mateixN == true) {
	   if (!e1.getNom().equals("Lluna")
	   &&  !e1.getNom().equals("Node")
       &&  !e1.getNom().equals("Ur\u00e0")
	   &&  !e1.getNom().equals("Nept\u00fa")
	   &&  !e1.getNom().equals("Plut\u00f3")
	   &&   aspecteN.getRegent1(2) == null
	   &&   e1.getId() < 7
	   &&  !e1.getAngle(i).equals("")) {
		setAngles(aspecteN, e1, i);
	   }
	   if (!e2.getNom().equals("Lluna")
	   &&  !e2.getNom().equals("Node")
       &&  !e2.getNom().equals("Ur\u00e0")
	   &&  !e2.getNom().equals("Nept\u00fa")
	   &&  !e2.getNom().equals("Plut\u00f3")
	   &&   aspecteN.getRegent2(2) == null
	   &&   e2.getId() < 7
	   &&  !e2.getAngle(i).equals("")) {
		setAngles(aspecteN, e2, i);
		}
		/*
   if (e1.getNom().equals("Mercuri") && e2.getNom().equals("Venus")) {
		if (i == 558 | i== 4807 | i == 4808) {
			System.out.println("i: " + i);
			System.out.println("aspecteN.getRegent1(0): " + aspecteN.getRegent1(0));
			System.out.println("aspecteN.getRegent1(1): " + aspecteN.getRegent1(1));
			System.out.println("aspecteN.getRegent1(2): " + aspecteN.getRegent1(2));
			System.out.println("aspecteN.getRegent2(0): " + aspecteN.getRegent2(0));
			System.out.println("aspecteN.getRegent2(1): " + aspecteN.getRegent2(1));
			System.out.println("aspecteN.getRegent2(2): " + aspecteN.getRegent2(2));
	   }
		}
		*/
   }
   
   if (mateixA == true) {
	   if (!e1.getNom().equals("Lluna")
	   &&  !e1.getNom().equals("Node")
       &&  !e1.getNom().equals("Ur\u00e0")
	   &&  !e1.getNom().equals("Nept\u00fa")
	   &&  !e1.getNom().equals("Plut\u00f3")
	   &&   aspecteA.getRegent1(2) == null
	   &&   e1.getId() < 7
	   &&  !e1.getAngle(i).equals("")) {
		setAngles(aspecteA, e1, i);
	   }
	   if (!e2.getNom().equals("Lluna")
	   &&  !e2.getNom().equals("Node")
       &&  !e2.getNom().equals("Ur\u00e0")
	   &&  !e2.getNom().equals("Nept\u00fa")
	   &&  !e2.getNom().equals("Plut\u00f3")
	   &&   aspecteA.getRegent2(2) == null
	   &&   e2.getId() < 7
	   &&  !e2.getAngle(i).equals("")) {
		setAngles(aspecteA, e2, i);
	   }
	   /*
	   if (e1.getNom().equals("Mercuri") && e2.getNom().equals("Venus")) {
		if (i == 559 | i== 4807 | i == 4808) {
			System.out.println("i: " + i);
			System.out.println("aspecteA.getRegent1(0): " + aspecteA.getRegent1(0));
			System.out.println("aspecteA.getRegent1(1): " + aspecteA.getRegent1(1));
			System.out.println("aspecteA.getRegent1(2): " + aspecteA.getRegent1(2));
			System.out.println("aspecteA.getRegent2(0): " + aspecteA.getRegent2(0));
			System.out.println("aspecteA.getRegent2(1): " + aspecteA.getRegent2(1));
			System.out.println("aspecteA.getRegent2(2): " + aspecteA.getRegent2(2));
	   }
	   */
   }
   
   aplica = false;
   
   if ((e1.getNom().equals("Lluna")
			 | e1.getNom().equals("Ur\u00e0")
			 | e1.getNom().equals("Nept\u00fa")
			 | e1.getNom().equals("Plut\u00f3"))
			 && (e2.getNom().equals("Lluna")
			 | e2.getNom().equals("Ur\u00e0")
			 | e2.getNom().equals("Nept\u00fa")
			 | e2.getNom().equals("Plut\u00f3"))) {
	   aplica = true;
   }
     
   if (!e1.getNom().equals("Lluna")
		   && !e1.getNom().equals("Ur\u00e0")
		   && !e1.getNom().equals("Nept\u00fa")
		   && !e1.getNom().equals("Plut\u00f3")
		   && !e1.getAngle(i).equals("")) {
	   aplica = true;
   }
   
   if (!e2.getNom().equals("Lluna")
			 && !e2.getNom().equals("Ur\u00e0")
			 && !e2.getNom().equals("Nept\u00fa")
			 && !e2.getNom().equals("Plut\u00f3")
			 && !e2.getAngle(i).equals("")) {
	   aplica = true;
   }
   
   // calculem els graus simples
   e1.setGrauSimple(calcularGrauSimple(e1.getG(), e1.getM(), e1.getS()));
   e1.setGrauSimpleAnt(calcularGrauSimple(e1.getGAnt(), e1.getMAnt(), e1.getSAnt()));
   e2.setGrauSimple(calcularGrauSimple(e2.getG(), e2.getM(), e2.getS()));

/*
	//if (e1.getNom().equals("Nept\u00fa") && e2.getNom().equals("Plut\u00f3")) {
	//if (e1.getNom().equals("Lluna") && e2.getNom().equals("Saturn")
	//if (e1.getNom().equals("Venus") && e2.getNom().equals("Ur\u00e0")
	if (e1.getNom().equals("Sol") && e2.getNom().equals("Mercuri")
	&& i == 3897) {
	 System.out.println("i: " + i);
   	 System.out.println("graus1: " + graus1);
   	 System.out.println("graus2: " + graus2);
		 System.out.println("posicio1: " + posicio1);
		 System.out.println("posicio2: " + posicio2);
	   //System.out.println("momH: " + momH);
   	 System.out.println("momM: " + momM);
   	 System.out.println("momS: " + momS);
		 System.out.println("e1.getSigne(4807): " + e1.getSigne(0));
		 System.out.println("e2.getSigne(4807): " + e2.getSigne(0));
		 System.out.println("e1.getCasa(): " + e1.getCasa());
		 System.out.println("e1.getCasaAnt(): " + e1.getCasaAnt());
		 System.out.println("e2.getCasa(): " + e2.getCasa());
		 System.out.println("e2.getCasaAnt(): " + e2.getCasaAnt());
		 System.out.println("aplica: " + aplica);
    }
*/
   
   // normal a lents
   if (aplica == true
   && ((e1.getGrauSimple() - e2.getGrauSimple() < 2 
   &&   e1.getGrauSimple() - e2.getGrauSimple() > -2)
   && (e1.getNom().equals("Lluna")
     | e2.getNom().equals("Lluna")
     | e1.esAngular()
     | e2.esAngular()))) {
    if (mateixN == false) {
    	
    	/*
	 	if (e1.getNom().equals("Lluna") && e2.getNom().equals("Mercuri")) {
	 		if (i==0) {
	 			System.out.println("i: " + i);
				System.out.println("graus1: " + graus1);
				System.out.println("graus2: " + graus2);
				System.out.println("distancia: " + distancia);
				System.out.println("momH: " + momH);
				System.out.println("momM: " + momM);
				System.out.println("momS: " + momS);
				System.out.println("aspecteN.getRegent1(0): " + aspecteN.getRegent1(0));
				System.out.println("aspecteN.getRegent1(1): " + aspecteN.getRegent1(1));
				System.out.println("aspecteN.getRegent1(2): " + aspecteN.getRegent1(2));
	 		}
	 	}
	 	*/
    	
    	// calcul del tipus d'aspecte
    	int distancia = (int) (graus1 - graus2);
    	String tipus = graus.get(distancia);
    	
    	// aspecte per normal de lents
    	// si l'aspecte es dóna al descans (en cas que estigui informat), l'aspecte no es crearà
    	if (horaIniDescans == 0
    			&& minutIniDescans == 0
    			&&  segonIniDescans == 0) {
    		mateixN = true;
    		aspecteN = new Aspecte (e1,false,e2,i,momH,momM,momS,tipus);
    		aspectes.add(aspecteN);
    	}
    	else
    	{
    		// hi ha 2 situacions possibles que creen l'aspecte
    		// 1. comen�a abans del descans
    		if ((momH < horaIniDescans
    				|  (momH == horaIniDescans && momM < minutIniDescans)
    				|  (momH == horaIniDescans && momM == minutIniDescans && momS < segonIniDescans))
    		// 2. acaba despr�s del descans
    				|  (momH > horaFiDescans
    						| (momH == horaFiDescans && momM > minutFiDescans)
    						| (momH == horaFiDescans && momM == minutFiDescans && momS > segonFiDescans))) {
    			mateixN = true;
    			// calcul del tipus d'aspecte
    			aspecteN = new Aspecte (e1,false,e2,i,momH,momM,momS,tipus);
    			aspectes.add(aspecteN);
    		}
    	}
    	
    	if (mateixN == true) {
    		// avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
    		for (Gol gol: gols) {
    			if (i == gol.getSegons()) {
    				String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
    				e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
    				e2.getSigne(i) + ") " + e2.getAngle(i);
    				gol.setAspecte(asp);
    			}
    		}
    	}
    }
    else
	{
    	// l'aspecte ja est� creat i durant el per�ode hi ha un gol
		// calcul del tipus d'aspecte
		int distancia = (int) (graus1 - graus2);
		String tipus = graus.get(distancia);
		// avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
		for (Gol gol: gols) {
			if (i == gol.getSegons()) {
				String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
				e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
				e2.getSigne(i) + ") " + e2.getAngle(i);
				gol.setAspecte(asp);
			}
		}
	}
   }
   else
   {
    if (mateixN == true) {
     // aprofitem per calcular també el moment final de l'aspecte
     aspecteN.setMomFi(momH, momM, momS);
     
	 /*
	 if (aspecteN.getNom1().equals("Lluna") && aspecteN.getNom2().equals("Mercuri")) {
		System.out.println("i: " + i);
		System.out.println("aspecteN.getRegent1(i): " + aspecteN.getRegent1(i));
	 }
	 */
	 
	 // calcul del tipus d'aspecte
	 int distancia = (int) (graus1 - graus2);
	 String tipus = graus.get(distancia);
	 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
	 for (Gol gol: gols) {
		 if (i == gol.getSegons()) {
			 String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
			 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
			 e2.getSigne(i) + ") " + e2.getAngle(i);
			 gol.setAspecte(asp);
		 }
	 }
	 
     escriureCSV(aspecteN, csvWriter);
     mateixN = false;
    }
   }
   
   /*
   //if (e1.getNom().equals("Lluna") && e2.getNom().equals("Mercuri") && (i == 0 | i == 1 | i == 2))
   //if (e1.getNom().equals("Sol") && e2.getNom().equals("Mercuri")
	&& (i == 3897 | i == 3898)) {
		 //System.out.println("i: " + i);
		 //System.out.println("grausAnt: " + grausAnt);
    	 //System.out.println("graus2: " + graus2);
		 //System.out.println("posicioAnt1: " + posicioAnt1);
		 //System.out.println("posicio2: " + posicio2);
		 //System.out.println("e1.getGAnt(): " + e1.getGAnt());
		 //System.out.println("e1.getMAnt(): " + e1.getMAnt());
		 //System.out.println("e1.getSAnt(): " + e1.getSAnt());
		 //System.out.println("e1.getGrauSimpleAnt(): " + e1.getGrauSimpleAnt());
		 //System.out.println("e2.getG(): " + e2.getG());
		 //System.out.println("e2.getM(): " + e2.getM());
		 //System.out.println("e2.getS(): " + e2.getS());
		 //System.out.println("e2.getGrauSimple(): " + e2.getGrauSimple());
 	     //System.out.println("momH: " + momH);
    	 //System.out.println("momM: " + momM);
    	 //System.out.println("momS: " + momS);
		 //System.out.println("e1.esAngular(): " + e1.esAngular());
		 //System.out.println("e2.esAngular(): " + e2.esAngular());
		 //System.out.println("aplica: " + aplica);
	   }
	*/
	 
   // antisci a lents
   if (aplica == true
		   && ((e1.getGrauSimpleAnt() - e2.getGrauSimple() < 2
				   &&   e1.getGrauSimpleAnt() - e2.getGrauSimple() > -2)
   && (e1.getNom().equals("Lluna")
     | e2.getNom().equals("Lluna")
     | e1.esAngular()
     | e2.esAngular()))) {
    if (mateixA == false) {
    	
    	// calcul del tipus d'aspecte
    	int distancia = (int) (grausAnt - graus2);
		String tipus = graus.get(distancia);
		
    	// aspecte per antisci de lents
    	// si l'aspecte es dóna al descans (en cas que estigui informat), l'aspecte no es crearà
    	if (horaIniDescans == 0
    			&& minutIniDescans == 0
    			&&  segonIniDescans == 0) {
    		mateixA = true;
			aspecteA = new Aspecte (e1,true,e2,i,momH,momM,momS,tipus);
    		aspectes.add(aspecteA);
    	}
    	else
    	{
    		// hi ha 2 situacions possibles que creen l'aspecte
    		// 1. comen�a abans del descans
    		if ((momH < horaIniDescans
    				|  (momH == horaIniDescans && momM < minutIniDescans)
    				|  (momH == horaIniDescans && momM == minutIniDescans && momS < segonIniDescans))
    		// 2. acaba despr�s del descans
    				|  (momH > horaFiDescans
    						| (momH == horaFiDescans && momM > minutFiDescans)
    						| (momH == horaFiDescans && momM == minutFiDescans && momS > segonFiDescans))) {
    			mateixA = true;
		    	aspecteA = new Aspecte (e1,true,e2,i,momH,momM,momS,tipus);
    			aspectes.add(aspecteA);
    		}
    	}
    	
    	if (mateixA == true) {
    		// avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
    		 for (Gol gol: gols) {
    			 if (i == gol.getSegons()) {
    				 String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
    				 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
    				 e2.getSigne(i) + ") " + e2.getAngle(i);
    				 gol.setAspecte(asp);
    			 }
    		 }
    	}
    }
	else
	{
		// l'aspecte ja est� creat i durant el per�ode hi ha un gol
		// aspecte per antisci de lents
		int distancia = (int) (grausAnt - graus2);
		String tipus = graus.get(distancia);
		// avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
		for (Gol gol: gols) {
			if (i == gol.getSegons()) {
				String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
				e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
				e2.getSigne(i) + ") " + e2.getAngle(i);
				gol.setAspecte(asp);
			}
		}
	}
   }
   else
   {
    if (mateixA == true) {
     //aprofitem per calcular també el moment final de l'aspecte
	 /*
	 if (aspecteA.getNom1().equals("Lluna") && aspecteA.getNom2().equals("Mercuri")) {
		System.out.println("i: " + i);
		System.out.println("aspecteN.getRegent1(0): " + aspecteN.getRegent1(0));
		System.out.println("aspecteN.getRegent1(1): " + aspecteN.getRegent1(1));
		System.out.println("aspecteN.getRegent1(2): " + aspecteN.getRegent1(2));
	 }
	 */
     aspecteA.setMomFi(momH, momM, momS);
     escriureCSV(aspecteA, csvWriter);
	 
	 // aspecte per antisci de lents
	 int distancia = (int) (grausAnt - graus2);
	 String tipus = graus.get(distancia);
	 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
	 for (Gol gol: gols) {
		 if (i == gol.getSegons()) {
			 String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
			 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
			 e2.getSigne(i) + ") " + e2.getAngle(i);
			 gol.setAspecte(asp);
		 }
	 }
	 
     mateixA = false;
    }
   }
  
  /*
	if (e1.getNom().equals("Nept\u00fa") && e2.getNom().equals("Plut\u00f3")) {
		if (i == 0) {
    	 //System.out.println("i: " + i);
    	 System.out.println("graus1: " + graus1);
    	 System.out.println("graus2: " + graus2);
		 System.out.println("grausAnt: " + grausAnt);
		 System.out.println("posicio1: " + posicio1);
		 System.out.println("posicio2: " + posicio2);
		 System.out.println("posicioAnt1: " + posicioAnt1);
    	 //System.out.println("distancia: " + distancia);
    	 //System.out.println("momH: " + momH);
    	 //System.out.println("momM: " + momM);
    	 //System.out.println("momS: " + momS);
		}
     }
	 */
   
   // velocitat en segons angulars, posici� en graus angulars
   // entitat 1 a lents
   posicio1 += e1.getVelocitat() / 3600;
   graus1 += e1.getVelocitat() / 3600;
   posicioAnt1 = 30 - posicio1;
   grausAnt = ((e1.ordSigne(e1.getSigneIniAnt())) - 1) * 30 + posicioAnt1;

   if (posicio1 > 30) {
	   posicio1 -= 30;
	   grausAnt = ((e1.ordSigne(e1.getSigneFiAnt())) - 1) * 30 + posicioAnt1;
	   // si canviava de signe aqu� ja fixarem el signe a cada segon si no s'havia fet
	   if (e1.getSigne(0).equals("")) {
		   for (int x = 0; x <= segons; x++) {
			   if (x < i) {
				   e1.setSigne(x, e1.getSigneIni());
			   }
			   else
			   {
				   e1.setSigne(x, e1.getSigneFi());
			   }
		   }
	   }
   }
   
   if (posicio1 < 0) {
	   posicio1 += 30;
	   grausAnt = ((e1.ordSigne(e1.getSigneFiAnt())) - 1) * 30 + posicioAnt1;
	   // si canviava de signe aquí ja fixarem el signe a cada segon si no s'havia fet
	   if (e1.getSigne(0).equals("")) {
		   for (int x = 0; x <= segons; x++) {
			   if (x < i) {
				   e1.setSigne(x, e1.getSigneIni());
			   }
			   else
			   {
				   e1.setSigne(x, e1.getSigneFi());
			   }
		   }
	   }
   }
   
   posicioAnt1 = 30 - posicio1;
   
   if (graus1 > 360) {
       graus1 -= 360;
   }

   if (graus1 < 0) {
       graus1 += 360;
   }
   
   // guardar graus a lents pels graus simples i casa de l'antisci
   guardarGraus(e1, posicio1);
   guardarGrausAnt(e1, posicioAnt1);
   
   // entitat 2 a lents
   posicio2 += e2.getVelocitat() / 3600;
   graus2 += e2.getVelocitat() / 3600;
   
   if (posicio2 > 30) {
	   posicio2 -= 30;
	   // si canviava de signe aquí ja fixarem el signe a cada segon si no s'havia fet
	   if (e2.getSigne(0).equals("")) {
		   for (int x = 0; x <= segons; x++) {
			   if (x < i) {
				   e2.setSigne(x, e2.getSigneIni());
			   }
			   else
			   {
				   e2.setSigne(x, e2.getSigneFi());
			   }
		   }
	   }
   }
   
   if (posicio2 < 0) {
	   posicio2 += 30;
	   // si canviava de signe aquí ja fixarem el signe a cada segon si no s'havia fet
	   if (e2.getSigne(0).equals("")) {
		   for (int x = 0; x <= segons; x++) {
			   if (x < i) {
				   e2.setSigne(x, e2.getSigneIni());
			   }
			   else
			   {
				   e2.setSigne(x, e2.getSigneFi());
			   }
		   }
	   }
   }
   
   // per calcular la casa de l'antisci
   posicioAnt2 = 30 - posicio2;
   
   if (graus2 > 360) {
       graus2 -= 360;
   }
   
   if (graus2 < 0) {
       graus2 += 360;
   }
   
   // guardar graus a lents pels graus simples i casa de l'antisci
   guardarGraus(e2, posicio2);
   guardarGrausAnt(e2, posicioAnt2);
 }
  // completem aspectes a lents
  // ha recorregut tots els segons: completem el que faci falta
  // s'ha calculat momIni per� no momFi: l'aspecte �s fins el final del partit
  if (mateixN == true) {
   aspecteN.setHMomFi(horaFi);
   aspecteN.setMMomFi(minutFi);
   aspecteN.setSMomFi(segonFi);
   escriureCSV(aspecteN, csvWriter);
  }
  if (mateixA == true) {
   aspecteA.setHMomFi(horaFi);
   aspecteA.setMMomFi(minutFi);
   aspecteA.setSMomFi(segonFi);
   escriureCSV(aspecteA, csvWriter);
  }
 }
 
 public void cases(Entitat e1, Entitat e2, CSVWriter csvWriter) {
  boolean mateixN = false;
  boolean mateixA = false;
  boolean aplica = false;
  double posicio1 = 0;
  double posicioCasa = 0;
  double posicioAnt = 0;
  double graus1 = 0;
  double grausCasa = 0;
  double grausAnt = 0;
  posicio1 = (double) e1.getGIni() + (double) e1.getMIni() / 60 + (double) e1.getSIni() / 3600;
  graus1 = ((e1.ordSigne(e1.getSigneIni())) - 1) * 30 + posicio1;
  posicioAnt = (double) e1.getGIniAnt() + (double) e1.getMIniAnt() / 60 + (double) e1.getSIniAnt() / 3600;
  grausAnt = ((e1.ordSigne(e1.getSigneIniAnt())) - 1) * 30 + posicioAnt;
  Aspecte aspecteN = null;
  Aspecte aspecteA = null;
  e1.setCasa("");
  e1.setCasaAnt("");
  // inicialitzem lent i casa
  e1.setSigneAnt(e1.getSigneIniAnt());
  e2.setSigneAnt(e2.getSigneIniAnt());
  e1.setG(e1.getGIni());
  e1.setM(e1.getMIni());
  e1.setS(e1.getSIni());
  e1.setGAnt(e1.getGIniAnt());
  e1.setMAnt(e1.getMIniAnt());
  e1.setSAnt(e1.getSIniAnt());
  
  for (int i = 0; i < segons; i++) {
   //toca calcular les coordenades de les cases per un moment i
   int momH = (int) i / 3600;
   int momM = (int) (i - momH * 3600) / 60;
   int momS = (int) i - momH * 3600 - momM * 60;
   momS += segonIni;
   if (momS > 59) {
    momM++;
    momS -= 60;
   }
    
   momM += minutIni;
   if (momM > 59) {
    momH++;
    momM -= 60;
   }
   
   momH += horaIni;
   if (momH > 23) {
	   momH -= 24;
   }

   horoscop.setHora(diaIni, mesIni, anyIni, momH, momM, momS, zonahoraria);
   horoscop.calcValors();
   casesR = horoscop.casaEmprada.getCasesR();
   if (e2.getNom().equals("Casa 1 (AC)")) {
	   grausCasa = CalcUtil.RaG(casesR[0]);
	   posicioCasa = CalcUtil.RaG(casesR[0]) % 30;
   }
   else
   {
	   grausCasa = CalcUtil.RaG(casesR[3]);
	   posicioCasa = CalcUtil.RaG(casesR[3]) % 30;
   }
   
   // guardar graus a cases pels graus simples
   guardarGraus(e2, posicioCasa);
   
   // calculem el signe on esta la casa a cases
   e2.setSigne(i, e2.cases((int)Math.floor(grausCasa / 30) + 1));
   
   aplica = false;
   assignaCases(e1,i);
   
   /*
   if (!e1.getNom().equals("Lluna")
	   &&  !e1.getNom().equals("Node")
       &&  !e1.getNom().equals("Ur\u00e0")
	   &&  !e1.getNom().equals("Nept\u00fa")
	   &&  !e1.getNom().equals("Plut\u00f3")) {
	esRegent(e1, i);
   }
   */
   
   if (mateixN == true) {
	   if (!e1.getNom().equals("Lluna")
	   &&  !e1.getNom().equals("Node")
       &&  !e1.getNom().equals("Ur\u00e0")
	   &&  !e1.getNom().equals("Nept\u00fa")
	   &&  !e1.getNom().equals("Plut\u00f3")
	   &&   aspecteN.getRegent1(2) == null
	   &&   e1.getId() < 7
	   &&  !e1.getAngle(i).equals("")) {
		setAngles(aspecteN, e1, i);
	   }
   }
   
   if (mateixA == true) {
	   if (!e1.getNom().equals("Lluna")
	   &&  !e1.getNom().equals("Node")
       &&  !e1.getNom().equals("Ur\u00e0")
	   &&  !e1.getNom().equals("Nept\u00fa")
	   &&  !e1.getNom().equals("Plut\u00f3")
	   &&   aspecteA.getRegent1(2) == null
	   &&   e1.getId() < 7
	   &&  !e1.getAngle(i).equals("")) {
		setAngles(aspecteA, e1, i);
	   }
   }
   
   aplica = false;
   
   if (e1.getNom().equals("Lluna")
			 | e1.getNom().equals("Ur\u00e0")
			 | e1.getNom().equals("Nept\u00fa")
			 | e1.getNom().equals("Plut\u00f3")
			 | e1.getNom().equals("Node")) {
	   aplica = true;
 }
 
 if (!e1.getNom().equals("Lluna")
		   && !e1.getNom().equals("Ur\u00e0")
		   && !e1.getNom().equals("Nept\u00fa")
		   && !e1.getNom().equals("Plut\u00f3")
		   && !e1.getNom().equals("Node")
		   && !e1.getAngle(i).equals("")) {
	 aplica = true;
 }
 
 /*
	 if (e1.getNom().equals("Node") && e2.getNom().equals("Casa 4")) {
		 if (i == 720) {
    	 System.out.println("i: " + i);
    	 System.out.println("graus1: " + graus1);
		 System.out.println("e1.getVelocitat(): " + e1.getVelocitat());
    	 System.out.println("grausCasa: " + grausCasa);
    	 System.out.println("momH: " + momH);
    	 System.out.println("momM: " + momM);
    	 System.out.println("momS: " + momS);
		 System.out.println("e1.getAngle(i): " + e1.getAngle(i));
		 System.out.println("posicioAnt: " + posicioAnt);
		 System.out.println("posicioCasa: " + posicioCasa);
		 System.out.println("e1.esAngular(): " + e1.esAngular());
		 System.out.println("aplica: " + aplica);
		 }
     }
*/

 	// calculem els graus simples
 	e1.setGrauSimple(calcularGrauSimple(e1.getG(), e1.getM(), e1.getS()));
 	e1.setGrauSimpleAnt(calcularGrauSimple(e1.getGAnt(), e1.getMAnt(), e1.getSAnt()));
 	e2.setGrauSimple(calcularGrauSimple(e2.getG(), e2.getM(), e2.getS()));
 
   // normal a cases
   if (aplica == true
		   && ((e1.getGrauSimple() - e2.getGrauSimple() < 2
				   &&   e1.getGrauSimple() - e2.getGrauSimple() > -2)
				   && (e1.esAngular()
						   | e1.getNom().equals("Lluna")
						   | e1.getNom().equals("Node")))) {
	   if (mateixN == false) {
     
		   // c�lcul del tipus d'aspecte
		   int distancia = (int) (graus1 - grausCasa);
		   String tipus = graus.get(distancia);
	 
	 
		   // si l'aspecte es dóna al descans (en cas que estigui informat), l'aspecte no es crearà
		   if (horaIniDescans == 0
				   && minutIniDescans == 0
				   && segonIniDescans == 0) {
			   mateixN = true;
			   aspecteN = new Aspecte (e1,false,e2,i,momH,momM,momS,tipus);
			   aspectes.add(aspecteN);
		   }
		   else
		   {
			   // hi ha 2 situacions possibles que creen l'aspecte
			   // 1. comen�a abans del descans
			   if ((momH < horaIniDescans
					   |  (momH == horaIniDescans && momM < minutIniDescans)
					   |  (momH == horaIniDescans && momM == minutIniDescans && momS < segonIniDescans))
			   // 2. acaba despr�s del descans
					   |  (momH > horaFiDescans
							   | (momH == horaFiDescans && momM > minutFiDescans)
							   | (momH == horaFiDescans && momM == minutFiDescans && momS > segonFiDescans))) {
				   mateixN = true;
				   aspecteN = new Aspecte (e1,false,e2,i,momH,momM,momS,tipus);
				   aspectes.add(aspecteN);
			   }
		   }
 	 
		   if (mateixN == true) {
			   // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
			   for (Gol gol: gols) {
				   if (i == gol.getSegons()) {
					   String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
					   e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
				 
					   switch (e2.getNom()) {
					   case "Casa 1 (AC)":
						   asp += "C7 (" + c7.getSigne(i) + ")";
						   break;
					 
					   case "Casa 4":
						   asp += "C10 (" + c10.getSigne(i) + ")";
						   break;
					   }
				 			 
					   gol.setAspecte(asp);
				   }
			   }
		   }
	   }
	   else
	   {
		   // l'aspecte ja s'ha creat i es d�na un gol durant el per�ode
		   //calcul del tipus d'aspecte
		   int distancia = (int) (graus1 - grausCasa);
		   String tipus = graus.get(distancia);
		   
		   // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
		   for (Gol gol: gols) {
			   if (i == gol.getSegons()) {
				   String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
				   e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
				   switch (e2.getNom()) {
				   case "Casa 1 (AC)":
					   asp += "C7 (" + c7.getSigne(i) + ")";
					   break;
				 
				   case "Casa 4":
					   asp += "C10 (" + c10.getSigne(i) + ")";
					   break;
				   }
			 			 
				   gol.setAspecte(asp);
			   }
		   }
	   }
   }
   else
   {
    if (mateixN == true) {
     //aprofitem per calcular també el moment final de l'aspecte
     aspecteN.setMomFi(momH, momM, momS);
	 escriureCSV(aspecteN, csvWriter);
	 
	 //calcul del tipus d'aspecte
	 int distancia = (int) (graus1 - grausCasa);
	 String tipus = graus.get(distancia); 
	 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
	 for (Gol gol: gols) {
		 if (i == gol.getSegons()) {
			 String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
			 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
			 switch (e2.getNom()) {
				 case "Casa 1 (AC)":
				 asp += "C7 (" + c7.getSigne(i) + ")";
				 break;
				 
				 case "Casa 4":
				 asp += "C10 (" + c10.getSigne(i) + ")";
				 break;
			 }
			 			 
			 gol.setAspecte(asp);
		 }
	 }
	 
	 mateixN = false;
    }
   }
   
   // antisci a cases
   if (aplica == true
		   && ((e1.getGrauSimpleAnt() - e2.getGrauSimple() < 2
				   &&   e1.getGrauSimpleAnt() - e2.getGrauSimple() > -2)
     && (e1.getNom().equals("Lluna")
     | e1.esAngular()))) {
    if (mateixA == false) {

		//calcul del tipus d'aspecte
		int distancia = (int) (grausAnt - grausCasa);
		String tipus = graus.get(distancia); 
	 
		/*
		if (e1.getNom().equals("Node") && e2.getNom().equals("Casa 4")) {
    	 System.out.println("i: " + i);
    	 System.out.println("grausAnt: " + grausAnt);
    	 System.out.println("grausCasa: " + grausCasa);
    	 System.out.println("distancia: " + distancia);
    	 System.out.println("momH: " + momH);
    	 System.out.println("momM: " + momM);
    	 System.out.println("momS: " + momS);
		 //System.out.println("e1.getSigne(i): " + e1.getSigne(i));
		}
		*/
	 
		// si l'aspecte es dóna al descans (en cas que estigui informat), l'aspecte no es crearà
    	if (horaIniDescans == 0
    			&& minutIniDescans == 0
    			&&  segonIniDescans == 0) {
    		mateixA = true;
			aspecteA = new Aspecte (e1,true,e2,i,momH,momM,momS,tipus);
    		aspectes.add(aspecteA);
    	}
    	else
    	{
    		// hi ha 2 situacions possibles que creen l'aspecte
    		// 1. comença abans del descans
    		if ((momH < horaIniDescans
    				|  (momH == horaIniDescans && momM < minutIniDescans)
    				|  (momH == horaIniDescans && momM == minutIniDescans && momS < segonIniDescans))
    		// 2. acaba després del descans
    				|  (momH > horaFiDescans
    						| (momH == horaFiDescans && momM > minutFiDescans)
    						| (momH == horaFiDescans && momM == minutFiDescans && momS > segonFiDescans))) {
    			mateixA = true;
		    	aspecteA = new Aspecte (e1,true,e2,i,momH,momM,momS,tipus);
    			aspectes.add(aspecteA);
    		}
    	}
	 
		if (mateixA == true) {
		    // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
			for (Gol gol: gols) {
				if (i == gol.getSegons()) {
					String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
					e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
					switch (e2.getNom()) {
						case "Casa 1 (AC)":
						asp += "C7 (" + c7.getSigne(i) + ")";
						break;
				 
						case "Casa 4":
						asp += "C10 (" + c10.getSigne(i) + ")";
						break;
					}
			 			 
					gol.setAspecte(asp);
				}
			}
		}
	}
	else
	{
		// l'aspecte ja està creat i es dóna un gol durant el període
		int distancia = (int) (grausAnt - grausCasa);
		String tipus = graus.get(distancia); 
		// avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
		for (Gol gol: gols) {
		 if (i == gol.getSegons()) {
			 String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
			 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
			 switch (e2.getNom()) {
				 case "Casa 1 (AC)":
				 asp += "C7 (" + c7.getSigne(i) + ")";
				 break;
				 
				 case "Casa 4":
				 asp += "C10 (" + c10.getSigne(i) + ")";
				 break;
			 }
			 			 
			 gol.setAspecte(asp);
		 }
	 }
	}
   }
   else
   {
    if (mateixA == true) {
     //aprofitem per calcular també el moment final de l'aspecte
     aspecteA.setMomFi(momH, momM, momS);
     escriureCSV(aspecteA, csvWriter);
	 
	 int distancia = (int) (grausAnt - grausCasa);
	 String tipus = graus.get(distancia); 
	 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
	 for (Gol gol: gols) {
		 if (i == gol.getSegons()) {
			 String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
			 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
			 switch (e2.getNom()) {
				 case "Casa 1 (AC)":
				 asp += "C7 (" + c7.getSigne(i) + ")";
				 break;
				 
				 case "Casa 4":
				 asp += "C10 (" + c10.getSigne(i) + ")";
				 break;
			 }
			 			 
			 gol.setAspecte(asp);
		 }
	 }
	 
     mateixA = false;
    }
   }
   
   // velocitat en segons angulars, posici� en graus angulars
   // entitat 1 a cases
   posicio1 += e1.getVelocitat() / 3600;
   graus1 += e1.getVelocitat() / 3600;
   posicioAnt = 30 - posicio1;
   grausAnt = ((e1.ordSigne(e1.getSigneIniAnt())) - 1) * 30 + posicioAnt;
   
   if (posicio1 > 30) {
	   posicio1 -= 30;
	   posicioAnt = 30 - posicio1;
	   grausAnt = ((e1.ordSigne(e1.getSigneFiAnt())) - 1) * 30 + posicioAnt;
	   // per lents totes les posicions ja haurien d'estar informades
   }
   
   if (posicio1 < 0) {
	   posicio1 += 30;
	   posicioAnt = 30 - posicio1;
	   grausAnt = ((e1.ordSigne(e1.getSigneFiAnt())) - 1) * 30 + posicioAnt;
	   // per lents totes les posicions ja haurien d'estar informades
   }
   
   if (graus1 > 360) {
       graus1 -= 360;
   }
   
   // guardar graus a cases pels graus simples i casa de l'antisci
   guardarGraus(e1, posicio1);
   guardarGrausAnt(e1, posicioAnt);
 }
  // ha recorregut tots els segons: completem el que faci falta
  // s'ha calculat momIni però no momFi: l'aspecte es dóna fins el final del partit
  if (mateixN == true) {
   aspecteN.setHMomFi(horaFi);
   aspecteN.setMMomFi(minutFi);
   aspecteN.setSMomFi(segonFi);
   escriureCSV(aspecteN, csvWriter);
  }
  if (mateixA == true) {
   aspecteA.setHMomFi(horaFi);
   aspecteA.setMMomFi(minutFi);
   aspecteA.setSMomFi(segonFi);
   escriureCSV(aspecteA, csvWriter);
  }
 }
 
 public void fortunes(Entitat e1, Entitat e2, CSVWriter csvWriter) {
  boolean mateixN = false;
  boolean mateixA = false;
  boolean aplica = false;
  double posicio1 = 0;
  double posicioPart = 0;
  double posicioAnt1 = 0;
  double posicioAnt2 = 0;
  double graus1 = 0;
  double grausPart = 0;
  double grausAnt1 = 0;
  double grausLluna = 0;
  double grausSol = 0;
  double grausMart = 0;
  double grausSaturn = 0;
  Aspecte aspecteN = null;
  Aspecte aspecteA = null;
  posicio1 = (double) e1.getGIni() + (double) e1.getMIni() / 60 + (double) e1.getSIni() / 3600;
  graus1 = ((e1.ordSigne(e1.getSigneIni())) - 1) * 30 + posicio1;
  posicioAnt1 = (double) e1.getGIniAnt() + (double) e1.getMIniAnt() / 60 + (double) e1.getSIniAnt() / 3600;
  grausAnt1 = ((e1.ordSigne(e1.getSigneIniAnt())) - 1) * 30 + posicioAnt1;
  
  e1.setCasa("");
  e1.setCasaAnt("");
  // inicialitzem lent i part
  e1.setSigneAnt(e1.getSigneIniAnt());
  e2.setSigneAnt(e2.getSigneIniAnt());
  e1.setG(e1.getGIni());
  e1.setM(e1.getMIni());
  e1.setS(e1.getSIni());
  e1.setGAnt(e1.getGIniAnt());
  e1.setMAnt(e1.getMIniAnt());
  e1.setSAnt(e1.getSIniAnt());
  e2.setG(e2.getGIni());
  e2.setM(e2.getMIni());
  e2.setS(e2.getSIni());
  e2.setGAnt(e2.getGIniAnt());
  e2.setMAnt(e2.getMIniAnt());
  e2.setSAnt(e2.getSIniAnt());
  
  for (int i = 0; i < segons; i++) {
   // calculem les coordenades de les cases per un moment i
   int momH = (int) i / 3600;
   int momM = (int) (i - momH * 3600) / 60;
   int momS = i - momH * 3600 - momM * 60;
   momS += segonIni;
   if (momS > 59) {
    momM++;
    momS -= 60;
   }
    
   momM += minutIni;
   if (momM > 59) {
    momH++;
    momM -= 60;
   }
   
   momH += horaIni;
   if (momH > 23) {
	   momH -= 24;
   }

   horoscop.setHora(diaIni, mesIni, anyIni, momH, momM, momS, zonahoraria);
   horoscop.calcValors();
   casesR = horoscop.casaEmprada.getCasesR();
   
   // partit mixt
   if (mixt == true) {
	   diurn = false;
	   nocturn = false;
	   if (i < sol.i) {
		   if (sol.getCasaIni().equals("7")
			 | sol.getCasaIni().equals("7 - 8")) {
				diurn = true;
			}
			else
			{
				nocturn = true;
			}
	   }
	   else
	   {
		   if (sol.getCasaIni().equals("7")
		    | sol.getCasaIni().equals("7 - 8")) {
			   nocturn = true;
		   }
		   else
		   {
			   diurn = true;
		   }
	   }
   }
	   
   // calculem part (i antisci) calculant les variables necessàries
   if (e2.getNom().equals("Fortuna")) {
	   grausSol = ((sol.ordSigne(sol.getSigne(i))) - 1) * 30 + (double) sol.getGIni() + (double) sol.getMIni() / 60 + (double) sol.getSIni() / 3600 + sol.getVelocitat() / 3600 * i;
	   grausLluna = ((lluna.ordSigne(lluna.getSigne(i))) - 1) * 30 + (double) lluna.getGIni() + (double) lluna.getMIni() / 60 + (double) lluna.getSIni() / 3600 + lluna.getVelocitat() / 3600 * i;
	   if (nocturn == true) {
		   // nocturn: sol - lluna + ascendent
		   grausPart = grausSol - grausLluna + CalcUtil.RaG(casesR[0]);
	   }
	   else
	   {
		   // diurn: lluna - sol + ascendent
		   grausPart = grausLluna - grausSol + CalcUtil.RaG(casesR[0]);
	   }
   }
   else
   {
	   grausMart = ((mart.ordSigne(mart.getSigne(i))) - 1) * 30 + (double) mart.getGIni() + (double) mart.getMIni() / 60 + (double) mart.getSIni() / 3600 + mart.getVelocitat() / 3600 * i;
	   grausSaturn = ((saturn.ordSigne(saturn.getSigne(i))) - 1) * 30 + (double) saturn.getGIni() + (double) saturn.getMIni() / 60 + (double) saturn.getSIni() / 3600 + saturn.getVelocitat() / 3600 * i;
	   if (nocturn == true) {
		   // nocturn: saturn - mart + ascendent
		   grausPart = grausSaturn - grausMart + CalcUtil.RaG(casesR[0]);
	   }
	   else
	   {
		   // diurn: mart - saturn + ascendent
		   grausPart = grausMart - grausSaturn + CalcUtil.RaG(casesR[0]);
	   }
   }
   
	  if (grausPart < 0) {grausPart += 360;}
	  if (grausPart > 360) {grausPart -= 360;}
	  // calculem el signe on esta el part (i el seu antisci)
	  /*
	//System.out.println("i: " + i);
	//System.out.println("grausPart: " + grausPart);
	//System.out.println("grausPart / 30: " + grausPart / 30);
	//System.out.println("Math.floor(grausPart / 30): " + Math.floor(grausPart / 30));
	//System.out.println("(int)Math.floor(grausPart / 30): " + (int)Math.floor(grausPart / 30));
	//System.out.println("(int)(Math.floor(grausPart / 30) + 1): " + (int)(Math.floor(grausPart / 30) + 1));
	//System.out.println("e2.cases((int)(Math.floor(grausPart / 30) + 1)):" + e2.cases((int)(Math.floor(grausPart / 30) + 1)));
	  */
	  e2.setSigne(i, e2.cases((int)(Math.floor(grausPart / 30) + 1)));
	  e2.setSigneAnt(e2.getAntisci(e2.cases((int)(Math.floor(grausPart / 30) + 1))));
	  posicioPart = grausPart % 30;
	  // per calcular la casa de l'antisci del part
	  posicioAnt2 = 30 - posicioPart;
	  // guardar graus a fortunes abans d'assignar cases i pels graus simples
	  guardarGraus(e2,posicioPart);
	  guardarGrausAnt(e2,posicioAnt2);
   
   assignaCases(e1,i);
   assignaCases(e2,i);
   //e1.buidaSignes();
   
   /*
	if (!e1.getNom().equals("Lluna")
	   &&  !e1.getNom().equals("Node")
       &&  !e1.getNom().equals("Ur\u00e0")
	   &&  !e1.getNom().equals("Nept\u00fa")
	   &&  !e1.getNom().equals("Plut\u00f3")) {
		esRegent(e1, i);
	}
   */
   
   if (mateixN == true) {
	   if (!e1.getNom().equals("Lluna")
	   &&  !e1.getNom().equals("Node")
       &&  !e1.getNom().equals("Ur\u00e0")
	   &&  !e1.getNom().equals("Nept\u00fa")
	   &&  !e1.getNom().equals("Plut\u00f3")
	   &&   aspecteN.getRegent1(2) == null
	   &&   e1.getId() < 7
	   &&  !e1.getAngle(i).equals("")) {
		setAngles(aspecteN, e1, i);
	   }
   }
   
   if (mateixA == true) {
	   if (!e1.getNom().equals("Lluna")
	   &&  !e1.getNom().equals("Node")
       &&  !e1.getNom().equals("Ur\u00e0")
	   &&  !e1.getNom().equals("Nept\u00fa")
	   &&  !e1.getNom().equals("Plut\u00f3")
	   &&   aspecteA.getRegent1(2) == null
	   &&   e1.getId() < 7
	   &&  !e1.getAngle(i).equals("")) {
		setAngles(aspecteA, e1, i);
	   }
   }
   
   /*
   if (e1.getNom().equals("Venus") && e2.getNom().equals("Infortuni")) {
	   if (i == 281) {
		 //System.out.println("després assignació regents");
		 //System.out.println("e1.getSigne(0): " + e1.getSigne(0));
		 //System.out.println("e1.getSigne(1): " + e1.getSigne(1));
		 //System.out.println("e1.getSigne(2): " + e1.getSigne(2));
	   }
   }
   */
   
   aplica = false;
   
   if (e1.getNom().equals("Lluna")
			 | e1.getNom().equals("Ur\u00e0")
			 | e1.getNom().equals("Nept\u00fa")
			 | e1.getNom().equals("Plut\u00f3")) {
	   aplica = true;
   }

   if (!e1.getNom().equals("Lluna")
		   && !e1.getNom().equals("Ur\u00e0")
		   && !e1.getNom().equals("Nept\u00fa")
		   && !e1.getNom().equals("Plut\u00f3")
		   && !e1.getAngle(i).equals("")) {
	 aplica = true;
   }
      
	// calculem els graus simples
	e1.setGrauSimple(calcularGrauSimple(e1.getG(), e1.getM(), e1.getS()));
	e1.setGrauSimpleAnt(calcularGrauSimple(e1.getGAnt(), e1.getMAnt(), e1.getSAnt()));
	e2.setGrauSimple(calcularGrauSimple(e2.getG(), e2.getM(), e2.getS()));
	
	/*
	   //if (e1.getNom().equals("J\u00fapiter") && e2.getNom().equals("Fortuna")) {
	   //if (e1.getNom().equals("Lluna") && e2.getNom().equals("Fortuna")) {
	   //if (e1.getNom().equals("Venus") && e2.getNom().equals("Fortuna")) {
	   //if (e1.getNom().equals("Mart") && e2.getNom().equals("Infortuni")) {
	   if (e1.getNom().equals("Mercuri") && e2.getNom().equals("Infortuni")) {
			 //if (i == 1005 | i == 1006 | i == 2221 | i == 2222) {
			 if (i == 2863) {
				 //System.out.println("---------------------------------------");
	    	 System.out.println("graus1: " + graus1);
			 //System.out.println("grausLluna: " + grausLluna);
			 //System.out.println("grausSol: " + grausSol);
			 //System.out.println("grausMart: " + grausMart);
			 //System.out.println("grausSaturn: " + grausSaturn);
			 //System.out.println("CalcUtil.RaG(casesR[0]): " + CalcUtil.RaG(casesR[0]));
			 //System.out.println("CalcUtil.RaG(casesR[6]): " + CalcUtil.RaG(casesR[6]));
			 //System.out.println("grausPart: " + grausPart);
			 //System.out.println("grausAnt1: " + grausAnt1);
			 //System.out.println("posicio1: " + posicio1);
			 //System.out.println("posicioPart: " + posicioPart);
			 //System.out.println("posicioAnt1: " + posicioAnt1);
			 //System.out.println("posicioAnt2: " + posicioAnt2);
			 //System.out.println("e1.getG(): " + e1.getG());
		   //System.out.println("e1.getM(): " + e1.getM());
		   //System.out.println("e1.getS(): " + e1.getS());
		   //System.out.println("e1.getGrauSimple(): " + e1.getGrauSimple());
			 //System.out.println("e1.getGAnt(): " + e1.getGAnt());
		   	 System.out.println("e1.getMAnt(): " + e1.getMAnt());
		   	 System.out.println("e1.getSAnt(): " + e1.getSAnt());
		   //System.out.println("e1.getGrauSimpleAnt(): " + e1.getGrauSimpleAnt());
		   //System.out.println("e2.getG(): " + e2.getG());
		   //System.out.println("e2.getM(): " + e2.getM());
		   //System.out.println("e2.getS(): " + e2.getS());
		   //System.out.println("e2.getGrauSimple(): " + e2.getGrauSimple());
			 //System.out.println("aplica: " + aplica);
			 //System.out.println("momH: " + momH);
	    	 System.out.println("momM: " + momM);
	    	 System.out.println("momS: " + momS);
			 //System.out.println("horaIniDescans: " + horaIniDescans);
			 //System.out.println("minutIniDescans: " + minutIniDescans);
			 //System.out.println("segonIniDescans: " + segonIniDescans);
			 //System.out.println("horaFiDescans: " + horaFiDescans);
			 //System.out.println("minutFiDescans: " + minutFiDescans);
			 //System.out.println("segonFiDescans: " + segonFiDescans);
			 }
	     }
	  */ 
	
   // normal a fortunes
   if (aplica == true
		   && ((e1.getGrauSimple() - e2.getGrauSimple() < 2 
				   &&   e1.getGrauSimple() - e2.getGrauSimple() > -2)
     && (e1.esAngular()
	 |	 e2.esAngular()
     |   e1.getNom().equals("Lluna")))) {
		 if (mateixN == false) {
			 
			int distancia = (int) (graus1 - grausPart);
			String tipus = graus.get(distancia);
	 
			// si l'aspecte es dóna al descans (en cas que estigui informat), l'aspecte no es crearà
			if (horaIniDescans == 0
    			&& minutIniDescans == 0
    			&&  segonIniDescans == 0) {
					mateixN = true;
					aspecteN = new Aspecte (e1,false,e2,i,momH,momM,momS,tipus);
					aspectes.add(aspecteN);
			}
			else
			{
				// hi ha 2 situacions possibles que creen l'aspecte
				// 1. comença abans del descans
				if ((momH < horaIniDescans
    				|  (momH == horaIniDescans && momM < minutIniDescans)
    				|  (momH == horaIniDescans && momM == minutIniDescans && momS < segonIniDescans))
				// 2. acaba després del descans
    				|  (momH > horaFiDescans
    						| (momH == horaFiDescans && momM > minutFiDescans)
    						| (momH == horaFiDescans && momM == minutFiDescans && momS > segonFiDescans))) {
								mateixN = true;
								aspecteN = new Aspecte (e1,false,e2,i,momH,momM,momS,tipus);
								aspectes.add(aspecteN);
							}
			}

			if (mateixN == true) {
				// avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
				for (Gol gol: gols) {
					if (i == gol.getSegons()) {
						String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
						e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
						e2.getSigne(i) + ")";
						gol.setAspecte(asp);
					}
				}
			}
		 }
		 else
		 {
			 // l'aspecte ja està creat i es dóna un gol durant el període
			 //calcul del tipus d'aspecte
			 int distancia = (int) (graus1 - grausPart);
			 String tipus = graus.get(distancia);
			 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
			 for (Gol gol: gols) {
				 if (i == gol.getSegons()) {
					 String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
					 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
					 e2.getSigne(i) + ")";
					 gol.setAspecte(asp);
				 }
			 }
		 }
	 }
	 else
	 {
		 if (mateixN == true) {
			 //aprofitem per fixar el moment final de l'aspecte
			 aspecteN.setMomFi(momH, momM, momS);
			 escriureCSV(aspecteN, csvWriter);
	 
			 int distancia = (int) (graus1 - grausPart);
			 String tipus = graus.get(distancia);
			 
			 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
			 for (Gol gol: gols) {
				 if (i == gol.getSegons()) {
					 String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
					 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
					 e2.getSigne(i) + ")";
					 gol.setAspecte(asp);
				 }
			 }
	 
			 mateixN = false;
		 }
	 }
   
	 // antisci a fortunes
	 if (aplica == true
		   && ((e1.getGrauSimpleAnt() - e2.getGrauSimple() < 2
				   &&   e1.getGrauSimpleAnt() - e2.getGrauSimple() > -2)
     && (e1.esAngular()
	 |	 e2.esAngular()
     |   e1.getNom().equals("Lluna")))) {
		 if (mateixA == false) {
			 
			 int distancia = (int) (grausAnt1 - grausPart);
			 String tipus = graus.get(distancia);
	 
			 /*
			 if (e1.getNom().equals("Sol") && e2.getNom().equals("Casa 4")) {
				System.out.println("i: " + i);
				System.out.println("grausAnt1: " + grausAnt1);
				System.out.println("grausPart: " + grausPart);
				System.out.println("distancia: " + distancia);
				System.out.println("momH: " + momH);
				System.out.println("momM: " + momM);
				System.out.println("momS: " + momS);
				//System.out.println("e1.getSigne(i): " + e1.getSigne(i));
			}
			*/
	 
			// si l'aspecte es dóna al descans (en cas que estigui informat), l'aspecte no es crearà
			if (horaIniDescans == 0
    			&& minutIniDescans == 0
    			&&  segonIniDescans == 0) {
					mateixA = true;
					aspecteA = new Aspecte (e1,true,e2,i,momH,momM,momS,tipus);
					aspectes.add(aspecteA);
			}
			else
			{
				// hi ha 2 situacions possibles que creen l'aspecte
				// 1. comença abans del descans
				if ((momH < horaIniDescans
    				|  (momH == horaIniDescans && momM < minutIniDescans)
    				|  (momH == horaIniDescans && momM == minutIniDescans && momS < segonIniDescans))
				// 2. acaba després del descans
    				|  (momH > horaFiDescans
    						| (momH == horaFiDescans && momM > minutFiDescans)
    						| (momH == horaFiDescans && momM == minutFiDescans && momS > segonFiDescans))) {
								mateixA = true;
								aspecteA = new Aspecte (e1,true,e2,i,momH,momM,momS,tipus);
								aspectes.add(aspecteA);
							}
			}
	 
			if (mateixA == true) {
				// avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
				for (Gol gol: gols) {
					if (i == gol.getSegons()) {
						String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
						e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
						e2.getSigne(i) + ")";
						gol.setAspecte(asp);
					}
				}
			}
		 }
		 else
		 {
			 // l'aspecte ja està creat i es dóna un gol durant el període
			 int distancia = (int) (grausAnt1 - grausPart);
			 String tipus = graus.get(distancia);
			 
			 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
			 for (Gol gol: gols) {
				 if (i == gol.getSegons()) {
					 String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
					 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
					 e2.getSigne(i) + ")";
					 gol.setAspecte(asp);
				 }
			 }
		 }
	 }
   else
   {
    if (mateixA == true) {
     //aprofitem per fixar el moment final de l'aspecte
     aspecteA.setMomFi(momH, momM, momS);
     escriureCSV(aspecteA, csvWriter);
	 
	 //calcul del tipus d'aspecte
	 int distancia = (int) (grausAnt1 - grausPart);
	 String tipus = graus.get(distancia);
	 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
	 for (Gol gol: gols) {
		 if (i == gol.getSegons()) {
			 String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
			 e1.getAngle(i)+ tipus + e2.getNom() + " (" + e2.getCasa() + "/" + e2.getCasaAnt() + "/" + 
			 e2.getSigne(i) + ")";
			 gol.setAspecte(asp);
		 }
	 }
	 
     mateixA = false;
    }
   }

   // velocitat en segons angulars, posició en graus angulars
   // entitat 1 a fortunes
   posicio1 += e1.getVelocitat() / 3600;
   graus1 += e1.getVelocitat() / 3600;
   posicioAnt1 = 30 - posicio1;
   grausAnt1 = ((e1.ordSigne(e1.getSigneIniAnt())) - 1) * 30 + posicioAnt1;
   
   if (posicio1 > 30) {
	   posicio1 -= 30;
	   posicioAnt1 = 30 - posicio1;
	   grausAnt1 = ((e1.ordSigne(e1.getSigneFiAnt())) - 1) * 30 + posicioAnt1;
	   // per lents totes les posicions ja haurien d'estar informades
   }
   
   if (posicio1 < 0) {
	   posicio1 += 30;
	   posicioAnt1 = 30 - posicio1;
	   grausAnt1 = ((e1.ordSigne(e1.getSigneFiAnt())) - 1) * 30 + posicioAnt1;
	   // per lents totes les posicions ja haurien d'estar informades
   }
   
   if (graus1 > 360) {graus1 -= 360;}
   if (graus1 < 0) {graus1 += 360;}
   
   // guardar graus a fortunes pels graus simples i la casa de l'antisci
   guardarGraus(e1,posicio1);
   guardarGrausAnt(e1,posicioAnt1);
  }
  // ha recorregut tots els segons: completem el que faci falta
  // s'ha calculat momIni però no momFi: l'aspecte es dóna fins el final del partit
  if (mateixN == true) {
   aspecteN.setHMomFi(horaFi);
   aspecteN.setMMomFi(minutFi);
   aspecteN.setSMomFi(segonFi);
   escriureCSV(aspecteN, csvWriter);
  }
  if (mateixA == true) {
   aspecteA.setHMomFi(horaFi);
   aspecteA.setMMomFi(minutFi);
   aspecteA.setSMomFi(segonFi);
   escriureCSV(aspecteA, csvWriter);
  }
 }
 
 public void rapids(Entitat e1, Entitat e2, CSVWriter csvWriter) {
	  boolean mateixN = false;
	  boolean mateixA = false;
	  Aspecte aspecteN = null;
	  Aspecte aspecteA = null;
	  double grausLluna = 0;
	  double grausSol = 0;
	  double grausMart = 0;
	  double grausSaturn = 0;
	  double posicioCasa = 0;
	  double posicioAnt = 0;
	  double grausPart = 0;
	  double grausCasa = 0;
	  double grausAnt = 0;
	  double posicioPart = 0;
	  e1.setCasa("");
	  e1.setCasaAnt("");
	  
	  /*
	  // inicialitzem part i casa
	  e1.setSigne(e1.getSigne(0));
	  e1.setSigneAnt(e1.getSigneIniAnt());
	  e2.setSigne(e2.getSigne(0));
	  e2.setSigneAnt(e2.getSigneIniAnt());
	  e1.setG(e1.getGIni());
	  e1.setM(e1.getMIni());
	  e1.setS(e1.getSIni());
	  e1.setGAnt(e1.getGIniAnt());
	  e1.setMAnt(e1.getMIniAnt());
	  e1.setSAnt(e1.getSIniAnt());
	  */
	  
	  for (int i = 0; i < segons; i++) {
	   // calculem les coordenades de les cases per un moment i
	   int momH = (int) i / 3600;
	   int momM = (int) (i - momH * 3600) / 60;
	   int momS = (int) i - momH * 3600 - momM * 60;
	   momS += segonIni;
	   if (momS > 59) {
	    momM++;
	    momS -= 60;
	   }
	    
	   momM += minutIni;
	   if (momM > 59) {
	    momH++;
	    momM -= 60;
	   }
	   
	   momH += horaIni;
	   if (momH > 23) {
		   momH -= 24;
	   }
	   
	   horoscop.setHora(diaIni, mesIni, anyIni, momH, momM, momS, zonahoraria);
	   horoscop.calcValors();
	   casesR = horoscop.casaEmprada.getCasesR();
	   
	   // partit mixt
		if (mixt == true) {
			   diurn = false;
			   nocturn = false;
			   if (i < sol.i) {
				   if (sol.getCasaIni().equals("7")
						   | sol.getCasaIni().equals("7 - 8")) {
					   	diurn = true;
				   	}
				   	else
				   	{
				   		nocturn = true;
				   	}
			   }
			   else
			   {
				   if (sol.getCasaIni().equals("7")
						   | sol.getCasaIni().equals("7 - 8")) {
					   	nocturn = true;
				   }
				   else
				   {
					   	diurn = true;
				   }
			   }
		   }
		   
	   // calculem part (i antisci) calculant les variables necessàries
	   if (e1.getNom().equals("Fortuna")) {
		  // pendent planeta canvia de signe
		  grausSol = ((sol.ordSigne(sol.getSigne(i))) - 1) * 30 + (double) sol.getGIni() + (double) sol.getMIni() / 60 + (double) sol.getSIni() / 3600 + sol.getVelocitat() / 3600 * i;  
		  grausLluna = ((lluna.ordSigne(lluna.getSigne(i))) - 1) * 30 + (double) lluna.getGIni() + (double) lluna.getMIni() / 60 + (double) lluna.getSIni() / 3600 + lluna.getVelocitat() / 3600 * i;
		  if (nocturn == true) {
			  // nocturn: sol - lluna + ascendent
			  grausPart = grausSol - grausLluna + CalcUtil.RaG(casesR[0]);
		  }
		  else
		  {
			  // diurn: lluna - sol + ascendent
			  grausPart = grausLluna - grausSol + CalcUtil.RaG(casesR[0]);
		  }
	  }
	  else
	  {
	   grausMart = ((mart.ordSigne(mart.getSigne(i))) - 1) * 30 + (double) mart.getGIni() + (double) mart.getMIni() / 60 + (double) mart.getSIni() / 3600 + mart.getVelocitat() / 3600 * i;
	   grausSaturn = ((saturn.ordSigne(saturn.getSigne(i))) - 1) * 30 + (double) saturn.getGIni() + (double) saturn.getMIni() / 60 + (double) saturn.getSIni() / 3600 + saturn.getVelocitat() / 3600 * i;
	   if (nocturn == true) {
		   // nocturn: saturn - mart + ascendent
		   grausPart = grausSaturn - grausMart + CalcUtil.RaG(casesR[0]);
	   }
	   else
	   {
		   // diurn: mart - saturn + ascendent
		   grausPart = grausMart - grausSaturn + CalcUtil.RaG(casesR[0]);
	   }
	  }
   
	  if (grausPart < 0) {grausPart += 360;}
	  if (grausPart > 360) {grausPart -= 360;}

	  // calculem el signe on esta el part (i el seu antisci)
	  e1.setSigne(i, e1.cases((int)Math.floor(grausPart / 30) + 1));
	  e1.setSigneAnt(e1.getAntisci(e1.cases((int)Math.floor(grausPart / 30) + 1)));
	  posicioPart = grausPart % 30;
	  posicioAnt = 30 - posicioPart;
	  // guardar graus a rapids pels graus simples i la casa de l'antisci
	  guardarGraus(e1,posicioPart);
	  guardarGrausAnt(e1,posicioAnt);
	  grausAnt = (e1.ordSigne(e1.getSigneAnt()) - 1) * 30 + posicioAnt;

	  // asignem casa a rapids
	  if (e2.getNom().equals("Casa 1 (AC)")) {
		  grausCasa = CalcUtil.RaG(casesR[0]);
		  posicioCasa = CalcUtil.RaG(casesR[0]) % 30;
	  }
	  else
	  {
		  grausCasa = CalcUtil.RaG(casesR[3]);
		  posicioCasa = CalcUtil.RaG(casesR[3]) % 30;
	  }
	   
	  // calculem el signe on esta la casa
	  e2.setSigne(i, e2.cases((int)Math.floor(grausCasa / 30) + 1));
	   
	  // guardar graus a rapids pels graus simples
	  guardarGraus(e2,posicioCasa);
	   
  
	  assignaCases(e1,i);
	   
	   /*
	   if (e1.getNom().equals("Fortuna") && e2.getNom().equals("Casa 4")) {
		   if (i == 1349) {
			 //System.out.println("-----------------------------------");
			 //System.out.println("grausSol: " + grausSol);
			 //System.out.println("grausLluna: " + grausLluna);
			 //System.out.println("grausMart: " + grausMart);
			 //System.out.println("grausSaturn: " + grausSaturn);
			 //System.out.println("CalcUtil.RaG(casesR[0])" + CalcUtil.RaG(casesR[0]));
			 //System.out.println("grausPart: " + grausPart);
			 //System.out.println("Math.floor(grausPart / 30) + 1: " + Math.floor(grausPart / 30) + 1);
			 //System.out.println("(int)Math.floor(grausPart / 30) + 1: " + (int)Math.floor(grausPart / 30) + 1);
			 //System.out.println("e1.cases((int)Math.floor(grausPart / 30) + 1): " + e1.cases((int)Math.floor(grausPart / 30) + 1));
			   //System.out.println("posicioAnt: " + posicioAnt);
			 //System.out.println("posicioPart: " + posicioPart);
			 //System.out.println("posicioCasa: " + posicioCasa);
			 //System.out.println("momH: " + momH);
			 //System.out.println("momM: " + momM);
			 //System.out.println("momS: " + momS);
		   }
	   }
	   */
	  
	 	// calculem els graus simples
	 	e1.setGrauSimple(calcularGrauSimple(e1.getG(), e1.getM(), e1.getS()));
	 	e1.setGrauSimpleAnt(calcularGrauSimple(e1.getGAnt(), e1.getMAnt(), e1.getSAnt()));
	 	e2.setGrauSimple(calcularGrauSimple(e2.getG(), e2.getM(), e2.getS()));
	 	
		// normal a rapids
	 	if ((e1.getGrauSimple() - e2.getGrauSimple() < 2 
	 		   &&   e1.getGrauSimple() - e2.getGrauSimple() > -2)
	     && (e1.esAngular())) {
			 if (mateixN == false) {
				 
				 int distancia = (int) (grausPart - grausCasa);
				 String tipus = graus.get(distancia);
		 
				 /*
				 if (e1.getNom().equals("Fortuna") && e2.getNom().equals("Casa 1 (AC)")) {
					System.out.println("i: " + i);
					System.out.println("graus1: " + graus1);
					System.out.println("e1.getVelocitat(): " + e1.getVelocitat());
					System.out.println("grausCasa: " + grausCasa);
					System.out.println("distancia: " + distancia);
					System.out.println("momH: " + momH);
					System.out.println("momM: " + momM);
					System.out.println("momS: " + momS);
					//System.out.println("e1.getSigne(i): " + e1.getSigne(i));
				 }
				 */
				 
				 // si l'aspecte es dóna al descans (en cas que estigui informat), l'aspecte no es crearà
				 if (horaIniDescans == 0
				 && minutIniDescans == 0
				 &&  segonIniDescans == 0) {
					 mateixN = true;
					 aspecteN = new Aspecte (e1,false,e2,i,momH,momM,momS,tipus);
					 aspectes.add(aspecteN);
				 }
				 else
				 {
					 // hi ha 2 situacions possibles que creen l'aspecte
					 // 1. comença abans del descans
					 if ((momH < horaIniDescans
					  |  (momH == horaIniDescans && momM < minutIniDescans)
    				  |  (momH == horaIniDescans && momM == minutIniDescans && momS < segonIniDescans))
					 // 2. acaba despr고del descans
    				  |  (momH > horaFiDescans
    				  |  (momH == horaFiDescans && momM > minutFiDescans)
    				  |  (momH == horaFiDescans && momM == minutFiDescans && momS > segonFiDescans))) {
						  mateixN = true;
						  aspecteN = new Aspecte (e1,false,e2,i,momH,momM,momS,tipus);
						  aspectes.add(aspecteN);
					  }
				 }
		 
				 if (mateixN == true) {
					 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
					 for (Gol gol: gols) {
						 if (i == gol.getSegons()) {
							 String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
							 tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
							 switch (e2.getNom()) {
								 case "Casa 1 (AC)":
								 asp += "C7 (" + c7.getSigne(i) + ")";
								 break;
				 
								 case "Casa 4":
								 asp += "C10 (" + c10.getSigne(i) + ")";
								 break;
							 }
			 			 
							 gol.setAspecte(asp);
						 }
					 }
				 }
			 }
			 else
			 {
				 // l'aspecte ja està creat i es dóna un gol durant el període
				 int distancia = (int) (grausPart - grausCasa);
				 String tipus = graus.get(distancia);
				 
				 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
				 for (Gol gol: gols) {
					 if (i == gol.getSegons()) {
						 String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
						 tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
						 switch (e2.getNom()) {
							 case "Casa 1 (AC)":
							 asp += "C7 (" + c7.getSigne(i) + ")";
							 break;
				 
							 case "Casa 4":
							 asp += "C10 (" + c10.getSigne(i) + ")";
							 break;
						 }
			 			 
						 gol.setAspecte(asp);
					 }
				 }
			 }
		 }
		 else
		 {
			 if (mateixN == true) {
				 //aprofitem per fixar el moment final de l'aspecte
				 aspecteN.setMomFi(momH, momM, momS);
				 escriureCSV(aspecteN, csvWriter);
		 
				 int distancia = (int) (grausPart - grausCasa);
				 String tipus = graus.get(distancia);
		 
				 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
				 for (Gol gol: gols) {
					 if (i == gol.getSegons()) {
						 String asp = e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
						 tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
						 switch (e2.getNom()) {
							 case "Casa 1 (AC)":
							 asp += "C7 (" + c7.getSigne(i) + ")";
							 break;
				 
							 case "Casa 4":
							 asp += "C10 (" + c10.getSigne(i) + ")";
							 break;
						 }
			 			 
					gol.setAspecte(asp);
					 }
				 }
		 
				 mateixN = false;
			 }
		 }
	   
		 // antisci a rapids
		 if ((e1.getGrauSimpleAnt() - e2.getGrauSimple() < 2
			   &&   e1.getGrauSimpleAnt() - e2.getGrauSimple() > -2)
	     && e1.esAngular()) {
			 if (mateixA == false) {
				 
				 int distancia = (int) (grausAnt - grausCasa);
				 String tipus = graus.get(distancia); 
		 
				 /*
				 if (e1.getNom().equals("Fortuna") && e2.getNom().equals("Casa 4")) {
					System.out.println("i: " + i);
					System.out.println("grausAnt: " + grausAnt);
					System.out.println("graus2: " + graus2);
					System.out.println("distancia: " + distancia);
					System.out.println("momH: " + momH);
					System.out.println("momM: " + momM);
					System.out.println("momS: " + momS);
					//System.out.println("e1.getSigne(i): " + e1.getSigne(i));
				}
				*/
				
				// si l'aspecte es dóna al descans (en cas que estigui informat), l'aspecte no es crearà
				if (horaIniDescans == 0
    			&& minutIniDescans == 0
    			&&  segonIniDescans == 0) {
					mateixA = true;
					aspecteA = new Aspecte (e1,true,e2,i,momH,momM,momS,tipus);
					aspectes.add(aspecteA);
				}
				else
				{
					// hi ha 2 situacions possibles que creen l'aspecte
					// 1. comença abans del descans
					if ((momH < horaIniDescans
    				 |  (momH == horaIniDescans && momM < minutIniDescans)
    				 |  (momH == horaIniDescans && momM == minutIniDescans && momS < segonIniDescans))
					// 2. acaba després del descans
    				 |  (momH > horaFiDescans
    				 |  (momH == horaFiDescans && momM > minutFiDescans)
    				 |  (momH == horaFiDescans && momM == minutFiDescans && momS > segonFiDescans))) {
						 mateixA = true;
						 aspecteA = new Aspecte (e1,true,e2,i,momH,momM,momS,tipus);
						 aspectes.add(aspecteA);
					 }
				}
		 
				if (mateixA == true) {

					// avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
					for (Gol gol: gols) {
						if (i == gol.getSegons()) {
							String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
							tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
							switch (e2.getNom()) {
								case "Casa 1 (AC)":
								asp += "C7 (" + c7.getSigne(i) + ")";
								break;
				 
								case "Casa 4":
								asp += "C10 (" + c10.getSigne(i) + ")";
								break;
							}
			 			 
							gol.setAspecte(asp);
						}
					}
				}
			 }
			 else
			 {
				 // el gol ja està creat i  es dona un gol durant el període
				 int distancia = (int) (grausAnt - grausCasa);
				 String tipus = graus.get(distancia);
				 
				 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
				 for (Gol gol: gols) {
					 if (i == gol.getSegons()) {
						 String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
						 tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
						 switch (e2.getNom()) {
							 case "Casa 1 (AC)":
							 asp += "C7 (" + c7.getSigne(i) + ")";
							 break;
				 
							 case "Casa 4":
							 asp += "C10 (" + c10.getSigne(i) + ")";
							 break;
						 }
			 			 
						 gol.setAspecte(asp);
					 }
				 }
			 }
		 }
		 else
		 {
			 if (mateixA == true) {
				 //aprofitem per fixar el moment final de l'aspecte
				 aspecteA.setMomFi(momH, momM, momS);
				 escriureCSV(aspecteA, csvWriter);
		 
				 int distancia = (int) (grausAnt - grausCasa);
				 String tipus = graus.get(distancia); 
				 
				 // avaluem si el segon pertany a algun dels gols guardats, i si es així calculem l'aspecte precís i el guardem
				 for (Gol gol: gols) {
					 if (i == gol.getSegons()) {
						 String asp = "Ant. " + e1.getNom() + " (" + e1.getCasa() + "/" + e1.getCasaAnt() + "/" + e1.getSigne(i) + ") " + 
						 tipus + e2.getNom() + " (" + e2.getSigne(i) + ") " + graus.get(180-distancia);
			 
						 switch (e2.getNom()) {
							 case "Casa 1 (AC)":
							 asp += "C7 (" + c7.getSigne(i) + ")";
							 break;
				 
							 case "Casa 4":
							 asp += "C10 (" + c10.getSigne(i) + ")";
							 break;
						 }
			 			 
						 gol.setAspecte(asp);
					 }
				 }
			
				 mateixA = false;
			 }
		 }
	  }
	  
	  // ha recorregut tots els segons: completem el que faci falta
	  // s'ha calculat momIni però no momFi: l'aspecte es dóna fins el final del partit
	  if (mateixN == true) {
		  aspecteN.setHMomFi(horaFi);
		  aspecteN.setMMomFi(minutFi);
		  aspecteN.setSMomFi(segonFi);
		  escriureCSV(aspecteN, csvWriter);
	  }
	  if (mateixA == true) {
		  aspecteA.setHMomFi(horaFi);
		  aspecteA.setMMomFi(minutFi);
		  aspecteA.setSMomFi(segonFi);
		  escriureCSV(aspecteA, csvWriter);
	  }
 }
 
 public void assignaCases(Entitat e, int i) {
  double posicioE = ((e.ordSigne(e.getSigne(i))) - 1) * 30
  + ((double) e.getG() + (double) e.getM() / 60 + (double) e.getS() / 3600);
  double posicioAnt = ((e.ordSigne(e.getSigneAnt())) - 1) * 30 
  + ((double) e.getGAnt() + (double) e.getMAnt() / 60 + (double) e.getSAnt() / 3600);
  
  // casa pel grau normal de la entitat
  String casa = "";
  
  // transformació de la casa de radians a graus
  double[] pCasa = new double[12];
  pCasa[0] = CalcUtil.RaG(casesR[0]);
  pCasa[1] = CalcUtil.RaG(casesR[1]);
  pCasa[2] = CalcUtil.RaG(casesR[2]);
  pCasa[3] = CalcUtil.RaG(casesR[3]);
  pCasa[4] = CalcUtil.RaG(casesR[4]);
  pCasa[5] = CalcUtil.RaG(casesR[5]);
  pCasa[6] = CalcUtil.RaG(casesR[6]);
  pCasa[7] = CalcUtil.RaG(casesR[7]);
  pCasa[8] = CalcUtil.RaG(casesR[8]);
  pCasa[9] = CalcUtil.RaG(casesR[9]);
  pCasa[10] = CalcUtil.RaG(casesR[10]);
  pCasa[11] = CalcUtil.RaG(casesR[11]);
  /*
  if (e1.getNom().equals("Sol") && e2.getNom().equals("Ur\u00e0")) {
 //System.out.println("i: " + i);
 //System.out.println("posicioE: " + posicioE);
 //System.out.println("posicioE2: " + posicioE2);
 //System.out.println("posicioAnt: " + posicioAnt);
 //System.out.println("posicioAnt2: " + posicioAnt2);
 //System.out.println("pCasa[0]: " + pCasa[0]);
 //System.out.println("pCasa[1]: " + pCasa[1]);
 //System.out.println("pCasa[2]: " + pCasa[2]);
 //System.out.println("pCasa[3]: " + pCasa[3]);
 //System.out.println("pCasa[4]: " + pCasa[4]);
 //System.out.println("pCasa[5]: " + pCasa[5]);
 //System.out.println("pCasa[6]: " + pCasa[6]);
 //System.out.println("pCasa[7]: " + pCasa[7]);
 //System.out.println("pCasa[8]: " + pCasa[8]);
 //System.out.println("pCasa[9]: " + pCasa[9]);
 //System.out.println("pCasa[10]: " + pCasa[10]);
 //System.out.println("pCasa[11]: " + pCasa[11]);
  }
  */
  if ((posicioE >= pCasa[0]
    && posicioE < pCasa[1])
    | (pCasa[0] > pCasa[1]
    &&(posicioE < pCasa[1]
    |  posicioE > pCasa[0]))) {
   casa = "1";
   if (pCasa[1] > posicioE) {
    if (pCasa[1] - posicioE <= 4) {
     casa = "1-2";
    }
   }
   else
   {
    if (pCasa[1] - posicioE >= -4) {
     casa = "1-2";
    }
   }
  }
  if ((posicioE >= pCasa[1]
    && posicioE < pCasa[2])
    | (pCasa[1] > pCasa[2]
    &&(posicioE < pCasa[2]
    |  posicioE > pCasa[1]))) {
     casa = "2";
     if (pCasa[2] > posicioE) {
      if (pCasa[2] - posicioE <= 4) {
       casa = "2-3";
      }
     }
     else
     {
      if (pCasa[2] - posicioE >= -4) {
       casa = "2-3";
      }
     }
    }
  if ((posicioE >= pCasa[2]
    && posicioE < pCasa[3])
    | (pCasa[2] > pCasa[3]
    &&(posicioE < pCasa[3]
    |  posicioE > pCasa[2]))) {
     casa = "3";
     if (pCasa[3] > posicioE) {
      if (pCasa[3] - posicioE <= 4) {
       casa = "3-4";
      }
     }
     else
     {
      if (pCasa[3] - posicioE >= -4) {
       casa = "3-4";
      }
     }
    }
  if ((posicioE >= pCasa[3]
    && posicioE < pCasa[4])
    | (pCasa[3] > pCasa[4]
    &&(posicioE < pCasa[4]
    |  posicioE > pCasa[3]))) {
     casa = "4";
     if (pCasa[4] > posicioE) {
      if (pCasa[4] - posicioE <= 4) {
       casa = "4-5";
      }
     }
     else
     {
      if (pCasa[4] - posicioE >= -4) {
       casa = "4-5";
      }
     }
    }
  if ((posicioE >= pCasa[4]
    && posicioE < pCasa[5])
    | (pCasa[4] > pCasa[5]
    &&(posicioE < pCasa[5]
    |  posicioE > pCasa[4]))) {
     casa = "5";
     if (pCasa[5] > posicioE) {
      if (pCasa[5] - posicioE <= 4) {
       casa = "5-6";
      }
     }
     else
     {
      if (pCasa[5] - posicioE >= -4) {
       casa = "5-6";
      }
     }
    }
  if ((posicioE >= pCasa[5]
    && posicioE < pCasa[6])
    | (pCasa[5] > pCasa[6]
    &&(posicioE < pCasa[6]
    |  posicioE > pCasa[5]))) {
     casa = "6";
     if (pCasa[6] > posicioE) {
      if (pCasa[6] - posicioE <= 4) {
       casa = "6-7";
      }
     }
     else
     {
      if (pCasa[6] - posicioE >= -4) {
       casa = "6-7";
      }
     }
    }
  if ((posicioE >= pCasa[6]
    && posicioE < pCasa[7])
    | (pCasa[6] > pCasa[7]
    &&(posicioE < pCasa[7]
    |  posicioE > pCasa[6]))) {
     casa = "7";
     if (pCasa[7] > posicioE) {
      if (pCasa[7] - posicioE <= 4) {
       casa = "7-8";
      }
     }
     else
     {
      if (pCasa[7] - posicioE >= -4) {
       casa = "7-8";
      }
     }
    }
  if ((posicioE >= pCasa[7]
    && posicioE < pCasa[8])
    | (pCasa[7] > pCasa[8]
    &&(posicioE < pCasa[8]
    |  posicioE > pCasa[7]))) {
     casa = "8";
     if (pCasa[8] > posicioE) {
      if (pCasa[8] - posicioE <= 4) {
       casa = "8-9";
      }
     }
     else
     {
      if (pCasa[8] - posicioE >= -4) {
       casa = "8-9";
      }
     }
    }
  if ((posicioE >= pCasa[8]
    && posicioE < pCasa[9])
    | (pCasa[8] > pCasa[9]
    &&(posicioE < pCasa[9]
    |  posicioE > pCasa[8]))) {
     casa = "9";
     if (pCasa[9] > posicioE) {
      if (pCasa[9] - posicioE <= 4) {
       casa = "9-10";
      }
     }
     else
     {
      if (pCasa[9] - posicioE >= -4) {
       casa = "9-10";
      }
     }
    }
  if ((posicioE >= pCasa[9]
    && posicioE < pCasa[10])
    | (pCasa[9] > pCasa[10]
    &&(posicioE < pCasa[10]
    |  posicioE > pCasa[9]))) {
     casa = "10";
     if (pCasa[10] > posicioE) {
      if (pCasa[10] - posicioE <= 4) {
       casa = "10-11";
      }
     }
     else
     {
      if (pCasa[10] - posicioE >= -4) {
       casa = "10-11";
      }
     }
    }
  if ((posicioE >= pCasa[10]
    && posicioE < pCasa[11])
    | (pCasa[10] > pCasa[11]
    &&(posicioE < pCasa[11]
    |  posicioE > pCasa[10]))) {
     casa = "11";
     if (pCasa[11] > posicioE) {
      if (pCasa[11] - posicioE <= 4) {
       casa = "11-12";
      }
     }
     else
     {
      if (pCasa[11] - posicioE >= -4) {
       casa = "11-12";
      }
     }
    }
  if ((posicioE >= pCasa[11]
    && posicioE < pCasa[0])
    | (pCasa[11] > pCasa[0]
    &&(posicioE < pCasa[0]
    |  posicioE > pCasa[11]))) {
     casa = "12";
     if (pCasa[0] > posicioE) {
      if (pCasa[0] - posicioE <= 4) {
       casa = "12-1";
      }
     }
     else
     {
      if (pCasa[0] - posicioE >= -4) {
       casa = "12-1";
      }
     }
    }
  e.setCasa(casa);
  
    //casa pel antisci de la entitat
    
    if ((posicioAnt >= pCasa[0]
      && posicioAnt < pCasa[1])
      |  (pCasa[0] > pCasa[1]
      && (posicioAnt < pCasa[1]
      |   posicioAnt > pCasa[0]))) {
     casa = "1";
     if (pCasa[1] > posicioAnt) {
      if (pCasa[1] - posicioAnt <= 4) {
       casa = "1-2";
      }
     }
     else
     {
      if (pCasa[1] - posicioAnt >= -4) {
       casa = "1-2";
      }
     }
    }
    if ((posicioAnt >= pCasa[1]
      && posicioAnt < pCasa[2])
      |  (pCasa[1] > pCasa[2]
      && (posicioAnt < pCasa[2]
      |   posicioAnt > pCasa[1]))) {
       casa = "2";
       if (pCasa[2] > posicioAnt) {
        if (pCasa[2] - posicioAnt <= 4) {
         casa = "2-3";
        }
       }
       else
       {
        if (pCasa[2] - posicioAnt >= -4) {
         casa = "2-3";
        }
       }
      }
    if ((posicioAnt >= pCasa[2]
      && posicioAnt < pCasa[3])
      |  (pCasa[2] > pCasa[3]
      && (posicioAnt < pCasa[3]
      |   posicioAnt > pCasa[2]))) {
       casa = "3";
       if (pCasa[3] > posicioAnt) {
        if (pCasa[3] - posicioAnt <= 4) {
         casa = "3-4";
        }
       }
       else
       {
        if (pCasa[3] - posicioAnt >= -4) {
         casa = "3-4";
        }
       }
      }
    if ((posicioAnt >= pCasa[3]
      && posicioAnt < pCasa[4])
      |  (pCasa[3] > pCasa[4]
      && (posicioAnt < pCasa[4]
      |   posicioAnt > pCasa[3]))) {
       casa = "4";
       if (pCasa[4] > posicioAnt) {
        if (pCasa[4] - posicioAnt <= 4) {
         casa = "4-5";
        }
       }
       else
       {
        if (pCasa[4] - posicioAnt >= -4) {
         casa = "4-5";
        }
       }
      }
    if ((posicioAnt >= pCasa[4]
      && posicioAnt < pCasa[5])
      |  (pCasa[4] > pCasa[5]
      && (posicioAnt < pCasa[5]
      |   posicioAnt > pCasa[4]))) {
       casa = "5";
       if (pCasa[5] > posicioAnt) {
        if (pCasa[5] - posicioAnt <= 4) {
         casa = "5-6";
        }
       }
       else
       {
        if (pCasa[5] - posicioAnt >= -4) {
         casa = "5-6";
        }
       }
      }
    if ((posicioAnt >= pCasa[5]
      && posicioAnt < pCasa[6])
      |  (pCasa[5] > pCasa[6]
      && (posicioAnt < pCasa[6]
      |   posicioAnt > pCasa[5]))) {
       casa = "6";
       if (pCasa[6] > posicioAnt) {
        if (pCasa[6] - posicioAnt <= 4) {
         casa = "6-7";
        }
       }
       else
       {
        if (pCasa[6] - posicioAnt >= -4) {
         casa = "6-7";
        }
       }
      }
    if ((posicioAnt >= pCasa[6]
      && posicioAnt < pCasa[7])
      |  (pCasa[6] > pCasa[7]
      && (posicioAnt < pCasa[7]
      |   posicioAnt > pCasa[6]))) {
       casa = "7";
       if (pCasa[7] > posicioAnt) {
        if (pCasa[7] - posicioAnt <= 4) {
         casa = "7-8";
        }
       }
       else
       {
        if (pCasa[7] - posicioAnt >= -4) {
         casa = "7-8";
        }
       }
      }
    if ((posicioAnt >= pCasa[7]
      && posicioAnt < pCasa[8])
      |  (pCasa[7] > pCasa[8]
      && (posicioAnt < pCasa[8]
      |   posicioAnt > pCasa[7]))) {
       casa = "8";
       if (pCasa[8] > posicioAnt) {
        if (pCasa[8] - posicioAnt <= 4) {
         casa = "8-9";
        }
       }
       else
       {
        if (pCasa[8] - posicioAnt >= -4) {
         casa = "8-9";
        }
       }
      }
    if ((posicioAnt >= pCasa[8]
      && posicioAnt < pCasa[9])
      |  (pCasa[8] > pCasa[9]
      && (posicioAnt < pCasa[9]
      |   posicioAnt > pCasa[8]))) {
       casa = "9";
       if (pCasa[9] > posicioAnt) {
        if (pCasa[9] - posicioAnt <= 4) {
         casa = "9-10";
        }
       }
       else
       {
        if (pCasa[9] - posicioAnt >= -4) {
         casa = "9-10";
        }
       }
      }
    if ((posicioAnt >= pCasa[9]
      && posicioAnt < pCasa[10])
      |  (pCasa[9] > pCasa[10]
      && (posicioAnt < pCasa[10]
      |   posicioAnt > pCasa[9]))) {
       casa = "10";
       if (pCasa[10] > posicioAnt) {
        if (pCasa[10] - posicioAnt <= 4) {
         casa = "10-11";
        }
       }
       else
       {
        if (pCasa[10] - posicioAnt >= -4) {
         casa = "10-11";
        }
       }
      }
    if ((posicioAnt >= pCasa[10]
      && posicioAnt < pCasa[11])
      |  (pCasa[10] > pCasa[11]
      && (posicioAnt < pCasa[11]
      |   posicioAnt > pCasa[10]))) {
       casa = "11";
       if (pCasa[11] > posicioAnt) {
        if (pCasa[11] - posicioAnt <= 4) {
         casa = "11-12";
        }
       }
       else
       {
        if (pCasa[11] - posicioAnt >= -4) {
         casa = "11-12";
        }
       }
      }
    if ((posicioAnt >= pCasa[11]
      && posicioAnt < pCasa[0])
      |  (pCasa[11] > pCasa[0]
      && (posicioAnt < pCasa[0]
      |   posicioAnt > pCasa[11]))) {
       casa = "12";
       if (pCasa[0] > posicioAnt) {
        if (pCasa[0] - posicioAnt <= 4) {
         casa = "12-1";
        }
       }
       else
       {
        if (pCasa[0] - posicioAnt >= -4) {
         casa = "12-1";
        }
       }
      }
    e.setCasaAnt(casa);
 }
 
 public void anglesRegents() {
	 // guarda si s'escau l'angle o angles dels quals és regent PER CADA SEGON DEL PARTIT
	 for (Entitat e: entitats) {
		 // del Sol a Saturn (la Lluna és neutral)
		 if (e.getId() < 7 && !e.getNom().equals("Lluna")) {
			 for (int i = 0; i < segons; i++) {
				 // C1
				 if (c1.getSigne(i).equals("Cranc")) {
					 if (e.getNom().equals(c1.getRegent(lluna.getSigne(i)))) {
						 if (e.getAngle(i).equals("")) {
							 e.setAngle(i,"C1 ");
						 }
						 else
						 {
							 e.setAngle(i,"/ C1 ");
						 }
					 }
				 }
				 else
				 {
					 if (e.getNom().equals(c1.getRegent(c1.getSigne(i)))) {
						 if (e.getAngle(i).equals("")) {
							 e.setAngle(i,"C1 ");
						 }
						 else
						 {
							 e.setAngle(i,"/ C1 ");
						 }
					 }
				 }
				 // C4
				 if (c4.getSigne(i).equals("Cranc")) {
					 if (e.getNom().equals(c4.getRegent(lluna.getSigne(i)))) {
						 if (e.getAngle(i).equals("")) {
							 e.setAngle(i,"C4 ");
						 }
						 else
						 {
							 e.setAngle(i,"/ C4 ");
						 }
					 }
				 }
				 else
				 {
					 if (e.getNom().equals(c4.getRegent(c4.getSigne(i)))) {
						 if (e.getAngle(i).equals("")) {
							 e.setAngle(i,"C4 ");
						 }
						 else
						 {
							 e.setAngle(i,"/ C4 ");
						 }
					 }
				 }
				 // C7
				 if (c7.getSigne(i).equals("Cranc")) {
					 if (e.getNom().equals(c7.getRegent(lluna.getSigne(i)))) {
						 if (e.getAngle(i).equals("")) {
							 e.setAngle(i,"C7 ");
						 }
						 else
						 {
							 e.setAngle(i,"/ C7 ");
						 }
					 }
				 }
				 else
				 {
					 if (e.getNom().equals(c7.getRegent(c7.getSigne(i)))) {
						 if (e.getAngle(i).equals("")) {
							 e.setAngle(i,"C7 ");
						 }
						 else
						 {
							 e.setAngle(i,"/ C7 ");
						 }
					 }
				 }
				 // C10
				 if (c10.getSigne(i).equals("Cranc")) {
					 if (e.getNom().equals(c10.getRegent(lluna.getSigne(i)))) {
						 if (e.getAngle(i).equals("")) {
							 e.setAngle(i,"C10 ");
						 }
						 else
						 {
							 e.setAngle(i,"/ C10 ");
						 }
					 }
				 }
				 else
				 {
					 if (e.getNom().equals(c10.getRegent(c10.getSigne(i)))) {
						 if (e.getAngle(i).equals("")) {
							 e.setAngle(i,"C10 ");
						 }
						 else
						 {
							 e.setAngle(i,"/ C10 ");
						 }
					 }
				 }
			 }
		 }
	 }
 }
 
 public void canvisAngles() {
	 // guarda el signe al qual es troba cada angle PER CADA SEGON DEL PARTIT
	 int dia = diaIni;
	 int h = 0;

	 String signeC1 = c1.getSigneIni();
	 String signeC4 = c4.getSigneIni();
	 String signeC7 = c7.getSigneIni();
	 String signeC10 = c10.getSigneIni();
	 /*  
   //System.out.println("signeC1: " + signeC1);
	 System.out.println("signeC4: " + signeC4);
	 System.out.println("signeC7: " + signeC7);
	 System.out.println("signeC10: " + signeC10);
	 */  
	 c1.setSigne(0,signeC1);
	 c4.setSigne(0,signeC4);
	 c7.setSigne(0,signeC7);
	 c10.setSigne(0,signeC10);
	 horoscop.setCasa(new CasaPlacidus());
	 /*28:27:37N - 16:16:50 W ---> al revés, S i E en negatiu */
	 horoscop.setGrausCoordenades(modLon * (lonG + lonM / 60 + lonS / 3600), modLat * (latG + latM / 60 + latS / 3600));
	 
	 for (int i = 1; i < getSegons(); i++) {
		 // calcular les coordenades de les cases per un moment i
		 int momH = (int) i / 3600;
		 int momM = (int) (i - momH * 3600) / 60;
		 int momS = (int) i - momH * 3600 - momM * 60;
		 momS += segonIni;
		 if (momS > 59) {
			 momM++;
			 momS -= 60;
		 }
    
		 momM += minutIni;
		 if (momM > 59) {
			 momH++;
			 momM -= 60;
		 }
   
		 momH += horaIni;
		 if (momH > 23) {
			 momH -= 24;
			 dia++;
		 }
		 horoscop.setHora(dia, mesIni, anyIni, momH, momM, momS, zonahoraria);
		 horoscop.calcValors();
		 casesR = horoscop.casaEmprada.getCasesR();
		 
		 double d = CalcUtil.RaG(casesR[0]);
		 //System.out.println ("d per c1: " + d);
		 h = (int)Math.floor(d);
		 //System.out.println ("h per c1: " + h);
		 
		 if (h % 30 == 0 &&  (h + 30) != (c1.ordSigne(signeC1)) * 30) {
			 //System.out.println ("omplint c1. d: " + d);
			 signeC1 = c1.ompleRegent(i);
			 //System.out.println ("Nou signeC1: " + signeC1);
			 //System.out.println ("omplint c7");
			 signeC7 = c7.ompleRegent(i);
			 //System.out.println ("Nou signeC7: " + signeC7);
		 }
		 
		 d = CalcUtil.RaG(casesR[3]);
		 //System.out.println ("d per c4: " + d);
		 h = (int)Math.floor(d);
		 //System.out.println ("h per c4: " + h);
	
		 if (h % 30 == 0 &&  (h + 30) != (c4.ordSigne(signeC4)) * 30) {
			 //System.out.println ("omplint c4. d: " + d);
			 signeC4 = c4.ompleRegent(i);
			 //System.out.println ("Nou signeC4: " + signeC4);
			 //System.out.println ("omplint c10");
			 signeC10 = c10.ompleRegent(i);
			 //System.out.println ("Nou signeC10: " + signeC10);
		 }
		 
		 c1.setSigne(i,signeC1);
		 c4.setSigne(i,signeC4);
		 c7.setSigne(i,signeC7);
		 c10.setSigne(i,signeC10);
	 }
  /*
//System.out.println("c1.getMom(0): " + c1.getMom(0));
//System.out.println("c1.getMom(1): " + c1.getMom(1));
//System.out.println("c1.getSigne(558):" + c1.getSigne(558));
//System.out.println("c1.getSigne(4807):" + c1.getSigne(4807));
//System.out.println("c4.getMom(0): " + c4.getMom(0));
//System.out.println("c4.getMom(1): " + c4.getMom(1));
//System.out.println("c4.getSigne(558):" + c4.getSigne(558));
//System.out.println("c7.getMom(0): " + c7.getMom(0));
//System.out.println("c7.getMom(1): " + c7.getMom(1));
//System.out.println("c7.getSigne(558):" + c7.getSigne(558));
//System.out.println("c7.getSigne(4807):" + c7.getSigne(4807));
//System.out.println("c10.getMom(0): " + c10.getMom(0));
//System.out.println("c10.getMom(1): " + c10.getMom(1));
//System.out.println("c10.getSigne(558):" + c10.getSigne(558));
  */
 }
 
 public void escriureCSV(Aspecte asp, CSVWriter csvWriter) {
  // pendent potser signe de l'antisci per CSV
  String ant1, ant2, momIni, momFi = "";
  
  if (asp.getAnt1() == true) {
   ant1 = "S\u00ed";
  }
  else
  {
   ant1 = "No";
  }
  
  momIni = String.format("%02d",asp.getHMomIni()) + ":" + String.format("%02d",asp.getMMomIni()) + ":" + 
		  String.format("%02d",asp.getSMomIni());
  momFi = String.format("%02d",asp.getHMomFi()) + ":" + String.format("%02d",asp.getMMomFi()) + ":" + 
		  String.format("%02d",asp.getSMomFi());
  String[] aspCSV = {momIni, momFi, asp.getNom1(), ant1, asp.getCasa1(), asp.getCasaAnt1(), asp.getSigne1(), asp.getRegent1(0), asp.getRegent1(1), asp.getRegent1(2), 
  asp.getTipus(), asp.getNom2(), asp.getCasa2(), asp.getCasaAnt2(), asp.getSigne2(), asp.getRegent2(0), asp.getRegent2(1), asp.getRegent2(2), nomPartit};
  csvWriter.writeNext(aspCSV);
 }
 
 public void esRegent(Entitat e, int i) {
	 //for (Aspecte aspecte: Partit.aspectes) {
	 // i < Mom(0)
	 if (i < c1.getMom(0)) {
		 // C1
		 if (c1.getSigne(0).equals("Cranc")) {
			 if (lluna.getSigne(0).equals(lluna.getSigneFi())) {
				 if (e.getNom().equals(c1.getRegent(lluna.getSigne(0)))) {
					 for (int x = 0; x < 4; x++) {
						 if (e.getSigne(x).equals("")) {
							 e.setSigne(x,"C1");
						 }
					 }
				 }
			 }
		 }
		 else
		 {
			 if (e.getSigne(0).equals(e.getSigneFi())) {
				 if (e.getNom().equals(c1.getRegent(c1.getSigne(0)))) {
					 for (int x = 0; x < 4; x++) {
						 if (e.getSigne(x).equals("")) {
							 e.setSigne(x,"C1");
						 }
					 }
				 }
			 }
		 }
		 // C7
		 if (c7.getSigne(0).equals("Cranc")) {
			 if (lluna.getSigne(0).equals(lluna.getSigneFi())) {
				 if (e.getNom().equals(c7.getRegent(lluna.getSigne(0)))) {
					 for (int x = 0; x < 4; x++) {
						 if (e.getSigne(x).equals("")) {
							 e.setSigne(x,"C7");
						 }
					 }
				 }
			 }
		 }
		 else
		 {
			 if (e.getSigne(0).equals(e.getSigneFi())) {
				 if (e.getNom().equals(c7.getRegent(c7.getSigne(0)))) {
					 for (int x = 0; x < 4; x++) {
						 if (e.getSigne(x).equals("")) {
							 e.setSigne(x,"C7");
						 }
					 }
				 }
			 }
		 }
	 }
	 if (i < c4.getMom(0)) {
		 // C4
		 if (c4.getSigne(0).equals("Cranc")) {
			 if (lluna.getSigne(0).equals(lluna.getSigneFi())) {
				 if (e.getNom().equals(c4.getRegent(lluna.getSigne(0)))) {
					 for (int x = 0; x < 4; x++) {
						 if (e.getSigne(x).equals("")) {
							 e.setSigne(x,"C4");
						 }
					 }
				 }
			 }
		 }
		 else
		 {
			 if (e.getSigne(0).equals(e.getSigneFi())) {
				 if (e.getNom().equals(c4.getRegent(c1.getSigne(0)))) {
					 for (int x = 0; x < 4; x++) {
						 if (e.getSigne(x).equals("")) {
							 e.setSigne(x,"C4");
						 }
					 }
				 }
			 }
		 }
		 // C10
		 if (c10.getSigne(0).equals("Cranc")) {
			 if (lluna.getSigne(0).equals(lluna.getSigneFi())) {
				 if (e.getNom().equals(c10.getRegent(lluna.getSigne(0)))) {
					 for (int x = 0; x < 4; x++) {
						 if (e.getSigne(x).equals("")) {
							 e.setSigne(x,"C10");
						 }
					 }
				 }
			 }
		 }
		 else
		 {
			 if (e.getSigne(0).equals(e.getSigneFi())) {
				 if (e.getNom().equals(c10.getRegent(c10.getSigne(0)))) {
					 for (int x = 0; x < 4; x++) {
						 if (e.getSigne(x).equals("")) {
							 e.setSigne(x,"C10");
						 }
					 }
				 }
			 }
		 }
	 }
	 }
  
 public int canviCasaSol() {
	 int cc = 0;
	 boolean activaC6 = false;
	 //System.out.println("sol.getGIni(): " + sol.getGIni());
	 //System.out.println("sol.getMIni(): " + sol.getMIni());
	 //System.out.println("sol.getSIni(): " + sol.getSIni());
	 //System.out.println("sol.getSigneIni():" + sol.getSigneIni());
	 double grausSol = (sol.ordSigne(sol.getSigneIni()) - 1) * 30 + sol.getGIni() + sol.getMIni() / 60.0 + sol.getSIni() / 3600.0;
	 //System.out.println("grausSol: " + grausSol);
	 	 
	 // la casa inicial del Sol ens diu a quina casa ha de passar
	 if (sol.getCasaIni().equals("7")
		 | sol.getCasaIni().equals("7 - 8")
	     | sol.getCasaIni().equals("8")) {
			 activaC6 = true;
		 }
	 
	 for (int m = 0; m <= segons; m++) {
		   //toca calcular els graus de les cases per un moment m
		   int momH = (int) m / 3600;
		   int momM = (int) (m - momH * 3600) / 60;
		   int momS = (int) m - momH * 3600 - momM * 60;
		   momS += segonIni;
		   if (momS > 59) {
		    momM++;
		    momS -= 60;
		   }
		    
		   momM += minutIni;
		   if (momM > 59) {
		    momH++;
		    momM -= 60;
		   }
		   
		   momH += horaIni;
		   if (momH > 23) {
			   momH -= 24;
		   }
		   horoscop.setHora(diaIni, mesIni, anyIni, momH, momM, momS, zonahoraria);
		   horoscop.calcValors();
		   casesR = horoscop.casaEmprada.getCasesR();
		   //System.out.println("activaC6: " + activaC6);
		   if (activaC6 == true) {
				if (grausSol < CalcUtil.RaG(casesR[6])) {
					//System.out.println("m:" + m);
					//System.out.println("grausSol: " + grausSol);
					//System.out.println("CalcUtil.RaG(casesR[6]): " + CalcUtil.RaG(casesR[6]));
					sol.i = m;
					cc = m;
					m = segons;
				}
			}
			else
			{
				if (grausSol < CalcUtil.RaG(casesR[0])) {
					//System.out.println("m:" + m);
					//System.out.println("grausSol: " + grausSol);
					//System.out.println("CalcUtil.RaG(casesR[0]): " + CalcUtil.RaG(casesR[0]));
					sol.i = m;
					cc = m;
					m = segons;
			    }
			}
		   
	   grausSol += sol.getVelocitat() / 3600;
		   
	 }
	 return cc;
 }
 
 public void guardarGraus(Entitat e, double posicio) {
	 int g = 0;
	 int m = 0;
	 int s = 0;
	 g = (int)Math.floor(posicio);
	 m = (int)Math.floor((posicio - g) * 60);
	 s = (int)Math.floor((posicio - g - m / 60) * 60);
	 e.setG(g);
	 e.setM(m);
	 e.setS(s);
 }
 
 public void guardarGrausAnt(Entitat e, double posicio) {
	 int g = 0;
	 int m = 0;
	 int s = 0;
	 g = (int)Math.floor(posicio);
	 m = (int)Math.floor((posicio - g) * 60);
	 s = (int)Math.floor((posicio - g - m / 60) * 60);
	 e.setGAnt(g);
	 e.setMAnt(m);
	 e.setSAnt(s);
 }
 
 public String traduir(String signe) {
	 //signe = signe.replace("\u00e9","e");
	 //System.out.println("signe dintre:" + signe);
	 if (signe.equals("Aries")) {
		signe = "\u00c0ries";
	}
    if (signe.equals("Tauro")) {
     signe = "Taure";
    }
	// pendent 
	// processant "20180111 Barça - Celta 21.30 a" va bé
	// peta amb "20180114 Real Societat - Barça 20.45 a"
    if (signe.equals("G\u00E9minis")) {
	// peta amb "20180111 Barça - Celta 21.30 a"
	//if (signe.equals("Geminis")) {
	// peta amb "20180111 Barça - Celta 21.30 a"
	//if ("Géminis".equals(signe)) {
	//if ("G\u00e9minis".equals(signe)) {
     signe = "Bessons";
    }
    if (signe.equals("C\u00e1ncer")) {
     signe = "Cranc";
    }
    if (signe.equals("Leo")) {
     signe = "Lle\u00f3";
    }
    if (signe.equals("Virgo")) {
     signe = "Verge";
    }
    if (signe.equals("Libra")) {
     signe = "Balan\u00e7a";
    }
    if (signe.equals("Escorpio")) {
     signe = "Escorp\u00ed";
    }
    if (signe.equals("Sagitario")) {
     signe = "Sagitari";
    }
    if (signe.equals("Capricornio")) {
     signe = "Capricorn";
    }
    if (signe.equals("Acuario")) {
     signe = "Aquari";
    }
    if (signe.equals("Piscis")) {
     signe = "Peixos";
    }
	return signe;
 }
 
 public void setAngles(Aspecte aspecte, Entitat e, int i) {
	 // copia angles a cada regent d'un aspecte a partir dels angles dels quals cada entitat és regent
	 if (aspecte.getNom1().contains(e.getNom())) {
			 String[] angles = e.getAngle(i).split("/");
			 int y = 0;
			 for (int x = 0; x < 3 && y < angles.length; x++) {
				 /*
				 //System.out.println("getRegent1(" + x + "): " + aspecte.getRegent1(x));
				 //System.out.println("angles[ " + y + "]: " + angles[y]);
				 */ 
				 if (aspecte.getRegent1(x) == null) {
					 /*
					 //System.out.println("i1: " + i);
					 //System.out.println("Assigna angle a regent1. angles[" + y + "]: " + angles[y] + " copiat a regent1[" + x + "]");
					 */
					 aspecte.setRegent1(x,angles[y]);
					 y++;
				 }
				 else
				 {
					 if (aspecte.getRegent1(x).equals(angles[y])) {
						 /*
						 //System.out.println("i: " + i);
						 //System.out.println("Aquest angle ja es troba a regent1: " + angles[y]);
						 */	 
						 break;
					 }
				 }
			 }
		 }
	 
	 if (aspecte.getNom2().contains(e.getNom())) {
			 String[] angles = e.getAngle(i).split("/");
			 int y = 0;
			 for (int x = 0; x < 3 && y < angles.length; x++) {
				 /*
				 //System.out.println("getRegent2(" + x + "): " + aspecte.getRegent2(x));
				 //System.out.println("angles[ " + y + "]: " + angles[y]);
				 */	 
				 if (aspecte.getRegent2(x) == null) {
					 /*
					 //System.out.println("i2: " + i);
					 //System.out.println("Assigna angle a regent2. angles[" + y + "]: " + angles[y] + " copiat a regent2[" + x + "]");
					 */
					 aspecte.setRegent2(x,angles[y]);
					 y++;
				 }
				 else
				 {
					 if (aspecte.getRegent2(x).equals(angles[y])) {
						 /*
						 //System.out.println("i: " + i);
						 //System.out.println("Aquest angle ja es troba a regent2: " + angles[y]);
						 */	 
						 break;
					 }
				 }
			 }
		 }
 }
 
 protected void guardarGols(String fitxer) {
	 try
	 {
		 String cadena;
		 FileReader f = new FileReader(fitxer);
		 BufferedReader b = new BufferedReader(f);
		 cadena = b.readLine();
		 int s = 0;
		 while(cadena != null) {
			 //System.out.println("guardarGols:" + cadena);
			 if (!"".equals(cadena)) {
				 String[] g = cadena.split(";");
				 switch (g[0]) {
					 case "﻿COLORS":
					 this.colors = g[1];
					 break;
					 
					 case "INICI1":
					 String[] t = g[1].split(":");
					 
					 // segons que passen des de l'inici teòric del partit
					 s = (Integer.parseInt(t[0]) - horaIni) * 3600;
					 s += (Integer.parseInt(t[1]) - minutIni) * 60;
					 s += Integer.parseInt(t[2]) - segonIni;
					 
					 this.horaIni = Integer.parseInt(t[0]);
					 this.minutIni = Integer.parseInt(t[1]);
					 this.segonIni = Integer.parseInt(t[2]);
					 sol.moure(s);
					 lluna.moure(s);
					 mercuri.moure(s);
					 venus.moure(s);
					 mart.moure(s);
					 jupiter.moure(s);
					 saturn.moure(s);
					 if(ura instanceof Entitat){
						ura.moure(s);
					 }
					 if(neptu instanceof Entitat){
						neptu.moure(s);
					 }
					 if(pluto instanceof Entitat){
						pluto.moure(s);
					 }
					 node.moure(s);
					 fortuna.moure(s);
					 if(infortuni instanceof Entitat){
						 infortuni.moure(s);
					 }
					 c1.moure(s);
					 c4.moure(s);
					 c7.moure(s);
					 c10.moure(s);
					 break;
					 
					 case "FINAL1":
					 t = g[1].split(":");
					 this.horaIniDescans = Integer.parseInt(t[0]);
					 this.minutIniDescans = Integer.parseInt(t[1]);
					 this.segonIniDescans = Integer.parseInt(t[2]);
					 break;
					 
					 case "INICI2":
					 t = g[1].split(":");
					 this.horaFiDescans = Integer.parseInt(t[0]);
					 this.minutFiDescans = Integer.parseInt(t[1]);
					 this.segonFiDescans = Integer.parseInt(t[2]);
					 break;
					 
					 case "FINAL2":
					 t = g[1].split(":");
					 this.horaFi = Integer.parseInt(t[0]);
					 this.minutFi = Integer.parseInt(t[1]);
					 this.segonFi = Integer.parseInt(t[2]);
					 this.segons = (horaFi * 3600 + minutFi * 60 + segonFi) - (horaIni * 3600 + minutIni * 60 + segonIni);
					 // pendent... i si tot i rectificar inici i fi acaba a un dia diferent? 
					 //if (diaIni != diaFi) {this.segons += 86400;}
					 break;
					 
					 case "ASPECTE":
					 Gol gol = new Gol (this, g[0], g[1], g[2], g[3], g[4]);
					 gols.add(gol);
					 break;
					 
					 case "ASPECTENO":
					 gol = new Gol (this, g[0], g[1], g[2], g[3], g[4]);
					 gols.add(gol);
				 }
			 }
			 cadena = b.readLine();
		 }
		 b.close();
	 }
	 catch (FileNotFoundException e) {e.printStackTrace();} 
	 catch (IOException e) {e.printStackTrace();}
 }
 
 public int calcularGrauSimple(int g, int m, int s) {
	 int grauSimple = 0;
	 if (m < 30) {
		 grauSimple = g;
	 }
	
	 if (m == 30) {
		 if (s > 29) {
			 grauSimple = g + 1;
		 }
		 else {
			 grauSimple = g;
		 }
	 }
	
	 if (m > 30) {
		 grauSimple = g + 1;
	 }
	 
	 return grauSimple;
	}
}