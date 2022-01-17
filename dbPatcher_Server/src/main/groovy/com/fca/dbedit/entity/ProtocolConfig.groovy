package com.fca.dbedit.entity

import javax.persistence.Column

/**
 * Created by kkulathilake on 5/12/2016.
 */
class ProtocolConfig {

    @Column(name="id")
    int id

    @Column(name="protocol_id")
    int protocol_id

    @Column(name="protocol_sdd_id")
    int protocol_sdd_id

    @Column(name="sdd_version")
    String sdd_version

    @Column(name="transport")
    String transport

    @Column(name="use_29_bit_can_id")
    int use_29_bit_can_id

    @Column(name="use_frame_padding")
    int use_frame_padding

    @Column(name="use_extended_address")
    int use_extended_address
}
