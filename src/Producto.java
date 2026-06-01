import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Producto {

	// int tipo = 2;
	String campoProducto;
	
	String nombreComer = "";
	int capacidad;
	int unidad;  // gramos, litros,...
	String numRegistro = "";
	String lote ="";
	int cantidad;
	String esComun = "";
	
	// 
	

	public Producto() {
		
	}
	
	
	// O el volumen total, o la capacidad + cantidad
	int volumenTotal;
	// int numEnvases; Creo que borrar
	
	public Producto(String campoProducto) {
		
		extraerUnidades(campoProducto);
		
	}
	
	public Producto(String campoProducto, String lote, int cantidad) {
		
		extraerUnidades(campoProducto);
//		nombreComer = Inicio.listaProductos.get(this.numRegistro);
		
		this.lote = lote;
		this.cantidad= cantidad;
		
	}
	
	public Producto(String numRegistro, String nombreComer, String lote, int cantidad) {
		this.numRegistro = numRegistro;
		this.nombreComer = nombreComer;
		this.lote = lote;
		this.cantidad = cantidad;
		
		extraerUnidades(this.nombreComer);
	}
	
	private void extraerUnidades(String cadenaTexto) {
		
		// TODO: Lanzar aviso si no se ajusta al formato
		
		// nombreComer = Inicio.listaProductos.get(this.numRegistro);
		nombreComer = cadenaTexto.substring(0,cadenaTexto.indexOf("("));
		
		nombreComer = nombreComer.substring(0,nombreComer.lastIndexOf(" "));
		nombreComer = nombreComer.substring(0,nombreComer.lastIndexOf(" "));
		nombreComer = nombreComer.substring(0,nombreComer.lastIndexOf(" "));
		
		numRegistro = cadenaTexto.substring(cadenaTexto.indexOf("(") + 1,cadenaTexto.indexOf(")"));
		
		int ultimoParentesis = cadenaTexto.lastIndexOf(")");
		this.esComun = cadenaTexto.substring(ultimoParentesis-1,ultimoParentesis);
		
		
		// String patron = "\\s(\\d{1,})\\s(GRS|CC|KG|LT|ML)\\s\\(";
		
		String patron = "\\s(\\d{1,})\\s(GRS|GR|CC|KG|L|LT|LITRO|MG|ML)\\.*\\s+\\(";
		
		// String patron = "\\w+\\s(\\d{1,})\\s(GRS|GR|CC|KG|L|LT|LITRO|MG|ML)\\.*\\s+\\(";
		

		
		Pattern pattern = Pattern.compile(patron);
		Matcher matcher = pattern.matcher(cadenaTexto);
		
		while(matcher.find()) {
			
			//System.out.println(matcher.group(0));
			//nombreComer = matcher.group(0);
			capacidad = Integer.valueOf(matcher.group(1));
			String unidadMetrica = matcher.group(2);
			
			
			/*
			System.out.print(matcher.group(0) + " : " );
			System.out.print(matcher.group(1)+ " - ");
			System.out.println(matcher.group(2));
			*/
			
			switch (unidadMetrica) {
			case "GR":
			case "GRS":
				unidad = 1;
				break;
			case "KG":
				unidad = 2;
				break;
			case "L":
			case "LT":
			case "LITRO":
				unidad = 3;
				break;
			case "MG":
				unidad = 4;
				break;
			case "ML":
			case "ML.":
				unidad = 5;
				break;
			case "CC":
				unidad = 6;
				break;
			default:
				unidad = -1;
				break;
			}
			
			System.out.println(nombreComer + ": " + capacidad + " " + unidadMetrica + " " + unidad + " " + numRegistro);

		}
		
		if( unidad == 0 || capacidad == 0) {
			capacidad = 1;
			unidad = 2;
		}
		
		
	}
	
	public static void main(String args[]) {
		
		String productos[] = {"CARIAL FLEX 60 GRS (ES-00322) (CIMOXANILO 18% + MANDIPROPAMIDA 25%) (WG)",
				"LUQSAZUFRE WG 1 KG (24807) (AZUFRE 80%) (WG)",
				"MIKAL PLUS WG 1 LT (18984)(CIMOXANILO 4% + FOLPET 25% + FOSETIL-AL 50%)(WG)",
				"Midas 20 SG 40 CC (23454)(gdfsdfe)(SL)"
				};
		
		for(String producto: productos) {
			// new Producto("323131", producto, "3N",10).extraerUnidades(producto);
			new Producto(producto);
		}
		
		
	}
	/*  nº registro ---> (ES-00322) */
}
