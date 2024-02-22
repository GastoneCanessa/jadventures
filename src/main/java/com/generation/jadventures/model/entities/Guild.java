package com.generation.jadventures.model.entities;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Guild 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name, authentication_seal, seal_img_url, hq_address;
    private Integer n_employees;

    public void setAuthentication_seal(String authentication_seal) {
        if (authentication_seal == null || authentication_seal.length() < 8) {
            throw new IllegalArgumentException("Il sigillo di autenticazione deve contenere almeno 8 caratteri");
        }

        // Controlla la presenza di almeno una minuscola, una maiuscola, un numero e un carattere speciale
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$");
        Matcher matcher = pattern.matcher(authentication_seal);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Il sigillo di autenticazione deve contenere almeno una minuscola, una maiuscola, un numero e un carattere speciale");
        }

        this.authentication_seal = authentication_seal;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "patron",fetch = FetchType.EAGER)
    private List<Quest> posted_quests;
}
