package CrudSCJapp.models.service;

import java.util.List;

import CrudSCJapp.models.entity.Tarea;

public interface ITareaService {
	
	public List<Tarea> listarTodos();
	public void guardar(Tarea tarea);
	public Tarea BuscarPorId(Long id_tarea);
	public void eliminar(Long id_tarea);

}
