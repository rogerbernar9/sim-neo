package sim.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="endereco")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column
	private String logradouro;

	@Column
	private String numero;
	
	@Column
	private String cep;
	
	public Endereco() {
	}

	public Endereco(Integer codigo, String logradouro, String numero, String cep) {
		this.codigo = codigo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Endereco [codigo=" + codigo + ", logradouro=" + logradouro + ", numero=" + numero + ", cep=" + cep
				+ "]";
	}
	
	

}
