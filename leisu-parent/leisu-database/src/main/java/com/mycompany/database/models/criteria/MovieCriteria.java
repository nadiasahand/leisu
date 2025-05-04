package com.mycompany.database.models.criteria;

import lombok.Getter;
import lombok.Setter;
import org.bardframework.form.model.filter.IntegerFilter;

@Setter
@Getter
public class MovieCriteria extends AbstractCriteria {
    IntegerFilter publishYearFilter;
    IntegerFilter ratingFilter;
}
