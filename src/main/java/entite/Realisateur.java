package entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Realisateur")
public class Realisateur extends Personne{

    @ManyToMany(mappedBy = "realisateurs")
    List<Film> films = new ArrayList<>();
    
    @Column(name = "id_imdb", nullable = false, unique = true)
    private String idImdb;
    
    // Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Realisateur() {
		super();
		this.films = new ArrayList<>();
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
	 * @return the idImdb
	 */
	public String getIdImdb() {
		return idImdb;
	}

	/**
	 * @param idImdb the idImdb to set
	 */
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}
	
	/**
	 * @param listRealisateur
	 * @param imdb
	 * @return
	 */
	public static Realisateur getRealisateurByIMDB(List<Realisateur> listRealisateur, String imdb) {
		for(Realisateur realisateur : listRealisateur) {
			if(realisateur.getIdImdb().equals(imdb)) {
				return realisateur;
			}
		}
		return null;
	}
}