package sim.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	@Column(length=50,nullable=false)
	private String nome;
	
	@Column
	private Integer permissao;

	public Perfil() {
	}

	public Perfil(Integer codigo, String nome, Integer permissao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.permissao = permissao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPermissao() {
		return permissao;
	}

	public void setPermissao(Integer permissao) {
		this.permissao = permissao;
	}

	@Override
	public String toString() {
		return "Perfil [codigo=" + codigo + ", nome=" + nome + ", permissao=" + permissao + "]";
	}
	
	

}
