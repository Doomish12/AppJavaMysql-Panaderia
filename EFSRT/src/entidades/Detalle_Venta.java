package entidades;

import java.util.Date;

public class Detalle_Venta {
	private int idVenta;
	private String vendedor;
	private int codigoProducto;
	private String nombreProducto;
	private int cantidad;
	private double precioUnitario;
	private double total;
	private String fecha;
	private int codEmp;
	private int codPro;
	private int importeTotal;
	private double vueltoDinero;
	
	
	
	
	public int getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(int importeTotal) {
		this.importeTotal = importeTotal;
	}
	public double getVueltoDinero() {
		return vueltoDinero;
	}
	public void setVueltoDinero(double vueltoDinero) {
		this.vueltoDinero = vueltoDinero;
	}
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCodEmp() {
		return codEmp;
	}
	public void setCodEmp(int codEmp) {
		this.codEmp = codEmp;
	}
	public int getCodPro() {
		return codPro;
	}
	public void setCodPro(int codPro) {
		this.codPro = codPro;
	}
	
	








	
		
}
