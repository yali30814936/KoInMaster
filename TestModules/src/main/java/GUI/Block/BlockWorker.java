package GUI.Block;


import Posts.Post;
import Posts.PostList;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockWorker extends SwingWorker<Object, Object> {
    private final PostList postsList;
    private final JPanel parent;
    private final List<BlockGenerator> generators;

    public BlockWorker(PostList postList, JPanel parent) throws IOException {
        this.postsList = postList;
        this.parent = parent;
        generators = new ArrayList<>();
    }

    @Override
    protected Object doInBackground() {
        parent.setLayout(new BoxLayout(parent,BoxLayout.PAGE_AXIS));
        Box vBox = Box.createVerticalBox();
        Box hBox;
        for(Post post:postsList){
            hBox = Box.createHorizontalBox();
            BlockGenerator generator = new BlockGenerator(hBox, post);
            vBox.add(hBox);
            vBox.add(Box.createVerticalStrut(20));
            generators.add(generator);
            generator.execute();
        }
//        vBox.setMaximumSize(new Dimension(1300, Integer.MAX_VALUE));
        parent.add(vBox);
        parent.revalidate();
        return null;
    }

    public void terminate() {
        for (BlockGenerator g:generators)
            g.terminate();
        cancel(true);
    }
}
