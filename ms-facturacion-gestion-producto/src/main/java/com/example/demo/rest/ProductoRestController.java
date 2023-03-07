package com.example.demo.rest;

import com.example.demo.repository.entity.Distribuidor;
import com.example.demo.repository.entity.Producto;
import com.example.demo.service.DistribuidorService;
import com.example.demo.service.ProductoService;
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
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DistribuidorService distribuidorService;

    @GetMapping("/producto")
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> findAll() {
        return productoService.findAllProductos();
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Producto producto = null;
        Map<String, Object> response = new HashMap<>();
        try{
            producto = productoService.findById(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(producto == null){
            response.put("mensaje", "El Producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

    @PostMapping("/producto")
    public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result){

        Producto productoNew = null;
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
            productoNew = productoService.save(producto);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Producto ha sido creado con exito!");
        response.put("cliente", productoNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id){

        Producto productoActual = productoService.findById(id);
        Distribuidor distribuidor = distribuidorService.findById(producto.getDistribuidor().getId());
        Producto productoUpdated = null;
        Map<String,Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err->"El campo '"+err.getField() + "'"+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(productoActual == null){
            response.put("mensaje", "Error: no se pudo editar, el producto ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            productoActual.setNombre(producto.getNombre());
            productoActual.setPrecio(producto.getPrecio());
            productoActual.setDistribuidor(distribuidor);
            productoActual.setStock(producto.getStock());
            productoActual.setMarca(producto.getMarca());
            productoActual.setDescripcion(producto.getDescripcion());

            productoUpdated = productoService.save(productoActual);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar el producto en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Producto ha sido modificado con exito!");
        response.put("cliente", productoUpdated);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try{
            productoService.delete(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el producto de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El producto fue eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}
