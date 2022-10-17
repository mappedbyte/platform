package com.francis.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Author Francis
 * @Date 2022-09-23 16:45
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_map_information")
public class MapInformation {

    @TableId(value = " map_information_id", type = IdType.ASSIGN_ID)
            private Long mapInformationId;

            /**
             * 地图图片
            */
            @TableField(value = "map_picture")
            @NotBlank(message = "地图没有上传！")
            private String mapPicture;

            /**
             * 地图名称
            */
            @TableField(value = "map_name")
            private String mapName;

            /**
             * 楼层
            */
            @TableField(value = "`floor`")
            @NotBlank(message = "楼层不可以为空！")
            private String floor;

            @TableField(value = "map_width")
            @NotBlank(message = "地图宽度不可以为空！")
            private String mapWidth;

            @TableField(value = "map_height")
            @NotBlank(message = "地图高度不可以为空！")
            private String mapHeight;

            /**
             * 原点x坐标
            */
            @TableField(value = "origin_x")
            @NotBlank(message = "原点x坐标不可以为空！")
            private String originX;

            /**
             * 原点y坐标
            */
            @TableField(value = "origin_y")
            @NotBlank(message = "原点y坐标不可以为空！")
            private String originY;

            @TableField(value = "map_width_pixel")
            @NotBlank(message = "地图像素宽度不可以为空！")
            private String mapWidthPixel;

            @TableField(value = "map_height_pixel")
            @NotBlank(message = "地图像素高度不可以为空！")
            private String mapHeightPixel;

            /**
             * 1已经删除 0未删除
            */
            @TableField(value = "is_deleted")
            private Integer isDeleted;

            @TableField(value = "create_time")
            private Date createTime;

            @TableField(value = "update_time")
            private Date updateTime;

            public static final String COL_MAP_INFORMATION_ID= "map_information_id";

            public static final String COL_MAP_PICTURE="map_picture";

            public static final String COL_MAP_NAME="map_name";

            public static final String COL_FLOOR="floor";

            public static final String COL_MAP_WIDTH="map_width";

            public static final String COL_MAP_HEIGHT="map_height";

            public static final String COL_ORIGIN_X="origin_x";

            public static final String COL_ORIGIN_Y="origin_y";

            public static final String COL_MAP_WIDTH_PIXEL="map_width_pixel";

            public static final String COL_MAP_HEIGHT_PIXEL="map_height_pixel";

            public static final String COL_IS_DELETED="is_deleted";

            public static final String COL_CREATE_TIME="create_time";

            public static final String COL_UPDATE_TIME="update_time";
}