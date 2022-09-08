package com.francis.platform.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

/**
 * @Author Francis
 * @Date 2022-09-02 13:46
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LocationPoint {

    private double x;
    private double y;


    public LocationPoint(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }


    public Point toPoint() {
        return new Point(x, y);
    }


}
