package KoInMaster.TestModules.Posts;

import java.util.ArrayList;

public class PostListContainer {
    private PostList originalList;
    private PostList finalList;
    private ArrayList<PLATFORM> platformWhiteList;
    private ArrayList<String> nameWhiteList;

    public PostListContainer(PostList originalList){
        this.originalList=originalList;
    }

    public void setPlatformWhiteList(ArrayList<PLATFORM> platformWhiteList) {
        this.platformWhiteList = platformWhiteList;
    }

    public void setNameWhiteList(ArrayList<String> nameWhiteList) {
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
    public PostList getFinalList() {
        filter();
        return finalList;
    }
    public void filter(){
        finalList=new PostList();
        boolean flag=false;
        for(Post p:originalList) {
            flag=false;
            for (String name : nameWhiteList) {
                if(!flag) {
                    for (PLATFORM pla : platformWhiteList) {
                        if (p.getPlatform() == pla && p.getName().equals(name)) {
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
