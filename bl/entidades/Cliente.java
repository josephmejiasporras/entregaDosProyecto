/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.entidades;

import java.time.LocalDate;
import java.util.Objects;

public class Cliente extends Persona {

    /*inits*/
    private String pais;
    private String contrasena;
    private String nombreUsuario;
    private String correo;
    private String avatar;

    public Cliente(
            String nombre,
            String segundoNombre,
            String apellidos,
            String nombreUsuario,
            String contrasena,
            String correo,
            LocalDate fechanacimiento,
            String pais,
            String avatar) {
        super(nombre, segundoNombre, apellidos, fechanacimiento);
        this.pais = pais;
        this.contrasena = contrasena;
        this.nombreUsuario = nombreUsuario;
        this.avatar = avatar;
        this.correo = correo;
    }
    
    public Cliente(
            String nombre,
            String segundoNombre,
            String apellidos,
            String nombreUsuario,
            String contrasena,
            String correo,
            LocalDate fechanacimiento,
            String pais,
            int id,
            String avatar) {
        super(nombre, segundoNombre, apellidos, fechanacimiento, id);
        this.pais = pais;
        this.id = id;
        this.contrasena = contrasena;
        this.nombreUsuario = nombreUsuario;
        this.avatar = avatar;
        this.correo = correo;
    }

    public Cliente() {

    }

    @Override
    public String toString() {
        return "Cliente: "
                + " \nID: " + id
                + " \nNombre: " + getNombreCompleto()
                + " \nPais: " + pais
                + " \nnombreUsuario=" + nombreUsuario
                + " \ncorreo=" + correo
                + " \navatar=" + avatar + '}';
    }

    public String getNacionalidad() {
        return pais;
    }

    public void setNacionalidad(String nacionalidad) {
        this.pais = nacionalidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.pais);
        hash = 97 * hash + Objects.hashCode(this.nombreUsuario);
        hash = 97 * hash + Objects.hashCode(this.correo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.contrasena, other.contrasena)) {
            return false;
        }
        if (!Objects.equals(this.nombreUsuario, other.nombreUsuario)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }
    
    

}
