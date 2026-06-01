import java.util.Date;
import java.util.TreeSet;

/**
 * @author mgama
 *
 */
public class Registro {

	String fecha;
	
	String NIF = "B27749548";
	String ROPO = "1236149506SS";
	
	int operacion; 				/* 2 Venta en España; 11 Compra en España; 12 Compra en España Salida  */
	
	Producto producto;
	Entidad entidad;
	
	
	
	public void imprime() {
		
		System.out.println("Fecha: \t\t\t" + fecha);
		System.out.println("Nombre producto: \t" + producto.nombreComer);
		System.out.println("Lote: \t\t\t" + producto.lote);
		System.out.println("Capacidad: \t\t" + producto.capacidad);
		System.out.println("Unidad: \t\t" + producto.unidad);
		System.out.println("Registro: \t\t" + producto.numRegistro);
		System.out.println("Cantidad: \t\t" + producto.cantidad);
		
		System.out.println("Cif: \t\t\t" + entidad.CIF);
		System.out.println("Nombre: \t\t" + entidad.getNombre());
		System.out.println("Dirección: \t\t" + entidad.direccion);
		System.out.println("Municipio: \t\t" + entidad.getMunicipio());
		System.out.println("Telefono: \t\t" + entidad.telefono);
		System.out.println("Movil: \t\t\t" + entidad.movil);
		System.out.println("Email: \t\t\t" + entidad.email);
		System.out.println("Código Postal: \t\t" + entidad.CP);
		System.out.println("País: \t\t\t" + entidad.pais);
		System.out.println("Provincia: \t\t" + entidad.getProvincia());
		System.out.println("CodigoProvincia: \t" + entidad.codigoProvincia);
		
		
	}
	
	public void imprimeLinea() {
		System.out.print("Fecha: " + fecha + "\t");
		
		System.out.print("Cif: " + entidad.CIF+ "\t");
		System.out.print("Nombre: " + entidad.getNombre()+ "\t");
		System.out.print("Dirección: " + entidad.direccion+ "\t");
		System.out.print("Municipio: " + entidad.getMunicipio() + "\t");
		System.out.print("Municipio: " + entidad.codigoMunicipio + "\t");
		System.out.print("Telefono: " + entidad.telefono+ "\t");
		System.out.print("Movil: " + entidad.movil+ "\t");
		System.out.print("Email: " + entidad.email+ "\t");
		System.out.print("Código Postal: " + entidad.CP+ "\t");
		System.out.print("País: " + entidad.pais+ "\t");
		System.out.print("Provincia: " + entidad.getProvincia()+ "\t");
		System.out.print("CodigoProvincia: " + entidad.codigoProvincia);
		
		System.out.print("Nombre producto: " + producto.nombreComer + "\t");
		System.out.print("Lote: " + producto.lote + "\t");
		System.out.print("Capacidad: " + producto.capacidad + "\t");
		System.out.print("Unidad: " + producto.unidad + "\t");
		System.out.print("Registro: " + producto.numRegistro + "\t");
		System.out.println("Cantidad: " + producto.cantidad+ "\t");
	}
	
	public void imprimeErrores() {
		
		
		
		if(
				entidad.CIF.length() == 0 || 
				entidad.CIF.length() > 9 || 
				entidad.getNombre().length() == 0 || 
				entidad.CP.length() < 5 || 
				entidad.getMunicipio().length() == 0) {
			
					
					System.out.println( entidad.CIF + "\t-\t" + entidad.getNombre() + "\t-\t" + 
										entidad.getMunicipio() + "\t-\t" + entidad.CP + "\t-\t" +  entidad.codigoProvincia);
		}
		
	}
	
	public boolean getErrores() {
		if(
				entidad.CIF.length() == 0 || 
				entidad.CIF.length() > 9 || 
				entidad.getNombre().length() == 0 || 
				entidad.CP.length() < 5 || 
				entidad.getMunicipio().length() == 0) {
			
					
				//	System.out.println( entidad.CIF + "\t-\t" + entidad.getNombre() + "\t-\t" + 
				//						entidad.municipio + "\t-\t" + entidad.CP + "\t-\t" +  entidad.codigoProvincia);
					return true;
		}
		return false;
		
		
	}
	
}
