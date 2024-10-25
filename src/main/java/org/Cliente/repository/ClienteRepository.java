package org.Cliente.repository;

import org.Cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    @Query("SELECT c from Cliente c")
    List<Cliente> getClientes();

    @Query("select c from Cliente c where c.idCliente =:id")
    Cliente getClientesByIdCliente(Long id);

}
