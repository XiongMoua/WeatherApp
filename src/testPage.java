import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class testPage extends Application{

    @Override
    public void start(Stage primaryStge) throws Exception {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primary.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Scene scene = new Scene(root);
            primaryStge.setTitle("Weather App");
            primaryStge.getIcons().add(new Image("icons/umbrella_80px.png"));
            primaryStge.setScene(scene);
            primaryStge.setResizable(false);
            primaryStge.show();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args){
        launch(args);
    }
    
}
