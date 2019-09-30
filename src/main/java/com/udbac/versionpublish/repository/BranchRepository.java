package com.udbac.versionpublish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.udbac.versionpublish.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, String>, QuerydslPredicateExecutor<Branch> {
    
    
    /**
     * 使用<code>spel</code>表达式以及原生查询方式进行查找,这里使用了对象传递参数,相比较多个参数更方便一点,
     * 但是这种方式难以维护,并且随版本升级可能会有变动, 不建议使用这种方式,这里只做参考.单表查询使用jpa,联表查询使用querydsl
     * 
     * @param branch
     * @param province
     * @return
     */
    @Query(value = "select b.* from branch b where name = ?#{[0].name} and dcsid = ?#{[0].dcsid} and province = ?#{[0].province}", nativeQuery = true)
    List<Branch> findByNameAndDcsidAndProvince(Branch branch);
}
