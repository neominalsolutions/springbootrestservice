package com.akbank.springbootrestservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akbank.springbootrestservice.dtos.PostDto;
import com.akbank.springbootrestservice.entities.Post;
import com.akbank.springbootrestservice.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/posts")
@Tag(name = "POST API", description = "Makale ekleme")
public class PostsController { // Api Endpointleri veri kaynağı döndüleri için s takısı ile çoğullaştırılır.

  // api/posts (HTTPGET)
  // api/posts/{id} (HTTPGET)
  // api/posts (HTTP POST)
  // api/posts/{id} (HTTP PUT)
  // api/posts/{id} (HTT PATCH) güncellenecek bir entitynin belirli alanlarını
  // modifiye etme için embeden yapılarda kullanılır
  // userProfile:{username,password,settings:{theme:black}}
  // api/userProfile/{id}/settings (HTTP PATCH)
  // api/posts/{id} (HTTP DELETE)
  // api/posts/{id}/tags => (HTTP GET) 1 numaralı posta ait tag bilgileri

  // API HTTPSTATUS CODE STANDARS

  // GET StatusCode 200
  // POST Status 201 Created (Response ihtiyaç var.)
  // PUT, DELETE, PATCH 204 NoContent, 401, 400, 500, 403, 404
  // 401 UnAuthorized
  // 404 NotFound
  // 400 Bad Request
  // 500 Internal Server Error
  // 403 Forbidden Request
  // 422 Unproccesable Entity
  // /api/posts

  // properties dosyalarında key value cinsinden değerleri okumamızı sağlar. değerler değişen environmentlar için farklı şekilde okunabilir.
  @Value("key")
  String key;

  private PostService postService;

  public PostsController(PostService postService) {
    super();
    this.postService = postService;
  }

  @GetMapping("")
  @Operation(summary = "Post Listleme", description = "Bütün Postları Getir")
  public ResponseEntity<List<PostDto>> Get() {

    var response = this.postService.getPosts();

    return ResponseEntity.ok().body(response);
  }

  // api/posts/1
  @GetMapping("{id}")
  public ResponseEntity<PostDto> Get(@PathVariable(name = "id", required = true) String id) {

    var response = this.postService.getPostWithTags(id);

    return ResponseEntity.ok().body(response);
  }

  // api içerisine gönderilecek olan değerler dto formatında olmalı çıktı dto
  // formatında olmalı
  @PostMapping("") // 400 Status code
  public ResponseEntity<PostDto> Post(@Valid @RequestBody PostDto dto) {

    // return new ResponseEntity(HttpStatus.CREATED,null);

    this.postService.create(dto);

    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
  }

  // api/posts/1 HTTPUT
  @PutMapping("{id}")
  public ResponseEntity<?> Put(@PathVariable(name = "id") String id, @Valid @RequestBody PostDto dto) {

    // this.postService.update(id, dto);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    // return ResponseEntity.noContent().build();
  }

  // api/posts/1/tags/2 => post altındaki tag entitysini güncellemek modifiye
  // etmek için kullanılan bir yöntem.
  @PatchMapping("{postId}/tags/{tagId}")
  public ResponseEntity<?> Patch(@PathVariable(name = "postId") String postId,
      @PathVariable(name = "tagId") String tagId, @Valid @RequestBody PostDto dto) {

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    // return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> Delete(@PathVariable(name = "id") String id) {
    this.postService.delete(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
