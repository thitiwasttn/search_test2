package com.thitiwas.drug.drugdataset.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t1_drug")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class T1Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_ndc")
    private String productNdc;

    @Column(name = "generic_name")
    private String genericName;

    @Column(name = "labeler_name")
    private String labelerName;

    @Column(name = "product_id")
    private String productId;

    @OneToMany(mappedBy = "drug", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<T2packaging> packagings;

    /*@Override
    public String toString() {
        return "T1Drug{" +
                "id=" + id +
                ", productNdc='" + productNdc + '\'' +
                ", genericName='" + genericName + '\'' +
                ", labelerName='" + labelerName + '\'' +
                ", productId='" + productId + '\'' +
                ", packagings=" + packagings +
                '}';
    }*/
}
