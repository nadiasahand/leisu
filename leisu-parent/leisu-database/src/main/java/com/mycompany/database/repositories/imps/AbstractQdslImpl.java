package com.mycompany.database.repositories.imps;

import com.mycompany.database.repositories.AbstractRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.bardframework.form.model.filter.BooleanFilter;
import org.bardframework.form.model.filter.RangeFilter;
import org.bardframework.form.model.filter.StringFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractQdslImpl<T, C> implements AbstractRepository<T, C> {
    protected final JPAQueryFactory queryFactory;
    @PersistenceContext
    private final EntityManager em;
    private final Class<T> entityClass;

//abstract EntityPathBase<T> getQClass();

    protected AbstractQdslImpl(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
        this.queryFactory = new JPAQueryFactory(em);
    }

    protected abstract BooleanBuilder getPredicate(C criteria);

    @Override
    public T getById(long id) {
        return em.find(entityClass, id);
    }

    @Transactional
    public T save(T t) {
        if (t == null) {
            return null;
        }
        em.persist(t);
        em.flush();
        return t;
    }

    @Override
    public T update(T t) {
        return em.merge(t);
    }

    @Override
    public T delete(int id) {
        return null;
    }

    protected <F extends RangeFilter> BooleanBuilder getPredicate(F filter, NumberPath path) {
        if (null == filter) {
            return null;
        }
        if (path == null) {
            return null;
        }
        BooleanBuilder predicate = new BooleanBuilder();
        if (null != filter.getSpecified()) {
            if (Boolean.FALSE == filter.getSpecified()) {
                predicate.and(path.isNull());
                return predicate;
            } else if (Boolean.TRUE == filter.getSpecified()) {
                predicate.and(path.isNotNull());
            }
        }
        if (null != filter.getEquals()) {
            predicate.and(path.eq(filter.getEquals()));
        }
        if (null != filter.getIn()) {
            predicate.and(path.in(filter.getIn()));
        }
        if (null != filter.getNotEquals()) {
            predicate.and(path.ne(filter.getNotEquals()));
        }
        if (null != filter.getNotIn()) {
            predicate.and(path.notIn(filter.getNotIn()));
        }
        return predicate;
    }

    protected BooleanBuilder getPredicate(StringFilter filter, StringPath path) {
        if (null == filter) {
            return null;
        }
        if (path == null) {
            return null;
        }
        BooleanBuilder predicate = new BooleanBuilder();
        if (null != filter.getSpecified()) {
            if (Boolean.FALSE == filter.getSpecified()) {
                predicate.and(path.isNull());
                return predicate;
            } else if (Boolean.TRUE == filter.getSpecified()) {
                predicate.and(path.isNotNull());
            }
        }
        if (null != filter.getEquals()) {
            predicate.and(path.eq(filter.getEquals()));
        }
        if (null != filter.getIn()) {
            predicate.and(path.in(filter.getIn()));
        }
        if (null != filter.getNotEquals()) {
            predicate.and(path.ne(filter.getNotEquals()));
        }
        if (null != filter.getNotIn()) {
            predicate.and(path.notIn(filter.getNotIn()));
        }
        return predicate;
    }

    protected BooleanBuilder getPredicate(BooleanFilter filter, BooleanPath path) {
        if (null == filter) {
            return null;
        }
        if (path == null) {
            return null;
        }
        BooleanBuilder predicate = new BooleanBuilder();
        if (null != filter.getSpecified()) {
            if (Boolean.FALSE == filter.getSpecified()) {
                predicate.and(path.isNull());
                return predicate;
            } else if (Boolean.TRUE == filter.getSpecified()) {
                predicate.and(path.isNotNull());
            }
        }
        if (null != filter.getEquals()) {
            predicate.and(path.eq(filter.getEquals()));
        }
        if (null != filter.getIn()) {
            predicate.and(path.in(filter.getIn()));
        }
        if (null != filter.getNotEquals()) {
            predicate.and(path.ne(filter.getNotEquals()));
        }
        if (null != filter.getNotIn()) {
            predicate.and(path.notIn(filter.getNotIn()));
        }
        return predicate;
    }
}
