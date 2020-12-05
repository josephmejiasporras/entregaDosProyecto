package tl.controlador;

import ui.ConfirmBox;
import ui.UI;
import bl.entidades.*;
import bl.logica.Gestor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.MapChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.stage.Stage;

public class Controlador extends Application {

    /*init Controlador*/
    private final UI ui = new UI();
    private File importAux;
    private String title;
    private String artist;
    private String albumName;
    private Image image;
    private Cliente clienteActual;
    private final Gestor gestor = new Gestor();
    public boolean sessionWard = false;

    public static void ejecutar() throws FileNotFoundException {
        Controlador.launch(Controlador.class);
    }

    /*ejecutando*/
    @Override
    public void start(Stage primaryStage) throws Exception {

        loadLogin();

        gestor.listarClientes();
        int action = ui.initApp();

        switch (action) {

            case 1:
                interfazCliente(clienteActual);
                /*while(sessionWard){
                        action = ui.initApp();
                        interfazCliente(usuarioActual2);
                    }*/
                break;
            case 2:
                //action = UI.createAccount();
                break;
            case -1:
                System.exit(0);
                break;
        }

        System.out.println("El App ha finalizado");

    }

    /*interfaz del Cliente*/
    private void interfazCliente(Cliente cliente) throws FileNotFoundException, MalformedURLException, SQLException {

        /*init objetos de Control*/
        Stage stage = UI.getMainWindow();

        ArrayList<Playlist> allPlaylists = gestor.getPlaylistsCliente(cliente.getId());

        for (int i = 0; i < allPlaylists.size(); i++) {
            Button inside = new Button(allPlaylists.get(i).getNombre());
            UI.box.getChildren().addAll(inside);
        }

        /*load UI screen*/
        UI.loadMainCliente(cliente, stage);

        /**
         * *acciones***
         */
        /* cerrar app */
        UI.cerrar.setOnAction(e -> {

            //  if (ConfirmBox.display("", "Esta seguro que desea salir de la aplicación?")) { }
            stage.close();
            sessionWard = false;
        });

        /* Cerrar Sesion*/
        UI.cerrarSesion.setOnAction(e -> {
            if (ConfirmBox.display("", "Esta seguro que desea cerrar sesion?")) {
                stage.close();
                sessionWard = true;
            }

        });

        /*NAVIGATION/*
        
        /*mostrar panel Importar*/
        UI.importar.setOnAction(e -> {

            try {
                UI.mainBorder.setCenter(UI.importScene());
            } catch (FileNotFoundException ex) {

            }

        });

        UI.albumes.setOnAction(e -> {

            try {
                UI.mainBorder.setCenter(UI.albumScene(gestor.getAlbumesCliente(cliente.getId())));
            } catch (SQLException ex) {

            } catch (MalformedURLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //mostrar playlist de Canciones Favoritas
        UI.canciones.setOnAction(e -> {

            try {
                UI.mainBorder.setCenter(UI.songsScene(gestor.getCancionesCliente(cliente.getId())));
            } catch (SQLException | MalformedURLException ex) {
            }

        });

        //Mostrar artistas
        UI.artistas.setOnAction(e -> {

            try {
                UI.mainBorder.setCenter(UI.artistScene(gestor.getArtistasCliente(cliente.getId())));
            } catch (SQLException ex) {

            }

        });

        UI.AddPlaylist.setOnAction(e -> {

            String nombrePlaylist = UI.createPlaylist();

            if (nombrePlaylist != null) {
                Playlist playlist = new Playlist(nombrePlaylist);
                try {
                    gestor.addPlaylist(playlist, cliente.getId());
                } catch (SQLException ex) {
                }
                UI.box.getChildren().removeAll();
                ArrayList<Playlist> allPlaylists_ = new ArrayList<>();
                try {
                    allPlaylists_ = gestor.getPlaylistsCliente(cliente.getId());
                } catch (SQLException ex) {
                }
                for (int i = 0; i < allPlaylists_.size(); i++) {

                    Button inside = new Button(allPlaylists_.get(i).getNombre());
                    UI.box.getChildren().addAll(inside);
                }
            }

        });

        /*IMPORTAR*/
 /*Click Importar Cancion*/
        UI.confirmImport.setOnAction((ActionEvent e) -> {

            try {
                Cancion cancion = new Cancion();
                cancion.setNombre(UI.songName.getText());
                cancion.setRuta(importAux.getPath());
                cancion.setFechaLanzamiento(LocalDate.parse("2020-01-01"));
                Artista artista = new Artista();
                artista.setNombre(UI.artistName.getText());
                artista.setSegundoNombre("");
                artista.setApellidos("");
                artista.setPais("");
                artista.setDescripcion("");
                artista.setFechaNacimiento(LocalDate.parse("2020-01-01"));
                artista.setFechaDefuncion(LocalDate.parse("2020-01-01"));
                artista.setNombreArtistico(UI.artistName.getText());
                Album album = new Album();
                album.setNombre(UI.albumName.getText());

                
                cancion = gestor.crearCancion(cancion);
                artista = gestor.crearArtista(artista);
                album = gestor.crearAlbum(album);
                
                System.out.println(cancion.toString());

                gestor.anadirArtistaFavorito(artista.getId(), cliente.getId());
                gestor.anadirAlbumFavorito(album.getId(), cliente.getId());
                gestor.anadirCancionFavorita(cancion.getId(), cliente.getId());

                UI.artistName.setText("");
                UI.songName.setText("");
                UI.albumName.setText("");
                UI.importName.setText("");
                UI.loadRandom();
            } catch (FileNotFoundException | SQLException | MalformedURLException ex) {

            }

            try {
                gestor.listarAlbumes();
                gestor.listarCanciones();
                gestor.listarArtistas();
            } catch (SQLException | MalformedURLException ex) {
            }

        });

        UI.browseSong.setOnAction(e -> {

            importAux = UI.browseSong(stage);
            UI.importName.setText(importAux.getName());
            System.out.println(importAux.getPath());
            Media media = new Media(importAux.toURI().toString());
            media.getMetadata().addListener((Change<? extends String, ? extends Object> c) -> {
                if (c.wasAdded()) {
                    if ("artist".equals(c.getKey())) {
                        artist = c.getValueAdded().toString();
                    } else if ("title".equals(c.getKey())) {
                        title = c.getValueAdded().toString();
                    } else if ("album".equals(c.getKey())) {
                        albumName = c.getValueAdded().toString();
                    } else if ("image".equals(c.getKey())) {
                        image = (Image) c.getValueAdded();
                    }
                    System.out.println(c.getKey() + " " + c.getValueAdded().toString());
                }
                if (artist == null || title == null || albumName == null) {

                } else {
                    UI.songName.setText(title);
                    UI.artistName.setText(artist);
                    UI.albumName.setText(albumName);
                    UI.importImage.setImage(image);
                }

            });

        });

        /*showAndWait*/
        stage.showAndWait();
    }

    private void loadLogin() throws InterruptedException, FileNotFoundException {

        //ui.loadLogo();
        UI.close.setOnAction(e -> {
            /*if (ConfirmBox.display("", "Está seguro que desea cerrar?")) {
                
            }*/
            UI.loginWindow.close();
            UI.chosen = -1;
        });

        UI.loginButton.setOnAction(e -> {
            boolean centinela = false;
            try {
                for (Cliente cliente : gestor.getClientes()) {
                    if (cliente.getCorreo().equals(UI.correo.getText().trim().toLowerCase()) && cliente.getContrasena().equals(UI.contrasena.getText().trim())) {
                        centinela = true;
                        clienteActual = cliente;
                        UI.chosen = 1;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("No cargo a los clientes!");
            }
            if (centinela) {
                UI.loginWindow.close();
            } else {
                UI.espacio2.setText("Login Invalido");
                UI.espacio2.setStyle("-fx-text-fill: red");
            }
        });

        UI.crearCuenta.setOnAction(e -> {
            UI.chosen = 2;
            UI.loginWindow.close();
        });
    }

}
