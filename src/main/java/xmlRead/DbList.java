package xmlRead;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.util.Dictionary;

import java.util.ArrayList;
import java.util.List;

@Root(name = "dbList")
public class DbList {
    @ElementList(entry = "dbName", inline = true)
    private Dictionary<DbName> dbNames = new Dictionary<DbName>();

    public DbList(@ElementList(entry = "dbName", inline = true) Dictionary<DbName> dbName) {
        this.dbNames = dbName;
    }

    public DbName getResourceByName(String name) {
        return dbNames.get(name);

    }

    public List<String> getAllRes() {
        List<String> list = new ArrayList<>();
        for (DbName dbName : dbNames) {
            list.add(dbName.getName());
        }
        return list;
    }
}