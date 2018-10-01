package sim.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="item_estoque")
public class ItemEstoque {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	@Column
	private Float saldo;
	
	@Column(length=45)
	private String localizacao;
	
	@Column(length=45)
	private String prateleira;

	@ManyToOne
	@JoinColumn(name="id_material")
	private Material material;

	public ItemEstoque(Integer codigo, Float saldo, String localizacao, String prateleira, Material material) {
		this.codigo = codigo;
		this.saldo = saldo;
		this.localizacao = localizacao;
		this.prateleira = prateleira;
		this.material = material;
	}

	public ItemEstoque() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Float getSaldo() {
		return saldo;
	}

	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getPrateleira() {
		return prateleira;
	}

	public void setPrateleira(String prateleira) {
		this.prateleira = prateleira;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "ItemEstoque [codigo=" + codigo + ", saldo=" + saldo + ", localizacao=" + localizacao + ", prateleira="
				+ prateleira + ", material=" + material + "]";
	}
	

}
