import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/*
A l'hora de processar partits, no es poden copiar subcarpetes d'any, copiar directament els fitxers
Per partits fora de la zona horària de Barcelona, al nom del fitxer es posa l'hora d'inici de Barcelona,
però l'hora de les dades és la local del lloc on es fa el partit (carta-natal.es)
Pels gols, el moment del gol tamb� ha de ser a la hora del pa�s on es fa el partit (Anglaterra -1, etc...)
*/

public class Futbol {

 public static void main(String[] args) throws IOException  {
	 // 1. inicialitzem fitxers de sortida
	final String aspectesCSV = "./aspectes.csv";
	final String logCSV = "./log.txt";
	BufferedWriter aspectes = new BufferedWriter(new FileWriter(aspectesCSV));
	// true fa que afegeixi al fitxer JA EXISTENT (si no existeix el crea?)
	BufferedWriter log = new BufferedWriter(new FileWriter(logCSV,false));
	PrintWriter pwLog = null;
	CSVWriter csvAspectes = null;
	final ArrayList<String> nomspartits = new ArrayList<String>();
	final ArrayList<Partit> partits = new ArrayList<Partit>();
	
	// 1a. Aspectes
	try
	{
		aspectes.write("");
		aspectes.close();
		aspectes = Files.newBufferedWriter(Paths.get(aspectesCSV), 
				StandardCharsets.UTF_8, 
				StandardOpenOption.WRITE);
		csvAspectes = new CSVWriter (aspectes,
				csvAspectes.DEFAULT_SEPARATOR,
				csvAspectes.NO_QUOTE_CHARACTER,
				csvAspectes.DEFAULT_ESCAPE_CHARACTER,
				csvAspectes.DEFAULT_LINE_END);
		String[] titols = {"MomIni", "MomFi", "Entitat1", "Antisci1", "Casa1", "CasaAnt1", "Signe1", "Regent1a", "Regent1b", 
		"Regent1c", "Tipus", "Entitat2", "Casa2", "CasaAnt2", "Signe2", "Regent2a", "Regent2b", "Regent2c", "Partit"};
		csvAspectes.writeNext(titols);
	}
	catch (FileNotFoundException e) {e.printStackTrace();}
	catch (IOException e) {e.printStackTrace();}
	
	// 1b. AspectesGol
		
		final String aspectesGolCSV = "./aspectesGol.csv";
		BufferedWriter aspectesGol = new BufferedWriter(new FileWriter(aspectesGolCSV));
		CSVWriter csvAspectesGol = null;
		
		try
		{
			aspectesGol.write("");
			aspectesGol.close();
			aspectesGol = Files.newBufferedWriter(Paths.get(aspectesGolCSV),
					StandardCharsets.UTF_8, 
					StandardOpenOption.WRITE);
			csvAspectesGol = new CSVWriter(aspectesGol,
					csvAspectesGol.DEFAULT_SEPARATOR,
					csvAspectesGol.NO_QUOTE_CHARACTER,
					csvAspectesGol.DEFAULT_ESCAPE_CHARACTER,
					csvAspectesGol.DEFAULT_LINE_END);
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		
	// 1c. Log
	try
	{
		log.write("");
		log.close();
		log = Files.newBufferedWriter(Paths.get(logCSV),
				StandardCharsets.UTF_8, 
				StandardOpenOption.WRITE);
		pwLog = new PrintWriter(log);
	}
	catch (FileNotFoundException e) {e.printStackTrace();}
	catch (IOException e) {e.printStackTrace();}
	
	// 2. obtenim els fitxers del directori partits
	final File directori = new File("./Per processar/");
	llistarFitxers(directori, nomspartits);
	
	String p = "";
	String nompartit = "";
	Partit a = null;
	
	// 3. llegim els fitxers obtinguts
	for (int i = 0; i <= nomspartits.size() - 1; i++) {
		// Cal mirar si acaba per a / b / gols
		p = nomspartits.get(i);
		System.out.println("Fitxer llegit: " + p + "*");
		pwLog.println("Fitxer llegit: " + p + "*");
		if (p.endsWith("a.txt")) {
			nompartit = p.substring(0,p.length()-6);
			a = new Partit(nompartit);
			
			try {
				a.llegir("./Per processar/" + p, pwLog);
			}
			catch (FileNotFoundException e) {e.printStackTrace();}
			catch (IOException e) {e.printStackTrace();}
		}
		
		if (p.endsWith("b.txt")) {
			try {
				a.llegir("./Per processar/" + p, pwLog);
				partits.add(a);
			}
			catch (FileNotFoundException e) {e.printStackTrace();}
			catch (IOException e) {e.printStackTrace();}
		}
		
		if (p.endsWith("gols.csv")) {
			a.activaGols = true;
			a.guardarGols("./Per processar/" + p);
		}
	}
  	
	// 4. una vegada llegits, cal processar cada partit
	for (Partit partit: partits) {
		System.out.println("Processant " + partit.nomPartit + "...");
		pwLog.println("Processant " + partit.nomPartit + "...");
		partit.canvisAngles();
		partit.anglesRegents();
		
		if (partit.mixt == true) {
			int iMixt = partit.canviCasaSol();
		
			// calcula moment quan canvia de diurn a nocturn
			int momH = (int) iMixt / 3600;
			int momM = (int) (iMixt - momH * 3600) / 60;
			int momS = iMixt - momH * 3600 - momM * 60;
			momS += partit.segonIni;
			if (momS > 59) {
				momM++;
				momS -= 60;
			}
		    
			momM += partit.minutIni;
			if (momM > 59) {
				momH++;
				momM -= 60;
			}
		   
			momH += partit.horaIni;
			if (momH > 23) {
				momH -= 24;
			}
			pwLog.println("partit mixt. Canvia a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
			//System.out.println("partit mixt. Canvia a les " + String.format("%02d",momH) + ":" + String.format("%02d",momM) + ":" + String.format("%02d",momS));
		}
  
		//creem els antiscis per cada entitat que no sigui una casa (id < 13)
		for (Entitat e: partit.entitats) {
			if (e.getId() < 13) {
				e.setAntisci();
			}
		}
  
		//calculem els aspectes
		for(Entitat e1 : partit.entitats) {
			for(Entitat e2 : partit.entitats) {
				boolean aspectat = false;
				if (e1.getId() < e2.getId()) {
					if (e1.getId() < 13) {
						// Lluna amb resta de físics
						if (e1.getNom().equals("Lluna")
							|   e2.getNom().equals("Lluna")
						&& !e2.getNom().substring(0,1).equals("C")) {
							if (e2.getNom().equals("Fortuna")
								| e2.getNom().equals("Infortuni")) {
								    //pwLog.println("fortunes1.e1: " + e1.getNom() + " e2: " + e2.getNom());
									//System.out.println("fortunes1.e1: " + e1.getNom() + " e2: " + e2.getNom());
									partit.fortunes(e1, e2, csvAspectes);
									aspectat = true;
								}
								else
								{
									if (!e2.getNom().equals("Node")) {
										//System.out.println("lents1.e1: " + e1.getNom() + " e2: " + e2.getNom());
										partit.lents(e1, e2, csvAspectes);
										aspectat = true;
									}
								}
						}
						// Cases 1/4
						if (aspectat == false) {
							if (!e1.getNom().substring(0,1).equals("C")
								&& (e2.getNom().equals("Casa 1 (AC)")
							|   e2.getNom().equals("Casa 4"))) {
								if (e1.getNom().equals("Fortuna")
									| e1.getNom().equals("Infortuni")) {
										// fortuna = lluna - sol + ascendent
										// infortuni diurn = mart - saturn + ascendent
										// infortuni nocturn = saturn - mart + ascendent
									    //pwLog.println("rapids1.e1: " + e1.getNom() + " e2: " + e2.getNom());
										//System.out.println("rapids1.e1: " + e1.getNom() + " e2: " + e2.getNom());
										partit.rapids(e1, e2, csvAspectes);
										aspectat = true;
									}
									else
									{
										//pwLog.println("cases1.e1: " + e1.getNom() + " e2: " + e2.getNom());
										//System.out.println("cases1.e1: " + e1.getNom() + " e2: " + e2.getNom());
										partit.cases(e1, e2, csvAspectes);
										aspectat = true;
									}
							}
						}
						// Físics a e1 (i sense Fortuna amb Infortuni ni Node amb altre)
						if (aspectat == false) {
							if (!e2.getNom().substring(0,1).equals("C")) {
								if ((e1.getNom().equals("Fortuna")
									&& e2.getNom().equals("Infortuni"))
								| e1.getNom().equals("Node")
								| e2.getNom().equals("Node")) {
									continue;
								}
								else
								{
									if (e2.getNom().equals("Fortuna")
										| e2.getNom().equals("Infortuni")) {
										    //pwLog.println("fortunes2.e1: " + e1.getNom() + " e2: " + e2.getNom());
											//System.out.println("fortunes2.e1: " + e1.getNom() + " e2: " + e2.getNom());
											partit.fortunes(e1, e2, csvAspectes);
											aspectat = true;
										}
										else
										{
											//pwLog.println("lents2.e1: " + e1.getNom() + " e2: " + e2.getNom());
											//System.out.println("lents2.e1: " + e1.getNom() + " e2: " + e2.getNom());
											partit.lents(e1, e2, csvAspectes);
											aspectat = true;
										}
								}
							}
						}
					}
				}
			}
		}
	  
		// gravem el nom del partit i colors
		String[] buit = {" "};
		csvAspectesGol.writeNext(buit);
		String[] nomP = {partit.nomPartit};
		csvAspectesGol.writeNext(nomP);
		String[] colors = {partit.colors};
		csvAspectesGol.writeNext(colors);
		
		for (Gol gol: partit.gols) {
			String[] textGol = {gol.getTipus(), gol.getHora(), gol.getResultat(), gol.getJugador(), gol.getObservacions()};
			csvAspectesGol.writeNext(buit);
			csvAspectesGol.writeNext(textGol);
			for (String aspecte: gol.getAspectes()) {
				String[] asp = {aspecte};
				csvAspectesGol.writeNext(asp);
			}
		}
	
		
		
		// 5. períodes dels angles a cada signe
  String angle = "";
  String regent = "";
  int i1, i2 = 0;
  int h1, m1, s1, h2, m2, s2 = 0;
  // 1. signes de C1
  if (partit.c1.getSigne(0).equals(partit.c1.getSigne(partit.getSegons() - 1))) {
	  // C1 no canvia en tot el partit
	  regent = partit.c1.getRegent(partit.c1.getSigne(0));
	  angle = "[" + String.format("%02d",partit.horaIni) + ":" + String.format("%02d",partit.minutIni) + ":" + String.format("%02d",partit.segonIni) 
			  + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) + ":" + String.format("%02d",partit.segonFi) 
			  + "] C1: " + partit.c1.getSigne(0) + " (" + regent + ")";
	  if (partit.c1.getSigne(0).equals("Cranc")) {
		  // pendent lluna canvia de signe per mostrar regent del signe (moment inicial)
		  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(0)) + ")";
	  }
	  
	  escriureAspecteGol(angle, csvAspectesGol);
  }
  else
  {
	  // C1 canvia de signe durant el partit
	  // 1r moment que C1 canvia de signe
	  i1 = partit.c1.getMom(0);
	  h1 = (int) i1 / 3600;
	  m1 = (int) (i1 - h1 * 3600) / 60;
	  s1 = (int) i1 - h1 * 3600 - m1 * 60;
      s1 += partit.segonIni;
      if (s1 > 59) {
		  m1++;
		  s1 -= 60;
	  }
    
	  m1 += partit.minutIni;
	  if (m1 > 59) {
		  h1++;
		  m1 -= 60;
	  }
   
	  h1 += partit.horaIni;
	  if (h1 > 23) {
		  h1 -= 24;
	  }
	  // signe per 1r tram de C1
	  regent = partit.c1.getRegent(partit.c1.getSigne(0));
	  angle = "[" + String.format("%02d",partit.horaIni) + ":" + String.format("%02d",partit.minutIni) + ":" + 
	  String.format("%02d",partit.segonIni) + "-" + String.format("%02d",h1) + ":" + 
	  String.format("%02d",m1) + ":" + String.format("%02d",s1) + "] C1: " + 
	  partit.c1.getSigne(0) + " (" + regent + ")";
	  if (partit.c1.getSigne(0).equals("Cranc")) {
		  // pendent lluna canvia de signe per mostrar regent del signe (moment inicial)
		  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(0)) + ")";
	  }
	  escriureAspecteGol(angle, csvAspectesGol);

      if (partit.c1.getMom(1) == 0) {
		  // C1 amb dos signes
		  // signe per 2n tram de C1
		  regent = partit.c1.getRegent(partit.c1.getSigne(partit.c1.getMom(0)));
		  angle = "[" + String.format("%02d",h1) + ":" + String.format("%02d",m1) + ":" + 
		  String.format("%02d",s1) + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) 
		  + ":" + String.format("%02d",partit.segonFi) + "] C1: " + partit.c1.getSigne(partit.c1.getMom(0)) + " (" + regent + ")";
		  if (partit.c1.getSigne(partit.c1.getMom(0)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c1.getMom(0))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
	  }
	  else
	  {
		  // C1 amb tres signes
		  // 2n moment que C1 canvia de signe
		  regent = partit.c1.getRegent(partit.c1.getSigne(partit.c1.getMom(1)));
		  i2 = partit.c1.getMom(1);
		  h2 = (int) i2 / 3600;
		  m2 = (int) (i2 - h2 * 3600) / 60;
		  s2 = (int) i2 - h2 * 3600 - m2 * 60;
		  s2 += partit.segonIni;
		  if (s2 > 59) {
			  m2++;
			  s2 -= 60;
		  }
    
		  m2 += partit.minutIni;
		  if (m2 > 59) {
			  h2++;
			  m2 -= 60;
		  }
   
		  h2 += partit.horaIni;
		  if (h2 > 23) {
			  h2 -= 24;
		  }
		  
		  // signe per 2n tram de C1
		  regent = partit.c1.getRegent(partit.c1.getSigne(partit.c1.getMom(0)));
		  angle = "[" + String.format("%02d",h1) + ":" + String.format("%02d",m1) + ":" + 
		  String.format("%02d",s1) + "-" + String.format("%02d",h2) + ":" + String.format("%02d",m2) 
		  + ":" + String.format("%02d",s2) + "] C1: " + partit.c1.getSigne(partit.c1.getMom(0)) + " (" + regent + ")";
		  if (partit.c1.getSigne(partit.c1.getMom(0)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c1.getMom(0))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
		  
		  // signe per 3r tram de C1
		  regent = partit.c1.getRegent(partit.c1.getSigne(partit.c1.getMom(1)));
		  angle = "[" + String.format("%02d",h2) + ":" + String.format("%02d",m2) + ":" + 
		  String.format("%02d",s2) + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) 
		  + ":" + String.format("%02d",partit.segonFi) + "] C1: " + partit.c1.getSigne(partit.c1.getMom(1)) + " (" + regent + ")";
		  if (partit.c1.getSigne(partit.c1.getMom(1)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c1.getMom(1))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
	  }
  }
  // fi de signes C1
  // 2. signes de C4
  if (partit.c4.getSigne(0).equals(partit.c4.getSigne(partit.getSegons()-1))) {
	  // C4 no canvia en tot el partit
	  regent = partit.c4.getRegent(partit.c4.getSigne(0));
	  angle = "[" + String.format("%02d",partit.horaIni) + ":" + String.format("%02d",partit.minutIni) + ":" + String.format("%02d",partit.segonIni) 
			  + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) + ":" + String.format("%02d",partit.segonFi)
			  + "] C4: " + partit.c4.getSigne(0) + " (" + regent + ")";
	  if (partit.c4.getSigne(0).equals("Cranc")) {
		  // pendent lluna canvia de signe per mostrar regent del signe (moment inicial)
		  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(0)) + ")";
	  }
	  escriureAspecteGol(angle, csvAspectesGol);
  }
  else
  {
	  // C4 canvia de signe durant el partit
	  // 1r moment que C4 canvia de signe
	  i1 = partit.c4.getMom(0);
	  h1 = (int) i1 / 3600;
	  m1 = (int) (i1 - h1 * 3600) / 60;
	  s1 = (int) i1 - h1 * 3600 - m1 * 60;
      s1 += partit.segonIni;
      if (s1 > 59) {
		  m1++;
		  s1 -= 60;
	  }
    
	  m1 += partit.minutIni;
	  if (m1 > 59) {
		  h1++;
		  m1 -= 60;
	  }
   
	  h1 += partit.horaIni;
	  if (h1 > 23) {
		  h1 -= 24;
	  }
	  // signe per 1r tram de C4
	  regent = partit.c4.getRegent(partit.c4.getSigne(0));
	  angle = "[" + String.format("%02d",partit.horaIni) + ":" + String.format("%02d",partit.minutIni) + ":" + 
	  String.format("%02d",partit.segonIni) + "-" + String.format("%02d",h1) + ":" + 
	  String.format("%02d",m1) + ":" + String.format("%02d",s1) + "] C4: " + 
	  partit.c4.getSigne(0) + " (" + regent + ")";
	  if (partit.c4.getSigne(0).equals("Cranc")) {
		  // pendent lluna canvia de signe per mostrar regent del signe (moment inicial)
		  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(0)) + ")";
	  }
	  escriureAspecteGol(angle, csvAspectesGol);
   
      if (partit.c4.getMom(1) == 0) {
		  // c4 amb dos signes
		  // signe per 2n tram de C4
		  regent = partit.c4.getRegent(partit.c4.getSigne(partit.c4.getMom(0)));
		  angle = "[" + String.format("%02d",h1) + ":" + String.format("%02d",m1) + ":" + 
		  String.format("%02d",s1) + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) 
		  + ":" + String.format("%02d",partit.segonFi) + "] C4: " + partit.c4.getSigne(partit.c4.getMom(0)) + " (" + regent + ")";
		  if (partit.c4.getSigne(partit.c4.getMom(0)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c4.getMom(0))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
	  }
	  else
	  {
		  // c4 amb tres signes
		  // 2n moment que C4 canvia de signe
		  i2 = partit.c4.getMom(1);
		  h2 = (int) i2 / 3600;
		  m2 = (int) (i2 - h2 * 3600) / 60;
		  s2 = (int) i2 - h2 * 3600 - m2 * 60;
		  s2 += partit.segonIni;
		  if (s2 > 59) {
			  m2++;
			  s2 -= 60;
		  }
    
		  m2 += partit.minutIni;
		  if (m2 > 59) {
			  h2++;
			  m2 -= 60;
		  }
   
		  h2 += partit.horaIni;
		  if (h2 > 23) {
			  h2 -= 24;
		  }
		  // signe per 2n tram de C4
		  regent = partit.c4.getRegent(partit.c4.getSigne(partit.c4.getMom(0)));
		  angle = "[" + String.format("%02d",h1) + ":" + String.format("%02d",m1) + ":" + 
		  String.format("%02d",s1) + "-" + String.format("%02d",h2) + ":" + String.format("%02d",m2) 
		  + ":" + String.format("%02d",s2) + "] C4: " + partit.c4.getSigne(partit.c4.getMom(0)) + " (" + regent + ")";
		  if (partit.c4.getSigne(partit.c4.getMom(0)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c4.getMom(0))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
		  
		  // signe per 3r tram de C4
		  regent = partit.c4.getRegent(partit.c4.getSigne(partit.c4.getMom(1)));
		  angle = "[" + String.format("%02d",h2) + ":" + String.format("%02d",m2) + ":" + 
		  String.format("%02d",s2) + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) 
		  + ":" + String.format("%02d",partit.segonFi) + "] C4: " + partit.c4.getSigne(partit.c4.getMom(1)) + " (" + regent + ")";
		  if (partit.c4.getSigne(partit.c4.getMom(1)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c4.getMom(1))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
	  }
  }
	  // fi de signes C4
	  // 3. signes de C7
	  if (partit.c7.getSigne(0).equals(partit.c7.getSigne(partit.getSegons()-1))) {
	  // C7 no canvia en tot el partit
	  regent = partit.c7.getRegent(partit.c7.getSigne(0));
	  angle = "[" + String.format("%02d",partit.horaIni) + ":" + String.format("%02d",partit.minutIni) + ":" + String.format("%02d",partit.segonIni) 
			  + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) + ":" + String.format("%02d",partit.segonFi)
			  + "] C7: " + partit.c7.getSigne(0) + " (" + regent + ")";
	  if (partit.c7.getSigne(0).equals("Cranc")) {
		  // pendent lluna canvia de signe per mostrar regent del signe (moment inicial)
		  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(0)) + ")";
	  }
	  escriureAspecteGol(angle, csvAspectesGol);
  }
  else
  {
	  // C7 canvia de signe durant el partit
	  // 1r moment que C7 canvia de signe
	  i1 = partit.c7.getMom(0);
	  h1 = (int) i1 / 3600;
	  m1 = (int) (i1 - h1 * 3600) / 60;
	  s1 = (int) i1 - h1 * 3600 - m1 * 60;
      s1 += partit.segonIni;
      if (s1 > 59) {
		  m1++;
		  s1 -= 60;
	  }
    
	  m1 += partit.minutIni;
	  if (m1 > 59) {
		  h1++;
		  m1 -= 60;
	  }
   
	  h1 += partit.horaIni;
	  if (h1 > 23) {
		  h1 -= 24;
	  }
	  // signe per 1r tram de C7
	  regent = partit.c7.getRegent(partit.c7.getSigne(0));
	  angle = "[" + String.format("%02d",partit.horaIni) + ":" + String.format("%02d",partit.minutIni) + ":" + 
	  String.format("%02d",partit.segonIni) + "-" + String.format("%02d",h1) + ":" + 
	  String.format("%02d",m1) + ":" + String.format("%02d",s1) + "] C7: " + 
	  partit.c7.getSigne(0) + " (" + regent + ")";
	  if (partit.c7.getSigne(0).equals("Cranc")) {
		  // pendent lluna canvia de signe per mostrar regent del signe (moment inicial)
		  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(0)) + ")";
	  }
	  escriureAspecteGol(angle, csvAspectesGol);
   
      if (partit.c7.getMom(1) == 0) {
		  // C7 amb dos signes
		  // signe per 2n tram de C7
		  regent = partit.c7.getRegent(partit.c7.getSigne(partit.c7.getMom(0)));
		  angle = "[" + String.format("%02d",h1) + ":" + String.format("%02d",m1) + ":" + 
		  String.format("%02d",s1) + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) 
		  + ":" + String.format("%02d",partit.segonFi) + "] C7: " + partit.c7.getSigne(partit.c7.getMom(0)) + " (" + regent + ")";
		  if (partit.c7.getSigne(partit.c7.getMom(0)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c7.getMom(0))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
	  }
	  else
	  {
		  // C7 amb tres signes
		  // 2n moment que C7 canvia de signe
		  i2 = partit.c7.getMom(1);
		  h2 = (int) i2 / 3600;
		  m2 = (int) (i2 - h2 * 3600) / 60;
		  s2 = (int) i2 - h2 * 3600 - m2 * 60;
		  s2 += partit.segonIni;
		  if (s2 > 59) {
			  m2++;
			  s2 -= 60;
		  }
    
		  m2 += partit.minutIni;
		  if (m2 > 59) {
			  h2++;
			  m2 -= 60;
		  }
   
		  h2 += partit.horaIni;
		  if (h2 > 23) {
			  h2 -= 24;
		  }
		  // signe per 2n tram de C7
		  regent = partit.c7.getRegent(partit.c7.getSigne(partit.c7.getMom(0)));
		  angle = "[" + String.format("%02d",h1) + ":" + String.format("%02d",m1) + ":" + 
		  String.format("%02d",s1) + "-" + String.format("%02d",h2) + ":" + String.format("%02d",m2) 
		  + ":" + String.format("%02d",s2) + "] C7: " + partit.c7.getSigne(partit.c7.getMom(0)) + " (" + regent + ")";
		  if (partit.c7.getSigne(partit.c7.getMom(0)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c7.getMom(0))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
		  
		  // signe per 3r tram de C7
		  regent = partit.c7.getRegent(partit.c7.getSigne(partit.c7.getMom(1)));
		  angle = "[" + String.format("%02d",h2) + ":" + String.format("%02d",m2) + ":" + 
		  String.format("%02d",s2) + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) 
		  + ":" + String.format("%02d",partit.segonFi) + "] C7: " + partit.c7.getSigne(partit.c7.getMom(1)) + " (" + regent + ")";
		  if (partit.c7.getSigne(partit.c7.getMom(1)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c7.getMom(1))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
	  }
  }
  // fi de signes C7
  // 4. signes de C10
  if (partit.c10.getSigne(0).equals(partit.c10.getSigne(partit.getSegons() - 1))) {
	  // C10 no canvia en tot el partit
	  regent = partit.c10.getRegent(partit.c10.getSigne(0));
	  angle = "[" + String.format("%02d",partit.horaIni) + ":" + String.format("%02d",partit.minutIni) + ":" + String.format("%02d",partit.segonIni) 
			  + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) + ":" + String.format("%02d",partit.segonFi)
			  + "] C10: " + partit.c10.getSigne(0) + " (" + regent + ")";
	  if (partit.c10.getSigne(0).equals("Cranc")) {
		  // pendent lluna canvia de signe per mostrar regent del signe (moment inicial)
		  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(0)) + ")";
	  }
	  escriureAspecteGol(angle, csvAspectesGol);
  }
  else
  {
	  // C10 canvia de signe durant el partit
	  // 1r moment que C10 canvia de signe
	  i1 = partit.c10.getMom(0); 
	  h1 = (int) i1 / 3600;
	  m1 = (int) (i1 - h1 * 3600) / 60;
	  s1 = (int) i1 - h1 * 3600 - m1 * 60;
      s1 += partit.segonIni;
      if (s1 > 59) {
		  m1++;
		  s1 -= 60;
	  }
    
	  m1 += partit.minutIni;
	  if (m1 > 59) {
		  h1++;
		  m1 -= 60;
	  }
   
	  h1 += partit.horaIni;
	  if (h1 > 23) {
		  h1 -= 24;
	  }
	  // signe per 1r tram de C10
	  regent = partit.c10.getRegent(partit.c10.getSigne(0));
	  angle = "[" + String.format("%02d",partit.horaIni) + ":" + String.format("%02d",partit.minutIni) + ":" + 
	  String.format("%02d",partit.segonIni) + "-" + String.format("%02d",h1) + ":" + 
	  String.format("%02d",m1) + ":" + String.format("%02d",s1) + "] C10: " + 
	  partit.c10.getSigne(0) + " (" + regent + ")";
	  if (partit.c10.getSigne(0).equals("Cranc")) {
		  // pendent lluna canvia de signe per mostrar regent del signe (moment inicial)
		  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(0)) + ")";
	  }
	  escriureAspecteGol(angle, csvAspectesGol);
   
      if (partit.c10.getMom(1) == 0) {
		  // C10 amb dos signes
		  // signe per 2n tram de C10
		  regent = partit.c10.getRegent(partit.c10.getSigne(partit.c10.getMom(0)));
		  angle = "[" + String.format("%02d",h1) + ":" + String.format("%02d",m1) + ":" + 
		  String.format("%02d",s1) + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) 
		  + ":" + String.format("%02d",partit.segonFi) + "] C10: " + partit.c10.getSigne(partit.c10.getMom(0)) + " (" + regent + ")";
		  if (partit.c10.getSigne(partit.c10.getMom(0)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c10.getMom(0))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
	  }
	  else
	  {
		  // C10 amb tres signes
		  // 2n moment que C10 canvia de signe
		  regent = partit.c10.getRegent(partit.c10.getSigne(partit.c10.getMom(1)));
		  i2 = partit.c10.getMom(1);
		  h2 = (int) i2 / 3600;
		  m2 = (int) (i2 - h2 * 3600) / 60;
		  s2 = (int) i2 - h2 * 3600 - m2 * 60;
		  s2 += partit.segonIni;
		  if (s2 > 59) {
			  m2++;
			  s2 -= 60;
		  }
    
		  m2 += partit.minutIni;
		  if (m2 > 59) {
			  h2++;
			  m2 -= 60;
		  }
   
		  h2 += partit.horaIni;
		  if (h2 > 23) {
			  h2 -= 24;
		  }
		  
		  // signe per 2n tram de C10
		  regent = partit.c10.getRegent(partit.c10.getSigne(partit.c10.getMom(0)));
		  angle = "[" + String.format("%02d",h1) + ":" + String.format("%02d",m1) + ":" + 
		  String.format("%02d",s1) + "-" + String.format("%02d",h2) + ":" + String.format("%02d",m2) 
		  + ":" + String.format("%02d",s2) + "] C10: " + partit.c10.getSigne(partit.c10.getMom(0)) + " (" + regent + ")";
		  if (partit.c10.getSigne(partit.c10.getMom(0)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c10.getMom(0))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
		  
		  // signe per 3r tram de C10
		  regent = partit.c10.getRegent(partit.c10.getSigne(partit.c10.getMom(1)));
		  angle = "[" + String.format("%02d",h2) + ":" + String.format("%02d",m2) + ":" + 
		  String.format("%02d",s2) + "-" + String.format("%02d",partit.horaFi) + ":" + String.format("%02d",partit.minutFi) 
		  + ":" + String.format("%02d",partit.segonFi) + "] C10: " + partit.c10.getSigne(partit.c10.getMom(1)) + " (" + regent + ")";
		  if (partit.c10.getSigne(partit.c10.getMom(1)).equals("Cranc")) {
			  // pendent lluna canvia de signe per mostrar regent del signe (ja agafa moment del canvi de signe del angle)
			  angle += " (" + partit.lluna.getRegent(partit.lluna.getSigne(partit.c10.getMom(1))) + ")";
		  }
		  escriureAspecteGol(angle, csvAspectesGol);
	  }
  }

  /*
  // ordenem tots els aspectes per inicial - final
  partit.aspectes.sort(null);
  // mostrem tots els aspectes ordenats per moment inicial i final
  for (Aspecte aspecte: partit.aspectes) {
	  String asp, regent1, regent2 = "";
	  if (aspecte.getRegent1(0) != null) {
		  regent1 = aspecte.getRegent1(0);
		  if (aspecte.getRegent1(1) != null) {
			  regent1 += "/" + aspecte.getRegent1(1);
			  if (aspecte.getRegent1(2) != null) {
				  regent1 += "/" + aspecte.getRegent1(2);
			  }
		  }
	  }
	  else
	  {
		  regent1 = "";
	  }

	  asp = "[" + String.format("%02d",aspecte.getHMomIni()) + ":" + String.format("%02d",aspecte.getMMomIni()) + ":" + 
	  String.format("%02d",aspecte.getSMomIni()) + "-" + String.format("%02d",aspecte.getHMomFi()) + ":" + 
	  String.format("%02d",aspecte.getMMomFi()) + ":" + String.format("%02d",aspecte.getSMomFi()) + "] " + 
	  aspecte.nom1 + " (" + aspecte.getCasa1() + "/" + aspecte.getCasaAnt1() + ") " + regent1 + " " + aspecte.tipus + aspecte.nom2;
	  
	  if (!aspecte.getNom2().substring(0,1).equals("C")) {
		if (aspecte.getRegent2(0) != null) {
		  regent1 = aspecte.getRegent2(0);
		  if (aspecte.getRegent2(1) != null) {
			  regent1 += "/" + aspecte.getRegent2(1);
			  if (aspecte.getRegent2(2) != null) {
				  regent1 += "/" + aspecte.getRegent2(2);
			  }
		  }
	  }
	  else
	  {
		regent2 = "";
	  }
		asp += " (" + aspecte.getCasa2() + "/" + aspecte.getCasaAnt2() + ") " + regent2;
	  }
	  pwLog.println(asp);
	//System.out.println(asp);
  }
  */
 }
  log.close();
  csvAspectes.close();
  csvAspectesGol.close();
}
 
 public static void llistarFitxers(final File directori, final ArrayList<String> partits) {
	 for (final File fitxerEntrada : directori.listFiles()) {
	        if (fitxerEntrada.isDirectory()) {
	            llistarFitxers(fitxerEntrada, partits);
	        } else {
	        	//pwLog.println(fitxerEntrada.getName());
	            //System.out.println(fitxerEntrada.getName());
	            partits.add(fitxerEntrada.getName());
	        }
	    }
	}
 
 public static void escriureAspecteGol(String angle, CSVWriter csvAspectesGol) {
	 String[] ang = {angle};
	 csvAspectesGol.writeNext(ang);
 }
}
