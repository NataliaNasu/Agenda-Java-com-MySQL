package DAO;

import DTO.ContatoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ContatoDAO {
    
    Connection conexao;
    PreparedStatement pstm;
    ResultSet result;
    ArrayList<ContatoDTO> lista = new ArrayList<>();
    
    public ContatoDAO() {
        conexao = new ConexaoDAO().conectaMySQL();
    }
    
    public void salvar(ContatoDTO objContatoDto){
        
        String sql = "insert into contatos (nome, telefone) values (?, ?)";
        
        try {
            
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objContatoDto.getNome());
            pstm.setString(2, objContatoDto.getTelefone());
            
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Contato Salvo com sucesso!");
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "ContatoDAO salvar " + erro);
            
        }
    }
    
    public ArrayList<ContatoDTO> Pesquisar() {

        String sql = "select * from contatos";
        
        try {
            pstm = conexao.prepareStatement(sql);
            result = pstm.executeQuery();

            while (result.next()) {
                ContatoDTO objContatoDto = new ContatoDTO();
                objContatoDto.setId_contato(result.getInt("id_contato"));
                objContatoDto.setNome(result.getString("nome"));
                objContatoDto.setTelefone(result.getString("telefone"));

                lista.add(objContatoDto);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ContatoDAO Pesquisar " + erro);
        }
        return lista;
    }
    
    public void atualizar(ContatoDTO objContatoDto) {
        
        String sql = "update contatos set nome = ?, telefone = ? where id_contato = ?";
        
        try {
            
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objContatoDto.getNome());
            pstm.setString(2, objContatoDto.getTelefone());
            pstm.setInt(3, objContatoDto.getId_contato());
            
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Contato Atualizado!");
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "ContatoDAO Atualizar " + erro);
            
        }
        
    }
    
    public void deletar(ContatoDTO objContatoDto) {
        
        String sql = "delete from contatos where id_contato = ?";
        
        try {
            
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, objContatoDto.getId_contato());
            
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Contato Excluído!");
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "ContatoDAO Deletar " + erro);
            
        }
    }
}