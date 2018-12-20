// autor : Norman & César 2018-2019// autor : Norman & César 2018-2019
package main;

import ctrl.Ctrl;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import model.TournoiList;
import view.View;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception { 
        TournoiList tournoiList = new TournoiList();
        Ctrl ctrl = new Ctrl(tournoiList);
        View view = new View(primaryStage, ctrl);
        tournoiList.addObserver(view);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

