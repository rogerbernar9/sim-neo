package sim.persistence.jdbc;

import java.io.Serializable;
import java.sql.Timestamp;


public class Pedido implements Serializable {
	
	public Pedido() {
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private Float quantidade;
	private String status;
	private Timestamp dataEmissao;
	private Timestamp dataFim;
	private String aplicacao;
	private String andamento;
	private boolean urgencia;
	private String observacoes;
	private boolean ressuprimento;
	private String descricao;
	private String simbolo;
	private Float precoUf;
	private String nome;
	private Integer id_material;
	private Integer id_usuario;
	
	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", quantidade=" + quantidade + ", status=" + status + ", dataEmissao="
				+ dataEmissao + ", dataFim=" + dataFim + ", aplicacao=" + aplicacao + ", andamento=" + andamento
				+ ", urgencia=" + urgencia + ", observacoes=" + observacoes + ", ressuprimento=" + ressuprimento
				+ ", descricao=" + descricao + ", simbolo=" + simbolo + ", precoUf=" + precoUf + ", nome=" + nome + "]";
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Timestamp dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Timestamp getDataFim() {
		return dataFim;
	}

	public void setDataFim(Timestamp dataFim) {
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

	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public Float getPrecoUf() {
		return precoUf;
	}

	public void setPrecoUf(Float precoUf) {
		this.precoUf = precoUf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId_material() {
		return id_material;
	}

	public void setId_material(Integer id_material) {
		this.id_material = id_material;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	
}
