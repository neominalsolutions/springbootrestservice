package com.akbank.springbootrestservice.entities;

import java.util.List;

import org.hibernate.query.sqm.FetchClauseType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Tag")
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "TagId")
  private int Id;

  @Column(name = "Name")
  private String name;

  @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
  private List<Post> posts;

}
