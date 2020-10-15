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


import projekti.tulkkaukset.domain.Tulkkauskieli;
import projekti.tulkkaukset.domain.TulkkauskieliRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class TulkkauskieliRepositoryTest {
	
	
	@Autowired
	private TulkkauskieliRepository krepository;
	
	
	@Test
	public void isThereAllLanguages() {
		ArrayList<Tulkkauskieli> kielet = new ArrayList<>();
		kielet.addAll((Collection<? extends Tulkkauskieli>) krepository.findAll());
		int apu = 0;
		for(int i = 0; i<kielet.size();i++) {
			if(kielet.get(i).getTulkkauskielennimi().equals("italia")) {
				apu++;
			}
			if(kielet.get(i).getTulkkauskielennimi().equals("englanti")) {
				apu++;
			}
			if(kielet.get(i).getTulkkauskielennimi().equals("venäjä")) {
				apu++;
			}
			if(kielet.get(i).getTulkkauskielennimi().equals("ranska")) {
				apu++;
			}
			if(kielet.get(i).getTulkkauskielennimi().equals("ruotsi")) {
				apu++;
			}
			if(kielet.get(i).getTulkkauskielennimi().equals("saksa")) {
				apu++;
			}
				
		}
		assertThat(apu == 6);
		
		
	}
	
}