package ds.project.UTIL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseMananger {

    private DatabaseMananger() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void executeSqlScript(String filePath) throws SQLException, IOException {
        Path path = Paths.get(filePath);
        String fullScript = new String(Files.readAllBytes(path));
        if (fullScript.trim().startsWith("CREATE OR REPLACE PROCEDURE")) {
            executeSingleStatement(fullScript);
        } else {
            String[] sqlStatements = fullScript.split(";\\s*\\n");
            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    executeSingleStatement(sql);
                }
            }
        }
    }

    private static void executeSingleStatement(String sql) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

}
