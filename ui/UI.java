package ui;

import bl.entidades.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.animation.PauseTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;

public class UI extends Application {


    /*init*/
    protected static Stage initWindow = new Stage();
    public static Stage loginWindow = new Stage();
    public static Stage signInWindow = new Stage();
    protected static Stage principal = new Stage();
    public static final Button importar = new Button("Importar");
    public static final Button confirmImport = new Button("Importar Cancion");
    public static final Button cerrar = new Button("X");
    public static final Button cerrarSesion = new Button("Cerrar Sesion");
    public static final Button canciones = new Button("Canciones");
    public static final Button cuenta = new Button("Opciones");
    public static final Button artistas = new Button("Artistas");
    public static final Button albumes = new Button("Albumes");
    public static final Button AddPlaylist = new Button("+");
    public static final Label bibliotecaLabel = new Label("Biblioteca");
    public static final Label cuentaLabel = new Label("Cuenta");
    public static final Player player = new Player();
    public static final HBox containerPlaylist = new HBox();
    public static final TextField songName = new TextField();
    public static final TextField artistName = new TextField();
    public static final TextField albumName = new TextField();
    public static final Label importName = new Label("");
    public static final Button browseSong = new Button("Importar desde la PC");
    public static final BorderPane mainBorder = new BorderPane();
    public static final VBox navigation = new VBox(20);
    public static final Label playlists = new Label("Playlists");
    public static ImageView importImage;
    public static Button loginButton = new Button("⮞");
    public static String retornando;
    public static Button close = new Button();
    public static Button closeSignIn = new Button();
    public static final VBox box = new VBox(5);
    public static int cent = 0;
    public static int chosen;
    public static int chosen2;
    public static Cliente usuarioActual = new Cliente("Joseph", "", "Mejias Porras",
            "capricornjoe", "1234", "jmejiasp@ucenfotec.ac.cr", LocalDate.parse("1994-01-10"), "costarica", "");
    public static TextField correo = new TextField();
    public static TextField correoRegistro = new TextField();
    public static PasswordField contrasena = new PasswordField();
    public static PasswordField contrasenaRegistro = new PasswordField();
    public static Label espacio2 = new Label();
    private static double xOffset = 0;
    private static double yOffset = 0;
    public static Button crearCuenta = new Button("Crear una cuenta");

    public UI() {

    }

    public static void loadRandom() throws FileNotFoundException {
        Image random;
        File imagen1 = new File("C:\\custom.png");
        FileInputStream inputstream;
        inputstream = new FileInputStream(imagen1);
        Image preview11 = new Image(inputstream);
        importImage.setImage(preview11);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private static Image convertImage(String path, double width, double height, boolean preserveRatio, boolean smooth)
            throws FileNotFoundException {
        File file = new File(path);
        FileInputStream stream = new FileInputStream(file);
        Image output = new Image(stream, width, height, preserveRatio, smooth);
        return output;
    }

    /* Main Cliente */
    public static void loadMainCliente(Persona cliente, Stage mainWindow)
            throws FileNotFoundException {
        try {
            UI.navigation.getChildren().addAll(UI.cuentaLabel, UI.cuenta, UI.importar, UI.bibliotecaLabel, UI.canciones, UI.albumes, UI.artistas, containerPlaylist, box);
            containerPlaylist.getChildren().addAll(UI.playlists, UI.AddPlaylist);

        } catch (IllegalArgumentException ex) {

        }
        UI.AddPlaylist.getStyleClass().add("addPlaylist");
        HBox topMenu = new HBox();
        HBox closeMenu = new HBox();
        topMenu.setMinWidth(800);
        closeMenu.setMinWidth(1195);
        closeMenu.setAlignment(Pos.TOP_RIGHT);
        closeMenu.getChildren().addAll(cerrar);
        ImageView logo = new ImageView(convertImage("C:\\logo.png", 273.06, 106.25, true, true));
        topMenu.getStyleClass().add("topMenu");
        topMenu.getChildren().addAll(logo, closeMenu);
        topMenu.setOnMousePressed(e -> {
            xOffset = mainWindow.getX() - e.getScreenX();
            yOffset = mainWindow.getY() - e.getScreenY();
        });
        topMenu.setOnMouseDragged(e -> {
            mainWindow.setX(e.getScreenX() + xOffset);
            mainWindow.setY(e.getScreenY() + yOffset);
        }
        );

        cerrar.getStyleClass().add("closeButton");
        /*LeftBar*/

        navigation.getStyleClass().add("navigationBar");
        navigation.setAlignment(Pos.TOP_LEFT);

        /* bottomBar */
        HBox bottomBar = new HBox();
        HBox leftBottom = new HBox();
        HBox middleBottom = new HBox();
        HBox rightBottom = new HBox();

        bottomBar.getStyleClass().add("bottomBar");
        leftBottom.getStyleClass().add("leftBottom");
        middleBottom.getStyleClass().add("middleBottom");
        rightBottom.getStyleClass().add("rightBottom");

        bottomBar.getChildren().addAll(leftBottom, middleBottom, rightBottom);

        /*Estructura*/
        mainBorder.setBottom(player);
        mainBorder.setTop(topMenu);
        mainBorder.setLeft(navigation);
        mainBorder.setMinHeight(950);
        mainBorder.setMinWidth(1300);

        try {
            Scene scene = new Scene(mainBorder);
            scene.getStylesheets().add("style.css");
            mainWindow.initStyle(StageStyle.UNDECORATED);
            mainWindow.setScene(scene);
        } catch (IllegalArgumentException ex) {

        }

    }

    public static File browseSong(Stage stage) {

        FileChooser getSong = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("WAV files (*.wav)", "*.wav");
        getSong.getExtensionFilters().add(extFilter);
        getSong.getExtensionFilters().add(extFilter2);
        getSong.setTitle("Get song");
        return getSong.showOpenDialog(stage);

    }

    /* recibe objetos de control y devuelve escena de Importar cancion */
    public static VBox importScene() throws FileNotFoundException {

        HBox importAux = new HBox();
        File imagen1 = new File("C:\\custom.png");
        FileInputStream inputstream;
        inputstream = new FileInputStream(imagen1);
        Image preview11 = new Image(inputstream);
        importImage = new ImageView(preview11);
        importImage.setFitHeight(150);
        importImage.setFitWidth(150);
        songName.setPromptText("Nombre de la canción");
        artistName.setPromptText("Nombre del artista");
        albumName.setPromptText("Nombre del album");
        confirmImport.setText("Crear canción");
        VBox importLayout = new VBox(10);
        importAux.getChildren().addAll(browseSong, importName);
        importLayout.getChildren().addAll(importAux, importImage, songName, artistName, albumName, confirmImport);
        importLayout.getStyleClass().add("importScene");

        return importLayout;
    }

    public static VBox optionsScene() {

        Label nombreActual = new Label("Joe");
        Button editarNombre = new Button("editar");

        return new VBox();
    }

    public static VBox albumScene(ArrayList<Album> albumes) {

        VBox contenedor = new VBox();
        TilePane display = new TilePane(20, 20);
        display.setPadding(new Insets(50, 50, 50, 50));
        Label header = new Label("Mis albumes");
        int counter = 0;
        String[] nombreArtista = new String[albumes.size()];
        for (int i = 0; i < albumes.size(); i++) {
            boolean centinela = false;
            String measure = "";

            for (int j = 0; j < nombreArtista.length; j++) {
                measure = albumes.get(i).getNombre();
                try {
                    centinela = nombreArtista[j].equals(albumes.get(i).getNombre());
                } catch (NullPointerException ex) {

                }

            }

            if (!centinela) {
                nombreArtista[i] = measure;
                ImageView albumView = new ImageView(albumes.get(i).getImagen());
                albumView.setFitHeight(200);
                albumView.setFitWidth(200);
                Label goToAlbum = new Label(albumes.get(i).getNombre());
                goToAlbum.setMinWidth(200);
                VBox auxNoseCuanto = new VBox();
                auxNoseCuanto.setAlignment(Pos.CENTER);
                auxNoseCuanto.getChildren().addAll(albumView, goToAlbum);
                auxNoseCuanto.setStyle("-fx-cursor:hand");
                Album album1 = albumes.get(i);
                auxNoseCuanto.setOnMouseClicked(e -> {
                    loadAlbumView(album1);
                });
                display.getChildren().addAll(auxNoseCuanto);
            }
        }
        contenedor.getChildren().addAll(header, display);
        return contenedor;
    }

    public final static void loadAlbumView(Album album) {

        VBox layout = new VBox();

        ObservableList<Cancion> listado = FXCollections.observableArrayList();
        TableView<Cancion> songsLayout = new TableView();
        songsLayout.getStyleClass().add("artistsLayout");

        for (int i = 0; i < album.getLista().size(); i++) {
            listado.add(album.getLista().get(i));
        }

        /* Columna nombre */
        TableColumn<Cancion, String> nombre = new TableColumn<>("Nombre");
        nombre.setMinWidth(350);
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Cancion, String> artista = new TableColumn<>("Artista");
        artista.setMinWidth(350);
        artista.setCellValueFactory(new PropertyValueFactory<>("listaArtistas"));
        TableColumn numberCol = new TableColumn("");
        numberCol.setCellValueFactory(new Callback<CellDataFeatures<Cancion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Cancion, String> p) {
                return new ReadOnlyObjectWrapper(songsLayout.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        numberCol.setMinWidth(109);

        songsLayout.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {

                if (!event.getTarget().toString().equals("TableColumnHeader[id=null, styleClass=column-header table-column]")) {

                    if (songsLayout.getSelectionModel().getSelectedItem() == null || songsLayout.getSelectionModel().getSelectedItem().equals(player.currentSong)) {
                        if ("PLAYING".equals(player.getStatus())) {
                            player.pause();
                        } else {
                            player.resume();
                        }
                    } else {
                        try {
                            player.stop();
                        } catch (NullPointerException ex) {

                        }
                        try {
                            Cancion cancion = songsLayout.getSelectionModel().getSelectedItem();
                            player.play(cancion);
                        } catch (NullPointerException | MalformedURLException | FileNotFoundException ex) {
                        }
                    }
                }
            }
        });

        songsLayout.setItems(listado);
        songsLayout.getColumns().addAll(numberCol, nombre, artista);
        Label label = new Label(album.getNombre());
        ImageView previewImagen = new ImageView(); //no tiene la imagen del album
        previewImagen.setFitHeight(180);
        previewImagen.setFitWidth(180);
        HBox containerHeader = new HBox(20);
        containerHeader.getChildren().addAll(previewImagen, label);
        layout.getChildren().addAll(containerHeader, songsLayout);
        layout.getStyleClass().add("songsLayout");

        mainBorder.setCenter(layout);
    }

    public static VBox artistScene(ArrayList<Artista> artistas) {

        VBox layout = new VBox(5);

        ObservableList<Artista> listado = FXCollections.observableArrayList();
        ArrayList<Artista> lista = artistas;

        lista.forEach(e -> {
            e.toString();
        });
        TableView<Artista> songsLayout = new TableView();
        songsLayout.getStyleClass().add("artistsLayout");
        for (int i = 0; i < lista.size(); i++) {
            listado.add(lista.get(i));
        }

        /* Columna nombre */
        TableColumn<Artista, String> nombre = new TableColumn<>("Nombre");
        nombre.setMinWidth(580);
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Artista, String> nombreArtistico = new TableColumn<>("Nombre Artistico");
        nombreArtistico.setMinWidth(588);
        nombreArtistico.setCellValueFactory(new PropertyValueFactory<>("nombreArtistico"));

        songsLayout.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                System.out.println(songsLayout.getSelectionModel().getSelectedItem());
            }
        });

        nombre.setMinWidth(300);
        songsLayout.setItems(listado);
        songsLayout.getColumns().addAll(nombre, nombreArtistico);
        layout.getChildren().add(songsLayout);

        return layout;
    }

    public static TableView playlistView(ArrayList<Artista> artistas) {

        ObservableList<Artista> listado = FXCollections.observableArrayList();
        ArrayList<Artista> lista = artistas;
        TableView<Artista> songsLayout = new TableView();

        for (int i = 0; i < lista.size(); i++) {
            listado.add(lista.get(i));
        }

        /* Columna nombre */
        TableColumn<Artista, String> nombre = new TableColumn<>("Título");
        nombre.setMinWidth(200);
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        songsLayout.setItems(listado);
        songsLayout.getColumns().addAll(nombre);

        return songsLayout;
    }

    public static String createPlaylist() {

        Stage stageConfirm = new Stage();
        stageConfirm.setMaxWidth(250);
        stageConfirm.setMaxHeight(250);
        Label label = new Label("Escribe el nombre de la Playlist Nueva");
        Button aceptar = new Button("Aceptar");
        Button cancelar = new Button("Cancelar");
        TextField input = new TextField();
        VBox layout = new VBox(5);
        layout.getChildren().addAll(label, input, aceptar, cancelar);
        Scene scene = new Scene(layout);
        stageConfirm.initStyle(StageStyle.UNDECORATED);
        stageConfirm.setScene(scene);
        scene.getStylesheets().add("style4.css");

        aceptar.setOnAction(e -> {
            retornando = input.getText();
            stageConfirm.close();
        });

        cancelar.setOnAction(e -> {
            stageConfirm.close();
        });

        stageConfirm.showAndWait();

        return retornando;

    }

    public static VBox songsScene(ArrayList<Cancion> lista) {

        VBox layout = new VBox();

        ObservableList<Cancion> listado = FXCollections.observableArrayList();
        TableView<Cancion> songsLayout = new TableView();
        songsLayout.getStyleClass().add("artistsLayout");

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
            listado.add(lista.get(i));
        }

        TableColumn<Cancion, String> nombre = new TableColumn<>("Nombre");
        nombre.setMinWidth(580);
        nombre.setStyle("-fx-padding: 0 0 0 20;-fx-alignment: center_left;");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Cancion, String> artista = new TableColumn<>("Artista");
        artista.setCellValueFactory(new PropertyValueFactory<>("listaArtistas"));
        artista.setMinWidth(588);
        artista.setStyle("-fx-padding:0px;-fx-alignment: center_left;");

        songsLayout.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {

                if (!event.getTarget().toString().equals("TableColumnHeader[id=null, styleClass=column-header table-column]")) {

                    if (songsLayout.getSelectionModel().getSelectedItem() == null || songsLayout.getSelectionModel().getSelectedItem().equals(player.currentSong)) {
                        if ("PLAYING".equals(player.getStatus())) {
                            player.pause();
                        } else {
                            player.resume();
                        }
                    } else {
                        try {
                            player.stop();
                        } catch (NullPointerException ex) {

                        }
                        try {
                            Cancion cancion = songsLayout.getSelectionModel().getSelectedItem();
                            player.play(cancion);
                        } catch (NullPointerException | MalformedURLException | FileNotFoundException ex) {
                        }
                    }
                }

            }
        });

        songsLayout.setItems(listado);
        songsLayout.getColumns().addAll(nombre, artista);
        Label label = new Label("Mis canciones");
        label.getStyleClass().add("headerSongsLayout");
        layout.getChildren().addAll(label, songsLayout);
        layout.getStyleClass().add("songsLayout");

        return layout;
    }

    public static HBox artistScene(Playlist playlist) {

        ArrayList<Cancion> lista = null; //falta aca
        HBox songsLayout = new HBox();

        for (int i = 0; i < lista.size(); i++) {
            Button button = new Button(lista.get(i).getNombre());
            songsLayout.getChildren().addAll(button);
        }

        return songsLayout;
    }

    /* Animacion de Bienvenida */
    public void loadLogo() throws InterruptedException, FileNotFoundException {

        Media media = new Media(new File("C:\\logo.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        Group root = new Group();
        root.getChildren().add(mediaView);
        VBox welcomeBox = new VBox();
        welcomeBox.getChildren().add(root);
        Scene welcome = new Scene(welcomeBox);

        welcomeBox.setMinHeight(342);
        welcomeBox.setMinWidth(342);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));

        delay.setOnFinished(e -> {
            initWindow.close();
        });
        delay.play();
        welcome.getStylesheets().add("style2.css");
        initWindow.initStyle(StageStyle.TRANSPARENT);
        initWindow.setScene(welcome);
        initWindow.showAndWait();

    }

    public int initApp() throws FileNotFoundException, InterruptedException {

        /* TOP MENU */
        HBox topMenu = new HBox();

        Image closeButton = convertImage("C:\\cancel.png", 40, 40, true, true);
        ImageView closeButtonIv = new ImageView(closeButton);
        Image minimizeButton = convertImage("C:\\minimize2.png", 40, 40, true, true);
        ImageView minimizeButtonIv = new ImageView(minimizeButton);
        close.setGraphic(closeButtonIv);

        Button minimize = new Button();
        minimize.setGraphic(minimizeButtonIv);
        topMenu.getChildren().addAll(minimize, close);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        minimize.setOnAction(e -> {
            loginWindow.setIconified(true);
        });

        topMenu.getStyleClass().add("topMenu");

        /* Login Scene */
 /*login button*/
        VBox layout = new VBox();

        /* correo y pw*/
        correo.setPromptText("Correo electrónico");
        layout.setPadding(new Insets(5, 5, 5, 5));

        layout.setAlignment(Pos.TOP_CENTER);
        contrasena.setPromptText("Contraseña");
        Label iniciaSesion = new Label("Inicia Sesión");
        Label espacio = new Label();
        Label espacio3 = new Label();
        Label espacio4 = new Label();
        loginButton.getStyleClass().add("loginButton");
        espacio.getStyleClass().add("espacio");
        espacio2.getStyleClass().add("espacio2");
        espacio3.getStyleClass().add("espacio");
        espacio4.getStyleClass().add("espacio");
        correo.getStyleClass().add("input");
        contrasena.getStyleClass().add("input");

        PseudoClass empty = PseudoClass.getPseudoClass("empty");
        contrasena.pseudoClassStateChanged(empty, contrasena.getText().isEmpty());
        contrasena.textProperty().isEmpty().addListener((obs, wasEmpty, isNowEmpty)
                -> contrasena.pseudoClassStateChanged(empty, isNowEmpty));

        correo.pseudoClassStateChanged(empty, correo.getText().isEmpty());
        correo.textProperty().isEmpty().addListener((obs, wasEmpty, isNowEmpty)
                -> correo.pseudoClassStateChanged(empty, isNowEmpty));

        /* reset y create */
        Button resetPW = new Button("Restablecer contraseña");

        resetPW.getStyleClass().add("dwnButton");
        crearCuenta.getStyleClass().add("dwnButton");

        ImageView logoLogin = new ImageView(convertImage("C:\\logo2.png", 300, 300, true, true));

        correo.setText("ejemplo@ejemplo.com");
        contrasena.setText("1234");

        layout.getChildren().addAll(topMenu, logoLogin, iniciaSesion, espacio3, correo, espacio, contrasena, espacio2, loginButton, espacio4, resetPW, crearCuenta);
        layout.setMinWidth(400);
        layout.setMinHeight(820);

        try {
            loginWindow.initStyle(StageStyle.TRANSPARENT);
        } catch (IllegalStateException ex) {

        }
        topMenu.setOnMousePressed(e -> {
            resize();
            xOffset = loginWindow.getX() - e.getScreenX();
            yOffset = loginWindow.getY() - e.getScreenY();
        });
        topMenu.setOnMouseDragged(e -> {
            loginWindow.setX(e.getScreenX() + xOffset);
            loginWindow.setY(e.getScreenY() + yOffset);
        }
        );
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style3.css");
        loginWindow.setScene(scene);
        loginWindow.showAndWait();
        return chosen;
    }

    public static <S, T> void columnReorder(TableView table, TableColumn<S, T>... columns) {
        table.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;

            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    table.getColumns().setAll(columns);
                    this.suspended = false;
                }
            }
        });
    }

    public Persona getUsuarioActual() {
        return usuarioActual;
    }

    public static Stage getMainWindow() {
        return principal;
    }

    public static int createAccount() throws FileNotFoundException {

        /* TOP MENU */
        HBox topMenu = new HBox();

        Image closeButton = convertImage("C:\\cancel.png", 40, 40, true, true);
        ImageView closeButtonIv = new ImageView(closeButton);
        Image minimizeButton = convertImage("C:\\minimize2.png", 40, 40, true, true);
        ImageView minimizeButtonIv = new ImageView(minimizeButton);
        closeSignIn.setGraphic(closeButtonIv);

        Button minimize = new Button();
        minimize.setGraphic(minimizeButtonIv);
        topMenu.getChildren().addAll(minimize, close);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        minimize.setOnAction(e -> {
            signInWindow.setIconified(true);
        });

        topMenu.getStyleClass().add("topMenu");

        topMenu.getStyleClass().add("topMenu");

        /* Login Scene */
        VBox layout = new VBox();

        /* correo y pw*/
        correoRegistro.setPromptText("Correo electrónico");
        layout.setPadding(new Insets(5, 5, 5, 5));

        layout.setAlignment(Pos.TOP_CENTER);
        contrasenaRegistro.setPromptText("Contraseña");
        Label iniciaSesion = new Label("Inicia Sesión");
        Label espacio = new Label();
        Label espacio3 = new Label();
        Label espacio4 = new Label();
        loginButton.getStyleClass().add("loginButton");
        espacio.getStyleClass().add("espacio");
        espacio3.getStyleClass().add("espacio");
        espacio4.getStyleClass().add("espacio");
        correoRegistro.getStyleClass().add("input");
        contrasenaRegistro.getStyleClass().add("input");

        PseudoClass empty = PseudoClass.getPseudoClass("empty");
        contrasenaRegistro.pseudoClassStateChanged(empty, contrasena.getText().isEmpty());
        contrasenaRegistro.textProperty().isEmpty().addListener((obs, wasEmpty, isNowEmpty)
                -> contrasenaRegistro.pseudoClassStateChanged(empty, isNowEmpty));

        correoRegistro.pseudoClassStateChanged(empty, correoRegistro.getText().isEmpty());
        correoRegistro.textProperty().isEmpty().addListener((obs, wasEmpty, isNowEmpty)
                -> correoRegistro.pseudoClassStateChanged(empty, isNowEmpty));

        /* reset y create */
        Button back = new Button();

        ImageView backImage = new ImageView(convertImage("C:\\logoB.png", 100, 100, true, true));
        back.setGraphic(backImage);
        layout.getChildren().addAll(topMenu, backImage, iniciaSesion, espacio3, correoRegistro, espacio, contrasenaRegistro);
        layout.setMinWidth(400);
        layout.setMinHeight(820);

        try {
            signInWindow.initStyle(StageStyle.TRANSPARENT);
        } catch (IllegalStateException ex) {

        }
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style3.css");
        signInWindow.setScene(scene);
        signInWindow.showAndWait();

        return chosen2;
    }

    private void resize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        System.out.println("Height: " + height + " Width: " + width);
    }

}
