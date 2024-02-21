package vista.util;

import controlador.EstacionDAO;
import javax.swing.JComboBox;
import modelo.Estacion;

/**
 *
 * @author caim2
 */
public class UtilCombo {
    
    public static void combo(JComboBox cbx){
        
        Estacion[] estaciones = new EstacionDAO().listAll().toArray();
        cbx.removeAllItems();
        for(Estacion estacion : estaciones){
            cbx.addItem(estacion);
            
        }
        
    }
}
