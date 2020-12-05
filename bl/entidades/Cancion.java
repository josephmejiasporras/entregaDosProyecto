package bl.entidades;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cancion {

    private int id;
    private String nombre;
    private String ruta;
    private LocalDate fechaLanzamiento;
    private double duracionSegundos;
    private double duracionMinutos;
    private String duracion = "";
    private Image imagen;
    private File file;
    private ImageView imageview;
    private ArrayList<Artista> artistas = new ArrayList<>();
    private String listaArtistas = "";

    public Cancion() {

    }

    public void setRuta(String ruta) throws MalformedURLException {
        this.ruta = ruta;
    }

    public Cancion(String nombre, String path, int idCancion) throws MalformedURLException {
        this.nombre = nombre;
        this.ruta = path;
        this.file = new File(path);
        this.id = idCancion;
    }

    public Cancion(String name, Genero genero, String ruta) throws MalformedURLException {
        this.nombre = name;
        this.ruta = ruta;
        this.file = new File(ruta);
    }

    /**
     * @return the artistas
     */
    public ArrayList<Artista> getArtistas() {
        return artistas;
    }

    /**
     * @param artistas the artistas to set
     */
    public void setArtistas(ArrayList<Artista> artistas) {
        this.artistas = artistas;
        System.out.println(artistas.size());
        for (int i = 0; i < artistas.size(); i++) {
            this.setListaArtistas(this.getListaArtistas() + artistas.get(i).getNombreArtistico() + " ");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public String printing() {
        return "Cancion{" + "idCancion="
                + id + ", nombre="
                + nombre + ", artista=" + ", duracionSegundos="
                + duracionSegundos + ", duracionMinutos="
                + duracionMinutos + ", duracion="
                + duracion + ", imagen="
                + imagen + ", ruta="
                + ruta + ", file="
                + file + ", imageview="
                + imageview + ", compositor="
                + fechaLanzamiento.toString() + '}';
    }

    @Override
    public String toString() {
        return "Cancion{" + "nombre=" + nombre + ", fechaLanzamiento=" + fechaLanzamiento + ", duracion=" + duracion + ", imagen=" + imagen + ", listaArtistas=" + getListaArtistas() + '}';
    }

    public String toStream() {
        try {
            return this.file.toURI().toURL().toExternalForm();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cancion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

        imageview = new ImageView(imagen);

    }

    /**
     * @return the imageview
     */
    public ImageView getImageview() {
        return imageview;
    }

    /**
     * @return the idCancion
     */
    public int getId() {
        return id;
    }

    /**
     * @param idCancion the idCancion to set
     */
    public void setId(int idCancion) {
        this.id = idCancion;
    }

    /**
     * @return the duracionSegundos
     */
    public double getDuracionSegundos() {
        return duracionSegundos;
    }

    /**
     * @param duracionSegundos the duracionSegundos to set
     */
    public void setDuracionSegundos(double duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    /**
     * @return the duracionMinutos
     */
    public double getDuracionMinutos() {
        return duracionMinutos;
    }

    /**
     * @param duracionMinutos the duracionMinutos to set
     */
    public void setDuracionMinutos(double duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
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
     * @return the listaArtistas
     */
    public String getListaArtistas() {
        return listaArtistas;
    }

    /**
     * @param listaArtistas the listaArtistas to set
     */
    public void setListaArtistas(String listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.ruta);
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
        final Cancion other = (Cancion) obj;
        if (!Objects.equals(this.ruta, other.ruta)) {
            return false;
        }
        return true;
    }
    
    
    

}
