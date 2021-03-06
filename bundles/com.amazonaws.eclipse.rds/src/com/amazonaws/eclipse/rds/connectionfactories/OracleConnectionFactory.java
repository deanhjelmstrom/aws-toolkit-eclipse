/*
 * Copyright 2011-2014 Amazon Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://aws.amazon.com/apache2.0
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amazonaws.eclipse.rds.connectionfactories;

import java.util.Properties;

import com.amazonaws.eclipse.rds.ImportDBInstanceDataModel;

/**
 * Configuration details for Oracle connections.
 */
public class OracleConnectionFactory extends DatabaseConnectionFactory {

    private final ImportDBInstanceDataModel wizardDataModel;

    public OracleConnectionFactory(ImportDBInstanceDataModel wizardDataModel) {
        this.wizardDataModel = wizardDataModel;
    }


    @Override
    public String getDriverClass() {
        return "oracle.jdbc.OracleDriver";
    }

    @Override
    public String getDatabaseVendor() {
        return "Oracle";
    }

    @Override
    public String getDatabaseVersion() {
        return "11";
    }

    @Override
    public Properties getAdditionalDriverProperties() {
        Properties driverProperties = new Properties();
        driverProperties.setProperty("org.eclipse.datatools.enablement.oracle.catalogType", "USER");
        return driverProperties;
    }

    @Override
    public String createJdbcUrl() {
        // Example Oracle JDBC URL: jdbc:oracle:thin:@server:1521:db
        String host = wizardDataModel.getDbInstance().getEndpoint().getAddress();
        Integer port = wizardDataModel.getDbInstance().getEndpoint().getPort();
        String dbName = wizardDataModel.getDbInstance().getDBName();
        return "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
    }

    @Override
    public String createDriverName() {
        return "RDS Oracle Driver";
    }

    @Override
    public String getDriverTemplate() {
        return "org.eclipse.datatools.enablement.oracle.11.driverTemplate";
    }

    @Override
    public String getConnectionProfileProviderId() {
        return "org.eclipse.datatools.enablement.oracle.connectionProfile";
    }
}
