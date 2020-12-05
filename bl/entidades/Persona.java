package bl.entidades;

import java.time.LocalDate;
import java.util.Objects;

public class Persona {

    protected int id;
    protected String nombre;
    protected String segundoNombre;
    protected String apellidos;
    protected LocalDate fechaNacimiento;

    public String getNombreCompleto() {
        return nombre + " " + segundoNombre + " " + apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getAppelidos() {
        return getApellidos();
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Persona() {

    }
    
    public Persona(String nombre, String segundoNombre, String apellidos, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona(String nombre, String segundoNombre, String apellidos, LocalDate fechaNacimiento, int ID) {
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.id = ID;
    }

    public String getApellidos() {
        return apellidos;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.nombre);
        hash = 47 * hash + Objects.hashCode(this.segundoNombre);
        hash = 47 * hash + Objects.hashCode(this.apellidos);
        hash = 47 * hash + Objects.hashCode(this.fechaNacimiento);
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.segundoNombre, other.segundoNombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id + ", nombre=" + nombre + ", segundoNombre=" + segundoNombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
    
    
    

}
