/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dl.DAO;

import bl.entidades.Compositor;
import bl.entidades.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class CompositorDAO extends PersonaDAO  {
    
    Connection cxn; 
    
    public CompositorDAO(Connection cxn){
        this.cxn = cxn;
    }

    public boolean save(Compositor persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    
    public ArrayList<Persona> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
