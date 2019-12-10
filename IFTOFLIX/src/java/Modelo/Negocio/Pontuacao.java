/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Negocio;

/**
 *
 * @author Maeh
 */
public class Pontuacao {

    private Usuario cod_usuario; 
    private Video cod_video; 
    private int pontuacao;

    public Pontuacao(Usuario cod_usuario, Video cod_video, int pontuacao) {
        this.cod_usuario = cod_usuario;
        this.cod_video = cod_video;
        this.pontuacao = pontuacao;
    }

 

    public int getPontuacao() {
        return pontuacao;
    }


    
    

}
    

