package com.unbabel.challenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

//@Entity
//@Setter @Getter
public class LanguageModel
{
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String shortname;

    @ManyToMany()
    @JoinTable(name="LANGUAGEPAIRMODEL", joinColumns = {@JoinColumn(name="ID")},
    inverseJoinColumns = {@JoinColumn(name="SOURCELANGUAGEID")})
    private Set<LanguageModel> sourceLanguage = new HashSet<>();

    @ManyToMany(mappedBy = "sourceLanguage")
    private Set<LanguageModel> targetLanguage = new HashSet<>();
*/

}
