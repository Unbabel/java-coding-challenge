package com.unbabel.challenge.configuration.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter
public class SessionAttributes
{
    private HashMap<String, Object> attributes = new HashMap<>();
}
