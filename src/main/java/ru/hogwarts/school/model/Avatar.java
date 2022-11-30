package ru.hogwarts.school.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@DynamicUpdate
@DynamicInsert
@Slf4j
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "file_size")
    private long fileSize;
    @Column(name = "media_type")
    private String mediaType;
    @Column(name = "data")
    private byte[] data;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Avatar avatar = (Avatar) o;
        return id != null && Objects.equals(id, avatar.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @PostRemove
    public void logAvatarRemoval() {
        log.info("Avatar with id={} removed", id);
    }

}