package com.ctream.scholar.crm;

import com.ctream.scholar.crm.api.CrmApi;
import com.ctream.scholar.crm.api.model.AppointmentRequest;
import com.ctream.scholar.crm.api.model.LeadRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrmRestController implements CrmApi {

    @Override
    public ResponseEntity<Void> createAppointment(AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createLead(LeadRequest leadRequest) {
        return ResponseEntity.ok().build();
    }
}
