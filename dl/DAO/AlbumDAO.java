/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dl.DAO;

import bl.entidades.Album;
import bl.entidades.Cancion;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class AlbumDAO {

    private PreparedStatement getAll;
    private PreparedStatement insert;
    private PreparedStatement getOne;
    private final String queryGetAll = "select * from albumes";
    private final String queryInsert = "Insert into albumes (nombre, fechalanzamiento) values (?, ?)";
    private final CancionAlbumDAO cancionAlbumDAO;
    private final CancionDAO cancionDAO;

    Connection cxn;

    public AlbumDAO(Connection cxn) {
        this.cxn = cxn;
        this.cancionAlbumDAO = new CancionAlbumDAO(cxn);
        this.cancionDAO = new CancionDAO(cxn);
    }

    public Album getById(int id) throws SQLException {
        this.getOne = cxn.prepareStatement("select * from canciones where idalbum = " + id);
        ResultSet resultado = this.getOne.executeQuery();

        while (resultado.next()) {
            Album leido = new Album();
            leido.setId(resultado.getInt("idalbum"));
            leido.setNombre(resultado.getString("nombre"));
            leido.setFechaLanzamiento(LocalDate.parse(resultado.getString("fechalanzamiento")));
            return leido;
        }
        return null;
    }

    public ArrayList<Album> findAll() throws SQLException, MalformedURLException {
        this.getAll = cxn.prepareStatement(queryGetAll);
        ResultSet resultado = this.getAll.executeQuery();
        ArrayList<Album> listaClientes = new ArrayList<>();
        while (resultado.next()) {
            Album leido = new Album();
            leido.setId(resultado.getInt("idalbum"));
            leido.setNombre(resultado.getString("nombre"));
            //leido.setFechaLanzamiento(LocalDate.parse(resultado.getString("fechalanzamiento")));
            leido.setLista(getCanciones(resultado.getInt("idalbum")));
            listaClientes.add(leido);
        }
        return listaClientes;
    }

    public Album save(Album album) throws SQLException {
        this.insert = cxn.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);

        try {
            int key;
            this.insert.setString(1, album.getNombre());
            this.insert.setString(2, "");
            this.insert.execute();
            ResultSet generatedKeys = insert.getGeneratedKeys();
            while (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
                album.setId(key);
                return album;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Cancion> getCanciones(int id) throws SQLException, MalformedURLException {

        ArrayList<Integer> listaIds = cancionAlbumDAO.obtenerCancionesAlbum(id);
        ArrayList<Cancion> canciones = new ArrayList<>();
        for (Integer integer : listaIds) {
            canciones.add(cancionDAO.getById(integer));
        }
        return canciones;
    }

    public boolean addCancion(int idCancion, int IDalbum, int numPista) throws SQLException {

        return cancionAlbumDAO.save(idCancion, IDalbum, numPista);

    }

}
