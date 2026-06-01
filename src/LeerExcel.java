

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeMap;
import java.util.TreeSet;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.biff.WorkbookMethods;
import jxl.read.biff.BiffException;

public class LeerExcel {


	
	public static ArrayList<Registro> leerRegistros(File archivoFuente, int operacion){
		
		WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setEncoding("Cp1252");
        wbSettings.setLocale(new Locale("es", "ES"));
        wbSettings.setExcelDisplayLanguage("ES"); 
        wbSettings.setExcelRegionalSettings("ES"); 
        wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
        
        String campos[];
        
        ArrayList<Registro> listaRegistros = new ArrayList<Registro>();
        
        Workbook archivoExcel;
		try {
			archivoExcel = Workbook.getWorkbook(archivoFuente, wbSettings);
			
	        Sheet hoja = archivoExcel.getSheet(0);
	        
	        //	Obtiene los nombres
	        int numColumnas = 0;
	        int numFilas = 0;
	        
	        numColumnas = hoja.getColumns();
	        numFilas = hoja.getRows();
	        
	        		/*
	        while(!hoja.getCell(numColumnas,0).getContents().toString().equals(null)){
	        	numColumnas++;
	        	System.out.println(numColumnas);
	        }
	        
	        while(!hoja.getCell(0,numFilas).getContents().toString().equals("")){
	        	numFilas++;
	        }
	        */
	        
	       System.out.println("Num filas = " + numFilas + ". Num columnas = " + numColumnas);
	        
	        campos = new String[numColumnas];
	        
	        Registro registro;
	        
	        //	Tabla de todos los documentos
	        for(int fila=1;fila<numFilas;fila++){
	        	
	        	registro = new Registro();
	        	
	        	registro.operacion = operacion;
	        	
	        	// Obtener fecha en formato texto
	        	Date date = ((DateCell) hoja.getCell(1,fila)).getDate();
	            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	            registro.fecha = formatter.format(date);
	            
	        	String campoProducto = hoja.getCell(2,fila).getContents().toString();
	        	String lote = hoja.getCell(4,fila).getContents().toString();
	        	int cantidad = Integer.valueOf(hoja.getCell(5,fila).getContents().toString());
	        	
	        	Producto producto = new Producto(campoProducto, lote, cantidad);
	        	registro.producto = producto;
	        	
	        	Entidad entidad = new Entidad();
	        	entidad.CIF = hoja.getCell(6,fila).getContents().toString();
	        	entidad.ROPO = hoja.getCell(16,fila).getContents().toString();
	        	entidad.setNombre(hoja.getCell(7,fila).getContents().toString());
	        	entidad.direccion = hoja.getCell(8,fila).getContents().toString();
	        	
	        	
	        	//	Primero obtenemos el codigo postal, luego la provincia, para poder acotar la búsqueda del municipio
	        	entidad.CP = hoja.getCell(13,fila).getContents().toString();
	        	entidad.setProvincia(hoja.getCell(15,fila).getContents().toString());
	        	entidad.setMunicipio(hoja.getCell(9,fila).getContents().toString());
	        	entidad.telefono = hoja.getCell(10,fila).getContents().toString();
	        	entidad.movil = hoja.getCell(11,fila).getContents().toString();
	        	entidad.email = hoja.getCell(12,fila).getContents().toString();
	        	
	        	entidad.pais = hoja.getCell(14,fila).getContents().toString();
	        	if(entidad.pais.equals("ESP")) {
	        		entidad.pais = "ES";
	        	}
	        	
	        	
	        	registro.entidad = entidad;
	        	
	        	listaRegistros.add(registro);
	        	
	        }
	        
	        imprimeListado(listaRegistros);	        

	        
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaRegistros;
	}

	static private void imprimeListado(ArrayList<Registro> listaRegistros) {

		TreeSet<String> entidadesConErrores = new TreeSet<String>();

		for (Registro registro : listaRegistros) {

			// registro.imprimeErrores();
			if (registro.getErrores()) {
				entidadesConErrores.add(registro.entidad.getNombre());
				System.out.println(registro.entidad.getNombre());
			}
		}
		
		for(String elemento : entidadesConErrores) {
			for(Registro registro: listaRegistros) {
				if(registro.entidad.getNombre().equals(elemento)) {
					System.out.println(registro.entidad.CIF + "\t-\t" + registro.entidad.getNombre() + "\t-\t" + registro.entidad.getMunicipio() + "\t-\t"
							+ registro.entidad.CP + "\t-\t" + registro.entidad.codigoProvincia);
					break;
				}
			}
		}


	}
	
	public static ArrayList<Municipio> leerMunicipios(File archivoFuente){
		
		WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setEncoding("Cp1252");
        wbSettings.setLocale(new Locale("es", "ES"));
        wbSettings.setExcelDisplayLanguage("ES"); 
        wbSettings.setExcelRegionalSettings("ES"); 
        wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
        
        String campos[];
        
        ArrayList<Municipio> listaMunicipios = new ArrayList<Municipio>();
        
        Workbook archivoExcel;
		try {
			archivoExcel = Workbook.getWorkbook(archivoFuente, wbSettings);
			
	        Sheet hoja = archivoExcel.getSheet(0);
	        
	        //	Obtiene los nombres
	        int numColumnas = 0;
	        int numFilas = 0;
	        
	        numColumnas = hoja.getColumns();
	        numFilas = hoja.getRows();
	        
	        		/*
	        while(!hoja.getCell(numColumnas,0).getContents().toString().equals(null)){
	        	numColumnas++;
	        	System.out.println(numColumnas);
	        }
	        
	        while(!hoja.getCell(0,numFilas).getContents().toString().equals("")){
	        	numFilas++;
	        }
	        */
	        
	       System.out.println("Num filas = " + numFilas + ". Num columnas = " + numColumnas);
	        
	        campos = new String[numColumnas];
	        
	        Municipio municipio;
	        
	        //	Tabla de todos los documentos
	        for(int fila=2;fila<numFilas;fila++){
	        	
	        	municipio = new Municipio();

	            
	        	municipio.CP = hoja.getCell(0,fila).getContents().toString();
	        	municipio.codigoMunicipio = hoja.getCell(1,fila).getContents().toString();
	        	municipio.nombreMunicipio = hoja.getCell(3,fila).getContents().toString();

	        	
	        	listaMunicipios.add(municipio);
	        	
	        }
	        
   
	        // imprimeMunicipios(listaMunicipios);	 

	        
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaMunicipios;
	}
	
	public static HashMap<String, String> leerProductos(File archivoFuente){
		
		WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setEncoding("Cp1252");
        wbSettings.setLocale(new Locale("es", "ES"));
        wbSettings.setExcelDisplayLanguage("ES"); 
        wbSettings.setExcelRegionalSettings("ES"); 
        wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
        
        String campos[];
        
        HashMap<String, String> listaProductos = new HashMap<String,String>();
        
        Workbook archivoExcel;
		try {
			archivoExcel = Workbook.getWorkbook(archivoFuente, wbSettings);
			
	        Sheet hoja = archivoExcel.getSheet(0);
	        
	        //	Obtiene los nombres
	        int numColumnas = 0;
	        int numFilas = 0;
	        
	        numColumnas = hoja.getColumns();
	        numFilas = hoja.getRows();

	        
	       System.out.println("Num filas = " + numFilas + ". Num columnas = " + numColumnas);
	        
	        campos = new String[numColumnas];
	        
	        Producto producto;
	        
	        //	Tabla de todos los documentos
	        for(int fila=5;fila<numFilas;fila++){
	        	
	        	String registro = hoja.getCell(1,fila).getContents().toString();
	        	String nombreComercial = hoja.getCell(2,fila).getContents().toString();
	        	
	        	listaProductos.put(registro,nombreComercial);
	        	
	        }

	        
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaProductos;
	}
	
	

	static private void imprimeMunicipios(ArrayList<Municipio> listaMunicipios){

		
		for(Municipio municipio: listaMunicipios) {
			municipio.imprime();
			System.out.println("-----------------------------------------------------------");
		}

	}
	
	static public void main(String args[]){

		 // LeerExcel.leerRegistros(new File("Ficheros//230803 Movimientos de Compras por Propiedad.XLS"));
		LeerExcel.leerRegistros(new File("Ficheros//230823 Movimientos de Ventas por Propiedad.XLS"),12);
		
		// LeerExcel.leerMunicipios(new File("Docs//11codmun.XLS"));
		
	}

	
}
