package com.springbootModules.learning.CaffineCacheDemo.TopicFlow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TopicRequest {

    @NonNull
    Long id;
    @NonNull
    String name;
    String description;
}