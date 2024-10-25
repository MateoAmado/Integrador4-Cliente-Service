package org.Cliente.service;


import org.Cliente.dto.CompraDTO;
import org.Cliente.dto.ProductoDTO;
import org.Cliente.dto.ReporteClienteCompraDTO;
import org.Cliente.model.Cliente;
import org.Cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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

    //Genere un reporte donde se indiquen los clientes y el monto total de sus compras
    public List<ReporteClienteCompraDTO> generarReporte() {
        List<Cliente> clientes = this.getClientes();
        List<ReporteClienteCompraDTO> reportes = new ArrayList<>();

        for (Cliente c : clientes) {
            // Llamar a la API de compras para obtener las compras del cliente
            String APICompra = "http://localhost:8026/compras/cliente/" + c.getIdCliente();
            List<CompraDTO> compras = this.obtenerComprasPorCliente(APICompra);

            float montoTotal = 0;

            for (CompraDTO compra : compras) {
                // Llamar a la API de productos para obtener el valor del producto
                String APIProducto = "http://localhost:8070/productos/" + compra.getIdProducto();
                ProductoDTO producto = this.obtenerProductoPorId(APIProducto);

                // Calcular el monto total por cada compra
                montoTotal += producto.getValor() * compra.getCantidad();
            }

            // Crear el reporte para el cliente
            ReporteClienteCompraDTO reporte = new ReporteClienteCompraDTO();
            reporte.setIdCliente(c.getIdCliente());
            reporte.setNombre(c.getNombre());
            reporte.setEmail(c.getEmail());
            reporte.setMontoTotal(montoTotal);

            reportes.add(reporte);
        }

        return reportes;
    }


    private List<CompraDTO> obtenerComprasPorCliente(String APICompra) {
        try {
            // Llamada a la API para obtener las compras del cliente
            ResponseEntity<CompraDTO[]> response = restTemplate.getForEntity(APICompra, CompraDTO[].class);
            CompraDTO[] comprasArray = response.getBody();

            // Convertir el array a una lista y retornarlo
            if (comprasArray != null) {
                return Arrays.asList(comprasArray);
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            // Manejo de errores, podrías registrar el error o lanzar una excepción personalizada
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private ProductoDTO obtenerProductoPorId(String APIProducto) {
        try {
            // Llamada a la API para obtener el producto
            ResponseEntity<ProductoDTO> response = restTemplate.getForEntity(APIProducto, ProductoDTO.class);
            ProductoDTO producto = response.getBody();

            // Retornar el producto si no es nulo
            if (producto != null) {
                return producto;
            } else {
                return new ProductoDTO(); // Retornar un objeto vacío si no se encuentra el producto
            }
        } catch (Exception e) {
            // Manejo de errores, podrías registrar el error o lanzar una excepción personalizada
            e.printStackTrace();
            return new ProductoDTO(); // Retornar un objeto vacío en caso de error
        }
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
