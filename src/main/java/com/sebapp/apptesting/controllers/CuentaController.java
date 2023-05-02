package com.sebapp.apptesting.controllers;

import com.sebapp.apptesting.DTOs.TransaccionDTO;
import com.sebapp.apptesting.models.Cuenta;
import com.sebapp.apptesting.services.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private ICuentaService iCuentaService;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cuenta cuentaDetalle(@PathVariable Long id){
       return iCuentaService.findById(id);
    }
    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody TransaccionDTO dto){
        iCuentaService.transferir(dto.getCuentaOrigen(), dto.getCuentaDestino(), dto.getMonto(), dto.getBancoId());
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("Fecha: ", LocalDate.now().toString());
        respuesta.put("Estado: ","OK");
        respuesta.put("Mensaje: ","La transferencia fue realizada de manera exitosa!");
        respuesta.put("Transaccion: ",dto);
        return ResponseEntity.ok(respuesta);
    }
}
