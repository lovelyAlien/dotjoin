package com.dangsan.dotjoin.infra.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Configuration
public class QueryDslConfig {
    @PersistenceContext
    private EntityManager entityManager;


    //빈으로 등록하여 프로젝트 전역에서 QueryDSL을 작성할 수 있도록 한다.
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
