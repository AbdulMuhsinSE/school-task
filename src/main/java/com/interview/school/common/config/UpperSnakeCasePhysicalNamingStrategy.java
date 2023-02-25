package com.interview.school.common.config;

/**
 * UpperSnakeCasePhysicalNamingStrategy.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Custom naming strategy for Javax column and table annotations.
 *
 * @author AbdulMuhsin J. Al-Kandari
 */
public class UpperSnakeCasePhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    @Override
    public Identifier toPhysicalCatalogName(final Identifier name, final JdbcEnvironment context) {
        return super.toPhysicalCatalogName(toSnakeCase(name), context);
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier name, final JdbcEnvironment context) {
        return super.toPhysicalColumnName(toSnakeCase(name), context);
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier name, final JdbcEnvironment context) {
        return super.toPhysicalSchemaName(toSnakeCase(name), context);
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier name, final JdbcEnvironment context) {
        return super.toPhysicalSequenceName(toSnakeCase(name), context);
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier name, final JdbcEnvironment context) {
        return super.toPhysicalTableName(toSnakeCase(name), context);
    }

    /**
     * Takes the java identifier and generates an UPPER_SNAKE_CASE identifier for SQL Server.
     * @param id Java Identifier.
     * @return {@link Identifier}
     */
    private Identifier toSnakeCase(final Identifier id) {
        if (id == null) {
            return id;
        }

        String name = id.getText();
        String upperSnakeCase = name.replaceAll("([a-z]+)([A-Z]+)", "$1\\_$2").toUpperCase();
        if (!upperSnakeCase.equals(name)) {
            return new Identifier(upperSnakeCase, id.isQuoted());
        } else {
            return id;
        }
    }
}
