package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import entite.Acteur;
import entite.Film;
import entite.Genre;
import entite.Langue;
import entite.Adresse;
import entite.Batiment;
import entite.Departement;
import entite.Pays;
import entite.Personne;
import entite.Quartier;
import entite.Realisateur;
import entite.Role;
import entite.Ville;

public class LectureCsv {
	public static List<Adresse> arrayAdresses = new ArrayList<>();
	public static List<Genre> arrayGenres = new ArrayList<>();
	public static List<Langue> arrayLangues = new ArrayList<>();
	public static List<Pays> arrayPays = new ArrayList<>();
	public static List<Ville> arrayVilles = new ArrayList<>();
	public static List<Departement> arrayDepartements = new ArrayList<>();
	public static List<Quartier> arrayQuartiers = new ArrayList<>();
	public static List<Batiment> arrayBatiments = new ArrayList<>();
	public static List<Acteur> arrayActeurs = new ArrayList<>();
	public static List<Film> arrayFilms = new ArrayList<>();
	public static List<Realisateur> arrayRealisateurs = new ArrayList<>();
	public static List<Personne> arrayPersonnes = new ArrayList<>();
	public static List<Role> arrayRoles = new ArrayList<>();
	
	/**
	 * PARSE PAYS
	 * @return
	 */
	public static void parsePays() {
		Path paysPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\pays.csv");
		List<String> linesPays;
		try {
			linesPays = Files.readAllLines(paysPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		//On elève la première ligne du fichier. 
		linesPays.remove(0);
		
		try{
			for (String line : linesPays) {
				String[] tokens = line.split(";");

				String label = tokens[0];
				String url = tokens[1];

				Pays actuelPays = new Pays(label, url);

				arrayPays.add(actuelPays);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	/**
	 * PARSE LANGUE
	 * @return
	 */
	public static void parseLangue(){

		Path filmsPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\films.csv");
		List<String> linesFilm;
		try {
			linesFilm = Files.readAllLines(filmsPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		//On elève la première ligne du fichier. 
		linesFilm.remove(0);
		
		for (String line : linesFilm) {
			String[] tokens = line.split(";");
			String labelLangue = tokens[7];
			if (!labelLangue.isEmpty()) {
				Langue langue = Langue.getLangueByNom(arrayLangues, labelLangue);
				if (langue == null) {
					Langue newLangue = new Langue(labelLangue);
					arrayLangues.add(newLangue);
				}
			}
		}
	}
	
	/**
	 * PARSE GENRE
	 * @return
	 */
	public static void parseGenre() {
		Path filmsPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\films.csv");
		List<String> linesFilm;
		try {
			linesFilm = Files.readAllLines(filmsPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		//On elève la première ligne du fichier. 
		linesFilm.remove(0);
		
		for (String line : linesFilm) {
			String[] tokens = line.split(";");
			String lineGenre = tokens[6];
			String[] arrayLineGenre = lineGenre.split(",");
			for (String labelGenre : arrayLineGenre) {
				if (!labelGenre.isEmpty()) {
					Genre genre = Genre.getGenreByNom(arrayGenres, labelGenre);
					if (genre == null) {
						Genre newGenre = new Genre(labelGenre);
						arrayGenres.add(newGenre);
					}
				}
			}
		}
	}
	
	/**
	 * PARSE ADRESSE
	 */
	public static void parseAdresse() {
		//Acteur
		Path acteursPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\acteurs.csv");
		List<String> linesActeur;
		
		//Réalisateur
		Path realisateurPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\acteurs.csv");
		List<String> linesRealisateur;
		
		//Film
		Path filmPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\films.csv");
		List<String> linesFilm;
		
		try {
			linesActeur = Files.readAllLines(acteursPath, StandardCharsets.UTF_8);
			linesRealisateur = Files.readAllLines(realisateurPath, StandardCharsets.UTF_8);
			linesFilm = Files.readAllLines(filmPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		
		//On elève la première ligne du fichier. 
		linesActeur.remove(0);
		linesRealisateur.remove(0);
		linesFilm.remove(0);
		
		/*
		 * Acteur
		 */
		for (String line : linesActeur) {
			String[] tokens = line.split(";");
			String lineLieuxNaissances = tokens[3];
			if (!lineLieuxNaissances.isEmpty()) {
				String[] lieuNaissanceTab = lineLieuxNaissances.split(",");
				if (lieuNaissanceTab.length == 5) {
					String paysNom = lieuNaissanceTab[4].trim();
					String villeNom = lieuNaissanceTab[2].trim();
					String depNom = lieuNaissanceTab[3].trim();
					String quartierNom = lieuNaissanceTab[1].trim();
					String batimentNom = lieuNaissanceTab[0].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom != null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}

					if (!depNom.isEmpty()) {
			            dep = Departement.getDepartementByNom(arrayDepartements, depNom);
			            if (dep == null) {
			                dep = new Departement(depNom);
			                arrayDepartements.add(dep);
			            }
					}

					if (!quartierNom.isEmpty()) {
			            quartier = Quartier.getQuartierByNom(arrayQuartiers, quartierNom);
			            if (quartier == null) {
			                quartier = new Quartier(quartierNom);
			                arrayQuartiers.add(quartier);
			            }
					}

					if (!batimentNom.isEmpty()) {
			            batiment = Batiment.getBatimentByNom(arrayBatiments, batimentNom);
			            if (batiment == null) {
			                batiment = new Batiment(batimentNom);
			                arrayBatiments.add(batiment);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen5Adresse(arrayAdresses, pays, ville, dep, quartier, batiment);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(lieuNaissanceTab.length == 4) {
					String paysNom = lieuNaissanceTab[3].trim();
					String villeNom = lieuNaissanceTab[1].trim();
					String depNom = lieuNaissanceTab[2].trim();
					String quartierNom = lieuNaissanceTab[0].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}

					if (!depNom.isEmpty()) {
			            dep = Departement.getDepartementByNom(arrayDepartements, depNom);
			            if (dep == null) {
			                dep = new Departement(depNom);
			                arrayDepartements.add(dep);
			            }
					}

					if (!quartierNom.isEmpty()) {
			            quartier = Quartier.getQuartierByNom(arrayQuartiers, quartierNom);
			            if (quartier == null) {
			                quartier = new Quartier(quartierNom);
			                arrayQuartiers.add(quartier);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen4Adresse(arrayAdresses, pays, ville, dep, quartier);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(lieuNaissanceTab.length == 3) {
					String paysNom = lieuNaissanceTab[2].trim();
					String villeNom = lieuNaissanceTab[0].trim();
					String depNom = lieuNaissanceTab[1].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}

					if (!depNom.isEmpty()) {
			            dep = Departement.getDepartementByNom(arrayDepartements, depNom);
			            if (dep == null) {
			                dep = new Departement(depNom);
			                arrayDepartements.add(dep);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen3Adresse(arrayAdresses, pays, ville, dep);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(lieuNaissanceTab.length == 2) {
					String paysNom = lieuNaissanceTab[1].trim();
					String villeNom = lieuNaissanceTab[0].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen2Adresse(arrayAdresses, pays, ville);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(lieuNaissanceTab.length == 1) {
					String paysNom = lieuNaissanceTab[0].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen1Adresse(arrayAdresses, pays);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}
			}
		}
		
		/**
		 * Realisateur
		 */
		for (String line : linesRealisateur) {
			String[] tokens = line.split(";");
			String lineLieuxNaissances = tokens[3];
			if (!lineLieuxNaissances.isEmpty()) {
				String[] lieuNaissanceTab = lineLieuxNaissances.split(",");
				if (lieuNaissanceTab.length == 5) {
					String paysNom = lieuNaissanceTab[4].trim();
					String villeNom = lieuNaissanceTab[2].trim();
					String depNom = lieuNaissanceTab[3].trim();
					String quartierNom = lieuNaissanceTab[1].trim();
					String batimentNom = lieuNaissanceTab[0].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}

					if (!depNom.isEmpty()) {
			            dep = Departement.getDepartementByNom(arrayDepartements, depNom);
			            if (dep == null) {
			                dep = new Departement(depNom);
			                arrayDepartements.add(dep);
			            }
					}

					if (!quartierNom.isEmpty()) {
			            quartier = Quartier.getQuartierByNom(arrayQuartiers, quartierNom);
			            if (quartier == null) {
			                quartier = new Quartier(quartierNom);
			                arrayQuartiers.add(quartier);
			            }
					}

					if (!batimentNom.isEmpty()) {
			            batiment = Batiment.getBatimentByNom(arrayBatiments, batimentNom);
			            if (batiment == null) {
			                batiment = new Batiment(batimentNom);
			                arrayBatiments.add(batiment);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen5Adresse(arrayAdresses, pays, ville, dep, quartier, batiment);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(lieuNaissanceTab.length == 4) {
					String paysNom = lieuNaissanceTab[3].trim();
					String villeNom = lieuNaissanceTab[1].trim();
					String depNom = lieuNaissanceTab[2].trim();
					String quartierNom = lieuNaissanceTab[0].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}

					if (!depNom.isEmpty()) {
			            dep = Departement.getDepartementByNom(arrayDepartements, depNom);
			            if (dep == null) {
			                dep = new Departement(depNom);
			                arrayDepartements.add(dep);
			            }
					}

					if (!quartierNom.isEmpty()) {
			            quartier = Quartier.getQuartierByNom(arrayQuartiers, quartierNom);
			            if (quartier == null) {
			                quartier = new Quartier(quartierNom);
			                arrayQuartiers.add(quartier);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen4Adresse(arrayAdresses, pays, ville, dep, quartier);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(lieuNaissanceTab.length == 3) {
					String paysNom = lieuNaissanceTab[2].trim();
					String villeNom = lieuNaissanceTab[0].trim();
					String depNom = lieuNaissanceTab[1].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}

					if (!depNom.isEmpty()) {
			            dep = Departement.getDepartementByNom(arrayDepartements, depNom);
			            if (dep == null) {
			                dep = new Departement(depNom);
			                arrayDepartements.add(dep);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen3Adresse(arrayAdresses, pays, ville, dep);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(lieuNaissanceTab.length == 2) {
					String paysNom = lieuNaissanceTab[1].trim();
					String villeNom = lieuNaissanceTab[0].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen2Adresse(arrayAdresses, pays, ville);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(lieuNaissanceTab.length == 1) {
					String paysNom = lieuNaissanceTab[0].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen1Adresse(arrayAdresses, pays);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}
			}
			

		}
		
		/**
		 * Film
		 */
		for (String line : linesFilm) {
			String[] tokens = line.split(";");
			String lineAdresses = tokens[3];
			if (!lineAdresses.isEmpty()) {
				String[] adressesTab = lineAdresses.split(",");
				if (adressesTab.length == 5) {
					String paysNom = adressesTab[0].trim();
					String villeNom = adressesTab[4].trim();
					String depNom = adressesTab[1].trim();
					String quartierNom = adressesTab[2].trim();
					String batimentNom = adressesTab[3].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}

					if (!depNom.isEmpty()) {
			            dep = Departement.getDepartementByNom(arrayDepartements, depNom);
			            if (dep == null) {
			                dep = new Departement(depNom);
			                arrayDepartements.add(dep);
			            }
					}

					if (!quartierNom.isEmpty()) {
			            quartier = Quartier.getQuartierByNom(arrayQuartiers, quartierNom);
			            if (quartier == null) {
			                quartier = new Quartier(quartierNom);
			                arrayQuartiers.add(quartier);
			            }
					}

					if (!batimentNom.isEmpty()) {
			            batiment = Batiment.getBatimentByNom(arrayBatiments, batimentNom);
			            if (batiment == null) {
			                batiment = new Batiment(batimentNom);
			                arrayBatiments.add(batiment);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen5Adresse(arrayAdresses, pays, ville, dep, quartier, batiment);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(adressesTab.length == 4) {
					String paysNom = adressesTab[0].trim();
					String villeNom = adressesTab[3].trim();
					String depNom = adressesTab[1].trim();
					String quartierNom = adressesTab[2].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}

					if (!depNom.isEmpty()) {
			            dep = Departement.getDepartementByNom(arrayDepartements, depNom);
			            if (dep == null) {
			                dep = new Departement(depNom);
			                arrayDepartements.add(dep);
			            }
					}

					if (!quartierNom.isEmpty()) {
			            quartier = Quartier.getQuartierByNom(arrayQuartiers, quartierNom);
			            if (quartier == null) {
			                quartier = new Quartier(quartierNom);
			                arrayQuartiers.add(quartier);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen4Adresse(arrayAdresses, pays, ville, dep, quartier);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(adressesTab.length == 3) {
					String paysNom = adressesTab[0].trim();
					String villeNom = adressesTab[2].trim();
					String depNom = adressesTab[1].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}

					if (!depNom.isEmpty()) {
			            dep = Departement.getDepartementByNom(arrayDepartements, depNom);
			            if (dep == null) {
			                dep = new Departement(depNom);
			                arrayDepartements.add(dep);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen3Adresse(arrayAdresses, pays, ville, dep);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(adressesTab.length == 2) {
					String paysNom = adressesTab[0].trim();
					String villeNom = adressesTab[1].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
					if (!villeNom.isEmpty()) {
			            ville = Ville.getVilleByNom(arrayVilles, villeNom);
			            if (ville == null) {
			                ville = new Ville(villeNom);
			                arrayVilles.add(ville);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen2Adresse(arrayAdresses, pays, ville);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}else if(adressesTab.length == 1) {
					String paysNom = adressesTab[0].trim();
					Pays pays = null;
					Ville ville = null;
					Departement dep = null;
					Quartier quartier = null;
					Batiment batiment = null;
					
					if (paysNom!= null && !paysNom.isEmpty()) {
			            pays = Pays.getPaysByNom(arrayPays, paysNom);
			            if (pays == null) {
			                pays = new Pays(paysNom);
			                arrayPays.add(pays);
			            }
					}
		            
		            Adresse adresse = Adresse.getSpecifiqueLen1Adresse(arrayAdresses, pays);
		            if (adresse == null) {
		            	adresse = new Adresse(ville, batiment, quartier, dep, pays);
		            	arrayAdresses.add(adresse);
					}
				}
			}
		}
	}
	
	/**
	 * PARSE FILM
	 */
	public static void parseFilm() {
		Path filmsPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\films.csv");
		List<String> linesFilm;
		
		try {
			linesFilm = Files.readAllLines(filmsPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		
		//On elève la première ligne du fichier. 
		linesFilm.remove(0);

		for (String line : linesFilm) {
			String[] tokens = line.split(";");
			Film film = new Film();
			
			if (tokens.length > 10) {
				tokens[8] = tokens[8] + tokens[9];
				tokens[9] = tokens[10];
			}

			String pays = null;

			if (tokens.length == 10) {
				pays = tokens[9];
			}

			String idImdb = tokens[0].trim();
			String nom = tokens[1];
			String annee = tokens[2];
			String noteString = tokens[3];
			String url = tokens[4];
			String lieuTournage = tokens[5];
			String ligneGenre = tokens[6];
			String langue = tokens[7];
			String resume = tokens[8];

			if (idImdb != null) {
				film.setIdImdb(idImdb);
			}
			
			if (nom != null) {
				film.setNom(nom);
			}
			
			if (annee != null) {
				film.setAnnee(annee);
			}
			
			if (resume != null) {
				film.setResume(resume);
			}
			
			if (url != null) {
				film.setUrl(url);
			}
			
			Pays paysObjet = Pays.getPaysByNom(arrayPays, pays);
			if (paysObjet != null) {
				film.setPays(paysObjet);
			}
			
			Langue langueObjet = Langue.getLangueByNom(arrayLangues, langue);
			if (langueObjet != null) {
				film.setLangue(langueObjet);
			}
			
			Adresse adresse = null;
			if (lieuTournage != null) {
				String[] tokensAdresse = lieuTournage.split(",");
				if (tokensAdresse.length == 5) {
					Pays paysAdresse = Pays.getPaysByNom(arrayPays, tokensAdresse[0]);
					Ville villeAdresse = Ville.getVilleByNom(arrayVilles, tokensAdresse[4]);
					Departement depAdresse = Departement.getDepartementByNom(arrayDepartements, tokensAdresse[1]);
					Quartier quartierAdresse = Quartier.getQuartierByNom(arrayQuartiers, tokensAdresse[2]);
					Batiment batimentAdresse = Batiment.getBatimentByNom(arrayBatiments, tokensAdresse[3]);
					adresse = Adresse.getSpecifiqueLen5Adresse(arrayAdresses,paysAdresse,villeAdresse,depAdresse,quartierAdresse,batimentAdresse);
				}else if (tokensAdresse.length == 4) {
					Pays paysAdresse = Pays.getPaysByNom(arrayPays, tokensAdresse[0]);
					Ville villeAdresse = Ville.getVilleByNom(arrayVilles, tokensAdresse[3]);
					Departement depAdresse = Departement.getDepartementByNom(arrayDepartements, tokensAdresse[1]);
					Quartier quartierAdresse = Quartier.getQuartierByNom(arrayQuartiers, tokensAdresse[2]);
					adresse = Adresse.getSpecifiqueLen4Adresse(arrayAdresses,paysAdresse,villeAdresse,depAdresse,quartierAdresse);
				}else if (tokensAdresse.length == 3) {
					Pays paysAdresse = Pays.getPaysByNom(arrayPays, tokensAdresse[0]);
					Ville villeAdresse = Ville.getVilleByNom(arrayVilles, tokensAdresse[2]);
					Departement depAdresse = Departement.getDepartementByNom(arrayDepartements, tokensAdresse[1]);
					adresse = Adresse.getSpecifiqueLen3Adresse(arrayAdresses,paysAdresse,villeAdresse,depAdresse);
				}else if (tokensAdresse.length == 2) {
					Pays paysAdresse = Pays.getPaysByNom(arrayPays, tokensAdresse[0]);
					Ville villeAdresse = Ville.getVilleByNom(arrayVilles, tokensAdresse[1]);
					adresse = Adresse.getSpecifiqueLen2Adresse(arrayAdresses,paysAdresse,villeAdresse);
				}else if (tokensAdresse.length == 1) {
					Pays paysAdresse = Pays.getPaysByNom(arrayPays, tokensAdresse[0]);
					adresse = Adresse.getSpecifiqueLen1Adresse(arrayAdresses,paysAdresse);
				}
				film.setAdresse(adresse);
			}
			
			String[] listGenre = ligneGenre.split(",");
			for (String genres : listGenre) {
				Genre genre = Genre.getGenreByNom(arrayGenres, genres);
				if (genre != null) {
					film.getGenres().add(genre);
				}
			}
			
			double note = 0;
			if (noteString != null && !noteString.isEmpty()) {
				note = Double.parseDouble(noteString);
			}
			film.setNote(note);
			arrayFilms.add(film);
		}
	}
	
	/**
	 * PARSE PERSONNE
	 */
	public static void parseRealisateur() {
		Path realisateurPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\realisateurs.csv");
		List<String> linesRealisateur;
		
		try {
			linesRealisateur = Files.readAllLines(realisateurPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		
		//On elève la première ligne du fichier. 
		linesRealisateur.remove(0);

		for (String line : linesRealisateur) {
			String[] tokens = line.split(";");
			Realisateur realisateur = new Realisateur();
			String imdb = tokens[0].trim();
			String nom = tokens[1].trim();
			String date = tokens[2].trim();
			String adresseString = tokens[3].trim();
			String url = tokens[4].trim();
			
			LocalDate dateNaissance = null;
			if (!date.isEmpty()) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy ", Locale.ENGLISH);
					dateNaissance = LocalDate.parse(date, formatter);
				} catch (Exception e) {
					System.out.println("La chaîne n'est pas au format de date désiré.");
				}
			}
			realisateur.setDdn(dateNaissance);
			
			Adresse adresse = findAdresse(adresseString);
			
			realisateur.setAdresse(adresse);
			realisateur.setUrl(url);
			realisateur.setIdImdb(imdb);
			realisateur.setDdn(dateNaissance);
			realisateur.setIdentite(nom);
			arrayRealisateurs.add(realisateur);
		}
		

	}
	
	/**
	 * PARSE ACTEUR
	 */
	public static void parseActeur() {
		Path acteurPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\acteurs.csv");
		List<String> linesActeur;
		
		try {
			linesActeur = Files.readAllLines(acteurPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		
		//On elève la première ligne du fichier. 
		linesActeur.remove(0);
		
		for (String line : linesActeur) {
			String[] tokens = line.split(";");
			Acteur acteur = new Acteur();
			String imdb = tokens[0].trim();
			String nom = tokens[1].trim();
			String date = tokens[2].trim();
			String adresseString = tokens[3].trim();
			String url = tokens[4].trim();
			
			LocalDate dateNaissance = null;
			if (!date.isEmpty()) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy ", Locale.ENGLISH);
					dateNaissance = LocalDate.parse(date, formatter);
				} catch (Exception e) {
					System.out.println("La chaîne n'est pas au format de date désiré.");
				}
			}
			acteur.setDdn(dateNaissance);
			
			Adresse adresse = findAdresse(adresseString);

			acteur.setAdresse(adresse);
			acteur.setUrl(url);
			acteur.setIdImdb(imdb);
			acteur.setIdentite(nom);
			arrayActeurs.add(acteur);
		}
	}
	
	/**
	 * PARSE FILM REALISATEUR
	 */
	public static void parseFilmRealisateur() {
		Path filmRealisateurPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\film_realisateurs.csv");
		List<String> linesFilmRealisateur;
		
		try {
			linesFilmRealisateur = Files.readAllLines(filmRealisateurPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		
		linesFilmRealisateur.remove(0);
		
		for (String line : linesFilmRealisateur) {
			String[] token = line.split(";");
			String filmIMDB = token[0].trim();
			String realisateurIMDB = token[1].trim();
			
			Realisateur realisateur = Realisateur.getRealisateurByIMDB(arrayRealisateurs, realisateurIMDB);
			Film film = Film.getFilmByIMDB(arrayFilms, filmIMDB);

			if (realisateur != null && film != null) {
			    realisateur.getFilms().add(film);
			    film.getRealisateurs().add(realisateur);
			} else {
			    System.out.println("Realisateur ou Film introuvable pour IMDB: " + realisateurIMDB + ", " + filmIMDB);
			}
		}
	}
	
	/**
	 * PARSE ROLE
	 */
	public static void parseRole() {
		Path rolesPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\roles.csv");
		List<String> linesRoles;
		
		try {
			linesRoles = Files.readAllLines(rolesPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		
		linesRoles.remove(0);
		
		for (String line : linesRoles) {
			String[] token = line.split(";");
			String filmIMDB = token[0];
			String acteurIMDB = token[1];
			String personnage = null;
			
			if (token.length >= 3) {
				personnage = token[2].trim();
			}

			System.out.println(filmIMDB + " , " + acteurIMDB + " , " + personnage);
			
			Acteur acteur = Acteur.getActeurByIMDB(arrayActeurs, acteurIMDB);
			Film film = Film.getFilmByIMDB(arrayFilms, filmIMDB);			
			Role role = null;
			
			if (personnage != null && !personnage.isEmpty()) {
				role = new Role(personnage, acteur, film);
			}else {
				role = new Role("Unknow", acteur, film);
			}
			
			arrayRoles.add(role);
		}
	}
	
	/**
	 * PARSE CASTING PRINCIPAL
	 */
	public static void parseCastingPrincipal() {
		Path castingPath = Paths.get("E:\\Projets java\\Cinema\\src\\main\\java\\csv\\castingPrincipal.csv");
		List<String> linesCastings;
		
		try {
			linesCastings = Files.readAllLines(castingPath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Le fichier .csv n'a pas été trouvé: ");
		}
		
		linesCastings.remove(0);
		
		for (String line : linesCastings) {
			String[] token = line.split(";");
			String filmIMDB = token[0];
			String acteurIMDB = token[1];
			
			Acteur acteur = Acteur.getActeurByIMDB(arrayActeurs, acteurIMDB);
			Film film = Film.getFilmByIMDB(arrayFilms, filmIMDB);	
				
			if (acteur != null) {
				acteur.getFilms().add(film);
			}else {
				System.err.println("Acteur introuvable pour l'IMDB : " + acteurIMDB);
			}
			
			if (film != null) {
				film.getActeurs().add(acteur);
			}else {
				System.err.println("Film introuvable pour l'IMDB : " + filmIMDB);
			}
		}
	}
	
	/**
	 * Trouver une adresse pour Acteur et Realisateur
	 * @param adresseString
	 * @return
	 */
	private static Adresse findAdresse(String adresseString) {
		Adresse adresse = null;
		if (adresseString != null) {
			String[] tokensAdresse = adresseString.split(",");
			if (tokensAdresse.length == 5) {
				String paysNom = tokensAdresse[4].trim();
				String villeNom = tokensAdresse[2].trim();
				String depNom = tokensAdresse[3].trim();
				String quartierNom = tokensAdresse[1].trim();
				String batimentNom = tokensAdresse[0].trim();
				Pays paysAdresse = Pays.getPaysByNom(arrayPays, paysNom);
				Ville villeAdresse = Ville.getVilleByNom(arrayVilles, villeNom);
				Departement depAdresse = Departement.getDepartementByNom(arrayDepartements, depNom);
				Quartier quartierAdresse = Quartier.getQuartierByNom(arrayQuartiers, quartierNom);
				Batiment batimentAdresse = Batiment.getBatimentByNom(arrayBatiments, batimentNom);
				adresse = Adresse.getSpecifiqueLen5Adresse(arrayAdresses,paysAdresse,villeAdresse,depAdresse,quartierAdresse,batimentAdresse);
			}else if (tokensAdresse.length == 4) {
				String paysNom = tokensAdresse[3].trim();
				String villeNom = tokensAdresse[1].trim();
				String depNom = tokensAdresse[2].trim();
				String quartierNom = tokensAdresse[0].trim();
				Pays paysAdresse = Pays.getPaysByNom(arrayPays, paysNom);
				Ville villeAdresse = Ville.getVilleByNom(arrayVilles, villeNom);
				Departement depAdresse = Departement.getDepartementByNom(arrayDepartements, depNom);
				Quartier quartierAdresse = Quartier.getQuartierByNom(arrayQuartiers, quartierNom);
				adresse = Adresse.getSpecifiqueLen4Adresse(arrayAdresses,paysAdresse,villeAdresse,depAdresse,quartierAdresse);
			}else if (tokensAdresse.length == 3) {
				String paysNom = tokensAdresse[2].trim();
				String villeNom = tokensAdresse[0].trim();
				String depNom = tokensAdresse[1].trim();
				Pays paysAdresse = Pays.getPaysByNom(arrayPays, paysNom);
				Ville villeAdresse = Ville.getVilleByNom(arrayVilles, villeNom);
				Departement depAdresse = Departement.getDepartementByNom(arrayDepartements, depNom);
				adresse = Adresse.getSpecifiqueLen3Adresse(arrayAdresses,paysAdresse,villeAdresse,depAdresse);
			}else if (tokensAdresse.length == 2) {
				String paysNom = tokensAdresse[1].trim();
				String villeNom = tokensAdresse[0].trim();
				Pays paysAdresse = Pays.getPaysByNom(arrayPays, paysNom);
				Ville villeAdresse = Ville.getVilleByNom(arrayVilles, villeNom);
				adresse = Adresse.getSpecifiqueLen2Adresse(arrayAdresses,paysAdresse,villeAdresse);
			}else if (tokensAdresse.length == 1) {
				String paysNom = tokensAdresse[0].trim();
				Pays paysAdresse = Pays.getPaysByNom(arrayPays, paysNom);
				adresse = Adresse.getSpecifiqueLen1Adresse(arrayAdresses,paysAdresse);
			}
			return adresse;
		}
		return null;
	}
}
