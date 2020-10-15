package projekti.tulkkaukset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import projekti.tulkkaukset.domain.Tulkkaus;
import projekti.tulkkaukset.domain.TulkkausRepository;
import projekti.tulkkaukset.domain.Tulkkauskieli;
import projekti.tulkkaukset.domain.TulkkauskieliRepository;
import projekti.tulkkaukset.domain.Tulkkaustyyppi;
import projekti.tulkkaukset.domain.TulkkaustyyppiRepository;
import projekti.tulkkaukset.domain.User;
import projekti.tulkkaukset.domain.UserRepository;


@SpringBootApplication
public class TulkkauksetApplication {
	public static final Logger log = LoggerFactory.getLogger(TulkkauksetApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TulkkauksetApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(TulkkausRepository repository, TulkkaustyyppiRepository trepository, UserRepository urepository, TulkkauskieliRepository krepository ) {
	return (args) -> {
		log.info("save a couple of books");
		trepository.save(new Tulkkaustyyppi("Paikan päällä"));
		trepository.save(new Tulkkaustyyppi("Videotulkkaus"));
		trepository.save(new Tulkkaustyyppi("Puhelintulkkaus"));
		krepository.save(new Tulkkauskieli("italia"));
		krepository.save(new Tulkkauskieli("englanti"));
		krepository.save(new Tulkkauskieli("venäjä"));
		krepository.save(new Tulkkauskieli("saksa"));
		krepository.save(new Tulkkauskieli("ranska"));
		krepository.save(new Tulkkauskieli("ruotsi"));
		
		
		Tulkkaus t1 = new Tulkkaus("Malmin neuvola", "lapsen 3v-tarkastus", "2.kerros, huone 3", "Talvelantie 4, 00700 Helsinki", "25.09.2020 kello 12:00", krepository.findByTulkkauskielennimi("englanti").get(0), trepository.findByTulkkaustyypinnimi("Paikan päällä").get(0));
		Tulkkaus t2 = new Tulkkaus("Itäkeskuksen sosiaalitoimisto", "raha-asiat", "6.kerros, huone 4", "Itäkeskuksenkatu 6, 00990 Helsinki", "27.09.2020 kello 15:00", krepository.findByTulkkauskielennimi("ruotsi").get(0), trepository.findByTulkkaustyypinnimi("Paikan päällä").get(0));
		Tulkkaus t3 = new Tulkkaus("Martinlaakson koulu", "vanhempainpalaveri", "opettajien huone", "Martinlaaksonkatu 6, 00550 Helsinki", "26.09.2020 kello 09:00", krepository.findByTulkkauskielennimi("venäjä").get(0), trepository.findByTulkkaustyypinnimi("Videotulkkaus").get(0));
		
		
		repository.save(t1);
		repository.save(t2);
		repository.save(t3);
		
		User user1 = new User("admin", "$2y$10$AxzWWo5GTqQdUMR5TN1/uOF2cvu3egzUvMlPoNIrtjwSSt1o8uZWK", "ADMIN");
		User user2 = new User("tulkki", "$2y$10$3paLnb6OMgPET27HzKyCZe/GjvgIUWPt4ezVhbcm9H0HGlAtDSBL.", "TULKKI");
		

		urepository.save(user1);
		urepository.save(user2);
		
	};
	}

}
