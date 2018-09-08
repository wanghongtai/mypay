package com.wht.pay.dao;

import com.wht.pay.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 对于简单语句来说，注解使代码显得更加简洁，然而 Java 注解对于稍微复杂的语句就会力不从心并且会显得更加混乱。
 * 因此，如果你需要做很复杂的事情，那么最好使用 XML 来映射语句。
 * 选择何种方式以及映射语句的定义的一致性对你来说有多重要这些完全取决于你和你的团队。
 * 换句话说，永远不要拘泥于一种方式，你可以很轻松的在基于注解和 XML 的语句映射方式间自由移植和切换。
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(int id);

    @Select("SELECT * FROM user limit #{start},#{pageSize}")
    List<User> getUserByPage(Map<String,Object> data);

}
