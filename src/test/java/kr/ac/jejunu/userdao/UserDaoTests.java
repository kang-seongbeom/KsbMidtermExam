package kr.ac.jejunu.userdao;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "hulk";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        userDao.insert(user);

        User insertedUser = userDao.get(user.getId());

        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

//    @Test
//    public void hallaTestGet() throws SQLException, ClassNotFoundException {
//        Integer id = 1;
//        String name = "hulk";
//        String password = "1234";
//
//        ConnectionMaker connectionMaker = new JejuConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//
//        User user = userDao.get(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//
//    @Test
//    public void hallaInsert() throws SQLException, ClassNotFoundException {
//        String name = "hulk";
//        String password = "1234";
//
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//
//        ConnectionMaker connectionMaker = new JejuConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//
//        userDao.insert(user);
//
//        User insertedUser = userDao.get(user.getId());
//
//        assertThat(insertedUser.getId(), is(user.getId()));
//        assertThat(insertedUser.getName(), is(user.getName()));
//        assertThat(insertedUser.getPassword(), is(user.getPassword()));
//    }
}
