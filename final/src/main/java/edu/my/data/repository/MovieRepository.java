package edu.my.data.repository;

import edu.my.data.entity.MovieEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<MovieEntity> {
}
