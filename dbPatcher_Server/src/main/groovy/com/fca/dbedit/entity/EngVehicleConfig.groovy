package com.fca.dbedit.entity

import javax.persistence.Column

class EngVehicleConfig {

    @Column(name="id")
    Integer id

    @Column(name="year")
    Integer year

    @Column(name="body")
    String body

    @Column(name="var_ver_id")
    Integer var_ver_id

    @Column(name="ecu_id")
    Integer ecu_id

    String ecu_acronym

    @Column(name="is_gateway")
    Integer is_gateway

    @Column(name="xmit_str")
    Integer xmit_str

    @Column(name="disable_gateway_command")
    String disable_gateway_command

    @Column(name="gateway_bit_position")
    Integer gateway_bit_position

    @Column(name="bus_id")
    Integer bus_id

    @Column(name="diag_bus_id")
    Integer diag_bus_id

    @Column(name="request_id")
    Integer request_id

    @Column(name="response_id")
    Integer response_id

    @Column(name="broadcast_id")
    Integer broadcast_id

    @Column(name="protocol_config_id")
    Integer protocol_config_id

    @Column(name="kline_protocol_config_id")
    Integer kline_protocol_config_id

    @Column(name="ecu_address")
    Integer ecu_address

    @Column(name="ident_type")
    String ident_type

    @Column(name="is_engine")
    Integer is_engine

    @Column(name="is_vin_master")
    Integer is_vin_master

    @Column(name="architecture_id")
    Integer architecture_id

    @Column(name="yb_version")
    String yb_version

    @Column(name="ecu_config")
    String ecu_config

    @Column(name="name_string_id")
    Integer name_string_id

    @Column(name="db_name")
    String db_name

    @Column(name="built_state")
    Integer built_state

    @Column(name="odometer_master_data")
    String odometer_master_data

    @Column(name="proxi_type")
    Integer proxi_type

    @Column(name="proxi_config_set_id")
    Integer proxi_config_set_id

    @Column(name="is_sgw")
    Integer is_sgw

    @Override
    public String toString() {
        return "${id},${year},${body},${var_ver_id},${ecu_id},${ecu_acronym},${is_gateway},${xmit_str},${disable_gateway_command},${gateway_bit_position},${bus_id},${diag_bus_id},${request_id},${response_id},${broadcast_id},${protocol_config_id},${kline_protocol_config_id},${ecu_address},${ident_type},${is_engine},${is_vin_master},${architecture_id},${yb_version},${ecu_config},${name_string_id},${db_name},${built_state},${odometer_master_data},${proxi_type},${proxi_config_set_id},${is_sgw}"
    }
}
