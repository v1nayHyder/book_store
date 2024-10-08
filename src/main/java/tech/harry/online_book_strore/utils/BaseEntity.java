package tech.harry.online_book_strore.utils;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity {
    private static final Logger logger = LoggerFactory.getLogger(BaseEntity.class);
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        LocalDateTime now=LocalDateTime.now() ;
        this.createdAt=now;
        this.updatedAt=now;
        logger.info("Created at: " + this.createdAt);

    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt=LocalDateTime.now();
        logger.info("Updated at: " + this.updatedAt);

    }
}
