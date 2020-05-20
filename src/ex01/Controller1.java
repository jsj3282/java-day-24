package ex01;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controller1 implements Initializable{
	@FXML private ListView<String> fxListView;
	@FXML private ImageView fxImageView;
	ObservableList<String> phoneString;
	ObservableList<Phone> phoneURL;
	
	public void setListView() {
		phoneURL = FXCollections.observableArrayList();
		phoneString = FXCollections.observableArrayList();
		for(int i = 1; i < 8; i++) {
			phoneString.add("������S" + i);
			Phone p = new Phone("�ַ���S"+i,"phone0"+i+".png");
			phoneURL.add(p);
		}
		fxListView.setItems(phoneString);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setListView();
		fxListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)->
		{
		System.out.println("observable(����) : " + observable);
		System.out.println("oldValue(������) : " + oldValue);
		System.out.println("newValue(���ð�) : " + newValue);	
		//System.out.println("���� �� : " + phoneString.get((int)newValue));
		fxImageView.setImage(new Image(getClass().getResource("/img/phone/"+phoneURL.get((int)newValue).getImage()).toString()) );
		});
		
	}
	public void OkBtn() {
		String item = fxListView.getSelectionModel().getSelectedItem();
		System.out.println("ListView ����Ʈ�� : " + item);
		System.out.println("Ȯ�� ��ư Ŭ��");
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		String selectedFilePath = selectedFile.getPath();
		System.out.println(selectedFilePath);
		
	}
	public void CancelBtn() {
		System.out.println("��� ��ư Ŭ��");
	}

}
