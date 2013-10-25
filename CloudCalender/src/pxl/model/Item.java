package pxl.model;

public class Item {
	
	private String naam;
	private String datum;
	private String beschrijving;
	private int start;
	private int einde;
	
	public Item(String naam, String datum, String beschrijving, int start, int einde){
		this.naam = naam;
		this.datum = datum;
		this.beschrijving =beschrijving;
		this.start = start;
		this.einde = einde;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEinde() {
		return einde;
	}

	public void setEinde(int einde) {
		this.einde = einde;
	}
	
	

}
