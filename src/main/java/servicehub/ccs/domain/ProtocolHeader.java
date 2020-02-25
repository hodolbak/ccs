package servicehub.ccs.domain;

import lombok.Getter;
import lombok.Setter;
import servicehub.ccs.domain.enums.RequestResponseType;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ProtocolHeader {

    @Id
    @GeneratedValue
    private Long id;

    private String key;
    private String value;

    private String format;
    private String description;

    @Enumerated(EnumType.STRING)
    private RequestResponseType requestResponseType;

    // 해당 프로토콜
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "protocol_id")
    private Protocol protocol;

}