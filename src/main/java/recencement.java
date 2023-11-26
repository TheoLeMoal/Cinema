import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entite.Acteur;
import entite.Adresse;
import entite.Batiment;
import entite.Departement;
import entite.Film;
import entite.Genre;
import entite.Langue;
import entite.Pays;
import entite.Personne;
import entite.Quartier;
import entite.Role;
import entite.Ville;
import service.LectureCsv;

public class recencement {

	public static void main(String[] args) {
		/**
		 * Entities manager
		 */
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cinemas-recencement");
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		/**
		 * Lecture des CSV
		 */
		LectureCsv.parsePays();
		LectureCsv.parseLangue();
		LectureCsv.parseGenre();
		LectureCsv.parseAdresse();
		LectureCsv.parseFilm();
		LectureCsv.parseRealisateur();
		LectureCsv.parseFilmRealisateur();
		LectureCsv.parseActeur();
		LectureCsv.parseRole();
		LectureCsv.parseCastingPrincipal();
		
		/**
		 * Mise en base de données
		 */
		transaction.begin();
		
		/**
		 * Ajout des langues
		 */
		for (Langue langue : LectureCsv.arrayLangues) {
			em.persist(langue);
		}
		
		/**
		 * Ajout des genres
		 */
		for (Genre genre : LectureCsv.arrayGenres) {
			em.persist(genre);
		}
		
		/**
		 * Ajout des pays
		 */
		for (Pays pays : LectureCsv.arrayPays) {
			em.persist(pays);
		}
		
		/**
		 * Ajout des villes
		 */
		for (Ville ville : LectureCsv.arrayVilles) {
			em.persist(ville);
		}
		
		/**
		 * Ajout des departements
		 */
		for (Departement departement : LectureCsv.arrayDepartements) {
			em.persist(departement);
		}
		
		/**
		 * Ajout des quartiers
		 */
		for (Quartier quartier : LectureCsv.arrayQuartiers) {
			em.persist(quartier);
		}
		
		/**
		 * Ajout des batiments
		 */
		for (Batiment batiment : LectureCsv.arrayBatiments) {
			em.persist(batiment);
		}
		
		/**
		 * Ajout des adresses
		 */
		for (Adresse adresse : LectureCsv.arrayAdresses) {
			em.persist(adresse);
		}
		
		/*
		 * Ajout des films
		 */
		for (Film film : LectureCsv.arrayFilms) {
			em.persist(film);
		}
		
		/**
		 * Ajout des personnes
		 */
		for (Personne personne : LectureCsv.arrayRealisateurs) {
			em.persist(personne);
		}
		
		/**
		 * Ajout des acteurs
		 */
		for (Acteur acteur : LectureCsv.arrayActeurs) {
			em.persist(acteur);
		}
		
		/**
		 * Ajout des roles
		 */
		for (Role role : LectureCsv.arrayRoles) {
			em.persist(role);
		}
		
		transaction.commit();
	}

}
