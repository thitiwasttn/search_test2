package com.thitiwas.drug.drugdataset.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t1_thai_sum_v2")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class T1ThaiSum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body_")
    private String body;

    @Column(name = "summary")
    private String summary;

    @Column(name = "type")
    private String type;

    @Column(name = "tag")
    private String tag;

    @Column(name = "url")
    private String url;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "t2_thaisum_tag",
            joinColumns = @JoinColumn(name = "thaisum_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("news")
    private List<T1Tags> tags;
}
