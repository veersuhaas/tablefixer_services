package com.ivo.app.services.repository;

import com.ivo.app.services.entity.EventDetailsEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventDetailsTransRepository extends CrudRepository<EventDetailsEntity, Long> {
}
