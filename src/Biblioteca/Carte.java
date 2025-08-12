package Biblioteca;

public class Carte {
	public String titlu;
	public String autor;
	public String data_imprumut;
	
	
	public Carte(String titlu, String autor) {
		super();
		this.titlu = titlu;
		this.autor = autor;
	}
	public String getTitlu() {
		return titlu;
	}
	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getData_imprumut() {
		return data_imprumut;
	}
	public void setData_imprumut(String data_imprumut) {
		this.data_imprumut = data_imprumut;
	}
	
}




