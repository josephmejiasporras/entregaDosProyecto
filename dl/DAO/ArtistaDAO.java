/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dl.DAO;

import bl.entidades.Artista;
import bl.entidades.Cancion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class ArtistaDAO {
    
    private PreparedStatement getAll;
    private PreparedStatement insert;
    private PreparedStatement getOne;
    private final String queryGetAll = "select * from artistas";
    private final String queryInsert = "Insert into artistas (nombre, segundonombre, apellidos, "
            + "nombreartistico, fechanacimiento, fechadefuncion, pais, descripcion) values (?, ?, ?, ?, ?, ?, ?, ?)";

    Connection cxn;

    public ArtistaDAO(Connection cxn) {
        this.cxn = cxn;
    }

    public Artista getById(int id) throws SQLException {
        this.getOne = cxn.prepareStatement("select * from artistas where idartista = " + id);
        ResultSet resultado = this.getOne.executeQuery();
        while (resultado.next()) {
            Artista leido = new Artista();
            leido.setId(resultado.getInt("idartista"));
            leido.setNombre(resultado.getString("nombre"));
            if (!resultado.getString("segundonombre").isEmpty()) {
                resultado.getString("segundonombre");
            }
            leido.setApellidos(resultado.getString("apellidos"));
            leido.setNombreArtistico(resultado.getString("nombreartistico"));
            leido.setFechaNacimiento(LocalDate.parse(resultado.getString("fechanacimiento")));
            leido.setFechaDefuncion(LocalDate.parse(resultado.getString("fechadefuncion")));
            leido.setPais(resultado.getString("pais"));
            leido.setDescripcion(resultado.getString("descripcion"));
            return leido;
        }
        return null;
    }

    public ArrayList<Artista> findAll() throws SQLException {
        this.getAll = cxn.prepareStatement(queryGetAll);
        ResultSet resultado = this.getAll.executeQuery();
        ArrayList<Artista> listaClientes = new ArrayList<>();
        while (resultado.next()) {
            Artista leido = new Artista();
            leido.setId(resultado.getInt("idartista"));
            leido.setNombre(resultado.getString("nombre"));
            if (!resultado.getString("segundonombre").isEmpty() || resultado.getString("segundonombre")!=null) {
                resultado.getString("segundonombre");
            }
            leido.setApellidos(resultado.getString("apellidos"));
            leido.setNombreArtistico(resultado.getString("nombreartistico"));
            //leido.setFechaNacimiento(LocalDate.parse(resultado.getString("fechanacimiento")));
            //leido.setFechaDefuncion(LocalDate.parse(resultado.getString("fechadefuncion")));
            leido.setPais(resultado.getString("pais"));
            leido.setDescripcion(resultado.getString("descripcion"));
            listaClientes.add(leido);
        }
        return listaClientes;
    }

    public Artista save(Artista artista) throws SQLException {
        this.insert = cxn.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
        try {
            int key;
            this.insert.setString(1, artista.getNombre());
            this.insert.setString(2, artista.getSegundoNombre());
            this.insert.setString(3, artista.getApellidos());
            this.insert.setString(4, artista.getNombreArtistico());
            this.insert.setString(5, "");
            this.insert.setString(6, "");
            this.insert.setString(7, artista.getPais());
            this.insert.setString(8, artista.getDescripcion());
            this.insert.execute();
            ResultSet generatedKeys = insert.getGeneratedKeys();
            while (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
                artista.setId(key);
                return artista;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<Cancion> getCanciones(int id){
        
        return null;
    }

}
