
public class Entidad {

	String ROPO;
	
	String CIF;
	private String nombre;
	String direccion;
	private String municipio;
	String telefono;
	String movil;
	String email;
	String CP;
	String pais;
	private String provincia;
	String codigoProvincia;
	String codigoMunicipio;
	
	public void setNombre(String nombreSinFormato) {
		int index = nombreSinFormato.indexOf("(");
		if(index != -1) {
			nombre = nombreSinFormato.substring(0,index);
		}
		else {
			nombre = nombreSinFormato;
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia != null ? provincia : "";
		CP = CP != null ? CP : "";
		if(CP.length() == 5) {
			codigoProvincia = CP.substring(0,2);
		}
		else {
			codigoProvincia = "";
		}
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setMunicipio(String municipio) {
		
		this.municipio = municipio != null ? municipio : "";
		codigoMunicipio = Municipio.getCodigoMunicipio(codigoProvincia,this.municipio);
	}
	
	public String getMunicipio() {
		return municipio;
	}
}
