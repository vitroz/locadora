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
public class Endereco implements BaseEntity, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String logradouro;
	private String bairro;
	private String complemento;
	private Integer numero;
	private Integer quadra;
	
	@OneToMany (mappedBy = "endereco", targetEntity = Cliente.class, 
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Cliente> clientes;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getQuadra() {
		return quadra;
	}

	public void setQuadra(Integer quadra) {
		this.quadra = quadra;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@Override 
    public int hashCode() { 
        int hash = 0; 
        hash += (codigo != null ? codigo.hashCode() : 0); 
        return hash; 
    } 

    @Override 
    public boolean equals(Object object) { 
        if (!(object instanceof Endereco)) { 
            return false; 
        } 
        Endereco other = (Endereco) object; 
        if ((this.codigo == null && other.codigo != null) || 
        			(this.codigo != null && !this.codigo.equals(other.codigo))) { 
            return false; 
        } 
        return true; 
    }
}
