

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;


public class Txt {
	public static final String CABECERA = "RT0001";
	public static final char CR = (char) 0x0D;
	public static final char LF = (char) 0x0A;
	public static final String RC = "" + CR + LF;
	
	static public boolean crearCSV(ArrayList<Registro> listaRegistros, String ruta) {

		FileWriter fichero = null;
		PrintWriter pw = null;
		boolean exito = false;
		System.setProperty("file.encoding","Cp1252");

		try {

			fichero = new FileWriter(ruta);
			pw = new PrintWriter(fichero);
			
			pw.print(CABECERA+RC);

			for (Registro registro : listaRegistros) {
				
					// Fila 1
				
				pw.print(
				        "1" + ";"                               // Tipo
				        + registro.fecha + ";"                  // Fecha
				        + /* registro.NIF + */ ";"              // NIF Responsable
				        + registro.ROPO + ";"                   // ROPO Responsable
				        + registro.operacion + ";"              // operacion
				        + registro.entidad.CIF + ";"            // NIF Destino
				        + registro.entidad.ROPO + ";"           // ROPO Destino
				        + registro.entidad.getNombre() + ";"    // Nombre Destino
				        + registro.entidad.email + ";"          // Email Destino
				        + registro.entidad.telefono + ";"       // Telefono Destino
				        + ";"                                   // Fax
				        + registro.entidad.direccion + ";"      // Dirección Destino
				        + registro.entidad.CP + ";"             // Codigo postal Destino
				        + registro.entidad.pais + ";"           // Pais Destino
				        + registro.entidad.codigoProvincia + ";"// Provincia Destino
				        + registro.entidad.codigoMunicipio + ";"// Municipio Destino

				        + ";"                                   // NIF autorizado
				        + ";"                                   // Nombre
				        + ";"                                   // Apell1
				        + ";"                                   // Apell2
				        + registro.entidad.CIF                  // EmpresaExplotacionUsuarioProfesional
				        + "");
					
					pw.print(RC);
					// Fila 2
					
					pw.print(
							"2" + ";"								// Tipo
							+ registro.producto.numRegistro + ";"	// Nº de registro del producto en MAPA
							+ registro.producto.nombreComer + ";"	// Nombre comercial
							+ registro.producto.lote + ";"			// Lote
							+ registro.producto.capacidad + ";"		// Capacidad
							+ registro.producto.unidad + ";"		// Codigo unidades
							+ registro.producto.cantidad + ";"	// Número de envases
					/*	+ registro.producto.volumenTotal */ + ";"	// Volumen
							+ "N" + ";"								// Es o no importacion paralela
							+ registro.producto.esComun + ";"								// Es o no denominacion comun
							+ ""								// Objeto del tratamiento
							);

					pw.print(RC);
			}
			
			
			/*
			pw.print("hola");
			pw.print(";");
			pw.print("adios");
			pw.println(";");
			pw.print("hola");
			pw.print(";");
			pw.print("adios");
			pw.println(";");
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return exito;
	}

}
