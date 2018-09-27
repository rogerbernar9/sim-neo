package sim.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="fornecedor")
public class Fornecedor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(length=15)
	private String cnpj;
	
	@Column(length=100)
	private String razaoSocial;
	
	@Column(length=200)
	private String nomeFantasia;
	
	@OneToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="id_endereco")
	private Endereco endereco;


	public Fornecedor(Integer codigo, String cnpj, String razaoSocial, String nomeFantasia, Endereco endereco) {
		this.codigo = codigo;
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.endereco = endereco;
	}

	public Fornecedor() {
		this.endereco = new Endereco();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public String toString() {
		return "Fornecedor [codigo=" + codigo + ", cnpj=" + cnpj + ", razaoSocial=" + razaoSocial + ", nomeFantasia="
				+ nomeFantasia + ", endereco=" + endereco + "]";
	}
	

}
