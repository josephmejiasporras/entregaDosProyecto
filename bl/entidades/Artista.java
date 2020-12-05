package bl.entidades;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author capri
 */
public class Artista extends Persona {

    private String pais;
    private LocalDate fechaDefuncion;
    private String nombreArtistico;
    private String descripcion;

    @Override
    public String toString() {
        return "Artista{" + "pais=" + pais + ", fechaDefuncion=" + fechaDefuncion + ", nombreArtistico=" + nombreArtistico + ", descripcion=" + descripcion + '}';
    }
    
    
    

    public Artista() {
    }
    
    public Artista(String pais, LocalDate fechaDefuncion, String nombreArtistico, String sipnopsis, String nombre, String segundoNombre, String apellidos, LocalDate fechaNacimiento) {
        super(nombre, segundoNombre, apellidos, fechaNacimiento);
        this.pais = pais;
        this.fechaDefuncion = fechaDefuncion;
        this.nombreArtistico = nombreArtistico;
        this.descripcion = sipnopsis;
    }

    public Artista(String pais, LocalDate fechaDefuncion, String nombreArtistico, String sipnopsis, String nombre, String segundoNombre, String apellidos, LocalDate fechaNacimiento, int ID) {
        super(nombre, segundoNombre, apellidos, fechaNacimiento, ID);
        this.pais = pais;
        this.fechaDefuncion = fechaDefuncion;
        this.nombreArtistico = nombreArtistico;
        this.descripcion = sipnopsis;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the fechaDefuncion
     */
    public LocalDate getFechaDefuncion() {
        return fechaDefuncion;
    }

    /**
     * @param fechaDefuncion the fechaDefuncion to set
     */
    public void setFechaDefuncion(LocalDate fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    /**
     * @return the nombreArtistico
     */
    public String getNombreArtistico() {
        return nombreArtistico;
    }

    /**
     * @param nombreArtistico the nombreArtistico to set
     */
    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param Descripcion the sipnopsis to set
     */
    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.pais);
        hash = 29 * hash + Objects.hashCode(this.fechaDefuncion);
        hash = 29 * hash + Objects.hashCode(this.nombreArtistico);
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
        final Artista other = (Artista) obj;
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.nombreArtistico, other.nombreArtistico)) {
            return false;
        }
        if (!Objects.equals(this.fechaDefuncion, other.fechaDefuncion)) {
            return false;
        }
        return true;
    }

   
    
    
    

}
