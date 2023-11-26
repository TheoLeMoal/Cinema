package entite;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Adresse")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresse")
    private int idAdresse;

    @ManyToOne
    @JoinColumn(name = "id_ville")
    private Ville ville;

    @ManyToOne
    @JoinColumn(name = "id_batiment")
    private Batiment batiment;

    @ManyToOne
    @JoinColumn(name = "id_quartier")
    private Quartier quartier;

    @ManyToOne
    @JoinColumn(name = "id_departement")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "id_pays")
    private Pays pays;

    // Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Adresse() {}

	/**
	 * @param ville
	 * @param batiment
	 * @param quartier
	 * @param departement
	 * @param pays
	 */
	public Adresse(Ville ville, Batiment batiment, Quartier quartier, Departement departement, Pays pays) {
		this.ville = ville;
		this.batiment = batiment;
		this.quartier = quartier;
		this.departement = departement;
		this.pays = pays;
	}

	/**
	 * @param ville
	 * @param quartier
	 * @param departement
	 * @param pays
	 */
	public Adresse(Ville ville, Quartier quartier, Departement departement, Pays pays) {
		this.ville = ville;
		this.quartier = quartier;
		this.departement = departement;
		this.pays = pays;
	}
	
	/**
	 * @param ville
	 * @param departement
	 * @param pays
	 */
	public Adresse(Ville ville, Departement departement, Pays pays) {
		this.ville = ville;
		this.departement = departement;
		this.pays = pays;
	}

	/**
	 * @param pays
	 */
	public Adresse(Pays pays) {
		this.pays = pays;
	}

	/**
	 * @param ville
	 * @param pays
	 */
	public Adresse(Ville ville, Pays pays) {
		this.ville = ville;
		this.pays = pays;
	}

	/**
	 * @return the idAdresse
	 */
	public int getIdAdresse() {
		return idAdresse;
	}

	/**
	 * @param idAdresse the idAdresse to set
	 */
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	/**
	 * @return the ville
	 */
	public Ville getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(Ville ville) {
		this.ville = ville;
	}

	/**
	 * @return the batiment
	 */
	public Batiment getBatiment() {
		return batiment;
	}

	/**
	 * @param batiment the batiment to set
	 */
	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}

	/**
	 * @return the quartier
	 */
	public Quartier getQuartier() {
		return quartier;
	}

	/**
	 * @param quartier the quartier to set
	 */
	public void setQuartier(Quartier quartier) {
		this.quartier = quartier;
	}

	/**
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/**
	 * @param dep the departement to set
	 */
	public void setDepartement(Departement dep) {
		this.departement = dep;
	}

	/**
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}

	/**
	 * @param pays the pays to set
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
	}
	
	public static Adresse getSpecifiqueLen5Adresse(List<Adresse> arrayAdresses, Pays paysAdresse, Ville villeAdresse, Departement depAdresse,
			Quartier quartierAdresse, Batiment batimentAdresse) {
		for (Adresse adresse : arrayAdresses) {
			if ((paysAdresse == null && adresse.getPays() == null || paysAdresse != null && paysAdresse.equals(adresse.getPays())) &&
			    (villeAdresse == null && adresse.getVille() == null || villeAdresse != null && villeAdresse.equals(adresse.getVille())) &&
			    (depAdresse == null && adresse.getDepartement() == null || depAdresse != null && depAdresse.equals(adresse.getDepartement())) &&
			    (quartierAdresse == null && adresse.getQuartier() == null || quartierAdresse != null && quartierAdresse.equals(adresse.getQuartier())) &&
			    (batimentAdresse == null && adresse.getBatiment() == null || batimentAdresse != null && batimentAdresse.equals(adresse.getBatiment()))) {
			    return adresse;
			}
		}
		return null;
	}
	
	public static Adresse getSpecifiqueLen4Adresse(List<Adresse> arrayAdresses, Pays paysAdresse, Ville villeAdresse, Departement depAdresse,
			Quartier quartierAdresse) {
		for (Adresse adresse : arrayAdresses) {
			if ((paysAdresse == null && adresse.getPays() == null || paysAdresse != null && paysAdresse.equals(adresse.getPays())) &&
			    (villeAdresse == null && adresse.getVille() == null || villeAdresse != null && villeAdresse.equals(adresse.getVille())) &&
			    (depAdresse == null && adresse.getDepartement() == null || depAdresse != null && depAdresse.equals(adresse.getDepartement())) &&
			    (quartierAdresse == null && adresse.getQuartier() == null || quartierAdresse != null && quartierAdresse.equals(adresse.getQuartier())) &&
			    (adresse.getBatiment() == null)) {
			    return adresse;
			}
		}
		return null;
	}
	
	public static Adresse getSpecifiqueLen3Adresse(List<Adresse> arrayAdresses, Pays paysAdresse, Ville villeAdresse, Departement depAdresse) {
		for (Adresse adresse : arrayAdresses) {
			if ((paysAdresse == null && adresse.getPays() == null || paysAdresse != null && paysAdresse.equals(adresse.getPays())) &&
			    (villeAdresse == null && adresse.getVille() == null || villeAdresse != null && villeAdresse.equals(adresse.getVille())) &&
			    (depAdresse == null && adresse.getDepartement() == null || depAdresse != null && depAdresse.equals(adresse.getDepartement())) &&
			    (adresse.getQuartier() == null)  &&
			    (adresse.getBatiment() == null)) {
			    return adresse;
			}
		}
		return null;
	}

	public static Adresse getSpecifiqueLen2Adresse(List<Adresse> arrayAdresses, Pays paysAdresse, Ville villeAdresse) {
		for (Adresse adresse : arrayAdresses) {
			if ((paysAdresse == null && adresse.getPays() == null || paysAdresse != null && paysAdresse.equals(adresse.getPays())) &&
			    (villeAdresse == null && adresse.getVille() == null || villeAdresse != null && villeAdresse.equals(adresse.getVille())) &&
			    (adresse.getDepartement() == null) &&
			    (adresse.getQuartier() == null) &&
			    (adresse.getBatiment() == null)) {
			    return adresse;
			}
		}
		return null;
	}

	public static Adresse getSpecifiqueLen1Adresse(List<Adresse> arrayAdresses, Pays paysAdresse) {
		for (Adresse adresse : arrayAdresses) {
			if ((paysAdresse == null && adresse.getPays() == null || paysAdresse != null && paysAdresse.equals(adresse.getPays())) &&
				    (adresse.getVille() == null) &&
				    (adresse.getDepartement() == null) &&
				    (adresse.getQuartier() == null) &&
				    (adresse.getBatiment() == null)) {
				    return adresse;
				}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Adresse [idAdresse=" + idAdresse + ", ville=" + ville + ", batiment=" + batiment + ", quartier="
				+ quartier + ", departement=" + departement + ", pays=" + pays + "]";
	}


}