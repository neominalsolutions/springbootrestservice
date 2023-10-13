package com.akbank.springbootrestservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.akbank.springbootrestservice.dtos.PostDto;
import com.akbank.springbootrestservice.entities.Post;
import com.akbank.springbootrestservice.repositories.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class PostServiceImp implements PostService {

  // istediğimiz kadar repository ile çalışabiliriz ve service

  private PostRepository postRepo;
  private ModelMapper mapper;

  public PostServiceImp(PostRepository postRepo, ModelMapper mapper) {
    super();
    this.postRepo = postRepo;
    this.mapper = mapper;
  }

  @Override
  public List<PostDto> getPosts() {
    var posts = this.postRepo.findAll();
    var dtos = posts
        .stream()
        .map(user -> mapper.map(user, PostDto.class))
        .collect(Collectors.toList());

    return dtos;
  }

  @Override
  public PostDto getPostWithTags(String id) {
    var post = this.postRepo.findById(id);
    var postDto = this.mapper.map(post, PostDto.class);

    return postDto;
  }

  @Override
  @Transactional
  public void create(PostDto dto) {
    var postEntity = this.mapper.map(dto, Post.class);
    this.postRepo.save(postEntity);
  }

  @Override
  @Transactional
  public void update(String id, PostDto dto) {
    var post = this.postRepo.findById(id).get();
    post.setTitle(dto.getTitle());
    post.setBody(dto.getBody());
    post.setPublished(dto.isPublished());
    post.setPublishedAt(dto.getPublishedAt());
    this.postRepo.save(post);
  }

  @Override
  @Transactional
  public void delete(String id) {
    var post = this.postRepo.findById(id).get();
    this.postRepo.delete(post);
  }

}
