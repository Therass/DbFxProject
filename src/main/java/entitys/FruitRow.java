package entitys;

public class FruitRow {

    private int id;
    private String fruit;
    private String sort;
    private String amount;
    private String provider;

    public FruitRow(int id, String fruit, String sort, String amount, String provider) {
        this.id = id;
        this.fruit = fruit;
        this.sort = sort;
        this.amount = amount;
        this.provider = provider;
    }

    public FruitRow() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }
}