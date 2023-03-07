package com.example.demo.rest;


import com.example.demo.repository.entity.Distribuidor;
import com.example.demo.service.DistribuidorService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class DistribuidorRestController {

    @Autowired
    private DistribuidorService distribuidorService;

    @GetMapping("/distribuidor")
    @ResponseStatus(HttpStatus.OK)
    public List<Distribuidor> findAll(){
        return distribuidorService.findAll();
    }

    @GetMapping("/distribuidor/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Distribuidor distribuidor = null;
        Map<String, Object> response = new HashMap<>();
        try{
            distribuidor = distribuidorService.findById(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(distribuidor == null){
            response.put("mensaje", "El Distribuidor ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Distribuidor>(distribuidor, HttpStatus.OK);
    }


    @PostMapping("/distribuidor")
    public ResponseEntity<?> create(@Valid @RequestBody Distribuidor distribuidor, BindingResult result){
        Distribuidor distribuidorNew = null;
        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err->"El campo '"+err.getField() + "'"+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            distribuidorNew = distribuidorService.save(distribuidor);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Distribuidor ha sido creado con exito!");
        response.put("cliente", distribuidorNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }


    @PutMapping("/distribuidor/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Distribuidor distribuidor, BindingResult result, @PathVariable Long id){
        Distribuidor distribuidorActual = distribuidorService.findById(id);
        Distribuidor distribuidorUpdated = null;
        Map<String,Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err->"El campo '"+err.getField() + "'"+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(distribuidor == null){
            response.put("mensaje", "El Distribuidor ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            distribuidorActual.setNombre(distribuidor.getNombre());
            distribuidorActual.setRubro(distribuidor.getRubro());
            distribuidorActual.setRubro(distribuidor.getRubro());
            distribuidorActual.setAreaDistribucion(distribuidor.getAreaDistribucion());
            distribuidorActual.setDireccion(distribuidor.getDireccion());
            distribuidorActual.setNombreContacto(distribuidor.getNombreContacto());
            distribuidorActual.setApellidoContacto(distribuidor.getApellidoContacto());
            distribuidorActual.setTelefono(distribuidor.getTelefono());
            distribuidorActual.setCorreo(distribuidor.getCorreo());

            distribuidorUpdated= distribuidorService.save(distribuidorActual);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Distribuidor ha sido modificado con exito!");
        response.put("distribuidor", distribuidorUpdated);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/distribuidor/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try{
            distribuidorService.delete(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El distribuidor fue eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}
