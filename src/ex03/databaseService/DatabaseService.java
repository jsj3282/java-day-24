package ex03.databaseService;

import java.util.ArrayList;

import ex03.Inquiry;

public interface DatabaseService {
	public void insertSQL(Inquiry into);
	public ArrayList<Inquiry> selectSQL();
}
