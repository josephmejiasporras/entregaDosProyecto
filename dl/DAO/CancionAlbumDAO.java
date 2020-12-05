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

public class CancionAlbumDAO {

    Connection connection;
    private final String TEMPLATE_QRY_TODOS = "select * from cancionalbum";
    private PreparedStatement queryObtenerTodos;
    private PreparedStatement queryDeleteEntry;
    private PreparedStatement queryObtenerAlbumesID;
    private final String TEMPLATE_CMD_INSERTAR = "Insert into cancionalbum (idcancion, idalbum, numpista)"
            + "values (?,?,?)";
    private PreparedStatement cmdInsertar;

    public CancionAlbumDAO(Connection cxn) {
        this.connection = cxn;
    }

    public boolean save(int idCancion, int IdAlbum, int numPista) throws SQLException {
        this.cmdInsertar = connection.prepareStatement(TEMPLATE_CMD_INSERTAR);
        try {
            this.cmdInsertar.setInt(1, idCancion);
            this.cmdInsertar.setInt(2, IdAlbum);
            this.cmdInsertar.setInt(3, numPista);
            this.cmdInsertar.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean remove(int IdCancion, int IdAlbum) throws SQLException {
        try {
            this.queryDeleteEntry = connection.prepareStatement("DELETE FROM cancionalbum WHERE idalbum = '" + IdAlbum + "' AND idcancion = '" + IdCancion + "'");
            this.queryDeleteEntry.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Integer> obtenerCancionesAlbum(int ID) throws SQLException {
        ArrayList<Integer> idCanciones = new ArrayList<>();
        this.queryObtenerAlbumesID = connection.prepareStatement("SELECT * FROM cancionalbum WHERE idalbum = " + ID + "");
        ResultSet resultado = this.queryObtenerAlbumesID.executeQuery();

        while (resultado.next()) {
            idCanciones.add(resultado.getInt("idcancion"));
        }
        return idCanciones;

    }

}
