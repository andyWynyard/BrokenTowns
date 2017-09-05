package data;

import java.util.Set;

import entities.Municipality;

public interface MunicipalityDAO {
	
	Set<Municipality> index();
	Municipality show(int id);
	Municipality create(String municipalityJson);
	Municipality update(int id, String municipalityJson);
	Boolean destroy(int id);

}
