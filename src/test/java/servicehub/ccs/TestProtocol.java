package servicehub.ccs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import servicehub.ccs.domain.Protocol;
import servicehub.ccs.domain.ProtocolHeader;
import servicehub.ccs.domain.enums.RequestResponseType;
import servicehub.ccs.service.ProtocolService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestProtocol {
    @Autowired
    ProtocolService protocolService;
    @PersistenceContext
    private EntityManager em;

    @Test
    @Rollback(false)
    public void createProtocol() throws Exception {
        Protocol protocol = new Protocol();
        protocol.setName("protocolTest");
        protocol.setSchema("HTTP");
        em.persist(protocol);

        ProtocolHeader header1 = new ProtocolHeader();
        header1.setKey("Header1");
        header1.setValue("Header1");
        header1.setRequestResponseType(RequestResponseType.REQUEST);
        header1.setProtocol(protocol);
        em.persist(header1);

        ProtocolHeader header2 = new ProtocolHeader();
        header2.setKey("Header2");
        header2.setValue("Header2");
        header2.setRequestResponseType(RequestResponseType.RESPONSE);
        header2.setProtocol(protocol);
        em.persist(header2);
//
//        List<ProtocolHeader> headers = new ArrayList<>();
//        headers.add(header1);
//        headers.add(header2);
//
//        protocolService.insertProtocol(protocol, headers);
    }

}
