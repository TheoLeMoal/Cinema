package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entite.Acteur;
import entite.Adresse;
import entite.Film;

public class FilmDao extends Dao<Film>{

	public FilmDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Film findByName(String filmNom) {
		try {
			Query query = entityManager.createQuery(
					"SELECT f FROM Film f WHERE f.nom = :nom",
					Film.class);
			query.setParameter("nom", filmNom);
			return (Film) query.getSingleResult();
		} catch (NoResultException e) {
			return null; 
		}
	}
	
	public Film findByIMDB(String filmIMBD) {
		try {
			Query query = entityManager.createQuery(
					"SELECT f FROM Film f WHERE f.idImdb = :idImdb",
					Film.class);
			query.setParameter("idImdb", filmIMBD);
			return (Film) query.getSingleResult();
		} catch (NoResultException e) {
			return null; 
		}
	}
	
	public Film ifNotExistCreate(String filmName) {
		Film film = findByName(filmName);
		if (film == null) {
			film = new Film();
			film.setNom(filmName);
		}
		return film;
	}
	
	public void createOrUpdate(Film film) {
		Film filmObjet = findByName(film.getNom());
		if (filmObjet == null) {
			create(film);
		}else {
			update(film);
		}
	}
	
	public List<Film> getFilmByActeur(Acteur acteur) {
		try {
			System.out.println(acteur);
		    TypedQuery<Film> query = entityManager.createQuery("SELECT f FROM Film f JOIN f.acteurs a WHERE a = :acteur", Film.class);
		    query.setParameter("acteur", acteur);
		    List<Film> listeFilms = query.getResultList();
		    return listeFilms;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	public Film getFilmByName(String nomFilm) {
		try {
			TypedQuery<Film> query = entityManager.createQuery("SELECT f FROM Film f WHERE f.nom = :nom", Film.class);
			query.setParameter("nom", nomFilm);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Film> getFilmByYear(String anneDebut, String anneFin) {
		try {
			TypedQuery<Film> query = entityManager.createQuery("SELECT f FROM Film f WHERE f.annee BETWEEN :debut AND :fin", Film.class);
			query.setParameter("debut", anneDebut);
			query.setParameter("fin", anneFin);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Film> getFilmByActeurCommun(Acteur acteur1, Acteur acteur2) {
		try {
			TypedQuery<Film> query = entityManager.createQuery("SELECT r1.film FROM Role r1 JOIN Role r2 ON r1.film = r2.film WHERE r1.acteur = :acteur1 AND r2.acteur = :acteur2", Film.class);
			query.setParameter("acteur1", acteur1);
			query.setParameter("acteur2", acteur2);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Film> getFilmByAnneeActeur(int anneDebut, int anneFin, Acteur acteur) {
		try {
			System.out.println(acteur);
			TypedQuery<Film> query = entityManager.createQuery("SELECT DISTINCT f FROM Film f JOIN f.acteurs a WHERE f.annee BETWEEN :anneeDebut AND :anneeFin AND a.idImdb = :acteur", Film.class);
			query.setParameter("anneeDebut", anneDebut);
			query.setParameter("anneeFin", anneFin);
			query.setParameter("acteur", acteur.getIdImdb());
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
}
