package com.kaishengit.mapper;

import com.kaishengit.pojo.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//@CacheNamespace
public interface AccountMapper {

    @Insert("insert into t_account(name,address) values(#{name},#{address})")
    @Options(useGeneratedKeys = true,keyProperty = "id",
            flushCache = Options.FlushCachePolicy.FALSE)
    void save(Account account);

    @Select("select * from t_account where id = #{id}")
    @Options(useCache = false)
    Account findById(Integer id);

    @Update("update t_account set name = #{name},address=#{address} where id = #{id}")
    void update(Account account);
    @Delete("delete from t_account where id = #{id}")
    void del(Integer id);
    @Select("select * from t_account")
    List<Account> findAll();

    List<Account> findByParam(Map<String,Object> param);

    @Select("select * from t_account limit ${start},${size}")
    List<Account> findByPage(@Param("start") int start,
                             @Param("size") int size);



}
