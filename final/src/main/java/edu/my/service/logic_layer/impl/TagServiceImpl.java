package edu.my.service.logic_layer.impl;

import edu.my.data.entity.MovieHasTagEntity;
import edu.my.data.entity.TagEntity;
import edu.my.data.repository.MovieHasTagRepository;
import edu.my.data.repository.TagRepository;
import edu.my.exception.EntityIsNotFoundException;
import edu.my.service.logic_layer.TagService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TagServiceImpl implements TagService {
    @Inject
    TagRepository tagRepository;
    @Inject
    MovieHasTagRepository movieHasTagRepository;

    @Override
    public void add(TagEntity tagEntity) {
        tagRepository.persist(tagEntity);
    }

    @Override
    public List<TagEntity> getAll() {
        List<TagEntity> tagList = tagRepository.findAll().list();
        if (tagList.isEmpty())
            throw new EntityIsNotFoundException("Can't find any tags in DB.");
        return tagList;
    }

    @Override
    public TagEntity getById(Long id) {
        TagEntity notNullTag = tagRepository.findById(id);
        if (notNullTag == null)
            throw new EntityIsNotFoundException("Can't find tag with id=" + id + ".");
        return notNullTag;
    }

    @Override
    public void update(Long id, TagEntity tagData) {
        TagEntity notNullTag = tagRepository.findById(id);
        if (notNullTag == null)
            throw new EntityIsNotFoundException("Can't find tag with id=" + id + ". Nothing to update.");

        notNullTag.setName(tagData.getName());

        tagRepository.persist(notNullTag);
    }

    @Override
    public void deleteById(Long id) {
        TagEntity notNullTag = tagRepository.findById(id);
        if (notNullTag == null)
            throw new EntityIsNotFoundException("Can't find tag with id=" + id + ". Nothing to delete.");

        for (MovieHasTagEntity link : notNullTag.getLinks()) {
            link.getMovieEntity().getLinks().remove(link);
            movieHasTagRepository.delete(link);
        }
        notNullTag.setLinks(null);

        tagRepository.deleteById(id);
    }
}
