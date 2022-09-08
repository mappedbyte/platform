package com.francis.platform.entity.vo;

import com.francis.platform.entity.SiteArea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Francis
 * @Date 2022-09-02 13:35
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteAreaVo implements Serializable {

    private String id;
    private String areaName;
    private int areaType;
    private List<LocationPoint> points;

    public SiteArea toSiteArea() {
        return SiteArea.builder()
                .geoJsonPolygon(new GeoJsonPolygon(points.stream().
                        map(LocationPoint::toPoint)
                        .collect(Collectors.toList())))
                .areaType(areaType)
                .areaName(areaName)
                .build();
    }

}
