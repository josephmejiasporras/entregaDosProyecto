/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dl.DAO;

import bl.entidades.Artista;
import bl.entidades.Cancion;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CancionDAO {

    private PreparedStatement getAll;
    private PreparedStatement insert;
    private PreparedStatement getOne;
    private ArtistaDAO artistaDAO;
    private CancionArtistaDAO cancionArtistaDAO;
    private final String queryGetAll = "select * from canciones";
    private final String queryInsert = "Insert into canciones (nombre, fechalanzamiento, ruta) values (?, ?, ?)";

    Connection connection;

    public CancionDAO(Connection cxn) {
        this.connection = cxn;
        this.artistaDAO = new ArtistaDAO(connection);
        this.cancionArtistaDAO = new CancionArtistaDAO(connection);
    }

    public Cancion getById(int id) throws SQLException, MalformedURLException {
        this.getOne = connection.prepareStatement("select * from canciones where idcancion = " + id);
        ResultSet resultado = this.getOne.executeQuery();
        while (resultado.next()) {
            Cancion leido = new Cancion();
            leido.setId(resultado.getInt("idcancion"));
            leido.setNombre(resultado.getString("nombre"));
            leido.setFechaLanzamiento(LocalDate.parse(resultado.getString("fechalanzamiento")));
            leido.setRuta(resultado.getString("ruta"));
            return leido;
        }
        return null;
    }

    public ArrayList<Cancion> findAll() throws SQLException, MalformedURLException {
        this.getAll = connection.prepareStatement(queryGetAll);
        ResultSet resultado = this.getAll.executeQuery();
        ArrayList<Cancion> listaCanciones = new ArrayList<>();
        while (resultado.next()) {
            Cancion leido = new Cancion();
            leido.setId(resultado.getInt("idcancion"));
            leido.setNombre(resultado.getString("nombre"));
            leido.setFechaLanzamiento(LocalDate.parse(resultado.getString("fechalanzamiento")));
            leido.setRuta(resultado.getString("ruta"));

            ArrayList<Integer> idartistas = cancionArtistaDAO.obtenerArtistasCancion(resultado.getInt("idcancion"));

            ArrayList<Artista> artistas = new ArrayList<>();
            for (Integer integer : idartistas) {
                Artista artista = artistaDAO.getById(integer);
                if (artista != null) {
                    artistas.add(artista);
                }
            }
            leido.setArtistas(artistas);
            listaCanciones.add(leido);
        }
        return listaCanciones;
    }

    public Cancion save(Cancion cancion) throws SQLException {
        this.insert = connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
        try {
            int key;
            this.insert.setString(1, cancion.getNombre());
            this.insert.setString(2, cancion.getFechaLanzamiento().toString());
            this.insert.setString(3, cancion.getRuta());
            this.insert.execute();
            ResultSet generatedKeys = insert.getGeneratedKeys();
            while (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
                cancion.setId(key);
                return cancion;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
