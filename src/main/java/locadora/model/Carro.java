package locadora.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carro implements BaseEntity, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String chassi;
	private String placa;
	private String cor;
	private String modelo;
	private String marca;
	private String ano;
	
	@OneToMany (mappedBy = "carro", targetEntity = Aluguel.class, 
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Aluguel> historicoAlugueis;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	@Override 
    public int hashCode() { 
        int hash = 0; 
        hash += (codigo != null ? codigo.hashCode() : 0); 
        return hash; 
    } 

    @Override 
    public boolean equals(Object object) { 
        if (!(object instanceof Carro)) { 
            return false; 
        } 
        Carro other = (Carro) object; 
        if ((this.codigo == null && other.codigo != null) || 
        			(this.codigo != null && !this.codigo.equals(other.codigo))) { 
            return false; 
        } 
        return true; 
    }

}
