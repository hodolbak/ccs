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
import servicehub.ccs.domain.Scenario;
import servicehub.ccs.domain.enums.RequestResponseType;
import servicehub.ccs.domain.sequence.ProtocolSequence;
import servicehub.ccs.domain.sequence.ScenarioSequence;
import servicehub.ccs.domain.sequence.Sequence;
import servicehub.ccs.service.ProtocolService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestScenario {
    @Autowired
    ProtocolService protocolService;
    @PersistenceContext
    private EntityManager em;

    @Test
    @Rollback(false)
    public void createProtocol() throws Exception {

        Scenario scenario = new Scenario();
        scenario.setName("s1 name");
        scenario.setDescription("s1 desc");

        ProtocolSequence protocolSequence = new ProtocolSequence();
        protocolSequence.setOrderNum(1);
        protocolSequence.setRequestResponseType(RequestResponseType.REQUEST);
        Protocol protocol = em.find(Protocol.class, 1L);
        protocolSequence.setSequenceInfo(protocol);

        scenario.addSequences(protocolSequence);
        em.persist(scenario);
    }


    @Test
    @Rollback(false)
    public void createProtocol2() throws Exception {

        Scenario scenario = new Scenario();
        scenario.setName("s2222 name");
        scenario.setDescription("s2222 desc");

        ProtocolSequence sequence = new ProtocolSequence();
        sequence.setOrderNum(1);
        sequence.setRequestResponseType(RequestResponseType.REQUEST);
        sequence.setSequenceInfo(em.find(Protocol.class, 4L));

        ScenarioSequence sequence1 = new ScenarioSequence();
        sequence1.setOrderNum(2);
        sequence1.setRequestResponseType(RequestResponseType.RESPONSE);
        sequence1.setSequenceInfo(em.find(Scenario.class, 26L));

        scenario.addSequences(sequence);
        scenario.addSequences(sequence1);
        em.persist(scenario);
    }

    @Test
    @Rollback(false)
    public void selectProtocol() throws Exception {

        Scenario scenario = em.find(Scenario.class, 28L);
        scenario.getSequences().isEmpty();  // sequence 쿼리 실행.
        List<Sequence> sequences = scenario.getSequences();
        for(Sequence sequence : sequences) {
            System.out.println("######## -"+sequence.getFlowType());
            if(sequence.getFlowType().equals("PROTOCOL")) {
                ProtocolSequence protocolSequence = (ProtocolSequence) sequence;
                System.out.printf("######## Protocol ID : %s, NAME : %s, SCHEME : %s",
                        protocolSequence.getSequenceInfo().getId(), // protocol 조회쿼리 최초 실행
                        protocolSequence.getSequenceInfo().getName(),
                        protocolSequence.getSequenceInfo().getSchema());
            } else {
                ScenarioSequence scenarioSequence = (ScenarioSequence) sequence;
                System.out.printf("######## Scenario ID : %s, NAME : %s",
                        scenarioSequence.getSequenceInfo().getId(), // scenario 조회쿼리 최초 실행
                        scenarioSequence.getSequenceInfo().getName());
            }
        }
    }


}
