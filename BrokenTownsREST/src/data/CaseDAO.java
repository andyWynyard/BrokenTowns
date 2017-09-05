package data;

import java.util.Set;

import entities.CaseItem;

public interface CaseDAO {
	
	public Set<CaseItem> index();
	public CaseItem show(int id);
	public CaseItem create(String jsonCaseItem);
	public CaseItem update(int id, String jsonCaseItem);
	public boolean destroy(int id);

}
