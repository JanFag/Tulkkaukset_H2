package projekti.tulkkaukset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import projekti.tulkkaukset.domain.Tulkkaustyyppi;
import projekti.tulkkaukset.domain.TulkkaustyyppiRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class TulkkaustyyppiRepositoryTest {
	
	
	@Autowired
	private TulkkaustyyppiRepository trepository;
	
	
	@Test
	public void isTheSizeCorrect() {
		List<Tulkkaustyyppi> tulkkaustyypit = new ArrayList<>();
		tulkkaustyypit.addAll((Collection<? extends Tulkkaustyyppi>) trepository.findAll());
		assertThat(tulkkaustyypit.size()==3);
		
	}
	
}

