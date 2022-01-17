package com.fca.dbedit.data

import com.fca.dbedit.DbData
import com.fca.dbedit.entity.Bus
import com.fca.dbedit.entity.EcuConfigOverride
import com.fca.dbedit.entity.EcuToBus
import com.fca.dbedit.entity.EngVehicleConfig
import com.fca.dbedit.entity.ProtocolConfig
import com.fca.dbedit.entity.VarVer
import com.fca.dbedit.exception.EngVehicleConfigUpdateFailedException
import groovy.io.FileType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

import java.sql.ResultSet;
import java.sql.SQLException

import com.fca.dbedit.entity.YbInfo
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.RowMapper

import javax.sql.DataSource;



/**
 * The content of this file is scrubbed
 * This uses JPA and Spring DAO mechanisms to query the DBs
 */
@Component
public class JdbcRepository {

}
