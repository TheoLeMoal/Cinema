package entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_film")
    private int idFilm;

    @Column(name = "id_imdb", nullable = false, unique = true)
    private String idImdb;

    @Column(name = "nom")
    private String nom;

    @Column(name = "annee")
    private String annee;

    @Column(name = "note", precision = 15, scale = 1)
    private double note;

    @Column(name = "url", nullable = false, unique = true)
    private String url;

    @Column(name = "resume", length = 500)
    private String resume;

    @ManyToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "id_pays")
    private Pays pays;

    @ManyToOne
    @JoinColumn(name = "id_langue")
    private Langue langue;
    
    @ManyToMany
    @JoinTable(
    		name = "appartenir",
    		joinColumns = @JoinColumn(name = "id_film"),
    		inverseJoinColumns = @JoinColumn(name = "id_genre")
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany
    @JoinTable(
    		name = "jouer",
    		joinColumns = @JoinColumn(name = "id_film"),
    		inverseJoinColumns = @JoinColumn(name = "id_acteur")
    )
    private List<Acteur> acteurs = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
    		name = "diriger",
    		joinColumns = @JoinColumn(name = "id_film"),
    		inverseJoinColumns = @JoinColumn(name = "id_realisateur")
    )
    private List<Realisateur> realisateurs = new ArrayList<>();
    
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private Set<Role> roles;
    
    // Constructors, getters, setters
    
    /**
     * Constructeur
     */
	public Film() {
		this.realisateurs = new ArrayList<>();
	}

	public Film(String idImdb, String nom, String annee, double note, String url, Adresse lieuTournage,
			String resume, Langue langue, List<Genre> genres, Pays pays) {
		this.idImdb = idImdb;
		this.nom = nom;
		this.annee = annee;
		this.note = note;
		this.url = url;
		this.adresse = lieuTournage;
		this.resume = resume;
		this.langue = langue;
		this.genres = genres;
		this.pays = pays;
		this.realisateurs = new ArrayList<>();
		this.acteurs = new ArrayList<>();
	}
	
	/**
	 * @return the idFilm
	 */
	public int getIdFilm() {
		return idFilm;
	}

	/**
	 * @param idFilm the idFilm to set
	 */
	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
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
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}

	/**
	 * @return the note
	 */
	public double getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(double note) {
		this.note = note;
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
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * @param resume the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
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

	/**
	 * @return the langue
	 */
	public Langue getLangue() {
		return langue;
	}

	/**
	 * @param langue the langue to set
	 */
	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * @return the films
	 */
	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	/**
	 * @param films the films to set
	 */
	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * @return the acteurs
	 */
	public List<Acteur> getActeurs() {
		return acteurs;
	}

	/**
	 * @param acteurs the acteurs to set
	 */
	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}

	@Override
	public String toString() {
		return "Film [idFilm=" + idFilm + ", idImdb=" + idImdb + ", nom=" + nom + ", annee=" + annee + ", note=" + note
				+ ", url=" + url + ", resume=" + resume + ", adresse=" + adresse + ", pays=" + pays + ", langue="
				+ langue + ", genres=" + genres + ", acteurs=" + acteurs + ", realisateurs=" + realisateurs + ", roles="
				+ roles + "]";
	}

	/**
	 * @param listFilm
	 * @param imdb
	 * @return
	 */
	public static Film getFilmByIMDB(List<Film> listFilm, String imdb) {
		for(Film film : listFilm) {
	        if (film.getIdImdb().equals(imdb)) {
	            return film;
	        }
		}
		return null;
	}
}
