package org.nice.services;


import org.nice.Utils;
import org.nice.models.PokemonType;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchService {
    public static SearchService getInstance() {
        if(instance == null) {
            instance = new SearchService();
        }
        return instance;
    }
    public Observable<String> onSearchStringChange() {
        return searchString.asObservable().distinctUntilChanged();
    }

    public Observable<List<PokemonType>> onTypeFilterChange() {
        return typeFilters;
    }

    public List<PokemonType> currentTypeFilters() {
        return typeFilters.getValue();
    }

    public void setSearchString(String v) {
        searchString.onNext(Utils.escapeRegex(v));
    }
    public void setTypeFilters(List<PokemonType> l) {
        typeFilters.onNext(l);
    }
    private static SearchService instance;
    private BehaviorSubject<String> searchString = BehaviorSubject.create("");

    private BehaviorSubject<List<PokemonType>> typeFilters = BehaviorSubject.create(
            List.of()
    );

    private SearchService() {

    }
}
