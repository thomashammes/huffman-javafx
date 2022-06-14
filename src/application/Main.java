package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entities.HuffmanTree;

public class Main extends Application {
	
	private static Scene mainScene;
	
	String testCase = "nos nao lutamos para morrer. nos viemos para cumprir a missao que incubimos para nossa vida, para proteger o amor e a justica na terra. mesmo que seja a morte ou qualquer outro fim que nos aguarda, nos continuaremos a lutar ate que as chamas de nossas vidas se apaguem.";
	
	String result;
	
	HuffmanTree tree = new HuffmanTree();

	@Override
	public void start(Stage primaryStage) {
		tree.doAll(testCase);
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
