package projekti.tulkkaukset.domain;

import java.util.List;


import org.springframework.data.repository.CrudRepository;




public interface TulkkausRepository extends CrudRepository<Tulkkaus, Long> {

	List<Tulkkaus> findAll();
}
