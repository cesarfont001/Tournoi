package view;

import ctrl.Ctrl;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Match;
import model.MatchEnum;
import model.Participant;
import model.Resultat;
import model.Tournoi;
import model.TournoiList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fontaine César
 */
public final class View extends BorderPane implements Observer {

    //TITLE + FENETRE SIZE
    private final int HEIGHT = 700;
    private final int WIDTH = 850;
    private final String TITLE = "Gestion de tournois";

    //CONTROLLER + LIST
    private final Ctrl ctrl;
    private TournoiList tL = new TournoiList();

    //COMBOBOX
    ComboBox<String> joueur1 = new ComboBox<>();
    ComboBox<String> joueur2 = new ComboBox<>();
    ComboBox<Resultat> resultat = new ComboBox<>();

    //BOUTONS 
    Button btnAddMatch = new Button("Ajouter");
    Button btnEditMatch = new Button("Editer");
    Button btnDelMatch = new Button("Supprimer");

    //LABEL
    Label labelTournoi = new Label("Tournois");
    Label labelInscrit = new Label("Inscrits");
    Label labelMatch = new Label("Matchs");
    Label ok = new Label("");
    Label nok = new Label("");

    //LISTVIEW
    ListView<String> lvTournoi = new ListView<>();
    ListView<String> lvInscrit = new ListView<>();

    //TABLEVIEW
    TableView<Match> tvMatch = new TableView<>();
    TableColumn<Match, Participant> columnJoueur1 = new TableColumn<>("Joueur 1");
    TableColumn<Match, Participant> columnJoueur2 = new TableColumn<>("Joueur 2");
    TableColumn<Match, Resultat> columnResultat = new TableColumn<>("Résultat");

    //HBOX & VBOX
    VBox hboxTournoi = new VBox();
    VBox hboxInscrit = new VBox();
    HBox hboxMatchBtn = new HBox();
    HBox hboxMatchCombo = new HBox();
    VBox vboxMatch = new VBox();

    // CONTENEUR TOURNOI
    BorderPane bpTournoi = new BorderPane();

    //CONTENEUR INSCRIT
    BorderPane bpInscrit = new BorderPane();

    //CONTAINER MATCH
    BorderPane bpMatch = new BorderPane();

    //BORDERPANE ROOT
    BorderPane root = new BorderPane();

    public View(Stage ps, Ctrl ctrl) throws Exception {

        this.ctrl = ctrl;

        Compo();
        Listener();

        //SCENE      
        Scene scene = new Scene(this, WIDTH, HEIGHT); //THIS = root (extends BorderPane)

        //PRIMARYSTAGE
        ps.setScene(scene);
        ps.setTitle(TITLE);
        ps.setResizable(false);
        ps.getIcons().add(new Image("file:icon.png"));
        ps.show();
    }

    private void Compo() {
        setRoot();
        setTournoiList();
        setInscritsList();
        setMatchList();
        setbutton();
    }

    private void Listener() {
        setTournoiListener();
        setMatchListener();
    }

    //ROOT
    private void setRoot() {
        this.setLeft(bpInscrit);
        this.setCenter(bpMatch);
        this.setTop(bpTournoi);
        this.setPadding(new Insets(5));
        this.setStyle("-fx-background-color: #222426");
    }

    //TOURNOILIST
    private void setTournoiList() {

        setCssTournoiList();
        setContainerTournoiList();
        ctrl.addObserverTournoi(this);
    }

    private void setCssTournoiList() {
        labelTournoi.setStyle("-fx-font-weight: bold");
        labelTournoi.setTextFill(Color.web("#f48024"));
        labelTournoi.setFont(new Font("Arial", 20));
        BorderPane.setAlignment(labelTournoi, Pos.TOP_CENTER);
        lvTournoi.setMaxHeight(280);
        bpTournoi.setPadding(new Insets(5));
    }

    private void setContainerTournoiList() {
        bpTournoi.setTop(hboxTournoi);
        bpTournoi.setCenter(lvTournoi);
        hboxTournoi.getChildren().addAll(labelTournoi, lvTournoi);
        List<String> ls = ctrl.getTournoiList();
        lvTournoi.getItems().addAll(ls);
    }

    //INSCRITS-PARTICIPANTS LIST
    private void setInscritsList() {
        setCssParticipantList();
        setContainerParticipantList();
    }

    private void setCssParticipantList() {
        labelInscrit.setStyle("-fx-font-weight: bold");
        labelInscrit.setTextFill(Color.web("#f48024"));
        labelInscrit.setFont(new Font("Arial", 20));
        bpInscrit.setPadding(new Insets(5));
        BorderPane.setAlignment(labelInscrit, Pos.TOP_CENTER);
    }

    private void setContainerParticipantList() {
        hboxInscrit.getChildren().addAll(labelInscrit, lvInscrit);
        hboxInscrit.setPadding(new Insets(0, 20, 0, 0));
        bpInscrit.setTop(labelInscrit);
        bpInscrit.setCenter(lvInscrit);
    }

    //LIST MATCH
    private void setMatchList() {
        setCssMatchList();
        setContainerMatchList();
        setTableViewMatchList();
    }

    private void setTableViewMatchList() {
        tvMatch.getColumns().addAll(columnJoueur1, columnJoueur2, columnResultat);
        columnJoueur1.setCellValueFactory(new PropertyValueFactory<>("Joueur1"));
        columnJoueur2.setCellValueFactory(new PropertyValueFactory<>("Joueur2"));
        columnResultat.setCellValueFactory(new PropertyValueFactory<>("resultat"));
    }

    private void setContainerMatchList() {
        vboxMatch.getChildren().addAll(hboxMatchBtn, hboxMatchCombo);
        hboxMatchBtn.getChildren().addAll(btnAddMatch, btnEditMatch, btnDelMatch);

        bpMatch.setTop(labelMatch);
        bpMatch.setCenter(tvMatch);
        bpMatch.setBottom(vboxMatch);
        bpMatch.setPadding(new Insets(5));

        hboxMatchCombo.getChildren().addAll(joueur1, joueur2, resultat, nok, ok);

    }

    private void setCssMatchList() {
        labelMatch.setStyle("-fx-font-weight: bold");
        labelMatch.setTextFill(Color.web("#f48024"));
        labelMatch.setFont(new Font("Arial", 20));
        hboxMatchBtn.setPadding(new Insets(10, 5, 10, 5));
        hboxMatchBtn.setSpacing(30.0);
        hboxMatchBtn.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(labelMatch, Pos.TOP_CENTER);
        hboxMatchCombo.setPadding(new Insets(10, 5, 10, 5));
        hboxMatchCombo.setSpacing(20.0);
        hboxMatchCombo.setAlignment(Pos.CENTER);
    }

    //TOURNOI LISTENER
    private void setTournoiListener() {
        lvTournoi.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Integer selected = lvTournoi.getSelectionModel().getSelectedIndex();
                ctrl.tournoiSelection(selected);
            }
        });
    }
    
    //MATCH LISTENER
    private void setMatchListener() {
        setBouttonAddListen();
        setSelectMatchListen();
        setBouttonDelListen();
    }

    private void setBouttonAddListen() {
        btnAddMatch.setOnAction((ActionEvent e) -> {
            String p1 = joueur1.getValue();
            String p2 = joueur2.getValue();
            Resultat r = resultat.getValue();

            if (null == p1 || null == p2 || null == r) {
                ok.setText("");
                nok.setText("Joueur 1, Joueur 2 et Resultat sont requis");
            } else {
                Match m = new Match(new Participant(p1), new Participant(p2), r);

                ArrayList<Match> mas = new ArrayList<>();
                ctrl.selectedTournoi().getMatchs().forEach((match) -> {
                    mas.add(match);
                });

                MatchEnum status = ctrl.MatchValide(m, mas);

                switch (status) {
                    case OK:
                        ok.setText("");
                        nok.setText("OK");
                        ctrl.addMatch(m);
                        joueur1.valueProperty().set(null);
                        joueur2.valueProperty().set(null);
                        resultat.getSelectionModel().clearSelection();
                        break;
                    case PAREIL:
                        ok.setText("");
                        nok.setText("Les adversaires sont identiques");
                        break;
                    case DEJAFAIT:
                        ok.setText("");
                        nok.setText("Ce match a déjà été joué");
                        break;
                }

            }
        });
    }

    private void setSelectMatchListen() {
        tvMatch.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Match>() {
            @Override
            public void changed(ObservableValue<? extends Match> observable, Match oldValue, Match newValue) {
                if (null != newValue) {
                    int selected = tvMatch.getSelectionModel().getSelectedIndex();
                    ctrl.matchSelection(selected);
                } else {
                    ctrl.matchSelection(-1);
                }
            }
        });
    }

    private void setBouttonDelListen() {
        btnDelMatch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ctrl.deleteMatch();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof TournoiList) {
            TournoiList tournoiLiist = (TournoiList) o;
            TournoiList.TypeNotif typeNotif = (TournoiList.TypeNotif) arg;

            switch (typeNotif) {
                case TOURNOI_SELECTED:
                    List<String> playerLs = tournoiLiist.getNomJoueursTournoi();
                    lvInscrit.getItems().setAll(playerLs);
                    ArrayList<Match> mas = new ArrayList<>();
                    tournoiLiist.getMatchs().forEach((match) -> {
                        mas.add(match);
                    });

                    tvMatch.getItems().clear();
                    tvMatch.getItems().addAll(mas);

                    joueur1.getItems().clear();
                    joueur1.getItems().addAll(playerLs);

                    joueur2.getItems().clear();
                    joueur2.getItems().addAll(playerLs);

                    resultat.getItems().setAll(Resultat.values());

                    ok.setText(null);
                    nok.setText(null);

                    btnAddMatch.setDisable(false);

                    break;
                case TOURNOI_UNSELECTED:
                    lvInscrit.getItems().clear();
                    tvMatch.getItems().clear();

                    joueur1.getItems().clear();
                    joueur2.getItems().clear();
                    resultat.getItems().clear();

                    ok.setText(null);
                    nok.setText(null);

                    btnAddMatch.setDisable(true);

                    break;
            }

        } else if (o instanceof Tournoi) {

            Tournoi.TypeNotif typeNotif = (Tournoi.TypeNotif) arg;

            switch (typeNotif) {
                case MATCH_ADD:
                    List<Match> mal = ctrl.selectedTournoi().getMatchs();
                    ArrayList<Match> mas = new ArrayList<>();
                    mal.forEach((match) -> {
                        mas.add(match);
                    });

                    tvMatch.getItems().clear();
                    tvMatch.getItems().addAll(mas);

                    break;
                case MATCH_SELECT:
                    Match selected = tvMatch.getSelectionModel().getSelectedItem();
                    joueur1.getSelectionModel().select(selected.getNomJoueur1());
                    joueur2.getSelectionModel().select(selected.getNomJoueur2());
                    resultat.getSelectionModel().select(selected.getResult());

                    btnAddMatch.setDisable(true);
                    btnEditMatch.setDisable(false);
                    btnDelMatch.setDisable(false);

                    nok.setText(null);
                    ok.setText(null);

                    break;
                case MATCH_UNSELECT:
                    joueur1.valueProperty().set(null);
                    joueur2.valueProperty().set(null);
                    resultat.getSelectionModel().clearSelection();

                    btnAddMatch.setDisable(false);
                    btnEditMatch.setDisable(true);
                    btnDelMatch.setDisable(true);

                    nok.setText(null);
                    ok.setText(null);

                    break;

                case MATCH_DEL:
                    List<Match> mal2 = ctrl.selectedTournoi().getMatchs();
                    ArrayList<Match> mas2 = new ArrayList<>();
                    mal2.forEach((match) -> {
                        mas2.add(match);
                    });

                    tvMatch.getItems().clear();
                    tvMatch.getItems().addAll(mas2);

                    joueur1.valueProperty().set(null);
                    joueur2.valueProperty().set(null);
                    resultat.getSelectionModel().clearSelection();

                    btnAddMatch.setDisable(false);
                    btnEditMatch.setDisable(true);
                    btnDelMatch.setDisable(true);

                    nok.setText(null);
                    ok.setText(null);

                    break;
            }

        }
    }

    private void setbutton() {
        btnAddMatch.setPadding(new Insets(5));
        btnDelMatch.setPadding(new Insets(5));
        btnEditMatch.setPadding(new Insets(5));
    }

}
