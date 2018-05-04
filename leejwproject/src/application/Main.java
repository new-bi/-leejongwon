package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/View/main.fxml"));
			Scene loginScene = new Scene(login);
			primaryStage.setTitle("삼겹살만 주문계산 프로그렘");
			primaryStage.setScene(loginScene);
			//크기조절 불가능하게만들기
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


/*create table manulist(
name varchar2(30) not null,
price number(20) not null,
list varchar2(16) not null,
count number(10),
primary key(list)
);
*/