/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Servlet;

import Modelo.Dao.GeneroDao;
import Modelo.Dao.VideoDao;
import Modelo.Negocio.Genero;
import Modelo.Negocio.GeneroVideo;
import Modelo.Negocio.Video;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maeh
 */
@WebServlet(name = "ControleVideo", urlPatterns = {"/ControleVideo"})
public class ControleVideo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       
        try {
            VideoDao dao = new VideoDao();
        List<GeneroVideo> lista=dao.genVideo();
         request.setAttribute("lista", lista);
        dao.sair();
        
         GeneroDao daog = new GeneroDao();
            List<Genero> listaGenero=daog.pegaGeneros();
            request.setAttribute("listaGenero", listaGenero);
            
            HttpSession sessao = request.getSession();
                   
                    sessao.setAttribute("listaGenero", listaGenero);
            daog.sair();
            
             VideoDao daov = new VideoDao();
        List<Video> maisVotados=daov.maisVotados();
         request.setAttribute("maisVotados", maisVotados);
        daov.sair();
            
        } catch (ClassNotFoundException ex) {
            request.setAttribute("mensagem", "Erro ao tentar pegar o drive JDBC");
        } catch (SQLException ex) {
            request.setAttribute("mensagem", ex);
        }
        request.getRequestDispatcher("filmes.jsp").forward(request, response);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
