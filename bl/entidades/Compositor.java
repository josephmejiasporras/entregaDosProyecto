/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.entidades;

import java.time.LocalDate;
import java.util.Objects;

public class Compositor extends Persona {

    private String pais;

    public Compositor() {
    }
    
    

    public Compositor(String pais, String nombre, String segundoNombre, String apellidos, LocalDate fechaNacimiento, int ID) {
        super(nombre, segundoNombre, apellidos, fechaNacimiento, ID);
        this.pais = pais;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.pais);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compositor other = (Compositor) obj;
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "pais=" + pais + '}';
    }
    
    
    
    

}
