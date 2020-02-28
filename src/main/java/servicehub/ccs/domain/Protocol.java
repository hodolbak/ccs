package servicehub.ccs.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Protocol {
    @Id
    @GeneratedValue
    private Long id;

    private String name;                    // 이름
    private String desccription;            // 설명
    private String schema;                  // schema : HTTP, MQTT, VPUSH
    private String vpushType;               // vpush 통신 방식 : noti, rcp, broadcast
    private String source;                  // mqtt/vpush 통신시 source : Service, Vehicle, Device
    private String destincation;            // mqtt/vpush 통신시 target : Service, Vehicle, Device
    private String host;                    // host 명
    private String methodType;              // method type : POST, PUT
    private String uri;                     // uri
    private String protocol_id;             // prototol id
    private String topicPrefix;             // tocpic name prefix
    private String topicName;               // tocpic full name
    private String qos;                     // qos
    private int ttl;                        // ttl
    private int responseTimeout;            // response time out
    private String collbackHttpMethodType;  //
    private String callbackHttpPath;        //
    private String sinkType;                //
    private String sinkTypeMethodType;      //
    private String sinkTypePath;            //
    private String kafkaTopic;              //
    private String requestEntityId;         //
    private String responseEntityId;        //


    // 프로토콜별 헤더
    @OneToMany(mappedBy = "protocol", cascade = CascadeType.ALL)
    private List<ProtocolHeader> protocolHeaders = new ArrayList<>();

    public void addProtocolHeader(ProtocolHeader protocolHeader) {
        protocolHeaders.add(protocolHeader);
        protocolHeader.setProtocol(this);
    }
}
