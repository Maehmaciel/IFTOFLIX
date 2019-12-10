
package Modelo.Dao;


import Modelo.Negocio.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDao {
    Connection con;

    public UsuarioDao() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
           con=DriverManager.getConnection("jdbc:postgresql://localhost/IFTOFLIX","postgres","mariajose12");
    }
     public void sair() throws SQLException
    {
        if(con!=null)
            con.close();
    }
     
         public  Usuario signup(String nome, String email, String senha) throws SQLException
    {
        PreparedStatement ps=con.prepareStatement("insert into usuario (nome,email,senha) values (?,?,?) returning id,nome");
        ps.setString(1, nome);
        ps.setString(2, email);
        ps.setString(3, senha);
        ResultSet rs=ps.executeQuery();
       
       Usuario u =null;
        while(rs.next())
        {
            u=new Usuario();
            u.setId(rs.getInt(1));
            u.setNome(rs.getString(2));           
        }
        ps.close();
        return u;
    }
     
    
     
     

     
     
       public  Usuario login(String email, String senha) throws SQLException
    {
        PreparedStatement ps=con.prepareStatement("select id,nome from usuario where email=? and senha=?");
        ps.setString(1, email);
        ps.setString(2, senha);
        ResultSet rs=ps.executeQuery();
       
       Usuario u =null;
        while(rs.next())
        {
            u=new Usuario();
            u.setId(rs.getInt(1));
            u.setNome(rs.getString(2));           
        }
        ps.close();
        return u;
    }
       
  
     
     public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UsuarioDao dao = new UsuarioDao();
        Usuario u=dao.signup("maeh2","mariaeugeniamaciel2@gmail.co", "mariajose122");
            
       
       if(u!=null){
       System.out.println("Codigo: "+u.getId()+"\n Nome: "+u.getNome());
       }else{
           System.out.println("Nao encontrado");
       }
            
        
      
        
       
        dao.sair();
    }
}
