package com.wemakesoftware.NavigationSystem.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class BaseStation {

    @Id
    @Column
    String id;

    @Column
    String name;

    @Column
    Float x;

    @Column
    Float y;

    @Column
    Float detectionRadiusInMeters;

}
