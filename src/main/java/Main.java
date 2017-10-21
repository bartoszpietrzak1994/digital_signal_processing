import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@ComponentScan({"model", "service"})
public class Main extends Application
{
	public static final String RELATIVE_CONTROLLER_PATH = "view/mainController.fxml";

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource(RELATIVE_CONTROLLER_PATH));
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 1024, 768));
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		launch(args);
	}
}
