
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

public class VideoDao {
    Connection con;

    public VideoDao() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
           con=DriverManager.getConnection("jdbc:postgresql://localhost/IFTOFLIX","postgres","mariajose12");
    }
     public void sair() throws SQLException
    {
        if(con!=null)
            con.close();
    }
    
     public List<Video> maisVotados() throws SQLException{
     
     List<Video> lista;
        try (PreparedStatement ps = con.prepareStatement("SELECT v.*,avg(p.pontuacao) FROM video AS v INNER JOIN pontuacao AS P ON (P.cod_video = v.id) GROUP BY v.id order by  avg DESC limit 10" )) {
       
            ResultSet rs=ps.executeQuery();
            lista = new ArrayList<>();
            Video p=null;
            while(rs.next())
            {
                p=new Video();
                p.setId(rs.getInt(1));
                p.setCapa(rs.getString(2));
                p.setNome(rs.getString(3));
                p.setDuracao(rs.getString(4));
                p.setLingua(rs.getString(5));
                p.setSinopse(rs.getString(6));
                p.setUrl(rs.getString(7));
                
                lista.add(p);
            }
              
         return lista;
     
     }
     
     }
     public  List<Video> pegar(int idGenero) throws SQLException
    {
        
        List<Video> lista;
        try (PreparedStatement ps = con.prepareStatement("SELECT v.* FROM video AS v INNER JOIN genero_video AS P ON (P.cod_video = v.id) WHERE P.cod_genero = ? ORDER BY RANDOM() LIMIT 10")) {
            ps.setInt(1, idGenero);
            ResultSet rs=ps.executeQuery();
            lista = new ArrayList<>();
            Video p=null;
            while(rs.next())
            {
                p=new Video();
                p.setId(rs.getInt(1));
                p.setCapa(rs.getString(2));
                p.setNome(rs.getString(3));
                p.setDuracao(rs.getString(4));
                p.setLingua(rs.getString(5));
                p.setSinopse(rs.getString(6));
                p.setUrl(rs.getString(7));
                
                lista.add(p);
            }
        }
       
        
         return lista;
    }
    
     
       
  
     
     public  List<GeneroVideo> genVideo() throws ClassNotFoundException, SQLException{
         
       GeneroDao dao = new GeneroDao();
       VideoDao daoV = new VideoDao();
        Video v = new Video();
        Genero[] genero=dao.pegaCincoGeneros();
       List<GeneroVideo> listaVG = new ArrayList();
        for(Genero g : genero){
            
        List<Video> lista=daoV.pegar(g.getId());
        GeneroVideo gv = new GeneroVideo(g,lista);
         listaVG.add(gv);
        
        }
        
       
        dao.sair();
        daoV.sair();
         
         return listaVG;
         
     }
     
     
       public  Video pegarPeloId(int id) throws SQLException
    {
        PreparedStatement ps=con.prepareStatement("select * from video where id=?");
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
       
       Video p=null;
        while(rs.next())
        {
            p=new Video();
            p.setId(rs.getInt(1));
            p.setCapa(rs.getString(2));
            p.setNome(rs.getString(3));
            p.setDuracao(rs.getString(4));
            p.setLingua(rs.getString(5));
            p.setSinopse(rs.getString(6));
            p.setUrl(rs.getString(7));
           
           
        }
        ps.close();
        return p;
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
