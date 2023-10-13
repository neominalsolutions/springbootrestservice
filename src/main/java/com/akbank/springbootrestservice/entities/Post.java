package com.akbank.springbootrestservice.entities;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Post")
@NoArgsConstructor
@AllArgsConstructor
// @Schema(hidden = true)
// @Hidden
public class Post {

  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "PostId")
  private String Id;

  @Column(name = "Title", nullable = false)
  private String title;

  @Column(name = "Body", nullable = true)
  private String body;

  @Column(name = "Published")
  private boolean published;

  @Temporal(TemporalType.DATE) // Utils.Date, SqlDate, java.time.LocalDate
  @Column(name = "PublishedAt", nullable = true)
  private LocalDate publishedAt;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "PostTag", joinColumns = {
      @JoinColumn(name = "postId") }, inverseJoinColumns = {
          @JoinColumn(name = "tagId")
      })
  private List<Tag> tags;

}
