package com.mycompany.database.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTokenSession is a Querydsl query type for TokenSession
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTokenSession extends EntityPathBase<TokenSession> {

    private static final long serialVersionUID = -1064716520L;

    public static final QTokenSession tokenSession = new QTokenSession("tokenSession");

    public final NumberPath<Long> expirationTime = createNumber("expirationTime", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> issuedTime = createNumber("issuedTime", Long.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final BooleanPath revoked = createBoolean("revoked");

    public final StringPath sessionId = createString("sessionId");

    public final StringPath userAgent = createString("userAgent");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QTokenSession(String variable) {
        super(TokenSession.class, forVariable(variable));
    }

    public QTokenSession(Path<? extends TokenSession> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTokenSession(PathMetadata metadata) {
        super(TokenSession.class, metadata);
    }

}

