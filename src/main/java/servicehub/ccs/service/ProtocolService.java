package servicehub.ccs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import servicehub.ccs.domain.Protocol;
import servicehub.ccs.domain.ProtocolHeader;
import servicehub.ccs.repository.ProtocolRepository;

import java.util.List;

@Service
public class ProtocolService {

    @Autowired
    private ProtocolRepository protocolRepository;

    public Long insertProtocol(Protocol reqProtocol, List<ProtocolHeader> headers) {

        Protocol protocol = new Protocol();

        protocol.setName(reqProtocol.getName());
        protocol.setSchema(reqProtocol.getSchema());

        for(ProtocolHeader header : headers) {
            protocol.getProtocolHeaders().add(header);
        }

        protocolRepository.save(protocol);

        return protocol.getId();
    }
}
