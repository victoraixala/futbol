import java.util.*;

public class Entitat {
	String nom, signeIni, signeFi, casaIni, casaFi, casaIniAnt, casaFiAnt = "";
	String casa, casaAnt = "";
	String signeAnt, signeIniAnt, signeFiAnt, estat = "";
	int gIni, gFi, mIni, mFi, sIni, sFi = 0;
	int grauSimple = 0; 
	int grauSimpleAnt = 0;
	int g = 0;
	int m = 0;
	int s = 0;
	// per partits mixtos diu quan el sol s'amaga
	int i = 0;
	int gAnt = 0;
	int mAnt = 0;
	int sAnt = 0;
	int gIniAnt, gFiAnt, mIniAnt, mFiAnt, sIniAnt, sFiAnt = 0;
	int hMomCanvi, mMomCanvi, sMomCanvi = 0;
	// signe al qual pertany una entitat cada segon del partit (només les lentes i els angles)
	String[] signe = null;
	// angles que pot regir un planeta cada segon del partit (concatenat)
	String[] angle = null;
	// moments als quals un angle canvia de signe
	int[] mom = new int[2];
	double velocitat = 0;
	int id = 0;
	Partit partit = null;
	
	public Entitat (Partit partit, String nom, String signe, int g, int m, int s, String casa, String estat) {
		this.partit = partit;
		this.id = partit.i;
		this.nom = nom;
		this.signeIni = signe;
		this.gIni = g;
		this.mIni = m;
		this.sIni = s;
		this.casaIni = casa;
		this.estat = estat;
	}
	
	private static Map<String, Integer> signes = new HashMap<String, Integer>();
	static {
		signes.put("\u00c0ries", 1);
		signes.put("Taure", 2);
		signes.put("Bessons", 3);
		signes.put("Cranc", 4);
		signes.put("Lle\u00f3", 5);
		signes.put("Verge", 6);
		signes.put("Balan\u00e7a", 7);
		signes.put("Escorp\u00ed", 8);
		signes.put("Sagitari", 9);
		signes.put("Capricorn", 10);
		signes.put("Aquari", 11);
		signes.put("Peixos", 12);
	}
	
	private static Map<Integer, String> cases = new HashMap<Integer, String>();
	static {
		cases.put(1, "\u00c0ries");
		cases.put(2, "Taure");
		cases.put(3, "Bessons");
		cases.put(4, "Cranc");
		cases.put(5, "Lle\u00f3");
		cases.put(6, "Verge");
		cases.put(7, "Balan\u00e7a");
		cases.put(8, "Escorp\u00ed");
		cases.put(9, "Sagitari");
		cases.put(10, "Capricorn");
		cases.put(11, "Aquari");
		cases.put(12, "Peixos");
	}

	private static Map<String, String> regents = new LinkedHashMap<String, String>();
	static {
		regents.put("\u00c0ries", "Mart");
		regents.put("Taure", "Venus");
		regents.put("Bessons", "Mercuri");
		regents.put("Cranc", "Lluna");
		regents.put("Lle\u00f3", "Sol");
		regents.put("Verge", "Mercuri");
		regents.put("Balan\u00e7a", "Venus");
		regents.put("Escorp\u00ed", "Mart");
		regents.put("Sagitari", "J\u00fapiter");
		regents.put("Capricorn", "Saturn");
		regents.put("Aquari", "Saturn");
		regents.put("Peixos", "J\u00fapiter");
	}
	
	private static Map<String, String> antiscis = new HashMap<String, String>();
	static {
		antiscis.put("\u00c0ries", "Verge");
		antiscis.put("Taure", "Lle\u00f3");
		antiscis.put("Bessons", "Cranc");
		antiscis.put("Cranc", "Bessons");
		antiscis.put("Lle\u00f3", "Taure");
		antiscis.put("Verge", "\u00c0ries");
		antiscis.put("Balan\u00e7a", "Peixos");
		antiscis.put("Escorp\u00ed", "Aquari");
		antiscis.put("Sagitari", "Capricorn");
		antiscis.put("Capricorn", "Sagitari");
		antiscis.put("Aquari", "Escorp\u00ed");
		antiscis.put("Peixos","Balan\u00e7a");
	}

    public String getSigne (int x) {
		return this.signe[x];
	}
	
	public void setSigne (int x, String signe) {
		this.signe[x] = signe;
	}

	public String getAngle(int x) {
		return this.angle[x];
	}
	
	public void setAngle(int x, String angle) {
		// A un planeta regent, els angles que regeix
		this.angle[x] += angle;
	}

	public int getMom(int x) {
		return this.mom[x];
	}
			
	public int getG() {
		return g;
	}
	
	public int getM() {
		return m;
	}
	
	public int getS() {
		return s;
	}
	
	public int getGAnt() {
		return gAnt;
	}
	
	public int getMAnt() {
		return mAnt;
	}
	
	public int getSAnt() {
		return sAnt;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public void setM(int m) {
		this.m = m;
	}
	
	public void setS(int s) {
		this.s = s;
	}
	
	public void setGAnt(int g) {
		this.gAnt = g;
	}
	
	public void setMAnt(int m) {
		this.mAnt = m;
	}
	
	public void setSAnt(int s) {
		this.sAnt = s;
	}

    public String getSigneAnt() {
		return signeAnt;
	}	
	
	public void setSigneAnt(String signe) {
		signeAnt = signe;
	}
		
	public int ordSigne(String signe) {
		return signes.get(signe);
	}
	
	public String cases(int casa) {
		return cases.get(casa);
	}
	
	public String getRegent(String signe) {
		return regents.get(signe);
	}
	
	public String getAntisci(String signe) {
		return antiscis.get(signe);
	}
	
	public String getCasa() {
		return casa;
	}
	
	public void setCasa(String casa) {
		this.casa = casa;
	}
	
	public String getCasaAnt() {
		return casaAnt;
	}
	
	public void setCasaAnt(String casa) {
		this.casaAnt = casa;
	}
	
	public int getHCanvi() {
		return hMomCanvi;
	}
	
	public int getMCanvi() {
		return mMomCanvi;
	}
	
	public int getSCanvi() {
		return sMomCanvi;
	}
	
	public boolean esAngular() {
		if (getNom().equals("Casa 1 (AC)") || getNom().equals("Casa 4") || getCasa().equals("1") || getCasa().equals("4") || 
			getCasa().equals("7") || getCasa().equals("10") || getCasa().equals("12-1") || getCasa().equals("1-2") || 
			getCasa().equals("3-4") || getCasa().equals("4-5") || getCasa().equals("6-7") || getCasa().equals("7-8") || 
			getCasa().equals("9-10") || getCasa().equals("10-11") || getCasaAnt().equals("1") || getCasaAnt().equals("4") || 
			getCasaAnt().equals("7") || getCasaAnt().equals("10") || getCasaAnt().equals("12-1") || getCasaAnt().equals("1-2") |
			getCasaAnt().equals("3-4") || getCasaAnt().equals("4-5") || getCasaAnt().equals("6-7") || 
			getCasaAnt().equals("7-8") || getCasaAnt().equals("9-10") || getCasaAnt().equals("10-11")) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getGrauSimple() {
		return grauSimple;
	}
	
	public void setGrauSimple(int grauSimple) {
		this.grauSimple = grauSimple;
	}
	
	public int getGrauSimpleAnt() {
		return grauSimpleAnt;
	}
	
	public void setGrauSimpleAnt(int grauSimple) {
		this.grauSimpleAnt = grauSimple;
	}
	
	public double getVelocitat() {
		return velocitat;
	}
	
	public void setVelocitat() {
		double velocitat = 0.0;
		
		//els segons els calcula a la classe principal, ja que són iguals per totes les entitats
				
		int recorregut = (gFi * 3600 + mFi * 60 + sFi) - (gIni * 3600 + mIni * 60 + sIni);
		
		//si ha canviat de signe: sumem 30 graus -> 108.000 segons angulars
		if (signeIni != signeFi) {
			recorregut += 108000;
		}

		velocitat = (double) recorregut / (double) partit.getSegons();
		//la velocitat son el nombre de segons angulars que fa en un segon
		this.velocitat = velocitat;
	}
	
	public int getGIni() {
		return gIni;
	}
	
	public int getMIni() {
		return mIni;
	}
	
	public int getSIni() {
		return sIni;
	}
	
	public int getGFi() {
		return gFi;
	}
	
	public void setGFi(int gFi) {
		this.gFi = gFi;
	}
	
	public int getMFi() {
		return mFi;
	}
	
	public void setMFi(int mFi) {
		this.mFi = mFi;
	}
	
	public int getSFi() {
		return sFi;
	}
	
	public void setSFi(int sFi) {
		this.sFi = sFi;
	}

	public String getSigneIni() {
		return signeIni;
	}
	
	public void setSigneIni(String signe) {
		this.signeIni = signe;
	}
	
	public String getSigneFi() {
		return signeFi;
	}
	
	public void setSigneFi(String signe) {
		this.signeFi = signe;
	}

	public String getCasaIni() {
		return casaIni;
	}
	
	public String getCasaIniAnt() {
		return casaIniAnt;
	}
	
	public void setCasaIniAnt(String casaIniAnt) {
		this.casaIniAnt = casaIniAnt;
	}
	
	public String getCasaFi() {
		return casaFi;
	}
	
	public String getCasaFiAnt() {
		return casaFiAnt;
	}
	
	public void setCasaFiAnt(String casaFiAnt) {
		this.casaFiAnt = casaFiAnt;
	}
	
	public int getGIniAnt() {
		return gIniAnt;
	}
	
	public int getGFiAnt() {
		return gFiAnt;
	}
	
	public int getMIniAnt() {
		return mIniAnt;
	}
	
	public int getMFiAnt() {
		return mFiAnt;
	}
	
	public int getSIniAnt() {
		return sIniAnt;
	}
	
	public int getSFiAnt() {
		return sFiAnt;
	}
	
	public String getSigneIniAnt() {
		return signeIniAnt;
	}
	
	public String getSigneFiAnt() {
		return signeFiAnt;
	}
		
	public void setCasaFi(String casaFi) {
		this.casaFi = casaFi;
	}
	
	public void setAntisci() {
		//inicial		
		sIniAnt = 60 - sIni;
		
		if (sIni == 0) {
			sIniAnt = 0;
		}
		
		if (mIni == 0
		&&  sIni == 0) {
			sIniAnt = 0;
		}
		
		mIniAnt = 59 - mIni;
		
		if (sIni == 0) {
			mIniAnt += 1;
		}
		
		if (mIniAnt == 60) {
			mIniAnt = 0;
		}
				
		gIniAnt = 29 - gIni;

		if (mIni == 0 && sIni == 0) {
			gIniAnt += 1;
		}
		
		signeIniAnt = antiscis.get(signeIni);
		
		//final
		sFiAnt = 60 - sFi;
		
		if (sFi == 0) {
			sFiAnt = 0;
		}
		
		if (mFi == 0
		&&  sFi == 0) {
			sFiAnt = 0;
		}
		
		mFiAnt = 59 - mFi;
		
		if (sFi == 0) {
			mFiAnt += 1;
		}
		
		if (mFiAnt == 60) {
			mFiAnt = 0;
		}
				
		gFiAnt = 29 - gFi;

		if (mFi == 0 && sFi == 0) {
			gFiAnt += 1;
		}
		
		signeFiAnt = antiscis.get(signeFi);		
		
	}
	
	public int canviAngle() {
		double posicio = (double) getGIni() + (double) getMIni() / 60 + (double) getSIni() / 3600;
		int s = 0;
		for (int i = 1; i <= partit.getSegons(); i++) {
			posicio += getVelocitat() / 3600;
			if (posicio > 30) {
				s = i;
				break;
			}
		}
		return s;
	}
	
	protected String ompleRegent(int i) {
		// informa el signe sota el qual es troba un angle per un segon determinat quan aquest canvia
		String signe = "";
		boolean trobat = false;
		Iterator<String> iterador = regents.keySet().iterator();
		// 1. trobem el nou signe que s'haurà de guardar
		// 1a. guardem el moment al qual canvia de signe
		if (mom[0] == 0) {
			mom[0] = i;
			//System.out.println ("mom[0]: " + mom[0]);
		}
		else
		{
			mom[1] = i;
			//System.out.println ("mom[1]: " + mom[1]);
		}
		// 1b. busquem el nou signe
		if (getSigne(i-1).equals("Peixos")) {
			signe = "\u00c0ries";
			//System.out.println ("guardat signe \u00c0ries.");
		}
		else
		{
			while (iterador.hasNext() && trobat == false) {
				signe = iterador.next();
				if (getSigne(i-1).equals(signe)) {
					signe = iterador.next();
					//System.out.println ("guardat signe " + signe);
					trobat = true;
				}
			}
		}
		return signe;
	}
	
	protected void setSegons() {
		signe = new String[partit.getSegons()];
		for (int x = 0; x < partit.getSegons(); x++) {
				this.signe[x] = "";
		}
		angle = new String[partit.getSegons()];
		for (int x = 0; x < partit.getSegons(); x++) {
				this.angle[x] = "";
		}
	}
	
	protected void moure(int temps) {

		double p = (double) gIni + (double) mIni / 60 + (double) sIni / 3600;
		p += temps * velocitat / 3600;
		
		int g = (int)Math.floor(p);
		int m = (int)Math.floor((p - g) * 60);
		int s = (int)Math.floor((p - g - m / 60) * 60);
		
		if (g > 29) {
			g -= 30;
			signeIni = signeFi;
		}
		
		this.gIni = g;
		this.mIni = m;
		this.sIni = s;
		
	}
}