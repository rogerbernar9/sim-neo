package sim.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="unidade_fornecimento")
public class UnidadeFornecimento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(length=10, nullable=false, unique=true)
	private String simboloUnidade;
	
	@Column(length=60)
	private String descricao;

	public UnidadeFornecimento() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getSimboloUnidade() {
		return simboloUnidade;
	}

	public void setSimboloUnidade(String simboloUnidade) {
		this.simboloUnidade = simboloUnidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "" + simboloUnidade + " - "
				+ descricao + "";
	}

	

}
