package sim.persistence.jdbc;

public class AutorizacaoDao extends Dao {
	
	
	public AutorizacaoFornecimento findByCode(Integer cod)throws Exception{
		open();
		String sql = "select p.codigo, f.nomeFantasia, e.logradouro, e.numero, e.cep, f.cnpj, m.descricao, p.quantidade, m.precoUf from " + 
				"ordem_compra oc " + 
				"join fornecedor f on oc.id_fornecedor = f.codigo " + 
				"join pedido p on oc.id_pedido = p.codigo " + 
				"join endereco e on f.id_endereco = e.codigo " + 
				"join material m on p.id_material = m.codigo " + 
				"where p.codigo = ?";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, cod);
		rs = stmt.executeQuery();
		AutorizacaoFornecimento af = null;
		if(rs.next()) {
			af = new AutorizacaoFornecimento();
			af.setCodigo(rs.getInt(1));
			af.setNomeFantasia(rs.getString(2));
			af.setLogradouro(rs.getString(3));
			af.setNumero(rs.getString(4));
			af.setCep(rs.getString(5));
			af.setCnpj(rs.getString(6));
			af.setDescricao(rs.getString(7));
			af.setQuantidade(rs.getFloat(8));
			af.setPrecoUf(rs.getFloat(9));
		}
		close();
		return af;
	}

}
