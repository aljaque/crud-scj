package CrudSCJapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CrudSCJapp.models.entity.Ciudad;
import CrudSCJapp.models.repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements ICiudadService {

	@Autowired
	private CiudadRepository ciudadRepository;
	@Override
	public List<Ciudad> ListaCiudades() {
		
		return (List<Ciudad>) ciudadRepository.findAll();
	}

}
