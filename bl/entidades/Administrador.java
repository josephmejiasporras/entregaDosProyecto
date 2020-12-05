/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.entidades;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author capri
 */
public class Administrador extends Persona {
    
    private String nombreUsuario;
    private String avatar;
    private String correo;
    private String contrasena;

    public Administrador() {
    }
    
    

    public Administrador(int id, String nombreUsuario, String avatar, String correo, String contrasena, String nombre, String segundoNombre, String apellidos, LocalDate fechaNacimiento) {
        super(nombre, segundoNombre, apellidos, fechaNacimiento, id);
        this.nombreUsuario = nombreUsuario;
        this.avatar = avatar;
        this.correo = correo;
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

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
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

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Administrador other = (Administrador) obj;
        if (!Objects.equals(this.nombreUsuario, other.nombreUsuario)) {
            return false;
        }
        if (!Objects.equals(this.avatar, other.avatar)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "nombreUsuario=" + nombreUsuario + ", correo=" + correo + '}';
    }

    
   

}
