package controlador;

import modelo.Union;
import controlador.dao.DataAccessObject;

/**
 *
 * @author caim2
 */
public class UnionDAO extends DataAccessObject<Union> {
    
    private Union union;
    
    public UnionDAO(){
        super(Union.class);
    }
    
    public void guardarU(Integer origen, Integer destino){
        union = new Union(origen, destino);
        save(union);
        union = null;
    }
    
}
