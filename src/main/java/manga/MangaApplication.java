package manga;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import manga.model.Actualiter;
import manga.model.Langue;
import manga.model.Manga;
import manga.model.Role;
import manga.model.Tom;
import manga.model.Tva;
import manga.model.Utilisateur;

import manga.repository.ActualiterRepository;
import manga.repository.LangueRepository;
import manga.repository.MangaRepository;

import manga.repository.RoleRepository;
import manga.repository.TomRepository;
import manga.repository.TvaRepository;
import manga.repository.UtilisateurRepository;
import manga.service.PasswordEncoderService;

@SpringBootApplication
public class MangaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangaApplication.class, args);

	}

	@Autowired
	private ActualiterRepository actualiterRepository;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoderService passwordEncoderService;

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private TvaRepository tvaRepository;
	@Autowired
	private MangaRepository mangaRepository;
	@Autowired
	private TomRepository tomRepository;
	@Autowired
	private LangueRepository langueRepository;


	@Bean
	CommandLineRunner run() {
		return args -> {
/*			GregorianCalendar calandar = new GregorianCalendar(2017, 4, 21);
			Date date = calandar.getTime();
		
			// password
			String password = "123456789";
			String encode = passwordEncoderService.encoder(password);

			Actualiter actualiter = new Actualiter("Tres bon manga", date);
			actualiterRepository.save(actualiter);
*/
			Role user = new Role();
			user.setNom(Role.USER);
			Role admin = new Role();
			admin.setNom(Role.ADMIN);
			roleRepository.save(user);
			roleRepository.save(admin);

			Tva tva01 = new Tva();
			tva01.setNom("TVA de 20 %");
			tva01.setTaux(20);
			Tva tva02 = new Tva();
			tva02.setNom("TVA de 30 %");
			tva02.setTaux(30);
			tvaRepository.save(tva01);
			tvaRepository.save(tva02);
/*
			Langue langue = new Langue();
			langue.setNom("Francais");
			langueRepository.save(langue);

			Manga manga = new Manga("One piece", "AZERTYUI93", "onepiece.jpeg", "one piece tom 3", "meilleur manga du monde",
					 4, true, date, 12, 24);
			manga.setTva(tva02);
			manga.setLangue(langue);
			mangaRepository.save(manga);

			Manga manga1 = new Manga("Inazuma", "AZERTYUI93", "Inazuma.jpeg", "one piece tom 3", "meilleur manga du monde",
					 4, false, date, 12, 24);
			manga1.setTva(tva02);
			manga1.setLangue(langue);
			mangaRepository.save(manga1);
			Manga manga2 = new Manga("Naruto", "AZERTYUI93", "Naruto.jpeg", "one piece tom 3", "meilleur manga du monde", 
					4, true, date, 12, 24);
			manga2.setTva(tva02);
			manga2.setLangue(langue);
			mangaRepository.save(manga2);

			Tom tom = new Tom();
			tom.setNumImage("Manga.jpeg");
			tom.setNom("One piece l'arc de luffy");
			tom.setNumero(3);
			tom.setStatut(true);
			tom.setManga(manga);
			tom.setNumSeri("Azertyuio12");
			tom.setDateDeSortie(date);
			tom.setDescription("tom manga de l'annee");
			tom.setManga(manga);
			tom.setPrix(12);
			tom.setTitre("One piece l'arc de luffy");
			tomRepository.save(tom);

//			LocalDate date2 =LocalDate.of(1999, 04, 15);

			Utilisateur adminUser = new Utilisateur(" Bismark01", "Bob", "marley", "bob@gmail.com", 21, "0659940110",
					encode, Utilisateur.active, 0);
			adminUser.setRole(admin);
			utilisateurRepository.save(adminUser);*/

		};
	}

}
