package sim.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	@Column
	private Float quantidade;
	
	@Column
	private String status;
	
	@Column
	private Date dataEmissao;
	
	@Column
	private Date dataFim;
	
	@Column
	private String aplicacao;
	
	@Column
	private String andamento;
	
	@Column
	private boolean urgencia;
	
	@Column(length=200, nullable=true)
	private String observacoes;
	
	@Column
	private boolean ressuprimento;
	
	@ManyToOne
	@JoinColumn(name="id_material")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	

	public Pedido(Integer codigo, Float quantidade, String status, Date dataEmissao, Date dataFim, String aplicacao,
			String andamento, boolean urgencia, String observacoes, boolean ressuprimento, Material material,
			Usuario usuario) {
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.status = status;
		this.dataEmissao = dataEmissao;
		this.dataFim = dataFim;
		this.aplicacao = aplicacao;
		this.andamento = andamento;
		this.urgencia = urgencia;
		this.observacoes = observacoes;
		this.ressuprimento = ressuprimento;
		this.material = material;
		this.usuario = usuario;
	}

	public Pedido() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}


	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getAndamento() {
		return andamento;
	}

	public void setAndamento(String andamento) {
		this.andamento = andamento;
	}

	public boolean isUrgencia() {
		return urgencia;
	}

	public void setUrgencia(boolean urgencia) {
		this.urgencia = urgencia;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public boolean isRessuprimento() {
		return ressuprimento;
	}

	public void setRessuprimento(boolean ressuprimento) {
		this.ressuprimento = ressuprimento;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", quantidade=" + quantidade + ", status=" + status + ", dataEmissao="
				+ dataEmissao + ", dataFim=" + dataFim + ", aplicacao=" + aplicacao + ", andamento=" + andamento
				+ ", urgencia=" + urgencia + ", observacoes=" + observacoes + ", ressuprimento=" + ressuprimento
				+ ", material=" + material + ", usuario=" + usuario + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
