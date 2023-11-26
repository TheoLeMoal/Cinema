package entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Batiment")
public class Batiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_batiment")
    private int idBatiment;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "batiment")
    private List<Adresse> adresses = new ArrayList<>();
    // Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Batiment() {
		adresses = new ArrayList<>();
	}

	public Batiment(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the idBatiment
	 */
	public int getIdBatiment() {
		return idBatiment;
	}

	/**
	 * @param idBatiment the idBatiment to set
	 */
	public void setIdBatiment(int idBatiment) {
		this.idBatiment = idBatiment;
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

	@Override
	public String toString() {
		return "Batiment [idBatiment=" + idBatiment + ", nom=" + nom + "]";
	}

	/**
	 * @param listBatiment
	 * @param nom
	 * @return
	 */
	public static Batiment getBatimentByNom(List<Batiment> listBatiment, String nom) {
		for(Batiment batiment : listBatiment) {
			if(batiment.getNom().equals(nom)) {
				return batiment;
			}
		}
		return null;
	}
}