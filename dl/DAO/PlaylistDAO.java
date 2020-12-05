/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dl.DAO;

import bl.entidades.Cancion;
import bl.entidades.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaylistDAO {

    private PreparedStatement getAll;
    private PreparedStatement insert;
    private PreparedStatement getOne;
    private final String queryGetAll = "select * from playlists";
    private final String queryInsert = "Insert into playlists (nombre, fechacreacion) values (?, ?)";

    Connection connection;

    public PlaylistDAO(Connection cxn) {
        this.connection = cxn;
    }

    public Playlist getById(int id) throws SQLException {
        this.getOne = connection.prepareStatement("select * from playlists where idplaylist = " + id);
        ResultSet resultado = this.getOne.executeQuery();
        while (resultado.next()) {
            Playlist leido = new Playlist();
            leido.setId(resultado.getInt("idplaylist"));
            leido.setNombre(resultado.getString("nombre"));
            leido.setFechaCreacion(LocalDate.parse(resultado.getString("fechacreacion")));
            return leido;
        }
        return null;
    }

    public ArrayList<Playlist> findAll() throws SQLException {
        this.getAll = connection.prepareStatement(queryGetAll);
        ResultSet resultado = this.getAll.executeQuery();
        ArrayList<Playlist> listaPlaylists = new ArrayList<>();
        while (resultado.next()) {
            Playlist leido = new Playlist();
            leido.setId(resultado.getInt("idplaylist"));
            leido.setNombre(resultado.getString("nombre"));
            leido.setFechaCreacion(LocalDate.parse(resultado.getString("fechacreacion")));
            listaPlaylists.add(leido);
        }
        return listaPlaylists;
    }

    public int save(Playlist playlist) throws SQLException {
        int key;
        this.insert = connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
        try {
            this.insert.setString(1, playlist.getNombre());
            this.insert.setString(2, playlist.getFechaCreacion().toString());
            this.insert.execute();
            ResultSet generatedKeys = insert.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
                return key;
            }
            return -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public ArrayList<Cancion> getCanciones(int id) {

        return null;
    }

}
