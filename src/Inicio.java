import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Inicio {

    static ArrayList<Registro> compras = null;
    static ArrayList<Registro> ventas = null;
    static ArrayList<Municipio> listaMunicipios = null;
    static HashMap<String, String> listaProductos = null;

    static final String RUTA_FICHEROS = "Ficheros";
    static final String RUTA_HISTORICO = "Historico";
    static final String RUTA_CSVS = "CSVs";

    static final String RUTA_MUNICIPIOS = "Docs/11codmun.xls";
    static final String RUTA_PRODUCTOS = "Docs/ProductosAutorizados.xls";

    public static void main(String[] args) {

        listaMunicipios = LeerExcel.leerMunicipios(new File(RUTA_MUNICIPIOS));
        listaProductos = LeerExcel.leerProductos(new File(RUTA_PRODUCTOS));

        File carpetaFicheros = new File(RUTA_FICHEROS);
        File carpetaHistorico = new File(RUTA_HISTORICO);
        File carpetaCSVs = new File(RUTA_CSVS);

        carpetaHistorico.mkdirs();
        carpetaCSVs.mkdirs();

        File[] ficheros = carpetaFicheros.listFiles();

        if (ficheros == null || ficheros.length == 0) {
            System.out.println("No hay ficheros Excel en la carpeta " + RUTA_FICHEROS);
            return;
        }

        for (File fichero : ficheros) {

            if (!fichero.isFile()) {
                continue;
            }

            String nombre = fichero.getName().toLowerCase();

            if (nombre.contains("ventas")) {
                ventas = LeerExcel.leerRegistros(fichero, 2);
            }

            if (nombre.contains("compras")) {
                compras = LeerExcel.leerRegistros(fichero, 11);
            }
        }

        if (compras != null) {
            for (Registro registro : compras) {
                Municipio.getCodigoMunicipio(registro);
            }
        }

        if (ventas != null) {
            for (Registro registro : ventas) {
                Municipio.getCodigoMunicipio(registro);
            }
        }

        String fechaHora = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());

        if (ventas != null && !ventas.isEmpty()) {
            Txt.crearCSV(
                    ventas,
                    RUTA_CSVS + "/CSV Ventas " + fechaHora + ".csv"
            );
        }

        if (compras != null && !compras.isEmpty()) {
            Txt.crearCSV(
                    compras,
                    RUTA_CSVS + "/CSV Compras " + fechaHora + ".csv"
            );
        }

        for (File fichero : ficheros) {

            if (!fichero.isFile()) {
                continue;
            }

            File destino = new File(carpetaHistorico, fichero.getName());

            if (!fichero.renameTo(destino)) {
                System.out.println("No se pudo mover a Historico: " + fichero.getName());
            }
        }

        String mensaje = ""
                + "Proceso finalizado correctamente.\n\n"
                + "Ventas generadas: " + (ventas == null ? 0 : ventas.size()) + "\n"
                + "Compras generadas: " + (compras == null ? 0 : compras.size()) + "\n\n";

        JOptionPane.showMessageDialog(
                null,
                mensaje,
                "RETO - Generación finalizada",
                JOptionPane.INFORMATION_MESSAGE
        );

        try {
            Desktop.getDesktop().open(carpetaCSVs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo abrir la carpeta CSVs:\n" + e.getMessage(),
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}

