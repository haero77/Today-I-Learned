package org.festilog.springtransaction.propagation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그 엔티티
 */
@Entity
@Getter
@Setter
public class LogMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String message;

    public LogMessage() {
    }

    public LogMessage(final String message) {
        this.message = message;
    }
}
