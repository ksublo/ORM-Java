package ds.project.CREATING;

import ds.project.UTIL.DatabaseMananger;

import java.io.IOException;
import java.sql.SQLException;

public class Create {

    public static void createTables() {
        try {
            DatabaseMananger.executeSqlScript("src/main/resources/sql/create.sql");
            DatabaseMananger.executeSqlScript("src/main/resources/sql/create_procedure.sql");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void dropTables() {
        try {
            DatabaseMananger.executeSqlScript("src/main/resources/sql/drop.sql");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
