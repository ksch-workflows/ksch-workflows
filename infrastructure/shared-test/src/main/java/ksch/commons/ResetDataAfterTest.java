package ksch.commons;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.rules.ExternalResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Removes all data in all tables after the execution of a test case.
 *
 * @see <a href="https://www.javarticles.com/2016/02/junit-externalresource-rule-example.html">
 *     JUnit ExternalResource Rule Example (javarticles.com)</a>
 * @see <a href="http://www.greggbolinger.com/truncate-all-tables-in-spring-boot-jpa-app/">
 *     Truncate All Tables in Spring Boot JPA App (greggbolinger.com)</a>
 * @see <a href="https://stackoverflow.com/a/53593249/2339010">List all DB tables - JPA (stackoverflow.com)</a>
 */
@Component
@RequiredArgsConstructor
public class ResetDataAfterTest extends ExternalResource {

    private final EntityManager entityManager;

    private final DataSource dataSource;

    private final TransactionTemplate transactionTemplate;

    @Override
    public void after() {
        transactionTemplate.execute(action -> {
            resetAllTables();
            return null;
        });
    }

    @SneakyThrows
    public void resetAllTables() {
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        getAllTableNames().forEach(this::truncateData);
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

    @SneakyThrows
    private List<String> getAllTableNames() {
        List<String> tableNames = new ArrayList<>();

        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
        while (tables.next()) {
            String tableName=tables.getString("TABLE_NAME");
            tableNames.add(tableName);
        }
        return tableNames;
    }

    private void truncateData(String tableName) {
        entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
    }
}
