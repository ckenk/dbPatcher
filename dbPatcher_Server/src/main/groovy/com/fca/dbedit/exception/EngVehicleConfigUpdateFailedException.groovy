package com.fca.dbedit.exception

/**
 * Created by kkulathilake on 5/20/2016.
 */
@SuppressWarnings("serial")
class EngVehicleConfigUpdateFailedException extends RuntimeException {

    def status = null
    public EngVehicleConfigUpdateFailedException() {}

    public EngVehicleConfigUpdateFailedException(String msg) {
        super(msg)
    }

    public EngVehicleConfigUpdateFailedException(String msg, String status) {
        super(msg)
        this.status=status
    }

}
