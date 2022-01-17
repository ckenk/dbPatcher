package com.fca.dbedit.entity

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person")
class YbInfo {

    @Id
    @Column(name="yb_code")
    String yb_code

    @Column(name="year")
    String year
    @Column(name="body")
    String body
    @Column(name="architecture_id")
    int architecture_id
    @Column(name="model")
    String model
    @Column(name="engine_sales_code_name")
    String engine_sales_code_name
    @Column(name="server")
    String server
    @Column(name="oem")
    String oem
    @Column(name="flash_broadcast_required")
    int flash_broadcast_required
    @Column(name="prototype")
    int prototype
    @Column(name="is_sgw")
    int is_sgw

    public YbInfo() {}

    public YbInfo(String year, String body) {
        this.year=year
        this.body=body
    }
}
