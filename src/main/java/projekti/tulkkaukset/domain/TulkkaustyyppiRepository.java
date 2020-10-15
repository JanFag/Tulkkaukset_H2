package projekti.tulkkaukset.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface TulkkaustyyppiRepository extends CrudRepository<Tulkkaustyyppi, Long> {
	List<Tulkkaustyyppi>findByTulkkaustyypinnimi(String tulkkaustyypinnimi);

	
	
}
