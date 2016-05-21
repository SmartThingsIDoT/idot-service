package com.integratingfactor.idot.service.api.ping;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.integratingfactor.idot.api.ping.model.HealthStatu;
import com.integratingfactor.idot.api.ping.resource.HealthCheckAPIResource;

@Component
@Path("ping")
public class HealthCheckService implements HealthCheckAPIResource {

    @Override
    public GetPingResponse getPing() throws Exception {
        HealthStatu status = new HealthStatu();
        status.setStatusName("service status");
        status.setStatusType("string");
        status.setStatusValue("all good");
        List<HealthStatu> statuses = new ArrayList<HealthStatu>();
        statuses.add(status);
        return GetPingResponse.withJsonOK(statuses);
    }

}
