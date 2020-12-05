/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bl.entidades.Cancion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public final class Player extends VBox {

    Media mediaPlayer;
    MediaPlayer player;

    Image image;
    MediaView view;
    final Slider time = new Slider();
    Slider vol = new Slider();
    Button PlayButton = new Button("⏸");
    Label volume = new Label("Volume: ");
    HBox abajo = new HBox();
    HBox info = new HBox();
    VBox data = new VBox();
    HBox controls = new HBox();
    HBox volumeBox = new HBox();
    ImageView previewImagen = new ImageView();
    HBox contenedorImagen = new HBox();
    Label songName = new Label("");
    Label songArtist = new Label("");
    Cancion currentSong;

    public Player() {
        vol.setMin(0.0);
        vol.setMax(100);
        vol.setValue(100);
        vol.valueProperty().addListener((Observable ov) -> {

            if (vol.isPressed()) {
                try {
                    player.setVolume(vol.getValue() / 100);
                } catch (NullPointerException ex) {

                }
            }
            StackPane trackPane = (StackPane) vol.lookup(".track");
            String style = String.format("-fx-background-color: linear-gradient(to right, #2D819D %d%%, #969696 %d%%);",
                    vol.valueProperty().intValue(), vol.valueProperty().intValue());
            trackPane.setStyle(style);
        });
        getStyleClass().add("player");
        File imagen1 = new File("C:\\Users\\capri\\Pictures\\custom.png");
        FileInputStream inputstream;
        Image preview11 = null;
        try {
            inputstream = new FileInputStream(imagen1);
            preview11 = new Image(inputstream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        previewImagen = new ImageView(preview11);
        previewImagen.setFitHeight(80);
        previewImagen.setFitWidth(80);
        songName.getStyleClass().add("songName");
        songArtist.getStyleClass().add("songArtist");
        contenedorImagen.getChildren().addAll(previewImagen);
        contenedorImagen.setAlignment(Pos.CENTER_LEFT);
        contenedorImagen.getStyleClass().add("containerImagen");
        data.getChildren().addAll(songName, songArtist);
        data.setAlignment(Pos.CENTER_LEFT);
        info.getChildren().addAll(contenedorImagen, data);
        info.setAlignment(Pos.CENTER_LEFT);
        info.setMinWidth(500);
        controls.getChildren().addAll(PlayButton);
        controls.setMinWidth(500);
        controls.setAlignment(Pos.CENTER);
        volumeBox.getChildren().addAll(volume, vol);
        volumeBox.setMinWidth(480);
        volumeBox.setAlignment(Pos.CENTER_RIGHT);
        abajo.getChildren().addAll(info, controls, volumeBox);
        abajo.setAlignment(Pos.BOTTOM_CENTER);
        PlayButton.getStyleClass().add("playButton");
        volume.getStyleClass().add("volumeLabel");
        vol.getStyleClass().add("volume");
        time.getStyleClass().add("timeSlider");
        getChildren().addAll(time, abajo);
    }

    public void play(String file) {

        mediaPlayer = new Media(file);
        player = new MediaPlayer(mediaPlayer);
        view = new MediaView(player);
        time.setMin(0.0);
        PlayButton.setOnAction(e -> {
            MediaPlayer.Status status = player.getStatus();
            if (status == MediaPlayer.Status.PLAYING) {

                if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {

                    player.seek(player.getStartTime());
                    player.play();
                } else {

                    player.pause();

                    PlayButton.setText("▶");
                }
            }
            if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
                player.play();
                PlayButton.setText("⏸");
            }
        });

        player.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration duration, Duration current) -> {
            time.setValue(current.toSeconds());

        });

        player.totalDurationProperty().addListener((ObservableValue<? extends Duration> observable, Duration duration, Duration current) -> {
            time.setMax(current.toSeconds());
        });

        time.valueProperty().addListener((Observable ov) -> {

            double progress = time.valueProperty().doubleValue() / (time.getMax() / 100);
            double ok = Math.floor(progress * 10000);

            if (time.isPressed()) {
                player.seek(Duration.seconds(time.getValue()));
            }

            StackPane trackPane = (StackPane) time.lookup(".track");
            String style = String.format("-fx-background-color: linear-gradient(to right, #2D819D %f%%, #969696 %f%%);",
                    ok / 10000, ok / 10000);
            trackPane.setStyle(style);

        });

        player.setVolume(vol.getValue() / 100);
        player.play();
    }

    public void play(Cancion cancion) throws MalformedURLException, FileNotFoundException {

        currentSong = cancion;
        File file = new File(cancion.getRuta());

        songName.setText(cancion.getNombre());
        songArtist.setText(cancion.getListaArtistas());
        mediaPlayer = new Media(file.toURI().toString());
        mediaPlayer.getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> c) -> {
            if (c.wasAdded()) {
                if ("image".equals(c.getKey())) {
                    image = (Image) c.getValueAdded();
                }
                System.out.println(c.getKey() + " " + c.getValueAdded().toString());
            }
            previewImagen.setImage(image);
        });
        
        player = new MediaPlayer(mediaPlayer);
        view = new MediaView(player);
        time.setMin(0.0);
        PlayButton.setOnAction(e -> {
            MediaPlayer.Status status = player.getStatus();
            if (status == MediaPlayer.Status.PLAYING) {

                if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {

                    player.seek(player.getStartTime());
                    player.play();
                } else {

                    player.pause();

                    PlayButton.setText("▶");
                }
            }
            if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
                player.play();
                PlayButton.setText("⏸");
            }
        });

        player.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration duration, Duration current) -> {
            time.setValue(current.toSeconds());

        });

        player.totalDurationProperty().addListener((ObservableValue<? extends Duration> observable, Duration duration, Duration current) -> {
            time.setMax(current.toSeconds());
        });

        time.valueProperty().addListener((Observable ov) -> {

            double progress = time.valueProperty().doubleValue() / (time.getMax() / 100);
            double ok = Math.floor(progress * 10000);

            if (time.isPressed()) {
                player.seek(Duration.seconds(time.getValue()));
            }

            StackPane trackPane = (StackPane) time.lookup(".track");
            String style = String.format("-fx-background-color: linear-gradient(to right, #2D819D %f%%, #969696 %f%%);",
                    ok / 10000, ok / 10000);
            trackPane.setStyle(style);

        });
        
        player.setVolume(vol.getValue() / 100);
        player.play();
    }

    public void stop() {
        player.stop();
    }

    public void resume() {
        player.play();
    }

    public void pause() {
        player.pause();
    }

    public String getStatus() {
        return player.getStatus().toString();
    }
}
