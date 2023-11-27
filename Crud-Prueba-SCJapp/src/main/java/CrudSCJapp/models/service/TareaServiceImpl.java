package CrudSCJapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CrudSCJapp.models.entity.Tarea;
import CrudSCJapp.models.repository.TareaRepository;

@Service
public class TareaServiceImpl implements ITareaService {
	
	@Autowired
	private TareaRepository tareaRepository;
	
	@Override
	public List<Tarea> listarTodos() {
		
		return (List<Tarea>) tareaRepository.findAll();
	}

	@Override
	public void guardar(Tarea tarea) {
		
		tareaRepository.save(tarea);
	}

	@Override
	public Tarea BuscarPorId(Long id_tarea) {
	
		return tareaRepository.findById(id_tarea).orElse(null);
	}

	@Override
	public void eliminar(Long id_tarea) {
		
		tareaRepository.deleteById(id_tarea);
	}

}
