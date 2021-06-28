package GUI.Block;


import Posts.Post;
import Posts.PostList;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockWorker extends SwingWorker<Object, Object> {
    private final PostList postsList;
    private final Box parent;
    private final List<BlockGenerator> generators;
    private final int unit = 20;
    private int cursor = 0;

    public BlockWorker(PostList postList, Box parent) throws IOException {
        this.postsList = postList;
        this.parent = parent;
        generators = new ArrayList<>();
    }

    @Override
    protected Object doInBackground() {
        Box hBox;
        Component struct;
        for(Post post:postsList){
            hBox = Box.createHorizontalBox();
            struct = Box.createVerticalStrut(20);
            BlockGenerator generator = new BlockGenerator(hBox, struct, post);
            parent.add(hBox);
            struct.setVisible(false);
            parent.add(struct);
            generators.add(generator);
        }
        loadMore();
        return null;
    }

    public void loadMore() {
        for (int i = 0; i < unit; i++)
            if (cursor+i < generators.size())
                generators.get(cursor+i).execute();
        cursor += unit;
    }

    public void terminate() {
        for (BlockGenerator g:generators)
            g.cancel(true);
        cancel(true);
    }
}
