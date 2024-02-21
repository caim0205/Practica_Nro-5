package controlador;

import modelo.Union;
import controlador.TDA.grafos.DibujarGrafo;
import controlador.TDA.grafos.GrafoEtiquetadoDirigido;
import controlador.TDA.listas.LinkedList;
import controlador.util.Utilidades;
import java.io.File;
import javax.swing.JOptionPane;
import modelo.Estacion;

/**
 *
 * @author caim2
 */
public class ControladorNodos {

    private final EstacionDAO dao;
    private final UnionDAO daoUnion;
    private GrafoEtiquetadoDirigido<Estacion> grafo;
    private LinkedList<Estacion> estaciones;

    public ControladorNodos() {
        dao = new EstacionDAO();
        daoUnion = new UnionDAO();
    }

    public void grafo() {
        try {

            etiquetarGrafo();

            DibujarGrafo dg = new DibujarGrafo();
            dg.crearArchivo(grafo);

            String os = Utilidades.getOS();
            String dir = Utilidades.getDirProject();

            if (os.contains("Windows")) {
                Utilidades.abrirNavegadorPredeterminadoWindows(dir + File.separatorChar + "d3" + File.separatorChar + "grafo.html");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public void etiquetarGrafo() throws Exception {

        estaciones = dao.listAll();
        LinkedList<Union> uniones = daoUnion.listAll();

        Integer tamanio = dao.listAll().getSize();

        grafo = new GrafoEtiquetadoDirigido<>(tamanio, Estacion.class);

        if (tamanio > 0) {
            grafo = new GrafoEtiquetadoDirigido(tamanio, Estacion.class);
            for (int i = 0; i < tamanio; i++) {
                grafo.etiquetarVertice((i + 1), estaciones.get(i));
            }

        }
        
        for (int i = 0; i < uniones.getSize(); i++) {

            Union union = uniones.get(i);

            Double peso = Utilidades.distanciaDosPuntos(estaciones.get(union.getOrigen()).getLatitud(),
                    estaciones.get(union.getOrigen()).getLongitud(),
                    estaciones.get(union.getDestino()).getLatitud(),
                    estaciones.get(union.getDestino()).getLongitud());

            grafo.insertarAristaE(estaciones.get(union.getOrigen()),
                    estaciones.get(union.getDestino()),
                    peso);

        }

    }

    public void agregarUnion(int o, int d) throws Exception {
        
        etiquetarGrafo();

        if (!grafo.existe_arista(o + 1, d + 1)) {
            double peso = Utilidades.distanciaDosPuntos(estaciones.get(o).getLatitud(),
                    estaciones.get(o).getLongitud(),
                    estaciones.get(d).getLatitud(),
                    estaciones.get(d).getLongitud());

            grafo.insertarAristaE(estaciones.get(o), estaciones.get(d), peso);
            
            daoUnion.guardarU(o, d);
        }
        
        etiquetarGrafo();
        
    }

    public EstacionDAO getDao() {
        return dao;
    }

    public GrafoEtiquetadoDirigido<Estacion> getGrafo() {
        try {
            etiquetarGrafo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return grafo;
    }

}
