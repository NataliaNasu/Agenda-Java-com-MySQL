package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {
    
    public Connection conectaMySQL() {
        Connection conexao = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/agenda";
            String user = "root";
            String pass = "";
            
            conexao = DriverManager.getConnection(url, user, pass);
                        
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de SQL " + erro.getMessage());
        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Erro de Driver " + erro.getMessage());
        }
        
        return conexao;
        
    }    
}
