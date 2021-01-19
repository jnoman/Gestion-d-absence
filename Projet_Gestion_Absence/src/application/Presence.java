package application;


public class Presence {
   private int id;
   private int idApprenat;
   private int idFormateur;
   private Boolean absence;
   private String dateAbsence;
   private int dureAbsence;
   
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdApprenat() {
		return idApprenat;
	}
	public void setIdApprenat(int idApprenat) {
		this.idApprenat = idApprenat;
	}
	public int getIdFormateur() {
		return idFormateur;
	}
	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}
	public Boolean getAbsence() {
		return absence;
	}
	public void setAbsence(Boolean absence) {
		this.absence = absence;
	}
	public String getDateAbsence() {
		return dateAbsence;
	}
	public void setDateAbsence(String dateAbsence) {
		this.dateAbsence = dateAbsence;
	}
	public int getDureAbsence() {
		return dureAbsence;
	}
	public void setDureAbsence(int dureAbsence) {
		this.dureAbsence = dureAbsence;
	}
	
	public Presence(int id, int idApprenat, int idFormateur, Boolean absence, String dateAbsence, int dureAbsence) {
		super();
		this.id = id;
		this.idApprenat = idApprenat;
		this.idFormateur = idFormateur;
		this.absence = absence;
		this.dateAbsence = dateAbsence;
		this.dureAbsence = dureAbsence;
	}

   
}