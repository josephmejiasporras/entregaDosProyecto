/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dl.DAO;

import bl.entidades.Administrador;
import bl.entidades.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class AdministradorDAO extends PersonaDAO {
    
    Connection cxn;
    
    public AdministradorDAO(Connection cxn){
        this.cxn = cxn;
    }
    
    public boolean save(Administrador persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Persona> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
