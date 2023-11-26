package entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private int idGenre;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    @ManyToMany(mappedBy = "genres")
    List<Film> films = new ArrayList<>();
    
    // Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Genre() {}

	public Genre(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the idGenre
	 */
	public int getIdGenre() {
		return idGenre;
	}

	/**
	 * @param idGenre the idGenre to set
	 */
	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
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

	public static Genre getGenreByNom(List<Genre> listeGenreMAJ, String nom) {
		for(Genre genres : listeGenreMAJ) {
			if(genres.getNom().equals(nom)) {
				return genres;
			}
		}
		return null;
	}
}