package com.ifpb.bancoggi.controllers;

import com.ifpb.bancoggi.entidades.Cliente;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ControllerCliente {

    @RequestMapping("/")
    public boolean comparaEndereco() {
        Cliente cliente = new Cliente();
        cliente.setEndereco("oi");
        return cliente.comparaEndeceo("oi");
    }



}