package CrudSCJapp.models.repository;

import org.springframework.data.repository.CrudRepository;

import CrudSCJapp.models.entity.Ciudad;

public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

}
