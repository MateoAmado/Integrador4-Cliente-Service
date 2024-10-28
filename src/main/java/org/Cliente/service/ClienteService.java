package org.Cliente.service;


import org.Cliente.model.Cliente;
import org.Cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getClientes(){
       return clienteRepository.getClientes();
    }

    public Cliente getClienteById(Long id){
        return clienteRepository.getClientesByIdCliente(id);
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente putCliente(Long id, Cliente cliente){
       Cliente c = clienteRepository.getClientesByIdCliente(id);
       if(c != null){
           c.setEmail(cliente.getEmail());
           c.setNombre(cliente.getNombre());
           return clienteRepository.save(c);
       }
      return null;
    }


    public Cliente deleteCliente(Long id){
        Cliente c = clienteRepository.getClientesByIdCliente(id);
        if(c!=null){
            clienteRepository.delete(c);
            return c;
        }
        return null;
    }



}
