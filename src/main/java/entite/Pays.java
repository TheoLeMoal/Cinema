package entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Pays")
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pays")
    private int idPays;

    @Column(name = "nom")
    private String nom;

    @Column(name = "url")
    private String url;
    
    @OneToMany(mappedBy = "pays")
    private List<Adresse> adresses = new ArrayList<>();
    // Constructors, getters, setters
    
    /**
     * Constructeur
     */
	public Pays() {
		adresses = new ArrayList<>();
	}

	public Pays(String nom, String url) {
		this.nom = nom;
		this.url = url;
	}

	public Pays(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the idPays
	 */
	public int getIdPays() {
		return idPays;
	}

	/**
	 * @param idPays the idPays to set
	 */
	public void setIdPays(int idPays) {
		this.idPays = idPays;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
		return "Pays [idPays=" + idPays + ", nom=" + nom + ", url=" + url + "]";
	}

	public static Pays getPaysByNom(List<Pays> listePays, String nom) {
		if (nom != null && !nom.isEmpty()) {
		    for (Pays pays : listePays) {
		        if (pays != null && nom.equals(pays.getNom())) {
		            return pays;
		        }
		    }
		}
		return null;
	}
	

}