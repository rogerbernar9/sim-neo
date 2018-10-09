package sim.persistence.jdbc;

import java.sql.Timestamp;

public class OrdemCompraDao extends Dao {
	
	public OrdemCompra findByCode(Integer cod) throws Exception	{
		open();
		String sql = "select oc.codigo, oc.dataEmissao, oc.preco, p.quantidade, m.simbolo, m.descricao, f.nomeFantasia, e.logradouro, e.numero, e.cep from ordem_compra oc join pedido p on p.codigo = oc.id_pedido join material m on p.id_material = m.codigo join fornecedor f on f.codigo = oc.id_fornecedor join endereco e on f.id_endereco = e.codigo where oc.codigo= ?";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, cod);
		
		rs = stmt.executeQuery();
		OrdemCompra oc = null;
		if(rs.next())	{
			oc= new OrdemCompra();
			oc.setCodigo(rs.getInt(1));
			oc.setDataEmissao(rs.getTimestamp(2));
			oc.setPreco(rs.getFloat(3));
			oc.setQuantidade(rs.getFloat(4));
			oc.setSimbolo(rs.getString(5));
			oc.setDescricao(rs.getString(6));
			oc.setNomeFantasia(rs.getString(7));
			oc.setLogradouro(rs.getString(8));
			oc.setNumero(rs.getString(9));
			oc.setCep(rs.getString(10));
			
		}
		close();
		
		return oc;
	}
	
}
