/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dl.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientePlaylistDAO {

    Connection connection;
    private final String TEMPLATE_QRY_TODOS = "select * from clienteplaylist";
    private PreparedStatement queryObtenerTodos;
    private PreparedStatement queryDeleteEntry;
    private PreparedStatement queryObtenerPlaylistsID;
    private final String TEMPLATE_CMD_INSERTAR = "Insert into clienteplaylist (idcliente, idplaylist)"
            + "values (?,?)";
    private PreparedStatement cmdInsertar;

    public ClientePlaylistDAO(Connection cxn) {
        this.connection = cxn;
    }

    public boolean save(int idPlaylist, int IdCliente) throws SQLException {
        this.cmdInsertar = connection.prepareStatement(TEMPLATE_CMD_INSERTAR);
        try {
            this.cmdInsertar.setInt(1, IdCliente);
            this.cmdInsertar.setInt(2, idPlaylist);
            this.cmdInsertar.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean remove(int idPlaylist, int idCliente) throws SQLException {
        try {
            this.queryDeleteEntry = connection.prepareStatement("DELETE FROM clienteplaylist WHERE idcliente = '" + idCliente + "' AND idplaylist = '" + idPlaylist + "'");
            this.queryDeleteEntry.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Integer> obtenerPorId(int ID) throws SQLException {
        ArrayList<Integer> idPlaylists = new ArrayList<>();
        this.queryObtenerPlaylistsID = connection.prepareStatement("SELECT * FROM clienteplaylist WHERE idcliente = " + ID + "");
        ResultSet resultado = this.queryObtenerPlaylistsID.executeQuery();

        while (resultado.next()) {
            idPlaylists.add(resultado.getInt("idplaylist"));
        }
        return idPlaylists;

    }

}
