package sys.jdbctemplate;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sys.entity.User;

import java.util.List;

public class UserDao {

    private JdbcTemplate template = new JdbcTemplate(JdbcTemplateUtil.getDataSource());

    public static void main(String[] args) {
        new UserDao().listUser2("孙", 50).forEach(System.out::println);

        UserDao dao = new UserDao();
        User user = dao.selectById(11);
        System.out.println(user);
    }

    public User selectById(Integer id) {
        String sql = "select * from sys_user where id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id);
    }

    //add update delete DML update()
    public void deleteUserById(Integer id) {
        String sql = "delete from sys_user where id=?";
        template.update(sql,id);
    }

    public void addUser(User user) {
        String sql = "insert into sys_user(name,age,sex,create_time) values( ?,?,?,?)";
        template.update(sql, user.getName(), user.getAge(), user.getSex(), user.getCreateTime());
    }

    public List<User> listUser2(String name, Integer age) {
        String sql = "select * from sys_user where name like ? and age> ?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%", age);
    }

    public List<User> listUser() {
        String sql = "select * from sys_user";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

}
