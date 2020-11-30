package com.example.library;

public class Word {
    private String word;
    private String definition;

    public Word(){
        this.word="";
        this.definition="";
    }

    public Word(String word, String definition){
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return word + '\n' + definition + '\n';
    }
}
