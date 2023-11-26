package entite;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Personne")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personne")
    private int idPersonne;

    @Column(name = "identite", nullable = false)
    private String identite;
    
    @Column(name = "url")
    private String url;

    @Column(name = "ddn")
    private LocalDate ddn;
    
    @ManyToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    // Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Personne() {
	}

	/**
	 * @return the idPersonne
	 */
	public int getIdPersonne() {
		return idPersonne;
	}

	/**
	 * @param idPersonne the idPersonne to set
	 */
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	/**
	 * @return the identite
	 */
	public String getIdentite() {
		return identite;
	}

	/**
	 * @param identite the identite to set
	 */
	public void setIdentite(String identite) {
		this.identite = identite;
	}

	/**
	 * @return the ddn
	 */
	public LocalDate getDdn() {
		return ddn;
	}

	/**
	 * @param dateNaissance the ddn to set
	 */
	public void setDdn(LocalDate dateNaissance) {
		this.ddn = dateNaissance;
	}

	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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
}
