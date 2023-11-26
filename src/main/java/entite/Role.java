package entite;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int idRole;

    @Column(name = "personnage")
    private String personnage;

    @ManyToOne
    @JoinColumn(name = "id_acteur")
    private Acteur acteur;

    @ManyToOne
    @JoinColumn(name = "id_film")
    private Film film;

    // Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Role() {}
	
	public Role(String personnage, Acteur acteur, Film film) {
		this.personnage = personnage;
		this.acteur = acteur;
		this.film = film;
	}

	/**
	 * @return the idRole
	 */
	public int getIdRole() {
		return idRole;
	}

	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	/**
	 * @return the personnage
	 */
	public String getPersonnage() {
		return personnage;
	}

	/**
	 * @param personnage the personnage to set
	 */
	public void setPersonnage(String personnage) {
		this.personnage = personnage;
	}

	/**
	 * @return the acteur
	 */
	public Acteur getActeur() {
		return acteur;
	}

	/**
	 * @param acteur the acteur to set
	 */
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

	/**
	 * @return the film
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * @param film the film to set
	 */
	public void setFilm(Film film) {
		this.film = film;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", personnage=" + personnage + "]";
	}
	
}