package com.springbootModules.learning.CaffineCacheDemo.TopicFlow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class TopicEntity{
    @Id
    @Column(updatable = false)
    Long id;
    String name;
    String description;
}