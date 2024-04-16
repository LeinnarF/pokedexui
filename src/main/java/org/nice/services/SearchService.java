package org.nice.services;


import org.nice.models.PokemonType;
import rx.Observable;
import rx.subjects.PublishSubject;

import java.util.List;

public class SearchService {
    public static SearchService getInstance() {
        if(instance == null) {
            instance = new SearchService();
        }
        return instance;
    }
    public Observable<String> onSearchStringChange() {
        return searchString;
    }

    public Observable<List<PokemonType>> onTypeFilterChange() {
        return typeFilters;
    }

    public void setSearchString(String v) {
        searchString.onNext(v);
    }
    public void setTypeFilters(List<PokemonType> l) {
        typeFilters.onNext(l);
    }
    private static SearchService instance;
    private PublishSubject<String> searchString = PublishSubject.create();

    private PublishSubject<List<PokemonType>> typeFilters = PublishSubject.create();

    private SearchService() {

    }
}
