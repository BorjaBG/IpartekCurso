package com.ipartek.borja.ejerciciosClases;

public class Fecha{
	
	private int a�o = 0;
	private int mes = 1;
	private int dia = 1;
	private String nombreMes = "";
	
	static FechaException fe = new FechaException();
	
	public static final int MESES_A�O = 12;
	public static final String ERROR_DIA = "A habido un error al introducir el dia";
	public static final Throwable ERROR_CAUSA = fe.getCause();
	public static boolean esBisiesto (int a�o) {
		if((a�o % 4 == 0) && ((a�o % 100 != 0) || (a�o % 400 == 0))) {
			return true;
		}else {
			return false;
		}
	}
	
	public Fecha(int a�o, int mes, int dia) {
		super();
		setA�o(a�o);
		setMes(mes);
		setDia(dia);
	}

	public int getA�o() {
		return a�o;
	}
	public void setA�o(int a�o) {
		this.a�o = a�o;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		if (mes >= 1 && mes <= 12) {
			//setMes(mes);
			this.mes = mes;
		} else {
			throw new FechaException("A habido un error al introducir el mes", ERROR_CAUSA);
		}
		//this.mes = mes;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		switch(getMes()) {
		case 1:
			setNombreMes("Enero");
			if (dia >= 1 && dia <=31) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 2:
			setNombreMes("Febrero");
			if (!esBisiesto(a�o)) {
				if (dia >= 1 && dia <=28) {
					//setDia(dia);
					this.dia = dia;
				} else {
					throw new FechaException(ERROR_DIA, ERROR_CAUSA);
				}
			} else {
				if (dia >= 1 && dia <=29) {
					//setDia(dia);
					this.dia = dia;
				} else {
					throw new FechaException(ERROR_DIA, ERROR_CAUSA);
				}
			}
			break;
		case 3:
			setNombreMes("Marzo");
			if (dia >= 1 && dia <=31) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 4:
			setNombreMes("Abril");
			if (dia >= 1 && dia <=30) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 5:
			setNombreMes("Mayo");
			if (dia >= 1 && dia <=31) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 6:
			setNombreMes("Junio");
			if (dia >= 1 && dia <=30) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 7:
			setNombreMes("Julio");
			if (dia >= 1 && dia <=31) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 8:
			setNombreMes("Agosto");
			if (dia >= 1 && dia <=31) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 9:
			setNombreMes("Septiembre");
			if (dia >= 1 && dia <=30) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 10:
			setNombreMes("Octubre");
			if (dia >= 1 && dia <=31) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 11:
			setNombreMes("Noviembre");
			if (dia >= 1 && dia <=30) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
			break;
		case 12:
			setNombreMes("Diciembre");
			if (dia >= 1 && dia <=31) {
				//setDia(dia);
				this.dia = dia;
			} else {
				throw new FechaException(ERROR_DIA, ERROR_CAUSA);
			}
		}
		//this.dia = dia;
	}

	public String getNombreMes() {
		return nombreMes;
	}

	public void setNombreMes(String nombreMes) {
		this.nombreMes = nombreMes;
	}

	@Override
	public String toString() {
		return "Fecha [" + dia + " de " + nombreMes + " del a�o " + a�o + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a�o;
		result = prime * result + dia;
		result = prime * result + mes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fecha other = (Fecha) obj;
		if (a�o != other.a�o)
			return false;
		if (dia != other.dia)
			return false;
		if (mes != other.mes)
			return false;
		return true;
	}
	

}
