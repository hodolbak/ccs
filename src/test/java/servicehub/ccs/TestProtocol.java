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
        protocol.setName("VPUSH Test");
        protocol.setSchema("VPUSH");
        em.persist(protocol);

        ProtocolHeader header1 = new ProtocolHeader();
        header1.setKey("Header5555555");
        header1.setValue("Header555555");
        header1.setRequestResponseType(RequestResponseType.REQUEST);
        header1.setProtocol(protocol);
        em.persist(header1);

        ProtocolHeader header2 = new ProtocolHeader();
        header2.setKey("Header666666");
        header2.setValue("Header666666");
        header2.setRequestResponseType(RequestResponseType.RESPONSE);
        header2.setProtocol(protocol);
        em.persist(header2);

    }


    @Test
    @Rollback(false)
    public void createProtocol2() throws Exception {
        Protocol protocol = new Protocol();
        protocol.setName("protocolTest----22");
        protocol.setSchema("VPUSH");

        List<ProtocolHeader> protocolHeaders = new ArrayList<>();

        ProtocolHeader header1 = new ProtocolHeader();
        header1.setKey("Header555555");
        header1.setValue("Header55555");
        header1.setRequestResponseType(RequestResponseType.REQUEST);

        ProtocolHeader header2 = new ProtocolHeader();
        header2.setKey("Header6666");
        header2.setValue("Header66666");
        header2.setRequestResponseType(RequestResponseType.RESPONSE);

        protocolHeaders.add(header1);
        protocolHeaders.add(header2);

        protocolService.insertProtocol(protocol, protocolHeaders);
    }

}
