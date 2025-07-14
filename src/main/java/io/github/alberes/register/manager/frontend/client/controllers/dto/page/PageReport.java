package io.github.alberes.register.manager.frontend.client.controllers.dto.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageReport<T> {

    private List<T> content;

    private Pageable pageable;

    private int totalPages;

    private int totalElements;

    private boolean last;

    private int size;

    private int number;

    private Sort sort;

    private int numberOfElements;

    private boolean first;

    private boolean empty;

    private String orderBy;

    private String direction;

    public int getFirstPageNumber(){
        return 0;
    }

    public int getLastPageNumber(){
        return this.totalPages - 1;
    }

    public int getNextPageNumber(){
        if(this.empty){
            return 0;
        }else if(this.last){
            return totalPages;
        }
        return number + 1;
    }

    public int getPreviousPageNumber(){
        if(this.empty || this.first){
            return 0;
        }
        return number - 1;

    }

}
