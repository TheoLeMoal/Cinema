package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entite.Acteur;
import entite.Adresse;
import entite.Film;

public class ActeurDao extends Dao<Acteur>{

	public ActeurDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Acteur findByName(String acteurIdentite) {
		try {
			TypedQuery query = entityManager.createQuery(
					"SELECT a FROM Acteur a WHERE a.identite = :identite",
					Acteur.class);
			query.setParameter("identite", acteurIdentite);
			return (Acteur) query.getSingleResult();
		} catch (NoResultException e) {
			return null; 
		}
	}
	
	public Acteur findByIMDB(String acteurIMBD) {
		try {
			TypedQuery query = entityManager.createQuery(
					"SELECT a FROM Acteur a WHERE a.idImdb = :idImdb",
					Acteur.class);
			query.setParameter("idImdb", acteurIMBD);
			return (Acteur) query.getSingleResult();
		} catch (NoResultException e) {
			return null; 
		}
	}
	
	public Acteur ifNotExistCreate(String acteurIdentite, String idIMDB, LocalDate dateNaissance, Adresse adresse) {
		Acteur acteur = findByName(acteurIdentite);
		if (acteur == null) {
			acteur = new Acteur();
			acteur.setIdentite(acteurIdentite);
			acteur.setDdn(dateNaissance);
			acteur.setIdImdb(idIMDB);
			create(acteur);
		}
		return acteur;
	}

	public Acteur getActeurByIdentite(String acteurNom) {
		try {
			TypedQuery query = entityManager.createQuery(
					"SELECT a FROM Acteur a WHERE a.identite = :identite",
					Acteur.class);
			query.setParameter("identite", acteurNom);
			return (Acteur) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Acteur> getActeurByFilm(Film film) {
		try {
			TypedQuery<Acteur> query = entityManager.createQuery("SELECT a FROM Acteur a JOIN a.films f WHERE f = :film", Acteur.class);
			query.setParameter("film", film);
			return (List<Acteur>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Acteur> getActeurCommunByFilm(Film film1, Film film2) {
		try {
			TypedQuery<Acteur> query = entityManager.createQuery("SELECT r1.acteur From Role r1 JOIN Role r2 ON r1.acteur = r2.acteur WHERE r1.film = :film1 AND r2.film = :film2", Acteur.class);
			query.setParameter("film1", film1);
			query.setParameter("film2", film2);
			return (List<Acteur>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
}
