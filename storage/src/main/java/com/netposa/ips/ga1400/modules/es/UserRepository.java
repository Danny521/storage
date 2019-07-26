//package com.netposa.dgep.modules.es;
//
//import com.netposa.dgep.domain.es.User;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * Author: zhangtao@netposa.com on 2019/3/26
// */
//public interface UserRepository extends ElasticsearchRepository<User,String> {
//    //按userName like查询
//    List<User> findByUserNameLike(String userName);
//
//    //按role的name属性查询
//    List<User> findByRolesName(String name);
//
//    //按role的name属性查询 两种方式都可以
//    List<User> findByRoles_Name(String name);
//}
