package com.francis.platform.entity;

import com.francis.platform.entity.vo.LocationPoint;
import com.francis.platform.entity.vo.SiteAreaVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Francis
 * @Date 2022-09-02 11:15
 **/

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SiteArea implements Serializable {

    @Id
    private String id;
    private String areaName;
    private int areaType;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPolygon geoJsonPolygon;

    public SiteAreaVo toSiteAreaVo() {
        return SiteAreaVo.builder()
                .areaName(areaName)
                .areaType(areaType)
                .id(id)
                .points(geoJsonPolygon.getPoints().stream()
                  .map(LocationPoint::new).collect(Collectors.toList()))
                .build();
    }


}
