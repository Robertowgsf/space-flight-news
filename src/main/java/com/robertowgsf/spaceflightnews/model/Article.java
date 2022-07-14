package com.robertowgsf.spaceflightnews.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean featured;
    private String title;
    private String url;
    private String imageUrl;
    private String newsSite;
    private String summary;
    private String publishedAt;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Set<Launch> launches;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Set<Event> events;
}
