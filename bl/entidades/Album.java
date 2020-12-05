package bl.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import javafx.scene.image.Image;

public class Album {

    private int id;
    private String nombre;
    private LocalDate fechaLanzamiento;
    private Image imagen;
    private ArrayList<Cancion> lista = new ArrayList<>();

    public Album(String nombre, LocalDate fechaLanzamiento) {
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Album(int id, String nombre, LocalDate fechaLanzamiento, Image imagen) {
        this.id = id;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagen = imagen;
    }

    public Album() {
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdAlbum() {
        return null;
    }

    /**
     * @return the fechaLanzamiento
     */
    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    /**
     * @param fechaLanzamiento the fechaLanzamiento to set
     */
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * @return the imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
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
     * @return the lista
     */
    public ArrayList<Cancion> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ArrayList<Cancion> lista) {
        this.lista = lista;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.nombre);
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
        final Album other = (Album) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", nombre=" + nombre + ", fechaLanzamiento=" + fechaLanzamiento + '}';
    }
    
    
    
    

}
