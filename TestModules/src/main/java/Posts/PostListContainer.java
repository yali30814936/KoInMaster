package Posts;

import java.util.List;

public class PostListContainer {
    private final PostList originalList;
    private PostList finalList;
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
    }

    public PostList getOriginalList() {
        return originalList;
    }

    public PostList getFinalList() {
        filter();
        return finalList;
    }

    public void filter(){
        finalList=new PostList();
        boolean flag;
        for(Post p:originalList) {
            flag=false;
            for (String name : nameWhiteList) {
                if(!flag) {
                    for (TYPE type : typeWhiteList) {
                        if (p.getType() == type && p.getName().equals(name)) {
                            finalList.add(p);
                            flag=true;
                            break;
                        }
                    }
                }
                else{
                    break;
                }
            }
        }
    }
}
