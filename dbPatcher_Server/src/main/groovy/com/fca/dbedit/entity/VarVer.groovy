package com.fca.dbedit.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="var_ver")
class VarVer {

    @Id
    @Column(name="id")
    int id

    @Column(name="var")
    String var

    @Column(name="ident")
    String ident

    @Column(name="min_ver")
    int min_ver

    @Column(name="ser_min_ver")
    int ser_min_ver

    @Column(name="ecu_id")
    int ecu_id

    @Column(name="protocol_config_id")
    int protocol_config_id

    @Column(name="flash_type_id")
    int flash_type_id

    @Column(name="ident_type")
    String ident_type

    boolean has_11_bit_ids = false

    boolean has_29_bit_ids = false
}
