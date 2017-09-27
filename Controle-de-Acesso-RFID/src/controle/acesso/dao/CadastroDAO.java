package controle.acesso.dao;

import controle.acesso.bean.CadastroAluno;
import controle.acesso.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rafael Carvalho Caetano
 */
public class CadastroDAO {

    public ArrayList<CadastroAluno> listar() throws SQLException {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT a.codigo, a.nome, a.rg, a.cpf, a.tipo ");
        sql.append("FROM arduino a ");
        
        Connection conexao = Conexao.conexao();
        PreparedStatement ps = conexao.prepareStatement(sql.toString());

        ResultSet r = ps.executeQuery();
        ArrayList<CadastroAluno> itens = new ArrayList<CadastroAluno>();

        while (r.next()) {

            CadastroAluno ca = new CadastroAluno();
            ca.setCodigo(r.getString("a.codigo"));
            ca.setNome(r.getString("a.nome"));
            ca.setRg(r.getString("a.rg"));
            ca.setCpf(r.getString("a.cpf"));
            ca.setTipo(r.getString("a.tipo"));
            itens.add(ca);
        }
        return itens;
    }
    public void initSave(CadastroAluno c){
        StringBuilder sql = new StringBuilder();
        
        sql.append("INSERT INTO arduino (id, codigo, nome, rg, cpf, curso, entrada, saida, tipo) VALUES (?,?,?,?,?,?,?,?, ?) ");
        sql.append(" WHERE codigo = ? ");
        Connection con;
        try {

            con = Conexao.conexao();
            PreparedStatement ptt = con.prepareStatement(sql.toString());
            
            ptt.setString(1, c.getId());
            ptt.setString(2, c.getCodigo());
            ptt.setString(3, c.getNome());
            ptt.setString(4, c.getRg());
            ptt.setString(5, c.getCpf());
            ptt.setString(6, c.getCurso());
            
            Date hj = new Date();
            SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyyy HH:mm:ss");
            c.setEntrada(sp.format(hj));
            c.setSaida(sp.format(hj));
            
            ptt.setString(7, c.getEntrada());
            ptt.setString(8, c.getSaida());
            ptt.setString(9, c.getTipo());
           
            ptt.executeUpdate();

            System.out.println("SALVO COM SUCESSO " + c.getCodigo());

        } catch (SQLException ex) {
            System.out.println("ERRO AO SALVAR INITSAVE DAO");
            ex.printStackTrace();
        }
    }
    public CadastroAluno buscarPorCodigo(CadastroAluno f) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT id, codigo ");
		sql.append("FROM arduino ");
		sql.append("WHERE codigo = ? ");


		Connection conexao = Conexao.conexao();

		PreparedStatement c = conexao.prepareStatement(sql.toString());
		c.setString(1, f.getCodigo());
                
		ResultSet rs = c.executeQuery();

		CadastroAluno r = null;

		if (rs.next()) {
			r = new CadastroAluno();
                        r.setId("id");
			r.setCodigo("codigo");
		}

		return r;

	}
    
    

    public void salvar(CadastroAluno c) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO arduino (id, codigo, nome, rg, cpf, curso, tipo) VALUES (?,?,?,?,?,?,?) ");
        
        Connection con;
        try {

            con = Conexao.conexao();
            PreparedStatement ptt = con.prepareStatement(sql.toString());
            
            ptt.setString(1, c.getId());
            ptt.setString(2, c.getCodigo());
            ptt.setString(3, c.getNome());
            ptt.setString(4, c.getRg());
            ptt.setString(5, c.getCpf());
            ptt.setString(6, c.getCurso());
            /*
            Date hj = new Date();
            SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyyy HH:mm:ss");
            c.setEntrada(sp.format(hj));
            c.setSaida(sp.format(hj));
            */
        
            ptt.setString(7, c.getTipo());
           
            ptt.executeUpdate();

            System.out.println("SALVO COM SUCESSO " + c.getId());

        } catch (SQLException ex) {
            System.out.println("ERRO AO SALVAR O ALUNO NA CAMADA DAO");
            ex.printStackTrace();
        }
    }
    
}
