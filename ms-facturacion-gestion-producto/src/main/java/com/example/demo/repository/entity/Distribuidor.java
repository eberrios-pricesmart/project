package com.example.demo.repository.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "distribuidor")
public class Distribuidor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ruc")
    private String ruc;
    @Column(name = "rubro")
    private String rubro;
    @Column(name = "areaDistribucion")
    private String areaDistribucion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "nombreContacto")
    private String nombreContacto;
    @Column(name = "apellidoContacto")
    private String apellidoContacto;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getAreaDistribucion() {
        return areaDistribucion;
    }

    public void setAreaDistribucion(String areaDistribucion) {
        this.areaDistribucion = areaDistribucion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getApellidoContacto() {
        return apellidoContacto;
    }

    public void setApellidoContacto(String apellidoContacto) {
        this.apellidoContacto = apellidoContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
