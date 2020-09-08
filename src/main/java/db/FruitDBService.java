package db;

public interface FruitDBService {

	void insertIntoMain(String fruit, String sort, int amount,String provider);

	void updateMain(String fruit, String sort, int amount,String provider);

	void removeFromMain(String fruit, String sort, String provider);

}
