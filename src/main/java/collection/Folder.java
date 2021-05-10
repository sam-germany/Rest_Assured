package collection;

import java.util.List;

public class Folder {

    private String name;
    List<RequestRoot> requestRoot;        // he take it List , as it can be many requestRoot objects in one request
                                                    // and we have defined as Array in the Request
    public Folder() {   }

    public Folder(String name, List<RequestRoot> requestRoot) {
        this.name = name;
        this.requestRoot = requestRoot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RequestRoot> getRequestRoot() {
        return requestRoot;
    }

    public void setRequestRoot(List<RequestRoot> requestRoot) {
        this.requestRoot = requestRoot;
    }
}
