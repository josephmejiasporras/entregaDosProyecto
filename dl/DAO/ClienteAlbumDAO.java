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

public class ClienteAlbumDAO {

    Connection connection;
    private final String TEMPLATE_QRY_TODOS = "select * from clientealbum";
    private PreparedStatement queryObtenerTodos;
    private PreparedStatement queryDeleteEntry;
    private PreparedStatement queryObtenerAlbumesID;
    private final String TEMPLATE_CMD_INSERTAR = "Insert into clientealbum (idcliente, idalbum)"
            + "values (?,?)";
    private PreparedStatement cmdInsertar;

    public ClienteAlbumDAO(Connection cxn) {
        this.connection = cxn;
    }

    public boolean save(int IdAlbum, int IdCliente) throws SQLException {
        this.cmdInsertar = connection.prepareStatement(TEMPLATE_CMD_INSERTAR);
        try {
            this.cmdInsertar.setInt(1, IdCliente);
            this.cmdInsertar.setInt(2, IdAlbum);
            this.cmdInsertar.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean remove(int IdAlbum, int IdCliente) throws SQLException {
        try {
            this.queryDeleteEntry = connection.prepareStatement("DELETE FROM clientealbum WHERE idcliente = '" + IdCliente + "' AND idalbum = '" + IdAlbum + "'");
            this.queryDeleteEntry.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Integer> obtenerPorId(int ID) throws SQLException {
        ArrayList<Integer> idAlbumes = new ArrayList<>();
        this.queryObtenerAlbumesID = connection.prepareStatement("SELECT * FROM clientealbum WHERE idcliente = " + ID + "");
        ResultSet resultado = this.queryObtenerAlbumesID.executeQuery();

        while (resultado.next()) {
            idAlbumes.add(resultado.getInt("idalbum"));
        }
        return idAlbumes;

    }


}
