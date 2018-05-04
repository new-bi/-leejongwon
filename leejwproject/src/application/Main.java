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
			primaryStage.setTitle("���츸 �ֹ���� ���α׷�");
			primaryStage.setScene(loginScene);
			//ũ������ �Ұ����ϰԸ����
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