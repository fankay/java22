package com.kaishengit.mapper;

import com.kaishengit.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("select * from t_role\n" +
            "        left join t_user_role on t_role.id = t_user_role.role_id\n" +
            "        where t_user_role.user_id = #{id}")
    List<Role> findByUserId(Integer id);
}
