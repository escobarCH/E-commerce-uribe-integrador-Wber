package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidoServicio {

    @Autowired
    private IPedidoRepositorio pedidoRepositorio;

    @Autowired
    private IPedidoMapa pedidoMapa;

    public PedidoDTO guardarPedido(Pedido datosPedido) {

        // Validar monto total
        if (datosPedido.getMontoTotal() == null || datosPedido.getMontoTotal() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "***** El monto total del pedido debe ser mayor que cero *****"
            );
        }

        // Guardar el pedido en el repositorio
        Pedido pedidoGuardado = this.pedidoRepositorio.save(datosPedido);

        if (pedidoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "***** Ocurrió un error al intentar registrar el pedido *****"
            );
        }

        // Convertir a DTO y devolver
        return this.pedidoMapa.convertir_pedido_a_pedidodto(pedidoGuardado);
    }
}
