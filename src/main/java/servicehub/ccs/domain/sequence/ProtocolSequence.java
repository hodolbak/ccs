package servicehub.ccs.domain.sequence;

import lombok.Getter;
import lombok.Setter;
import servicehub.ccs.domain.Protocol;

import javax.persistence.*;

/**
 * 시퀀스정보가 프로토콜인 경우
 */
@Entity
@DiscriminatorValue("PROTOCOL")
@Getter
@Setter
public class ProtocolSequence extends Sequence {

    // 시퀀스 구성요소가 프로토콜인 경우 해당 프로토콜 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_protocol_id")
    private Protocol sequenceInfo;
}
