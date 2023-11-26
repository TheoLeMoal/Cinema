package entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Ville")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ville")
    private int idVille;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "ville")
    private List<Adresse> adresses = new ArrayList<>();
    
	// Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Ville() {
		this.adresses = new ArrayList<>();
	}

	public Ville(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the idVille
	 */
	public int getIdVille() {
		return idVille;
	}

	/**
	 * @param idVille the idVille to set
	 */
	public void setIdVille(int idVille) {
		this.idVille = idVille;
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
		return "Ville [idVille=" + idVille + ", nom=" + nom + "]";
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
	 * @param listVille
	 * @param nom
	 * @return
	 */
	public static Ville getVilleByNom(List<Ville> listVille, String nom) {
		for(Ville ville : listVille) {
			if(ville.getNom().equals(nom)) {
				return ville;
			}
		}
		return null;
	}

	
}