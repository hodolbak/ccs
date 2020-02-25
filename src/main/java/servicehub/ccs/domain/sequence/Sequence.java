package servicehub.ccs.domain.sequence;

import lombok.Getter;
import lombok.Setter;
import servicehub.ccs.domain.Scenario;
import servicehub.ccs.domain.enums.RequestResponseType;

import javax.persistence.*;

/**
 * 시퀀스 정보
 * 시퀀스의 구성정보에 따라 각 구성 Class 에서 상송받아 처리 (ProtocolSequence, ScenarioSequence)
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "flowType")
@Getter
@Setter
public class Sequence {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestResponseType requestResponseType;

    private int orderNum;

    // 해당 시나리오
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;
}
