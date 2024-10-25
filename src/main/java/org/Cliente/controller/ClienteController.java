package org.Cliente.controller;

import org.Cliente.model.Cliente;
import org.Cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<Cliente>> getClientes(){
       List<Cliente> clientes = clienteService.getClientes();
       return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long idCliente){
        Cliente c = clienteService.getClienteById(idCliente);
        if(c!=null){
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

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
