
package Modelo.Dao;

import Modelo.Negocio.Genero;
import Modelo.Negocio.GeneroVideo;
import Modelo.Negocio.Video;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroDao {
    Connection con;

    public GeneroDao() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        con=DriverManager.getConnection("jdbc:postgresql://localhost/IFTOFLIX","postgres","mariajose12");

    }
     public void sair() throws SQLException
    {
        if(con!=null)
            con.close();
    }
     
     public List<Genero> pegaGeneros() throws SQLException{
         List<Genero> lista;
        try (PreparedStatement gen = con.prepareStatement("SELECT * FROM genero")) {
            ResultSet rs=gen.executeQuery();
             lista = new ArrayList<>();
            Genero g=null;
            while(rs.next())
            {
                g=new Genero();
                g.setId(rs.getInt(1));
                g.setGenero(rs.getString(2));
                
                lista.add(g);
            }
        }
        return lista;
     }
     
     public Genero[] pegaCincoGeneros() throws SQLException{
         Genero[] genero;
        try (PreparedStatement gen = con.prepareStatement("SELECT * FROM genero ORDER BY RANDOM() LIMIT 5")) {
            ResultSet rs=gen.executeQuery();
            genero = new Genero[5];
            Genero g=null;
            int i=0;
            while(rs.next())
            {
                g=new Genero();
                g.setId(rs.getInt(1));
                g.setGenero(rs.getString(2));
                genero[i]=g;
                i++;
            }
        }
        return genero;
     }

    
     
     
     public static void main(String[] args) throws ClassNotFoundException, SQLException {
        VideoDao dao = new VideoDao();
        List<GeneroVideo> lista=dao.genVideo();
            
       
        for(GeneroVideo g : lista){
            System.out.println(g.getG().getGenero());
        
         for(Video p:g.getVideos())
            System.out.println("Codigo: "+p.getId()+"\n Capa: "+p.getCapa()+"\n Nome:"+p.getNome()+"\n Duracao:"+p.getDuracao()+"\n Lingua:"+p.getLingua()+"\n Sinopse:"+p.getSinopse()+"\n Url:"+p.getUrl());
        
        
        }
        
       
        dao.sair();
    }
}
