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

public class CancionArtistaDAO {

    Connection connection;
    private final String TEMPLATE_QRY_TODOS = "select * from cancionartista";
    private PreparedStatement queryObtenerTodos;
    private PreparedStatement queryDeleteEntry;
    private PreparedStatement queryObtenerArtistasID;
    private final String TEMPLATE_CMD_INSERTAR = "Insert into cancionartista (idcancion, idartista)"
            + "values (?,?)";
    private PreparedStatement cmdInsertar;

    public CancionArtistaDAO(Connection cxn) {
        this.connection = cxn;
    }

    public boolean save(int idCancion, int idArtista) throws SQLException {

        this.cmdInsertar = connection.prepareStatement(TEMPLATE_CMD_INSERTAR);
        try {
            this.cmdInsertar.setInt(1, idCancion);
            this.cmdInsertar.setInt(2, idArtista);
            this.cmdInsertar.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean remove(int IdCancion, int idArtista) throws SQLException {
        try {
            this.queryDeleteEntry = connection.prepareStatement("DELETE FROM cancionartista WHERE idartista = '" + idArtista + "' AND idcancion = '" + IdCancion + "'");
            this.queryDeleteEntry.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Integer> obtenerArtistasCancion(int idCancion) throws SQLException {
        System.out.println("ksjdnfksjd");
        ArrayList<Integer> idArtistas = new ArrayList<>();
        this.queryObtenerArtistasID = connection.prepareStatement("SELECT * FROM cancionartista WHERE idcancion = " + idCancion + "");
        ResultSet resultado = this.queryObtenerArtistasID.executeQuery();

        while (resultado.next()) {
            idArtistas.add(resultado.getInt("idartista"));
        }
        return idArtistas;

    }

}
