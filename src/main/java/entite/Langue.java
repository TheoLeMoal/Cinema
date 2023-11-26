package entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Langue")
public class Langue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_langue")
    private int idLangue;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;
    
    @OneToMany(mappedBy = "langue")
    private List<Film> films = new ArrayList<>();
    
    // Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Langue() {}

	public Langue(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the idLangue
	 */
	public int getIdLangue() {
		return idLangue;
	}

	/**
	 * @param idLangue the idLangue to set
	 */
	public void setIdLangue(int idLangue) {
		this.idLangue = idLangue;
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
	 * @return the films
	 */
	public List<Film> getFilms() {
		return films;
	}

	/**
	 * @param films the films to set
	 */
	public void setFilms(List<Film> films) {
		this.films = films;
	}
	
	/**
	 * @param listLangue
	 * @param nom
	 * @return
	 */
	public static Langue getLangueByNom(List<Langue> listLangue, String nom) {
		for(Langue langues : listLangue) {
			if(langues.getNom().equals(nom)) {
				return langues;
			}
		}
		return null;
	}
}