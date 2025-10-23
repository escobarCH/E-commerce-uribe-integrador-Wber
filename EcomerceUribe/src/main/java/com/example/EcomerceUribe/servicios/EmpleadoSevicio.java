package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmpleadoSevicio {

    @Autowired
    private IEmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private IEmpleadoMapa empleadoMapa;

    public EmpleadoDTO registrarEmpleado(Empleado nuevoEmpleado) {

        if (nuevoEmpleado.getCargo() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe especificar el cargo del empleado."
            );
        }

        Empleado empleadoGuardado = empleadoRepositorio.save(nuevoEmpleado);

        if (empleadoGuardado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ocurrió un error al intentar guardar el empleado."
            );
        }

        return empleadoMapa.convertir_empleado_a_empleadodto(empleadoGuardado);
    }

}

