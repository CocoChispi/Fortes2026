import java.util.ArrayList;

public class Municipio {

	String CP;
	String codigoMunicipio;
	String nombreMunicipio;

	public void imprime() {

		System.out.println("CP: \t\t\t" + CP);
		System.out.println("Código: \t\t" + codigoMunicipio);
		System.out.println("Nombre: \t\t" + nombreMunicipio);

	}

	static public String getCodigoMunicipio(Registro registro) { // String campoMunicipio, String cp) {

		String codigo = "";

		String cprov = registro.entidad.codigoProvincia;
		String municipioRegistrado = registro.entidad.getMunicipio().toLowerCase();
		
		/*

		String campos[] = registro.entidad.getMunicipio().split(" ");


		
		ArrayList<String> candidatos = new ArrayList<String>();

		for (String campo : campos) {
			//System.out.println(campo);
			if (campo.length() > 2) {
				candidatos.add(campo.toLowerCase());
			}
		}
		
		*/
		

		// Tanda 01
//		for (String candidato : candidatos) {
			
//			System.out.println(candidato);
			
			
			for (Municipio municipio : Inicio.listaMunicipios) {
			
				if (municipio.CP.equals(cprov)) {
//					System.out.println(municipio.CP + " --- " + cprov);
					
					municipio.nombreMunicipio = municipio.nombreMunicipio.toLowerCase();
					String nombreM = municipio.nombreMunicipio;
					int indice = nombreM.indexOf(",");
					
					// System.out.println(indice);
					
					nombreM =  indice != -1 ? nombreM.substring(0,indice) : nombreM;
					municipio.nombreMunicipio = nombreM;

					// System.out.println(municipioRegistrado + "\t" + nombreM);
					
					if (municipioRegistrado.contains(nombreM)) {
						// System.out.println("Encontrado: " + municipio.codigoMunicipio);
						return municipio.codigoMunicipio;
					}
				}
//			}
		}
		
		

		return codigo;
	}

	
	static public String getCodigoMunicipio(String codProvincia, String municipio) { // String campoMunicipio, String cp) {

		String codigo = "";
		
		if(municipio.length() == 0 || codProvincia.length() == 0 ) {
			return "";
		}

		String campos[] = municipio.split(" ");
		
		ArrayList<String> candidatos = new ArrayList<String>();

		for (String campo : campos) {
			// System.out.println(campo);
			if (campo.length() > 2) {
				candidatos.add(campo.toLowerCase());
			}
		}
		
		

		// Tanda 01
		for (String candidato : candidatos) {
			
//			System.out.println(candidato);
			
			
			for (Municipio muni: Inicio.listaMunicipios) {
				
				if (muni.CP.equals(codProvincia)) {
					// System.out.println("codigo provincia  --- " + codProvincia);
					
					if (muni.nombreMunicipio.toLowerCase().contains(candidato)) {
						System.out.println("El codigo del municipio es: " + muni.codigoMunicipio);
						return muni.codigoMunicipio;
					}
				}
			}
		}
		
		

		return codigo;
	}
}
