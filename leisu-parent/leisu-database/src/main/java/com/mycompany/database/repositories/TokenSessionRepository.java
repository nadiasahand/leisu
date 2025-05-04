package com.mycompany.database.repositories;

import com.mycompany.database.models.TokenSession;
import com.mycompany.database.models.criteria.TokenSessionCriteria;

public interface TokenSessionRepository extends AbstractRepository<TokenSession, TokenSessionCriteria> {
TokenSession getBySessionId(String sessionId);
TokenSession getByToken(String token);
}
