package org.Cliente.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.Cliente.model.Cliente;
import org.Cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
public class ClienteController {


    @Autowired
    ClienteService clienteService;

    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping()
    public ResponseEntity<List<Cliente>> getClientes(){
       List<Cliente> clientes = clienteService.getClientes();
       return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un cliente por ID", description = "Devuelve un cliente específico dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long idCliente){
        Cliente c = clienteService.getClienteById(idCliente);
        if(c!=null){
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Agrega un nuevo cliente al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping()
    public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente){
        Cliente savedCliente = clienteService.save(cliente);
        if(savedCliente != null){
            return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Actualizar un cliente existente", description = "Modifica los datos de un cliente dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> putCliente(@RequestBody Cliente cliente, @PathVariable Long idCliente){
        Cliente c = clienteService.getClienteById(idCliente);
        if(c!=null){
            c.setEmail(cliente.getEmail());
            c.setNombre(cliente.getNombre());
            clienteService.save(c);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(c, HttpStatus.NOT_FOUND);

    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente del sistema dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable Long idCliente){
        Cliente c = clienteService.getClienteById(idCliente);
        if(c!=null){
            clienteService.deleteCliente(idCliente);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(c, HttpStatus.NOT_FOUND);
    }

}
