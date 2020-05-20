package ex02;

import java.net.URL;
import java.util.ResourceBundle;
import ex02.mediaService.MediaService;
import ex02.mediaService.MediaServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class Controller2 implements Initializable {
	private Parent root;
	MediaService mediaServ;
	
	public void volumeControll() {
		mediaServ.volumnControll();
	}
	public void setRoot(Parent root) {
		this.root = root;
		mediaServ.setMedia(root, "/media/video.m4v");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mediaServ = new MediaServiceImpl();	
	}
	public void myPlay() {
		mediaServ.myStart();
	}
	public void myPause() {
		mediaServ.myPause();
	}
	public void myStop() {
		mediaServ.myStop();
	}

}
