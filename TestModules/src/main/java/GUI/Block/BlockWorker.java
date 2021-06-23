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

    public BlockWorker(PostList postList, Box parent) throws IOException {
        this.postsList = postList;
        this.parent = parent;
        generators = new ArrayList<>();
    }

    @Override
    protected Object doInBackground() {
        Box hBox;
        for(Post post:postsList){
            hBox = Box.createHorizontalBox();
            hBox.setMaximumSize(new Dimension(1550, Integer.MAX_VALUE));
            BlockGenerator generator = new BlockGenerator(hBox, post);
            parent.add(hBox);
            parent.add(Box.createVerticalStrut(20));
            generators.add(generator);
            generator.execute();
        }
        parent.setMaximumSize(new Dimension(1550, Integer.MAX_VALUE));
        parent.revalidate();
        return null;
    }

    public void terminate() {
        for (BlockGenerator g:generators)
            g.terminate();
        cancel(true);
    }
}
