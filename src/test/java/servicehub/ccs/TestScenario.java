package servicehub.ccs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import servicehub.ccs.domain.Protocol;
import servicehub.ccs.domain.Scenario;
import servicehub.ccs.domain.enums.RequestResponseType;
import servicehub.ccs.domain.enums.SequenceFlowType;
import servicehub.ccs.domain.sequence.ProtocolSequence;
import servicehub.ccs.domain.sequence.ScenarioSequence;
import servicehub.ccs.domain.sequence.Sequence;
import servicehub.ccs.service.ProtocolService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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
    public void createScenario1() throws Exception {

        Scenario scenario = new Scenario();
        scenario.setName("s333 name");
        scenario.setDescription("s3333 desc");

        System.out.println("SequenceFlowType.PROTOCOL = " + SequenceFlowType.PROTOCOL);

        if("PROTOCOL".equals(String.valueOf(SequenceFlowType.PROTOCOL))) {
            ProtocolSequence protocolSequence = new ProtocolSequence();
          //  protocolSequence.setFlowType(SequenceFlowType.PROTOCOL);
            protocolSequence.setOrderNum(1);
            protocolSequence.setRequestResponseType(RequestResponseType.REQUEST);
            Protocol protocol = em.find(Protocol.class, 1L);
            protocolSequence.setProtocolInfo(protocol);
            scenario.addSequences(protocolSequence);
        }

        if("PROTOCOL".equals(String.valueOf(SequenceFlowType.PROTOCOL))) {
            ProtocolSequence protocolSequence = new ProtocolSequence();
           // protocolSequence.setFlowType(SequenceFlowType.PROTOCOL);
            protocolSequence.setOrderNum(2);
            protocolSequence.setRequestResponseType(RequestResponseType.REQUEST);
            Protocol protocol = em.find(Protocol.class, 4L);
            protocolSequence.setProtocolInfo(protocol);
            scenario.addSequences(protocolSequence);
        }

        em.persist(scenario);
    }


    @Test
    @Rollback(false)
    public void createScenario2() throws Exception {

        Scenario scenario = new Scenario();
        scenario.setName("s2222 name");
        scenario.setDescription("s2222 desc");

        ProtocolSequence sequence = new ProtocolSequence();
        sequence.setOrderNum(1);
        sequence.setRequestResponseType(RequestResponseType.REQUEST);
        sequence.setProtocolInfo(em.find(Protocol.class, 1L));

        ScenarioSequence sequence1 = new ScenarioSequence();
        sequence1.setOrderNum(2);
        sequence1.setRequestResponseType(RequestResponseType.RESPONSE);
        sequence1.setScenarioInfo(em.find(Scenario.class, 7L));

        scenario.addSequences((Sequence)sequence);
        scenario.addSequences((Sequence)sequence1);
        em.persist(scenario);
    }

    @Test
    @Rollback(false)
    public void selectScenario() throws Exception {

        Scenario scenario = em.find(Scenario.class, 10L);
        scenario.getSequences().isEmpty();  // sequence 쿼리 실행.
        List<Sequence> sequences = scenario.getSequences();
        for(Sequence sequence : sequences) {
            System.out.println("######## -"+sequence.getFlowType());
            if(sequence.getFlowType().equals("PROTOCOL")) {
                ProtocolSequence protocolSequence = (ProtocolSequence) sequence;
                System.out.printf("######## Protocol ID : %s, NAME : %s, SCHEME : %s \r\n",
                        protocolSequence.getProtocolInfo().getId(), // protocol 조회쿼리 최초 실행
                        protocolSequence.getProtocolInfo().getName(),
                        protocolSequence.getProtocolInfo().getSchema());
            } else {
                ScenarioSequence scenarioSequence = (ScenarioSequence) sequence;
                System.out.printf("######## Scenario ID : %s, NAME : %s \r\n",
                        scenarioSequence.getScenarioInfo().getId(), // scenario 조회쿼리 최초 실행
                        scenarioSequence.getScenarioInfo().getName());
            }
        }
    }

    @Test
    @Rollback(false)
    public void selectSequence() throws Exception {

        Sequence sequence = em.find(Sequence.class, 11L);
        System.out.println("######## -"+ String.valueOf(sequence.getFlowType()));
        if("PROTOCOL".equals(String.valueOf(sequence.getFlowType()))) {
            ProtocolSequence protocolSequence = (ProtocolSequence) sequence;
            System.out.printf("######## Protocol ID : %s, NAME : %s, SCHEME : %s \r\n",
                    protocolSequence.getProtocolInfo().getId(), // protocol 조회쿼리 최초 실행
                    protocolSequence.getProtocolInfo().getName(),
                    protocolSequence.getProtocolInfo().getSchema());
        } else {
            ScenarioSequence scenarioSequence = (ScenarioSequence) sequence;
            System.out.printf("######## Scenario ID : %s, NAME : %s \r\n",
                    scenarioSequence.getScenarioInfo().getId(), // scenario 조회쿼리 최초 실행
                    scenarioSequence.getScenarioInfo().getName());
        }

    }

    @Test
    @Rollback(false)
    public void selectSequenceList() throws Exception {

        Scenario scenario = em.find(Scenario.class, 10L);
        scenario.getSequences().isEmpty();  // sequence 쿼리 실행.
        List<Sequence> sequences = scenario.getSequences();
        for(Sequence sequence : sequences) {
            System.out.println("######## -"+sequence.getFlowType());
            if(sequence.getFlowType().equals("PROTOCOL")) {
                ProtocolSequence protocolSequence = (ProtocolSequence) sequence;
                System.out.printf("######## Protocol ID : %s, NAME : %s, SCHEME : %s \r\n",
                        protocolSequence.getProtocolInfo().getId(), // protocol 조회쿼리 최초 실행
                        protocolSequence.getProtocolInfo().getName(),
                        protocolSequence.getProtocolInfo().getSchema());
            } else {
                ScenarioSequence scenarioSequence = (ScenarioSequence) sequence;
                System.out.printf("######## Scenario ID : %s, NAME : %s \r\n",
                        scenarioSequence.getScenarioInfo().getId(), // scenario 조회쿼리 최초 실행
                        scenarioSequence.getScenarioInfo().getName());
            }
        }
    }

}
