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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="item_movimento")
public class ItemMovimento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	@Column
	private Timestamp dataMovimento;
	
	@Column
	private String operacao;
	
	@Column
	private Double quantidade;
	
	@ManyToOne
	@JoinColumn(name="id_item_estoque")
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	private ItemEstoque itemEstoque;
	
	@OneToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	public ItemMovimento(Integer codigo, Timestamp dataMovimento, String operacao, Double quantidade,
			ItemEstoque itemEstoque, Pedido pedido) {
		this.codigo = codigo;
		this.dataMovimento = dataMovimento;
		this.operacao = operacao;
		this.quantidade = quantidade;
		this.itemEstoque = itemEstoque;
		this.pedido = pedido;
	}
	
	public ItemMovimento() {
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Timestamp getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Timestamp dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public ItemEstoque getItemEstoque() {
		return itemEstoque;
	}
	public void setItemEstoque(ItemEstoque itemEstoque) {
		this.itemEstoque = itemEstoque;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	@Override
	public String toString() {
		return "ItemMovimento [codigo=" + codigo + ", dataMovimento=" + dataMovimento + ", operacao=" + operacao
				+ ", quantidade=" + quantidade + ", itemEstoque=" + itemEstoque + ", pedido=" + pedido + "]";
	}

}
