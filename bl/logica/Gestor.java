package bl.logica;

import bl.entidades.*;
import dl.DAO.*;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Gestor {

    Connection connection;

    ClienteDAO clienteDAO;
    AdministradorDAO administradorDAO;
    ArtistaDAO artistaDAO;
    CancionDAO cancionDAO;
    CompositorDAO compositorDAO;
    GeneroDAO generoDAO;
    PlaylistDAO playlistDAO;
    AlbumDAO albumDAO;

    public Gestor() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "root");

            this.clienteDAO = new ClienteDAO(this.connection);
            this.administradorDAO = new AdministradorDAO(this.connection);
            this.artistaDAO = new ArtistaDAO(this.connection);
            this.cancionDAO = new CancionDAO(this.connection);
            this.compositorDAO = new CompositorDAO(this.connection);
            this.generoDAO = new GeneroDAO(this.connection);
            this.playlistDAO = new PlaylistDAO(this.connection);
            this.albumDAO = new AlbumDAO(this.connection);
            System.out.println("Connected");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println("Cant connect to db");
            System.out.println(e.getMessage());
        }
    }

    /* crea cliente y la registra en la bd de clientes*/
    public void crearCliente(Cliente cliente) throws SQLException {
        //clientes.add(cliente);
        if (clienteDAO.save(cliente)) {
            System.out.println("sucess");
        } else {
            System.out.println("fuck");
        }
    }

    public void listarClientes() throws SQLException {
        System.out.println("\nClientes");
        for (Cliente cliente : clienteDAO.findAll()) {
            System.out.println(cliente.getNombre());
        }
    }

    public void listarCanciones() throws SQLException, MalformedURLException {
        System.out.println("\nCanciones");
        for (Cancion cancion : cancionDAO.findAll()) {
            System.out.println(cancion.getNombre());
        }
    }

    public void listarArtistas() throws SQLException {
        System.out.println("\nArtistas");
        for (Artista artista : artistaDAO.findAll()) {
            System.out.println(artista.getNombre());
        }
    }

    public void listarAlbumes() throws SQLException, MalformedURLException {
        System.out.println("\nAlbumes");
        for (Album album : albumDAO.findAll()) {
            System.out.println(album.getNombre());

        }
    }

    /* crea cancion y la registra en la bd de canciones*/
    public Cancion crearCancion(Cancion cancion) throws SQLException, MalformedURLException {

        ArrayList<Cancion> lista = cancionDAO.findAll();
        for (Cancion cancionIn : lista) {
            if (cancionIn.equals(cancion)) {
                return cancionIn;
            }
        }
        return cancionDAO.save(cancion);
    }

    public Album crearAlbum(Album album) throws SQLException, MalformedURLException {
        ArrayList<Album> lista = albumDAO.findAll();
        for (Album albumIn : lista) {
            if (albumIn.equals(album)) {
                return albumIn;
            }
        }
        return albumDAO.save(album);
    }

    public void test() throws SQLException {

        System.out.println(clienteDAO.anadirArtistaFavorito(1, 1));

    }

    public Artista crearArtista(Artista artista) throws SQLException {

        ArrayList<Artista> lista = artistaDAO.findAll();
        for (Artista artistaIn : lista) {
            if (artistaIn.equals(artista)) {
                return artistaIn;
            }
        }
        return artistaDAO.save(artista);
    }

    public List<Cliente> getClientes() throws SQLException {
        List<Cliente> listaClientes = clienteDAO.findAll();

        return listaClientes;
    }

    public ArrayList<Playlist> getPlaylistsCliente(int i) throws SQLException {

        return clienteDAO.getPlaylistsFavoritos(i);

    }

    public ArrayList<Album> getAlbumesCliente(int id) throws SQLException, MalformedURLException {
        return clienteDAO.getAlbumesFavoritos(id);
    }

    public ArrayList<Cancion> getCancionesCliente(int id) throws SQLException, MalformedURLException {
        return clienteDAO.getCancionesFavoritas(id);
    }

    public ArrayList<Artista> getArtistasCliente(int id) throws SQLException {
        return clienteDAO.getArtistasFavoritos(id);
    }

    public void addPlaylist(Playlist playlist, int id) throws SQLException {
        int key = playlistDAO.save(playlist);
        if (playlistDAO.save(playlist) != -1) {
            clienteDAO.anadirPlaylist(key, id);
        }

    }

    public void anadirArtistaFavorito(int idCliente, int idArtista) throws SQLException {
        clienteDAO.anadirArtistaFavorito(idArtista, idCliente);
    }

    public void anadirCancionFavorita(int idCancion, int idCliente) throws SQLException {
        clienteDAO.anadirCancionFavorita(idCancion, idCliente);
    }

    public void anadirAlbumFavorito(int idAlbum, int idCliente) throws SQLException {
        clienteDAO.anadirAlbumFavorito(idAlbum, idCliente);
    }

}
