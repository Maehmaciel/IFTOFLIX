
package Modelo.Dao;

import Modelo.Negocio.Pontuacao;
import Modelo.Negocio.Usuario;
import Modelo.Negocio.Video;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PontuacaoDao {
     Connection con;

    public PontuacaoDao() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
           con=DriverManager.getConnection("jdbc:postgresql://localhost/IFTOFLIX","postgres","mariajose12");
    }
     public void sair() throws SQLException
    {
        if(con!=null)
            con.close();
    }
     
              public  void votar(int cod_usuario, int cod_video, int ponto) throws SQLException
    {
        PreparedStatement ps=con.prepareStatement("insert into pontuacao (cod_usuario,cod_video, pontuacao) values (?,?,?)");
        ps.setInt(1, cod_usuario);
        ps.setInt(2, cod_video);
        ps.setInt(3, ponto);
        ResultSet rs=ps.executeQuery();
        ps.close();
       
     
    }
                           public  void voto(int cod_usuario, int cod_video, int ponto) throws SQLException
    {
        PreparedStatement ps=con.prepareStatement("update pontuacao set pontuacao=? where cod_usuario=? and cod_video=?");
        ps.setInt(2, cod_usuario);
        ps.setInt(3, cod_video);
        ps.setInt(1, ponto);
        ResultSet rs=ps.executeQuery();
        ps.close();
       
     
    }
                           

   
    public static void main(String[] args) {
     
    }
 
}
