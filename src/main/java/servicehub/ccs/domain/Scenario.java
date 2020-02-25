package servicehub.ccs.domain;

import lombok.Getter;
import lombok.Setter;
import servicehub.ccs.domain.sequence.Sequence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Scenario {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String imagePath;
    private String imageUrl;

    // 시나리오별 시퀀스 목록
    @OneToMany(mappedBy = "scenario")
    private List<Sequence> sequences = new ArrayList<>();

}

