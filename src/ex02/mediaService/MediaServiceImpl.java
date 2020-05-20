package ex02.mediaService;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaServiceImpl implements MediaService {
	private MediaPlayer mediaPlayer;
	private MediaView mediaView;
	private Button btnPlay;
	private Button btnPause;
	private Button btnStop;
	private boolean endOfMedia;
	private Label labelTime;
	private Slider sliderVolume;
	private ProgressBar progressBar;
	private ProgressIndicator progressIndicator;
	
	@Override
	public void volumnControll() {
		mediaPlayer.setVolume(sliderVolume.getValue() / 100.0);
	}
	
	private void setControll(Parent root) {
		mediaView = (MediaView)root.lookup("#fxMediaView");
		btnPlay = (Button)root.lookup("#btnPlay");
		btnPause = (Button)root.lookup("#btnPause");
		btnStop = (Button)root.lookup("#btnStop");
		labelTime = (Label)root.lookup("#labelTime");
		sliderVolume = (Slider)root.lookup("#sliderVolume");
		progressBar = (ProgressBar)root.lookup("#progressBar");
		progressIndicator = (ProgressIndicator)root.lookup("#progressIndicator");
	}
	@Override
	public void myStart() {
		if(endOfMedia) {
			mediaPlayer.stop();
			mediaPlayer.seek(mediaPlayer.getStartTime());
		}
		mediaPlayer.play();
		endOfMedia = false;
		
	}

	@Override
	public void myStop() {
		
		mediaPlayer.stop();
		
	}

	@Override
	public void myPause() {
		
		mediaPlayer.pause();
		
	}

	@Override
	public void setMedia(Parent root, String mediaName) {
		Media media = new Media(getClass().getResource(mediaName).toString());
		setControll(root);
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.setOnReady(new Runnable() {

			@Override
			public void run() {
				btnPlay.setDisable(false);
				btnPause.setDisable(true);
				btnStop.setDisable(true);
				mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue)->{
					double progress = mediaPlayer.getCurrentTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
					progressBar.setProgress(progress);
					progressIndicator.setProgress(progress);
					labelTime.setText((int)mediaPlayer.getCurrentTime().toSeconds()+"/"+
					(int)mediaPlayer.getTotalDuration().toSeconds()+" sec");
				});
			}
		});
		mediaPlayer.setOnPlaying(()->{
			
			sliderVolume.setValue(50.0);
			btnPlay.setDisable(true);
			btnPause.setDisable(false);
			btnStop.setDisable(false);
		});
		mediaPlayer.setOnPaused(()->{
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(false);
		});
		mediaPlayer.setOnEndOfMedia(()->{
			endOfMedia = true;
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
		});
		mediaPlayer.setOnStopped(()->{
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
		});
		
		
	}
	

}
