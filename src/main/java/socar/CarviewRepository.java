package socar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarviewRepository extends CrudRepository<Carview, Long> {

    List<Carview> findByRsvId(Long rsvId);
    List<Carview> findByRsvId(Long rsvId);
    List<Carview> findByPayId(Long payId);

}