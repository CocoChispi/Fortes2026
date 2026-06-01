import java.io.File;
import java.util.ArrayList;

public class Error {
	
	public ArrayList<Integer> hasErrors(Registro registro) {
		
		ArrayList<Integer> error = new ArrayList<Integer>();
		
		if(!isFechaOk(registro.fecha)) { error.add(1); }							// Fecha
		
		if(!isCIFOk(registro.entidad.CIF)) { error.add(2); }						// CIF	Destino  ! consultar!!!!
	//	if(!isRopo_Ok(registro.entidad.ROPO)) { error = 3; }						// ROPO
		
		if(!isString_Ok(registro.entidad.getNombre(),200)) { error.add(4); }		// Nombre entidad
		if(!isString_Ok(registro.entidad.direccion,255)) { error.add(5); }			// Direccion
	//	if(!isTelefono_Ok(registro.entidad.telefono)) { error.add(6); }				// Telefono
		if(!isCPostal_Ok(registro.entidad.CP)) { error.add(7); }					// Código Postal
		if(!isString_Ok(registro.entidad.getMunicipio(),255)) { error.add(8); }		// Municipio
		if(!isString_Ok(registro.entidad.codigoMunicipio,3)) { error.add(9); }		// Codigo Municipio
	//	if(!isString_Ok(registro.entidad.getProvincia(),2)) { error.add(10); }		// Provincia
		
		if(!isString_Ok(registro.producto.numRegistro,255)) { error.add(11); }		// Registro
		if(!isString_Ok(registro.producto.nombreComer,255)) { error.add(12); }		// Nombre comercial
		if(!isString_Ok(registro.producto.lote,255)) { error.add(13); }				// Lote
		
		
		/*
		System.out.println(isFechaOk("30/04/2023"));
		System.out.println(isString_Ok("f",255));
		System.out.println(isCIFOk("35322383h"));
		*/
		
		return error;
		
	}
	
	private boolean isFechaOk(String fecha) {
		boolean ok = false;

		if (fecha.length() == 10) {
			ok = true;
			int slash = 0;
			for (Character caracter : fecha.toCharArray()) {
				if (caracter == '/') {
					slash++;
				}
			}

			if (slash == 2) {
				ok = true;
			} 
			else {
				ok = false;
			}
		} 

		return ok;
	}

	private boolean isString_Ok(String producto, int numCaracteres) {
		return (producto.length() > 0 && producto.length() <= numCaracteres + 1) ? true : false;
	}
	
	private boolean isCIFOk(String CIF) {
		return (CIF.length() <= 9 && CIF.length() > 0) ? true : false;
	}
	
	private boolean isRopo_Ok(String ropo) {
		return ropo.length() <= 20 ? true : false;
	}
	
	private boolean isTelefono_Ok(String telefono) {
		return (telefono.length() == 9) ? true : false;
	}
	
	private boolean isCPostal_Ok(String cp) {
		return cp.length() == 5 ? true : false;
	}
	
	public String getErrores(Registro registro) {
		ArrayList<Integer> errores = hasErrors(registro);
		String cadena = "";
		for(int err: errores) {
			cadena += err + " - ";
		}
		
		return cadena;
	}
	
	
	static public void main(String args[]){

		new Error().hasErrors(null);
		
	}
}
