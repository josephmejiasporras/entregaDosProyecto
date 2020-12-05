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

public class ClienteArtistaDAO {

    Connection connection;
    private final String TEMPLATE_QRY_TODOS = "select * from clienteartista";
    private PreparedStatement queryObtenerTodos;
    private PreparedStatement queryDeleteEntry;
    private PreparedStatement queryObtenerArtistasID;
    private final String TEMPLATE_CMD_INSERTAR = "Insert into clienteartista (idcliente, idartista)"
            + "values (?,?)";
    private PreparedStatement cmdInsertar;

    public ClienteArtistaDAO(Connection cxn) {
        this.connection = cxn;
    }

    public boolean save(int idArtista, int IdCliente) throws SQLException {
        this.cmdInsertar = connection.prepareStatement(TEMPLATE_CMD_INSERTAR);
        try {
            this.cmdInsertar.setInt(1, IdCliente);
            this.cmdInsertar.setInt(2, idArtista);
            this.cmdInsertar.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean remove(int idArtista, int idCliente) throws SQLException {
        try {
            this.queryDeleteEntry = connection.prepareStatement("DELETE FROM clienteartista WHERE idcliente = '" + idCliente + "' AND idartista = '" + idArtista + "'");
            this.queryDeleteEntry.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Integer> obtenerPorId(int ID) throws SQLException {
        ArrayList<Integer> idArtistas = new ArrayList<>();
        this.queryObtenerArtistasID = connection.prepareStatement("SELECT * FROM clienteartista WHERE idcliente = " + ID + "");
        ResultSet resultado = this.queryObtenerArtistasID.executeQuery();

        while (resultado.next()) {
            idArtistas.add(resultado.getInt("idartista"));
        }
        return idArtistas;

    }

}
