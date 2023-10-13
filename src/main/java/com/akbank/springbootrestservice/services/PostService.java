package com.akbank.springbootrestservice.services;

import java.util.List;

import com.akbank.springbootrestservice.dtos.PostDto;

public interface PostService {

  List<PostDto> getPosts();

  PostDto getPostWithTags(String id);

  void create(PostDto dto);

  void update(String id, PostDto dto);

  void delete(String id);

}
