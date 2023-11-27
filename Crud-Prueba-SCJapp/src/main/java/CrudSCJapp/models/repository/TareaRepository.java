package CrudSCJapp.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import CrudSCJapp.models.entity.Tarea;


@Repository
public interface TareaRepository extends CrudRepository<Tarea, Long> {

}
