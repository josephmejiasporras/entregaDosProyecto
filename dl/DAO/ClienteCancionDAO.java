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

public class ClienteCancionDAO {

    Connection connection;
    private final String TEMPLATE_QRY_TODOS = "select * from clientecancion";
    private PreparedStatement queryObtenerTodos;
    private PreparedStatement queryDeleteEntry;
    private PreparedStatement queryObtenerCancionesID;
    private final String TEMPLATE_CMD_INSERTAR = "Insert into clientecancion (idcliente, idcancion)"
            + "values (?,?)";
    private PreparedStatement cmdInsertar;

    public ClienteCancionDAO(Connection cxn) {
        this.connection = cxn;
    }

    public boolean save(int IdCancion, int IdCliente) throws SQLException {
        this.cmdInsertar = connection.prepareStatement(TEMPLATE_CMD_INSERTAR);
        try {
            this.cmdInsertar.setInt(1, IdCliente);
            this.cmdInsertar.setInt(2, IdCancion);
            this.cmdInsertar.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean remove(int IdCancion, int IdCliente) throws SQLException {
        try {
            this.queryDeleteEntry = connection.prepareStatement("DELETE FROM clientecancion WHERE idcliente = '" + IdCliente + "' AND idcancion = '" + IdCancion + "'");
            this.queryDeleteEntry.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Integer> obtenerPorId(int ID) throws SQLException {
        ArrayList<Integer> idCanciones = new ArrayList<>();
        this.queryObtenerCancionesID = connection.prepareStatement("SELECT * FROM clientecancion WHERE idcliente = " + ID + "");
        ResultSet resultado = this.queryObtenerCancionesID.executeQuery();

        while (resultado.next()) {
            idCanciones.add(resultado.getInt("idcancion"));
        }
        return idCanciones;

    }

}
