package br.inf.audasi.domain.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author renatomoitinhodias@gmail.com
 */
public class Sample implements Serializable {

    @NotNull(message = "{error.id.notnull}")
    private Long id;

    @NotNull(message = "{error.name.notnull}")
    @NotEmpty(message = "{error.name.notEmpty}")
    private String name;

    @NotNull(message = "{error.age.notnull}")
    private Integer age;

    @Email(message = "{error.email.notValid}")
    @NotEmpty(message = "{error.email.notEmpty}")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
