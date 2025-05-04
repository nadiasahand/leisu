package com.mycompany.database.models.criteria;

import lombok.Getter;
import lombok.Setter;
import org.bardframework.form.model.filter.IntegerFilter;
import org.bardframework.form.model.filter.StringFilter;

@Setter
@Getter
public class UserCriteria extends AbstractCriteria {
private StringFilter userNameFilter;
}
