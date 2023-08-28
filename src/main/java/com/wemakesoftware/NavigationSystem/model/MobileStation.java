package com.wemakesoftware.NavigationSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class MobileStation {

    @Id
    @Column
    String id;

    @Column
    Float lastKnownX;

    @Column
    Float lastKnownY;


}
