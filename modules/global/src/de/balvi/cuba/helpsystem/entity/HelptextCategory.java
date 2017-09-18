package de.balvi.cuba.helpsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "DBCHS_HELPTEXT_CATEGORY")
@Entity(name = "dbchs$HelptextCategory")
public class HelptextCategory extends StandardEntity {
    private static final long serialVersionUID = -5133260760378001550L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @NotNull
    @Column(name = "CODE", nullable = false)
    protected String code;

    @Column(name = "CONTEXT_INDEPENDENT", nullable = false)
    protected Boolean contextIndependent = false;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setContextIndependent(Boolean contextIndependent) {
        this.contextIndependent = contextIndependent;
    }

    public Boolean getContextIndependent() {
        return contextIndependent;
    }


}