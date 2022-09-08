package com.francis.platform.controller;

import com.francis.platform.entity.SiteArea;
import com.francis.platform.entity.vo.LocationPoint;
import com.francis.platform.entity.vo.SiteAreaVo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Francis
 * @Date 2022-09-02 11:19
 **/
@RestController
@RequestMapping("/v1/siteArea")
public class SiteAreaController {

    private final MongoTemplate mongoTemplate;

    public SiteAreaController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @PostMapping("/save")

    public void save(@RequestBody SiteAreaVo siteArea) {
        mongoTemplate.save(siteArea.toSiteArea());
    }


    @GetMapping("/queryList")
    public List<SiteAreaVo> queryList() {
        List<SiteArea> siteAreas = mongoTemplate.findAll(SiteArea.class);
        return siteAreas.stream().map(SiteArea::toSiteAreaVo)
                .collect(Collectors.toList());
    }


    @PostMapping("/queryLocation")
    public List<SiteAreaVo> queryLocation(@RequestBody LocationPoint point) {
        Query query = new Query(Criteria.where("geoJsonPolygon").intersects(new GeoJsonPoint(point.toPoint())));
        return mongoTemplate.<SiteArea>find(query, SiteArea.class)
                .stream().map(SiteArea::toSiteAreaVo)
                .collect(Collectors.toList());
    }


}



