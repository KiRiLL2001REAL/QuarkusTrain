package edu.my.data.repository;

import edu.my.data.entity.MovieHasTagEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieHasTagRepository implements PanacheRepository<MovieHasTagEntity> {
}
