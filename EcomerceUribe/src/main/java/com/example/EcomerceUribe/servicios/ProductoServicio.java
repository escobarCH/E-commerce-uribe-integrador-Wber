package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio productoRepositorio;

    @Autowired
    private IProductoMapa productoMapa;

    public ProductoDTO guardarProducto(Producto datosProducto) {

        // Validar nombre del producto
        if (datosProducto.getNombre() == null || datosProducto.getNombre().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "***** El nombre del producto no puede estar vacío *****"
            );
        }

        // Guardar producto en la base de datos
        Producto productoGuardado = this.productoRepositorio.save(datosProducto);

        if (productoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "***** error al intentar registrar el producto *****"
            );
        }

        // Convertir a DTO y devolver
        return this.productoMapa.convertir_producto_a_productodto(productoGuardado);
    }
}
