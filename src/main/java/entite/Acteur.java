package entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Acteur")
public class Acteur extends Personne{

	@OneToMany(mappedBy = "acteur")
	private List<Role> listeRole;
	
    @Column(name = "id_imdb", nullable = false, unique = true)
    private String idImdb;
	
    @ManyToMany(mappedBy = "acteurs")
    List<Film> films = new ArrayList<>();
    
	/**
	 * Constructeur
	 */
	public Acteur() {
		super();
		this.films = new ArrayList<>();
	}

	/**
	 * @return the listeRole
	 */
	public List<Role> getListeRole() {
		return listeRole;
	}

	/**
	 * @param listeRole the listeRole to set
	 */
	public void setListeRole(List<Role> listeRole) {
		this.listeRole = listeRole;
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

	@Override
	public String toString() {
		return "Acteur [idImdb=" + idImdb + "identite=" + getIdentite() + "]";
	}

	/**
	 * @param listActeur
	 * @param imdb
	 * @return
	 */
	public static Acteur getActeurByIMDB(List<Acteur> listActeur, String imdb) {
		for(Acteur acteur : listActeur) {
	        if (acteur.getIdImdb().equals(imdb)) {
	        	return acteur;
	        }
		}
		return null;
	}
	
	
}