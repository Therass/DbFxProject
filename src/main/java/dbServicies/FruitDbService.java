package dbServicies;

public interface FruitDbService {

	void selectFromMain();

	void insertIntoMain(String fruit, String sort, String amount, String provider);

	void updateMain(String fruit, String sort, String amount, String provider);

	void removeFromMain(String fruit, String sort, String provider);

	void selectAllFromMain();

}
