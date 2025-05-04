package com.mycompany.database.models.criteria;

import lombok.Getter;
import lombok.Setter;
import org.bardframework.form.model.filter.LongFilter;

@Setter
@Getter
public abstract class AbstractCriteria {
    private LongFilter idFilter;

}
