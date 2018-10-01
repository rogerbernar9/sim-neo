package sim.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ordem_compra")
public class OrdemCompra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column
	private Timestamp dataEmissao;
	
	@Column
	private Float preco;

	@OneToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	private Fornecedor fornecedor;
	
	public OrdemCompra() {
	}
	

	public OrdemCompra(Integer codigo, Timestamp dataEmissao, Float preco, Pedido pedido, Fornecedor fornecedor) {
		this.codigo = codigo;
		this.dataEmissao = dataEmissao;
		this.preco = preco;
		this.pedido = pedido;
		this.fornecedor = fornecedor;
	}



	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Timestamp getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Timestamp dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	@Override
	public String toString() {
		return "OrdemCompra [codigo=" + codigo + ", dataEmissao=" + dataEmissao + ", preco=" + preco + ", pedido="
				+ pedido + ", fornecedor=" + fornecedor + "]";
	}


}
