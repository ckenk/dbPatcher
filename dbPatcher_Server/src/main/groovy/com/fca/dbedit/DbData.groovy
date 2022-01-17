package com.fca.dbedit

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * Created by kkulathilake on 5/12/2016.
 */
@Component
@Scope("session")
class DbData {
    public static final String GLOBAL_DB_DIR = DbEditApplication.ROOT + "/vehicles/"
    public static final String ECUS_DB_DIR = DbEditApplication.ROOT + "/ecus/"

    public static String GLOBAL_DB = GLOBAL_DB_DIR + "/global.db"
    public static def ECU_DBS = [:]

    public static void clean() {
        GLOBAL_DB = GLOBAL_DB_DIR + "/global.db"
        ECU_DBS = [:]
    }
}
