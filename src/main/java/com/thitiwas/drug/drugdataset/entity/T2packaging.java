package com.thitiwas.drug.drugdataset.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t2_packaging")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class T2packaging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_ndc")
    private String packageNdc;

    @Column(name = "description")
    private String description;

    @Column(name = "marketing_start_date")
    private Date marketingStartDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t1_drug_id" , insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private T1Drug drug;

    /*@Override
    public String toString() {
        return "T2packaging{" +
                "id=" + id +
                ", packageNdc='" + packageNdc + '\'' +
                ", description='" + description + '\'' +
                ", marketingStartDate=" + marketingStartDate +
                //", drug=" + drug +
                '}';
    }*/
}
