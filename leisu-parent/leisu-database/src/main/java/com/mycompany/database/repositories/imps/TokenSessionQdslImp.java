package com.mycompany.database.repositories.imps;

import com.mycompany.database.models.QTokenSession;
import com.mycompany.database.models.TokenSession;
import com.mycompany.database.models.criteria.TokenSessionCriteria;
import com.mycompany.database.repositories.TokenSessionRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TokenSessionQdslImp extends AbstractQdslImpl<TokenSession, TokenSessionCriteria> implements TokenSessionRepository {

    protected TokenSessionQdslImp(EntityManager em) {
        super(em, TokenSession.class);
    }

    @Override
    protected BooleanBuilder getPredicate(TokenSessionCriteria criteria) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(this.getPredicate(criteria.getIdFilter(), QTokenSession.tokenSession.id));
        builder.and(this.getPredicate(criteria.getExpirationTimeFilter(), QTokenSession.tokenSession.expirationTime));
        builder.and(this.getPredicate(criteria.getRevokedFilter(), QTokenSession.tokenSession.revoked));
        return builder;
    }

    @Override
    public List<TokenSession> getByCriteria(TokenSessionCriteria criteria) {
        return this.queryFactory.selectFrom(QTokenSession.tokenSession).where(this.getPredicate(criteria)).fetch();
    }

    @Override
    public TokenSession getBySessionId(String sessionId) {
        return this.queryFactory
                .selectFrom(QTokenSession.tokenSession)
                .where(QTokenSession.tokenSession.sessionId.eq(sessionId))
                .fetchOne();
    }

    @Override
    public TokenSession getByToken(String token) {
        return this.queryFactory
                .selectFrom(QTokenSession.tokenSession)
                .where(QTokenSession.tokenSession.refreshToken.eq(token))
                .fetchOne();
    }
}
