package sim.persistence.relatorio.jdbc;

import java.util.ArrayList;
import java.util.List;

import sim.persistence.relatorio.jdbc.Pedido;


public class PedidoDao extends Dao{
	
	public Pedido findByCode(Integer cod)throws Exception{
		open();
		String sql = "select p.codigo, p.andamento, p.aplicacao, p.dataEmissao, p.dataFim, p.observacoes, p.quantidade, p.ressuprimento, p.status, p.urgencia, m.simbolo as simbolo, m.descricao as descricao, m.precoUf as precoUf, u.nome as nome from pedido p join material m on p.id_material = m.codigo join usuario u on p.id_usuario = u.codigo where p.codigo= ?";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, cod);
		rs = stmt.executeQuery();
		Pedido p = null;
		if(rs.next()) {
			p = new Pedido();
			p.setCodigo(rs.getInt(1));
			p.setAndamento(rs.getString(2));
			p.setAplicacao(rs.getString(3));
			p.setDataEmissao(rs.getTimestamp(4));
			p.setDataFim(rs.getTimestamp(5));
			p.setObservacoes(rs.getString(6));
			p.setQuantidade(rs.getFloat(7));
			p.setRessuprimento(rs.getBoolean(8));
			p.setStatus(rs.getString(9));
			p.setUrgencia(rs.getBoolean(10));
			p.setSimbolo(rs.getString(11));
			p.setDescricao(rs.getString(12));
			p.setPrecoUf(rs.getFloat(13));
			p.setNome(rs.getString(14));
		}
		close();
		return p;
	}
	
	public static void main(String[] args) {
		PedidoDao dao = new PedidoDao();
		try {
			System.out.println(dao.findByCode(33));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
