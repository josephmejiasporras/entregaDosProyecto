/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dl.DAO;

import bl.entidades.Album;
import bl.entidades.Artista;
import bl.entidades.Cancion;
import bl.entidades.Cliente;
import bl.entidades.Playlist;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends PersonaDAO {

    List<Cliente> clientesLista;
    Connection connection;
    ClientePlaylistDAO clientePlaylist;
    ClienteCancionDAO clienteCancion;
    ClienteArtistaDAO clienteArtista;
    ClienteAlbumDAO clienteAlbum;
    CancionDAO cancionDAO;
    AlbumDAO albumDAO;
    ArtistaDAO artistaDAO;
    PlaylistDAO playlistDAO;

    private PreparedStatement cmdInsertar;
    private final String TEMPLATE_CMD_INSERTAR = "Insert into clientes ("
            + "nombre, segundonombre, apellidos, fechanacimiento, pais, avatar, "
            + "nombreUsuario, correo, contrasena)"
            + "values (?,?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSCLIENTE = "select * from clientes";
    private PreparedStatement queryObtenerTodos;
    private PreparedStatement queryUnCliente;
    private PreparedStatement updateNombre;
    private PreparedStatement updateSegundoNombre;
    private PreparedStatement updateApellidos;
    private PreparedStatement updateFechaNacimiento;
    private PreparedStatement updatePais;
    private PreparedStatement updateAvatar;
    private PreparedStatement updateNombreUsuario;
    private PreparedStatement updateCorreo;
    private PreparedStatement updateContrasena;

    public ClienteDAO(Connection link) {
        this.connection = link;
        this.clienteCancion = new ClienteCancionDAO(connection);
        this.cancionDAO = new CancionDAO(connection);
        this.clienteAlbum = new ClienteAlbumDAO(connection);
        this.albumDAO = new AlbumDAO(connection);
        this.clienteArtista = new ClienteArtistaDAO(connection);
        this.artistaDAO = new ArtistaDAO(connection);
        this.clientePlaylist = new ClientePlaylistDAO(connection);
        this.playlistDAO = new PlaylistDAO(connection);
        try {
            this.queryObtenerTodos = connection.prepareStatement(TEMPLATE_QRY_TODOSLOSCLIENTE);

        } catch (SQLException e) {
            System.out.println("true");
        }
    }

    /**
     *
     * @param cliente
     * @return
     * @throws java.sql.SQLException
     */
    public boolean save(Cliente cliente) throws SQLException {

        this.cmdInsertar = connection.prepareStatement(TEMPLATE_CMD_INSERTAR, Statement.RETURN_GENERATED_KEYS);
        try {
            this.cmdInsertar.setString(1, cliente.getNombre());
            this.cmdInsertar.setString(2, cliente.getSegundoNombre());
            this.cmdInsertar.setString(3, cliente.getApellidos());
            this.cmdInsertar.setString(4, cliente.getFechaNacimiento().toString());
            this.cmdInsertar.setString(5, cliente.getNacionalidad());
            this.cmdInsertar.setString(6, cliente.getAvatar());
            this.cmdInsertar.setString(7, cliente.getNombreUsuario());
            this.cmdInsertar.setString(8, cliente.getCorreo());
            this.cmdInsertar.setString(9, cliente.getContrasena());
            this.cmdInsertar.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public List<Cliente> findAll() throws SQLException {
        ResultSet resultado = this.queryObtenerTodos.executeQuery();

        clientesLista = new ArrayList<>();
        while (resultado.next()) {

            Cliente leido = new Cliente();
            leido.setId(resultado.getInt("idcliente"));
            leido.setNombre(resultado.getString("nombre"));
            if (!resultado.getString("segundonombre").isEmpty()) {
                resultado.getString("segundonombre");
            }
            leido.setApellidos(resultado.getString("apellidos"));
            leido.setFechaNacimiento(LocalDate.parse(resultado.getString("fechanacimiento")));
            leido.setNacionalidad(resultado.getString("pais"));
            leido.setNombreUsuario(resultado.getString("nombreUsuario"));
            leido.setCorreo(resultado.getString("correo"));
            leido.setContrasena(resultado.getString("contrasena"));
            clientesLista.add(leido);
        }
        return clientesLista;
    }

    public Cliente getCliente(int ID) throws SQLException {

        this.queryUnCliente = connection.prepareStatement("SELECT * FROM clientes WHERE idcliente = '1'");
        ResultSet resultado = this.queryUnCliente.executeQuery();
        if (resultado.next()) {
            Cliente leido = new Cliente();
            leido.setId(resultado.getInt("idcliente"));
            leido.setNombre(resultado.getString("nombre"));
            if (!resultado.getString("segundonombre").isEmpty()) {
                resultado.getString("segundonombre");
            }
            leido.setApellidos(resultado.getString("apellidos"));
            leido.setFechaNacimiento(LocalDate.parse(resultado.getString("fechanacimiento")));
            leido.setNacionalidad(resultado.getString("pais"));
            leido.setAvatar(resultado.getString("avatar"));
            leido.setNombreUsuario(resultado.getString("nombreUsuario"));
            leido.setCorreo(resultado.getString("correo"));
            leido.setContrasena(resultado.getString("contrasena"));
            return leido;
        }

        return null;
    }

    public boolean updateNombre(int ID, String value) throws SQLException {

        this.updateNombre = connection.prepareStatement("UPDATE clientes SET nombre = '" + value + "' WHERE idcliente = " + ID + "");
        try {
            this.updateNombre.execute();
            System.out.println("Updated Cliente ID: " + ID);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean updateSegundoNombre(int ID, String value) throws SQLException {

        this.updateSegundoNombre = connection.prepareStatement("UPDATE clientes SET segundonombre = '" + value + "' WHERE idcliente = " + ID + "");
        try {
            this.updateSegundoNombre.execute();
            System.out.println("Updated Cliente ID: " + ID);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean updateApellidos(int ID, String value) throws SQLException {

        this.updateApellidos = connection.prepareStatement("UPDATE clientes SET apellidos = '" + value + "' WHERE idcliente = " + ID + "");
        try {
            this.updateApellidos.execute();
            System.out.println("Updated Cliente ID: " + ID);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean updateFechaNacimiento(int ID, LocalDate value) throws SQLException {

        this.updateFechaNacimiento = connection.prepareStatement("UPDATE clientes SET fechanacimiento = '" + value.toString() + "' WHERE idcliente = " + ID + "");
        try {
            this.updateFechaNacimiento.execute();
            System.out.println("Updated Cliente ID: " + ID);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean updatePais(int ID, String value) throws SQLException {

        this.updatePais = connection.prepareStatement("UPDATE clientes SET pais = '" + value + "' WHERE idcliente = " + ID + "");
        try {
            this.updatePais.execute();
            System.out.println("Updated Cliente ID: " + ID);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean updateAvatar(int ID, String value) throws SQLException {

        this.updateAvatar = connection.prepareStatement("UPDATE clientes SET avatar = '" + value + "' WHERE idcliente = " + ID + "");

        try {
            this.updateAvatar.execute();
            System.out.println("Updated Cliente ID: " + ID);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean updateNombreUsuario(int ID, String value) throws SQLException {

        this.updateNombreUsuario = connection.prepareStatement("UPDATE clientes SET nombreusuario = '" + value + "' WHERE idcliente = " + ID + "");
        try {
            this.updateNombreUsuario.execute();
            System.out.println("Updated Cliente ID: " + ID);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean updateCorreo(int ID, String value) throws SQLException {

        this.updateCorreo = connection.prepareStatement("UPDATE clientes SET correo = '" + value + "' WHERE idcliente = " + ID + "");

        try {
            this.updateCorreo.execute();
            System.out.println("Updated Cliente ID: " + ID);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean updateContrasena(int ID, String value) throws SQLException {

        this.updateContrasena = connection.prepareStatement("UPDATE clientes SET contrasena = '" + value + "' WHERE idcliente = " + ID + "");

        try {
            this.updateContrasena.execute();
            System.out.println("Updated Cliente ID: " + ID);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean anadirCancionFavorita(int IdCancion, int IdCliente) throws SQLException {

        return clienteCancion.save(IdCancion, IdCliente);

    }

    public boolean removerCancionFavorita(int IdCancion, int IdCliente) throws SQLException {

        return clienteCancion.remove(IdCancion, IdCliente);

    }

    public ArrayList<Cancion> getCancionesFavoritas(int ID) throws SQLException, MalformedURLException {

        ArrayList<Integer> listaCancion = clienteCancion.obtenerPorId(ID);
        ArrayList<Cancion> canciones = cancionDAO.findAll();
        ArrayList<Cancion> output = new ArrayList<>();
        for (Integer integer : listaCancion) {
            for (Cancion cancion : canciones) {
                if (integer.equals(cancion.getId())) {
                    output.add(cancion);
                }
            }
        }
        return output;

    }

    public boolean anadirAlbumFavorito(int IdAlbum, int IdCliente) throws SQLException {

        return clienteAlbum.save(IdAlbum, IdCliente);

    }

    public boolean removerAlbumFavorito(int IdAlbum, int IdCliente) throws SQLException {

        return clienteAlbum.remove(IdAlbum, IdCliente);

    }

    public ArrayList<Album> getAlbumesFavoritos(int ID) throws SQLException, MalformedURLException {

        ArrayList<Integer> listaAlbum = clienteAlbum.obtenerPorId(ID);
        ArrayList<Album> albumes = albumDAO.findAll();
        ArrayList<Album> output = new ArrayList<>();
        for (Integer integer : listaAlbum) {
            for (Album album : albumes) {
                if (integer.equals(album.getId())) {
                    output.add(album);
                                        
                }
            }
        }
        return output;
    }

    public boolean anadirArtistaFavorito(int IdArtista, int IdCliente) throws SQLException {

        return clienteArtista.save(IdArtista, IdCliente);

    }

    public boolean removerArtistaFavorito(int IdArtista, int IdCliente) throws SQLException {

        return clienteArtista.remove(IdArtista, IdCliente);

    }

    public ArrayList<Artista> getArtistasFavoritos(int ID) throws SQLException {

        ArrayList<Integer> listaArtistas = clienteArtista.obtenerPorId(ID);
        ArrayList<Artista> artistas = artistaDAO.findAll();
        ArrayList<Artista> output = new ArrayList<>();
        for (Integer integer : listaArtistas) {
            for (Artista artista : artistas) {
                if (integer.equals(artista.getId())) {
                    output.add(artista);
                }
            }
        }
        return output;
    }

    public boolean anadirPlaylist(int IdPlaylist, int IdCliente) throws SQLException {

        return clientePlaylist.save(IdPlaylist, IdCliente);

    }

    public boolean removerPlaylist(int IdPlaylist, int IdCliente) throws SQLException {

        return clientePlaylist.remove(IdPlaylist, IdCliente);

    }

    public ArrayList<Playlist> getPlaylistsFavoritos(int ID) throws SQLException {

        ArrayList<Integer> listaArtistas = clientePlaylist.obtenerPorId(ID);
        ArrayList<Playlist> playlists = playlistDAO.findAll();
        ArrayList<Playlist> output = new ArrayList<>();
        for (Integer integer : listaArtistas) {
            for (Playlist playlist : playlists) {
                if (integer.equals(playlist.getId())) {
                    output.add(playlist);
                }
            }
        }
        return output;
    }

}
