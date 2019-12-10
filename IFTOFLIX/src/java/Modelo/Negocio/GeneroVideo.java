/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Negocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria.maciel
 */
public class GeneroVideo {
    private Genero g;
    private List<Video> videos;

    public GeneroVideo(Genero g, List<Video> videos) {
        this.g = g;
        this.videos=new ArrayList();
        this.videos = videos;
    }

    public Genero getG() {
        return g;
    }

    public List<Video> getVideos() {
        return videos;
    }


    
    
    
}
