package Posts;

import java.util.List;
import java.util.stream.Collectors;

public class PostListContainer {
    private final PostList originalList;
    private PostList filteredList;
    private List<String> nameWhiteList;
    private List<TYPE> typeWhiteList;

    public PostListContainer(PostList originalList){
        this.originalList=originalList;
    }

    public void setTypeWhiteList(List<TYPE> typeWhiteList) {
        this.typeWhiteList = typeWhiteList;
    }

    public void setNameWhiteList(List<String> nameWhiteList) {
        this.nameWhiteList = nameWhiteList;
    }

    public void add(Post post){
        boolean hasInput=false;
        for(int i=0;i<originalList.size();i++){
            if(originalList.get(i).getUrl().equals(post.getUrl())){
                originalList.set(i,post);
                hasInput=true;
                break;
            }

        }
        if(!hasInput){
            originalList.add(post);
        }
    }

    public void add(PostList posts){
        boolean hasInput=false;
        for(Post p:posts){
            for(int i=0;i<originalList.size();i++){
               if(originalList.get(i).getUrl().equals(p.getUrl())){
                   originalList.set(i,p);
                   hasInput=true;
                   break;
               }
            }
            if(!hasInput){
                originalList.add(p);
            }
            hasInput=false;
        }

        originalList.sort(new PostSort().reversed());
    }

    public PostList getOriginalList() {
        return originalList;
    }

    public PostList getFilteredList() {
        originalList.sort(new PostSort().reversed());

        filter();
        return filteredList;
    }

    public void filter(){
        filteredList = new PostList(originalList.stream()
                                                .filter(post -> nameWhiteList.stream()
                                                                             .anyMatch(s -> s.equals(post.name)))
                                                .filter(post -> typeWhiteList.stream()
                                                                             .anyMatch(type -> type == post.getType()))
                                                .collect(Collectors.toList()));
    }
}
