package projekti.tulkkaukset.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;





public interface TulkkauskieliRepository extends CrudRepository<Tulkkauskieli, Long> {

	List<Tulkkauskieli> findByTulkkauskielennimi(String tulkkauskielennimi);
  }