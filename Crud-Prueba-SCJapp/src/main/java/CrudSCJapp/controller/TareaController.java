package CrudSCJapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import CrudSCJapp.models.entity.Ciudad;
import CrudSCJapp.models.entity.Tarea;
import CrudSCJapp.models.service.ICiudadService;
import CrudSCJapp.models.service.ITareaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/views/tareas")
public class TareaController {

	@Autowired
	private ITareaService tareaService;
	
	@Autowired
	private ICiudadService ciudadService;
	
	@GetMapping("/")
	public String ListarTareas(Model model) {
		
		
		List<Tarea> listadoTareas=tareaService.listarTodos();
		
		model.addAttribute("titulo", "Listado de Tareas");
		model.addAttribute("tareas", listadoTareas);
		
		return "/views/tareas/listar";
	}
	
	@GetMapping("/create")
	
	public String nuevo(Model model) {
		
		Tarea tarea = new Tarea();
		List<Ciudad> listCiudades = ciudadService.ListaCiudades();
		model.addAttribute("titulo", "Formulario: Nueva Tarea" );
		model.addAttribute("tareas", tarea);
		model.addAttribute("ciudades", listCiudades);
		
		return "/views/tareas/frmNuevo";
		}
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Tarea tarea, BindingResult result, Model model, RedirectAttributes attribute) {
		
		List<Ciudad> listCiudades = ciudadService.ListaCiudades();
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nueva Tarea" );
			model.addAttribute("tareas", tarea);
			model.addAttribute("ciudades", listCiudades);
			System.out.println("Errores en el Formulrio");
			
			return "/views/tareas/frmNuevo";
			
		}
		
		tareaService.guardar(tarea);
		System.out.println("Tarea Creada");
		attribute.addFlashAttribute("success","Tarea Creada");
		
		return "redirect:/views/tareas/";
		}
	
	@GetMapping("/edit/{id_tarea}")
	
	public String Editar(@PathVariable("id_tarea") Long idTarea, Model model, RedirectAttributes attribute) {
		
		Tarea tarea = null;
		
		
		
		if(idTarea>0) {
			tarea = tareaService.BuscarPorId(idTarea); 
			if(tarea== null) {
				System.out.println("Error : El Id tarea no existe");
				attribute.addFlashAttribute("error","ATTENCION: El Id tarea no existe");
				return "redirect:/views/tareas/";
			}
		} else {
			
			System.out.println("Error : Error con el Id de la tarea");
			attribute.addFlashAttribute("error"," ATTENCION: Error con el Id de la tarea");
			return "redirect:/views/tareas/";
		}
		
		List<Ciudad> listCiudades = ciudadService.ListaCiudades();
		model.addAttribute("titulo", "Formulario: Editar Tarea" ); 
		model.addAttribute("tareas", tarea);
		model.addAttribute("ciudades", listCiudades);
		
		return "/views/tareas/frmEdita";
	
	}
	
	@GetMapping("/delete/{id_tarea}")
	
	public String eliminar(@PathVariable("id_tarea") Long idTarea, RedirectAttributes attribute) {
		
		Tarea tarea = null;
		
		if(idTarea>0) {
			tarea = tareaService.BuscarPorId(idTarea);
			
			if(tarea== null) {
				System.out.println("Error : EL Id tarea no existe");
				attribute.addFlashAttribute("error","ATTENCION: El Id tarea no existe");
				return "redirect:/views/tareas/";
			}
		} else {
			
			System.out.println("Error : Erron con el Id tarea no existe");
			attribute.addFlashAttribute("error"," ATTENCION: Error con el Id de la tarea");
			return "redirect:/views/tareas/";
		}
			
		tareaService.eliminar(idTarea);
		System.out.println("Tarea Eliminada");
		attribute.addFlashAttribute("warning","Tarea Eliminada");
		
		return "redirect:/views/tareas/";
	
	}
}
