package com.btcag.bootcamp;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
public class RequestsGenerics<T> {

    private List<T> bots = new ArrayList<T>();

    public void createABot(T bot){
        bots.add(bot);
    }

    public List<T> getBots(){
        return bots;
    }

}