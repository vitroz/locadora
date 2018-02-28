package locadora.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cliente implements BaseEntity, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String numHabilitacao;
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	@OneToMany (mappedBy = "cliente", targetEntity = Aluguel.class, 
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Aluguel> historicoAlugueis;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNumHabilitacao() {
		return numHabilitacao;
	}

	public void setNumHabilitacao(String numHabilitacao) {
		this.numHabilitacao = numHabilitacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override 
    public int hashCode() { 
        int hash = 0; 
        hash += (codigo != null ? codigo.hashCode() : 0); 
        return hash; 
    } 

    @Override 
    public boolean equals(Object object) { 
        if (!(object instanceof Cliente)) { 
            return false; 
        } 
        Cliente other = (Cliente) object; 
        if ((this.codigo == null && other.codigo != null) || 
        			(this.codigo != null && !this.codigo.equals(other.codigo))) { 
            return false; 
        } 
        return true; 
    }
}
