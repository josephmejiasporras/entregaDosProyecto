package bl.entidades;

import java.time.LocalDate;
import java.util.Objects;

public class Playlist {

    private int id;
    private String nombre;
    private LocalDate fechaCreacion;

    public Playlist() {
    }
    
    public Playlist(String nombre) {
        this.nombre = nombre;
        this.fechaCreacion = LocalDate.now();
    }

    public Playlist(int ID, String nombre, LocalDate fechaCreacion) {
        this.id = ID;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlaylistId() {
        return null;
    }

    /**
     * @return the ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param ID the ID to set
     */
    public void setId(int ID) {
        this.id = ID;
    }

    /**
     * @return the fechaCreacion
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Playlist{" + "id=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + '}';
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.fechaCreacion);
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
        final Playlist other = (Playlist) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        return true;
    }
    
    

}
