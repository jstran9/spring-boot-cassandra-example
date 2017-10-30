package guru.springframework.config;

import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jt on 10/6/17.
 */
@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    public static final String KEYSPACE = "guru_keyspace";

    @Override
    public SchemaAction getSchemaAction() {
        // if product table doesn't then Spring Data will come in and create it.
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);

        return Arrays.asList(specification);
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        // method is here because keyspace is dropped when Spring stops.
        // if Cassandra installation is permanent you do not need to implement this.
        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"guru.springframework.domain"};
    }
}
