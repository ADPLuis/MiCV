package dad.javafx.micv.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MiCVApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		rootController root = new rootController();
		Scene scene = new Scene(root.getRootView(),620, 480);
		
		primaryStage.setTitle("MiCV");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
