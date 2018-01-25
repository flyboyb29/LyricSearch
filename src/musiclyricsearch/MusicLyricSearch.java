/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musiclyricsearch;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author B-29
 */
public class MusicLyricSearch extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        java.awt.EventQueue.invokeLater(() -> {
            new LyricSearch().setVisible(true);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
