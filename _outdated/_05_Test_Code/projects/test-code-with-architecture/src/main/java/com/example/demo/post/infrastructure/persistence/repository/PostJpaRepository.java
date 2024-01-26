package com.example.demo.post.infrastructure.persistence.repository;

import com.example.demo.post.infrastructure.persistence.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

}