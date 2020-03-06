package servicehub.ccs.domain.sequence;

import lombok.Getter;
import lombok.Setter;
import servicehub.ccs.domain.Scenario;
import servicehub.ccs.domain.enums.RequestResponseType;
import servicehub.ccs.domain.enums.SequenceFlowType;

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
public abstract class Sequence {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestResponseType requestResponseType;

    // DiscriminatorColumn 으로 지정된 flowType 을 get set 하기 위해 저장. jpa 저장시 기본 입력되므로 insert, update false 로 지정.
    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private SequenceFlowType flowType;

    private int orderNum;

    // 해당 시나리오
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;


}
