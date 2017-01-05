package com.kaishengit;

import com.kaishengit.mapper.AccountMapper;
import com.kaishengit.pojo.Account;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class AccountMapperTestCase {

    @Test
    public void save() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

        List<Account> accounts = accountMapper.findAll();

        for(Account account : accounts) {
            System.out.println(account.getName());
        }

        //accountMapper.del(1);

        /*Account account = accountMapper.findById(1);
        account.setAddress("上海");
        accountMapper.update(account);*/

       /* Account account = new Account();
        account.setName("赵思丽");
        account.setAddress("北京");

        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        accountMapper.save(account);
*/
        sqlSession.commit();
        sqlSession.close();

    }


    @Test
    public void findByPage() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

        List<Account> accounts = accountMapper.findByPage(0,1);
        for(Account account : accounts) {
            System.out.println(account.getName());
        }

        sqlSession.close();

    }
}
