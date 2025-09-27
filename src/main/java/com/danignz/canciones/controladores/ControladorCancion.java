package com.danignz.canciones.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.danignz.canciones.modelos.Cancion;
import com.danignz.canciones.servicios.ServicioCancion;

import jakarta.validation.Valid;

@Controller
public class ControladorCancion {
    @Autowired
    private final ServicioCancion servicioCancion;

    public ControladorCancion(ServicioCancion servicioCancion){
        this.servicioCancion=servicioCancion;
    }

    @GetMapping("/canciones")
    public String desplegarCanciones(Model modelo){
       List<Cancion>listaCanciones = this.servicioCancion.obtenerTodasLasCanciones(); 
       modelo.addAttribute("listaCanciones", listaCanciones);
            return "canciones";
    }

    @GetMapping("/canciones/detalle/{idCancion}")
    public String desplegarDetalleCancion(@PathVariable Long idCancion, Model modelo) {
        Cancion cancion = this.servicioCancion.obtenerCancionPorId(idCancion);
        modelo.addAttribute("cancion", cancion);
            return "detalleCancion";
    }

    @GetMapping("/canciones/formulario/agregar")
    public String formularioAgregarCancion(@ModelAttribute("cancion") Cancion cancion){
        return "agregarCancion";
    }

    @PostMapping("/canciones/procesa/agregar")
    public String procesarAgregarCancion(
        @Valid @ModelAttribute("cancion") Cancion cancion,
        BindingResult result){
            if (result.hasErrors()) {
                return"agregarCancion";
                
            } 
            servicioCancion.agregarCancion(cancion);
            return "redirect:/canciones";
        }

   @GetMapping("/canciones/formulario/editar/{idCancion}")
public String formularioEditarCancion(@PathVariable Long idCancion, Model model) {
    Cancion cancion = servicioCancion.obtenerCancionPorId(idCancion);
    if (cancion == null) {
        throw new IllegalArgumentException("Id inválido: " + idCancion);
    }
    model.addAttribute("cancion", cancion);
    return "editarCancion";
}

    @PostMapping("/canciones/procesa/editar/{idCancion}")
    public String procesarEditarCancion(
        @PathVariable Long idCancion,
        @Valid @ModelAttribute("cancion") Cancion cancion,
        BindingResult result) {
    
    if (result.hasErrors()) {
        return "editarCancion";
    }
    
     cancion.setId(idCancion);
    servicioCancion.actualizaCancion(cancion);
    return "redirect:/canciones";
    }
}
