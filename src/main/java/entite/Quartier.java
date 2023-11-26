package entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Quartier")
public class Quartier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quartier")
    private int idQuartier;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "quartier")
    private List<Adresse> adresses = new ArrayList<>();
	// Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Quartier() {
		adresses = new ArrayList<>();
	}

	public Quartier(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the idQuartier
	 */
	public int getIdQuartier() {
		return idQuartier;
	}

	/**
	 * @param idQuartier the idQuartier to set
	 */
	public void setIdQuartier(int idQuartier) {
		this.idQuartier = idQuartier;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Quartier [idQuartier=" + idQuartier + ", nom=" + nom + "]";
	}

	/**
	 * @return the adresses
	 */
	public List<Adresse> getAdresses() {
		return adresses;
	}

	/**
	 * @param adresses the adresses to set
	 */
	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	/**
	 * @param listQuartier
	 * @param nom
	 * @return
	 */
	public static Quartier getQuartierByNom(List<Quartier> listQuartier, String nom) {
		for(Quartier quartier : listQuartier) {
			if(quartier.getNom().equals(nom)) {
				return quartier;
			}
		}
		return null;
	}

	
}
