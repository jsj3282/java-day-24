package ex03;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ex03.ResultService.ResultService;
import ex03.ResultService.ResultServiceImpl;
import ex03.chart.ChartService;
import ex03.chart.ChartServiceImpl;
import ex03.databaseService.DatabaseService;
import ex03.databaseService.DatabaseServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class Controller implements Initializable{
	private Parent root;
	private ResultService rs;
	private Inquiry into;
	private DatabaseService db;
	private ChartService cs;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void resultBtn() {
		System.out.println("결과 보기");
		ArrayList<Inquiry> lists = db.selectSQL();
		cs = new ChartServiceImpl();
		cs.viewChart(lists);
		for(Inquiry i : lists) {
			System.out.println(i.getAge() + " : " + i.getGender() + " : " + i.getTravel());
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rs = new ResultServiceImpl();
		db = new DatabaseServiceImpl();
	}
	public void savaBtn() {
		System.out.println("저장");
		into = rs.Result(root);
		db.insertSQL(into);
	}
	public void cancleBtn() {
		System.out.println("취소");
	}

}
