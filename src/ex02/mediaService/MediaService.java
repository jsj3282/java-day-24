package ex02.mediaService;

import javafx.scene.Parent;

public interface MediaService {
	public void myStart();
	public void myStop();
	public void myPause();
	public void setMedia(Parent form, String mediaName);
	public void volumnControll();
}
