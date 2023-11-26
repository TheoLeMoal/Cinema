package entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Departement")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dep")
    private int idDep;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "departement")
    private List<Adresse> adresses = new ArrayList<>();
    // Constructors, getters, setters
    
	/**
	 * Constructeur
	 */
	public Departement() {
		adresses = new ArrayList<>();
	}

	public Departement(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the idDep
	 */
	public int getIdDep() {
		return idDep;
	}

	/**
	 * @param idDep the idDep to set
	 */
	public void setIdDep(int idDep) {
		this.idDep = idDep;
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
		return "Departement [idDep=" + idDep + ", nom=" + nom + "]";
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
	 * @param listDepartement
	 * @param nom
	 * @return
	 */
	public static Departement getDepartementByNom(List<Departement> listDepartement, String nom) {
		for(Departement departement : listDepartement) {
			if(departement.getNom().equals(nom)) {
				return departement;
			}
		}
		return null;
	}

}