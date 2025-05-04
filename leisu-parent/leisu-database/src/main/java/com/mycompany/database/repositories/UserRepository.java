package com.mycompany.database.repositories;

import com.mycompany.database.models.LeisuUser;
import com.mycompany.database.models.Movie;
import com.mycompany.database.models.criteria.MovieCriteria;
import com.mycompany.database.models.criteria.UserCriteria;

public interface UserRepository extends AbstractRepository<LeisuUser, UserCriteria> {

    LeisuUser getByUsername(String username);
    Boolean existsUsername(String username);

}
