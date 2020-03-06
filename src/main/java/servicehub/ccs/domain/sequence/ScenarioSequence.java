package servicehub.ccs.domain.sequence;


import lombok.Getter;
import lombok.Setter;
import servicehub.ccs.domain.Scenario;

import javax.persistence.*;

/**
 * 시퀀스정보가 시나리오인 경우
 */
@Entity
@DiscriminatorValue("SCENARIO")
@Getter
@Setter
public class ScenarioSequence extends Sequence{

    // 시퀀스 구성요소가 시나리오인 경우 해당 시나리오 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_scenario_id")
    private Scenario scenarioInfo;
}
