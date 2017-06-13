package com.example.girls;

import java.util.List;

public class Girls {

    public Boolean error;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Results> results;


    public List<Results> getResultsList() {
        return results;
    }

    public void setResultsList(List<Results> resultsList) {
        this.results = resultsList;
    }

/*

    public String toString() {
        return "{ results:" +"error="+error+ results + "}";
    }
*/


}

