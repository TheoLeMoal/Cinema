import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.ActeurDao;
import dao.FilmDao;
import entite.Acteur;
import entite.Film;

public class menu {

	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinemas");
        EntityManager em = emf.createEntityManager();
		/**
		 * Dao
		 */		
		ActeurDao acteurDao = new ActeurDao(em);
		FilmDao filmDao = new FilmDao(em);
		
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Affichage de la filmographie d'un acteur donné");
            System.out.println("2. Affichage du casting d'un film donné");
            System.out.println("3. Affichage des films sortis entre 2 années données");
            System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés");
            System.out.println("5. Affichage des acteurs communs à 2 films donnés");
            System.out.println("6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
            System.out.println("7. Fin de l'application");

            System.out.print("Choisissez une option (1-7): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Appel à la fonction pour afficher la filmographie d'un acteur donné
                	Scanner scanner2 = new Scanner(System.in);
                	String acteurNom = scanner2.nextLine();
                	Acteur acteur = acteurDao.getActeurByIdentite(acteurNom);
                	List<Film> Listefilms = filmDao.getFilmByActeur(acteur);
                	for (Film film : Listefilms) {
						System.out.println(film);
					}
                    break;
                case 2:
                    // Appel à la fonction pour afficher le casting d'un film donné
    				String nomFilm = scanner.nextLine();
    				
    				Film film = filmDao.getFilmByName(nomFilm);

    				List<Acteur> listActeurByFilm = acteurDao.getActeurByFilm(film);

    				for (Acteur acteurs : listActeurByFilm) {
    					System.out.println(acteurs.getIdentite());
    				}
                    break;
                case 3:
                    // Appel à la fonction pour afficher les films sortis entre 2 années données
    				System.out.print("Année de début : ");
    				String anneDebut = scanner.nextLine();
    				
    				System.out.print("Année de fin : ");
    				String anneFin = scanner.nextLine();

    				List<Film> filmByAnnee = filmDao.getFilmByYear(anneDebut, anneFin);

    				for (Film films : filmByAnnee) {
    					System.out.println(films.getNom());
    				}
                    break;
                case 4:
                    // Appel à la fonction pour afficher les films communs à 2 acteurs/actrices donnés
    				System.out.print("Nom de l'acteur 1 : ");
    				String nomActeur1 = scanner.nextLine();
    				
    				System.out.print("Nom de l'acteur 2 : ");
    				String nomActeur2 = scanner.nextLine();
    				
    				Acteur acteur1 = acteurDao.getActeurByIdentite(nomActeur1);
    				Acteur acteur2 = acteurDao.getActeurByIdentite(nomActeur2);

    				List<Film> listFilmByActeurCommun = filmDao.getFilmByActeurCommun(acteur1, acteur2);

    				for (Film films : listFilmByActeurCommun) {
    					System.out.println(films.getNom());
    				}
                	break;
                case 5:
                    // Appel à la fonction pour afficher les acteurs communs à 2 films donnés
    				System.out.print("Nom du film 1 : ");
    				String nomFilm1 = scanner.nextLine();
    				
    				System.out.print("Nom du film 2 : ");
    				String nomFilm2 = scanner.nextLine();
    				
    				Film film1 = filmDao.getFilmByName(nomFilm1);
    				Film film2 = filmDao.getFilmByName(nomFilm2);

    				List<Acteur> listActeurCommunByFilm = acteurDao.getActeurCommunByFilm(film1, film2);

    				for (Acteur acteurs : listActeurCommunByFilm) {
    					System.out.println(acteurs.getIdentite());
    				}
                    break;
                case 6:
                    // Appel à la fonction pour afficher les films sortis entre 2 années données avec un acteur donné au casting
    				System.out.print("Année de début : ");
    				int anneDebut1 = scanner.nextInt();
    				
    				System.out.print("Année de fin : ");
    				int anneFin1 = scanner.nextInt();
    				
    				Scanner scannerFilmByActeur = new Scanner(System.in);
    				System.out.print("Acteur : ");
    				String acteurNom1 = scannerFilmByActeur.nextLine();
    				
    				Acteur acteur3 = acteurDao.getActeurByIdentite(acteurNom1);

    				List<Film> listFilms = filmDao.getFilmByAnneeActeur(anneDebut1, anneFin1, acteur3);

    				for (Film films : listFilms) {
    					System.out.println(films.getNom());
    				}
                	break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez saisir un numéro entre 1 et 7.");
            }
        }
        System.out.println("Fin de l'application.");
        scanner.close();
	}

}
