package com.bin.api.dao.repository.mapper;

import com.bin.webase.core.model.IdName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommonMapper {

    @Select("select id from ${table} order by id desc LIMIT 1")
    Long getMaxId(@Param("table") String table);


    @Select("<script>" +
            "select #{id} as id,#{name} as name from ${table} where id in " +
            "<foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<IdName<Long>> listName(@Param("id") String id, @Param("name") String name, @Param("table") String table, @Param("ids") List<Long> ids);

}
