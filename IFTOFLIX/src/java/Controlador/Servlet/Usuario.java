/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Servlet;

import Modelo.Dao.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class Usuario extends HttpServlet {

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
            throws ServletException, IOException, SQLException {

        if (request.getParameter("acao").equals("1")) {
            String email = request.getParameter("emailL");
            String senha = request.getParameter("passwordL");
            if(!email.equals("") && !senha.equals("")){
            
             try {
                    UsuarioDao login = new UsuarioDao();
                    Modelo.Negocio.Usuario user =login.login(email, senha);
                    if(user!=null){
                     HttpSession sessao = request.getSession();
                    response.getWriter().print(email);
                    sessao.setAttribute("user", user);
                    login.sair();
                    request.getRequestDispatcher("/ControleVideo").forward(request, response);
                    }
                    else{
                    request.setAttribute("mensagemL", "Usuario nao encontrado, cadastre-se");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                   

                    
                } catch (ClassNotFoundException ex) {
                    request.setAttribute("mensagemL", "Erro ao tentar pegar o drive JDBC");
                } catch (SQLException ex) {
                    request.setAttribute("mensagemL", "Erro ao executar o SQL");
                }
            }else{
                    request.setAttribute("mensagemL", "Campos em Branco, preencha");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
            }
          
     
                
            

           
        } else {
            String email = request.getParameter("emailS");
            String senha = request.getParameter("passwordS");
            String nome = request.getParameter("nomeS");
            
            
            if(!email.equals("") && !senha.equals("") && !nome.equals("")){
            
             try {
                 UsuarioDao dao = new UsuarioDao();
 
                     Modelo.Negocio.Usuario user =dao.signup(nome,email, senha);
                    if(user!=null){
                     HttpSession sessao = request.getSession();
                    response.getWriter().print(email);
                    sessao.setAttribute("user", user);
                    sessao.setMaxInactiveInterval(1000 * 60);
                    dao.sair();
                    request.getRequestDispatcher("/ControleVideo").forward(request, response);
                    }
                    else{
                    request.setAttribute("mensagemL", "Usuario nao encontrado, cadastre-se");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                   
                    dao.sair();
                    
                } catch (ClassNotFoundException ex) {
                    request.setAttribute("mensagemL", "Erro ao tentar pegar o drive JDBC");
                } catch (SQLException ex) {
                    request.setAttribute("mensagemL", "Erro ao executar o SQL");
                }
            }else{
                    request.setAttribute("mensagemL", "Campos em Branco, preencha");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
