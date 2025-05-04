package com.mycompany.database.repositories.imps;

import com.mycompany.database.models.LeisuUser;
import com.mycompany.database.models.QLeisuUser;
import com.mycompany.database.models.criteria.UserCriteria;
import com.mycompany.database.repositories.UserRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import org.bardframework.form.model.filter.StringFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserQdslImp extends AbstractQdslImpl<LeisuUser, UserCriteria> implements UserRepository {

    protected UserQdslImp(EntityManager em) {
        super(em, LeisuUser.class);
    }

    @Override
    public List<LeisuUser> getByCriteria(UserCriteria criteria) {
        return this.queryFactory.selectFrom(QLeisuUser.leisuUser).where(this.getPredicate(criteria)).fetch();
    }

    @Override
    protected BooleanBuilder getPredicate(UserCriteria criteria) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(this.getPredicate(criteria.getIdFilter(), QLeisuUser.leisuUser.id));
        builder.and(this.getPredicate(criteria.getUserNameFilter(), QLeisuUser.leisuUser.userName));
        return builder;
    }

    @Override
    public LeisuUser getByUsername(String username) {
        return this.queryFactory.selectFrom(QLeisuUser.leisuUser)
                .where(QLeisuUser.leisuUser.userName.eq(username)).fetchOne();
    }

    @Override
    public Boolean existsUsername(String username) {
        return this.queryFactory.selectFrom(QLeisuUser.leisuUser)
                .where(QLeisuUser.leisuUser.userName.eq(username)).stream().findAny().isPresent();
    }
}
