package projekti.tulkkaukset;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import projekti.tulkkaukset.domain.Tulkkaus;
import projekti.tulkkaukset.domain.TulkkausRepository;
import projekti.tulkkaukset.domain.Tulkkauskieli;
import projekti.tulkkaukset.domain.Tulkkaustyyppi;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class TulkkausRepositoryTest {
	
	@Autowired
	private TulkkausRepository repository;
	
	
	@Test
	public void createNewTulkkaus() {
		Tulkkaus tulkkaus = new Tulkkaus("Kannelmäen koulu", "vanhempainilta", "2.kerros, opettajien huone", "Kannelmäenketu 14, 00450 Helsinki", "25.11.2020 kello 8:30", new Tulkkauskieli("somali"), new Tulkkaustyyppi("videotulkkaus"));
		repository.save(tulkkaus);
		assertThat(tulkkaus.getId()).isNotNull();
		
	}
	
}
