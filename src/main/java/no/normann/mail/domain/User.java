
package no.normann.mail.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Patrick Baumgartner
 * @since 1.0
 */
@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date lastEmail;

    private String name;

    private String email;

    private boolean sendMail = true;
}
