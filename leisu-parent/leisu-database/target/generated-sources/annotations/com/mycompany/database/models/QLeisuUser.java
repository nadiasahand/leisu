package com.mycompany.database.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLeisuUser is a Querydsl query type for LeisuUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeisuUser extends EntityPathBase<LeisuUser> {

    private static final long serialVersionUID = -323050366L;

    public static final QLeisuUser leisuUser = new QLeisuUser("leisuUser");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final EnumPath<com.mycompany.database.enums.UserRole> role = createEnum("role", com.mycompany.database.enums.UserRole.class);

    public final StringPath userName = createString("userName");

    public QLeisuUser(String variable) {
        super(LeisuUser.class, forVariable(variable));
    }

    public QLeisuUser(Path<? extends LeisuUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLeisuUser(PathMetadata metadata) {
        super(LeisuUser.class, metadata);
    }

}

