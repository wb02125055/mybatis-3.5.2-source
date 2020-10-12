/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.wb.ibatis;

import com.wb.ibatis.domain.User;
import com.wb.ibatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author wangbin33
 * @date Created in 23:28 2019/10/4
 */
public class TestDemo1 {
  @Test
  public void test() throws Exception {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession session = sqlSessionFactory.openSession();
    try {
      UserMapper mapper = session.getMapper(UserMapper.class);
      System.out.println(mapper.selectById(1));
//      UserMapper mapper = session.getMapper(UserMapper.class);
      User user = new User();
      user.setId(12);
      user.setName("wb");
      user.setAge(23);
      int res = mapper.insertUser(user);
      session.commit();
      System.out.println(res);
    } finally {
      session.close();
    }
  }
}
