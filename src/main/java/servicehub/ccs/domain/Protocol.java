package servicehub.ccs.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Protocol {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String protocolName;
    private String schema;

    // 프로토콜별 헤더
    @OneToMany(mappedBy = "protocol")
    private List<ProtocolHeader> protocolHeaders = new ArrayList<>();

}
