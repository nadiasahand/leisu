package com.mycompany.database.models.criteria;

import lombok.Getter;
import lombok.Setter;
import org.bardframework.form.model.filter.BooleanFilter;
import org.bardframework.form.model.filter.LongFilter;

@Setter
@Getter
public class TokenSessionCriteria extends AbstractCriteria {
    private BooleanFilter revokedFilter;
    private LongFilter issuedTimeFilter;
    private LongFilter expirationTimeFilter;
}
