package sim.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="material")
public class Material implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(length=30, nullable=false, unique=true)
	private String simbolo;
	
	@Column(length=255, nullable=false)
	private String descricao;
	
	@Column(length=255, nullable=true)
	private String anexo;
	
	@Column(nullable=true)
	private boolean aprovado;
	
	@Column
	private Float precoUf;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="id_uf")
	private UnidadeFornecimento uf;

	public Material() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public Float getPrecoUf() {
		return precoUf;
	}

	public void setPrecoUf(Float precoUf) {
		this.precoUf = precoUf;
	}

	public UnidadeFornecimento getUf() {
		return uf;
	}

	public void setUf(UnidadeFornecimento uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Material [codigo=" + codigo + ", simbolo=" + simbolo + ", descricao=" + descricao + ", anexo=" + anexo
				+ ", aprovado=" + aprovado + ", precoUf=" + precoUf + ", uf=" + uf + "]";
	}
	

}
