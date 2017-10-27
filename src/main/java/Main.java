import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DigitalSignalProcessingConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final String RELATIVE_CONTROLLER_PATH = "view/mainController.fxml";

	private static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DigitalSignalProcessingConfiguration.class);

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		fxmlLoader.setLocation(getClass().getResource(RELATIVE_CONTROLLER_PATH));
		Parent root = fxmlLoader.load();
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 1024, 768));
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		new AnnotationConfigApplicationContext(DigitalSignalProcessingConfiguration.class);
		launch(args);
	}
}
